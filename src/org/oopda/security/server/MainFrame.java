package org.oopda.security.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import org.oopda.security.alarm.Listener;

/**
 * The main JFrame. Displays room status. This is more or less the driver GUI and one of the most important
 * classes in the project. 
 * 
 */
public class MainFrame extends JFrame 
{
	private static final long serialVersionUID = 7823931863514918005L;

	/**
	 * Constructs a MainFrame
	 * Utilizes a try-catch block in the case a room is not found. There are two catch blocks as 
	 * exceptions can come in either the form of a parsing failure or an I/O error.
	 */
	public MainFrame(String roomsFile, String inspectionsFile)
	{
		RoomList rooms = new RoomList();

		try
		{
			FileUtils.addRoomsFromFile(
					roomsFile, rooms);

			FileUtils.addInspectionsFromFile(
					inspectionsFile, rooms);
		}
		catch (IOException e1) 
		{
			JOptionPane.showMessageDialog(this, "IO Error: " + e1.getMessage(),
					"IO Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);

		}
		catch (ParseException e1) 
		{

			JOptionPane.showMessageDialog(this, "Cannot parse inspections: " 
					+ e1.getMessage(), "Parse Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		Thread listenerThread = new Listener(rooms);
		listenerThread.start();

		setLayout(new BorderLayout());

		Font largerFont = new Font("Sans-serif", Font.PLAIN, 20);

		JTable table = new JTable(new RoomTableModel(rooms));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setFont(largerFont);
		table.setRowSorter(new TableRowSorter<>((RoomTableModel) table.getModel()));
		table.setFont(largerFont);
		table.setRowHeight(40);

		table.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent me) 
			{
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				if (me.getClickCount() == 2 && row != -1) 
				{
					Room selectedRoom = ((RoomTableModel) table.getModel()).getRoom(row);
					showRoomDetails(selectedRoom);
				}
			}
		});

		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);

		JButton eventLogButton = new JButton("View Event Log");

		eventLogButton.addActionListener(e -> new EventLogFrame());

		eventLogButton.setFont(largerFont);
		add(eventLogButton, BorderLayout.SOUTH);

		setSize(850, 550);
		setTitle("Security System");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Shows a RoomFrame with the specified room and a detailed report of it's relevant data.
	 * 
	 * @param room
	 *            the room to detail
	 */
	private void showRoomDetails(Room room) 
	{
		new RoomDialog(this, room);
	}
}
