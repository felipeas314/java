package br.com.labs.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.labs.exception.ResourceNotFoundException;

public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	private final String MESSAGE_ERROR = "Produto não encontrado";

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Page<Product> list(Pageable pageable) {
		return this.productRepository.findAll(pageable);
	}

	@Override
	public Product create(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ERROR));
	}

	@Override
	public Product update(Product product) {
		return productRepository.findById(product.getId()).map(p -> {
			p.setCategory(product.getCategory());
			p.setDescription(product.getDescription());
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			return p;
		}).orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ERROR));
	}

	@Override
	public void delete(Integer id) {
		productRepository.findById(id).ifPresentOrElse(product -> {
			productRepository.delete(product);
		}, () -> new ResourceNotFoundException(MESSAGE_ERROR));
	}

	public void addQuantityInProduct(String code, int quantity) {
		productRepository.findByCode(code).ifPresentOrElse(product -> {
			product.addQuantity(quantity);
			productRepository.save(product);
		}, () -> new ResourceNotFoundException(MESSAGE_ERROR));
	}

}
