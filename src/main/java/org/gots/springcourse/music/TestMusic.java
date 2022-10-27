package org.gots.springcourse.music;

import org.gots.springcourse.TestBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMusic {

    private static MusicPlayer musicPlayer;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        //createMusicPlayer_Lesson_5(context);

        //Pure DEPENDENCY INJECTION is implemented here:
        musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        musicPlayer.printInfo();
        musicPlayer.playMusic();

        //To the homework to the Lesson 7:
        musicPlayer.playMusicList();

        //checkBeanScope_Lesson_8(context);

        //To the Lesson 9  "Bean Life cycle"
        System.out.println("""
                //To the Lesson 9  "Bean Life cycle"
                Music ClassicalMusic = context.getBean("musicBean", ClassicalMusic.class);
                """);
        Music ClassicalMusic = context.getBean("musicBean", ClassicalMusic.class);

        context.close();
    }

    @Deprecated
    private static void createMusicPlayer_Lesson_5(ClassPathXmlApplicationContext context) {
        Music music = context.getBean("musicBean", Music.class);
        //Dependency injection is here:
        musicPlayer = new MusicPlayer(music);
    }

    public static void checkBeanScope_Lesson_8(ClassPathXmlApplicationContext context) {

        MusicPlayer musicPlayer1
                = context.getBean("musicPlayer", MusicPlayer.class);

        boolean comparison = musicPlayer == musicPlayer1;

        System.out.println("""
                                
                Comparison.
                Object differs if <bean ... scope="prototype"/> is set.
                For default ("singleton") scope the references will point to the same object
                and => their hash-code will be equal and changes will affect "all the beans"-created:
                """);
        System.out.println("comparison (musicPlayer == musicPlayer1) = " + comparison);
        System.out.println("musicPlayer = " + musicPlayer);
        System.out.println("musicPlayer1 = " + musicPlayer1);

        musicPlayer.setVolume(10);
        System.out.println("musicPlayer.getVolume() = " + musicPlayer.getVolume());
        System.out.println("musicPlayer1.getVolume() = " + musicPlayer1.getVolume());
        System.out.println();
    }
}