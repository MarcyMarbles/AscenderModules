package kz.saya.labs.ascender.games;

import kz.saya.labs.ascender.common.config.EurekaClientConfig;
import kz.saya.labs.ascender.common.config.FeignClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"kz.saya.labs.ascender.games.entity", "kz.saya.labs.ascender.common.model"})
@EnableJpaRepositories(basePackages = "kz.saya.labs.ascender.games.repository")
@Import({EurekaClientConfig.class, FeignClientConfig.class})
public class GamesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesServiceApplication.class, args);
    }
}