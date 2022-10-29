package org.gots.springcourse.music;

import org.springframework.stereotype.Component;

@Component
public class RockMusic implements Music {
    private String[] compositions;
    private RockMusic() {
        compositions = new String[PLAYER_LIST_CAPACITY];
        compositions[0] = "Queen · I Want To Break Free (1984)";
        compositions[1] = "Guns N'Roses · Don't Cry (Original)";
        compositions[2] = "The Cranberries · Zombie";
    }
    @Override
    public String getSong() {
        return "Wind cries Mary";
    }

    @Override
    public String getSong(int i) {
        return compositions[i];
     }

    @Override
    public int getId() {
        return -1;
    }
}
