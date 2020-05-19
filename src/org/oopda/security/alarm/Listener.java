package org.oopda.security.alarm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.oopda.security.server.RoomList;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Listener extends Thread 
{

	private final RoomList rooms;

	public Listener(RoomList rooms) 
	{
		this.rooms = rooms;
	}

	@Override
	/**
	 * Generates a ZeroMQ instance that will thread the alarm information to and from the server. 
	 */
	public void run() 
	{
		Context context = ZMQ.context(1);
		
		Socket responder = context.socket(SocketType.REP);
		responder.bind("tcp://localhost:5560");

		while (!Thread.currentThread().isInterrupted()) 
		{
			byte[] message = responder.recv(0);
			Alarm alarm = Alarm.deserialize(message);

			Exception response = null;
			try 
			{
				rooms.updateAlarm(alarm);
			} 
			catch (Exception ex) 
			{
				response = ex;
			}
			
			responder.send(serializeException(response));
		}
		responder.close();
		context.term();
	}

	/**
	 * Serializes an exception to a byte array, this information is then sent to the server
	 * before being relayed to the user. 
	 * 
	 * @param e
	 *            the exception to serialize
	 * @return the exception as a byte array
	 */
	private byte[] serializeException(Exception e) 
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try 
		{
			ObjectOutputStream objectStream = new ObjectOutputStream(out);
			objectStream.writeObject(e);
			objectStream.close();
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		return out.toByteArray();
	}
}
