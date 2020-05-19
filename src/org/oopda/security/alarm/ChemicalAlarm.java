package org.oopda.security.alarm;

/**
 * This alarm type is triggered by a chemical spill
 * 
 * 
 *
 */
public class ChemicalAlarm extends Alarm 
{

	private static final long serialVersionUID = -275559443032862514L;

	
	/**
	 * A basic sub-class constructor that makes a super call to the abstract superclass 'Alarm'
	 * with the location and name of the new instance of ChemicalAlarm.
	 */
	public ChemicalAlarm(String location, String name) 
	{
		super(location, name);
	}

	@Override
	/**
	 * Returns the boolean value to see if the alarm is critical or not.
	 * It is set to true because all chemical spills are dangerous and 
	 * must be considered 'critical'.
	 */
	public boolean isCritical() 
	{
		return true;
	}

	@Override
	public AlarmType getType() 
	{
		return AlarmType.CHEMICAL;
	}
}
