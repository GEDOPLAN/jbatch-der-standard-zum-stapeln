package de.gedoplan.talk.batch.batch.common;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;

@Named
public class DummyItemWriter extends AbstractItemWriter
{
  @Inject
  Log log;

  @Override
  public void writeItems(List<Object> items) throws Exception
  {
    this.log.debug("writeItems: " + items);
  }

}
