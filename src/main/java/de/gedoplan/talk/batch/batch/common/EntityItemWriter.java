package de.gedoplan.talk.batch.batch.common;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;

@Named
public class EntityItemWriter extends AbstractItemWriter
{
  @Inject
  StepContext   stepContext;

  @Inject
  Log           log;

  @Inject
  EntityManager entityManager;

  boolean       updateEnabled;

  @PostConstruct
  void postConstruct()
  {
    this.updateEnabled = "true".equalsIgnoreCase(this.stepContext.getProperties().getProperty("writer.update.enabled"));
  }

  @Override
  public void writeItems(List<Object> items)
  {
    this.log.debug("write: " + items);

    for (Object item : items)
    {
      if (this.updateEnabled)
      {
        this.entityManager.merge(item);
      }
      else
      {
        this.entityManager.persist(item);
      }
    }
  }

}
