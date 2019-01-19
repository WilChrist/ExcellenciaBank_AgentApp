package com.jsfstarterproject.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsfstarterproject.models.Agent;
import com.jsfstarterproject.models.Client;
import com.jsfstarterproject.util.SessionUtils;
import okhttp3.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginDao {
    private static OkHttpClient clientHttp;
    private static ObjectMapper objectMapper;
    private static HttpSession session;

    public static Response validate(String login, String password) {
        System.out.println(login + "   +  " + password);
        clientHttp = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String auth = "{\n\t\"login\": \"" + login + "\",\n\t\"password\": \"" + password + "\"\n}";
        RequestBody body = RequestBody.create(mediaType, auth);
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/login")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = null;
        try {
            response = clientHttp.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            System.out.println(response.code());
        }
        System.out.println(response.header("Authorization"));

        return response;
    }

    public static Response updateClientOnApi(Client client) throws IOException {
        System.out.println(client.getEmail());
        clientHttp = new OkHttpClient();
        String jsonClient = objectMapper.writeValueAsString(client);
        // System.out.println(jsonClient);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonClient);

        session = SessionUtils.getSession();
        String token = session.getAttribute("token").toString();
        //System.out.println(token);

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/clients/" + client.getPk())
                .put(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .addHeader("cache-control", "no-cache")
                .build();
        Response response;

        response = clientHttp.newCall(request).execute();

        System.out.println(response.code());

        return response;
    }

    public static Response postClientOnApi(Client client) throws IOException {
        System.out.println(client.getEmail());
        clientHttp = new OkHttpClient();
        String jsonClient = objectMapper.writeValueAsString(client);
        // System.out.println(jsonClient);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonClient);

        session = SessionUtils.getSession();
        String token = session.getAttribute("token").toString();
        //System.out.println(token);

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/clients")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .addHeader("cache-control", "no-cache")
                .build();
        Response response;

        response = clientHttp.newCall(request).execute();

        System.out.println(response.code());

        return response;
    }

    public static Agent getAgent(Response response) throws IOException {
        objectMapper = new ObjectMapper();
        Agent agent = objectMapper.readValue(response.body().string(), new TypeReference<Agent>() {
        });
        System.out.println(agent.getManagedClients().size());
        if (agent.getManagedClients() == null) {
            agent = new Agent();
        }
        response.body().close();
        return agent;
    }

    public static Client getClient(Response response) throws IOException {
        objectMapper = new ObjectMapper();
        Client client = objectMapper.readValue(response.body().string(), new TypeReference<Client>() {
        });
        System.out.println(client.getEmail());
        response.body().close();
        return client;
    }
}
