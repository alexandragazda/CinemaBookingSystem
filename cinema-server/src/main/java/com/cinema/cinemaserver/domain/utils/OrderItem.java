package com.cinema.cinemaserver.domain.utils;

public class OrderItem{
    private Integer concessionID;
    private Integer qty;

    public OrderItem() { }

    public OrderItem(Integer concessionID, Integer qty) {
        this.concessionID = concessionID;
        this.qty = qty;
    }

    public Integer getConcessionID() {
        return concessionID;
    }

    public void setConcessionID(Integer concessionID) {
        this.concessionID = concessionID;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderItem: " +
                "concessionID=" + concessionID + " | " +
                "qty=" + qty;
    }
}
