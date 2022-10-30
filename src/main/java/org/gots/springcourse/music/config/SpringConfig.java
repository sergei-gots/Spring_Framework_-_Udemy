package org.gots.springcourse.music.config;

import org.gots.springcourse.autowired.Computer;
import org.gots.springcourse.music.MusicPlayer_Qualifier;
import org.gots.springcourse.music.genres.ClassicalMusic;
import org.gots.springcourse.music.genres.LoungeMusic;
import org.gots.springcourse.music.genres.RockMusic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.gots.springcourse")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {
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
    public MusicPlayer_Qualifier musicPlayer_Qualifier(){
        return new MusicPlayer_Qualifier(rockMusic(), classicalMusic());
    }

    @Bean
    public Computer computer(){
        return new Computer(musicPlayer_Qualifier());
    }
}
