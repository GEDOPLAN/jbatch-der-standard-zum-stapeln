package de.gedoplan.talk.batch.presentation;

import de.gedoplan.talk.batch.service.BatchService;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.StepExecution;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
@SessionScoped
public class DemoJobPresenter implements Serializable
{
  private JobOperator jobOperator = BatchRuntime.getJobOperator();

  private Long        lastExecutionId;

  @Inject
  BatchService       batchService;

  public List<String> getJobNames()
  {
    return this.batchService
        .getDefinedJobs()
        .stream()
        .map(job -> job.getName())
        .filter(name -> name.startsWith("demo"))
        .collect(Collectors.toList());
  }

  public void startJob(String jobName)
  {
    Properties jobParameters = new Properties();

    switch (jobName)
    {
    case "demo2_showPropertiesBatchlet":
      jobParameters.setProperty("jobParm1", "job parameter value 1");
      jobParameters.setProperty("jobParm2", "job parameter value 2");
      break;
    }

    startJob(jobName, jobParameters);
  }

  private void startJob(String jobName, Properties jobParameters)
  {
    try
    {
      this.lastExecutionId = this.jobOperator.start(jobName, jobParameters);
    }
    catch (Exception e)
    {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), e.toString()));
    }
  }

  public Long getLastExecutionId()
  {
    return this.lastExecutionId;
  }

  public JobExecution getLastJobExecution()
  {
    try
    {
      return this.jobOperator.getJobExecution(this.lastExecutionId);
    }
    catch (Exception e)
    {
      return null;
    }
  }

  public List<StepExecution> getLastStepExecutions()
  {
    try
    {
      return this.jobOperator.getStepExecutions(this.lastExecutionId);
    }
    catch (Exception e)
    {
      return null;
    }

  }

}
