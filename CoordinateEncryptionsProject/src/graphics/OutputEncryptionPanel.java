package graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutputEncryptionPanel extends JPanel {
	private static final JTextArea theOutput = new JTextArea();
	public OutputEncryptionPanel(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 40, 0, 40);
		JLabel messageInserting = new JLabel("Output:");
		add(messageInserting,c);
		theOutput.setEditable(false);
		JScrollPane outputScrolling = new JScrollPane(theOutput);
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0, 40, 100, 40);
		add(outputScrolling,c);
		setOpaque(false);
	}
	public static void setOutput(String output){
		theOutput.setText(theOutput.getText()+output);
	}
	public static void clearOutput(){theOutput.setText("");}
	public static String getEcryptMessage(){return theOutput.getText();}
}
