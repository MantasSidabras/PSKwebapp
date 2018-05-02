package usecases;

import entities.Author;
import persistence.AuthorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Authors {
    @Inject
    private AuthorsDAO authorsDAO;
    private List<Author> allAuthors;

    private Author newAuthor = new Author();

    @PostConstruct
    public void init() {
        this.loadAuthors();
    }

    @Transactional
    public String createNewAuthor() {
        authorsDAO.save(newAuthor);
        return "index?faces-redirect=true";
    }

    private void loadAuthors() {
        this.allAuthors = authorsDAO.loadAll();
    }

    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    public Author getNewAuthor() {
        return newAuthor;
    }

    public void setNewAuthor(Author newAuthor) {
        this.newAuthor = newAuthor;
    }
}