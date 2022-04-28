package springseller.filipey.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestInfoDTO {
    private Long id;
    private String cpf;
    private String clientName;
    private BigDecimal amount;
    private String requestDate;
    private List<ProductRequestInfoDTO> products;
}
