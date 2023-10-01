package com.db.tableclass;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "LocationSettings", schema = "dbo", catalog = "Nreg")
public class LocationSettingsEntity implements Cloneable{
    public int locationId;
    public Boolean active;
    public Integer locationNumber;
    public Short locationNet;
    public String webSite;
    public String companyName;
    public String compStreet;
    public String compCityStZip;
    public String state;
    public String compPhone;
    public Integer minimunSeasonPass;
    public Integer maximunSeasonPass;
    public Integer minimumValuePass;
    public Integer maximunValuePass;
    public Integer minimumEmployee;
    public Integer maximunEmployee;
    public Integer minimumFamily;
    public Integer maximunFamily;
    public Integer minimumConsignment;
    public Integer maximunConsignment;
    public Integer minimumWebTicket;
    public Integer maximunWebTicket;
    public Integer minimumVipTicket;
    public Integer maximunVipTicket;
    public Integer minimumOTicket;
    public Integer maximunOTicket;
    public Integer localPrintWidth;
    public Integer kitchenPrintWidth;
    public Integer gameCardSystem;
    public BigDecimal pointValue;
    public BigDecimal ticketValue;
    public BigDecimal taxRate1;
    public Byte taxType1;
    public LocalDateTime effectTaxDate1;
    public BigDecimal taxRate2;
    public Byte taxType2;
    public LocalDateTime effectTaxDate2;
    public BigDecimal taxRate3;
    public Byte taxType3;
    public LocalDateTime effectTaxDate3;
    public BigDecimal taxRate4;
    public Byte taxType4;
    public LocalDateTime effectTaxDate4;
    public String supervisorEmail;
    public String locationMrgEmail;
    public String areaMgrEmail;
    public String revenueEmail;
    public String accountingEmail;
    public String corpEmail;
    public String systemEmail;
    public LocalDateTime dailyReportTime;
    public String timeClockServer;
    public Integer registerPassword;
    public Integer supervisorPassword;
    public String processor;
    public String merchant;
    public String serverPath;
    public Integer webSaleAddresswithIpLookUpRadius;
    public Boolean mustSignForTickets;
    public Boolean noForcePerAuthCCardCheck;
    public String parkLat;
    public String parkLong;
    public Integer whoHasEmbed;
    public String embedKey;
    public String embedSecret;
    public String embedThumb;
    public String embedUrl;
    public String laserTagComPort;
    public BigDecimal tipPercent;
    public BigDecimal changeFund1;
    public BigDecimal changeFund2;
    public Boolean revolution;
    public Boolean changeOrderService;
    public LocalDateTime attractionsTimeplay;
    public Boolean useMagEmpBadges;
    public Boolean hasBadgePrinter;
    public LocalDateTime newSeasonPassStartDate;
    public String theftNotifyPhones;
    public Boolean patronBiometricBypass;
    public String unitermVersion;
    public BigDecimal pointValue2;
    public LocalDateTime effectPointValue2;
    public Integer waterparkCapacity;
    public Integer golfCourses;
    public BigDecimal largeItemValue;
    public String ccServerUser;
    public String ccServerPassword;
    public String ccServerHost;
    public String badgePrintLocations;
    public String modPhone;
    public Integer puttsLocationId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LocationId")
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "Active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "LocationNumber")
    public Integer getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(Integer locationNumber) {
        this.locationNumber = locationNumber;
    }

    @Basic
    @Column(name = "LocationNet")
    public Short getLocationNet() {
        return locationNet;
    }

    public void setLocationNet(Short locationNet) {
        this.locationNet = locationNet;
    }

    @Basic
    @Column(name = "WebSite")
    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Basic
    @Column(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "CompStreet")
    public String getCompStreet() {
        return compStreet;
    }

    public void setCompStreet(String compStreet) {
        this.compStreet = compStreet;
    }

    @Basic
    @Column(name = "CompCityStZip")
    public String getCompCityStZip() {
        return compCityStZip;
    }

    public void setCompCityStZip(String compCityStZip) {
        this.compCityStZip = compCityStZip;
    }

    @Basic
    @Column(name = "State")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "CompPhone")
    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
    }

    @Basic
    @Column(name = "MinimunSeasonPass")
    public Integer getMinimunSeasonPass() {
        return minimunSeasonPass;
    }

    public void setMinimunSeasonPass(Integer minimunSeasonPass) {
        this.minimunSeasonPass = minimunSeasonPass;
    }

    @Basic
    @Column(name = "MaximunSeasonPass")
    public Integer getMaximunSeasonPass() {
        return maximunSeasonPass;
    }

    public void setMaximunSeasonPass(Integer maximunSeasonPass) {
        this.maximunSeasonPass = maximunSeasonPass;
    }

    @Basic
    @Column(name = "MinimumValuePass")
    public Integer getMinimumValuePass() {
        return minimumValuePass;
    }

    public void setMinimumValuePass(Integer minimumValuePass) {
        this.minimumValuePass = minimumValuePass;
    }

    @Basic
    @Column(name = "MaximunValuePass")
    public Integer getMaximunValuePass() {
        return maximunValuePass;
    }

    public void setMaximunValuePass(Integer maximunValuePass) {
        this.maximunValuePass = maximunValuePass;
    }

    @Basic
    @Column(name = "MinimumEmployee")
    public Integer getMinimumEmployee() {
        return minimumEmployee;
    }

    public void setMinimumEmployee(Integer minimumEmployee) {
        this.minimumEmployee = minimumEmployee;
    }

    @Basic
    @Column(name = "MaximunEmployee")
    public Integer getMaximunEmployee() {
        return maximunEmployee;
    }

    public void setMaximunEmployee(Integer maximunEmployee) {
        this.maximunEmployee = maximunEmployee;
    }

    @Basic
    @Column(name = "MinimumFamily")
    public Integer getMinimumFamily() {
        return minimumFamily;
    }

    public void setMinimumFamily(Integer minimumFamily) {
        this.minimumFamily = minimumFamily;
    }

    @Basic
    @Column(name = "MaximunFamily")
    public Integer getMaximunFamily() {
        return maximunFamily;
    }

    public void setMaximunFamily(Integer maximunFamily) {
        this.maximunFamily = maximunFamily;
    }

    @Basic
    @Column(name = "MinimumConsignment")
    public Integer getMinimumConsignment() {
        return minimumConsignment;
    }

    public void setMinimumConsignment(Integer minimumConsignment) {
        this.minimumConsignment = minimumConsignment;
    }

    @Basic
    @Column(name = "MaximunConsignment")
    public Integer getMaximunConsignment() {
        return maximunConsignment;
    }

    public void setMaximunConsignment(Integer maximunConsignment) {
        this.maximunConsignment = maximunConsignment;
    }

    @Basic
    @Column(name = "MinimumWebTicket")
    public Integer getMinimumWebTicket() {
        return minimumWebTicket;
    }

    public void setMinimumWebTicket(Integer minimumWebTicket) {
        this.minimumWebTicket = minimumWebTicket;
    }

    @Basic
    @Column(name = "MaximunWebTicket")
    public Integer getMaximunWebTicket() {
        return maximunWebTicket;
    }

    public void setMaximunWebTicket(Integer maximunWebTicket) {
        this.maximunWebTicket = maximunWebTicket;
    }

    @Basic
    @Column(name = "MinimumVIPTicket")
    public Integer getMinimumVipTicket() {
        return minimumVipTicket;
    }

    public void setMinimumVipTicket(Integer minimumVipTicket) {
        this.minimumVipTicket = minimumVipTicket;
    }

    @Basic
    @Column(name = "MaximunVIPTicket")
    public Integer getMaximunVipTicket() {
        return maximunVipTicket;
    }

    public void setMaximunVipTicket(Integer maximunVipTicket) {
        this.maximunVipTicket = maximunVipTicket;
    }

    @Basic
    @Column(name = "MinimumOTicket")
    public Integer getMinimumOTicket() {
        return minimumOTicket;
    }

    public void setMinimumOTicket(Integer minimumOTicket) {
        this.minimumOTicket = minimumOTicket;
    }

    @Basic
    @Column(name = "MaximunOTicket")
    public Integer getMaximunOTicket() {
        return maximunOTicket;
    }

    public void setMaximunOTicket(Integer maximunOTicket) {
        this.maximunOTicket = maximunOTicket;
    }

    @Basic
    @Column(name = "LocalPrintWidth")
    public Integer getLocalPrintWidth() {
        return localPrintWidth;
    }

    public void setLocalPrintWidth(Integer localPrintWidth) {
        this.localPrintWidth = localPrintWidth;
    }

    @Basic
    @Column(name = "KitchenPrintWidth")
    public Integer getKitchenPrintWidth() {
        return kitchenPrintWidth;
    }

    public void setKitchenPrintWidth(Integer kitchenPrintWidth) {
        this.kitchenPrintWidth = kitchenPrintWidth;
    }

    @Basic
    @Column(name = "GameCardSystem")
    public Integer getGameCardSystem() {
        return gameCardSystem;
    }

    public void setGameCardSystem(Integer gameCardSystem) {
        this.gameCardSystem = gameCardSystem;
    }

    @Basic
    @Column(name = "PointValue")
    public BigDecimal getPointValue() {
        return pointValue;
    }

    public void setPointValue(BigDecimal pointValue) {
        this.pointValue = pointValue;
    }

    @Basic
    @Column(name = "TicketValue")
    public BigDecimal getTicketValue() {
        return ticketValue;
    }

    public void setTicketValue(BigDecimal ticketValue) {
        this.ticketValue = ticketValue;
    }

    @Basic
    @Column(name = "TaxRate1")
    public BigDecimal getTaxRate1() {
        return taxRate1;
    }

    public void setTaxRate1(BigDecimal taxRate1) {
        this.taxRate1 = taxRate1;
    }

    @Basic
    @Column(name = "TaxType1")
    public Byte getTaxType1() {
        return taxType1;
    }

    public void setTaxType1(Byte taxType1) {
        this.taxType1 = taxType1;
    }

    @Basic
    @Column(name = "EffectTaxDate1")
    public LocalDateTime getEffectTaxDate1() {
        return effectTaxDate1;
    }

    public void setEffectTaxDate1(LocalDateTime effectTaxDate1) {
        this.effectTaxDate1 = effectTaxDate1;
    }

    @Basic
    @Column(name = "TaxRate2")
    public BigDecimal getTaxRate2() {
        return taxRate2;
    }

    public void setTaxRate2(BigDecimal taxRate2) {
        this.taxRate2 = taxRate2;
    }

    @Basic
    @Column(name = "TaxType2")
    public Byte getTaxType2() {
        return taxType2;
    }

    public void setTaxType2(Byte taxType2) {
        this.taxType2 = taxType2;
    }

    @Basic
    @Column(name = "EffectTaxDate2")
    public LocalDateTime getEffectTaxDate2() {
        return effectTaxDate2;
    }

    public void setEffectTaxDate2(LocalDateTime effectTaxDate2) {
        this.effectTaxDate2 = effectTaxDate2;
    }

    @Basic
    @Column(name = "TaxRate3")
    public BigDecimal getTaxRate3() {
        return taxRate3;
    }

    public void setTaxRate3(BigDecimal taxRate3) {
        this.taxRate3 = taxRate3;
    }

    @Basic
    @Column(name = "TaxType3")
    public Byte getTaxType3() {
        return taxType3;
    }

    public void setTaxType3(Byte taxType3) {
        this.taxType3 = taxType3;
    }

    @Basic
    @Column(name = "EffectTaxDate3")
    public LocalDateTime getEffectTaxDate3() {
        return effectTaxDate3;
    }

    public void setEffectTaxDate3(LocalDateTime effectTaxDate3) {
        this.effectTaxDate3 = effectTaxDate3;
    }

    @Basic
    @Column(name = "TaxRate4")
    public BigDecimal getTaxRate4() {
        return taxRate4;
    }

    public void setTaxRate4(BigDecimal taxRate4) {
        this.taxRate4 = taxRate4;
    }

    @Basic
    @Column(name = "TaxType4")
    public Byte getTaxType4() {
        return taxType4;
    }

    public void setTaxType4(Byte taxType4) {
        this.taxType4 = taxType4;
    }

    @Basic
    @Column(name = "EffectTaxDate4")
    public LocalDateTime getEffectTaxDate4() {
        return effectTaxDate4;
    }

    public void setEffectTaxDate4(LocalDateTime effectTaxDate4) {
        this.effectTaxDate4 = effectTaxDate4;
    }

    @Basic
    @Column(name = "SupervisorEmail")
    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    @Basic
    @Column(name = "LocationMrgEmail")
    public String getLocationMrgEmail() {
        return locationMrgEmail;
    }

    public void setLocationMrgEmail(String locationMrgEmail) {
        this.locationMrgEmail = locationMrgEmail;
    }

    @Basic
    @Column(name = "AreaMgrEmail")
    public String getAreaMgrEmail() {
        return areaMgrEmail;
    }

    public void setAreaMgrEmail(String areaMgrEmail) {
        this.areaMgrEmail = areaMgrEmail;
    }

    @Basic
    @Column(name = "RevenueEmail")
    public String getRevenueEmail() {
        return revenueEmail;
    }

    public void setRevenueEmail(String revenueEmail) {
        this.revenueEmail = revenueEmail;
    }

    @Basic
    @Column(name = "AccountingEmail")
    public String getAccountingEmail() {
        return accountingEmail;
    }

    public void setAccountingEmail(String accountingEmail) {
        this.accountingEmail = accountingEmail;
    }

    @Basic
    @Column(name = "CorpEmail")
    public String getCorpEmail() {
        return corpEmail;
    }

    public void setCorpEmail(String corpEmail) {
        this.corpEmail = corpEmail;
    }

    @Basic
    @Column(name = "SystemEmail")
    public String getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail) {
        this.systemEmail = systemEmail;
    }

    @Basic
    @Column(name = "DailyReportTime")
    public LocalDateTime getDailyReportTime() {
        return dailyReportTime;
    }

    public void setDailyReportTime(LocalDateTime dailyReportTime) {
        this.dailyReportTime = dailyReportTime;
    }

    @Basic
    @Column(name = "TimeClockServer")
    public String getTimeClockServer() {
        return timeClockServer;
    }

    public void setTimeClockServer(String timeClockServer) {
        this.timeClockServer = timeClockServer;
    }

    @Basic
    @Column(name = "RegisterPassword")
    public Integer getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(Integer registerPassword) {
        this.registerPassword = registerPassword;
    }

    @Basic
    @Column(name = "SupervisorPassword")
    public Integer getSupervisorPassword() {
        return supervisorPassword;
    }

    public void setSupervisorPassword(Integer supervisorPassword) {
        this.supervisorPassword = supervisorPassword;
    }

    @Basic
    @Column(name = "PROCESSOR")
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Basic
    @Column(name = "MERCHANT")
    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    @Basic
    @Column(name = "ServerPath")
    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    @Basic
    @Column(name = "WebSaleAddresswithIPLookUpRadius")
    public Integer getWebSaleAddresswithIpLookUpRadius() {
        return webSaleAddresswithIpLookUpRadius;
    }

    public void setWebSaleAddresswithIpLookUpRadius(Integer webSaleAddresswithIpLookUpRadius) {
        this.webSaleAddresswithIpLookUpRadius = webSaleAddresswithIpLookUpRadius;
    }

    @Basic
    @Column(name = "MustSignForTickets")
    public Boolean getMustSignForTickets() {
        return mustSignForTickets;
    }

    public void setMustSignForTickets(Boolean mustSignForTickets) {
        this.mustSignForTickets = mustSignForTickets;
    }

    @Basic
    @Column(name = "NoForcePerAuthCCardCheck")
    public Boolean getNoForcePerAuthCCardCheck() {
        return noForcePerAuthCCardCheck;
    }

    public void setNoForcePerAuthCCardCheck(Boolean noForcePerAuthCCardCheck) {
        this.noForcePerAuthCCardCheck = noForcePerAuthCCardCheck;
    }

    @Basic
    @Column(name = "ParkLat")
    public String getParkLat() {
        return parkLat;
    }

    public void setParkLat(String parkLat) {
        this.parkLat = parkLat;
    }

    @Basic
    @Column(name = "ParkLong")
    public String getParkLong() {
        return parkLong;
    }

    public void setParkLong(String parkLong) {
        this.parkLong = parkLong;
    }

    @Basic
    @Column(name = "WhoHasEmbed")
    public Integer getWhoHasEmbed() {
        return whoHasEmbed;
    }

    public void setWhoHasEmbed(Integer whoHasEmbed) {
        this.whoHasEmbed = whoHasEmbed;
    }

    @Basic
    @Column(name = "EmbedKey")
    public String getEmbedKey() {
        return embedKey;
    }

    public void setEmbedKey(String embedKey) {
        this.embedKey = embedKey;
    }

    @Basic
    @Column(name = "EmbedSecret")
    public String getEmbedSecret() {
        return embedSecret;
    }

    public void setEmbedSecret(String embedSecret) {
        this.embedSecret = embedSecret;
    }

    @Basic
    @Column(name = "EmbedThumb")
    public String getEmbedThumb() {
        return embedThumb;
    }

    public void setEmbedThumb(String embedThumb) {
        this.embedThumb = embedThumb;
    }

    @Basic
    @Column(name = "EmbedURL")
    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    @Basic
    @Column(name = "LaserTagComPort")
    public String getLaserTagComPort() {
        return laserTagComPort;
    }

    public void setLaserTagComPort(String laserTagComPort) {
        this.laserTagComPort = laserTagComPort;
    }

    @Basic
    @Column(name = "TipPercent")
    public BigDecimal getTipPercent() {
        return tipPercent;
    }

    public void setTipPercent(BigDecimal tipPercent) {
        this.tipPercent = tipPercent;
    }

    @Basic
    @Column(name = "ChangeFund1")
    public BigDecimal getChangeFund1() {
        return changeFund1;
    }

    public void setChangeFund1(BigDecimal changeFund1) {
        this.changeFund1 = changeFund1;
    }

    @Basic
    @Column(name = "ChangeFund2")
    public BigDecimal getChangeFund2() {
        return changeFund2;
    }

    public void setChangeFund2(BigDecimal changeFund2) {
        this.changeFund2 = changeFund2;
    }

    @Basic
    @Column(name = "Revolution")
    public Boolean getRevolution() {
        return revolution;
    }

    public void setRevolution(Boolean revolution) {
        this.revolution = revolution;
    }

    @Basic
    @Column(name = "ChangeOrderService")
    public Boolean getChangeOrderService() {
        return changeOrderService;
    }

    public void setChangeOrderService(Boolean changeOrderService) {
        this.changeOrderService = changeOrderService;
    }

    @Basic
    @Column(name = "AttractionsTimeplay")
    public LocalDateTime getAttractionsTimeplay() {
        return attractionsTimeplay;
    }

    public void setAttractionsTimeplay(LocalDateTime attractionsTimeplay) {
        this.attractionsTimeplay = attractionsTimeplay;
    }

    @Basic
    @Column(name = "UseMagEmpBadges")
    public Boolean getUseMagEmpBadges() {
        return useMagEmpBadges;
    }

    public void setUseMagEmpBadges(Boolean useMagEmpBadges) {
        this.useMagEmpBadges = useMagEmpBadges;
    }

    @Basic
    @Column(name = "HasBadgePrinter")
    public Boolean getHasBadgePrinter() {
        return hasBadgePrinter;
    }

    public void setHasBadgePrinter(Boolean hasBadgePrinter) {
        this.hasBadgePrinter = hasBadgePrinter;
    }

    @Basic
    @Column(name = "NewSeasonPassStartDate")
    public LocalDateTime getNewSeasonPassStartDate() {
        return newSeasonPassStartDate;
    }

    public void setNewSeasonPassStartDate(LocalDateTime newSeasonPassStartDate) {
        this.newSeasonPassStartDate = newSeasonPassStartDate;
    }

    @Basic
    @Column(name = "TheftNotifyPhones")
    public String getTheftNotifyPhones() {
        return theftNotifyPhones;
    }

    public void setTheftNotifyPhones(String theftNotifyPhones) {
        this.theftNotifyPhones = theftNotifyPhones;
    }

    @Basic
    @Column(name = "PatronBiometricBypass")
    public Boolean getPatronBiometricBypass() {
        return patronBiometricBypass;
    }

    public void setPatronBiometricBypass(Boolean patronBiometricBypass) {
        this.patronBiometricBypass = patronBiometricBypass;
    }

    @Basic
    @Column(name = "UnitermVersion")
    public String getUnitermVersion() {
        return unitermVersion;
    }

    public void setUnitermVersion(String unitermVersion) {
        this.unitermVersion = unitermVersion;
    }

    @Basic
    @Column(name = "EffectPointValue2")
    public LocalDateTime getEffectPointValue2() {
        return effectPointValue2;
    }

    public void setEffectPointValue2(LocalDateTime effectPointValue2) {
        this.effectPointValue2 = effectPointValue2;
    }

    @Basic
    @Column(name = "PointValue2")
    public BigDecimal getPointValue2() {
        return pointValue2;
    }

    public void setPointValue2(BigDecimal pointValue2) {
        this.pointValue2 = pointValue2;
    }

    @Basic
    @Column(name = "WaterparkCapacity")
    public Integer getWaterparkCapacity() {
        return waterparkCapacity;
    }

    public void setWaterparkCapacity(Integer waterparkCapacity) {
        this.waterparkCapacity = waterparkCapacity;
    }

    @Basic
    @Column(name = "GolfCourses")
    public Integer getGolfCourses() {
        return golfCourses;
    }

    public void setGolfCourses(Integer golfCourses) {
        this.golfCourses = golfCourses;
    }

    @Basic
    @Column(name = "LargeItemValue")
    public BigDecimal getLargeItemValue() {
        return largeItemValue;
    }

    public void setLargeItemValue(BigDecimal largeItemValue) {
        this.largeItemValue = largeItemValue;
    }

    @Basic
    @Column(name = "CCServerUser", length = 20)
    public String getCCServerUser() {
        return ccServerUser;
    }

    public void setCCServerUser(String ccServerUser) {
        this.ccServerUser = ccServerUser;
    }

    @Basic
    @Column(name = "CCServerHost", length = 20)
    public String getCCServerHost() {
        return ccServerHost;
    }

    public void setCCServerHost(String ccServerHost) {
        this.ccServerHost = ccServerHost;
    }

    @Basic
    @Column(name = "CCServerPassword", length = 15)
    public String getCCServerPassword() {
        return ccServerPassword;
    }

    public void setCCServerPassword(String ccServerPassword) {
        this.ccServerPassword = ccServerPassword;
    }

    @Basic
    @Column(name = "BadgePrintLocations", length = 50)
    public String getBadgePrintLocations() {
        return badgePrintLocations;
    }

    public void setBadgePrintLocations(String badgePrintLocations) {
        this.badgePrintLocations = badgePrintLocations;
    }

    @Basic
    @Column(name = "MODPhone", length = 10)
    public String getModPhone() {
        return modPhone;
    }

    public void setModPhone(String modPhone) {
        this.modPhone = modPhone;
    }

    @Basic
    @Column(name = "PuttsLocationId")
    public Integer getPuttsLocationId() {
        return puttsLocationId;
    }

    public void setPuttsLocationId(Integer puttsLocationId) {
        this.puttsLocationId = puttsLocationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationSettingsEntity that = (LocationSettingsEntity) o;

        if (locationId != that.locationId) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (locationNumber != null ? !locationNumber.equals(that.locationNumber) : that.locationNumber != null)
            return false;
        if (locationNet != null ? !locationNet.equals(that.locationNet) : that.locationNet != null) return false;
        if (webSite != null ? !webSite.equals(that.webSite) : that.webSite != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (compStreet != null ? !compStreet.equals(that.compStreet) : that.compStreet != null) return false;
        if (compCityStZip != null ? !compCityStZip.equals(that.compCityStZip) : that.compCityStZip != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (minimunSeasonPass != null ? !minimunSeasonPass.equals(that.minimunSeasonPass) : that.minimunSeasonPass != null)
            return false;
        if (maximunSeasonPass != null ? !maximunSeasonPass.equals(that.maximunSeasonPass) : that.maximunSeasonPass != null)
            return false;
        if (minimumValuePass != null ? !minimumValuePass.equals(that.minimumValuePass) : that.minimumValuePass != null)
            return false;
        if (maximunValuePass != null ? !maximunValuePass.equals(that.maximunValuePass) : that.maximunValuePass != null)
            return false;
        if (minimumEmployee != null ? !minimumEmployee.equals(that.minimumEmployee) : that.minimumEmployee != null)
            return false;
        if (maximunEmployee != null ? !maximunEmployee.equals(that.maximunEmployee) : that.maximunEmployee != null)
            return false;
        if (minimumFamily != null ? !minimumFamily.equals(that.minimumFamily) : that.minimumFamily != null)
            return false;
        if (maximunFamily != null ? !maximunFamily.equals(that.maximunFamily) : that.maximunFamily != null)
            return false;
        if (minimumConsignment != null ? !minimumConsignment.equals(that.minimumConsignment) : that.minimumConsignment != null)
            return false;
        if (maximunConsignment != null ? !maximunConsignment.equals(that.maximunConsignment) : that.maximunConsignment != null)
            return false;
        if (minimumWebTicket != null ? !minimumWebTicket.equals(that.minimumWebTicket) : that.minimumWebTicket != null)
            return false;
        if (maximunWebTicket != null ? !maximunWebTicket.equals(that.maximunWebTicket) : that.maximunWebTicket != null)
            return false;
        if (minimumVipTicket != null ? !minimumVipTicket.equals(that.minimumVipTicket) : that.minimumVipTicket != null)
            return false;
        if (maximunVipTicket != null ? !maximunVipTicket.equals(that.maximunVipTicket) : that.maximunVipTicket != null)
            return false;
        if (minimumOTicket != null ? !minimumOTicket.equals(that.minimumOTicket) : that.minimumOTicket != null)
            return false;
        if (maximunOTicket != null ? !maximunOTicket.equals(that.maximunOTicket) : that.maximunOTicket != null)
            return false;
        if (localPrintWidth != null ? !localPrintWidth.equals(that.localPrintWidth) : that.localPrintWidth != null)
            return false;
        if (kitchenPrintWidth != null ? !kitchenPrintWidth.equals(that.kitchenPrintWidth) : that.kitchenPrintWidth != null)
            return false;
        if (gameCardSystem != null ? !gameCardSystem.equals(that.gameCardSystem) : that.gameCardSystem != null)
            return false;
        if (pointValue != null ? !pointValue.equals(that.pointValue) : that.pointValue != null) return false;
        if (ticketValue != null ? !ticketValue.equals(that.ticketValue) : that.ticketValue != null) return false;
        if (taxRate1 != null ? !taxRate1.equals(that.taxRate1) : that.taxRate1 != null) return false;
        if (taxType1 != null ? !taxType1.equals(that.taxType1) : that.taxType1 != null) return false;
        if (effectTaxDate1 != null ? !effectTaxDate1.equals(that.effectTaxDate1) : that.effectTaxDate1 != null)
            return false;
        if (taxRate2 != null ? !taxRate2.equals(that.taxRate2) : that.taxRate2 != null) return false;
        if (taxType2 != null ? !taxType2.equals(that.taxType2) : that.taxType2 != null) return false;
        if (effectTaxDate2 != null ? !effectTaxDate2.equals(that.effectTaxDate2) : that.effectTaxDate2 != null)
            return false;
        if (taxRate3 != null ? !taxRate3.equals(that.taxRate3) : that.taxRate3 != null) return false;
        if (taxType3 != null ? !taxType3.equals(that.taxType3) : that.taxType3 != null) return false;
        if (effectTaxDate3 != null ? !effectTaxDate3.equals(that.effectTaxDate3) : that.effectTaxDate3 != null)
            return false;
        if (taxRate4 != null ? !taxRate4.equals(that.taxRate4) : that.taxRate4 != null) return false;
        if (taxType4 != null ? !taxType4.equals(that.taxType4) : that.taxType4 != null) return false;
        if (effectTaxDate4 != null ? !effectTaxDate4.equals(that.effectTaxDate4) : that.effectTaxDate4 != null)
            return false;
        if (supervisorEmail != null ? !supervisorEmail.equals(that.supervisorEmail) : that.supervisorEmail != null)
            return false;
        if (locationMrgEmail != null ? !locationMrgEmail.equals(that.locationMrgEmail) : that.locationMrgEmail != null)
            return false;
        if (areaMgrEmail != null ? !areaMgrEmail.equals(that.areaMgrEmail) : that.areaMgrEmail != null) return false;
        if (revenueEmail != null ? !revenueEmail.equals(that.revenueEmail) : that.revenueEmail != null) return false;
        if (accountingEmail != null ? !accountingEmail.equals(that.accountingEmail) : that.accountingEmail != null)
            return false;
        if (corpEmail != null ? !corpEmail.equals(that.corpEmail) : that.corpEmail != null) return false;
        if (systemEmail != null ? !systemEmail.equals(that.systemEmail) : that.systemEmail != null) return false;
        if (dailyReportTime != null ? !dailyReportTime.equals(that.dailyReportTime) : that.dailyReportTime != null)
            return false;
        if (timeClockServer != null ? !timeClockServer.equals(that.timeClockServer) : that.timeClockServer != null)
            return false;
        if (registerPassword != null ? !registerPassword.equals(that.registerPassword) : that.registerPassword != null)
            return false;
        if (supervisorPassword != null ? !supervisorPassword.equals(that.supervisorPassword) : that.supervisorPassword != null)
            return false;
        if (processor != null ? !processor.equals(that.processor) : that.processor != null) return false;
        if (merchant != null ? !merchant.equals(that.merchant) : that.merchant != null) return false;
        if (serverPath != null ? !serverPath.equals(that.serverPath) : that.serverPath != null) return false;
        if (webSaleAddresswithIpLookUpRadius != null ? !webSaleAddresswithIpLookUpRadius.equals(that.webSaleAddresswithIpLookUpRadius) : that.webSaleAddresswithIpLookUpRadius != null)
            return false;
        if (mustSignForTickets != null ? !mustSignForTickets.equals(that.mustSignForTickets) : that.mustSignForTickets != null)
            return false;
        if (noForcePerAuthCCardCheck != null ? !noForcePerAuthCCardCheck.equals(that.noForcePerAuthCCardCheck) : that.noForcePerAuthCCardCheck != null)
            return false;
        if (parkLat != null ? !parkLat.equals(that.parkLat) : that.parkLat != null) return false;
        if (parkLong != null ? !parkLong.equals(that.parkLong) : that.parkLong != null) return false;
        if (whoHasEmbed != null ? !whoHasEmbed.equals(that.whoHasEmbed) : that.whoHasEmbed != null) return false;
        if (embedKey != null ? !embedKey.equals(that.embedKey) : that.embedKey != null) return false;
        if (embedSecret != null ? !embedSecret.equals(that.embedSecret) : that.embedSecret != null) return false;
        if (embedThumb != null ? !embedThumb.equals(that.embedThumb) : that.embedThumb != null) return false;
        if (embedUrl != null ? !embedUrl.equals(that.embedUrl) : that.embedUrl != null) return false;
        if (laserTagComPort != null ? !laserTagComPort.equals(that.laserTagComPort) : that.laserTagComPort != null)
            return false;
        if (tipPercent != null ? !tipPercent.equals(that.tipPercent) : that.tipPercent != null) return false;
        if (changeFund1 != null ? !changeFund1.equals(that.changeFund1) : that.changeFund1 != null) return false;
        if (changeFund2 != null ? !changeFund2.equals(that.changeFund2) : that.changeFund2 != null) return false;
        if (revolution != null ? !revolution.equals(that.revolution) : that.revolution != null) return false;
        if (changeOrderService != null ? !changeOrderService.equals(that.changeOrderService) : that.changeOrderService != null)
            return false;
        if (attractionsTimeplay != null ? !attractionsTimeplay.equals(that.attractionsTimeplay) : that.attractionsTimeplay != null)
            return false;
        if (useMagEmpBadges != null ? !useMagEmpBadges.equals(that.useMagEmpBadges) : that.useMagEmpBadges != null)
            return false;
        if (hasBadgePrinter != null ? !hasBadgePrinter.equals(that.hasBadgePrinter) : that.hasBadgePrinter != null)
            return false;
        if (newSeasonPassStartDate != null ? !newSeasonPassStartDate.equals(that.newSeasonPassStartDate) : that.newSeasonPassStartDate != null)
            return false;
        if (theftNotifyPhones != null ? !theftNotifyPhones.equals(that.theftNotifyPhones) : that.theftNotifyPhones != null)
            return false;
        if (patronBiometricBypass != null ? !patronBiometricBypass.equals(that.patronBiometricBypass) : that.patronBiometricBypass != null)
            return false;
        if (unitermVersion != null ? !unitermVersion.equals(that.unitermVersion) : that.unitermVersion != null)
            return false;
        if (effectPointValue2 != null ? !effectPointValue2.equals(that.effectPointValue2) : that.effectPointValue2 != null)
            return false;
        if (pointValue2 != null ? !pointValue2.equals(that.pointValue2) : that.pointValue2 != null) return false;
        if (waterparkCapacity != null ? !waterparkCapacity.equals(that.waterparkCapacity) : that.waterparkCapacity != null)
            return false;
        if (golfCourses != null ? !golfCourses.equals(that.golfCourses) : that.golfCourses != null)
            return false;
        if (largeItemValue != null ? !largeItemValue.equals(that.largeItemValue) : that.largeItemValue != null) return false;
        if (ccServerPassword != null ? !ccServerPassword.equals(that.ccServerPassword) : that.ccServerPassword != null)
            return false;
        if (ccServerHost != null ? !ccServerHost.equals(that.ccServerHost) : that.ccServerHost != null)
            return false;
        if (ccServerUser != null ? !ccServerUser.equals(that.ccServerUser) : that.ccServerUser != null)
            return false;
        if (badgePrintLocations != null ? !badgePrintLocations.equals(that.badgePrintLocations) : that.badgePrintLocations != null)
            return false;
        if (modPhone != null ? !modPhone.equals(that.modPhone) : that.modPhone != null)
            return false;
        if (puttsLocationId != null ? !puttsLocationId.equals(that.puttsLocationId) : that.puttsLocationId != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = locationId;
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (locationNumber != null ? locationNumber.hashCode() : 0);
        result = 31 * result + (locationNet != null ? locationNet.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (compStreet != null ? compStreet.hashCode() : 0);
        result = 31 * result + (compCityStZip != null ? compCityStZip.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (minimunSeasonPass != null ? minimunSeasonPass.hashCode() : 0);
        result = 31 * result + (maximunSeasonPass != null ? maximunSeasonPass.hashCode() : 0);
        result = 31 * result + (minimumValuePass != null ? minimumValuePass.hashCode() : 0);
        result = 31 * result + (maximunValuePass != null ? maximunValuePass.hashCode() : 0);
        result = 31 * result + (minimumEmployee != null ? minimumEmployee.hashCode() : 0);
        result = 31 * result + (maximunEmployee != null ? maximunEmployee.hashCode() : 0);
        result = 31 * result + (minimumFamily != null ? minimumFamily.hashCode() : 0);
        result = 31 * result + (maximunFamily != null ? maximunFamily.hashCode() : 0);
        result = 31 * result + (minimumConsignment != null ? minimumConsignment.hashCode() : 0);
        result = 31 * result + (maximunConsignment != null ? maximunConsignment.hashCode() : 0);
        result = 31 * result + (minimumWebTicket != null ? minimumWebTicket.hashCode() : 0);
        result = 31 * result + (maximunWebTicket != null ? maximunWebTicket.hashCode() : 0);
        result = 31 * result + (minimumVipTicket != null ? minimumVipTicket.hashCode() : 0);
        result = 31 * result + (maximunVipTicket != null ? maximunVipTicket.hashCode() : 0);
        result = 31 * result + (minimumOTicket != null ? minimumOTicket.hashCode() : 0);
        result = 31 * result + (maximunOTicket != null ? maximunOTicket.hashCode() : 0);
        result = 31 * result + (localPrintWidth != null ? localPrintWidth.hashCode() : 0);
        result = 31 * result + (kitchenPrintWidth != null ? kitchenPrintWidth.hashCode() : 0);
        result = 31 * result + (gameCardSystem != null ? gameCardSystem.hashCode() : 0);
        result = 31 * result + (pointValue != null ? pointValue.hashCode() : 0);
        result = 31 * result + (ticketValue != null ? ticketValue.hashCode() : 0);
        result = 31 * result + (taxRate1 != null ? taxRate1.hashCode() : 0);
        result = 31 * result + (taxType1 != null ? taxType1.hashCode() : 0);
        result = 31 * result + (effectTaxDate1 != null ? effectTaxDate1.hashCode() : 0);
        result = 31 * result + (taxRate2 != null ? taxRate2.hashCode() : 0);
        result = 31 * result + (taxType2 != null ? taxType2.hashCode() : 0);
        result = 31 * result + (effectTaxDate2 != null ? effectTaxDate2.hashCode() : 0);
        result = 31 * result + (taxRate3 != null ? taxRate3.hashCode() : 0);
        result = 31 * result + (taxType3 != null ? taxType3.hashCode() : 0);
        result = 31 * result + (effectTaxDate3 != null ? effectTaxDate3.hashCode() : 0);
        result = 31 * result + (taxRate4 != null ? taxRate4.hashCode() : 0);
        result = 31 * result + (taxType4 != null ? taxType4.hashCode() : 0);
        result = 31 * result + (effectTaxDate4 != null ? effectTaxDate4.hashCode() : 0);
        result = 31 * result + (supervisorEmail != null ? supervisorEmail.hashCode() : 0);
        result = 31 * result + (locationMrgEmail != null ? locationMrgEmail.hashCode() : 0);
        result = 31 * result + (areaMgrEmail != null ? areaMgrEmail.hashCode() : 0);
        result = 31 * result + (revenueEmail != null ? revenueEmail.hashCode() : 0);
        result = 31 * result + (accountingEmail != null ? accountingEmail.hashCode() : 0);
        result = 31 * result + (corpEmail != null ? corpEmail.hashCode() : 0);
        result = 31 * result + (systemEmail != null ? systemEmail.hashCode() : 0);
        result = 31 * result + (dailyReportTime != null ? dailyReportTime.hashCode() : 0);
        result = 31 * result + (timeClockServer != null ? timeClockServer.hashCode() : 0);
        result = 31 * result + (registerPassword != null ? registerPassword.hashCode() : 0);
        result = 31 * result + (supervisorPassword != null ? supervisorPassword.hashCode() : 0);
        result = 31 * result + (processor != null ? processor.hashCode() : 0);
        result = 31 * result + (merchant != null ? merchant.hashCode() : 0);
        result = 31 * result + (serverPath != null ? serverPath.hashCode() : 0);
        result = 31 * result + (webSaleAddresswithIpLookUpRadius != null ? webSaleAddresswithIpLookUpRadius.hashCode() : 0);
        result = 31 * result + (mustSignForTickets != null ? mustSignForTickets.hashCode() : 0);
        result = 31 * result + (noForcePerAuthCCardCheck != null ? noForcePerAuthCCardCheck.hashCode() : 0);
        result = 31 * result + (parkLat != null ? parkLat.hashCode() : 0);
        result = 31 * result + (parkLong != null ? parkLong.hashCode() : 0);
        result = 31 * result + (whoHasEmbed != null ? whoHasEmbed.hashCode() : 0);
        result = 31 * result + (embedKey != null ? embedKey.hashCode() : 0);
        result = 31 * result + (embedSecret != null ? embedSecret.hashCode() : 0);
        result = 31 * result + (embedThumb != null ? embedThumb.hashCode() : 0);
        result = 31 * result + (embedUrl != null ? embedUrl.hashCode() : 0);
        result = 31 * result + (laserTagComPort != null ? laserTagComPort.hashCode() : 0);
        result = 31 * result + (tipPercent != null ? tipPercent.hashCode() : 0);
        result = 31 * result + (changeFund1 != null ? changeFund1.hashCode() : 0);
        result = 31 * result + (changeFund2 != null ? changeFund2.hashCode() : 0);
        result = 31 * result + (revolution != null ? revolution.hashCode() : 0);
        result = 31 * result + (changeOrderService != null ? changeOrderService.hashCode() : 0);
        result = 31 * result + (attractionsTimeplay != null ? attractionsTimeplay.hashCode() : 0);
        result = 31 * result + (useMagEmpBadges != null ? useMagEmpBadges.hashCode() : 0);
        result = 31 * result + (hasBadgePrinter != null ? hasBadgePrinter.hashCode() : 0);
        result = 31 * result + (newSeasonPassStartDate  != null ? newSeasonPassStartDate.hashCode() : 0);
        result = 31 * result + (theftNotifyPhones != null ? theftNotifyPhones.hashCode() : 0);
        result = 31 * result + (patronBiometricBypass != null ? patronBiometricBypass.hashCode() : 0);
        result = 31 * result + (unitermVersion != null ? unitermVersion.hashCode() : 0);
        result = 31 * result + (effectPointValue2 != null ? effectPointValue2.hashCode() : 0);
        result = 31 * result + (pointValue2 != null ? pointValue2.hashCode() : 0);
        result = 31 * result + (waterparkCapacity != null ? waterparkCapacity.hashCode() : 0);
        result = 31 * result + (waterparkCapacity != null ? waterparkCapacity.hashCode() : 0);
        result = 31 * result + (largeItemValue != null ? largeItemValue.hashCode() : 0);
        result = 31 * result + (ccServerUser != null ? ccServerUser.hashCode() : 0);
        result = 31 * result + (ccServerHost != null ? ccServerHost.hashCode() : 0);
        result = 31 * result + (ccServerPassword != null ? ccServerPassword.hashCode() : 0);
        result = 31 * result + (badgePrintLocations != null ? badgePrintLocations.hashCode() : 0);
        result = 31 * result + (modPhone != null ? modPhone.hashCode() : 0);
        result = 31 * result + (puttsLocationId != null ? puttsLocationId.hashCode() : 0);
        return result;
    }
    @Override
    public LocationSettingsEntity clone(){
        try {
            LocationSettingsEntity newItem = (LocationSettingsEntity) super.clone();
            return newItem;
        }catch(CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
