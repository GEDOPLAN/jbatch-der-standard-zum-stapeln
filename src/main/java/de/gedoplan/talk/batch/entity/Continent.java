package de.gedoplan.talk.batch.entity;

public enum Continent
{
  AFRICA("AF"), ANTARCTICA("AN"), ASIA("AS"), OCEANIA("OC"), EUROPE("EU"), NORTH_AMERICA("NA"), SOUTH_AMERICA("SA");

  private String code;

  private Continent(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return this.code;
  }

  public static Continent getValueForCode(String code)
  {
    for (Continent continent : values())
    {
      if (continent.code.equals(code))
      {
        return continent;
      }
    }

    throw new IllegalArgumentException("Illegal continent code: " + code);
  }
}
