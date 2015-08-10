package com.walmart.delivery.optimization.beans;

import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author renannp
 */
@Stateless
public class EntityLogisticsNetworkFacade extends AbstractFacade<EntityLogisticsNetwork> {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntityLogisticsNetworkFacade() {
        super(EntityLogisticsNetwork.class);
    }
    
}
