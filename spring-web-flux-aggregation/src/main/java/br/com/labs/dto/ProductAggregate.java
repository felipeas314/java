package br.com.labs.dto;

import java.util.List;

public class ProductAggregate {

	private Product product;

	private Promotion promotion;

	private List<Review> reviews;

	private ProductAggregate(Product product, Promotion promotion, List<Review> reviews) {
		this.product = product;
		this.promotion = promotion;
		this.reviews = reviews;
	}

	public static ProductAggregate create(Product product, Promotion promotion, List<Review> reviews) {
		return new ProductAggregate(product, promotion, reviews);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}
