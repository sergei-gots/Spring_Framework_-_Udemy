package org.gots.springcourse.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import static java.lang.System.out;

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

        out.println("MusicPlayer_Qualifier-CONSTRUCTOR started");
        music[MusicGenres.CLASSICAL.ordinal()] = music1;
        music[MusicGenres.ROCK.ordinal()] = music2;
        out.println("MusicPlayer_Qualifier-CONSTRUCTOR finished");
    }

    public void playMusic() {
        out.println(this);
    }

    public void playMusic(MusicGenres musicGenre) {
        out.println(compose(music[musicGenre.ordinal()].getSong(
                random.nextInt(Music.PLAYER_LIST_CAPACITY-1))));
    }

    private String compose(String song) {  return "Playing:\t" + song;}
    @Override
    public String toString() {
        return compose(music[0].getSong()) + ";\n" +
            compose(music[1].getSong());
    }

    public void printInfo() {
        out.println("musicPlayer.name = " + name);
        out.println("musicPlayer.volume = " +volume);
    }

    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContextComponents.xml");
        out.println("Context is created");
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
        musicPlayer.printInfo();

        ClassicalMusic classicalMusic1 = context.getBean(ClassicalMusic.BEAN_NAME, ClassicalMusic.class);
        ClassicalMusic classicalMusic2 = context.getBean(ClassicalMusic.BEAN_NAME, ClassicalMusic.class);
        boolean theSame = (classicalMusic1 == classicalMusic2);

       exploreAnnotations(ClassicalMusic.class);

        out.println("Let's check for @Scope(\"singleton\" or \"prototype\"):" );
        out.println("classicalMusic1 == classicalMusic2 = " + theSame);
        if(theSame == true) {
            out.println("\tThere is the only instance of " + ClassicalMusic.class.getSimpleName() + " => the actual scope=\"singleton\"");
        } else {
            out.println("\tThere are several instances of " + ClassicalMusic.class.getSimpleName() + " => the actual scope=\"prototype\"");
        }
        out.println("Let's check id-s of our Music-objects:");
        out.println("music[MusicGenres.ROCK.ordinal()].getId() = " + musicPlayer.music[MusicGenres.ROCK.ordinal()].getId());
        out.println("music[MusicGenres.CLASSICAL.ordinal()].getId() = " + musicPlayer.music[MusicGenres.CLASSICAL.ordinal()].getId());
        out.println("classicalMusic1.getId() = " + classicalMusic1.getId());
        out.println("classicalMusic2.getId() = " + classicalMusic2.getId());

        close();
    }

    public static void exploreAnnotations(Class classInstance) {
        Iterator<Annotation> iterator = Arrays.stream(classInstance.getDeclaredAnnotations()).iterator();
        out.println("Annotations defined for the " + classInstance.getSimpleName() + "-CLASS:");
        while (iterator.hasNext()) {
            Annotation annotation = iterator.next();
            String annotationName = annotation.annotationType().getName();
            out.println("\t" + annotation);
            if (annotation instanceof Scope) {
                out.println("\t\t!!!BINGO: Annotation @Scope has been found!!!");
                Scope scope = (Scope) annotation;
                String value = scope.value();
                out.println("\t\t\t\t its value=\"" + value + "\"");
                if (value.compareTo("prototype") == 0) {
                    out.println("\t\t\t\t\t@Scope(\"prototype\") =>" +
                            "\n\t\t\t\t\t\t\ta) the DESTROY-method WON'T BE CALLED" +
                            "\n\t\t\t\t\t\t\tb) created BEANS of ClassicalMusic WILL DIFFER");
                } else {
                    out.println("\t\t\t\t\t\t@Scope(\"singleton\")  =>" +
                            "\n\t\t\t\t\t\t\ta) the DESTROY-method WILL BE CALLED if declared" +
                            "\n\t\t\t\t\t\t\tb) created BEANS of ClassicalMusic WILL BE represented by THE SAME ONLY OBJECT");
                }
            }
        }
        out.println("""
                    NOTA BENE:
                        It seems that:
                            - the annotation @Scope does not affect scope of beans      :(
                            - the annotation @Bean does not affect factoring of beans   :( 
                    """);
    }
}
