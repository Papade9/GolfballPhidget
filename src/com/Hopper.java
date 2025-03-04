package com;

import com.phidget22.*;

import java.time.Duration;
import java.time.LocalDateTime;

public class Hopper {
    private Integer hopperNumber;
    private DigitalOutput motor;
    private DigitalInput button;
    private DigitalInput dispenseSensor;
    private String hopperColor;
    private DigitalOutput ringLight;
    private DigitalOutput buttonLight;
    private DigitalOutput agitator;
    private boolean enabled = true;
    private Thread motorTimeoutThread;
    private String settingNameXML;
    private LocalDateTime _lastDispenseSensor = LocalDateTime.now();
    private LocalDateTime _lastTextSent = null;
    private Integer _modTextCount = 0;

    public String getHopperColor(){return hopperColor;}

    public Integer getHopperNumber(){return hopperNumber;}

    public String getSettingNameXML(){return settingNameXML;}

    public void flashRing(){
        Thread flashThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ringLight.setState(false);
                    Thread.sleep(500);
                    ringLight.setState(true);
                }catch(PhidgetException | InterruptedException ex){

                }
            }
        });
        flashThread.start();
    }

    public void dispense(boolean agitated){
        if((enabled || agitated) && MainScreen.getInstance().getBallCredits() > 0 || MainScreen.getInstance().getMode().equals(MainScreen.TEST)) {
            if (MainScreen.getInstance().debounceTest()) {
                if(MainScreen.getInstance().getMode().equals(MainScreen.TEST))
                    MainScreen.getInstance().addLogEntry("Button " + hopperNumber + " pressed");
                try {
                    motor.setState(true);
                    setTimeout(agitated);
                } catch (PhidgetException mex) {
                    setEnabled(false,false);
                }
            }
        }else if(MainScreen.getInstance().getBallCredits() > 0){
            PlayAudioFile.playSound("./audio/ball-out.wav",true,true);
        }
    }

    public boolean motorRunning(){
        try {
            return motor.getState();
        }catch(PhidgetException ex){
            return false;
        }
    }

    public Hopper(String colorName, Integer number,String settingXMLName){
        hopperColor = colorName;
        hopperNumber = number;
        settingNameXML = settingXMLName;
        try{
            button = new DigitalInput();
            button.setHubPort(0);
            button.setChannel(HopperConfig.getHopperConfig(number).getButtonChannel());
            button.addStateChangeListener(digitalInputStateChangeEvent -> {dispense(false);});
            button.open();
            System.out.println("button " + number + " opened");
            dispenseSensor = new DigitalInput();
            dispenseSensor.setHubPort(0);
            dispenseSensor.setChannel(HopperConfig.getHopperConfig(number).getDispenseSensorChannel());
            motor = new DigitalOutput();
            motor.setHubPort(HopperConfig.getHopperConfig(number).getMotorChannel()[0]);
            motor.setChannel(HopperConfig.getHopperConfig(number).getMotorChannel()[1]);
            motor.open();
            if(!Register.get().getRegister().getForceAllCCardValidate()) {
                agitator = new DigitalOutput();
                agitator.setHubPort(HopperConfig.getHopperConfig(number).getAgitatorChannel()[0]);
                agitator.setChannel(HopperConfig.getHopperConfig(number).getAgitatorChannel()[1]);
                agitator.open();
            }
            ringLight = new DigitalOutput();
            ringLight.setHubPort(HopperConfig.getHopperConfig(number).getRingChannel()[0]);
            ringLight.setChannel(HopperConfig.getHopperConfig(number).getRingChannel()[1]);
            ringLight.open();
            try {
                while (!ringLight.getAttached()) {
                    Thread.sleep(1000);
                }
            }catch(InterruptedException ie){
                System.out.println("ring light attachment interrupted for hopper " + number);
            }
//            System.out.println("ring Attached for " + number + " : " + ringLight.getAttached());
            ringLight.setLEDForwardVoltage(LEDForwardVoltage.VOLTS_5_6);
//            System.out.println("ring Light voltage for " + number + " : " + ringLight.getLEDForwardVoltage());
//            System.out.println("ring Light state for " + number + " : " + ringLight.getState());
            ringLight.setState(true);
            buttonLight = new DigitalOutput();
            buttonLight.setHubPort(HopperConfig.getHopperConfig(number).getButtonLightChannel()[0]);
            buttonLight.setChannel(HopperConfig.getHopperConfig(number).getButtonLightChannel()[1]);
            buttonLight.open();
            try {
                while (!buttonLight.getAttached())
                    Thread.sleep(1000);
            }catch(InterruptedException ie){
            }
            buttonLight.setLEDForwardVoltage(LEDForwardVoltage.VOLTS_5_6);
            buttonLight.setState(true);
            dispenseSensor.addStateChangeListener(digitalInputStateChangeEvent -> {
                try {
                    if(!digitalInputStateChangeEvent.getState() && motor.getState() && Duration.between(MainScreen.getInstance().getStartTime(), LocalDateTime.now()).toMillis() > 10000L && Duration.between(_lastDispenseSensor,LocalDateTime.now()).toMillis() > 700L) {
                        _lastDispenseSensor = LocalDateTime.now();
                        motor.setState(false);
                        buttonLight.setState(true);
                        if (MainScreen.getInstance().getMode().equals(MainScreen.TEST))
                            MainScreen.getInstance().addLogEntry("Dispense Sensor " + number + " sensed");
                        MainScreen.getInstance().decrementCredits();
                        MainScreen.getInstance().addLogEntry("Ball credits remaining: " + MainScreen.getInstance().getBallCredits());
                        PlayAudioFile.playSound("./audio/ball-vend.wav",true,true);
                        if (MainScreen.getInstance().getBallCredits() > 0 && MainScreen.getInstance().getBallCredits() < 15) {
                            if(!MainScreen.getInstance().highVolumeMode()) {
                                PlayAudioFile.playSound("./audio/youHave.wav", false, false);
                                if (MainScreen.getInstance().getBallCredits() > 0)
                                    PlayAudioFile.playSound("./audio/" + MainScreen.getInstance().getBallCredits() + ".wav", false, false);
                                PlayAudioFile.playSound("./audio/ballRemaining.wav", false, false);
                            }
                        } else if(MainScreen.getInstance().getBallCredits() == 0){
                            PlayAudioFile.playSound("./audio/haveGreatGame.wav",true,true);
                        }
                        if (!enabled)
                            enabled = true;
                    }else if(motor.getState()){
                        motor.setState(false);
                    }
                    MainScreen.getInstance().sensorState(getHopperNumber(),digitalInputStateChangeEvent.getState());
                }catch(PhidgetException dex){
                    setEnabled(false,false);
                }
            });
            dispenseSensor.open();
        }catch(PhidgetException ex){
            System.out.println("Hopper error: " + ex.getMessage());
            for(StackTraceElement el : ex.getStackTrace())
                System.out.println(el.toString());
//            setEnabled(false,false);
            //MainScreen.getInstance().addLogEntry("Phidget Error: " + ex.getMessage());
        }
    }

    public void closePorts(){
        try {
            if(button != null)
                button.close();
            if(dispenseSensor != null)
                dispenseSensor.close();
            if(buttonLight != null)
                buttonLight.close();
            if(motor != null)
                motor.close();
            if(ringLight != null)
                ringLight.close();
        }catch(PhidgetException ex){
            MainScreen.getInstance().addLogEntry("closeportsPhidgetError: " + ex);
        }
    }

    public void resetRingLight(){
        try {
            ringLight.setState(enabled);
        }catch(PhidgetException ex){

        }
    }

    private void setTimeout(boolean agitated){
        motorTimeoutThread = new Thread(new Runnable() {
            LocalDateTime start = LocalDateTime.now();
            @Override
            public void run() {
                while(Duration.between(start,LocalDateTime.now()).getSeconds() < 3L){
                }
                try {
                    if (motor.getState()) {
                        motor.setState(false);
                        if(!agitated) {
                            MainScreen.getInstance().addLogEntry("Starting agitator for hopper " + getHopperNumber());
                            agitator.setState(true);
                            try {
                                Thread.sleep(5500);
                            } catch (InterruptedException ie) {

                            }
                            agitator.setState(false);
                            dispense(true);
                        }else {
                            setEnabled(false, true);
                        }
                    }
                }catch(PhidgetException ex){
                    setEnabled(false,true);
                }finally{
                    motorTimeoutThread = null;
                }
            }
        });
        motorTimeoutThread.start();
    }
    public void setEnabled(boolean enable,boolean fromTimeout){
        if(!enable && fromTimeout) {
            if(_lastTextSent == null || Duration.between(_lastTextSent,LocalDateTime.now()).getSeconds() >= 900L) {
                if(_modTextCount > 1) {
                    String[] numbers = Register.get().getLocation().getTheftNotifyPhones().split(",");
                    MessagingService.getInstance().sendSMS(getHopperColor() + " golfballs are out in " + Register.get().getRegister().getRegisterShortName().trim(), "+1" + numbers[2], false);
                    MessagingService.getInstance().sendPuttsNotification("Golfball Machine",getHopperColor() + " golfballs are out in " + Register.get().getRegister().getRegisterShortName().trim(),true,true,null);
                    _lastTextSent = LocalDateTime.now();
                    _modTextCount = 0;
                }else{
                    if(!MessagingService.getInstance().sendPuttsNotification("Golfball Machine",getHopperColor() + " golfballs are out in " + Register.get().getRegister().getRegisterShortName().trim(),true,true,null))
                        MessagingService.getInstance().sendSMS(getHopperColor() + " golfballs are out in " + Register.get().getRegister().getRegisterShortName().trim(), "+1" + Register.get().getLocation().getModPhone(), false);
                    _lastTextSent = LocalDateTime.now();
                    _modTextCount++;
                }
            }
        }
        enabled = enable;
        try {
            buttonLight.setState(enable);
        }catch(PhidgetException ex){
            MainScreen.getInstance().addLogEntry("setEnabledPhidgeError: " + ex);
        }
    }

    public boolean isEnabled(){return enabled;}

    public void test(){
        try{
            if(Duration.between(MainScreen.getInstance().getStartTime(),LocalDateTime.now()).toMillis() > 10000L) {
                motor.setState(true);
                setTimeout(false);
            }
        }catch(PhidgetException ex){
            setEnabled(false,false);
            MainScreen.getInstance().addLogEntry("testPhidgetError: " + ex);
        }
    }
}
