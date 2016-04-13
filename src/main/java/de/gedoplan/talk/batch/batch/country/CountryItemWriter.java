package de.gedoplan.talk.batch.batch.country;

import de.gedoplan.talk.batch.entity.Country;
import de.gedoplan.talk.batch.persistence.CountryRepository;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;

@Named
public class CountryItemWriter extends AbstractItemWriter
{

  @Inject
  Log               log;

  @Inject
  CountryRepository countryRepository;

  @Override
  public void writeItems(List<Object> items)
  {
    this.log.debug("write: " + items);

    for (Object item : items)
    {
      if (!(item instanceof Country))
      {
        throw new IllegalArgumentException("item must be of type Country: " + item);
      }

      this.countryRepository.persist((Country) item);
    }
  }

}
