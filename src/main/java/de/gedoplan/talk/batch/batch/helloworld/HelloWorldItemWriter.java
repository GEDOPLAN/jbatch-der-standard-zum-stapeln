package de.gedoplan.talk.batch.batch.helloworld;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

@Named
public class HelloWorldItemWriter extends AbstractItemWriter
{

  @Override
  public void open(Serializable checkpoint) throws Exception
  {
  }

  @Override
  public void writeItems(List<Object> items) throws Exception
  {
    System.out.println("write: " + items);
  }

  @Override
  public void close() throws Exception
  {
  }

}
