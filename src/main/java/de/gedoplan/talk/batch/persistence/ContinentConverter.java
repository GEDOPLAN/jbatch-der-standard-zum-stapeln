package de.gedoplan.talk.batch.persistence;

import de.gedoplan.talk.batch.entity.Continent;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ContinentConverter implements AttributeConverter<Continent, String>
{
  @Override
  public String convertToDatabaseColumn(Continent attribute)
  {
    return attribute.getCode();
  }

  @Override
  public Continent convertToEntityAttribute(String dbData)
  {
    return Continent.getValueForCode(dbData);
  }

}
