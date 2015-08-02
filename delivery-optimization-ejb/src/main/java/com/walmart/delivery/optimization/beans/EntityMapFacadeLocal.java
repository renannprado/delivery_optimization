package com.walmart.delivery.optimization.beans;

import com.walmart.delivery.optimization.entities.EntityMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author renannp
 */
@Local
public interface EntityMapFacadeLocal {

    void create(EntityMap entityMap);

    void edit(EntityMap entityMap);

    void remove(EntityMap entityMap);

    EntityMap find(Object id);

    List<EntityMap> findAll();

    List<EntityMap> findRange(int[] range);

    int count();
    
}
