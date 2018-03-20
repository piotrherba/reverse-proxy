package hello;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Rule
    public WireMockRule service1 = new WireMockRule(options().port(9999));
    @Rule
    public WireMockRule service2 = new WireMockRule(options().port(9998));
    @Rule
    public WireMockRule service3 = new WireMockRule(options().port(9997));


    @Test
    public void getRedirect() throws Exception {


        service1.stubFor(get(urlEqualTo("/get"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello world!")));

        String body = testRestTemplate.getForObject("/get", String.class);
        assertEquals(body, "Hello world!");

    }

    @Test
    public void postRedirect() throws Exception {

        service2.stubFor(post(urlEqualTo("/post"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Successfully posted!")));

        String body = testRestTemplate.postForEntity("/post", "Here is something", String.class).getBody();
        assertEquals(body, "Successfully posted!");


    }

    @Test
    public void putRedirectTest() throws Exception {

        service3.stubFor(put(urlEqualTo("/put"))
        .willReturn(aResponse().withHeader("Content-Type","text/plain")
        .withBody("Success")));

        testRestTemplate.put("/put", "Something", String.class);


    }

}