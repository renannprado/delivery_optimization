package com.walmart.delivery.optimization.beans;

import com.walmart.delivery.optimization.entities.EntityMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author renannp
 */
@Stateless
public class EntityMapFacade extends AbstractFacade<EntityMap> implements EntityMapFacadeLocal {
    @PersistenceContext(unitName = "delivery-optimization_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntityMapFacade() {
        super(EntityMap.class);
    }
    
}
