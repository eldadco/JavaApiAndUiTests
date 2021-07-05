package Infra.Api;

import Infra.DriverSingleton.DriverSingleton;
import lombok.Getter;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class SingletonHttpClient {
    @Getter
    private final CloseableHttpClient client;
    private static SingletonHttpClient instance = null;
    private SingletonHttpClient() {
        this.client = HttpClients.createDefault();

    }

    public static SingletonHttpClient getInstance() {
        if (instance == null)
            instance = new SingletonHttpClient();

        return instance;
    }

}
