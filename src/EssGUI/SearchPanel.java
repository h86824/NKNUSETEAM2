package EssGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import EssIO.DataStore;


public class SearchPanel extends JPanel{
	JLabel jLabel = new JLabel();
	
	
	DataStore dataStore;
	
	
	
	public SearchPanel(DataStore dataStore){
		this.dataStore=dataStore;
		GridBagLayout GBL = new GridBagLayout();
		this.setLayout(GBL);
		GridBagConstraints G=new GridBagConstraints();
		G.gridx = 0;
        G.gridy = 0;
        G.gridwidth = 6;
        G.gridheight = 1;
        G.weightx = 0;
        G.weighty = 0;
        G.fill = GridBagConstraints.BOTH;
        G.anchor = GridBagConstraints.WEST;
        
        this.add(field);
        GBL.setConstraints(field, G);
        JButton jb = new JButton("查詢");
        
		G.gridx = 10;
        G.gridy = 0;
        G.gridwidth = 1;
        G.gridheight = 1;
        G.weightx = 0;
        G.weighty = 0;
        G.fill = GridBagConstraints.BOTH;
        G.anchor = GridBagConstraints.WEST;
		this.add(jb);
		GBL.setConstraints(jb, G);
		field.setText("請輸入國家名");
		
		
		this.add(jLabel);
	}
	JTextField field = new JTextField();
	
	
}	

class ButtonHandler implements ActionListener{

	 private DataStore dataStore;
	public void actionPerformed(ActionEvent Event){ 
		} 
	 	JPanel panel = new SearchPanel(dataStore);
	 		
		} 












