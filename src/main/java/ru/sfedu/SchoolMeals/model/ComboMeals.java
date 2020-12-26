package ru.sfedu.SchoolMeals.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.Objects;

/**
 * Class ComboMeals
 */
@Root
public class ComboMeals {
  //
  // Fields
  //
  @Attribute
  private long id;
  @Attribute
  private long comboId;
  @Attribute
  private String name;
  @Attribute
  private long foodId;
  @Attribute
  private long[] itemsId = new long[10];

  //
  // Constructors
  //

  public ComboMeals(long id, long comboId, String name, long foodId) {
    this.id = id;
    this.comboId = comboId;
    this.name = name;
    this.foodId = foodId;
  }

  public ComboMeals() {
  }

  //
  // Methods
  //


  //
  // Accessor methods
  //

  public void setItemsId(long[] itemsId)
  {this.itemsId = itemsId;}

  public long[] getItemsId(){return itemsId;}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getComboId() {
    return comboId;
  }

  public void setComboId(Integer comboId) {
    comboId = comboId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getFoodId() {
    return foodId;
  }

  public void setFoodId(long foodId) {
    foodId = foodId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ComboMeals that = (ComboMeals) o;
    return id == that.id &&
            foodId == that.foodId &&
            Objects.equals(comboId, that.comboId) &&
            Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, comboId, name, foodId);
  }
}
