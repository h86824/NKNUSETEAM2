package EssGUI;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;

import EssIO.DataStore;
import EssObject.Athlete;
import EssObject.Country;
import EssObject.Team;

public class SearchPanel extends JPanel implements ActionListener,ListSelectionListener{
	JLabel jLabel = new JLabel();
	JLabel jLabel1 = new JLabel();
	JComboBox<String> box= new JComboBox<String>(); 
	JTextField field = new JTextField();
	JList<Athlete> jl =new JList<Athlete>();
	DataStore dataStore;
	JTextArea jTextArea2= new JTextArea();
	DefaultListModel<Athlete> dlm = new DefaultListModel<Athlete>();

	
	public SearchPanel(DataStore dataStore){
		this.dataStore=dataStore;
		GridBagLayout GBL = new GridBagLayout();
		this.setBackground(SystemColor.GRAY);
		this.setLayout(GBL);
		
		
		GridBagConstraints G=new GridBagConstraints();
		G.gridwidth = 2;
        G.gridheight = 1;
		G.anchor = GridBagConstraints.WEST;
		G.fill = GridBagConstraints.NONE;
		jLabel1.setText("請輸入選手名:");
		this.add(jLabel1,G);
		
		
		
		
		
		
		
		
		G.weightx=1;
		G.weighty=1;
        G.gridwidth = 3;
        G.gridheight = 1;
        G.fill = GridBagConstraints.HORIZONTAL;
        G.anchor = GridBagConstraints.EAST;
        this.add(field);
        GBL.setConstraints(field, G);
        field.setText("選手A");
        
        
       
		String[] countryList = new String[dataStore.getCountry().size()];
		int count = 0;
		for(Country i : dataStore.getCountry()){
			countryList[count] = i.getName();
			count++;
		}
		for(int i=0 ; i<countryList.length ;i++){
			box.addItem(countryList[i]);
		}
		G.gridwidth = 3;
        G.gridheight = 1;
        G.ipadx = 3;
        G.fill = GridBagConstraints.NONE;
        G.anchor = GridBagConstraints.CENTER;
		this.add(box,G);
		
		
		
        JButton jb = new JButton("查詢");
        jb.addActionListener(this);
        G.gridwidth = 0;
        G.gridheight = 1;
        G.fill = GridBagConstraints.NONE;
        G.anchor = GridBagConstraints.EAST;
		this.add(jb);
		GBL.setConstraints(jb, G);
		
		 
		
		
		
		
		G.insets=new Insets(0,10,10,10);
		G.gridwidth = 4;
        G.gridheight = 10;
        G.weightx=1;
        G.weighty=10;
        G.fill = GridBagConstraints.BOTH;
        G.anchor = GridBagConstraints.NORTHWEST;
		this.add(jl,G);
		
		
		G.insets=new Insets(0,10,10,10);
		G.gridwidth = 6;
		/*G.gridwidth =2;
        G.gridheight = 10;
        G.weightx=1;
        G.weighty=10;*/
        G.fill = GridBagConstraints.BOTH;
        G.anchor = GridBagConstraints.WEST;
        this.add(jTextArea2,G);
        jTextArea2.setBackground(SystemColor.WHITE);
        jl.addListSelectionListener(this);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		jTextArea2.setText("");
		Athlete a =  jl.getSelectedValue();
		jTextArea2.setText("姓名:"+a.getName()+"\r\n國家:"+a.getCountry()+"\r\n職業:"+a.getProfession()+"\r\n性別:"+a.getGender()+"\r\n年齡:"+a.getAge()+"\r\n身高:"+a.getHeight()+"\r\n體重:"+a.getWeight());
	}
	
	@Override
	
	public void actionPerformed(ActionEvent arg0) {
		jTextArea2.setText("");
		String returnStr[] = new String[2];
		returnStr[0]=box.getSelectedItem().toString();
		returnStr[1]=field.getText();
		dlm.clear();
	    jl.setModel(dlm);
		int count=0;
		for(Country i : dataStore.getCountry()){
			if(i.getName().equals(returnStr[0])){
				for(Team t: i.getTeam()){
					for(Athlete a: t.getAthlete()){
						if(a.getName().equals(returnStr[1])){
							dlm.addElement(a);
							count++;
						}
					}
				}
			}
		}
		jl.setModel(dlm);
		if(count==0){
			jTextArea2.setText("無此選手");
		}
		
		
		
	}
	
	
}

	
	
	
	
	













