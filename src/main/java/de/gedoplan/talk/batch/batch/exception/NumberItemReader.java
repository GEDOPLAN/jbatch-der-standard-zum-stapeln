package de.gedoplan.talk.batch.batch.exception;

import java.io.Serializable;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

@Named
public class NumberItemReader extends AbstractItemReader
{
  private int lastItem;

  @Override
  public void open(Serializable checkpoint) throws Exception
  {
    if (checkpoint instanceof Integer)
    {
      this.lastItem = (Integer) checkpoint;
    }
    else
    {
      this.lastItem = 0;
    }
  }

  @Override
  public Serializable checkpointInfo() throws Exception
  {
    return this.lastItem;
  }

  @Override
  public Object readItem() throws Exception
  {
    ++this.lastItem;
    if (this.lastItem > 100)
    {
      return null;
    }

    return this.lastItem;
  }
}
