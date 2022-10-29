package org.gots.springcourse.music;

import org.gots.springcourse.autowired.Computer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestMusic {

    private enum Lessons {
        LESSONS_1_9,
        LESSON_5,
        LESSON_7_HOMEWORK,
        LESSON_8,
        LESSON_9,
        LESSON_10,
        LESSON_11,
        LESSON_11_1,
        LESSON_12,
        LESSON_12_HOMEWORK,
        LESSON_13,
        LESSON_14

    };

    static Lessons lessonID;
    private static MusicPlayer musicPlayer;

    public static void main(String[] args) {
        lessonID = Lessons.LESSON_14;
        System.out.println("lessonID = " + lessonID);
        switch (lessonID) {
            case LESSONS_1_9:
            case LESSON_5:
            case LESSON_7_HOMEWORK:
            case LESSON_8:              lessons_1_9();                              break;
            case LESSON_10:             lesson_10();                                break;
            case LESSON_11:             lesson_11();                                break;
            case LESSON_11_1:           Computer.lesson_11_1();                     break;
            case LESSON_12:             MusicPlayer_Qualifier.lesson_12();          break;
            case LESSON_12_HOMEWORK:    MusicPlayer_Qualifier.lesson_12_Homework(); break;
            case LESSON_13:             MusicPlayer_Qualifier.lesson_13();          break;
            case LESSON_14:             lesson_14();                                break;
        }
    }

    public static void lesson_14() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        MusicPlayer_Qualifier musicPlayer = context.getBean("musicPlayer_Qualifier", MusicPlayer_Qualifier.class);
        musicPlayer.printInfo();
        musicPlayer.testSingletonPrototypeScopeBehaviour(context);
        context.close();
    }
    public static void lesson_11() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextComponents.xml");
        MusicPlayer_Autowired musicPlayer = context.getBean("musicPlayer_Autowired", MusicPlayer_Autowired.class);
        musicPlayer.playMusic();

        context.close();
    }
    public static void lesson_10() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextComponents.xml");

        Music music1 = context.getBean("rockMusic", Music.class);
        Music music2 = context.getBean("classicalMusic", Music.class);
        MusicPlayer musicPlayer = new MusicPlayer(music1);
        MusicPlayer classicalMusicPlayer = new MusicPlayer(music2);
        musicPlayer.playMusic();
        classicalMusicPlayer.playMusic();

        context.close();
    }

    public static void lessons_1_9() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        if(lessonID == Lessons.LESSON_5) {    createMusicPlayer_Lesson_5(context);  }

        //Pure DEPENDENCY INJECTION is implemented here:
        musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        musicPlayer.printInfo();
        musicPlayer.playMusic();

        if(lessonID == Lessons.LESSON_7_HOMEWORK)   {   musicPlayer.playMusicList(); }
        if (lessonID == Lessons.LESSON_8)           {   checkBeanScope_Lesson_8(context); }
        if (lessonID == Lessons.LESSON_9) {
            System.out.println("""
                    //To the Lesson 9  "Bean Life cycle"
                    Music ClassicalMusic = context.getBean("musicBean", ClassicalMusic.class);
                    """);
            Music ClassicalMusic = context.getBean("musicBean", ClassicalMusic.class);
        }
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