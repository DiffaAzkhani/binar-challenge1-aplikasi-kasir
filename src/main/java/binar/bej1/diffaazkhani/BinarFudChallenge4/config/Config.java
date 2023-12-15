package binar.bej1.diffaazkhani.BinarFudChallenge4.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Config {

    @Value("${merchant.name}")
    private String merchantName;

    @Bean
    public String basicConfiguration() {
        log.info("Basic configuration is initializing. . .");
        log.info("Initialize success by {}", merchantName);
        return this.merchantName;
    }
}
