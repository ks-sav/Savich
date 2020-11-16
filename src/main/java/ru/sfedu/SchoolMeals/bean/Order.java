package ru.sfedu.SchoolMeals.bean;

/**
 * Class Order
 */
public class Order {

  //
  // Fields
  //

  private Integer pupilId;
  private String date;
  private OrderStatus status;
  private Long totalCost;
  
  //
  // Constructors
  //
  public Order () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

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
  public Integer getPupilId () {
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

}
