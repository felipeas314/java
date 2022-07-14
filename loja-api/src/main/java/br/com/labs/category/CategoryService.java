package br.com.labs.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.NoHandlerFoundException;

public interface CategoryService {

    public Page<Category> list(Pageable pageable);
    public Category create(Category category);
    public Category findById(Integer id) ;
    public Category update(Category category);
    public void delete(Integer id);
}
