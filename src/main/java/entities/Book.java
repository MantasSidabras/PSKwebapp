package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @Size(min = 13, max = 13)
    @Column(name = "ISBN")
    private String isbn;

    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @ManyToMany
    @JoinTable(name="BOOK_READER", joinColumns = {
            @JoinColumn(name = "BOOK_ISBN", referencedColumnName = "ISBN")},
            inverseJoinColumns = {
            @JoinColumn(name="READER_ID", referencedColumnName = "ID")})
    private List<Reader> readers = new ArrayList<>();

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
