package letunov.samozdat.controllers;

import letunov.samozdat.domain.User;
import letunov.samozdat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    public UserService userService;

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user, Model model) {
        if (userService.add(user)) {
            return "login";
        } else {
            model.addAttribute("message", "Email уже занят");
            return "redirect:/";
        }
    }
}
