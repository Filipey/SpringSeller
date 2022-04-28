package springseller.filipey.services;

import springseller.filipey.api.dto.RequestDTO;
import springseller.filipey.api.dto.RequestInfoDTO;
import springseller.filipey.api.dto.RequestStatusUpdateDTO;
import springseller.filipey.domain.Request;
import springseller.filipey.domain.enums.RequestStatus;

import java.util.Optional;

public interface RequestService {
    Request save(RequestDTO dto);

    Optional<Request> getFullRequest(Long id);

    RequestInfoDTO getById(Long id);

    void updateStatus(Long id, RequestStatusUpdateDTO dto);
}
