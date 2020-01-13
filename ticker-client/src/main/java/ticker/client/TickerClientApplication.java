package ticker.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TickerClientApplication {

    public static void main(String... args) throws Exception {
        SpringApplication.run(TickerClientApplication.class);
    }

    @Component
    public class Runner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {

        }
    }
}
