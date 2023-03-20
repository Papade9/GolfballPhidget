package com.db.tableclass;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by tcarlston on 4/27/2017.
 */
@Entity
@Table(name = "Orders", schema = "dbo", catalog = "Nreg")
public class OrdersEntity {
    public long orderId;
    public Long transactionId;
    public Long cashPurseId;
    public Boolean voidSale;
    public LocalDateTime timeStamp;
    public Integer locationId;
    public Integer registerId;
    public Byte diningPreference;
    public Integer tableNumber;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "OrderID", nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "TransactionID", nullable = true)
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "CashPurseID", nullable = true)
    public Long getCashPurseId() {
        return cashPurseId;
    }

    public void setCashPurseId(Long cashPurseId) {
        this.cashPurseId = cashPurseId;
    }

    @Basic
    @Column(name = "VoidSale", nullable = true)
    public Boolean getVoidSale() {
        return voidSale;
    }

    public void setVoidSale(Boolean voidSale) {
        this.voidSale = voidSale;
    }

    @Basic
    @Column(name = "TimeStamp", nullable = true)
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Basic
    @Column(name = "LocationId", nullable = true)
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "RegisterId", nullable = true)
    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    @Basic
    @Column(name = "DiningPreference", nullable = true)
    public Byte getDiningPreference() {
        return diningPreference;
    }

    public void setDiningPreference(Byte diningPreference) {
        this.diningPreference = diningPreference;
    }

    @Basic
    @Column(name = "TableNumber", nullable = true)
    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderId != that.orderId) return false;
        if (transactionId != null ? !transactionId.equals(that.transactionId) : that.transactionId != null)
            return false;
        if (cashPurseId != null ? !cashPurseId.equals(that.cashPurseId) : that.cashPurseId != null) return false;
        if (voidSale != null ? !voidSale.equals(that.voidSale) : that.voidSale != null) return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (registerId != null ? !registerId.equals(that.registerId) : that.registerId != null) return false;
        if (diningPreference != null ? !diningPreference.equals(that.diningPreference) : that.diningPreference != null)
            return false;
        if (tableNumber != null ? !tableNumber.equals(that.tableNumber) : that.tableNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (transactionId != null ? transactionId.hashCode() : 0);
        result = 31 * result + (cashPurseId != null ? cashPurseId.hashCode() : 0);
        result = 31 * result + (voidSale != null ? voidSale.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (registerId != null ? registerId.hashCode() : 0);
        result = 31 * result + (diningPreference != null ? diningPreference.hashCode() : 0);
        result = 31 * result + (tableNumber != null ? tableNumber.hashCode() : 0);
        return result;
    }
}
