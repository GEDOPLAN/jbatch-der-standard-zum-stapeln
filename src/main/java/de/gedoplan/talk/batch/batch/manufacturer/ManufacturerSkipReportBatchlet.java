package de.gedoplan.talk.batch.batch.manufacturer;

import de.gedoplan.talk.batch.entity.Manufacturer;

import java.util.Map;
import java.util.Map.Entry;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class ManufacturerSkipReportBatchlet extends AbstractBatchlet
{
  @Inject
  JobContext    jobContext;

  @Inject
  EntityManager entityManager;

  @Override
  public String process() throws Exception
  {
    @SuppressWarnings("unchecked")
    Map<Manufacturer, Exception> skipMap = (Map<Manufacturer, Exception>) this.jobContext.getTransientUserData();
    if (skipMap != null)
    {
      for (Entry<Manufacturer, Exception> entry : skipMap.entrySet())
      {
        Manufacturer manufacturer = entry.getKey();
        Exception exception = entry.getValue();
        System.out.printf("%s\t%s\n", manufacturer.toDebugString(), exception);
      }
    }

    return BatchStatus.COMPLETED.toString();
  }

}
