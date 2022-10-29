package org.gots.springcourse.music;

public interface Music {
    final static int PLAYER_LIST_CAPACITY = 3;
    String getSong();
    String getSong(int i);
    int getId();
    public void printId();
}
