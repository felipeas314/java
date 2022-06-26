package br.com.labs.service;

import br.com.labs.dto.ProductAggregate;
import reactor.core.publisher.Mono;

public class ProductAggregatorServiceImpl implements ProductAggregatorService{

	@Override
	public Mono<ProductAggregate> getProduct(Integer productId) {
		return null;
	}

}
