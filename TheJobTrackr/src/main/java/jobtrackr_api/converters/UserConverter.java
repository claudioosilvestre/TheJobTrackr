package jobtrackr_api.converters;

import jobtrackr_api.dtos.UserRequestDTO;
import jobtrackr_api.dtos.UserResponseDTO;
import jobtrackr_api.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponseDTO toResponseDTO (User user) {

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmail(user.getEmail());

        return userResponseDTO;
    }

    public User toEntity(UserRequestDTO userRequestDTO) {

        User user = new User();

        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());

        return user;
    }
}
