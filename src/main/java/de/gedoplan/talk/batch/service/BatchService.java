package de.gedoplan.talk.batch.service;

import de.gedoplan.baselibs.batch.BatchJob;
import de.gedoplan.baselibs.batch.BatchJobExecution;
import de.gedoplan.baselibs.batch.BatchJobInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BatchService
{
  public List<BatchJob> getDefinedJobs()
  {
    return BatchJob.getDefinedJobs();
  }

  public List<BatchJobInstance> getJobInstances()
  {
    List<BatchJobInstance> jobInstances = new ArrayList<>();
    for (BatchJob batchJob : getDefinedJobs())
    {
      jobInstances.addAll(batchJob.getInstances());
    }

    Collections.sort(jobInstances, new Comparator<BatchJobInstance>()
    {
      @Override
      public int compare(BatchJobInstance o1, BatchJobInstance o2)
      {
        return Long.compare(o2.getId(), o1.getId());
      }
    });

    return jobInstances;
  }

  public List<BatchJobExecution> getJobExecutions()
  {
    List<BatchJobExecution> jobExecutions = new ArrayList<>();
    for (BatchJobInstance jobInstance : getJobInstances())
    {
      jobExecutions.addAll(jobInstance.getExecutions());
    }
    return jobExecutions;
  }
}
