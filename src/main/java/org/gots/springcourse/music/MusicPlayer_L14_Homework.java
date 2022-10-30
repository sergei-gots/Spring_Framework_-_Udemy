package org.gots.springcourse.music;

import org.gots.springcourse.music.config.SpringConfig_Homework_L14;
import org.gots.springcourse.music.genres.Music;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static java.lang.System.out;

@Component
public class MusicPlayer_L14_Homework implements IMusicPlayer {

    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;
    private List<Music> music;

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
    private Random random = new Random();

    public static void L14_Homework() {
        //ClassPathXmlApplicationContext context =
        //        new ClassPathXmlApplicationContext("musicPlayer_L14_Homework.applicationContext.xml");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig_Homework_L14.class);
        IMusicPlayer musicPlayer = context.getBean("musicPlayer_L14_Homework", MusicPlayer_L14_Homework.class);

        for(int i=0; i<100; i++) {
            musicPlayer.playMusic();
        }

        context.close();
    }

    private MusicPlayer_L14_Homework() {}

    public MusicPlayer_L14_Homework(List<Music> music) {
        setMusic(music);
    }

    @Override
    public void playMusic() {
        out.println(compose(music.get(random.nextInt(music.size())).getSong()));
    }
    private String compose(String song) {  return "Playing:\t" + song +
                                                ". volume - " + getVolume();  }

    public void printInfo() {
        out.println("musicPlayer.name = " + name);
        out.println("musicPlayer.volume = " +volume);
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }
}
