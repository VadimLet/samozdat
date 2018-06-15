package letunov.samozdat.controllers;

import letunov.samozdat.domain.Book;
import letunov.samozdat.domain.User;
import letunov.samozdat.helpers.FileHelpers;
import letunov.samozdat.repos.BookRepo;
import letunov.samozdat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookRepo bookRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, @RequestParam(required = false, defaultValue = "") String filterAuthor, Model model) {
        Iterable<Book> books;

        if (filter != null && !filter.isEmpty()) {
            books = bookRepo.findByTitle(filter);
        } else {
            books = bookRepo.findAll();
        }

        if (filterAuthor != null && !filterAuthor.isEmpty()) {
            User userTemp = (User) userService.loadUserByUsername(filterAuthor);
            if (userTemp!=null) {
               books = bookRepo.findByAuthor(userTemp);
            } else {
                books = bookRepo.findAll();
            }
        }

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        model.addAttribute("filterAuthor", filterAuthor);

        return "main";
    }



    private void saveFile(@Valid Book book, MultipartFile file) throws IOException {
        FileHelpers.saveFile(book, file, uploadPath);
    }
}
