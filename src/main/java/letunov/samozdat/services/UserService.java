package letunov.samozdat.services;

import letunov.samozdat.domain.User;
import letunov.samozdat.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public boolean add(User user) {
        if (userRepo.findByUsername(user.getEmail()) != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        String uuid = UUID.randomUUID().toString();

        try {
            mailSender.send(user.getEmail(), "activate account", "click to activate http://localhost:8080/activate/" + uuid);
            user.setToken(uuid);
        } catch (Exception e) {
            System.err.println("mail error");
        }
        userRepo.save(user);
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByToken(code);
        if (user.getToken() == null) {
            return false;
        }

        user.setToken("null");
        user.setActive(true);
        userRepo.save(user);
        return true;
    }
}
