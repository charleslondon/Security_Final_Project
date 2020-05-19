package org.oopda.security.server;

import java.util.Date;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmStatus;

/**
 * Tracks a difference in alarm status at a certain date.
 * This information can be logged.
 * 
 * 
 *
 */
public class Event 
{
	private Date date;
	private Alarm alarm;
	private AlarmStatus oldStatus;
	private AlarmStatus newStatus;

	/**
	 * @return the date of the event.
	 */
	public Date getDate() 
	{
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) 
	{
		this.date = date;
	}

	/**
	 * @return the alarm
	 */
	public Alarm getAlarm() 
	{
		return alarm;
	}

	/**
	 * @param alarm
	 *            the alarm to set
	 */
	public void setAlarm(Alarm alarm) 
	{
		this.alarm = alarm;
	}

	/**
	 * @return the oldStatus
	 */
	public AlarmStatus getOldStatus()
	{
		return oldStatus;
	}

	/**
	 * @param oldStatus
	 *            the oldStatus to set
	 */
	public void setOldStatus(AlarmStatus oldStatus) 
	{
		this.oldStatus = oldStatus;
	}

	/**
	 * @return the newStatus
	 */
	public AlarmStatus getNewStatus() 
	{
		return newStatus;
	}

	/**
	 * @param newStatus
	 *            the newStatus to set
	 */
	public void setNewStatus(AlarmStatus newStatus) 
	{
		this.newStatus = newStatus;
	}

	/**
	 * Constructs an Event. 
	 * 
	 * @param date
	 *            the date of the event
	 * @param alarm
	 *            the alarm that relates to the event
	 * @param oldStatus
	 *            the previous status of the alarm
	 * @param newStatus
	 *            the new status of the alarm
	 */
	public Event(Date date, Alarm alarm, AlarmStatus oldStatus, AlarmStatus newStatus) 
	{
		this.date = date;
		this.alarm = alarm;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
	}
}
