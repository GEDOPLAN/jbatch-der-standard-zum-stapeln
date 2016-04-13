package de.gedoplan.talk.batch.entity;

import de.gedoplan.baselibs.persistence.entity.SingleIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Access(AccessType.FIELD)
@Table(name = Country.TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = "NAME") )
public class Country extends SingleIdEntity<String>
{
  public static final String TABLE_NAME = "BATCH_COUNTRY";

  @Id
  @Column(name = "ISO_CODE", length = 2)
  private String             isoCode;

  private String             name;

  private String             capital;

  private Continent          continent;

  private Long               population;

  private Long               area;

  private boolean            expired;

  protected Country()
  {
  }

  public Country(String isoCode, String name, String capital, Continent continent, Long population, Long area)
  {
    this.isoCode = isoCode;
    this.name = name;
    this.capital = capital;
    this.continent = continent;
    this.population = population;
    this.area = area;
  }

  public Country(String isoCode, String name, String capital, Continent continent, Long population, Long area, boolean expired)
  {
    this(isoCode, name, capital, continent, population, area);
    this.expired = expired;
  }

  @Override
  public String getId()
  {
    return this.isoCode;
  }

  public String getIsoCode()
  {
    return this.isoCode;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getCapital()
  {
    return this.capital;
  }

  public void setCapital(String capital)
  {
    this.capital = capital;
  }

  public Continent getContinent()
  {
    return this.continent;
  }

  public void setContinent(Continent continent)
  {
    this.continent = continent;
  }

  public Long getPopulation()
  {
    return this.population;
  }

  public void setPopulation(Long population)
  {
    this.population = population;
  }

  public Long getArea()
  {
    return this.area;
  }

  public void setArea(Long area)
  {
    this.area = area;
  }

  public boolean isExpired()
  {
    return this.expired;
  }

  public void setExpired(boolean expired)
  {
    this.expired = expired;
  }
}
