package org.oopda.security.alarm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * Sends the state of an alarm to the server every second.
 * This is a threading system that communicates with both the server
 * and the Alarms as a way to order the data.
 * 
 */
public class Client extends Thread 
{

	final Alarm alarm;
	/**
	 * Constructs a Client
	 * 
	 * @param alarm 
	 * The alarm that will be reported upon
	 */
	public Client(Alarm alarm) 
	{
		this.alarm = alarm;
	}

	/**
	 * Creates an instance of ZeroMQ and connects to a 'server'.
	 * As the application runs, every 1 second the data from an alarm is serialized and 
	 * sent to the server from this client. In the client it is then de-serialized and compared to
	 * it's previous records to notice a change. 
	 */
	public void run() 
	{
		Context context = ZMQ.context(1);
		Socket requester = context.socket(SocketType.REQ);
		requester.connect("tcp://localhost:5560");

		while (true) 
		{
			requester.send(alarm.serialize(), 0);

			try 
			{
				Exception ex = deserializeException(requester.recv());

				if (ex != null) 
				{
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Alarm Error", 
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				Thread.sleep(1000);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deserialize an array of bytes to an exception
	 * 
	 * @param bytes
	 *            the serialized exception
	 * @return the exception de-serialized
	 */
	public Exception deserializeException(byte[] bytes) 
	{
		Exception ex = null;
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			ex = (Exception) ois.readObject();
			ois.close();
		}
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return ex;
	}
}
