package com;

import java.time.LocalDateTime;

public class Main {
    private static Integer _mode = 0;
    private static Integer DEBUG = 2;
    private static Integer NORMAL = 0;
    public static Integer PHIDGET_TEST = 1;
    public static Boolean restart = false;
    private static String VERSION = "2023-07-25 13:20";

    public static void main(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if(arg.toLowerCase().equals("-d"))
                    _mode = DEBUG;
                else if(arg.toLowerCase().equals("-p"))
                    _mode = PHIDGET_TEST;
                else if (arg.toLowerCase().equals("-restart"))
                        restart = true;
            }
        }

        Settings.init("golfballConfig.xml", "Golfland Golfball Phidget Settings");
        MainScreen screen = MainScreen.getInstance();
        screen.setRestart(restart);
        screen.setMode(_mode);
        screen.addLogEntry("VERSION: " + VERSION);
        screen.addLogEntry("Start time: " + LocalDateTime.now().format(Util.dtfDateTimeLong));
        screen.setVisible(true);
    }
}