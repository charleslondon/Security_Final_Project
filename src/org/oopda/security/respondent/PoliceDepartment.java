package org.oopda.security.respondent;

import javax.swing.JOptionPane;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmType;

/**
 * The police department will respond to activated alarms only on fire, door, and window
 * alarms and only if those alarms are considered by the server/system to be 'critical'.
 */
public class PoliceDepartment implements Respondent 
{
	public boolean applicable(Alarm alarm) 
	{
		return (alarm.getType() == AlarmType.FIRE || alarm.getType() == AlarmType.DOOR 
				|| alarm.getType() == AlarmType.WINDOW) && alarm.isCritical();
	}

	/**
	 * A GUI display is created, notifying the user that the police department has been alerted. 
	 */
	public void respond(Alarm alarm) 
	{	
		JOptionPane.showMessageDialog(null, "Alert the police department!");

	}
}
