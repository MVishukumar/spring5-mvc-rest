package guru.springframework.api.v1.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO {
    List<VendorDTO> vendors;
}
