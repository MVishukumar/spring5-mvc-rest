package guru.springframework.api.v1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendorDTO {
    private Long id;
    private String name;
}
