package persistence;


import entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class BooksDAO {
    @Inject
    private EntityManager em;

    @Transactional
    public void save(Book book) {
        this.em.persist(book);
    }

    public Book findByIsbn(String isbn){
        return this.em.find(Book.class, isbn);
    }
}
