package Infra.Api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpProcess {

    private CloseableHttpClient client;
    private ObjectMapper mapper;

    public HttpProcess(CloseableHttpClient client) {
        this.client = client;
        this.mapper = new ObjectMapper();
    }

    public HttpResponse executeRequest(HttpRequestBase request) throws IOException {
        return this.client.execute(request);
    }

    public <T> ResponseValidation validateJsonResponse(int expectedStatusCode, Class<T> expectedBodyType, HttpResponse response) {
        ResponseValidation result = new ResponseValidation();
        try {
            if (response.getStatusLine().getStatusCode() == expectedStatusCode) {
                result.setStatusCodeResult(true);

                result.setBodyResult(this.mapper.readValue(response.getEntity().getContent(), expectedBodyType));
            }
            return result;
        } catch (Exception exception) {
            result.setBodyResult(null);
            return result;
        }

    }

}
