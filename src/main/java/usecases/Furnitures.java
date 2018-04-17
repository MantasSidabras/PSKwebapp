package usecases;

import entities.Furniture;
import persistence.FurnitureDataAccessObject;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Furnitures{
    @Inject
    private FurnitureDataAccessObject furnitureDAO;
    private List<Furniture> furniture;
    private Furniture newFurniture = new Furniture();

    @PostConstruct
    public void init() {
        this.loadFurniture();
    }

    @Transactional
    public String createNewFurniture() {
        furnitureDAO.save(newFurniture);
        return "success"; // Why this should not be here? How could this be solved?
    }

    private void loadFurniture() {
        this.furniture = furnitureDAO.loadAll();
    }

    public List<Furniture> getFurniture(){
        return furniture;
    }

    public Furniture getNewFurniture() {
        return newFurniture;
    }

    public void setNewFurniture(Furniture newAuthor) {
        this.newFurniture = newFurniture;
    }
}