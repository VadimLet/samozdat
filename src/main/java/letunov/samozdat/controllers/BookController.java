package letunov.samozdat.controllers;

import letunov.samozdat.domain.Book;
import letunov.samozdat.domain.User;
import letunov.samozdat.helpers.FileHelpers;
import letunov.samozdat.repos.BookRepo;
import letunov.samozdat.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/book/{book}")
    public String getBookPage(@PathVariable Book book, Model model) {
//        model.addAttribute("book", bookRepo.findById(id));
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("/book/{book}")
    public String updateMessage(
            @AuthenticationPrincipal User user,
            @PathVariable Book book,
            @RequestParam("text") String text,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (book.getAuthor().equals(user)) {
            if (!StringUtils.isEmpty(text)) {
                book.setText(text);
            }

            if (!StringUtils.isEmpty(title)) {
                book.setTitle(title);
            }
            if (!StringUtils.isEmpty(description)) {
                book.setDescription(description);
            }

            FileHelpers.saveFile(book, file, uploadPath);
            bookRepo.save(book);
        }

        return "redirect:/book/" + book.getId();
    }

    @PostMapping("addbook")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Book book,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        book.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("book", book);
            return "profile";
        } else {
            FileHelpers.saveFile(book, file, uploadPath);

            model.addAttribute("book", null);

            bookRepo.save(book);
        }

        Iterable<Book> books = bookRepo.findAll();

        model.addAttribute("books", books);

        return "main";
    }

    @GetMapping("addfavourite/{book}")
    public String addToFavourite(@AuthenticationPrincipal User user, @PathVariable Book book) {
        User userCurrent = userRepo.findById(user.getId()).get();
        if (!userCurrent.getFavourite().contains(book)) {
            book.getSubscriptions().add(userCurrent);
            userCurrent.getFavourite().add(book);
            userRepo.save(userCurrent);
        }
        return "redirect:/main";
    }


}
