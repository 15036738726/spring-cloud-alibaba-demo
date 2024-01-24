package net.biancheng.c;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "net.biancheng")
public class SpringCloudAlibabaSeataAccount8007Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSeataAccount8007Application.class, args);
    }
}