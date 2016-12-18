package EssGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.TreeSet;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import EssIO.DataStore;
import EssObject.*;
import EssObject.Event;
import EssProcess.RandomScheduleBuilder;

public class MainFrame extends JFrame implements ActionListener{
	RandomScheduleBuilder RSB;
	JPanel mainPanel = new JPanel();
	JPanel openPanel = new JPanel();
	EventSchedule ES;
	DataStore dataStore = new DataStore();
	JPanel titlePanel = new JPanel();
	JLabel title;
	GridBagConstraints GBC = new GridBagConstraints();
	GridBagLayout GBL = new GridBagLayout();
	public MainFrame(){	
		setRandomScheduleBuilder();
		replanSchedule();
		title = new JLabel("首頁");
		title.setFont(new FontUIResource("標楷體",Font.CENTER_BASELINE,20));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUIFont(new FontUIResource("新細明體",Font.CENTER_BASELINE,18));
		
		//this.setBounds(5, 5,800, 600);
		this.setSize(1000, 800);
		this.setLayout(GBL);
		setMainPanel();
		
		GBC.insets = new Insets(10,10,10,10);
		GBC.ipadx = 0;
		GBC.ipady = 9;
		GBC.gridheight = 1;
		GBC.gridwidth = 0;
		GBC.weightx = 1.0;
		GBC.weighty = 0;
		GBC.fill = GridBagConstraints.BOTH;
		titlePanel.add(title);
		titlePanel.setBackground(Color.orange);
		this.getContentPane().add(titlePanel);
		GBL.setConstraints(titlePanel, GBC);
		GBC.gridheight = 8;
		GBC.gridwidth = 1;
		GBC.weightx = 0;
		GBC.weighty = 0.2;
		GBC.fill = GridBagConstraints.BOTH;
		this.getContentPane().add(mainPanel);
		GBL.setConstraints(mainPanel, GBC);
		GBC.gridheight = 0;
		GBC.gridwidth = 10;
		GBC.weightx = 1.0;
		GBC.weighty = 0.8;
		openPanel.setLayout(GBL);
		openPanel.setBackground(Color.PINK);
		this.getContentPane().add(openPanel);
		GBL.setConstraints(openPanel, GBC);
		this.setVisible(true);
	}
	
	private void setMainPanel(){
		//GridBagConstraints GBC = new GridBagConstraints();
		//GridBagLayout GBL = new GridBagLayout();
		mainPanel.setLayout(GBL);
		GBC.gridheight = 2;
		GBC.gridwidth = 0;
		GBC.weightx = 0;
		GBC.weighty = 1;
		GBC.insets = new Insets(10,0,0,0);
		GBC.fill = GridBagConstraints.BOTH;
		JButton[] button = new JButton[3];
		String [] buttonStr = {"顯示賽程" , "重新安排賽程" , "調整隊伍"};
		for(int i = 0 ; i < 3 ; i++){
			button[i] = new JButton(buttonStr[i]);
			button[i].addActionListener(this);
			mainPanel.add(button[i]);
			GBL.setConstraints(button[i], GBC);
		}
		
	}
	
	private void setOpenPanelShowSchedule() {
		openPanel.removeAll();
		GBC.gridheight = 1;
		GBC.gridwidth = 0;
		GBC.weightx = 0;
		GBC.weighty = 0;
		GBC.insets = new Insets(10,10,10,10);
		GBC.fill = GridBagConstraints.BOTH;
		TreeSet<Event> ESSet = dataStore.getEventSchedule().getEvents();
		JPanel panel = new JPanel();
		panel.setLayout(GBL);
		for(Event i : ESSet){
			JLabel timeJLabel = new JLabel(i.getTime().toString());
			GBC.gridheight = 1;
			GBC.gridwidth = 2;
			GBC.weightx = 0;
			GBC.weighty = 0;
			GBC.insets = new Insets(10,0,0,15);
			GBC.fill = GridBagConstraints.BOTH;
			panel.add(timeJLabel);
			GBL.setConstraints(timeJLabel, GBC);
			
			JLabel teamAJLabel = new JLabel(i.getTeamA());
			panel.add(teamAJLabel);
			GBL.setConstraints(teamAJLabel, GBC);
			
			JLabel markJLabel = new JLabel(":");
			panel.add(markJLabel);
			GBL.setConstraints(markJLabel, GBC);
			
			JLabel teamBJLabel = new JLabel(i.getTeamB());
			GBC.gridwidth = 0;
			panel.add(teamBJLabel);
			GBL.setConstraints(teamBJLabel, GBC);
		}
		
		GBC.gridheight = 8;
		GBC.gridwidth = 10;
		GBC.weightx = 1;
		GBC.weighty = 1;
		GBC.insets = new Insets(10,10,10,10);
		GBC.fill = GridBagConstraints.BOTH;
		openPanel.add(panel);
		GBL.setConstraints(panel, GBC);
		openPanel.updateUI();
	}
	
	private void setOpenPanelReplan() {
		replanSchedule();
		setOpenPanelShowSchedule();
	}
	
	private void setOpenPanelButton3() {
		openPanel.removeAll();
		openPanel.removeAll();
		GBC.gridheight = 10;
		GBC.gridwidth = 10;
		GBC.weightx = 1;
		GBC.weighty = 1;
		//JPanel panelButton3 = new JPanel();
		//panelButton3.add( new JLabel("button3"));
		JPanel panel = new AddPanel(dataStore);
		openPanel.add(panel);
		GBL.setConstraints(panel, GBC);
		openPanel.updateUI();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Event");
		switch(e.getActionCommand()){
		case "顯示賽程":
			System.out.println("顯示賽程");
			title.setText("顯示賽程");
			setOpenPanelShowSchedule();
			break;
		case "重新安排賽程":
			System.out.println("重新安排賽程");
			title.setText("顯示賽程");
			setOpenPanelReplan();
			break;
		case "調整隊伍":
			System.out.println("press button3");
			title.setText("新增隊伍");
			setOpenPanelButton3();
			break;
		}
	}
	
	private void setRandomScheduleBuilder(){
		TreeSet<String> teamSet = new TreeSet<String>();
		TreeSet<Time> timeSet = new TreeSet<Time>();
		for(Country i:dataStore.getCountry()){
			for(Team j : i.getTeam()){
				if(j.getName().equals("籃球"))
					teamSet.add(i.getName());
			}
		}
		timeSet.add(new Time(2016, 12, 15, 12, 00));
		timeSet.add(new Time(2016, 12, 15, 12, 30));
		timeSet.add(new Time(2016, 12, 15, 13, 00));
		RSB = new RandomScheduleBuilder(teamSet, timeSet);
	}
	
	private void replanSchedule(){
		dataStore.setEvnetSchedule(RSB.getSchedule());
		for(Event i : dataStore.getEventSchedule().getEvents()){
			System.out.println(i);
		}
	}
	
	 public void setUIFont (FontUIResource fui){
		 Enumeration keys=UIManager.getDefaults().keys();
		 while (keys.hasMoreElements()) {
			 Object key=keys.nextElement();
			 Object value=UIManager.get(key);
			 if (value != null && value instanceof FontUIResource) {
				 UIManager.put(key, fui);
			 }
		 } 
	}
}
