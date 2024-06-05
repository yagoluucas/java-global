package org.example.service;

import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;
import org.example.infrastructure.FakeCompanyApi;

public class FakeCompanyService {
    private static final FakeCompanyApi fakeCompanyApi = new FakeCompanyApi();

    public JsonObject getFakeCompanyData() {
        return fakeCompanyApi.getJsonFromApiData("https://fakerapi.it/api/v1/companies?_quantity=10") ;
    }
}
