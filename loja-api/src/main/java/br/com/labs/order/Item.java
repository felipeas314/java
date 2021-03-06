package br.com.labs.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.labs.product.Product;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Product product;

	private int quantity;
	
	@ManyToOne
	private Order order;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotalPrice() {
		return product.getPrice().doubleValue()*quantity;
	}
	
	
}
