package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

public class SupaBaseTest {

    @Test
    void testFetchStores() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://eckelaopkyqijqgkdibs.supabase.co/rest/v1/store")).header("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVja2VsYW9wa3lxaWpxZ2tkaWJzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI5ODEyNzQsImV4cCI6MjA3ODU1NzI3NH0.3bfLosSE0zDsMMAn5AguPJHFEIJchBW_yB3lrW-R3Uc").header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVja2VsYW9wa3lxaWpxZ2tkaWJzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI5ODEyNzQsImV4cCI6MjA3ODU1NzI3NH0.3bfLosSE0zDsMMAn5AguPJHFEIJchBW_yB3lrW-R3Uc").build();

        HttpResponse<String> response =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
    }
}
