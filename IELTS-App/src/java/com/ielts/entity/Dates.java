/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ielts.entity;

import com.ielts.util.Utils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author awage
 */
@Entity
@Table(name = "dates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dates.findAll", query = "SELECT d FROM Dates d")
    , @NamedQuery(name = "Dates.findById", query = "SELECT d FROM Dates d WHERE d.id = :id")
    , @NamedQuery(name = "Dates.findByIdate", query = "SELECT d FROM Dates d WHERE d.idate = :idate")})
public class Dates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IDATE")
    private String idate;

    @Transient
    private Date ieltsDate;

    public Dates() {
    }

    public Dates(Integer id) {
        this.id = id;
    }

    public Dates(Integer id, String idate) {
        this.id = id;
        this.idate = idate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dates)) {
            return false;
        }
        Dates other = (Dates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ielts.entity.Dates[ id=" + id + " ]";
    }

    @Override
    public Dates clone() throws CloneNotSupportedException {
        return (Dates) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getIeltsDate() {
        try {
            if (idate != null) {
                ieltsDate = Utils.SDF.parse(idate);
            }
        } catch (ParseException e) {
        }
        return ieltsDate;
    }

    public void setIeltsDate(Date ieltsDate) {
        if (ieltsDate != null) {
            idate = Utils.SDF.format(ieltsDate);
        }
        this.ieltsDate = ieltsDate;
    }

}
