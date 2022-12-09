package com;

public enum HopperConfig {
    Hopper1(new int[]{2,2},6,new int[]{1,0},0,new int[]{4,0}),
    Hopper2(new int[]{2,3},7,new int[]{1,1},1,new int[]{4,1}),
    Hopper3(new int[]{3,0},8,new int[]{1,2},2,new int[]{4,2}),
    Hopper4(new int[]{3,1},9,new int[]{1,3},3,new int[]{4,3}),
    Hopper5(new int[]{3,2},10,new int[]{2,0},4,new int[]{5,0}),
    Hopper6(new int[]{3,3},11,new int[]{2,1},5,new int[]{5,1});
    //
    private final int[] ringChannel;//hub, channel
    private final int buttonChannel;
    private final int[] buttonLightChannel;

    public static String getPort(Integer hub,Integer port){
        if(Hopper1.getRingChannel()[0] == hub && Hopper1.getRingChannel()[1] == port)
            return "hopper1 Ring";
        else if(Hopper2.getRingChannel()[0] == hub && Hopper2.getRingChannel()[1] == port)
            return "hopper2 Ring";
        else if(Hopper3.getRingChannel()[0] == hub && Hopper3.getRingChannel()[1] == port)
            return "hopper3 Ring";
        else if(Hopper4.getRingChannel()[0] == hub && Hopper4.getRingChannel()[1] == port)
            return "hopper4 Ring";
        else if(Hopper5.getRingChannel()[0] == hub && Hopper5.getRingChannel()[1] == port)
            return "hopper5 Ring";
        else if(Hopper6.getRingChannel()[0] == hub && Hopper6.getRingChannel()[1] == port)
            return "hopper6 Ring";
        else if(Hopper1.getMotorChannel()[0] == hub && Hopper1.getMotorChannel()[1] == port)
            return "hopper1 Motor";
        else if(Hopper2.getMotorChannel()[0] == hub && Hopper2.getMotorChannel()[1] == port)
            return "hopper2 Motor";
        else if(Hopper3.getMotorChannel()[0] == hub && Hopper3.getMotorChannel()[1] == port)
            return "hopper3 Motor";
        else if(Hopper4.getMotorChannel()[0] == hub && Hopper4.getMotorChannel()[1] == port)
            return "hopper4 Motor";
        else if(Hopper5.getMotorChannel()[0] == hub && Hopper5.getMotorChannel()[1] == port)
            return "hopper5 Motor";
        else if(Hopper6.getMotorChannel()[0] == hub && Hopper6.getMotorChannel()[1] == port)
            return "hopper6 Motor";
        else if(Hopper1.getButtonLightChannel()[0] == hub && Hopper1.getButtonLightChannel()[1] == port)
            return "hopper1 Motor";
        else if(Hopper2.getButtonLightChannel()[0] == hub && Hopper2.getButtonLightChannel()[1] == port)
            return "hopper2 Motor";
        else if(Hopper3.getButtonLightChannel()[0] == hub && Hopper3.getButtonLightChannel()[1] == port)
            return "hopper3 Motor";
        else if(Hopper4.getButtonLightChannel()[0] == hub && Hopper4.getButtonLightChannel()[1] == port)
            return "hopper4 Motor";
        else if(Hopper5.getButtonLightChannel()[0] == hub && Hopper5.getButtonLightChannel()[1] == port)
            return "hopper5 Motor";
        else if(Hopper6.getButtonLightChannel()[0] == hub && Hopper6.getButtonLightChannel()[1] == port)
            return "hopper6 Motor";
        else return "Unknown";
    }

    public int[] getButtonLightChannel() {
        return buttonLightChannel;
    }

    public int[] getRingChannel() {
        return ringChannel;
    }

    public int getButtonChannel() {
        return buttonChannel;
    }

    public int[] getMotorChannel() {
        return motorChannel;
    }

    public int getDispenseSensorChannel() {
        return dispenseSensorChannel;
    }

    private final int[] motorChannel;
    private final int dispenseSensorChannel;
    HopperConfig(int[] ring,int btn,int[] motor,int dispense,int[] btnLight){
        this.ringChannel = ring;
        this.buttonChannel = btn;
        this.motorChannel = motor;
        this.dispenseSensorChannel = dispense;
        this.buttonLightChannel = btnLight;
    }
    public static HopperConfig getHopperConfig(int hopperNumber){
        switch(hopperNumber){
            case 1:
                return Hopper1;
            case 2:
                return Hopper2;
            case 3:
                return Hopper3;
            case 4:
                return Hopper4;
            case 5:
                return Hopper5;
            default:
                return Hopper6;
        }
    }
}
