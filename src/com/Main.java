package com;

public class Main {
    private static Integer _mode = 0;
    private static Integer DEBUG = 1;
    private static Integer NORMAL = 0;
    private static String VERSION = "2022-11-10 18:00";

    public static void main(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if(arg.toLowerCase().equals("-d"))
                    _mode = DEBUG;
            }
        }

        Settings.init("golfballConfig.xml", "Golfland Golfball Phidget Settings");
        MainScreen screen = MainScreen.getInstance();
        screen.addLogEntry("VERSION: " + VERSION);
        screen.setVisible(true);
    }
}