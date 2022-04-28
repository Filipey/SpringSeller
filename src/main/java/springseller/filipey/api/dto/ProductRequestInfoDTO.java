package springseller.filipey.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestInfoDTO {
    private String description;
    private BigDecimal unitPrice;
    private Integer quantity;
}
