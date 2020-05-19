package org.oopda.security.alarm;

/**
 * An alarm that activates during high heat
 * 
 * 
 *
 */
public class FireAlarm extends Alarm 
{

	private static final long serialVersionUID = 8359307803383005650L;

	public FireAlarm(String location, String name) 
	{
		super(location, name);
	}

	@Override
	/**
	 * A getter that returns a boolean variable that denotes whether or not the server/system
	 * considers this alarm to be critical.
	 */
	public boolean isCritical() 
	{
		return true;
	}

	@Override
	/**
	 * Returns an AlarmType in the form of a simple 'getter' method - in this case, the return will
	 * always be 'FIRE'.
	 */
	public AlarmType getType() 
	{
		return AlarmType.FIRE;
	}
}
