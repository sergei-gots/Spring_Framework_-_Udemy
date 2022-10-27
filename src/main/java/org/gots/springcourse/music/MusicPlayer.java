package org.gots.springcourse.music;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicPlayer {
    private Music music;

    private List<Music> musicList = new ArrayList<>();
    private String name;
    private int volume;



    //Inversion of Control and Dependency Injection are here:
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic() { playMusic(music); }
    public void playMusic(Music music) {
        System.out.println("Playing: " + music.getSong());
    }

    public void playMusicList() {
        System.out.println("Playing a music list:");
        for (Music music: musicList) {
            System.out.print('\t');
            playMusic(music);
        }
    }
    public MusicPlayer() {
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
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

    public void printInfo() {
        System.out.println("musicPlayer.name = " + getName());
        System.out.println("musicPlayer.volume = " + getVolume());
    }
}
