package com.db.tableclass;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Credit", schema = "dbo", catalog = "Nreg")
public class CreditEntity {
    public long creditId;
    public Integer locationId;
    public Long transactionId;
    public Long webSaleId;
    public LocalDateTime dateUsed;
    public Integer registerId;
    public String name;
    public Integer quantity;
    public Integer ticketTypeId;
    public Integer redeemTicketId;
    public BigDecimal price;
    public BigDecimal discountedPrice;
    public Long ticketNumber;
    public LocalDateTime datePurchased;
    public LocalDateTime dateExpires;
    public Integer playMinutes;
    public Boolean cash;
    public Boolean bonusedCash;
    public Boolean bonus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CreditID")
    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    @Basic
    @Column(name = "LocationId")
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "TransactionID")
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "WebSaleID")
    public Long getWebSaleId() {
        return webSaleId;
    }

    public void setWebSaleId(Long webSaleId) {
        this.webSaleId = webSaleId;
    }

    @Basic
    @Column(name = "DateUsed")
    public LocalDateTime getDateUsed() {
        return dateUsed;
    }

    public void setDateUsed(LocalDateTime dateUsed) {
        this.dateUsed = dateUsed;
    }

    @Basic
    @Column(name = "RegisterId")
    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "TicketTypeId")
    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    @Basic
    @Column(name = "RedeemTicketId")
    public Integer getRedeemTicketId() {
        return redeemTicketId;
    }

    public void setRedeemTicketId(Integer redeemTicketId) {
        this.redeemTicketId = redeemTicketId;
    }

    @Basic
    @Column(name = "Price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "DiscountedPrice")
    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Basic
    @Column(name = "TicketNumber")
    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Basic
    @Column(name = "DatePurchased")
    public LocalDateTime getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDateTime datePurchased) {
        this.datePurchased = datePurchased;
    }

    @Basic
    @Column(name = "DateExpires")
    public LocalDateTime getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(LocalDateTime dateExpires) {
        this.dateExpires = dateExpires;
    }

    @Basic
    @Column(name = "PlayMinutes")
    public Integer getPlayMinutes() {
        return playMinutes;
    }

    public void setPlayMinutes(Integer playMinutes) {
        this.playMinutes = playMinutes;
    }

    @Basic
    @Column(name = "Cash")
    public Boolean getCash() {
        return cash;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }

    @Basic
    @Column(name = "BonusedCash")
    public Boolean getBonusedCash() {
        return bonusedCash;
    }

    public void setBonusedCash(Boolean bonusedCash) {
        this.bonusedCash = bonusedCash;
    }

    @Basic
    @Column(name = "Bonus")
    public Boolean getBonus() {
        return bonus;
    }

    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditEntity that = (CreditEntity) o;

        if (creditId != that.creditId) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (transactionId != null ? !transactionId.equals(that.transactionId) : that.transactionId != null)
            return false;
        if (webSaleId != null ? !webSaleId.equals(that.webSaleId) : that.webSaleId != null) return false;
        if (dateUsed != null ? !dateUsed.equals(that.dateUsed) : that.dateUsed != null) return false;
        if (registerId != null ? !registerId.equals(that.registerId) : that.registerId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (ticketTypeId != null ? !ticketTypeId.equals(that.ticketTypeId) : that.ticketTypeId != null) return false;
        if (redeemTicketId != null ? !redeemTicketId.equals(that.redeemTicketId) : that.redeemTicketId != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (discountedPrice != null ? !discountedPrice.equals(that.discountedPrice) : that.discountedPrice != null)
            return false;
        if (ticketNumber != null ? !ticketNumber.equals(that.ticketNumber) : that.ticketNumber != null) return false;
        if (datePurchased != null ? !datePurchased.equals(that.datePurchased) : that.datePurchased != null)
            return false;
        if (dateExpires != null ? !dateExpires.equals(that.dateExpires) : that.dateExpires != null) return false;
        if (playMinutes != null ? !playMinutes.equals(that.playMinutes) : that.playMinutes != null) return false;
        if (cash != null ? !cash.equals(that.cash) : that.cash != null) return false;
        if (bonusedCash != null ? !bonusedCash.equals(that.bonusedCash) : that.bonusedCash != null) return false;
        if (bonus != null ? !bonus.equals(that.bonus) : that.bonus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (creditId ^ (creditId >>> 32));
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (transactionId != null ? transactionId.hashCode() : 0);
        result = 31 * result + (webSaleId != null ? webSaleId.hashCode() : 0);
        result = 31 * result + (dateUsed != null ? dateUsed.hashCode() : 0);
        result = 31 * result + (registerId != null ? registerId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (ticketTypeId != null ? ticketTypeId.hashCode() : 0);
        result = 31 * result + (redeemTicketId != null ? redeemTicketId.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (discountedPrice != null ? discountedPrice.hashCode() : 0);
        result = 31 * result + (ticketNumber != null ? ticketNumber.hashCode() : 0);
        result = 31 * result + (datePurchased != null ? datePurchased.hashCode() : 0);
        result = 31 * result + (dateExpires != null ? dateExpires.hashCode() : 0);
        result = 31 * result + (playMinutes != null ? playMinutes.hashCode() : 0);
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        result = 31 * result + (bonusedCash != null ? bonusedCash.hashCode() : 0);
        result = 31 * result + (bonus != null ? bonus.hashCode() : 0);
        return result;
    }
}
