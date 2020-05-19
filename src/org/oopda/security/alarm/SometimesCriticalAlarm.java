package org.oopda.security.alarm;

/**
 * An alarm that can vary in criticality. 
 * 
 * A subclass of the abstract class 'Alarm'. 
 * 
 * 
 *
 */
public abstract class SometimesCriticalAlarm extends Alarm 
{

	private static final long serialVersionUID = -7733226324794832595L;
	private final boolean critical;
	
	/**
	 * A basic constructor that makes a super call to the constructor of the 'Alarm' class
	 * and takes a boolean value to denote whether the alarm is critical or not. 
	 * @param location l
	 * @param name n
	 * @param critical c
	 */
	public SometimesCriticalAlarm(String location, String name, boolean critical) 
	{
		super(location, name);
		
		this.critical = critical;
	}

	/**
	 * A getter that returns a boolean value that denotes whether the 
	 * alarm is considered critical or not by the server. 
	 */
	public boolean isCritical() 
	{
		return critical;
	}

}
