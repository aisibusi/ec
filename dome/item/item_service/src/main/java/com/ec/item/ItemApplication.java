package com.ec.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.ec.item.dao")
@EntityScan(basePackages = "com.lh.ec.item.pojo")
@EnableEurekaClient
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class);
    }
}
