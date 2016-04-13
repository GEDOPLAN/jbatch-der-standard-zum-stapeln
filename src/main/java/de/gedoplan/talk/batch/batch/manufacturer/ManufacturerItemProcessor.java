package de.gedoplan.talk.batch.batch.manufacturer;

import de.gedoplan.talk.batch.entity.Country;
import de.gedoplan.talk.batch.entity.Manufacturer;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class ManufacturerItemProcessor implements ItemProcessor
{
  @Inject
  EntityManager entityManager;

  @Override
  public Object processItem(Object item) throws Exception
  {
    if (!(item instanceof String))
    {
      throw new IllegalArgumentException("item must be a String: " + item);
    }

    String line = (String) item;
    if (line.startsWith("#"))
    {
      return null;
    }

    String[] attributes = line.split("[\\t,;]");
    int idx = 0;
    String name = attributes[idx++];
    String countryId = attributes[idx++];

    Country country = this.entityManager.getReference(Country.class, countryId);
    return new Manufacturer(name, country);
  }

}
