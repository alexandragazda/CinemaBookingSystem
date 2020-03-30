package com.cinema.cinemaserver.domain.dtos;

public class PlacedOrderItemDTO {
    private Integer quantity;
    private String concessionName;
    private Double concessionPrice;

    public PlacedOrderItemDTO() { }

    public PlacedOrderItemDTO(Integer quantity, String concessionName, Double concessionPrice) {
        this.quantity = quantity;
        this.concessionName = concessionName;
        this.concessionPrice = concessionPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getConcessionName() {
        return concessionName;
    }

    public void setConcessionName(String concessionName) {
        this.concessionName = concessionName;
    }

    public Double getConcessionPrice() {
        return concessionPrice;
    }

    public void setConcessionPrice(Double concessionPrice) {
        this.concessionPrice = concessionPrice;
    }

    @Override
    public String toString() {
        return "PlacedOrderItemDTO:" +
                "quantity=" + quantity + " | " +
                "concessionName=" + concessionName + " | " +
                "concessionPrice=" + concessionPrice;
    }
}
