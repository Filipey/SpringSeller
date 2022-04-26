package springseller.filipey.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private Long client;
    private BigDecimal amount;
    private List<ProductRequestDTO> requests;
}
