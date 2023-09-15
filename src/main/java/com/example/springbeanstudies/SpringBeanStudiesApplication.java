package com.example.springbeanstudies;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@SpringBootApplication
public class SpringBeanStudiesApplication implements CommandLineRunner {

    @Autowired
    private TestPasswordGenerator testPasswordGenerator;

    public static void main(String[] args) {
        SpringApplication.run(SpringBeanStudiesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testPasswordGenerator.test();
    }
}

@Component
class PasswordGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();

    public String generate(int length) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }

        return result.toString();
    }
    @PostConstruct
    public void testPostConstruct(){
        System.out.println("AFTER construction this method should be run");
    }

    @PreDestroy
    public void testPreDestroy(){
        System.out.println("Before Destruction this method should be executed");
    }
}

@Service
class TestPasswordGenerator{
    @Autowired
    private PasswordGenerator passwordGenerator;

    public void test(){
        System.out.println(passwordGenerator.generate(10));
    }
}
