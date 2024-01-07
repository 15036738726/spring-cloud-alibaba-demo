package net.biancheng.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudAlibabaNacosConfigClient3377Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaNacosConfigClient3377Application.class, args);
    }
}