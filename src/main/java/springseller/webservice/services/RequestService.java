package springseller.webservice.services;

import springseller.webservice.api.dto.RequestDTO;
import springseller.webservice.api.dto.RequestInfoDTO;
import springseller.webservice.api.dto.RequestStatusUpdateDTO;
import springseller.webservice.domain.Request;

import java.util.Optional;

public interface RequestService {
    Request save(RequestDTO dto);

    Optional<Request> getFullRequest(Long id);

    RequestInfoDTO getById(Long id);

    void updateStatus(Long id, RequestStatusUpdateDTO dto);
}
