package WebQuiz;


import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import org.json.JSONObject;

/**
 *
 * @author Dimitris Rakas
 */
public class RestCaller {
    private static final String BASE_URI = "http://localhost:8080/QuizServices/webresources";
    private Client client = javax.ws.rs.client.ClientBuilder.newClient();
    private WebTarget webTarget = client.target(BASE_URI);
    ArrayList<Integer> unanswered = new ArrayList();
    int i;
    public JSONObject exam() {
        WebTarget resource = webTarget.path("examq");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class));
    }
    //Βοηθητική στην εμφάνιση των 5 ερωτήσεων.
    public RestCaller() {
        for (int j = 0; j < 5; j++)
            unanswered.add(j);
        i = 0;
    }
}
