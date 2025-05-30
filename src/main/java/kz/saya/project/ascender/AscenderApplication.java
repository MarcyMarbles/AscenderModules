package kz.saya.project.ascender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "kz.saya"
})
public class AscenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AscenderApplication.class, args);
    }

}
