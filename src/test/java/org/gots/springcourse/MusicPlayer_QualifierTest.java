package org.gots.springcourse;

import org.gots.springcourse.music.MusicPlayer_Qualifier;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicPlayer_QualifierTest {

    @RepeatedTest(10)
    public void testLesson_12_Homework() {
        assertTrue(MusicPlayer_Qualifier.lesson_12_Homework());
    }
}
