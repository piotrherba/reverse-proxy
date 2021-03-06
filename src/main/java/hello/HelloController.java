package hello;

import com.github.tomakehurst.wiremock.extension.responsetemplating.RequestTemplateModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    // tak można odwoływać się do wartości z application.properties
    @Value("${destination.get}")
    private String getDestination;

    @Value("${destination.post}")
    private String postDestination;

    @Value("${destination.put}")
    private String putDestination;

    @GetMapping
    public String getRedirect(RestTemplate restTemplate, HttpServletRequest request) {
        return restTemplate.getForEntity(getDestination + request.getServletPath(), String.class).getBody();
    }

    @PostMapping
    public String postRedirect(RestTemplate restTemplate, HttpServletRequest request, @RequestBody String body){
        return restTemplate.postForEntity(postDestination + request.getServletPath(), body , String.class ).getBody();
    }

    @PutMapping
    public void putRedirect(RestTemplate restTemplate, HttpServletRequest request, @RequestBody String body){
        restTemplate.exchange(putDestination + request.getServletPath(), HttpMethod.PUT, new HttpEntity<>(body), String.class);
    }

    @DeleteMapping
    public void deleteRequest() throws Exception {
        throw new Exception();
    }


}

