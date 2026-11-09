package com.sanj.projectcatalogueservice.service;

import com.sanj.projectcatalogueservice.client.GroqClient;
import com.sanj.projectcatalogueservice.dto.GroqPitchResponseDto;
import com.sanj.projectcatalogueservice.dto.Pitch;
import com.sanj.projectcatalogueservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PitchService {
    private final String groqUrl;
    private static final String MODEL = "openai/gpt-oss-20b";
    private static final String SYSTEM_PROMPT = "You are a marketing copywriter for ShopNest, an online store. \"\n" +
            "            + \"You write short push notifications that make people want to open the app.";
    private final GroqClient groqClient;
    private final String apiKey;

    public PitchService(GroqClient groqClient,
                        @Value("${groq.api-key}") String apiKey,
                        @Value("${groq.url}") String groqUrl) {
        this.groqClient = groqClient;
        this.groqUrl = groqUrl;
        this.apiKey = apiKey;
    }

    public String generatePitch(Product product) {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> body = Map.of(
                    "model", MODEL,
                    "reasoning_effort", "low",
                    "max_completion_tokens", 300,
                    "messages", List.of(
                            Map.of("role", "system", "content", SYSTEM_PROMPT),
                            Map.of("role", "user", "content", buildPrompt(product))));

            GroqPitchResponseDto response = groqClient.chat(body);

            String pitch = response.getChoices().get(0).getMessage().getContent();
            return pitch;
        }
        catch (Exception e) {
            //System.out.println(e.getMessage());
            log.warn("Error in LLM call" + e.getMessage());
            return "New arrival: " + product.getName() +
                    " for just $" + product.getPrice() + ". Check it out!";
        }
    }

    private String buildPrompt(Product product) {
        return """
                Write ONE push notification for this product.
                Rules:
                - Maximum 25 words.
                - Mention the price.
                - At most one emoji.
                - No hashtags.
                - Reply with only the notification text.

                Product: %s
                Category: %s
                Price: $%s
                Details: %s""".formatted(product.getName(), product.getCategory().getName(),
                product.getPrice(), product.getDescription());
    }
}
