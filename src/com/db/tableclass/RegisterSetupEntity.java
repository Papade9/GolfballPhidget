package com.db.tableclass;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RegisterSetup", schema = "dbo", catalog = "Nreg")
public class RegisterSetupEntity implements Cloneable{
    public int registerId;
    public String registerName;
    public String registerShortName;
    public Boolean active;
    public Boolean additionalAuthentication;
    public Boolean rfidReader;
    public Boolean vascularReader;
    public String vascularDeviceMac;
    public Boolean handReader;
    public Boolean fingerReader;
    public Integer registerType;
    public Integer keyGroup;
    public Integer defaultGameCardSalesItem;
    public Boolean autoCompletGameSale;
    public Boolean dontAskForDriversLic;
    public Boolean forceWebSaleLookUp;
    public Boolean forceWebSaleCCardValidate;
    public Boolean useWebSaleAddresswithIpLookUp;
    public Boolean forceAllCCardValidate;
    public Boolean touchScreen;
    public Integer locationId;
    public Long cashPurseMoneyId;
    public Long loginCashpurseId;
    public String ipAddress;
    public String macAddress;
    public Integer registerNumber;
    public Boolean ccMonetra;
    public Boolean ccUniterm;
    public Integer cCardTId;
    public Integer corePacketSequence;
    public Integer loginTimeOut;
    public Integer receiptPrinterType;
    public Integer receiptPrinterId;
    public Boolean printAllReceipts;
    public Integer survey;
    public Boolean allowEmployeeDiscount;
    public Integer discountTime;
    public Integer activityTime;
    public Boolean customerAckSale;
    public Boolean customerScreen;
    public Boolean noSaleEnabled;
    public Boolean allowedChangePrice;
    public Integer receiptCouponId;
    public Integer shutDownTime;// todo using as project number add new feild
    public Boolean useAsKiosk;
    public BigDecimal startingCash;
    public String embedThumb;//40
    public String gameCardTerminal;//20
    public String timeClockDepartments;//20
    public Boolean allowUseAsTimeClock;
    public Integer departmentLocation;
    public Boolean lockerKiosk;
    public Boolean hasGameCardDispenser;
    public Boolean printGamecardPaymentReceipt;
    public String unitermVersion;//25

    @Id
    @Column(name = "RegisterId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    @Basic
    @Column(name = "RegisterName")
    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    @Basic
    @Column(name = "RegisterShortName")
    public String getRegisterShortName() {
        return registerShortName;
    }

    public void setRegisterShortName(String registerShortName) {
        this.registerShortName = registerShortName;
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
    @Column(name = "AdditionalAuthentication")
    public Boolean getAdditionalAuthentication() {
        return additionalAuthentication;
    }

    public void setAdditionalAuthentication(Boolean additionalAuthentication) {
        this.additionalAuthentication = additionalAuthentication;
    }

    @Basic
    @Column(name = "RFIDReader")
    public Boolean getRfidReader() {
        return rfidReader;
    }

    public void setRfidReader(Boolean rfidReader) {
        this.rfidReader = rfidReader;
    }

    @Basic
    @Column(name = "VascularReader")
    public Boolean getVascularReader() {
        return vascularReader;
    }

    public void setVascularReader(Boolean vascularReader) {
        this.vascularReader = vascularReader;
    }

    @Basic
    @Column(name = "VascularDeviceMAC")
    public String getVascularDeviceMac() {
        return vascularDeviceMac;
    }

    public void setVascularDeviceMac(String vascularDeviceMac) {
        this.vascularDeviceMac = vascularDeviceMac;
    }

    @Basic
    @Column(name = "HandReader")
    public Boolean getHandReader() {
        return handReader;
    }

    public void setHandReader(Boolean handReader) {
        this.handReader = handReader;
    }

    @Basic
    @Column(name = "FingerReader")
    public Boolean getFingerReader() {
        return fingerReader;
    }

    public void setFingerReader(Boolean fingerReader) {
        this.fingerReader = fingerReader;
    }

    @Basic
    @Column(name = "RegisterType")
    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    @Basic
    @Column(name = "KeyGroup")
    public Integer getKeyGroup() {
        return keyGroup;
    }

    public void setKeyGroup(Integer keyGroup) {
        this.keyGroup = keyGroup;
    }

    @Basic
    @Column(name = "DefaultGameCardSalesItem")
    public Integer getDefaultGameCardSalesItem() {
        return defaultGameCardSalesItem;
    }

    public void setDefaultGameCardSalesItem(Integer defaultGameCardSalesItem) {
        this.defaultGameCardSalesItem = defaultGameCardSalesItem;
    }

    @Basic
    @Column(name = "AutoCompletGameSale")
    public Boolean getAutoCompletGameSale() {
        return autoCompletGameSale;
    }

    public void setAutoCompletGameSale(Boolean autoCompletGameSale) {
        this.autoCompletGameSale = autoCompletGameSale;
    }

    @Basic
    @Column(name = "DontAskForDriversLic")
    public Boolean getDontAskForDriversLic() {
        return dontAskForDriversLic;
    }

    public void setDontAskForDriversLic(Boolean dontAskForDriversLic) {
        this.dontAskForDriversLic = dontAskForDriversLic;
    }

    @Basic
    @Column(name = "ForceWebSaleLookUp")
    public Boolean getForceWebSaleLookUp() {
        return forceWebSaleLookUp;
    }

    public void setForceWebSaleLookUp(Boolean forceWebSaleLookUp) {
        this.forceWebSaleLookUp = forceWebSaleLookUp;
    }

    @Basic
    @Column(name = "ForceWebSaleCCardValidate")
    public Boolean getForceWebSaleCCardValidate() {
        return forceWebSaleCCardValidate;
    }

    public void setForceWebSaleCCardValidate(Boolean forceWebSaleCCardValidate) {
        this.forceWebSaleCCardValidate = forceWebSaleCCardValidate;
    }

    @Basic
    @Column(name = "UseWebSaleAddresswithIPLookUp")
    public Boolean getUseWebSaleAddresswithIpLookUp() {
        return useWebSaleAddresswithIpLookUp;
    }

    public void setUseWebSaleAddresswithIpLookUp(Boolean useWebSaleAddresswithIpLookUp) {
        this.useWebSaleAddresswithIpLookUp = useWebSaleAddresswithIpLookUp;
    }

    @Basic
    @Column(name = "ForceAllCCardValidate")
    public Boolean getForceAllCCardValidate() {
        return forceAllCCardValidate;
    }

    public void setForceAllCCardValidate(Boolean forceAllCCardValidate) {
        this.forceAllCCardValidate = forceAllCCardValidate;
    }

    @Basic
    @Column(name = "TouchScreen")
    public Boolean getTouchScreen() {
        return touchScreen;
    }

    public void setTouchScreen(Boolean touchScreen) {
        this.touchScreen = touchScreen;
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
    @Column(name = "CashPurseMoneyId")
    public Long getCashPurseMoneyId() {
        return cashPurseMoneyId;
    }

    public void setCashPurseMoneyId(Long cashPurseMoneyId) {
        this.cashPurseMoneyId = cashPurseMoneyId;
    }

    @Basic
    @Column(name = "LoginCashpurseId")
    public Long getLoginCashpurseId() {
        return loginCashpurseId;
    }

    public void setLoginCashpurseId(Long loginCashpurseId) {
        this.loginCashpurseId = loginCashpurseId;
    }

    @Basic
    @Column(name = "IPAddress")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Basic
    @Column(name = "MacAddress")
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Basic
    @Column(name = "RegisterNumber")
    public Integer getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Integer registerNumber) {
        this.registerNumber = registerNumber;
    }

    @Basic
    @Column(name = "CCMonetra")
    public Boolean getCcMonetra() {
        return ccMonetra;
    }

    public void setCcMonetra(Boolean ccMonetra) {
        this.ccMonetra = ccMonetra;
    }

    @Basic
    @Column(name = "CCUniterm")
    public Boolean getCcUniterm() {
        return ccUniterm;
    }

    public void setCcUniterm(Boolean ccUniterm) {
        this.ccUniterm = ccUniterm;
    }

    @Basic
    @Column(name = "CCardTId")
    public Integer getcCardTId() {
        return cCardTId;
    }

    public void setcCardTId(Integer cCardTId) {
        this.cCardTId = cCardTId;
    }

    @Basic
    @Column(name = "CorePacketSequence")
    public Integer getCorePacketSequence() {
        return corePacketSequence;
    }

    public void setCorePacketSequence(Integer corePacketSequence) {
        this.corePacketSequence = corePacketSequence;
    }

    @Basic
    @Column(name = "LoginTimeOut")
    public Integer getLoginTimeOut() {
        return loginTimeOut;
    }

    public void setLoginTimeOut(Integer loginTimeOut) {
        this.loginTimeOut = loginTimeOut;
    }

    @Basic
    @Column(name = "ReceiptPrinterType")
    public Integer getReceiptPrinterType() {
        return receiptPrinterType;
    }

    public void setReceiptPrinterType(Integer receiptPrinterType) {
        this.receiptPrinterType = receiptPrinterType;
    }

    @Basic
    @Column(name = "ReceiptPrinterId")
    public Integer getReceiptPrinterId() {
        return receiptPrinterId;
    }

    public void setReceiptPrinterId(Integer receiptPrinterId) {
        this.receiptPrinterId = receiptPrinterId;
    }

    @Basic
    @Column(name = "PrintAllReceipts")
    public Boolean getPrintAllReceipts() {
        return printAllReceipts;
    }

    public void setPrintAllReceipts(Boolean printAllReceipts) {
        this.printAllReceipts = printAllReceipts;
    }

    @Basic
    @Column(name = "Survey")
    public Integer getSurvey() {
        return survey;
    }

    public void setSurvey(Integer survey) {
        this.survey = survey;
    }

    @Basic
    @Column(name = "AllowEmployeeDiscount")
    public Boolean getAllowEmployeeDiscount() {
        return allowEmployeeDiscount;
    }

    public void setAllowEmployeeDiscount(Boolean allowEmployeeDiscount) {
        this.allowEmployeeDiscount = allowEmployeeDiscount;
    }

    @Basic
    @Column(name = "DiscountTime")
    public Integer getDiscountTime() {
        return discountTime;
    }

    public void setDiscountTime(Integer discountTime) {
        this.discountTime = discountTime;
    }

    @Basic
    @Column(name = "ActivityTime")
    public Integer getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Integer activityTime) {
        this.activityTime = activityTime;
    }

    @Basic
    @Column(name = "CustomerAckSale")
    public Boolean getCustomerAckSale() {
        return customerAckSale;
    }

    public void setCustomerAckSale(Boolean customerAckSale) {
        this.customerAckSale = customerAckSale;
    }

    @Basic
    @Column(name = "CustomerScreen")
    public Boolean getCustomerScreen() {
        return customerScreen;
    }

    public void setCustomerScreen(Boolean customerScreen) {
        this.customerScreen = customerScreen;
    }

    @Basic
    @Column(name = "NoSaleEnabled")
    public Boolean getNoSaleEnabled() {
        return noSaleEnabled;
    }

    public void setNoSaleEnabled(Boolean noSaleEnabled) {
        this.noSaleEnabled = noSaleEnabled;
    }

    @Basic
    @Column(name = "AllowedChangePrice")
    public Boolean getAllowedChangePrice() {
        return allowedChangePrice;
    }

    public void setAllowedChangePrice(Boolean allowedChangePrice) {
        this.allowedChangePrice = allowedChangePrice;
    }

    @Basic
    @Column(name = "ReceiptCouponID")
    public Integer getReceiptCouponId() {
        return receiptCouponId;
    }

    public void setReceiptCouponId(Integer receiptCouponId) {
        this.receiptCouponId = receiptCouponId;
    }

    @Basic
    @Column(name = "ShutDownTime")
    public Integer getShutDownTime() {
        return shutDownTime;
    }

    public void setShutDownTime(Integer shutDownTime) {
        this.shutDownTime = shutDownTime;
    }

    @Basic
    @Column(name = "UseAsKiosk")
    public Boolean getUseAsKiosk() {
        return useAsKiosk;
    }

    public void setUseAsKiosk(Boolean useAsKiosk) {
        this.useAsKiosk = useAsKiosk;
    }

    @Basic
    @Column(name = "StartingCash")
    public BigDecimal getStartingCash() {
        return startingCash;
    }

    public void setStartingCash(BigDecimal startingCash) {
        this.startingCash = startingCash;
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
    @Column(name = "GameCardTerminal")
    public String getGameCardTerminal() {
        return gameCardTerminal;
    }

    public void setGameCardTerminal(String gameCardTerminal) {
        this.gameCardTerminal = gameCardTerminal;
    }

    @Basic
    @Column(name = "TimeClockDepartments")
    public String getTimeClockDepartments() {
        return timeClockDepartments;
    }

    public void setTimeClockDepartments(String timeClockDepartments) {
        this.timeClockDepartments = timeClockDepartments;
    }

    @Basic
    @Column(name = "AllowUseAsTimeClock")
    public Boolean getAllowUseAsTimeClock() {
        return allowUseAsTimeClock;
    }

    public void setAllowUseAsTimeClock(Boolean allowUseAsTimeClock) {
        this.allowUseAsTimeClock = allowUseAsTimeClock;
    }

    @Basic
    @Column(name = "DepartmentLocation")
    public Integer getDepartmentLocation() {
        return departmentLocation;
    }

    public void setDepartmentLocation(Integer departmentLocation) {
        this.departmentLocation = departmentLocation;
    }

    @Basic
    @Column(name = "LockerKiosk")
    public Boolean getLockerKiosk() {
        return lockerKiosk;
    }

    public void setLockerKiosk(Boolean lockerKiosk) {
        this.lockerKiosk = lockerKiosk;
    }

    @Basic
    @Column(name = "HasGameCardDispenser")
    public Boolean getHasGameCardDispenser() {
        return hasGameCardDispenser;
    }

    public void setHasGameCardDispenser(Boolean hasGameCardDispenser) {
        this.hasGameCardDispenser = hasGameCardDispenser;
    }

    @Basic
    @Column(name = "PrintGamecardPaymentReceipts")
    public Boolean getPrintGamecardPaymentReceipt() {
        return printGamecardPaymentReceipt;
    }

    public void setPrintGamecardPaymentReceipt(Boolean printGamecardPaymentReceipt) {
        this.printGamecardPaymentReceipt = printGamecardPaymentReceipt;
    }

    @Basic
    @Column(name = "UnitermVersion")
    public String getUnitermVersion() {
        return unitermVersion;
    }

    public void setUnitermVersion(String unitermVersion) {
        this.unitermVersion = unitermVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterSetupEntity that = (RegisterSetupEntity) o;

        if (registerId != that.registerId) return false;
        if (registerName != null ? !registerName.equals(that.registerName) : that.registerName != null) return false;
        if (registerShortName != null ? !registerShortName.equals(that.registerShortName) : that.registerShortName != null)
            return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (additionalAuthentication != null ? !additionalAuthentication.equals(that.additionalAuthentication) : that.additionalAuthentication != null)
            return false;
        if (rfidReader != null ? !rfidReader.equals(that.rfidReader) : that.rfidReader != null) return false;
        if (vascularReader != null ? !vascularReader.equals(that.vascularReader) : that.vascularReader != null)
            return false;
        if (vascularDeviceMac != null ? !vascularDeviceMac.equals(that.vascularDeviceMac) : that.vascularDeviceMac != null)
            return false;
        if (handReader != null ? !handReader.equals(that.handReader) : that.handReader != null) return false;
        if (fingerReader != null ? !fingerReader.equals(that.fingerReader) : that.fingerReader != null) return false;
        if (registerType != null ? !registerType.equals(that.registerType) : that.registerType != null) return false;
        if (keyGroup != null ? !keyGroup.equals(that.keyGroup) : that.keyGroup != null) return false;
        if (defaultGameCardSalesItem != null ? !defaultGameCardSalesItem.equals(that.defaultGameCardSalesItem) : that.defaultGameCardSalesItem != null)
            return false;
        if (autoCompletGameSale != null ? !autoCompletGameSale.equals(that.autoCompletGameSale) : that.autoCompletGameSale != null)
            return false;
        if (dontAskForDriversLic != null ? !dontAskForDriversLic.equals(that.dontAskForDriversLic) : that.dontAskForDriversLic != null)
            return false;
        if (forceWebSaleLookUp != null ? !forceWebSaleLookUp.equals(that.forceWebSaleLookUp) : that.forceWebSaleLookUp != null)
            return false;
        if (forceWebSaleCCardValidate != null ? !forceWebSaleCCardValidate.equals(that.forceWebSaleCCardValidate) : that.forceWebSaleCCardValidate != null)
            return false;
        if (useWebSaleAddresswithIpLookUp != null ? !useWebSaleAddresswithIpLookUp.equals(that.useWebSaleAddresswithIpLookUp) : that.useWebSaleAddresswithIpLookUp != null)
            return false;
        if (forceAllCCardValidate != null ? !forceAllCCardValidate.equals(that.forceAllCCardValidate) : that.forceAllCCardValidate != null)
            return false;
        if (touchScreen != null ? !touchScreen.equals(that.touchScreen) : that.touchScreen != null) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (cashPurseMoneyId != null ? !cashPurseMoneyId.equals(that.cashPurseMoneyId) : that.cashPurseMoneyId != null)
            return false;
        if (loginCashpurseId != null ? !loginCashpurseId.equals(that.loginCashpurseId) : that.loginCashpurseId != null)
            return false;
        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        if (macAddress != null ? !macAddress.equals(that.macAddress) : that.macAddress != null) return false;
        if (registerNumber != null ? !registerNumber.equals(that.registerNumber) : that.registerNumber != null)
            return false;
        if (ccMonetra != null ? !ccMonetra.equals(that.ccMonetra) : that.ccMonetra != null) return false;
        if (ccUniterm != null ? !ccUniterm.equals(that.ccUniterm) : that.ccUniterm != null) return false;
        if (cCardTId != null ? !cCardTId.equals(that.cCardTId) : that.cCardTId != null) return false;
        if (corePacketSequence != null ? !corePacketSequence.equals(that.corePacketSequence) : that.corePacketSequence != null)
            return false;
        if (loginTimeOut != null ? !loginTimeOut.equals(that.loginTimeOut) : that.loginTimeOut != null) return false;
        if (receiptPrinterType != null ? !receiptPrinterType.equals(that.receiptPrinterType) : that.receiptPrinterType != null)
            return false;
        if (receiptPrinterId != null ? !receiptPrinterId.equals(that.receiptPrinterId) : that.receiptPrinterId != null)
            return false;
        if (printAllReceipts != null ? !printAllReceipts.equals(that.printAllReceipts) : that.printAllReceipts != null)
            return false;
        if (survey != null ? !survey.equals(that.survey) : that.survey != null) return false;
        if (allowEmployeeDiscount != null ? !allowEmployeeDiscount.equals(that.allowEmployeeDiscount) : that.allowEmployeeDiscount != null)
            return false;
        if (discountTime != null ? !discountTime.equals(that.discountTime) : that.discountTime != null) return false;
        if (activityTime != null ? !activityTime.equals(that.activityTime) : that.activityTime != null) return false;
        if (customerAckSale != null ? !customerAckSale.equals(that.customerAckSale) : that.customerAckSale != null)
            return false;
        if (customerScreen != null ? !customerScreen.equals(that.customerScreen) : that.customerScreen != null)
            return false;
        if (noSaleEnabled != null ? !noSaleEnabled.equals(that.noSaleEnabled) : that.noSaleEnabled != null)
            return false;
        if (allowedChangePrice != null ? !allowedChangePrice.equals(that.allowedChangePrice) : that.allowedChangePrice != null)
            return false;
        if (receiptCouponId != null ? !receiptCouponId.equals(that.receiptCouponId) : that.receiptCouponId != null)
            return false;
        if (shutDownTime != null ? !shutDownTime.equals(that.shutDownTime) : that.shutDownTime != null) return false;
        if (useAsKiosk != null ? !useAsKiosk.equals(that.useAsKiosk) : that.useAsKiosk != null) return false;
        if (startingCash != null ? !startingCash.equals(that.startingCash) : that.startingCash != null) return false;
        if (embedThumb != null ? !embedThumb.equals(that.embedThumb) : that.embedThumb != null) return false;
        if (gameCardTerminal != null ? !gameCardTerminal.equals(that.gameCardTerminal) : that.gameCardTerminal != null)
            return false;
        if (timeClockDepartments != null ? !timeClockDepartments.equals(that.timeClockDepartments) : that.timeClockDepartments != null)
            return false;
        if (allowUseAsTimeClock != null ? !allowUseAsTimeClock.equals(that.allowUseAsTimeClock) : that.allowUseAsTimeClock != null)
            return false;
        if (departmentLocation != null ? !departmentLocation.equals(that.departmentLocation) : that.departmentLocation != null)
            return false;
        if (lockerKiosk != null ? !lockerKiosk.equals(that.lockerKiosk) : that.lockerKiosk != null) return false;
        if (hasGameCardDispenser != null ? !hasGameCardDispenser.equals(that.hasGameCardDispenser) : that.hasGameCardDispenser != null) return false;
        if (printGamecardPaymentReceipt != null ? !printGamecardPaymentReceipt.equals(that.printGamecardPaymentReceipt) : that.printGamecardPaymentReceipt != null) return false;
        if (unitermVersion != null ? !unitermVersion.equals(that.unitermVersion) : that.unitermVersion != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = registerId;
        result = 31 * result + (registerName != null ? registerName.hashCode() : 0);
        result = 31 * result + (registerShortName != null ? registerShortName.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (additionalAuthentication != null ? additionalAuthentication.hashCode() : 0);
        result = 31 * result + (rfidReader != null ? rfidReader.hashCode() : 0);
        result = 31 * result + (vascularReader != null ? vascularReader.hashCode() : 0);
        result = 31 * result + (vascularDeviceMac != null ? vascularDeviceMac.hashCode() : 0);
        result = 31 * result + (handReader != null ? handReader.hashCode() : 0);
        result = 31 * result + (fingerReader != null ? fingerReader.hashCode() : 0);
        result = 31 * result + (registerType != null ? registerType.hashCode() : 0);
        result = 31 * result + (keyGroup != null ? keyGroup.hashCode() : 0);
        result = 31 * result + (defaultGameCardSalesItem != null ? defaultGameCardSalesItem.hashCode() : 0);
        result = 31 * result + (autoCompletGameSale != null ? autoCompletGameSale.hashCode() : 0);
        result = 31 * result + (dontAskForDriversLic != null ? dontAskForDriversLic.hashCode() : 0);
        result = 31 * result + (forceWebSaleLookUp != null ? forceWebSaleLookUp.hashCode() : 0);
        result = 31 * result + (forceWebSaleCCardValidate != null ? forceWebSaleCCardValidate.hashCode() : 0);
        result = 31 * result + (useWebSaleAddresswithIpLookUp != null ? useWebSaleAddresswithIpLookUp.hashCode() : 0);
        result = 31 * result + (forceAllCCardValidate != null ? forceAllCCardValidate.hashCode() : 0);
        result = 31 * result + (touchScreen != null ? touchScreen.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (cashPurseMoneyId != null ? cashPurseMoneyId.hashCode() : 0);
        result = 31 * result + (loginCashpurseId != null ? loginCashpurseId.hashCode() : 0);
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (macAddress != null ? macAddress.hashCode() : 0);
        result = 31 * result + (registerNumber != null ? registerNumber.hashCode() : 0);
        result = 31 * result + (ccMonetra != null ? ccMonetra.hashCode() : 0);
        result = 31 * result + (ccUniterm != null ? ccUniterm.hashCode() : 0);
        result = 31 * result + (cCardTId != null ? cCardTId.hashCode() : 0);
        result = 31 * result + (corePacketSequence != null ? corePacketSequence.hashCode() : 0);
        result = 31 * result + (loginTimeOut != null ? loginTimeOut.hashCode() : 0);
        result = 31 * result + (receiptPrinterType != null ? receiptPrinterType.hashCode() : 0);
        result = 31 * result + (receiptPrinterId != null ? receiptPrinterId.hashCode() : 0);
        result = 31 * result + (printAllReceipts != null ? printAllReceipts.hashCode() : 0);
        result = 31 * result + (survey != null ? survey.hashCode() : 0);
        result = 31 * result + (allowEmployeeDiscount != null ? allowEmployeeDiscount.hashCode() : 0);
        result = 31 * result + (discountTime != null ? discountTime.hashCode() : 0);
        result = 31 * result + (activityTime != null ? activityTime.hashCode() : 0);
        result = 31 * result + (customerAckSale != null ? customerAckSale.hashCode() : 0);
        result = 31 * result + (customerScreen != null ? customerScreen.hashCode() : 0);
        result = 31 * result + (noSaleEnabled != null ? noSaleEnabled.hashCode() : 0);
        result = 31 * result + (allowedChangePrice != null ? allowedChangePrice.hashCode() : 0);
        result = 31 * result + (receiptCouponId != null ? receiptCouponId.hashCode() : 0);
        result = 31 * result + (shutDownTime != null ? shutDownTime.hashCode() : 0);
        result = 31 * result + (useAsKiosk != null ? useAsKiosk.hashCode() : 0);
        result = 31 * result + (startingCash != null ? startingCash.hashCode() : 0);
        result = 31 * result + (embedThumb != null ? embedThumb.hashCode() : 0);
        result = 31 * result + (gameCardTerminal != null ? gameCardTerminal.hashCode() : 0);
        result = 31 * result + (timeClockDepartments != null ? timeClockDepartments.hashCode() : 0);
        result = 31 * result + (allowUseAsTimeClock != null ? allowUseAsTimeClock.hashCode() : 0);
        result = 31 * result + (departmentLocation != null ? departmentLocation.hashCode() : 0);
        result = 31 * result + (lockerKiosk != null ? lockerKiosk.hashCode() : 0);
        result = 31 * result + (hasGameCardDispenser != null ? hasGameCardDispenser.hashCode() : 0);
        result = 31 * result + (printGamecardPaymentReceipt != null ? printGamecardPaymentReceipt.hashCode() : 0);
        result = 31 * result + (unitermVersion != null ? unitermVersion.hashCode() : 0);
        return result;
    }

    public RegisterSetupEntity clone(){
        try {
            RegisterSetupEntity newItem = (RegisterSetupEntity) super.clone();
            return newItem;
        }catch(CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
