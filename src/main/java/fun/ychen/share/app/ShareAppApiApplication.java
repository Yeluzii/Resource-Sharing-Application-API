package fun.ychen.share.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"fun.ychen.share.app.mapper"})
public class ShareAppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareAppApiApplication.class, args);
    }

}
