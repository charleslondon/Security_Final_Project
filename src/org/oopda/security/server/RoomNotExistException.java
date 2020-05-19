package org.oopda.security.server;

/**
 * Thrown when a room doesn't exist to modify.
 * This fulfills the project requirement of utilizing custom exception handling.
 * 
 */
public class RoomNotExistException extends RuntimeException 
{
	private static final long serialVersionUID = -5455259018408325944L;
	
	private String location;

	/**
	 * @return the location string.
	 */
	public String getLocation() 
	{
		return location;
	}

	/**
	 * @param location the location to set in String form.
	 */
	public void setLocation(String location) 
	{
		this.location = location;
	}

	/**
	 * Constructs a RoomNotExistException
	 * @param location the room that doesn't exist
	 */
	public RoomNotExistException(String location) 
	{
		this.location = location;
	}

	public String toString() 
	{
		return "Room, " + location + ", doesn't exist";
	}
}
