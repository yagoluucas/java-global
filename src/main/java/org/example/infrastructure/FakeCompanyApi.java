package org.example.infrastructure;

import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FakeCompanyApi {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private String getApiData(String url) {
        try {
            return httpClient.send(HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build(), HttpResponse.BodyHandlers.ofString()).body();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject getJsonFromApiData(String url) {
        String apiData = getApiData(url);
        if (apiData != null) {
            try {
                JsonReader jsonReader = jakarta.json.Json.createReader(new java.io.StringReader(apiData));
                return jsonReader.readObject();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
