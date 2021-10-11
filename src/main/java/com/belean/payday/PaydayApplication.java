package com.belean.payday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaydayApplication {

    public static void main(String[] args) {
        //SpringApplication.run(PaydayApplication.class, args);
        for(String arg : args) {
            System.out.println(arg);
        }
    }

}
