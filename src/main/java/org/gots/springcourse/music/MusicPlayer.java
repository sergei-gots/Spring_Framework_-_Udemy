package org.gots.springcourse.music;

public class MusicPlayer {
    private Music music;

    //Inversion of Control is here:
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }


}
