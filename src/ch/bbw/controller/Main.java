package ch.bbw.controller;

import ch.bbw.view.NortFrame;

/**
 * Includes the main Method
 * @author 5ia16padraheim
 */
public class Main {
	
	/**
	 * main Method, only executes initGui method of NortFrame
	 * @param args Arguments that were added to the start of the program
	 */
	public static void main(String[] args) {
		NortFrame.getInstance().initGui();
	}
}
