package org.gots.springcourse.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicPlayer_Autowired implements IMusicPlayer {

    //@Autowired
    private ClassicalMusic classicalMusic;
    private RockMusic rockMusic;



    @Autowired
    public MusicPlayer_Autowired(ClassicalMusic classicalMusic, RockMusic rockMusic) {

        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }

    //@Autowired
    public void somethingAboutSetMusic(ClassicalMusic classicalMusic) {
        this.classicalMusic = classicalMusic;
    }

    public void playMusic() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Playing:  " + classicalMusic.getSong() + ";" +
                "\nPlaying: " + rockMusic.getSong();
    }

}
