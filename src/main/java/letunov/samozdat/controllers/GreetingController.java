package letunov.samozdat.controllers;

import letunov.samozdat.domain.Book;
import letunov.samozdat.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private BookRepo bookRepo;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
         Iterable<Book> books =  bookRepo.findAll();
        model.put("books", books);
        return "main";
    }
     @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String name, Map<String, Object> model){
        Book book = new Book(text, name);

        bookRepo.save(book);

         Iterable<Book> books =  bookRepo.findAll();

         model.put("books", books);

        return  "main";
     }

     @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Book> books;

        if (filter != null && !filter.isEmpty()) {
             books = bookRepo.findByName(filter);
        }
        else
            {
                books = bookRepo.findAll();
            }
        model.put("books", books);
        return "main";
     }
}
