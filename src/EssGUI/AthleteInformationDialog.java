package EssGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import EssIO.DataStore;
import EssObject.Athlete;

public class AthleteInformationDialog extends JDialog implements ActionListener{
	GridBagConstraints GBC = new GridBagConstraints();
	GridBagLayout GBL = new GridBagLayout();
	String countryName;
	String teamName;
	DataStore dataStore;
	Athlete athlete;
	JPanel mainPanel = new JPanel();
	
	JTextField nameTextField;
	JTextField ageTextField;
	JTextField professionTextField;
	JTextField genderTextField;
	JTextField heightTextField;
	JTextField weightTextField;
	JTextField countryTextField;
	JButton editJButton;
	JButton confirmJButton;
	JButton cancelJButton;
	
	public AthleteInformationDialog(DataStore dataStore ,String countryName , String teamName , Athlete athlete ,Boolean editable){
		/*傳入參考*/
		this.dataStore = dataStore;
		this.athlete = athlete;
		this.countryName = countryName;
		this.teamName = teamName;
		
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
		mainPanel.setLayout(GBL);
		this.add(mainPanel);
		this.setModal(true);
		
		
		/*視窗元件*/
		
		/**姓名**/
		setGBC(0);
		JLabel nameJLabel = new JLabel("姓名 : ",SwingConstants.CENTER);
		mainPanel.add(nameJLabel);
		GBL.setConstraints(nameJLabel, GBC);
		
		setGBC(1);
		nameTextField = new JTextField();
		mainPanel.add(nameTextField);
		nameTextField.setEditable(editable);
		GBL.setConstraints(nameTextField, GBC);
		
		/**性別**/
		setGBC(0);
		JLabel genderJLabel = new JLabel("性別 : ",SwingConstants.CENTER);
		mainPanel.add(genderJLabel);
		GBL.setConstraints(genderJLabel, GBC);
		
		setGBC(1);
		genderTextField = new JTextField();
		mainPanel.add(genderTextField);
		genderTextField.setEditable(editable);
		GBL.setConstraints(genderTextField, GBC);
		
		/**年齡**/
		setGBC(0);
		JLabel ageJLabel = new JLabel("年齡 : ",SwingConstants.CENTER);
		mainPanel.add(ageJLabel);
		GBL.setConstraints(ageJLabel, GBC);
		
		setGBC(1);
		ageTextField = new JTextField();
		mainPanel.add(ageTextField);
		ageTextField.setEditable(editable);
		GBL.setConstraints(ageTextField, GBC);
		
		/**職業**/
		setGBC(0);
		JLabel professionJLabel = new JLabel("職業 : ",SwingConstants.CENTER);
		mainPanel.add(professionJLabel);
		GBL.setConstraints(professionJLabel, GBC);
		
		setGBC(1);
		professionTextField = new JTextField();
		mainPanel.add(professionTextField);
		professionTextField.setEditable(editable);
		GBL.setConstraints(professionTextField, GBC);
		
		/**身高**/
		setGBC(0);
		JLabel heightJLabel = new JLabel("身高 : ",SwingConstants.CENTER);
		mainPanel.add(heightJLabel);
		GBL.setConstraints(heightJLabel, GBC);
		
		setGBC(1);
		heightTextField = new JTextField();
		mainPanel.add(heightTextField);
		heightTextField.setEditable(editable);
		GBL.setConstraints(heightTextField, GBC);
		
		/**體重**/
		setGBC(0);
		JLabel weightJLabel = new JLabel("體重 : ",SwingConstants.CENTER);
		mainPanel.add(weightJLabel);
		GBL.setConstraints(weightJLabel, GBC);
		
		setGBC(1);
		weightTextField = new JTextField();
		mainPanel.add(weightTextField);
		weightTextField.setEditable(editable);
		GBL.setConstraints(weightTextField, GBC);
		
		/**國籍**/
		setGBC(0);
		JLabel countryJLabel = new JLabel("國籍 : ",SwingConstants.CENTER);
		mainPanel.add(countryJLabel);
		GBL.setConstraints(countryJLabel, GBC);
		
		setGBC(1);
		countryTextField = new JTextField();
		mainPanel.add(countryTextField);
		countryTextField.setEditable(editable);
		GBL.setConstraints(countryTextField, GBC);
		
		/**丟入資料**/
		fillTextField();
		
		/**按鈕**/
		setGBC(2);
		JPanel buttonJPanel = new JPanel(GBL);
		mainPanel.add(buttonJPanel);
		GBL.setConstraints(buttonJPanel, GBC);
		
		setGBC(0);
		editJButton = new JButton("編輯");
		editJButton.addActionListener(this);
		editJButton.setEnabled(true);
		if(editable)
			editJButton.setText("儲存後關閉");
		buttonJPanel.add(editJButton);
		GBL.setConstraints(editJButton, GBC);
		
		confirmJButton = new JButton("確認");
		confirmJButton.addActionListener(this);
		confirmJButton.setEnabled(editable);
		buttonJPanel.add(confirmJButton);
		GBL.setConstraints(confirmJButton, GBC);
		
		cancelJButton = new JButton("取消");
		cancelJButton.addActionListener(this);
		cancelJButton.setEnabled(editable);
		buttonJPanel.add(cancelJButton);
		GBL.setConstraints(cancelJButton, GBC);
		
		/*開啟*/
		this.setVisible(true);
	}
	
	/*初始化欄位*/
	private void fillTextField(){
		nameTextField.setText(athlete.getName());
		ageTextField.setText(athlete.getAge() + "");
		professionTextField.setText(athlete.getProfession());
		genderTextField.setText(athlete.getGender());
		heightTextField.setText(athlete.getHeight() + "");
		weightTextField.setText(athlete.getWeight() + "");
		countryTextField.setText(athlete.getCountry());
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
		boolean check = false;
		switch(e.getActionCommand()){
		case"編輯":
			setEditMode(true);
			break;
		case"確認":
			check = updateInformation();
			if(check)
				setEditMode(false);
			break;
		case"取消":
			setEditMode(false);
			fillTextField();
			break;
		case"儲存後關閉":
			check = updateInformation();
			if(check)
				this.dispose();
			break;
		}
	}
	
	private void setEditMode(Boolean mode){
		nameTextField.setEditable(mode);
		ageTextField.setEditable(mode);
		professionTextField.setEditable(mode);
		genderTextField.setEditable(mode);
		heightTextField.setEditable(mode);
		weightTextField.setEditable(mode);
		countryTextField.setEditable(mode);
		editJButton.setEnabled(true);
		if(mode)
			editJButton.setText("儲存後關閉");
		else
			editJButton.setText("編輯");
		confirmJButton.setEnabled(mode);
		cancelJButton.setEnabled(mode);
	}
	
	private boolean updateInformation(){
		dataStore.deleteAthlete(countryName, teamName, athlete);
		
		if(nameTextField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "請輸入姓名");
			return false;
		}
		athlete.setName(nameTextField.getText());
		
		if(ageTextField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "請輸入年齡");
			return false;
		}
		try{
			athlete.setAge(Integer.parseInt(ageTextField.getText()));
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "請輸入正確年齡");
			return false;
		}
		
		
		if(professionTextField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "請輸入職業");
			return false;
		}
		athlete.setProfession(professionTextField.getText());
		
		if(genderTextField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "請輸入性別");
			return false;
		}
		athlete.setGender(genderTextField.getText());
		
		if(heightTextField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "請輸入身高");
			return false;
		}
		try{
			athlete.setHeight(Integer.parseInt(heightTextField.getText()));
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "請輸入正確身高");
			return false;
		}
		
		if(weightTextField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "請輸入體重");
			return false;
		}
		try{
			athlete.setWeight(Integer.parseInt(weightTextField.getText()));
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "請輸入正確體重");
			return false;
		}
		
		if(countryTextField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "請輸入國籍");
			return false;
		}
		athlete.setCountry(countryTextField.getText());
		
		dataStore.addAthlete(countryName, teamName, athlete);
		return true;
	}
}
