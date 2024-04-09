package com.github.Nadia3647.good_newstelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {
		"package com.github.Nadia3647.good_newstelegrambot",
		"org.telegram.telegrambots"
})
@EnableScheduling
@SpringBootApplication
public class GoodNewsTelegrambotApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodNewsTelegrambotApplication.class, args);
	}

}
