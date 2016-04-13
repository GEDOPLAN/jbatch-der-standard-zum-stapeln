package de.gedoplan.talk.batch.batch.manufacturer;

import de.gedoplan.baselibs.persistence.entity.SingleIdEntity;
import de.gedoplan.talk.batch.entity.Manufacturer;
import de.gedoplan.talk.batch.persistence.ManufacturerRepository;

import java.util.List;
import java.util.stream.Collectors;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;

@Named
public class ManufacturerItemWriter extends AbstractItemWriter
{
  @Inject
  Log                    log;

  @Inject
  ManufacturerRepository manufacturerRepository;

  @Inject
  EntityManager          entityManager;

  @Override
  public void writeItems(List<Object> items) throws Exception
  {
    this.log.debug("write: " + items.stream().map(x -> x instanceof SingleIdEntity<?> ? ((SingleIdEntity<?>) x).toDebugString() : x.toString()).collect(Collectors.joining(",")));

    for (Object item : items)
    {
      if (!(item instanceof Manufacturer))
      {
        throw new IllegalArgumentException("item must be a Manufacturer");
      }

      Manufacturer manufacturer = (Manufacturer) item;

      Manufacturer oldManufacturer = this.manufacturerRepository.findByName(manufacturer.getName());
      if (oldManufacturer != null)
      {
        oldManufacturer.setCountry(manufacturer.getCountry());
      }
      else
      {
        this.manufacturerRepository.persist(manufacturer);
      }
    }

    // Fail fast!
    this.entityManager.flush();
  }

}
