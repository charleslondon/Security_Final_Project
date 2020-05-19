package org.oopda.security.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Utilities for reading files
 * 
 * 
 *
 */
public class FileUtils 
{

	/**
	 * Reads the file specified by fileName and adds each line as a room
	 * 
	 * @param fileName
	 *            the name of the file to read
	 * @param rooms
	 *            the RoomList to update
	 * @throws IOException
	 */
	public static void addRoomsFromFile(String fileName, RoomList rooms) throws IOException 
	{
		BufferedReader roomFile = new BufferedReader(new FileReader(fileName));
		while (roomFile.ready()) 
		{
			rooms.addRoom(roomFile.readLine());
		}
		roomFile.close();
	}

	/**
	 * Reads the csv file specified by fileName and adds each inspection to the
	 * RoomList
	 * 
	 * @param fileName
	 *            the name of the csv file to read
	 * @param rooms
	 *            the RoomList to update
	 * @throws IOException
	 *             an error in reading the file
	 * @throws ParseException
	 *             csv parsing fails
	 */
	public static void addInspectionsFromFile(String fileName, RoomList rooms) 
			throws IOException, ParseException 
	{
		Reader inspectionFile = new FileReader(fileName);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(inspectionFile);

		for (CSVRecord record : records)
		{
			RoomInspection inspection = RoomInspection.parse(record);
			rooms.addInspection(inspection);
		}
	}
}
