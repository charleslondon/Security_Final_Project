package org.oopda.security.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.csv.CSVRecord;

/**
 * Inspection data for a room. Includes a room name, initials of worker, and date
 * of inspection.
 * 
 * 
 *
 */
public class RoomInspection implements Comparable<RoomInspection> 
{

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	private final String roomName;
	private final String initial;
	private final Date date;

	/**
	 * @return the roomName in a String.
	 */
	public String getRoomName() 
	{
		return roomName;
	}

	/**
	 * @return the initials of the worker sent to Inspect the room for authentication purposes.
	 */
	public String getInitial() 
	{
		return initial;
	}

	/**
	 * @return A 'getter' method for the date the room was inspected.
	 */
	public Date getDate() 
	{
		return date;
	}

	/**
	 * Gets the string representation of the date using the dateFormat
	 * @return the date as a string
	 */
	public String getDateAsString() 
	{
		return dateFormat.format(date);
	}

	/**
	 * Constructs a RoomInspection.
	 * 
	 * @param roomName
	 *            the name of the room
	 * @param initial
	 *            the initial of the inspector
	 * @param date
	 *            the date of the inspection
	 */
	public RoomInspection(String roomName, String initial, Date date) 
	{
		this.roomName = roomName;
		this.initial = initial;
		this.date = date;
	}

	/**
	 * Uses a CSVRecord to construct a room inspection
	 * 
	 * @param record
	 *            the record to parse
	 * @return a room based off of the record
	 * @throws ParseException exception
	 */
	public static RoomInspection parse(CSVRecord record) throws ParseException 
	{
		String roomName = record.get(0);
		String initial = record.get(1);
		String dateAsString = record.get(2);

		return new RoomInspection(roomName, initial, dateFormat.parse(dateAsString));
	}

	@Override
	public int compareTo(RoomInspection o)
	{
		return this.date.compareTo(o.getDate());
	}
}
