package ru.sfedu.SchoolMeals.model.bean;

/**
 * Class Staff
 */
public class Staff extends Customer {

  private Boolean unionMember;

  public Staff () { };

    public Staff(long parseLong, String csvDatum, Boolean valueOf) {
    }

    public void setUnionMember (Boolean newVar) {
    unionMember = newVar;
  }

  public Boolean getUnionMember () {
    return unionMember;
  }

}
