package com.walmart.delivery.optimization.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author renannp
 */
@Entity(name = "map")
@Table(name = "tb_map")
public class EntityMap implements Serializable 
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entityMap")
    private Set<EntityLogisticsNetwork> logisticsNetwork;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EntityLogisticsNetwork> getLogisticsNetwork() {
        return logisticsNetwork;
    }

    public void setLogisticsNetwork(Set<EntityLogisticsNetwork> logisticsNetwork) {
        this.logisticsNetwork = logisticsNetwork;
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
        if (!(object instanceof EntityMap)) {
            return false;
        }
        EntityMap other = (EntityMap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.walmart.delivery.optimization.entities.Map[ id=" + id + " ]";
    }
    
    
}