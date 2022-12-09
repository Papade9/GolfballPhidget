package com.db.tableclass;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by tcarlston on 4/27/2017.
 */
@Entity
@Table(name = "LastUsed", schema = "dbo", catalog = "Nreg")
public class LastUsedEntity{
    public int id;
    public Integer locationId;
    public Long ticketNumber;
    public Integer redeemTicketId;
    public LocalDateTime timeStamp;
    public Integer registerId;
    public Long orderTransId;
    public Integer cashierId;
    public RegisterSetupEntity registerByRegisterId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "TicketNumber", nullable = true)
    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Basic
    @Column(name = "RedeemTicketId", nullable = true)
    public Integer getRedeemTicketId() {
        return redeemTicketId;
    }

    public void setRedeemTicketId(Integer redeemTicket) {
        this.redeemTicketId = redeemTicket;
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
    @Column(name = "RegisterId", nullable = true)
    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    @Basic
    @Column(name = "OrderTransID", nullable = true)
    public Long getOrderTransId() {
        return orderTransId;
    }

    public void setOrderTransId(Long orderTransId) {
        this.orderTransId = orderTransId;
    }

    @Basic
    @Column(name = "CashierId", nullable = true)
    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LastUsedEntity that = (LastUsedEntity) o;

        if (id != that.id) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (ticketNumber != null ? !ticketNumber.equals(that.ticketNumber) : that.ticketNumber != null) return false;
        if (redeemTicketId != null ? !redeemTicketId.equals(that.redeemTicketId) : that.redeemTicketId != null) return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;
        if (registerId != null ? !registerId.equals(that.registerId) : that.registerId != null) return false;
        if (orderTransId != null ? !orderTransId.equals(that.orderTransId) : that.orderTransId != null) return false;
        if (cashierId != null ? !cashierId.equals(that.cashierId) : that.cashierId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (ticketNumber != null ? ticketNumber.hashCode() : 0);
        result = 31 * result + (redeemTicketId != null ? redeemTicketId.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (registerId != null ? registerId.hashCode() : 0);
        result = 31 * result + (orderTransId != null ? orderTransId.hashCode() : 0);
        result = 31 * result + (cashierId != null ? cashierId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "RegisterId", referencedColumnName = "RegisterId", insertable = false, updatable = false)
    public RegisterSetupEntity getRegisterByRegisterId() {
        return registerByRegisterId;
    }

    public void setRegisterByRegisterId(RegisterSetupEntity registerByRegisterId) {
        this.registerByRegisterId = registerByRegisterId;
    }
}
