package jobtrackr_api.services;

import jobtrackr_api.converters.UserConverter;
import jobtrackr_api.dtos.UserRequestDTO;
import jobtrackr_api.dtos.UserResponseDTO;
import jobtrackr_api.exceptions.UserEmailAlreadyExistsException;
import jobtrackr_api.exceptions.UserNotFoundException;
import jobtrackr_api.models.User;
import jobtrackr_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserConverter userConverter;

    @Override
    public List<UserResponseDTO> listAll() {

        return userRepository.findAll().stream()
                .map(user -> userConverter.toResponseDTO(user))
                .toList();
    }

    @Override
    public UserResponseDTO findById(Long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        return userConverter.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if(userRequestDTO == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if(userRepository.findByEmail(userRequestDTO.getEmail()) != null) {
            throw new UserEmailAlreadyExistsException();
        }

        User user = userConverter.toEntity(userRequestDTO);

        userRepository.save(user);

        return userConverter.toResponseDTO(user);
    }

    @Override
    public void deleteById(Long id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }

        userRepository.deleteById(findById(id).getId());

    }

    @Autowired
    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
