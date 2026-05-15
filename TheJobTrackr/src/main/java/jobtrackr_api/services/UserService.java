package jobtrackr_api.services;

import jobtrackr_api.dtos.UserRequestDTO;
import jobtrackr_api.dtos.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> listAll();

    UserResponseDTO findById(Long id);

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    void deleteById(Long id);
}
