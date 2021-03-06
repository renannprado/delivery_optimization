package com.walmart.delivery.optimization.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author renannp
 */
@Entity(name = "LogisticsNetwork")
@Table(name = "tb_logistics_network", uniqueConstraints = @UniqueConstraint(columnNames = {"source_name", "destiny_name", "map_id"}))
public class EntityLogisticsNetwork implements Serializable 
{
    private static final long serialVersionUID = 2L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore // this is used to prevent the webservice caller to be able to set the id, which would cause exceptions/errros
    private Long id;

    @Column(name = "source_name", nullable = false)
    @NotNull
    private String sourceName;
    
    @Column(name = "destiny_name", nullable = false)
    @NotNull
    private String destinyName;

    @Column(name = "distance", nullable = false)
    @NotNull
    private Integer distance;
    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(nullable = false, name = "map_id", referencedColumnName = "id")
    @JsonIgnore
    private EntityMap entityMap; 

    public EntityLogisticsNetwork() 
    {
        /* hibernate needs a constructor with no parameters */
    }
    
    public EntityLogisticsNetwork(String sourceName, String destinyName, Integer distance, EntityMap entityMap) {
        this.setSourceName(sourceName);
        this.setDestinyName(destinyName);
        this.distance = distance;
        this.entityMap = entityMap;
    }
    
    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore(true) // the id must be ignored in the request
    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public final void setSourceName(String sourceName) {
        this.sourceName = sourceName.toUpperCase();
    }

    public String getDestinyName() {
        return destinyName;
    }

    public final void setDestinyName(String destinyName) {
        this.destinyName = destinyName.toUpperCase();
    }    

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @JsonProperty(value = "map")
    @JsonIgnoreProperties(value = {"name", "logisticsNetwork"}) // this will prevent the recursion, which would cause a stackoverflow, sending over just the ID is ok
    public EntityMap getEntityMap() {
        return entityMap;
    }

    @JsonIgnore(true)
    public void setEntityMap(EntityMap entityMap) {
        this.entityMap = entityMap;
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
        if (!(object instanceof EntityLogisticsNetwork)) {
            return false;
        }
        EntityLogisticsNetwork other = (EntityLogisticsNetwork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityLogisticsNetwork{" + "id=" + id + ", sourceName=" + sourceName + ", destinyName=" + destinyName + ", distance=" + distance + '}';
    }
}
