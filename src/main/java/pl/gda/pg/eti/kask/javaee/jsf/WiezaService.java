package pl.gda.pg.eti.kask.javaee.jsf;

import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Mag;
import pl.gda.pg.eti.kask.javaee.jsf.entities.ObjectFactory;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Swiat;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Wieza;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;

/**
 * @author psysiu
 * @author maciek
 */
@ManagedBean
@ApplicationScoped
@Log
public class WiezaService implements Serializable {

  private SortedMap<Integer, Wieza> wieze;

  private SortedMap<Integer, Mag> magowie;

  public WiezaService() {
    wieze = new TreeMap<>();
    magowie = new TreeMap<>();
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
      Unmarshaller u = jaxbContext.createUnmarshaller();
      Swiat swiat = (Swiat) u.unmarshal(getClass().getResourceAsStream("/pl/gda/pg/eti/kask/javaee/jsf/xml/wieze.xml"));
      for (Wieza wieza : swiat.getWieza()) {
        wieze.put(wieza.getId(), wieza);
        for (Mag mag : wieza.getMag()) {
          magowie.put(mag.getId(), mag);
        }
      }
    } catch (JAXBException ex) {
      log.log(Level.WARNING, ex.getMessage(), ex);
    }
  }

  private List<Mag> asList(Mag... magowie) {
    List<Mag> list = new ArrayList<>();
    for (Mag mag : magowie) {
      list.add(mag);
    }
    return list;
  }

  public List<Wieza> findAllWieze() {
    return new ArrayList<>(wieze.values());
  }

  public Wieza findWieza(int id) {
    return wieze.get(id);
  }

  public void removeWieza(Wieza wieza) {
    wieze.remove(wieza.getId());
  }

  public void saveWieza(Wieza wieza) {
    if (wieza.getId() == 0) {
      wieza.setId(wieze.lastKey() + 1);
    }
    wieze.put(wieza.getId(), wieza);
  }

  public List<Mag> findAllMagowie() {
    return new ArrayList<>(magowie.values());
  }

  public Mag findMag(int id) {
    return magowie.get(id);
  }

  public void marshalSwiat(OutputStream out) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
      Marshaller m = jaxbContext.createMarshaller();
      Swiat swiat = new Swiat();
      swiat.getWieza().addAll(wieze.values());
      m.marshal(swiat, out);
    } catch (JAXBException ex) {
      log.log(Level.WARNING, ex.getMessage(), ex);
    }
  }
}
