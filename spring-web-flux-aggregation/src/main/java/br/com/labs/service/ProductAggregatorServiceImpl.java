package br.com.labs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.labs.client.ProductClient;
import br.com.labs.client.PromotionClient;
import br.com.labs.client.ReviewClient;
import br.com.labs.dto.Product;
import br.com.labs.dto.ProductAggregate;
import br.com.labs.dto.Promotion;
import br.com.labs.dto.Review;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@Service
public class ProductAggregatorServiceImpl implements ProductAggregatorService {

	private final ProductClient productClient;
	private final PromotionClient promotionClient;
	private final ReviewClient reviewClient;

	public ProductAggregatorServiceImpl(ProductClient productClient, PromotionClient promotionClient,
			ReviewClient reviewClient) {
		this.productClient = productClient;
		this.promotionClient = promotionClient;
		this.reviewClient = reviewClient;
	}

	@Override
	public Mono<ProductAggregate> getProduct(Integer productId){
        return Mono.zip(
                this.productClient.getProduct(productId),
                this.promotionClient.getPromotion(productId),
                this.reviewClient.getReviews(productId)
        )
        .map(this::combine);
    }
	
	private ProductAggregate combine(Tuple3<Product, Promotion, List<Review>> tuple){
        return ProductAggregate.create(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3()
        );
    }

}
