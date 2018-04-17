package persistence;

import entities.Furniture;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class FurnitureDataAccessObject {
    @PersistenceContext
    private EntityManager em;

    public List<Furniture> loadAll() {
        return em.createNamedQuery("Furniture.findAll", Furniture.class).getResultList();
    }

    public void save(Furniture furniture) {
        this.em.persist(furniture);
    }
}