package de.gedoplan.talk.batch.batch.manufacturer;

import de.gedoplan.talk.batch.entity.Manufacturer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.batch.api.chunk.listener.SkipWriteListener;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ManufacturerSkipListener implements SkipWriteListener
{
  @Inject
  JobContext jobContext;

  @Override
  public void onSkipWriteItem(List<Object> items, Exception ex) throws Exception
  {
    @SuppressWarnings("unchecked")
    Map<Manufacturer, Exception> skipMap = (Map<Manufacturer, Exception>) this.jobContext.getTransientUserData();
    if (skipMap == null)
    {
      skipMap = new HashMap<>();
      this.jobContext.setTransientUserData(skipMap);
    }

    for (Object item : items)
    {
      skipMap.put((Manufacturer) item, ex);
    }
  }
}
