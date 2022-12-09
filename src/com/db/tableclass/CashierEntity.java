package com.db.tableclass;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cashier", schema = "dbo", catalog = "Nreg")
public class CashierEntity implements Cloneable, Serializable {
    public int cashierId;
    public Integer locationId;
    public Boolean active;
    public Integer recordType;
    public Integer profileId;
    public Integer badgeId;
    public String gameCardBarcode;
    public String gameCardId;
    public Long cashPurseId;
    public String pCode;
    public Integer familyId;
    public String firstName;
    public String lastName;
    public Long securityLevel;
    public Boolean cashier;
    public Boolean partySales;
    public Boolean superCashier;
    public Boolean supervisor;
    public Boolean seniorSupervisor;
    public Boolean useOthersDrawer;
    public Boolean revenue;
    public Boolean manager;
    public Boolean locationManager;
    public Boolean accounting;
    public Boolean system;
    public Boolean superUser;
    public String encryptedPassword;
    public LocalDateTime lastLogin;
    public Integer revolutionId;
    public Boolean sales;
    public Long rfid;
    public Boolean areaManager;
    public Boolean maintTech;

    @Id
    @Column(name = "CashierId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
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
    @Column(name = "Active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "RecordType")
    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    @Basic
    @Column(name = "ProfileID")
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "BadgeID")
    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    @Basic
    @Column(name = "GameCardBarcode")
    public String getGameCardBarcode() {
        return gameCardBarcode;
    }

    public void setGameCardBarcode(String gameCardBarcode) {
        this.gameCardBarcode = gameCardBarcode;
    }

    @Basic
    @Column(name = "GameCardId")
    public String getGameCardId() {
        return gameCardId;
    }

    public void setGameCardId(String gameCardId) {
        this.gameCardId = gameCardId;
    }

    @Basic
    @Column(name = "CashPurseId")
    public Long getCashPurseId() {
        return cashPurseId;
    }

    public void setCashPurseId(Long cashPurseId) {
        this.cashPurseId = cashPurseId;
    }

    @Basic
    @Column(name = "PCode")
    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    @Basic
    @Column(name = "FamilyID")
    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "SecurityLevel")
    public Long getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(Long securityLevel) {
        this.securityLevel = securityLevel;
    }

    @Basic
    @Column(name = "Cashier")
    public Boolean getCashier() {
        return cashier;
    }

    public void setCashier(Boolean cashier) {
        this.cashier = cashier;
    }

    @Basic
    @Column(name = "PartySales")
    public Boolean getPartySales() {
        return partySales;
    }

    public void setPartySales(Boolean partySales) {
        this.partySales = partySales;
    }

    @Basic
    @Column(name = "SuperCashier")
    public Boolean getSuperCashier() {
        return superCashier;
    }

    public void setSuperCashier(Boolean superCashier) {
        this.superCashier = superCashier;
    }

    @Basic
    @Column(name = "Supervisor")
    public Boolean getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Boolean supervisor) {
        this.supervisor = supervisor;
    }

    @Basic
    @Column(name = "SeniorSupervisor")
    public Boolean getSeniorSupervisor() {
        return seniorSupervisor;
    }

    public void setSeniorSupervisor(Boolean seniorSupervisor) {
        this.seniorSupervisor = seniorSupervisor;
    }

    @Basic
    @Column(name = "UseOthersDrawer")
    public Boolean getUseOthersDrawer() {
        return useOthersDrawer;
    }

    public void setUseOthersDrawer(Boolean useOthersDrawer) {
        this.useOthersDrawer = useOthersDrawer;
    }

    @Basic
    @Column(name = "Revenue")
    public Boolean getRevenue() {
        return revenue;
    }

    public void setRevenue(Boolean revenue) {
        this.revenue = revenue;
    }

    @Basic
    @Column(name = "Manager")
    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }

    @Basic
    @Column(name = "LocationManager")
    public Boolean getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(Boolean locationManager) {
        this.locationManager = locationManager;
    }

    @Basic
    @Column(name = "Accounting")
    public Boolean getAccounting() {
        return accounting;
    }

    public void setAccounting(Boolean accounting) {
        this.accounting = accounting;
    }

    @Basic
    @Column(name = "System")
    public Boolean getSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.system = system;
    }

    @Basic
    @Column(name = "SuperUser")
    public Boolean getSuperUser() {
        return superUser;
    }

    public void setSuperUser(Boolean superUser) {
        this.superUser = superUser;
    }

    @Basic
    @Column(name = "EncryptedPassword")
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Basic
    @Column(name = "LastLogin")
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "RevolutionId")
    public Integer getRevolutionId() {
        return revolutionId;
    }

    public void setRevolutionId(Integer revolutionId) {
        this.revolutionId = revolutionId;
    }

    @Basic
    @Column(name = "Sales")
    public Boolean getSales() {
        return sales;
    }

    public void setSales(Boolean sales) {
        this.sales = sales;
    }

    @Basic
    @Column(name = "RFID")
    public Long getRfid() {
        return rfid;
    }

    public void setRfid(Long rfid) {
        this.rfid = rfid;
    }

    @Basic
    @Column(name = "AreaManager")
    public Boolean getAreaManager() {
        return areaManager;
    }

    public void setAreaManager(Boolean areaManager) {
        this.areaManager = areaManager;
    }

    @Basic
    @Column(name = "MaintTech")
    public Boolean getMaintTech() {
        return maintTech;
    }

    public void setMaintTech(Boolean maintTech) {
        this.maintTech = maintTech;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashierEntity that = (CashierEntity) o;

        if (cashierId != that.cashierId) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (recordType != null ? !recordType.equals(that.recordType) : that.recordType != null) return false;
        if (profileId != null ? !profileId.equals(that.profileId) : that.profileId != null) return false;
        if (badgeId != null ? !badgeId.equals(that.badgeId) : that.badgeId != null) return false;
        if (gameCardBarcode != null ? !gameCardBarcode.equals(that.gameCardBarcode) : that.gameCardBarcode != null)
            return false;
        if (gameCardId != null ? !gameCardId.equals(that.gameCardId) : that.gameCardId != null) return false;
        if (cashPurseId != null ? !cashPurseId.equals(that.cashPurseId) : that.cashPurseId != null) return false;
        if (pCode != null ? !pCode.equals(that.pCode) : that.pCode != null) return false;
        if (familyId != null ? !familyId.equals(that.familyId) : that.familyId != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (securityLevel != null ? !securityLevel.equals(that.securityLevel) : that.securityLevel != null)
            return false;
        if (cashier != null ? !cashier.equals(that.cashier) : that.cashier != null) return false;
        if (partySales != null ? !partySales.equals(that.partySales) : that.partySales != null) return false;
        if (superCashier != null ? !superCashier.equals(that.superCashier) : that.superCashier != null) return false;
        if (supervisor != null ? !supervisor.equals(that.supervisor) : that.supervisor != null) return false;
        if (seniorSupervisor != null ? !seniorSupervisor.equals(that.seniorSupervisor) : that.seniorSupervisor != null)
            return false;
        if (useOthersDrawer != null ? !useOthersDrawer.equals(that.useOthersDrawer) : that.useOthersDrawer != null)
            return false;
        if (revenue != null ? !revenue.equals(that.revenue) : that.revenue != null) return false;
        if (manager != null ? !manager.equals(that.manager) : that.manager != null) return false;
        if (locationManager != null ? !locationManager.equals(that.locationManager) : that.locationManager != null)
            return false;
        if (accounting != null ? !accounting.equals(that.accounting) : that.accounting != null) return false;
        if (system != null ? !system.equals(that.system) : that.system != null) return false;
        if (superUser != null ? !superUser.equals(that.superUser) : that.superUser != null) return false;
        if (encryptedPassword != null ? !encryptedPassword.equals(that.encryptedPassword) : that.encryptedPassword != null)
            return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        if (revolutionId != null ? !revolutionId.equals(that.revolutionId) : that.revolutionId != null) return false;
        if (sales != null ? !sales.equals(that.sales) : that.sales != null) return false;
        if (rfid != null ? !rfid.equals(that.rfid) : that.rfid != null) return false;
        if (areaManager != null ? !areaManager.equals(that.areaManager) : that.areaManager != null) return false;
        if (maintTech != null ? !maintTech.equals(that.maintTech) : that.maintTech != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cashierId;
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (recordType != null ? recordType.hashCode() : 0);
        result = 31 * result + (profileId != null ? profileId.hashCode() : 0);
        result = 31 * result + (badgeId != null ? badgeId.hashCode() : 0);
        result = 31 * result + (gameCardBarcode != null ? gameCardBarcode.hashCode() : 0);
        result = 31 * result + (gameCardId != null ? gameCardId.hashCode() : 0);
        result = 31 * result + (cashPurseId != null ? cashPurseId.hashCode() : 0);
        result = 31 * result + (pCode != null ? pCode.hashCode() : 0);
        result = 31 * result + (familyId != null ? familyId.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (securityLevel != null ? securityLevel.hashCode() : 0);
        result = 31 * result + (cashier != null ? cashier.hashCode() : 0);
        result = 31 * result + (partySales != null ? partySales.hashCode() : 0);
        result = 31 * result + (superCashier != null ? superCashier.hashCode() : 0);
        result = 31 * result + (supervisor != null ? supervisor.hashCode() : 0);
        result = 31 * result + (seniorSupervisor != null ? seniorSupervisor.hashCode() : 0);
        result = 31 * result + (useOthersDrawer != null ? useOthersDrawer.hashCode() : 0);
        result = 31 * result + (revenue != null ? revenue.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (locationManager != null ? locationManager.hashCode() : 0);
        result = 31 * result + (accounting != null ? accounting.hashCode() : 0);
        result = 31 * result + (system != null ? system.hashCode() : 0);
        result = 31 * result + (superUser != null ? superUser.hashCode() : 0);
        result = 31 * result + (encryptedPassword != null ? encryptedPassword.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (revolutionId != null ? revolutionId.hashCode() : 0);
        result = 31 * result + (sales != null ? sales.hashCode() : 0);
        result = 31 * result + (rfid != null ? rfid.hashCode() : 0);
        result = 31 * result + (areaManager != null ? areaManager.hashCode() : 0);
        result = 31 * result + (maintTech != null ? maintTech.hashCode() : 0);
        return result;
    }

    @Override
    public CashierEntity clone(){
        try {
            CashierEntity newItem = (CashierEntity) super.clone();
            return newItem;
        }catch(CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
