package letunov.samozdat.controllers;

import letunov.samozdat.domain.User;
import letunov.samozdat.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class ProfileController {
    @Autowired
    private BookRepo bookRepo;

    @GetMapping("profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("myBooks", bookRepo.findByAuthor(user));
        model.addAttribute("favouriteBook", bookRepo.findBySubscriptions(user));
        return "profile";
    }

    @GetMapping("otherprofile/{user}")
    public String getOtherProfile(@PathVariable User user, Model model) {
        model.addAttribute("userView", user);
        model.addAttribute("myBooks", bookRepo.findByAuthor(user));
        model.addAttribute("favouriteBook", bookRepo.findBySubscriptions(user));
        return "otherprofile";
    }

}
