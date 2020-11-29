package ru.sfedu.SchoolMeals.model.bean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Class ComboMeals
 */
public class ComboMeals implements WithId {

  //
  // Fields
  //
  private Integer idCombo;
  private String date;
  private List<FoodItem> menu;
  private Long price;
  
  //
  // Constructors
  //
  public ComboMeals () { };

  public ComboMeals(long parseLong, Timestamp valueOf, List<FoodItem> csvDatum, Double valueOf1) {
  }

  //
  // Methods
  //


  //
  // Accessor methods
  //
  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setIdCombo (Integer newVar) {
    idCombo = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public Integer getIdCombo () {
    return idCombo;
  }
  /**
   * Set the value of date
   * @param newVar the new value of date
   */
  public void setDate (String newVar) {
    date = newVar;
  }

  /**
   * Get the value of date
   * @return the value of date
   */
  public String getDate () {
    return date;
  }

  /**
   * Set the value of menu
   * @param newVar the new value of menu
   */
  public void setMenu (List<FoodItem> newVar) {
    menu = newVar;
  }

  /**
   * Get the value of menu
   * @return the value of menu
   */
  public List<FoodItem> getMenu () {
    return menu;
  }

  /**
   * Set the value of price
   * @param newVar the new value of price
   */
  public void setPrice (Long newVar) {
    price = newVar;
  }

  /**
   * Get the value of price
   * @return the value of price
   */
  public Long getPrice () {
    return price;
  }

  @Override
  public long getId() {
    return 0;
  }

  //
  // Other methods
  //

  @Override
  public String toString() {
    return "ComboMeals{" +
            "idCombo=" + idCombo +
            ", date='" + date + '\'' +
            ", menu=" + menu +
            ", price=" + price +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ComboMeals that = (ComboMeals) o;
    return Objects.equals(idCombo, that.idCombo) &&
            Objects.equals(date, that.date) &&
            Objects.equals(menu, that.menu) &&
            Objects.equals(price, that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCombo, date, menu, price);
  }
}
