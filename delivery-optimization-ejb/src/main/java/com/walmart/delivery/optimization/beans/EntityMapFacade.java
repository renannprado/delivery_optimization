package com.walmart.delivery.optimization.beans;

import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import com.walmart.delivery.optimization.entities.EntityMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author renannp
 */
@Stateless
public class EntityMapFacade extends AbstractFacade<EntityMap> 
{
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntityMapFacade() {
        super(EntityMap.class);
    }
    
    /**
     * 
     * @param mapName
     * @return a list of <code>EntityLogisticsNetwork</code> given the map name
     */
    public List<EntityLogisticsNetwork> getNetworkByMapName(String mapName)
    {
        // search ins't case sensitive
        mapName = mapName.toUpperCase();
        
        Query getLogisticsByNameQuery = em.createNamedQuery("Map.getLogisticsNetworkByName");
        getLogisticsByNameQuery.setParameter("nameParam", mapName);
        
        return getLogisticsByNameQuery.getResultList();
    }
}
