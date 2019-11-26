package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        /*List<CategoryDTO> categoryDTOS = new LinkedList<>();

        for (Category category: categoryList) {
            categoryDTOS.add(categoryMapper.categoryToCategoryDTO(category));
        }*/

        return categoryList
                .stream()
                .map(category -> {
                    return categoryMapper.categoryToCategoryDTO(category);
                })
                .collect(Collectors.toList());

        //return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        return categoryMapper.categoryToCategoryDTO(category);
    }


}
