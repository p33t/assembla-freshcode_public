package biz.freshcode.swing_shots.config;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    /**
     * A token example of integrating an external class.
     */
    @Bean
    Runtime getRuntime() {
        return Runtime.getRuntime();
    }
}
