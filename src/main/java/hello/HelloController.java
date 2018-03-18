package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    // tak można odwoływać się do wartości z application.properties
    @Value("${destination.get}")
    private String get;

    @RequestMapping("/")
    public String index() {
        return "Hello!";
    }

}
