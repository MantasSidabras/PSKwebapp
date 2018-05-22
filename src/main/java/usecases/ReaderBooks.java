package usecases;

import entities.Book;
import entities.Reader;
import persistence.BooksDAO;
import persistence.ReaderDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class ReaderBooks {
    Reader reader;

    @Inject
    ReaderDAO readerDAO;

    @Inject
    BooksDAO booksDAO;

    private String IsbnToAdd;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer readerId = Integer.parseInt(requestParameters.get("readerId"));
        this.reader = readerDAO.findOne(readerId);
    }


    @Transactional
    public String borrowBook(String isbn) {
//        reader.getBooks().add(booksDAO.findByIsbn(isbn));
        Book b = booksDAO.findByIsbn(isbn);
        b.getReaders().add(this.reader);
//        readerDAO.save(reader);
        booksDAO.save(b);
        return "reader?faces-redirect=true&readerId=" + this.reader.getId();
    }

    public String getIsbnToAdd() {
        return IsbnToAdd;
    }

    public void setIsbnToAdd(String isbnToAdd) {
        IsbnToAdd = isbnToAdd;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}