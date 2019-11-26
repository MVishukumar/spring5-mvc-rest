package guru.springframework.api.v1.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryListDTO {
    List<CategoryDTO> categories;
}
