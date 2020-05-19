package org.oopda.security.respondent;

import javax.swing.JOptionPane;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmType;

/**
 * The Fire department. They are 'alerted' if either a 'FIRE' or 'SMOKE' alarm is tripped and the alarm is 
 * considered to be critical. Due to the dangerous nature of these occurrences, all FIRE alarms are to be considered
 * automatically critical. 
 * 
 * 
 *
 */
public class FireDepartment implements Respondent 
{

	public boolean applicable(Alarm alarm) 
	{
		return (alarm.getType() == AlarmType.FIRE || alarm.getType() == AlarmType.SMOKE) && alarm.isCritical();
	}

	/**
	 *A GUI display is created, notifying the user that the fire department has been alerted. 
	 */
	public void respond(Alarm alarm)
	{
		JOptionPane.showMessageDialog(null, "Alert the fire department!");
	}

}
