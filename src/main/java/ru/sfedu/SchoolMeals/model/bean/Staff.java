package ru.sfedu.SchoolMeals.model.bean;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Staff staff = (Staff) o;
    return Objects.equals(unionMember, staff.unionMember);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), unionMember);
  }

  @Override
  public String toString() {
    return "Staff{" +
            "unionMember=" + unionMember +
            '}';
  }
}
