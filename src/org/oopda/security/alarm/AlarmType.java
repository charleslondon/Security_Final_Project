package org.oopda.security.alarm;

/**
 * The type of Alarm
 * 
 */
public enum AlarmType 
{
	FIRE("Fire"), SMOKE("Smoke"), DOOR("Door"), WINDOW("Window"), CHEMICAL("Chemical");

	private final String text;

	/**
	 * @return the name of the alarm type.
	 */
	public String getText() 
	{
		return text;
	}

	/**
	 * Sets the name of the alarm type.
	 * @param text t
	 */
	AlarmType(String text) 
	{
		this.text = text;
	}

	@Override
	/**
	 * A ToString method for ease of data access.
	 */
	public String toString() 
	{
		return text;
	}
}
