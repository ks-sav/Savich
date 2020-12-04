package ru.sfedu.SchoolMeals.model.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.Objects;

/**
 * Class Staff
 */
@Root
public class Staff extends Customer {
  @Attribute
  private Boolean unionMember;

  public Staff(long id, String name, Boolean unionMember) {
    super(id, name);
    this.unionMember = unionMember;
  }

  public Staff(){
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
