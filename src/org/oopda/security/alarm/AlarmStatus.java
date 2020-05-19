package org.oopda.security.alarm;

/**
 * The status of an Alarm in enumerated form.
 *
 */
public enum AlarmStatus 
{
	CREATED("Created"), PREPARING("Preparing"), ARMED("Armed"), ACTIVATED("Activated");

	private final String text;

	/**
	 * Sets the alarm status.
	 * @param text
	 */
	AlarmStatus(String text) 
	{
		this.text = text;
	}

	@Override
	public String toString()
	{
		return text;
	}
}
