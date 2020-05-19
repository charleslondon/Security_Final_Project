package org.oopda.security.respondent;

import org.oopda.security.alarm.Alarm;

/**
 * Specifies that a class can respond to alarms
 */
public interface Respondent 
{
	/**
	 * Determines if the alarm applies to the respondent
	 * @param alarm
	 *           - the alarm to test
	 * @return if the respondent will respond to the alarm
	 */
	boolean applicable(Alarm alarm);

	/**
	 * Respond to the alarm if it has been 'tripped' aka the alarm status has had a unauthorized change after it 
	 * has been established as 'ACTIVATED'.
	 * 
	 * @param alarm the alarm
	 */
	void respond(Alarm alarm);
}
