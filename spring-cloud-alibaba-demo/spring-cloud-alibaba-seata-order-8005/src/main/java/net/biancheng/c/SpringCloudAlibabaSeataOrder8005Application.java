package net.biancheng.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "net.biancheng")
public class SpringCloudAlibabaSeataOrder8005Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSeataOrder8005Application.class, args);
    }
}