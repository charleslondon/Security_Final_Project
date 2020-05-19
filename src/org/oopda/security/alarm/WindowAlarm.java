package org.oopda.security.alarm;

/**
 * An alarm that activates when a window is opened
 * It utilizes serialization to communicate with the server. 
 * 
 * 
 *
 */
public class WindowAlarm extends SometimesCriticalAlarm 
{

	private static final long serialVersionUID = 3371777983128863835L;

	public WindowAlarm(String location, String name, boolean critical) 
	{
		super(location, name, critical);
	}

	@Override
	public AlarmType getType()
	{
		return AlarmType.WINDOW;
	}

}
