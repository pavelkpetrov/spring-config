package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}

@RestController
class MessageRestController {

    @Value("${test.message:Hello default}")
    private String message;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private PropertyConfiguration propertyConfiguration;

    @RequestMapping("/message")
    String getMessage() {
        return "Application :" + this.appName + " | current unchangeable property test.message=" + message +
                " | current changeable value of propertyConfiguration.property=" + propertyConfiguration.getProperty();
    }

}