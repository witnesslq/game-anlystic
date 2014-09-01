package cn.mob.anlystic.game.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * @version 1.0 date: 2014/8/28
 * @author: Dempe
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WebStartUp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebStartUp.class, args);
    }

    @PreDestroy
    public void closeOnJVMShutdown() {
        System.out.println("----close ------------------");

    }
}
