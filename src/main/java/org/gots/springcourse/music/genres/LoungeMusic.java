package org.gots.springcourse.music.genres;

import org.gots.springcourse.music.genres.Music;

public class LoungeMusic implements Music {
    @Override
    public String getSong() {
        return "Big dream";
    }
}
