package de.gedoplan.talk.batch.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {
  @PersistenceContext(unitName = "default")
  private EntityManager entityManager;

  @Produces
  public EntityManager getEntityManager() {
    return this.entityManager;
  }
}
