package org.gots.springcourse.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer_Qualifier {

    /* @Autowired
    @Qualifier ("classicalMusic")
    private Music music1;

    @Autowired
    @Qualifier ("rockMusic")
    private Music music2;
    */

    private Music music1;
    private Music music2;
    public MusicPlayer_Qualifier(@Qualifier("classicalMusic") Music music1,
                                 @Qualifier("rockMusic") Music music2) {
        this.music1 = music1;
        this.music2 = music2;
    }

    public void playMusic() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Playing:\t" + music1.getSong() +
                ";\nPlaying:\t" + music2.getSong();
    }

    public static void lesson_12() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextComponents.xml");
        MusicPlayer_Qualifier musicPlayer = context.getBean("musicPlayer_Qualifier", MusicPlayer_Qualifier.class);
        musicPlayer.playMusic();
        context.close();
    }

}
