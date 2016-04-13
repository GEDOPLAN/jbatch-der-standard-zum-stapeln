package de.gedoplan.talk.batch.presentation;

import de.gedoplan.baselibs.batch.BatchJob;
import de.gedoplan.baselibs.batch.BatchJobExecution;
import de.gedoplan.baselibs.batch.BatchJobInstance;
import de.gedoplan.talk.batch.service.BatchService;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.batch.runtime.BatchStatus;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class BatchConsolePresenter
{

  @Inject
  BatchService       batchService;

  private String     jobFilter;

  private Properties jobParameters;

  public List<BatchJob> getDefinedJobs()
  {
    List<BatchJob> jobs = this.batchService.getDefinedJobs();
    if (this.jobFilter != null)
    {
      jobs = jobs.stream().filter(j -> j.getName().matches(this.jobFilter)).collect(Collectors.toList());
    }
    return jobs;
  }

  public List<BatchJobInstance> getJobInstances()
  {
    return this.batchService.getJobInstances();
  }

  public List<BatchJobExecution> getJobExecutions()
  {
    return this.batchService.getJobExecutions()
        .stream()
        .filter(je -> !je.getBatchStatus().equals(BatchStatus.ABANDONED))
        .collect(Collectors.toList());
  }

  public String getJobFilter()
  {
    return this.jobFilter;
  }

  public void setJobFilter(String jobFilter)
  {
    this.jobFilter = jobFilter;
  }

  public Properties getJobParameters()
  {
    return this.jobParameters;
  }

  public void setJobParameters(Properties jobProperties)
  {
    this.jobParameters = jobProperties;
  }

}
