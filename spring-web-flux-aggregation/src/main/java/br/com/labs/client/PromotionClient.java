package br.com.labs.client;

import java.time.LocalDate;

import org.springframework.web.reactive.function.client.WebClient;

import br.com.labs.dto.Promotion;
import reactor.core.publisher.Mono;

public class PromotionClient {

private final WebClient client;
private final Promotion noPromotion = new Promotion("no-promotion", 0.0, LocalDate.of(2999, 12, 31));

public PromotionClient(WebClient.Builder builder) {
    this.client = builder.baseUrl("http://localhost:3000/promotions/").build();
}

public Mono<Promotion> getPromotion(Integer productId){
    return this.client
            .get()
            .uri("{productId}", productId)
            .retrieve()
            .bodyToMono(Promotion.class)
            .onErrorReturn(noPromotion); // if no result, it returns 404, so switch to no promotion
}
}
