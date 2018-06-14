package letunov.samozdat.repos;

import letunov.samozdat.domain.Book;
import letunov.samozdat.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(User author);

    Set<Book> findBySubscriptions(User user);
}
