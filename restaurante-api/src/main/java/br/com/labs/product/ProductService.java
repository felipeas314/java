package br.com.labs.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

	public Page<Product> list(Pageable pageable);
    public Product create(Product product);
    public Product findById(Integer id) ;
    public Product update(Product product);
    public void delete(Integer id);
}
