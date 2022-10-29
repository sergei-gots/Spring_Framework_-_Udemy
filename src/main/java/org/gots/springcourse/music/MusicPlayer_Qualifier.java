package org.gots.springcourse.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

@Component
public class MusicPlayer_Qualifier {

    /* @Autowired
    @Qualifier ("classicalMusic")
    private Music music1;

    @Autowired
    @Qualifier ("rockMusic")
    private Music music2;
    */

    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    private static ClassPathXmlApplicationContext context;
    private static MusicPlayer_Qualifier musicPlayer;
    private Music music[] = new Music[2];

    private Random random = new Random();
    public MusicPlayer_Qualifier(@Qualifier("classicalMusic") Music music1,
                                 @Qualifier("rockMusic") Music music2) {
        System.out.println("MusicPlayer_Qualifier-constructor started");
        music[MusicGenres.CLASSICAL.ordinal()] = music1;
        music[MusicGenres.ROCK.ordinal()] = music2;
        System.out.println("MusicPlayer_Qualifier-constructor finished");
    }

    public void playMusic() {
        System.out.println(this);
    }

    public void playMusic(MusicGenres musicGenre) {
        System.out.println(compose(music[musicGenre.ordinal()].getSong(
                random.nextInt(Music.PLAYER_LIST_CAPACITY-1))));
    }

    private String compose(String song) {  return "Playing:\t" + song;}
    @Override
    public String toString() {
        return compose(music[0].getSong()) + ";\n" +
            compose(music[1].getSong());
    }

    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContextComponents.xml");
        System.out.println("Context is created");
        musicPlayer = context.getBean("musicPlayer_Qualifier", MusicPlayer_Qualifier.class);
    }

    public static void close() {    context.close();    }
    public static void lesson_12() {
        init();

        musicPlayer.playMusic();

        close();
    }

    public static boolean lesson_12_Homework() {
        init();

        musicPlayer.playMusic(MusicGenres.CLASSICAL);
        musicPlayer.playMusic(MusicGenres.ROCK);

        close();
        return true;
    }

    public static void lesson_13() {
        init();

        System.out.println("musicPlayer.name = " + musicPlayer.name);
        System.out.println("musicPlayer.volume = " + musicPlayer.volume);


       ClassicalMusic classicalMusic1 = context.getBean(ClassicalMusic.BEAN_NAME, ClassicalMusic.class);
       ClassicalMusic classicalMusic2 = context.getBean(ClassicalMusic.BEAN_NAME, ClassicalMusic.class);

       exploreAnnotations(ClassicalMusic.class);

        System.out.println("Let's check for @Scope(\"singleton\" or \"prototype\"):" );
        System.out.println("classicalMusic1 == classicalMusic2 = " + (classicalMusic1 == classicalMusic2));
        System.out.println("music[MusicGenres.CLASSICAL.ordinal()].getId() = " + musicPlayer.music[MusicGenres.CLASSICAL.ordinal()].getId());
        System.out.println("classicalMusic1.getId() = " + classicalMusic1.getId());

        close();
    }

    public static void exploreAnnotations(Class classInstance) {
        Iterator<Annotation> iterator = Arrays.stream(classInstance.getDeclaredAnnotations()).iterator();
        System.out.println("Annotations defined for the " + classInstance.getSimpleName() + "-CLASS:");
        while (iterator.hasNext()) {
            Annotation annotation = iterator.next();
            String annotationName = annotation.annotationType().getName();
            System.out.println("\t" + annotation);
            if (annotation instanceof Scope) {
                System.out.println("\t\t!!!BINGO: Annotation @Scope has been found!!!");
                Scope scope = (Scope) annotation;
                String value = scope.value();
                System.out.println("\t\t\t\t its value=\"" + value + "\"");
                if (value.compareTo("prototype") == 0) {
                    System.out.println("\t\t\t\t\t@Scope(\"prototype\") =>" +
                            "\n\t\t\t\t\t\t\ta) the DESTROY-method WON'T BE CALLED" +
                            "\n\t\t\t\t\t\t\tb) created BEANS of ClassicalMusic WILL DIFFER");
                } else {
                    System.out.println("\t\t\t\t\t\t@Scope(\"singleton\")  =>" +
                            "\n\t\t\t\t\t\t\ta) the DESTROY-method WILL BE CALLED if declared" +
                            "\n\t\t\t\t\t\t\tb) created BEANS of ClassicalMusic WILL BE represented by THE SAME ONLY OBJECT");
                }
            }
        }
    }
}
