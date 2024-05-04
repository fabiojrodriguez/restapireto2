package reqres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.http.HttpResponse;

public class reqrestQuestions {
    public static void validateRequest(int statusCode, HttpResponse<String> response, String bodyContent){
        assertEquals(statusCode, response.statusCode(), "Validación del Status Code de la Respuesta");
        assertTrue(response.body().contains(bodyContent), "Validación del contenido del Body");
    }
}
