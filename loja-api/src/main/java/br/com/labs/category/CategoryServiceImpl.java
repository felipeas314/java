package br.com.labs.category;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.labs.exception.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Page<Category> list(Pageable pageable) {
		return this.categoryRepository.findAll(pageable);
	}

	@Override
	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findById(Integer id) {
		return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("sei la"));
	}

	@Override
	public Category update(Category category) {
		return categoryRepository.findById(category.getId()).map(c -> {
			c.setDescription(category.getDescription());
			c.setName(category.getName());
			categoryRepository.save(c);
			return c;
		}).orElseThrow(() -> new ResourceNotFoundException(""));
	}

	@Override
	public void delete(Integer id) {
		categoryRepository.findById(id).ifPresentOrElse(category -> {
			categoryRepository.delete(category);
		}, () -> new EntityNotFoundException());
	}
}
