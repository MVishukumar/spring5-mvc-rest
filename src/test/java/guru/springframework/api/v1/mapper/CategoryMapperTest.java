package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {
    private static final String name = "Joe";
    private static final Long id = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {
        Category category = new Category();
        category.setName(name);
        category.setId(id);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getId(), categoryDTO.getId());
    }
}