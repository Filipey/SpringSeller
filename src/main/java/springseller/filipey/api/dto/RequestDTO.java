package springseller.filipey.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    @NotNull(message = "Client id is required")
    private Long client;

    @NotNull(message = "Amount field is required")
    private BigDecimal amount;


    private List<ProductRequestDTO> requests;
}
