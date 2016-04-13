package de.gedoplan.talk.batch.batch.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;

@Named
public class TextFileItemReader extends AbstractItemReader
{
  private static final String READER_FILE_PATH = "reader.file.path";

  @Inject
  StepContext                 stepContext;

  @Inject
  @BatchProperty
  Integer                     firstItem;

  @Inject
  @BatchProperty
  Integer                     lastItem;

  @Inject
  @BatchProperty
  String                      linePattern;

  @Inject
  Log                         log;

  private BufferedReader      reader;

  private int                 readLineCount;

  @Override
  public void open(Serializable checkpoint) throws IOException
  {
    this.log.debug("checkpoint=" + checkpoint + ", firstItem=" + this.firstItem + ", lastItem=" + this.lastItem + ", linePattern=" + this.linePattern);

    String filePath = this.stepContext.getProperties().getProperty(READER_FILE_PATH);
    if (filePath == null)
    {
      throw new IllegalArgumentException("step property " + READER_FILE_PATH + " must be set");
    }

    this.reader = new BufferedReader(new FileReader(filePath));

    if (checkpoint instanceof Integer)
    {
      this.firstItem = (Integer) checkpoint;
    }

    if (this.firstItem != null)
    {
      this.readLineCount = this.firstItem;
      for (int i = 0; i < this.readLineCount; ++i)
      {
        if (this.reader.readLine() == null)
        {
          break;
        }
      }
    }
    else
    {
      this.readLineCount = 0;
    }

  }

  @Override
  public String readItem() throws IOException
  {
    while (true)
    {
      if (this.lastItem != null && this.readLineCount > this.lastItem)
      {
        return null;
      }

      ++this.readLineCount;

      String line = this.reader.readLine();
      if (line == null || this.linePattern == null || line.matches(this.linePattern))
      {
        this.log.debug("read: " + line);
        return line;
      }
    }
  }

  @Override
  public Serializable checkpointInfo()
  {
    this.log.debug("checkpointInfo: " + this.readLineCount);
    return this.readLineCount;
  }

  @Override
  public void close() throws IOException
  {
    this.reader.close();
  }
}
