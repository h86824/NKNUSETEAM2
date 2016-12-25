package EssGUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import EssObject.Event;
import EssObject.Time;

public class AddEventDialog extends JDialog implements ActionListener{
	JTextField yearTextFeild = new JTextField("");
	JTextField monthTextFeild = new JTextField("");
	JTextField dayTextFeild = new JTextField("");
	JTextField hourTextFeild = new JTextField("");
	JTextField minuteTextFeild = new JTextField("");
	JTextField siteTextFeild = new JTextField("");
	GridBagConstraints GBC = new GridBagConstraints();
	GridBagLayout GBL = new GridBagLayout();
	DefaultComboBoxModel<String> TeamAJComboBoxModel = new DefaultComboBoxModel<String>();
	JComboBox<String> TeamAJComboBox = new JComboBox<String>(TeamAJComboBoxModel);
	DefaultComboBoxModel<String> TeamBJComboBoxModel = new DefaultComboBoxModel<String>();
	JComboBox<String> TeamBJComboBox = new JComboBox<String>(TeamBJComboBoxModel);
	Event event;
	TreeSet<String> countrySet;
	AddEventDialog(Event event, TreeSet<String> countrySet){
		this.event = event;
		this.countrySet = countrySet;
		java.awt.Dimension scr_size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int width = scr_size.width * 2 / 3;
		int height = scr_size.height /12;
		this.setSize(width , height);
		this.setLocation(
				   (scr_size.width - this.getWidth()) / 2,
				   (scr_size.height - this.getHeight()) / 2);
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(new GridLayout(1,13));
		
		JLabel yearJLabel = new JLabel("年");
		
		TeamAJComboBox.addActionListener(new ComboBoxListener());
		TeamAJComboBoxModel.addElement("隨機");
		for(String i : countrySet){
			TeamAJComboBoxModel.addElement(i);
		}
		
		JButton enterJButton = new JButton("確定");
		enterJButton.addActionListener(this);
		yearTextFeild.setHorizontalAlignment(JTextField.RIGHT);
		dayTextFeild.setHorizontalAlignment(JTextField.RIGHT);
		monthTextFeild.setHorizontalAlignment(JTextField.RIGHT);
		hourTextFeild.setHorizontalAlignment(JTextField.RIGHT);
		minuteTextFeild.setHorizontalAlignment(JTextField.RIGHT);
		siteTextFeild.setHorizontalAlignment(JTextField.RIGHT);
		
		this.add(yearTextFeild);
		this.add(yearJLabel);
		this.add(dayTextFeild);
		this.add(new JLabel("日"));
		this.add(monthTextFeild);
		this.add(new JLabel("月"));
		this.add(hourTextFeild);
		this.add(new JLabel("點"));
		this.add(minuteTextFeild);

		this.add(new JLabel("分"));
		
		this.add(new JLabel("場地:"));
		this.add(siteTextFeild);
		this.add(new JLabel(" TeamA:"));
		this.add(TeamAJComboBox);
		this.add(new JLabel(" TeamB:"));
		this.add(TeamBJComboBox);
		this.add(enterJButton);
		this.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case"確定":
			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			try{
				year = Integer.parseInt(yearTextFeild.getText());
			}
			catch(NumberFormatException n){
				JOptionPane.showMessageDialog(null, "請輸入正確年的數字");
				return;
			}
			
			try{
				month = Integer.parseInt(monthTextFeild.getText());
			}
			catch(NumberFormatException n){
				JOptionPane.showMessageDialog(null, "請輸入正確月的數字");
				return;
			}
			
			try{
				day = Integer.parseInt(dayTextFeild.getText());
			}
			catch(NumberFormatException n){
				JOptionPane.showMessageDialog(null, "請輸入正確日的數字");
				return;
			}
			
			try{
				hour = Integer.parseInt(hourTextFeild.getText());
			}
			catch(NumberFormatException n){
				JOptionPane.showMessageDialog(null, "請輸入正確時的數字");
				return;
			}
			try{
				minute = Integer.parseInt(minuteTextFeild.getText());
			}
			catch(NumberFormatException n){
				JOptionPane.showMessageDialog(null, "請輸入正確分的數字");
				return;
			}
			
			if(siteTextFeild.getText().equals("")){
				JOptionPane.showMessageDialog(null, "請輸入場地");
				return;
			}
			
			event.setTime(new Time(year, month, day, hour, minute));
			event.setSite(siteTextFeild.getText());
			event.setTeaA((String)TeamAJComboBox.getSelectedItem());
			event.setTeaB((String)TeamBJComboBox.getSelectedItem());
			this.dispose();
			break;
		}
	}
	
	private class ComboBoxListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == TeamAJComboBox){
				String selectA = (String)TeamAJComboBox.getSelectedItem();
				TeamBJComboBoxModel.removeAllElements();
				TeamBJComboBoxModel.addElement("隨機");
				for(String i : countrySet){
					if(!i.equals(selectA))
						TeamBJComboBoxModel.addElement(i);
				}
			}
			else if(e.getSource() == TeamBJComboBox){
				String selectB = (String)TeamBJComboBox.getSelectedItem();
				TeamAJComboBoxModel.removeAllElements();
				TeamAJComboBoxModel.addElement("隨機");
				for(String i : countrySet){
					if(!i.equals(selectB))
						TeamAJComboBoxModel.addElement(i);
				}
			}
		}
	}
}
