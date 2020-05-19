package org.oopda.security.respondent;

import javax.swing.JOptionPane;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmType;

/**
 * The hazardous materials team. They are alerted if a Chemical alarm is tripped and the alarm is considered
 * to be 'critical'. Due to the dangerous nature of chemical hazards, all chemical alarms are considered to 
 * be 'critical'.
 * 
 * 
 *
 */
public class Hazmat implements Respondent 
{

	@Override
	public boolean applicable(Alarm alarm) 
	{
		return (alarm.getType() == AlarmType.CHEMICAL && alarm.isCritical());
	}

	@Override
	/**
	 *A GUI display is created, notifying the user a Hazmat team has been alerted. 
	 */
	public void respond(Alarm alarm) 
	{
		JOptionPane.showMessageDialog(null, "HAZMAT! HAZMAT!", "Hazmat is here!", 
				JOptionPane.INFORMATION_MESSAGE);
	}

}
