package org.gots.springcourse.music.genres;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static java.lang.System.out;

@Component
//NOTA BENE: The annotation @Scope seems to be affecting the actual scope of bean
// only if AnnotationConfigApplicationContext is used.
//@Scope("singleton")
@Scope("prototype")
public class ClassicalMusic implements Music {
    public final static String BEAN_NAME = "classicalMusic";

    private int id;
    private int factoryId;
    private static int factoryInstancesCounter;
    private static int instancesCounter;
    private String[] compositions;
    //private
    public ClassicalMusic() {
        printMethod("CONSTRUCTOR");
        factoryId = factoryInstancesCounter;
        id = ++instancesCounter;
        compositions = new String[PLAYER_LIST_CAPACITY];
        compositions[0] = "Barber - Adagio for Strings";
        compositions[1] = "Brahms - Piano Concerto No. 1, Op. 15";
        compositions[2] = "Saint-Saens - The Carnival of Animals: XIII, The Swan";
        printId();
    }
    @Override
    public void printId() {
        out.println("id=" + id + ", factoryId=" + factoryId + ", ref=" + this);
    }


    @Override
    public int getId() {    return id;  }

    public static void printMethod(String method) {
        out.print("#FactoryID=");
        out.print(instancesCounter);
        out.print(". ");
        out.print(method);
        out.println("-Method (Class ClassicalMusic)");
    }
    //NOTA BENE: The annotation @Bean seems to be not affecting the factoring of beans.
    @Bean
    public static ClassicalMusic getClassicalMusic() {
        factoryInstancesCounter++;
        printMethod("FACTORY");
        return new ClassicalMusic();
    }
    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }

    @Override
    public String getSong(int i) {
        return compositions[i];
    }

    @PostConstruct
    public void doMyInit() {  printMethod("INIT");  }

    @PreDestroy
    public void doMyDestroy() {
        printMethod("DESTROY");
        instancesCounter--;
    }
}
