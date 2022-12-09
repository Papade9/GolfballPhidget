package com.db.tableclass;

import javax.persistence.*;

/**
 * Created by tcarlston on 4/26/2017.
 */
@Entity
@Table(name = "PrintPath", schema = "dbo", catalog = "Nreg")
public class PrintPathEntity {
    public int id;
    public Integer locationId;
    public String description;
    public Integer printerType;
    public Integer printerConfig;
    public Boolean printToFile;
    public String path;
    public Boolean printJobHeader;
    public String filepath;
    public Boolean local;


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
    @Column(name = "Description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PrinterType", nullable = true)
    public Integer getPrinterType() {
        return printerType;
    }

    public void setPrinterType(Integer printerType) {
        this.printerType = printerType;
    }

    @Basic
    @Column(name = "PrinterConfig", nullable = true)
    public Integer getPrinterConfig() {
        return printerConfig;
    }

    public void setPrinterConfig(Integer printerConfig) {
        this.printerConfig = printerConfig;
    }

    @Basic
    @Column(name = "PrintToFile", nullable = true)
    public Boolean getPrintToFile() {
        return printToFile;
    }

    public void setPrintToFile(Boolean printToFile) {
        this.printToFile = printToFile;
    }

    @Basic
    @Column(name = "Path", nullable = true, length = 100)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "PrintJobHeader", nullable = true)
    public Boolean getPrintJobHeader() {
        return printJobHeader;
    }

    public void setPrintJobHeader(Boolean printJobHeader) {
        this.printJobHeader = printJobHeader;
    }

    @Basic
    @Column(name = "Filepath", nullable = true, length = 100)
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Basic
    @Column(name = "Local", nullable = true)
    public Boolean getLocal() {
        return local;
    }

    public void setLocal(Boolean local) {
        this.local = local;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrintPathEntity that = (PrintPathEntity) o;

        if (id != that.id) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (printerType != null ? !printerType.equals(that.printerType) : that.printerType != null) return false;
        if (printerConfig != null ? !printerConfig.equals(that.printerConfig) : that.printerConfig != null)
            return false;
        if (printToFile != null ? !printToFile.equals(that.printToFile) : that.printToFile != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (printJobHeader != null ? !printJobHeader.equals(that.printJobHeader) : that.printJobHeader != null) return false;
        if (filepath != null ? !filepath.equals(that.filepath) : that.filepath != null) return false;
        if (local != null ? !local.equals(that.local) : that.local != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (printerType != null ? printerType.hashCode() : 0);
        result = 31 * result + (printerConfig != null ? printerConfig.hashCode() : 0);
        result = 31 * result + (printToFile != null ? printToFile.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (printJobHeader != null ? printJobHeader.hashCode() : 0);
        result = 31 * result + (filepath != null ? filepath.hashCode() : 0);
        result = 31 * result + (local != null ? local.hashCode() : 0);
        return result;
    }
}
