package top.jianghuling.wechatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WechatappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatappApplication.class, args);
    }
}
