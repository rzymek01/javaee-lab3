package pl.gda.pg.eti.kask.javaee.jsf.entities;

import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author psysiu
 */
@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public Author createAuthor() {
        return new Author();
    }

    public Library createLibrary() {
        return new Library();
    }

    public Book createBook() {
        return new Book();
    }


}
