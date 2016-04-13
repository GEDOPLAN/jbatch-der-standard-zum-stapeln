package de.gedoplan.talk.batch.batch.helloworld;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class HelloWorldItemProcessor implements ItemProcessor
{

  @Override
  public Object processItem(Object item) throws Exception
  {
    String word = (String) item;
    return word.toUpperCase();
  }

}
