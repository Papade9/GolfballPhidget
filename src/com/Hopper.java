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
    private boolean enabled = true;
    private Thread motorTimeoutThread;
    private String settingNameXML;
    private LocalDateTime _lastDispenseSensor = LocalDateTime.now();

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

    public Hopper(String colorName, Integer number,String settingXMLName){
        hopperColor = colorName;
        hopperNumber = number;
        settingNameXML = settingXMLName;
        try{
            button = new DigitalInput();
            button.setHubPort(0);
            button.setChannel(HopperConfig.getHopperConfig(number).getButtonChannel());
            button.addStateChangeListener(digitalInputStateChangeEvent -> {
                if(enabled && MainScreen.getInstance().getBallCredits() > 0 || MainScreen.getInstance().getMode().equals(MainScreen.TEST)) {
                    if (MainScreen.getInstance().debounceTest()) {
                        if(MainScreen.getInstance().getMode().equals(MainScreen.TEST))
                            MainScreen.getInstance().addLogEntry("Button " + number + " pressed");
                        try {
                            motor.setState(true);
                            setTimeout();
                        } catch (PhidgetException mex) {
                            setEnabled(false);
                        }
                    }
                }else if(MainScreen.getInstance().getBallCredits() > 0){
//                    PlayAudioFile.playSound("./audio/ballout.wav",true);
                    PlayAudioFile.playSound("./audio/ball-out.wav",true,true);
                }
            });
            button.open();
            System.out.println("button opened");
            dispenseSensor = new DigitalInput();
            dispenseSensor.setHubPort(0);
            dispenseSensor.setChannel(HopperConfig.getHopperConfig(number).getDispenseSensorChannel());
            motor = new DigitalOutput();
            motor.setHubPort(HopperConfig.getHopperConfig(number).getMotorChannel()[0]);
            motor.setChannel(HopperConfig.getHopperConfig(number).getMotorChannel()[1]);
            motor.open();
            ringLight = new DigitalOutput();
            ringLight.setHubPort(HopperConfig.getHopperConfig(number).getRingChannel()[0]);
            ringLight.setChannel(HopperConfig.getHopperConfig(number).getRingChannel()[1]);
            ringLight.open();
            try {
                while (!ringLight.getAttached())
                    Thread.sleep(1000);
            }catch(InterruptedException ie){
            }
            ringLight.setLEDForwardVoltage(LEDForwardVoltage.VOLTS_5_6);
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
                    if(!digitalInputStateChangeEvent.getState() && motor.getState() && Duration.between(MainScreen.getInstance().getStartTime(), LocalDateTime.now()).toMillis() > 10000L && Duration.between(_lastDispenseSensor,LocalDateTime.now()).toMillis() > 2000L) {
                        _lastDispenseSensor = LocalDateTime.now();
                        motor.setState(false);
                        buttonLight.setState(true);
                        if (MainScreen.getInstance().getMode().equals(MainScreen.TEST))
                            MainScreen.getInstance().addLogEntry("Dispense Sensor " + number + " sensed");
                        MainScreen.getInstance().decrementCredits();
                        MainScreen.getInstance().addLogEntry("Ball credits remaining: " + MainScreen.getInstance().getBallCredits());
                        PlayAudioFile.playSound("./audio/ball-vend.wav",true,true);
                        if (MainScreen.getInstance().getBallCredits() > 0 && MainScreen.getInstance().getBallCredits() < 15) {
                            PlayAudioFile.playSound("./audio/youHave.wav",false,false);
                            if(MainScreen.getInstance().getBallCredits() > 0)
                                PlayAudioFile.playSound("./audio/" + MainScreen.getInstance().getBallCredits() + ".wav",false,false);
                            PlayAudioFile.playSound("./audio/ballRemaining.wav",false,false);
                        } else {
                            PlayAudioFile.playSound("./audio/haveGreatGame.wav",true,true);
                        }
                        if (!enabled)
                            enabled = true;
                    }else if(motor.getState()){
                        motor.setState(false);
                    }
                }catch(PhidgetException dex){
                    setEnabled(false);
                }
            });
            dispenseSensor.open();
        }catch(PhidgetException ex){
            setEnabled(false);
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

    private void setTimeout(){
        motorTimeoutThread = new Thread(new Runnable() {
            LocalDateTime start = LocalDateTime.now();
            @Override
            public void run() {
                while(Duration.between(start,LocalDateTime.now()).getSeconds() < 3L){
                }
                try {
                    if (motor.getState()) {
                        motor.setState(false);
                        setEnabled(false);
                    }
                }catch(PhidgetException ex){
                    setEnabled(false);
                }finally{
                    motorTimeoutThread = null;
                }
            }
        });
        motorTimeoutThread.start();
    }
    public void setEnabled(boolean enable){
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
                setTimeout();
            }
        }catch(PhidgetException ex){
            setEnabled(false);
            MainScreen.getInstance().addLogEntry("testPhidgetError: " + ex);
        }
    }
}
