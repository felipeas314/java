package br.com.labs.product;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductRepository productRepository;
	
	private ProductServiceImpl productServiceImpl;

	public ProductController(ProductRepository productRepository,ProductServiceImpl productServiceImpl) {
		this.productRepository = productRepository;
		this.productServiceImpl = productServiceImpl;
	}

	@GetMapping
	public ResponseEntity<Page<Product>> index(@PageableDefault(page = 0, size = 20) Pageable pageable) {
		return ResponseEntity.ok(productServiceImpl.list(pageable));
	}

	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
		productServiceImpl.create(product);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();

		return ResponseEntity.created(location).body(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> show(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(productServiceImpl.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Integer id, Product product) {
		return ResponseEntity.ok(productServiceImpl.update(product));
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
