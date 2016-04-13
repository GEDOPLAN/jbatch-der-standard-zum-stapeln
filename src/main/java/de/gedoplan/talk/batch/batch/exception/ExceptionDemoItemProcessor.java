package de.gedoplan.talk.batch.batch.exception;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;

@Named
public class ExceptionDemoItemProcessor implements ItemProcessor
{
  private int failAt20 = 3;

  @Inject
  Log         log;

  @Override
  public Object processItem(Object item) throws Exception
  {
    if (!(item instanceof Integer))
    {
      return null;
    }

    int number = (Integer) item;

    if ((number % 10) == 3)
    {
      this.log.debug("processItem: " + number + " --> SkippableException");
      throw new SkippableException();
    }

    if (number == 20)
    {
      if (this.failAt20 > 0)
      {
        --this.failAt20;
        this.log.debug("processItem: " + number + " --> RetryableException");
        throw new RetryableException();
      }
    }

    return item;
  }
}
