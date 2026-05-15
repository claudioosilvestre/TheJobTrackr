package jobtrackr_api.repositories;

import jobtrackr_api.models.Company;
import jobtrackr_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
