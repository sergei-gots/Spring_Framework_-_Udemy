package org.gots.springcourse.music.genres;

public interface Music {
    final static int PLAYER_LIST_CAPACITY = 3;
    String getSong();
    default String getSong(int i)   { return getSong(); }
    default int getId()             { return -1; }
    default public void printId()   { System.out.println("Music.Id=" + getId());}
}
