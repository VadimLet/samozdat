package letunov.samozdat.repos;

import letunov.samozdat.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo  extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);
}
