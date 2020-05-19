package org.oopda.security.server;

import org.oopda.security.respondent.RespondentList;

/**
 * A driver for the entire project. Instantiates a new MainFrame server for the project to run on
 * and initializes the RespondentList.
 */
public class App 
{
	public static void main(String[] args) {
		new MainFrame(args[0], args[1]);
		RespondentList.initialize();
	}
}
