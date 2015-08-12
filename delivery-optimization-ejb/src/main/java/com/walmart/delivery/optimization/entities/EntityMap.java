package com.walmart.delivery.optimization.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author renannp
 */
@NamedQueries({
	@NamedQuery(
            name = "Map.getLogisticsNetworkByName",
            query = "SELECT net FROM Map AS m INNER JOIN m.logisticsNetwork as net WHERE m.name LIKE :nameParam"
	)
})
@Entity(name = "Map")
@Table(name = "tb_map")
public class EntityMap implements Serializable 
{
    private static final long serialVersionUID = 1L;

    public EntityMap() 
    {
        /* hibernate needs a constructor with no parameters */
    }
    
    public EntityMap(String name) {
        this(name, null);
    }
    
    public EntityMap(String name, List<EntityLogisticsNetwork> logisticsNetwork) {
        this.setName(name);
        this.logisticsNetwork = logisticsNetwork;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore // this is used to prevent the webservice caller to be able to set the id, which would cause exceptions/errros
    private Long id;
    
    @Column(name = "name", unique = true, nullable = false)
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entityMap", cascade = CascadeType.ALL)
    private List<EntityLogisticsNetwork> logisticsNetwork;
    
    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore(true) // the id must be ignored in the request
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name.toUpperCase();
    }

    public List<EntityLogisticsNetwork> getLogisticsNetwork() {
        return logisticsNetwork;
    }

    public void setLogisticsNetwork(List<EntityLogisticsNetwork> logisticsNetwork) {
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
        return "EntityMap{" + "id=" + id + ", name=" + name + ", logisticsNetwork=" + logisticsNetwork + '}';
    }    
}