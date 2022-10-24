package org.gots.springcourse.music;

import org.gots.springcourse.TestBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMusic {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        //Music music = context.getBean("musicBean", Music.class);
        //Dependency injection is here:
        //MusicPlayer musicPlayer = new MusicPlayer(music);

        //Pure DEPENDENCY INJECTION is implemented here:
        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusic();
        System.out.println("musicPlayer.name = " + musicPlayer.getName());
        System.out.println("musicPlayer.volume = " + musicPlayer.getVolume());

        context.close();
    }
}
