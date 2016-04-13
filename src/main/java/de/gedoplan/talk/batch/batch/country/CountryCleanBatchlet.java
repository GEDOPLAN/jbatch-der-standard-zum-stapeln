package de.gedoplan.talk.batch.batch.country;

import javax.batch.api.AbstractBatchlet;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Named
public class CountryCleanBatchlet extends AbstractBatchlet
{
  @Inject
  EntityManager entityManager;

  @Override
  @Transactional
  public String process()
  {
    this.entityManager.createQuery("update Manufacturer m set m.country=null").executeUpdate();

    this.entityManager.createQuery("delete Country c").executeUpdate();

    return "OK";
  }
}
