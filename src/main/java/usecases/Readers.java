package usecases;

import entities.Reader;
import persistence.ReaderDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Readers {
    @Inject
    ReaderDAO readerDAO;

    List<Reader> allReaders;

    Reader newReader = new Reader();


    @PostConstruct
    public void init() {
        this.loadReaders();
    }

    private void loadReaders() {
        this.allReaders = readerDAO.loadAll();
    }

    @Transactional
    public String createNewReader() {
        readerDAO.save(newReader);
        return "index?faces-redirect=true";
    }

    public List<Reader> getAllReaders() {
        return allReaders;
    }

    public Reader getNewReader() {
        return newReader;
    }

    public void setNewReader(Reader newReader) {
        this.newReader = newReader;
    }
}