package letunov.samozdat.repos;

import letunov.samozdat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String Email);
    User findByUsername(String username);
}
