package softuni.booksrestserver.service;

import org.springframework.stereotype.Service;
import softuni.booksrestserver.model.dto.AuthorDTO;
import softuni.booksrestserver.model.dto.BookDTO;
import softuni.booksrestserver.model.entity.AuthorEntity;
import softuni.booksrestserver.model.entity.BookEntity;
import softuni.booksrestserver.repository.AuthorRepository;
import softuni.booksrestserver.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public long createBook(BookDTO newBook) {
        String authorName = newBook.getAuthor().getName();
        Optional<AuthorEntity> authorOpt = this.authorRepository.findAuthorEntityByName(authorName);

        BookEntity newBookEntity = new BookEntity().setTitle(newBook.getTitle())
                .setIsbn(newBook.getIsbn())
                .setAuthor(authorOpt.orElseGet(() -> createNewAuthor(authorName)));

        return bookRepository.save(newBookEntity).getId();
    }

    private AuthorEntity createNewAuthor(String authorName) {
        return authorRepository.save(new AuthorEntity().setName(authorName));
    }


    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Optional<BookDTO> findBookById(Long bookId) {
        return bookRepository.findById(bookId).map(this::map);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private BookDTO map(BookEntity bookEntity) {
        AuthorDTO authorDTO = new AuthorDTO()
                .setName(bookEntity.getAuthor().getName());

        return new BookDTO()
                .setId(bookEntity.getId()).setAuthor(authorDTO)
                .setIsbn(bookEntity.getIsbn())
                .setTitle(bookEntity.getTitle());
    }
}
