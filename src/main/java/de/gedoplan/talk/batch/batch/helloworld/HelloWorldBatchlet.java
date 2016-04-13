package de.gedoplan.talk.batch.batch.helloworld;

import javax.batch.api.AbstractBatchlet;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;

@Named
public class HelloWorldBatchlet extends AbstractBatchlet
{
  @Inject
  Log log;

  @Override
  public String process()
  {
    this.log.info("Hello, batch world!");

    return "OK";
  }
}
