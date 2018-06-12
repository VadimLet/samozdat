package letunov.samozdat;

import letunov.samozdat.domain.Book;
import letunov.samozdat.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private BookRepo bookRepo;


    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
         Iterable<Book> books =  bookRepo.findAll();
        model.put("books", books);
        return "main";
    }
     @PostMapping
    public String add(@RequestParam String text, @RequestParam String name, Map<String, Object> model){
        Book book = new Book(text, name);

        bookRepo.save(book);

         Iterable<Book> books =  bookRepo.findAll();

         model.put("books", books);

        return  "main";
     }
}
