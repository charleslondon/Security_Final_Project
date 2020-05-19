package org.oopda.security.alarm;

import java.io.*;
import java.util.UUID;

/**
 * An Alarm contains a location, status, uuid, and name. Internally, the alarm
 * is identified by uuid.
 *
 */
public abstract class Alarm implements Serializable 
{

	private static final long serialVersionUID = -245778678760105195L;
	private String location;
	private AlarmStatus status;
	private final UUID uuid;
	private String name;

	/**
	 * @return the location
	 */
	public String getLocation() 
	{
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) 
	{
		this.location = location;
	}

	/**
	 * @return the status
	 */
	public AlarmStatus getStatus() 
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(AlarmStatus status) {
		this.status = status;
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid() 
	{	
		return uuid;
	}

	/**
	 * Constructs an Alarm with an initial state of PREPARING
	 * 
	 * @param location
	 *            the location
	 * @param name
	 *            the name
	 */
	public Alarm(String location, String name) 
	{
		this.location = location;
		this.status = AlarmStatus.PREPARING;
		this.name = name;
		this.uuid = UUID.nameUUIDFromBytes((getType() + location + name).getBytes());
	}

	/**
	 * @return the name
	 */
	public String getName() 
	{
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * @return if the Alarm is considered critical
	 */
	public abstract boolean isCritical();

	/**
	 * @return the type of alarm
	 */
	public abstract AlarmType getType();

	/**
	 * Serializes the Alarm to an array of bytes
	 * Allows for the object data to be piped to server.
	 * @return the alarm as an array of bytes
	 */
	public byte[] serialize() 
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream objectStream = new ObjectOutputStream(out);
			objectStream.writeObject(this);
			objectStream.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	/**
	 * Deserializes an Alarm from an array of bytes for use in server.
	 * 
	 * @param input
	 *            the serialized Alarm
	 * @return the de-serialized alarm
	 */
	public static Alarm deserialize(byte[] input) 
	{
		Alarm alarm = null;
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(input));
			alarm = (Alarm) ois.readObject();
			ois.close();
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return alarm;
	}
}
