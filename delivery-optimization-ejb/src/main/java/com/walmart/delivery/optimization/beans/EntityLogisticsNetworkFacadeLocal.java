package com.walmart.delivery.optimization.beans;

import com.walmart.delivery.optimization.entities.EntityLogisticsNetwork;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author renannp
 */
@Local
public interface EntityLogisticsNetworkFacadeLocal {

    void create(EntityLogisticsNetwork entityLogisticsNetwork);

    void edit(EntityLogisticsNetwork entityLogisticsNetwork);

    void remove(EntityLogisticsNetwork entityLogisticsNetwork);

    EntityLogisticsNetwork find(Object id);

    List<EntityLogisticsNetwork> findAll();

    List<EntityLogisticsNetwork> findRange(int[] range);

    int count();
    
}
