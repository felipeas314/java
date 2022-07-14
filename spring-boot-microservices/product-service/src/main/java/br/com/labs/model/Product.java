package br.com.labs.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
public class Product {

	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private BigDecimal price;
}
