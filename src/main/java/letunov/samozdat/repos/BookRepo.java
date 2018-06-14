package letunov.samozdat.repos;

import letunov.samozdat.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String title);
}
