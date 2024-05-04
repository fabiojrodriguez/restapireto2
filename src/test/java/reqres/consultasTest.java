package reqres;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.JsonValidator;


public class consultasTest {

    HttpClient httpClient;
    URIBuilder endpoint;
    int resource = 2;

    @BeforeEach
    public void setup(){


        httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .connectTimeout(Duration.ofSeconds(10))
        .build();

        endpoint =  new URIBuilder()
        .setScheme("https")
        .setHost("reqres.in")
        .setPath("api/users");    
    }
    
    @Test
    public void responsetUserTest() throws URISyntaxException, IOException, InterruptedException{
        endpoint.addParameter("page", "2");
        System.out.println("ENDPOINT: " + endpoint.build());
        HttpResponse<String> response = reqrestRequest.reqrestRequestClients(httpClient, endpoint);
        System.out.println(response.body());
        
        String validacion = JsonValidator.validateJson(response.body(),"reqrestRequest.json");
        assertEquals("", validacion, "Validacion del Json Schema");


        reqrestQuestions.validateRequest(HttpStatus.SC_OK, response, "michael.lawson@reqres.in" );
    }

    @Test
    public void responsetUser12Test() throws URISyntaxException, IOException, InterruptedException{
        endpoint.setPath("api/users/12");
        System.out.println("ENDPOINT: " + endpoint.build());
        HttpResponse<String> response = reqrestRequest.reqrestRequestClients(httpClient, endpoint);
        System.out.println(response.body());
        
        String validacion = JsonValidator.validateJson(response.body(),"reqrestRequestUser12.json");
        assertEquals("", validacion, "Validacion del Json Schema");


        reqrestQuestions.validateRequest(HttpStatus.SC_OK, response, "rachel.howell@reqres.in" );
    }

    @Test
    public void responsetUserNotfound() throws URISyntaxException, IOException, InterruptedException{
        endpoint.setPath("api/users/23");
        System.out.println("ENDPOINT: " + endpoint.build());
        HttpResponse<String> response = reqrestRequest.reqrestRequestClients(httpClient, endpoint);
        System.out.println("404 Not Found"+ response.body());
        
        reqrestQuestions.validateRequest(HttpStatus.SC_NOT_FOUND, response, "" );

    }
    @Test
    public void responsetUserWithDelayTest() throws URISyntaxException, IOException, InterruptedException{
        endpoint.addParameter("delay", "3");
        System.out.println("ENDPOINT: " + endpoint.build());
        HttpResponse<String> response = reqrestRequest.reqrestRequestClients(httpClient, endpoint);
        System.out.println(response.body());
        System.out.println(response);
        
        String validacion = JsonValidator.validateJson(response.body(),"reqrestRequestdelay.json");
        assertEquals("", validacion, "Validacion del Json Schema");


        reqrestQuestions.validateRequest(HttpStatus.SC_OK, response, "george.bluth@reqres.in" );
    }    
}
