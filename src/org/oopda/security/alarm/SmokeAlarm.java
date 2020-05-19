package org.oopda.security.alarm;

/**
 * A alarm that is activated based on smoke
 * A sub-class of the abstract class 'Alarm'.
 * 
 * 
 *
 */
public class SmokeAlarm extends Alarm 
{

	private static final long serialVersionUID = -5173678612070336040L;

	/**
	 * Another simple constructor that makes a simple super call to the constructor of it's 
	 * superclass 'Alarm'.
	 * @param location loc
	 * @param name n
	 */
	public SmokeAlarm(String location, String name) 
	{
		super(location, name);
	}

	@Override
	/**
	 * A 'getter' method that returns a boolean variable denoting
	 * whether or not the alarm is considered critical by the server/system. 
	 */
	public boolean isCritical() 
	{
		return false;
	}

	@Override
	/**
	 * Another 'getter' method that returns an AlarmType - in this case, SMOKE. 
	 */
	public AlarmType getType() 
	{
		return AlarmType.SMOKE;
	}

}
