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
  private long id;
  private long ComboId;
  private String name;
  private long FoodId;

  //
  // Constructors
  //
  public ComboMeals(long id, long comboId, String name, long foodId) {
    this.id = id;
    ComboId = comboId;
    this.name = name;
    FoodId = foodId;
  }

  //
  // Methods
  //


  //
  // Accessor methods
  //

  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getComboId() {
    return ComboId;
  }

  public void setComboId(Integer comboId) {
    ComboId = comboId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getFoodId() {
    return FoodId;
  }

  public void setFoodId(long foodId) {
    FoodId = foodId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ComboMeals that = (ComboMeals) o;
    return id == that.id &&
            FoodId == that.FoodId &&
            Objects.equals(ComboId, that.ComboId) &&
            Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ComboId, name, FoodId);
  }
}
