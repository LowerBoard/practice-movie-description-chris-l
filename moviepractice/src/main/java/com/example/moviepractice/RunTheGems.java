package com.example.moviepractice;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


@Service
public class RunTheGems {
    @Value("${gemini.api.key}")
    private String apiKey;

    public String generateDescription(String movieTitle) {
        try {
            Client client = Client.builder().apiKey(apiKey).build();
            String prompt = "In under 250 characters, Please create a description for the following movie - " + movieTitle;
            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", prompt, null);
            return response.text();
        } catch (Exception e) {
            System.err.println("Gemini Error:");
            e.printStackTrace();
            return "Error- " + e.getMessage();
        }
    }
}
