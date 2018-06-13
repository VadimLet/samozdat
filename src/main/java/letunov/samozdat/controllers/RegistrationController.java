package letunov.samozdat.controllers;

import letunov.samozdat.domain.User;
import letunov.samozdat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegristration() {
        return "registration";
    }

    @PostMapping
    public String add(User user) {
        if (userService.add(user)) {

        }
    }
}
