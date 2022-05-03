package springseller.webservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springseller.webservice.api.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    @NotNull(message = "{field.client-id.required}")
    private Long client;

    @NotNull(message = "{field.request-amount.required}")
    private BigDecimal amount;

    @NotEmptyList(message = "{field.products-request.required}")
    private List<ProductRequestDTO> requests;
}
