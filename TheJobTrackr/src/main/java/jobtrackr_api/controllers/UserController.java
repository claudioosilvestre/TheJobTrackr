package jobtrackr_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jobtrackr_api.dtos.UserRequestDTO;
import jobtrackr_api.dtos.UserResponseDTO;
import jobtrackr_api.models.User;
import jobtrackr_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Manage users")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Operation(summary = "List all the users")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {

        return ResponseEntity.ok(userService.listAll());
    }

    @Operation(summary = "Create user")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @Operation(summary = "Find user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById (@PathVariable Long id) {

        UserResponseDTO userResponseDTO = userService.findById(id);

        return ResponseEntity.ok(userResponseDTO);
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
