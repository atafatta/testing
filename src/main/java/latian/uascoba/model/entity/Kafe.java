/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latian.uascoba.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author STRIX
 */
@Entity
@Table(name = "kafe")
@NamedQueries({
    @NamedQuery(name = "Kafe.findAll", query = "SELECT k FROM Kafe k"),
    @NamedQuery(name = "Kafe.findById", query = "SELECT k FROM Kafe k WHERE k.id = :id"),
    @NamedQuery(name = "Kafe.findByNamakafe", query = "SELECT k FROM Kafe k WHERE k.namakafe = :namakafe")})
public class Kafe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "namakafe")
    private String namakafe;
    @Lob
    @Column(name = "gambar")
    private byte[] gambar;

    public Kafe() {
    }

    public Kafe(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamakafe() {
        return namakafe;
    }

    public void setNamakafe(String namakafe) {
        this.namakafe = namakafe;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
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
        if (!(object instanceof Kafe)) {
            return false;
        }
        Kafe other = (Kafe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "latian.uascoba.model.entity.Kafe[ id=" + id + " ]";
    }
    
}
