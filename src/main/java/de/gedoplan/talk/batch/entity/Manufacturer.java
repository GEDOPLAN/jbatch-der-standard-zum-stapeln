package de.gedoplan.talk.batch.entity;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = Manufacturer.TABLE_NAME)
public class Manufacturer extends GeneratedIntegerIdEntity
{
  public static final String TABLE_NAME = "BATCH_MANUFACTURER";

  @Column(unique = true)
  private String             name;

  @ManyToOne
  private Country            country;

  public Manufacturer(String name, Country country)
  {
    this.name = name;
    this.country = country;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Country getCountry()
  {
    return this.country;
  }

  public void setCountry(Country country)
  {
    this.country = country;
  }

  protected Manufacturer()
  {
  }
}
