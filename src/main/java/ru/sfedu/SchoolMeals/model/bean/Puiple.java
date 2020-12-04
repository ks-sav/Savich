package ru.sfedu.SchoolMeals.model.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.Objects;

/**
 * Class Puiple
 */
@Root
public class Puiple extends Customer implements WithId{

  //
  // Fields
  //
  @Attribute
  private long nclass;
  @Attribute
  private Boolean freeMeals;
  //
  // Constructors
  //
  public Puiple(long id, String name, long nclass, Boolean freeMeals) {
    super(id, name);
    this.nclass = nclass;
    this.freeMeals = freeMeals;
  }

  public Puiple(){

  }
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of class
   * @param newVar the new value of class
   */
  public void setNClass (long newVar) {
    nclass = newVar;
  }

  /**
   * Get the value of class
   * @return the value of class
   */
  public long getNClass () {
    return nclass;
  }

  /**
   * Set the value of freeMeals
   * @param newVar the new value of freeMeals
   */
  public void setFreeMeals (Boolean newVar) {
    freeMeals = newVar;
  }

  /**
   * Get the value of freeMeals
   * @return the value of freeMeals
   */
  public Boolean getFreeMeals () {
    return freeMeals;
  }

  //
  // Other methods
  //

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Puiple puiple = (Puiple) o;
    return Objects.equals(nclass, puiple.nclass) &&
            Objects.equals(freeMeals, puiple.freeMeals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), nclass, freeMeals);
  }

  @Override
  public String toString() {
    return "Puiple{" +
            "nclass='" + nclass + '\'' +
            ", freeMeals=" + freeMeals +
            '}';
  }
}
