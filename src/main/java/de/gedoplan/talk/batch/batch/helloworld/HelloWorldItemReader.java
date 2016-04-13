package de.gedoplan.talk.batch.batch.helloworld;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

@Named
public class HelloWorldItemReader extends AbstractItemReader
{

  private static final String[] words = { "hello", "batch", "world", "this", "is", "a", "chunk", "demo" };
  private Iterator<String>      wordIter;

  @Override
  public void open(Serializable checkpoint) throws Exception
  {
    this.wordIter = Arrays.asList(words).iterator();
  }

  @Override
  public Object readItem() throws Exception
  {
    return this.wordIter.hasNext() ? this.wordIter.next() : null;
  }

  @Override
  public void close() throws Exception
  {
  }

}
