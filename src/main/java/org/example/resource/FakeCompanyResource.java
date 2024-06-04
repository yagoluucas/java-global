package org.example.resource;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.service.FakeCompanyService;

@Path("fakecompany")
public class FakeCompanyResource {
    private static final FakeCompanyService fakeCompanyService = new FakeCompanyService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getFakeCompanyData() {
        JsonObject dataApi = fakeCompanyService.getFakeCompanyData();

        return dataApi != null ? dataApi : Json.createObjectBuilder()
                                           .add("message", "Erro ao buscar dados da API")
                                           .build();
    }
}
