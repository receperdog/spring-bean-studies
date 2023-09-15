package com.example.springbeanstudies;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringBeanStudiesApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBeanStudiesApplication.class, args);
    }

    @Bean
    public Customer customer(@Qualifier("test") String address){
        return new Customer("Recep", address);
    }

    @Bean
    public Customer temporary(@Autowired Customer customer){
        System.out.println(customer);
        return customer;
    }

}

@Configuration
class Address {
    @Bean("test")
    public String address(){
        return "Elbistan";
    }
}

@Configuration
class TestAdddress{
    @Bean("anotherTest")
    public String address(){
        return "Elbistan";
    }
}

@Data
class Customer{
    private final String name;
    private final String address;
}
