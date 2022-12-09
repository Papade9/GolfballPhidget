package com.db.tableclass;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "TicketType", schema = "dbo", catalog = "Nreg")
public class TicketTypeEntity {
    public int id;
    public int ticketTypeId;
    public String description;
    public Integer locationId;
    public Integer playMinutes;
    public Integer ticketFormId;
    public Boolean consignmentBarcodeType;
    public BigDecimal value;
    public Boolean changeTicketNumber;
    public boolean thermalBand;
    public boolean receiptTicket;
    public boolean embed;
    public boolean otherTktRedeemType;
    public boolean changeTicketPrice;
    public boolean dayPass;
    public boolean seasonPass;
    public boolean sellSeason;
    public boolean seasonUp;
    public boolean consignment;
    public boolean cash;
    public boolean golf;
    public boolean raftThermal;
    public boolean multiThermalBand;
    public boolean laserTagCredit;
    public boolean upSale;
    public boolean dayCoupon;
    public boolean onlyValidToday;
    public Integer notValidUntil;
    public Integer notValidAfter;
    public LocalTime validUntilTime;
    public boolean invertedTicketHeading;
    public LocalDateTime validUntilDate;
    public boolean eventScheduler;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Formula("Id")
    @Column(name = "TicketTypeID")
    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "PlayMinutes")
    public Integer getPlayMinutes() {
        return playMinutes;
    }

    public void setPlayMinutes(Integer playMinutes) {
        this.playMinutes = playMinutes;
    }

    @Basic
    @Column(name = "TicketFormId")
    public Integer getTicketFormId() {
        return ticketFormId;
    }

    public void setTicketFormId(Integer ticketFormId) {
        this.ticketFormId = ticketFormId;
    }

    @Basic
    @Column(name = "ConsignmentBarcodeType")
    public Boolean getConsignmentBarcodeType() {
        return consignmentBarcodeType;
    }

    public void setConsignmentBarcodeType(Boolean consignmentBarcodeType) {
        this.consignmentBarcodeType = consignmentBarcodeType;
    }

    @Basic
    @Column(name = "Value")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Basic
    @Column(name = "ChangeTicketNumber")
    public Boolean getChangeTicketNumber() {
        return changeTicketNumber;
    }

    public void setChangeTicketNumber(Boolean changeTicketNumber) {
        this.changeTicketNumber = changeTicketNumber;
    }

    @Basic
    @Column(name = "ThermalBand")
    public boolean isThermalBand() {
        return thermalBand;
    }

    public void setThermalBand(boolean thermalBand) {
        this.thermalBand = thermalBand;
    }

    @Basic
    @Column(name = "ReceiptTicket")
    public boolean isReceiptTicket() {
        return receiptTicket;
    }

    public void setReceiptTicket(boolean receiptTicket) {
        this.receiptTicket = receiptTicket;
    }

    @Basic
    @Column(name = "Embed")
    public boolean isEmbed() {
        return embed;
    }

    public void setEmbed(boolean embed) {
        this.embed = embed;
    }

    @Basic
    @Column(name = "OtherTktRedeemType")
    public boolean isOtherTktRedeemType() {
        return otherTktRedeemType;
    }

    public void setOtherTktRedeemType(boolean otherTktRedeemType) {
        this.otherTktRedeemType = otherTktRedeemType;
    }

    @Basic
    @Column(name = "ChangeTicketPrice")
    public boolean isChangeTicketPrice() {
        return changeTicketPrice;
    }

    public void setChangeTicketPrice(boolean changeTicketPrice) {
        this.changeTicketPrice = changeTicketPrice;
    }

    @Basic
    @Column(name = "DayPass")
    public boolean isDayPass() {
        return dayPass;
    }

    public void setDayPass(boolean dayPass) {
        this.dayPass = dayPass;
    }

    @Basic
    @Column(name = "SeasonPass")
    public boolean isSeasonPass() {
        return seasonPass;
    }

    public void setSeasonPass(boolean seasonPass) {
        this.seasonPass = seasonPass;
    }

    @Basic
    @Column(name = "SellSeason")
    public boolean isSellSeason() {
        return sellSeason;
    }

    public void setSellSeason(boolean sellSeason) {
        this.sellSeason = sellSeason;
    }

    @Basic
    @Column(name = "SeasonUp")
    public boolean isSeasonUp() {
        return seasonUp;
    }

    public void setSeasonUp(boolean seasonUp) {
        this.seasonUp = seasonUp;
    }

    @Basic
    @Column(name = "Consignment")
    public boolean isConsignment() {
        return consignment;
    }

    public void setConsignment(boolean consignment) {
        this.consignment = consignment;
    }

    @Basic
    @Column(name = "Cash")
    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    @Basic
    @Column(name = "Golf")
    public boolean isGolf() {
        return golf;
    }

    public void setGolf(boolean golf) {
        this.golf = golf;
    }

    @Basic
    @Column(name = "RaftThermal")
    public boolean isRaftThermal() {
        return raftThermal;
    }

    public void setRaftThermal(boolean raftThermal) {
        this.raftThermal = raftThermal;
    }

    @Basic
    @Column(name = "MultiThermalBand")
    public boolean isMultiThermalBand() {
        return multiThermalBand;
    }

    public void setMultiThermalBand(boolean multiThermalBand) {
        this.multiThermalBand = multiThermalBand;
    }

    @Basic
    @Column(name = "LaserTagCredit")
    public boolean isLaserTagCredit() {
        return laserTagCredit;
    }

    public void setLaserTagCredit(boolean laserTagCredit) {
        this.laserTagCredit = laserTagCredit;
    }

    @Basic
    @Column(name = "UpSale")
    public boolean isUpSale() {
        return upSale;
    }

    public void setUpSale(boolean upSale) {
        this.upSale = upSale;
    }

    @Basic
    @Column(name = "DayCoupon")
    public boolean isDayCoupon() {
        return dayCoupon;
    }

    public void setDayCoupon(boolean dayCoupon) {
        this.dayCoupon = dayCoupon;
    }

    @Basic
    @Column(name = "OnlyValidToday")
    public boolean isOnlyValidToday() {
        return onlyValidToday;
    }

    public void setOnlyValidToday(boolean onlyValidToday) {
        this.onlyValidToday = onlyValidToday;
    }

    @Basic
    @Column(name = "NotValidUntil")
    public Integer getNotValidUntil() {
        return notValidUntil;
    }

    public void setNotValidUntil(Integer notValidUntil) {
        this.notValidUntil = notValidUntil;
    }

    @Basic
    @Column(name = "NotValidAfter")
    public Integer getNotValidAfter() {
        return notValidAfter;
    }

    public void setNotValidAfter(Integer notValidAfter) {
        this.notValidAfter = notValidAfter;
    }

    @Basic
    @Column(name = "ValidUntilTime")
    public LocalTime getValidUntilTime() {
        return validUntilTime;
    }

    public void setValidUntilTime(LocalTime validUntilTime) {
        this.validUntilTime = validUntilTime;
    }

    @Basic
    @Column(name = "InvertedTicketHeading")
    public boolean isInvertedTicketHeading() {
        return invertedTicketHeading;
    }

    public void setInvertedTicketHeading(boolean invertedTicketHeading) {
        this.invertedTicketHeading = invertedTicketHeading;
    }

    @Basic
    @Column(name = "ValidUntilDate", nullable = true)
    public LocalDateTime getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(LocalDateTime validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    @Basic
    @Column(name = "EventScheduler")
    public boolean isEventScheduler() {
        return eventScheduler;
    }

    public void setEventScheduler(boolean eventScheduler) {
        this.eventScheduler = eventScheduler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketTypeEntity that = (TicketTypeEntity) o;

        if (id != that.id) return false;
        if (ticketTypeId != that.ticketTypeId) return false;
        if (thermalBand != that.thermalBand) return false;
        if (receiptTicket != that.receiptTicket) return false;
        if (embed != that.embed) return false;
        if (otherTktRedeemType != that.otherTktRedeemType) return false;
        if (changeTicketPrice != that.changeTicketPrice) return false;
        if (dayPass != that.dayPass) return false;
        if (seasonPass != that.seasonPass) return false;
        if (sellSeason != that.sellSeason) return false;
        if (seasonUp != that.seasonUp) return false;
        if (consignment != that.consignment) return false;
        if (cash != that.cash) return false;
        if (golf != that.golf) return false;
        if (raftThermal != that.raftThermal) return false;
        if (multiThermalBand != that.multiThermalBand) return false;
        if (laserTagCredit != that.laserTagCredit) return false;
        if (upSale != that.upSale) return false;
        if (dayCoupon != that.dayCoupon) return false;
        if (onlyValidToday != that.onlyValidToday) return false;
        if (invertedTicketHeading != that.invertedTicketHeading) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (playMinutes != null ? !playMinutes.equals(that.playMinutes) : that.playMinutes != null) return false;
        if (ticketFormId != null ? !ticketFormId.equals(that.ticketFormId) : that.ticketFormId != null) return false;
        if (consignmentBarcodeType != null ? !consignmentBarcodeType.equals(that.consignmentBarcodeType) : that.consignmentBarcodeType != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (changeTicketNumber != null ? !changeTicketNumber.equals(that.changeTicketNumber) : that.changeTicketNumber != null)
            return false;
        if (notValidUntil != null ? !notValidUntil.equals(that.notValidUntil) : that.notValidUntil != null)
            return false;
        if (notValidAfter != null ? !notValidAfter.equals(that.notValidAfter) : that.notValidAfter != null)
            return false;
        if (validUntilTime != null ? !validUntilTime.equals(that.validUntilTime) : that.validUntilTime != null)
            return false;
        if (validUntilDate != null ? !validUntilDate.equals(that.validUntilDate) : that.validUntilDate != null) return false;
        if (eventScheduler != that.eventScheduler) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ticketTypeId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (playMinutes != null ? playMinutes.hashCode() : 0);
        result = 31 * result + (ticketFormId != null ? ticketFormId.hashCode() : 0);
        result = 31 * result + (consignmentBarcodeType != null ? consignmentBarcodeType.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (changeTicketNumber != null ? changeTicketNumber.hashCode() : 0);
        result = 31 * result + (thermalBand ? 1 : 0);
        result = 31 * result + (receiptTicket ? 1 : 0);
        result = 31 * result + (embed ? 1 : 0);
        result = 31 * result + (otherTktRedeemType ? 1 : 0);
        result = 31 * result + (changeTicketPrice ? 1 : 0);
        result = 31 * result + (dayPass ? 1 : 0);
        result = 31 * result + (seasonPass ? 1 : 0);
        result = 31 * result + (sellSeason ? 1 : 0);
        result = 31 * result + (seasonUp ? 1 : 0);
        result = 31 * result + (consignment ? 1 : 0);
        result = 31 * result + (cash ? 1 : 0);
        result = 31 * result + (golf ? 1 : 0);
        result = 31 * result + (raftThermal ? 1 : 0);
        result = 31 * result + (multiThermalBand ? 1 : 0);
        result = 31 * result + (laserTagCredit ? 1 : 0);
        result = 31 * result + (upSale ? 1 : 0);
        result = 31 * result + (dayCoupon ? 1 : 0);
        result = 31 * result + (onlyValidToday ? 1 : 0);
        result = 31 * result + (notValidUntil != null ? notValidUntil.hashCode() : 0);
        result = 31 * result + (notValidAfter != null ? notValidAfter.hashCode() : 0);
        result = 31 * result + (validUntilTime != null ? validUntilTime.hashCode() : 0);
        result = 31 * result + (invertedTicketHeading ? 1 : 0);
        result = 31 * result + (validUntilDate != null ? validUntilDate.hashCode() : 0);
        result = 31 * result + (eventScheduler ? 1 : 0);
        return result;
    }
}
