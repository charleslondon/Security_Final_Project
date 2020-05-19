package org.oopda.security.respondent;

import java.util.ArrayList;
import java.util.List;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmStatus;
import org.oopda.security.server.Event;
import org.oopda.security.server.EventLog;

/**
 * Tracks respondents and calls respondents on alarm changes
 *
 */
public class RespondentList 
{

	private static final List<Respondent> respondents = new ArrayList<>();

	/**
	 * Initializes the RespondentList and adds the known respondents to the list.
	 * Subscribes to the EventLog to listen to alarm activation events. If an event occurs then the 
	 * alarm is updated. If the alarm is 'tripped' while it's status is 'ACTIVATED' then a method call is 
	 * dispatched for respondents to respond to.
	 */
	public static void initialize() 
	{
		respondents.add(new FireDepartment());
		respondents.add(new PoliceDepartment());
		respondents.add(new Hazmat());

		EventLog.getInstance().addPropertyChangeListener("events", evt -> {
			Alarm eventAlarm = (((Event) evt.getNewValue())).getAlarm();
			if (eventAlarm.getStatus() == AlarmStatus.ACTIVATED)
			{
				RespondentList.alertAll(eventAlarm);
			}
		});
	}

	/**
	 * Alerts all respondents that are applicable to the alarm. This is performed through a for:each style 
	 * loop that goes through every respondent, it than performs a check to see if the respondent in each
	 * loop iteration is responsible for the alarm being tripped, if they are in fact
	 * responsible, they are alerted. 
	 * 
	 * @param alarm
	 *            the alarm to respond to
	 */
	public static void alertAll(Alarm alarm) 
	{
		for (org.oopda.security.respondent.Respondent res : respondents)
		{
			if (res.applicable(alarm)) 
			{
				res.respond(alarm);
			}
		}
	}
}
