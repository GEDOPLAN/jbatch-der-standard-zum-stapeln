package de.gedoplan.talk.batch.presentation;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Properties.class)
public class PropertiesConverter implements Converter
{
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value)
  {
    if (value == null)
    {
      return null;
    }

    try
    {
      Properties properties = new Properties();
      properties.load(new StringReader(value));
      return properties;
    }
    catch (IOException e)
    {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), e.toString()));
    }
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value)
  {
    if (value instanceof Properties)
    {
      Properties properties = (Properties) value;
      return new TreeSet<>(properties.stringPropertyNames())
          .stream()
          .map(k -> k + "=" + properties.getProperty(k))
          .collect(Collectors.joining("\n"));
    }
    return null;
  }

}
