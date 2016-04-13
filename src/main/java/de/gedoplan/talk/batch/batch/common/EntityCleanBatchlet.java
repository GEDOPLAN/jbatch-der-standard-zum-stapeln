package de.gedoplan.talk.batch.batch.common;

import javax.batch.api.AbstractBatchlet;
import javax.batch.api.BatchProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Named
public class EntityCleanBatchlet extends AbstractBatchlet
{
  @Inject
  EntityManager entityManager;

  @Inject
  @BatchProperty
  String        entityName;

  @Override
  @Transactional
  public String process()
  {
    this.entityManager.createQuery("delete " + this.entityName + " x").executeUpdate();

    return "OK";
  }
}
