package guru.springframework.services;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
