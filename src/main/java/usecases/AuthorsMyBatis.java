package usecases;

import mybatis.dao.AuthorMapper;
import mybatis.model.Author;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AuthorsMyBatis {

    @Inject
    private AuthorMapper authorMapper;

    private List<Author> allAuthors;

    private Author newAuthor = new Author();

    @PostConstruct
    public void init() {
        this.loadAuthors();
    }

    private void loadAuthors() {
        this.allAuthors = authorMapper.selectAll();
    }

    @Transactional
    public String createNewAuthor() {
        authorMapper.insert(newAuthor);
        return "/mybatis/authors?faces-redirect=true";
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
