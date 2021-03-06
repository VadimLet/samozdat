package letunov.samozdat.repos;

import letunov.samozdat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String Email);
    User findByUsername(String username);

    User findByToken(String token);
}
