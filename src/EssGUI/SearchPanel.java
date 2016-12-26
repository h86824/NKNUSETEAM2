package EssGUI;

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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import EssIO.DataStore;
import EssObject.Country;


public class SearchPanel extends JPanel implements ActionListener{
	JLabel jLabel = new JLabel();
	JLabel jLabel1 = new JLabel();
	JComboBox<String> box= new JComboBox<String>(); 
	JTextField field = new JTextField();
	DataStore dataStore;
	JTextArea jTextArea2= new JTextArea();
	
	
	
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
		
		 
		
		
		String[] city={"北京                                                             ","上海","長沙","深圳","南京"};
		JList<String> jl =new JList<String>(city);
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
		
	}
	
		
 


	@Override
	
	public void actionPerformed(ActionEvent arg0) {
		String returnStr[] = new String[2];
		box.getSelectedItem().toString(); 
		returnStr[0]=box.getSelectedItem().toString();
		returnStr[1]=field.getText();
		new massage(returnStr);
		
		jTextArea2.setText("國家:"+"\r\n"+"名字:");
		System.out.println(returnStr[1]);
	}
	
	
}
class massage extends JFrame{
	JLabel jLabel = new JLabel();
	JTextArea jTextArea1 = new JTextArea();
	JPanel Pan=new JPanel(null); 
	public massage(String [] a){
		JButton jb = new JButton("查詢");
		Container c = getContentPane(); 
		c = this.getContentPane(); 
		this.setTitle("查詢結果");
		c.setLayout(new GridLayout(1, 1));
		setSize(400,300);
		setLocation(200,120); 
		setVisible(true);
		
		
		jTextArea1.setBackground(SystemColor.control);
		jTextArea1.setText("國家:"+a[0]+"\r\n"+"名字:"+a[1]);
		c.add(jTextArea1);
		
		
		
	}
	
	
	
	
	
}












