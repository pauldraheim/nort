package ch.bbw.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Is able to create every custom Nort Component
 * @author 5ia16padraheim
 */
public class NortComponentFactory {
	
	private static final String FONT = "Century Gothic";
	
	private static final NortComponentFactory compFactory = new NortComponentFactory();
	
	private NortComponentFactory() {}
	
	/**
	 * Creates a custom JButton
	 * @param name The name of the Button
	 * @param text The text of the Button
	 * @return The created Button
	 */
	public JButton createButton(String name, String text) {
		JButton btn = new JButton(text);

		btn.setName(name);
		btn.setBackground(Color.WHITE);
		btn.setAlignmentX(JButton.CENTER_ALIGNMENT);
		btn.setFont(new Font(FONT, 0, (NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / 40));
		
		return btn;
	}
	
	/**
	 * Creates a custom JLabel that is used for almost everything but titles
	 * @param name The name of the Label
	 * @param text The text of the Label
	 * @return The created Label
	 */
	public JLabel createDescriptionLabel(String name, String text) {
		JLabel lbl = new JLabel(text);
		
		lbl.setName(name);
		lbl.setFont(new Font(FONT, 0, (NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / 40));
		lbl.setForeground(Color.WHITE);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		return lbl;
	}
	
	/**
	 * Creates a custom JLabel that is used only for titles
	 * @param name The name of the Label
	 * @param text The text of the Label
	 * @return The created Label
	 */
	public JLabel createTitleLabel(String name, String text) {
		JLabel lbl = new JLabel(text);

		lbl.setName(name);
		lbl.setFont(new Font(FONT, 0, (NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / 20));
		lbl.setForeground(Color.WHITE);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		return lbl;
	}
	
	/**
	 * Creates a custom JTextField
	 * @param name The name of the TextField 
	 * @return The created TextField
	 */
	public JTextField createTextField(String name) {
		JTextField tf = new JTextField();

		tf.setName(name);
		tf.setFont(new Font(FONT, 0, (NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / 40));
		tf.setHorizontalAlignment(JTextField.CENTER);
		
		return tf;
	}
	
	/**
	 * Creates a custom JPasswordField
	 * @param name The name of the PasswordField
	 * @return The created PasswordField
	 */
	public JPasswordField createPasswordField(String name) {
		JPasswordField pf = new JPasswordField();
		
		pf.setName(name);
		pf.setFont(new Font(FONT, 0, (NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / 40));
		pf.setHorizontalAlignment(JPasswordField.CENTER);
		
		return pf;
	}
	
	/**
	 * Creates a custom JTable
	 * @param rowData The row data of the table
	 * @param columnNames The column names of the table
	 * @param name The name of the table
	 * @return The new table
	 */
	public JTable createTable(Object[][] rowData, String[] columnNames, String name) {
		JTable table = new JTable(rowData, columnNames);
		
		Font font = new Font(FONT, 0, (NortFrame.getInstance().getWidth() - NortFrame.getInstance().getInsets().left * 2) / 40);
		
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setRowHeight((NortFrame.getInstance().getHeight() - NortFrame.getInstance().getInsets().top -
				NortFrame.getInstance().getInsets().bottom) / 18);
		table.setName(name);
		
		return table;
	}
	
	public static NortComponentFactory getInstance() {
		return compFactory;
	}
}
