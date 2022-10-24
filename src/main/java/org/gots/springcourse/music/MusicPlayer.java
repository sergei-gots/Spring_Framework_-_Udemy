package org.gots.springcourse.music;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private Music music;

    private List<Music> musicList = new ArrayList<>();
    private String name;
    private int volume;



    //Inversion of Control and Dependency Injection are here:
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }

    public MusicPlayer() {
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
