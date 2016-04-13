package de.gedoplan.talk.batch.batch.country;

import de.gedoplan.talk.batch.entity.Continent;
import de.gedoplan.talk.batch.entity.Country;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;

@Named
public class CountryItemProcessor implements ItemProcessor
{

  @Inject
  Log log;

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
    if (attributes.length != 6)
    {
      throw new IllegalArgumentException("input lines must consist of 6 fields separated by TAB or ;");
    }

    int idx = 0;
    String isoCode = empty2NullString(attributes[idx++]);
    String name = empty2NullString(attributes[idx++]);
    String capital = empty2NullString(attributes[idx++]);
    String s = empty2NullString(attributes[idx++]);
    Continent continent = (s != null) ? Continent.getValueForCode(s) : null;
    Long population = empty2NullLong(attributes[idx++]);
    Long area = empty2NullLong(attributes[idx++]);

    Country country = new Country(isoCode, name, capital, continent, population, area);
    this.log.debug("processed: " + country.toDebugString());
    return country;
  }

  private String empty2NullString(String string)
  {
    if (string == null)
    {
      return null;
    }
    string = string.trim();
    return string.isEmpty() ? null : string;
  }

  private Long empty2NullLong(String string)
  {
    string = empty2NullString(string);
    if (string == null)
    {
      return null;
    }

    return Long.valueOf(string);
  }

}
