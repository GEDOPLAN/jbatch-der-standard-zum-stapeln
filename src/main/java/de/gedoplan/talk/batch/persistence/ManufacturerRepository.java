package de.gedoplan.talk.batch.persistence;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.talk.batch.entity.Manufacturer;
import de.gedoplan.talk.batch.entity.Manufacturer_;

public class ManufacturerRepository extends SingleIdEntityRepository<Integer, Manufacturer>
{
  public Manufacturer findByName(String name)
  {
    return findSingleByProperty(Manufacturer_.name, name);
  }
}
