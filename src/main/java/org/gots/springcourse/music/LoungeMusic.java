package org.gots.springcourse.music;

public class LoungeMusic implements Music {
    @Override
    public String getSong() {
        return "Big dream";
    }

    @Override
    public String getSong(int i) {
        return getSong();
    }

    @Override
    public int getId() {
        return -1;
    }
}
