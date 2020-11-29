package ru.sfedu.SchoolMeals.model.bean;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class Order
 */
public class Order implements WithId{

  //
  // Fields
  //
  private long id;
  private long pupilId;
  private String date;
  private OrderStatus status;
  private long totalCost;
  
  //
  // Constructors
  //
  public Order () { };


  public Order(long parseLong, long parseLong1, Timestamp valueOf, String csvDatum, double parseDouble) {
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
  public void setId (Integer newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public long getId () {
    return id;
  }
  /**
   * Set the value of pupilId
   * @param newVar the new value of pupilId
   */
  public void setPupilId (Integer newVar) {
    pupilId = newVar;
  }

  /**
   * Get the value of pupilId
   * @return the value of pupilId
   */
  public long getPupilId () {
    return pupilId;
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
   * Set the value of status
   * @param newVar the new value of status
   */
  public void setStatus (OrderStatus newVar) {
    status = newVar;
  }

  /**
   * Get the value of status
   * @return the value of status
   */
  public OrderStatus getStatus () {
    return status;
  }

  /**
   * Set the value of totalCost
   * @param newVar the new value of totalCost
   */
  public void setTotalCost (Long newVar) {
    totalCost = newVar;
  }

  /**
   * Get the value of totalCost
   * @return the value of totalCost
   */
  public Long getTotalCost () {
    return totalCost;
  }

  //
  // Other methods
  //

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return id == order.id &&
            pupilId == order.pupilId &&
            totalCost == order.totalCost &&
            Objects.equals(date, order.date) &&
            status == order.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pupilId, date, status, totalCost);
  }

  @Override
  public String toString() {
    return "Order{" +
            "id=" + id +
            ", pupilId=" + pupilId +
            ", date='" + date + '\'' +
            ", status=" + status +
            ", totalCost=" + totalCost +
            '}';
  }
}
