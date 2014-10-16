
package pl.gda.pg.eti.kask.javaee.jsf.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * <p>Java class for mag complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="mag">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="imie" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mana" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="zywiol" use="required" type="{http://www.eti.pg.gda.pl/kask/javaee/wieze}zywiol" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mag", namespace = "http://www.eti.pg.gda.pl/kask/javaee/wieze")
public class Mag {

  @XmlAttribute(name = "imie", required = true)
  protected String imie;
  @XmlAttribute(name = "mana", required = true)
  protected int mana;
  @XmlAttribute(name = "zywiol", required = true)
  protected Zywiol zywiol = Zywiol.WODA;
  @XmlAttribute(name = "id", required = true)
  protected int id;

  @XmlTransient
  protected Wieza wieza;

  /**
   * Gets the value of the imie property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getImie() {
    return imie;
  }

  /**
   * Sets the value of the imie property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setImie(String value) {
    this.imie = value;
  }

  /**
   * Gets the value of the mana property.
   */
  public int getMana() {
    return mana;
  }

  /**
   * Sets the value of the mana property.
   */
  public void setMana(int value) {
    this.mana = value;
  }

  /**
   * Gets the value of the zywiol property.
   *
   * @return possible object is
   * {@link Zywiol }
   */
  public Zywiol getZywiol() {
    return zywiol;
  }

  /**
   * Sets the value of the zywiol property.
   *
   * @param value allowed object is
   *              {@link Zywiol }
   */
  public void setZywiol(Zywiol value) {
    this.zywiol = value;
  }

  /**
   * Gets the value of the id property.
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   */
  public void setId(int value) {
    this.id = value;
  }


  public Wieza getWieza() {
    return wieza;
  }

  public void setWieza(Wieza wieza) {
    this.setWieza(wieza, true);
  }

  public void setWieza(Wieza wieza, boolean addMagToWieza) {
    // if wieza not changed then nop & return
    if ((this.wieza == wieza) ||
        (null != this.wieza && this.wieza.equals(wieza))) {
      return;
    }

    if (null != this.wieza) {
      // removing mag from previous tower
      List<Mag> oldTowerMags = this.wieza.getMag();
      oldTowerMags.remove(this);
      this.wieza.setMag(oldTowerMags);
    }

    // assigning new tower
    this.wieza = wieza;

    if (null != this.wieza && addMagToWieza) {
      // adding mag to new tower
      List<Mag> newTowerMags = this.wieza.getMag();
      newTowerMags.add(this);
      this.wieza.setMag(newTowerMags);
    }
  }
}
