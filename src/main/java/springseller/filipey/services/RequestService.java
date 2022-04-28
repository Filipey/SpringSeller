package springseller.filipey.services;

import springseller.filipey.api.dto.RequestDTO;
import springseller.filipey.api.dto.RequestInfoDTO;
import springseller.filipey.domain.Request;

import java.util.Optional;

public interface RequestService {
    Request save(RequestDTO dto);

    Optional<Request> getFullRequest(Long id);

    RequestInfoDTO getById(Long id);
}
