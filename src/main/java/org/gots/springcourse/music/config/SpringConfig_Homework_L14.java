package org.gots.springcourse.music.config;

import org.gots.springcourse.autowired.Computer;
import org.gots.springcourse.music.MusicPlayer_L14_Homework;
import org.gots.springcourse.music.genres.ClassicalMusic;
import org.gots.springcourse.music.genres.LoungeMusic;
import org.gots.springcourse.music.genres.Music;
import org.gots.springcourse.music.genres.RockMusic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig_Homework_L14 {
    @Bean
    public ClassicalMusic classicalMusic() {
        return new ClassicalMusic();
    }
    @Bean
    public RockMusic rockMusic() {
        return new RockMusic();
    }
    @Bean
    public LoungeMusic loungeMusic() { return new LoungeMusic(); }
    @Bean
    public List<Music> musicGenresList() {
        return Arrays.asList(classicalMusic(), rockMusic(), loungeMusic());
    }
    @Bean
    public MusicPlayer_L14_Homework musicPlayer_L14_Homework (){
        return new MusicPlayer_L14_Homework(musicGenresList());
    }
}
