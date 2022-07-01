package br.com.labs.category;

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
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryRepository categoryRepository;

	private CategoryService categoryService;

	public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
		this.categoryRepository = categoryRepository;
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<Page<Category>> list(@PageableDefault(page = 0, size = 20) Pageable pageable) {
		return ResponseEntity.ok(categoryService.list(pageable));
	}

	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category) {

		category = categoryService.create(category);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();

		return ResponseEntity.created(location).body(category);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable("id") Integer id) throws NoHandlerFoundException {
		return ResponseEntity.ok(categoryService.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@Valid @RequestBody Category category, @PathVariable("id") Integer id) {
		return ResponseEntity.ok(categoryService.update(category));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		categoryService.delete(id);
		return ResponseEntity.ok().build();
	}
}
