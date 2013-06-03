package net.oddpoet.cucumber.sample;

import net.oddpoet.cucumber.sample.component.TeamCreateService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        TeamCreateService teamCreateService = context.getBean(TeamCreateService.class);

        teamCreateService.start();
    }
}
