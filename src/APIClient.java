import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClient {

    private HttpClient _httpClient;
    private String _url;

    public APIClient() throws Exception {
        this._httpClient = HttpClient.newHttpClient();
    }

    public HttpResponse<String> send(String body) throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(_url)).build();

        HttpResponse<String> response = _httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());

        return response;
    }
}
