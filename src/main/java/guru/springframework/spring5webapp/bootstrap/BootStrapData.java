package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher penguin = new Publisher("Penguin","Kommener str. 5", "", "Mechernich", "NRW", "53894");
        publisherRepository.save(penguin);

        Author bernhard = new Author("Bernhard", "Hennen");
        Book elfen = new Book("Die Elfen", "123456789");
        bernhard.getBooks().add(elfen);
        elfen.getAuthors().add(bernhard);

        elfen.setPublisher(penguin);
        penguin.getBooks().add(elfen);

        authorRepository.save(bernhard);
        bookRepository.save(elfen);
        publisherRepository.save(penguin);

        Author kai = new Author("Kai", "Meyer");
        Book faust = new Book("Faustus","345678");
        kai.getBooks().add(faust);
        faust.getAuthors().add(kai);
        authorRepository.save(kai);
        bookRepository.save(faust);

        elfen.setPublisher(penguin);
        penguin.getBooks().add(faust);
        publisherRepository.save(penguin);



        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: "+bookRepository.count());
        System.out.println("Number of Authors: "+authorRepository.count());
        System.out.println("Number of Publisher: "+publisherRepository.count());
        System.out.println("Publisher of Books: "+penguin.getBooks().size());



    }
}
