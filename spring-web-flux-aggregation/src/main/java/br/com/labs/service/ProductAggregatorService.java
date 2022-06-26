package br.com.labs.service;

import br.com.labs.dto.ProductAggregate;
import reactor.core.publisher.Mono;

public interface ProductAggregatorService {

	public Mono<ProductAggregate> getProduct(Integer productId);
}
