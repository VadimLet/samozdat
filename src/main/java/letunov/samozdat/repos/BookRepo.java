package letunov.samozdat.repos;

import letunov.samozdat.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo  extends CrudRepository<Book, Long> {
}
