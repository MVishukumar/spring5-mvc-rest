package guru.springframework.api.v1.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerListDTO {
    List<CustomerDTO> customers;
}
