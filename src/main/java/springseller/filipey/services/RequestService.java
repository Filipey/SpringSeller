package springseller.filipey.services;

import springseller.filipey.api.dto.RequestDTO;
import springseller.filipey.domain.Request;

public interface RequestService {
    Request save(RequestDTO dto);
}
