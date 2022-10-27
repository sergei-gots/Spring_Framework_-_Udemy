package org.gots.springcourse.music;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music {
    private static int instancesCounter;
    private ClassicalMusic() {}
    public static ClassicalMusic getClassicalMusic() {
        instancesCounter++;
        return new ClassicalMusic();
    }
    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }

    public void doMyInit() {
        System.out.println(instancesCounter + ") Doing my initialisation");  }

    public void doMyDestroy() {
        System.out.println(instancesCounter + ") Doing my destruction");
        instancesCounter--;
    }
}
