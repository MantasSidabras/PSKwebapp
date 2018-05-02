package persistence;

import entities.Author;
import entities.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ReaderDAO {
    @Inject
    private EntityManager em;

    public List<Reader> loadAll() {
        return em.createNamedQuery("Reader.findAll", Reader.class).getResultList();
    }

    public Reader findOne(Integer id) {
        return em.find(Reader.class, id);
    }

    public void save(Reader reader) {
        this.em.persist(reader);
    }
}