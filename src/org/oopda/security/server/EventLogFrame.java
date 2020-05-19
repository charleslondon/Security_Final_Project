package org.oopda.security.server;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Displays the EventLog through a GUI.
 */
public class EventLogFrame extends JFrame 
{

	private static final long serialVersionUID = -1971699532139767167L;

	/**
	 * Constructs an EventLogFrame that will hold a JTable instance take takes a new instance 
	 * of EventLogTableModel as a parameter. A 'Write Log' button is also added to this GUI to allow for 
	 * easy user-end file I/O.
	 */
	public EventLogFrame() 
	{

		JTable table = new JTable(new EventLogTableModel());
		table.getTableHeader().setReorderingAllowed(false);

		Box tableBox = Box.createVerticalBox();
		tableBox.add(table.getTableHeader());
		tableBox.add(new JScrollPane(table));

		add(tableBox);

		JButton writeLog = new JButton("Write Log");
		writeLog.addActionListener(e -> showWriteLog());

		Box bottomBox = Box.createHorizontalBox();
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(writeLog);
		bottomBox.add(Box.createHorizontalGlue());

		add(bottomBox, BorderLayout.SOUTH);
		
		setTitle("Event Log");
		setSize(450, 350);
		setVisible(true);
	}

	/**
	 * Shows a file chooser and writes the alarm data to the specified file.
	 */
	public void showWriteLog() 
	{
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			File file = fileChooser.getSelectedFile();
			try 
			{
				PrintWriter writer = new PrintWriter(new FileWriter(file.getAbsolutePath()));
				for (Event event : EventLog.getInstance().getEvents()) 
				{
					writer.printf("[%s] %s (%s) changed from %s to %s\r\n", event.getDate(),
							event.getAlarm().getName(), event.getAlarm().getLocation(), event.getOldStatus(),
							event.getNewStatus());
				}
				writer.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Can't write to file: " 
				+ e.getMessage(), "File error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
