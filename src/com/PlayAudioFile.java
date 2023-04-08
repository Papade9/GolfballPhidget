package com;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.time.LocalDateTime;

public class PlayAudioFile {
    private static Thread _playThread;
    private static SoundRunnable _soundRunnable = null;
    private static String _lastFilePlayed = null;

    private static class SoundRunnable implements Runnable{
        private Clip clip = null;
        private File file;
        private LocalDateTime startTime = null;

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
                MainScreen.getInstance().addLogEntry("Bad audio file inputStream: " + url + ":" + ex.getMessage());
            }
        }

        public boolean isRunning(){
            return startTime == null || clip.isRunning();
        }

        @Override
        public void run() {
            startTime = LocalDateTime.now();
            clip.start();
        }

        public void stop(){
            clip.stop();
            clip.close();
        }
    }

    public static synchronized void playSound(final String url,boolean interrupt,boolean repeatSame) {
        if(_playThread == null) {
            _soundRunnable = new SoundRunnable();
            _playThread = new Thread(_soundRunnable);
            _soundRunnable.setFile(url);
            _playThread.start();
        }else{
            if(!interrupt) {
                try {
                    Thread.sleep(10);
                    while (_soundRunnable.isRunning()) {
                        Thread.sleep(1);
                    }
                } catch (InterruptedException ex) {

                }
            }else{
                _lastFilePlayed = url;
            }
            if(!interrupt || _lastFilePlayed != null && _lastFilePlayed.equals(url) && repeatSame || _lastFilePlayed != null && !_lastFilePlayed.equals(url) ||_lastFilePlayed == null) {
                _soundRunnable.stop();
                _soundRunnable.setFile(url);
                _soundRunnable.run();
            }
        }

    }
}
