package de.gedoplan.talk.batch.batch.country;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;

@Named
public class CountryItemReader extends AbstractItemReader
{

  @Inject
  Log                    log;

  private BufferedReader reader;

  @Override
  public void open(Serializable checkpoint) throws IOException
  {
    this.reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("country.csv")));
  }

  @Override
  public String readItem() throws IOException
  {
    String line = this.reader.readLine();
    this.log.debug("read: " + line);
    return line;
  }

  @Override
  public void close() throws IOException
  {
    this.reader.close();
  }
}
