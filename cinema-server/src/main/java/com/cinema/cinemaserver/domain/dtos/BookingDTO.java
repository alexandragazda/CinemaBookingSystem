package com.cinema.cinemaserver.domain.dtos;

public class BookingDTO {
   private Integer showtimeID;
   private String userEmail;
   private String customerEmail;
   private String customerFirstName;
   private String customerLastName;
   private Integer nrChildTickets;
   private Integer nrStudentTickets;
   private Integer nrAdultTickets;
   private Integer nrRetiredTickets;
   private Double totalPrice;
   private String selectedSeats;

   public BookingDTO() { }

    public BookingDTO(Integer showtimeID, String userEmail, String customerEmail, String customerFirstName, String customerLastName, Integer nrChildTickets, Integer nrStudentTickets, Integer nrAdultTickets, Integer nrRetiredTickets, Double totalPrice, String selectedSeats) {
        this.showtimeID = showtimeID;
        this.userEmail = userEmail;
        this.customerEmail = customerEmail;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.nrChildTickets = nrChildTickets;
        this.nrStudentTickets = nrStudentTickets;
        this.nrAdultTickets = nrAdultTickets;
        this.nrRetiredTickets = nrRetiredTickets;
        this.totalPrice = totalPrice;
        this.selectedSeats = selectedSeats;
    }

    public Integer getShowtimeID() {
      return showtimeID;
   }

   public void setShowtimeID(Integer showtimeID) {
      this.showtimeID = showtimeID;
   }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public Integer getNrChildTickets() {
      return nrChildTickets;
   }

   public void setNrChildTickets(Integer nrChildTickets) {
      this.nrChildTickets = nrChildTickets;
   }

   public Integer getNrStudentTickets() {
      return nrStudentTickets;
   }

   public void setNrStudentTickets(Integer nrStudentTickets) {
      this.nrStudentTickets = nrStudentTickets;
   }

   public Integer getNrAdultTickets() {
      return nrAdultTickets;
   }

   public void setNrAdultTickets(Integer nrAdultTickets) {
      this.nrAdultTickets = nrAdultTickets;
   }

   public Integer getNrRetiredTickets() {
      return nrRetiredTickets;
   }

   public void setNrRetiredTickets(Integer nrRetiredTickets) {
      this.nrRetiredTickets = nrRetiredTickets;
   }

   public Double getTotalPrice() {
      return totalPrice;
   }

   public void setTotalPrice(Double totalPrice) {
      this.totalPrice = totalPrice;
   }

   public String getSelectedSeats() {
      return selectedSeats;
   }

   public void setSelectedSeats(String selectedSeats) {
      this.selectedSeats = selectedSeats;
   }

   @Override
   public String toString() {
      return "BookingDTO: " +
              "showtimeID=" + showtimeID + " | " +
              "userEmail=" + userEmail + " | " +
              "customerEmail=" + customerEmail + " | " +
              "customerFirstName=" + customerFirstName + " | " +
              "customerLastName=" + customerLastName + " | " +
              "nrChildTickets=" + nrChildTickets + " | " +
              "nrStudentTickets=" + nrStudentTickets + " | " +
              "nrAdultTickets=" + nrAdultTickets + " | " +
              "nrRetiredTickets=" + nrRetiredTickets + " | " +
              "totalPrice=" + totalPrice + " | " +
              "selectedSeats='" + selectedSeats;
   }
}
