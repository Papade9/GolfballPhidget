package com;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlayAudioFile {
    private static Thread _playThread;
    private static SoundRunnable _soundRunnable = null;

    private static class SoundRunnable implements Runnable{
        private Clip clip = null;
        private File file;

        public SoundRunnable(){
            try {
                if (clip == null)
                    clip = AudioSystem.getClip();
            }catch(Exception e){
                MainScreen.getInstance().addLogEntry("Could not open audio clip");
            }
        }

        public void setFile(String url){
            file = new File(url);
            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream((file.toURI().toURL()));
                clip.open(inputStream);
            }catch(Exception ex){
                MainScreen.getInstance().addLogEntry("Bad audio file inputStream");
            }
        }

        @Override
        public void run() {
            clip.start();
        }

        public void stop(){
            clip.stop();
            clip.close();
        }
    }

    public static synchronized void playSound(final String url) {
        if(_playThread == null) {
            _soundRunnable = new SoundRunnable();
            _playThread = new Thread(_soundRunnable);
            _soundRunnable.setFile(url);
            _playThread.start();
        }else{
            _soundRunnable.stop();
            _soundRunnable.setFile(url);
            _soundRunnable.run();
        }

    }
}
