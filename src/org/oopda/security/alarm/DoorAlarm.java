package org.oopda.security.alarm;

/**
 * A specific alarm that activates when a door is opened.
 * 
 */
public class DoorAlarm extends SometimesCriticalAlarm 
{
	private static final long serialVersionUID = 2868876913915133653L;

	/**
	 * Basic constructor to instantiate data.
	 * @param location
	 * @param name
	 * @param critical
	 */
	public DoorAlarm(String location, String name, boolean critical) 
	{
		super(location, name, critical);
	}

	@Override
	/**
	 * Returns the type of alarm - in this case, always door.
	 */
	public AlarmType getType() 
	{
		return AlarmType.DOOR;
	}
}
