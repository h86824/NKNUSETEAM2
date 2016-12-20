package EssGUI;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import EssIO.DataStore;
import EssObject.Athlete;

public class AthleteInformationFrame extends JFrame implements ActionListener{
	GridBagConstraints GBC = new GridBagConstraints();
	GridBagLayout GBL = new GridBagLayout();
	DataStore dataStore;
	Athlete athlete;
	
	public AthleteInformationFrame(DataStore dataStore , Athlete athlete){
		/*傳入參考*/
		this.dataStore = dataStore;
		this.athlete = athlete;
		
		/*設定大小*/
		java.awt.Dimension scr_size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(scr_size.width /5 , scr_size.height /2);
		this.setLocation(
				   (scr_size.width - this.getWidth()) / 2,
				   (scr_size.height - this.getHeight()) / 2);
		
		/*視窗設定*/
		this.setTitle("選手資訊");
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(GBL);
		
		/*視窗元件*/
		
		/**姓名**/
		setGBC(0);
		JLabel nameJLabel = new JLabel("姓名 : ",SwingConstants.CENTER);
		this.add(nameJLabel);
		GBL.setConstraints(nameJLabel, GBC);
		
		setGBC(1);
		JTextField nameTextField = new JTextField( athlete.getName() );
		this.add(nameTextField);
		nameTextField.setEditable(false);
		GBL.setConstraints(nameTextField, GBC);
		
		/**性別**/
		setGBC(0);
		JLabel genderJLabel = new JLabel("性別 : ",SwingConstants.CENTER);
		this.add(genderJLabel);
		GBL.setConstraints(genderJLabel, GBC);
		
		setGBC(1);
		JTextField genderTextField = new JTextField( athlete.getGender() );
		this.add(genderTextField);
		genderTextField.setEditable(false);
		GBL.setConstraints(genderTextField, GBC);
		
		/**年齡**/
		setGBC(0);
		JLabel ageJLabel = new JLabel("年齡 : ",SwingConstants.CENTER);
		this.add(ageJLabel);
		GBL.setConstraints(ageJLabel, GBC);
		
		setGBC(1);
		JTextField ageTextField = new JTextField( athlete.getAge() + "" );
		this.add(ageTextField);
		ageTextField.setEditable(false);
		GBL.setConstraints(ageTextField, GBC);
		
		/**職業**/
		setGBC(0);
		JLabel professionJLabel = new JLabel("職業 : ",SwingConstants.CENTER);
		this.add(professionJLabel);
		GBL.setConstraints(professionJLabel, GBC);
		
		setGBC(1);
		JTextField professionTextField = new JTextField( athlete.getProfession() + "" );
		this.add(professionTextField);
		professionTextField.setEditable(false);
		GBL.setConstraints(professionTextField, GBC);
		
		/**身高**/
		setGBC(0);
		JLabel heightJLabel = new JLabel("身高 : ",SwingConstants.CENTER);
		this.add(heightJLabel);
		GBL.setConstraints(heightJLabel, GBC);
		
		setGBC(1);
		JTextField heightTextField = new JTextField( athlete.getHeight() + "" );
		this.add(heightTextField);
		heightTextField.setEditable(false);
		GBL.setConstraints(heightTextField, GBC);
		
		/**體重**/
		setGBC(0);
		JLabel weightJLabel = new JLabel("體重 : ",SwingConstants.CENTER);
		this.add(weightJLabel);
		GBL.setConstraints(weightJLabel, GBC);
		
		setGBC(1);
		JTextField weightTextField = new JTextField( athlete.getWeight() + "" );
		this.add(weightTextField);
		weightTextField.setEditable(false);
		GBL.setConstraints(weightTextField, GBC);
		
		/**國籍**/
		setGBC(0);
		JLabel countryJLabel = new JLabel("國籍 : ",SwingConstants.CENTER);
		this.add(countryJLabel);
		GBL.setConstraints(countryJLabel, GBC);
		
		setGBC(1);
		JTextField countryTextField = new JTextField( athlete.getCountry() + "" );
		this.add(countryTextField);
		countryTextField.setEditable(false);
		GBL.setConstraints(countryTextField, GBC);
		
		/**按鈕**/
		setGBC(2);
		JPanel buttonJPanel = new JPanel(GBL);
		this.add(buttonJPanel);
		GBL.setConstraints(buttonJPanel, GBC);
		
		setGBC(0);
		JButton editJButton = new JButton("編輯");
		editJButton.addActionListener(this);
		buttonJPanel.add(editJButton);
		GBL.setConstraints(editJButton, GBC);
		
		JButton confirmJButton = new JButton("確認");
		confirmJButton.addActionListener(this);
		confirmJButton.setEnabled(false);
		buttonJPanel.add(confirmJButton);
		GBL.setConstraints(confirmJButton, GBC);
		
		JButton cancelJButton = new JButton("取消");
		cancelJButton.addActionListener(this);
		cancelJButton.setEnabled(false);
		buttonJPanel.add(cancelJButton);
		GBL.setConstraints(cancelJButton, GBC);
	}
	
	/*GBC樣式設定*/
	private void setGBC(int n){
		switch(n){
		case 0:
			GBC.insets = new Insets(10,10,10,10);
			GBC.ipadx = 1;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 1;
			GBC.weightx = 0.3;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 1:
			GBC.ipadx = 2;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 0.7;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 2:
			GBC.ipadx = 3;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 1;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
