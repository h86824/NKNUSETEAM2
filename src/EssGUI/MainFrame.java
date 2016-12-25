package EssGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Enumeration;
import java.util.TreeSet;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import EssIO.DataStore;
import EssObject.*;
import EssObject.Event;
import EssProcess.RandomScheduleBuilder;

public class MainFrame extends JFrame implements ActionListener {
	private RandomScheduleBuilder RSB;
	private JPanel mainPanel = new JPanel();
	private JPanel openPanel = new JPanel();
	private EventSchedule ES;
	private DataStore dataStore = new DataStore();
	private JPanel titlePanel = new JPanel();
	private JLabel title;
	private GridBagConstraints GBC = new GridBagConstraints();
	private GridBagLayout GBL = new GridBagLayout();
	
	public MainFrame(){	
		setRandomScheduleBuilder();
		replanSchedule();
		//JDialog d = new AthleteInformationDialog(dataStore, "a", "b", new Athlete("選手C", "男", 18, "B", 158, 45, "TW", null, null),false);
		//this.setBounds(5, 5,800, 600);
		this.setLayout(GBL);
		/*設定大小*/
		java.awt.Dimension scr_size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(scr_size.width * 3 / 4 , scr_size.height * 3 / 4);
		this.setLocation(
				   (scr_size.width - this.getWidth()) / 2,
				   (scr_size.height - this.getHeight()) / 2);
		
		/*視窗設定*/
		this.setTitle("第二組");
		title = new JLabel("首頁");
		title.setFont(new FontUIResource("標楷體",Font.CENTER_BASELINE,scr_size.height / 1080 * 20));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUIFont(new FontUIResource("新細明體",Font.CENTER_BASELINE,scr_size.height / 1080 * 18));
		
		/*設定按鈕Panel*/
		setMainPanel();
		
		/*標題欄位*/
		GBC.insets = new Insets(8,8,8,8);
		GBC.ipadx = 10;
		GBC.ipady = 1;
		GBC.gridheight = 1;
		GBC.gridwidth = 0;
		GBC.weightx = 1.0;
		GBC.weighty = 0;
		GBC.fill = GridBagConstraints.BOTH;
		titlePanel.add(title);
		titlePanel.setBackground(Color.orange);
		this.getContentPane().add(titlePanel);
		
		/*主按鈕欄位*/
		GBL.setConstraints(titlePanel, GBC);
		GBC.gridheight = 10;
		GBC.gridwidth = 1;
		GBC.weightx = 0;
		GBC.weighty = 1;
		GBC.fill = GridBagConstraints.BOTH;
		this.getContentPane().add(mainPanel);
		GBL.setConstraints(mainPanel, GBC);
		
		/*主要畫面*/
		GBC.gridheight = 0;
		GBC.gridwidth = 10;
		GBC.weightx = 1.0;
		GBC.weighty = 1;
		openPanel.setLayout(GBL);
		openPanel.setBackground(Color.PINK);
		this.getContentPane().add(openPanel);
		GBL.setConstraints(openPanel, GBC);
		
		/*顯示Frame*/
		this.setVisible(true);
	}
	
	/*設定主要按鈕*/
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
		JButton[] button = new JButton[4];
		String [] buttonStr = {"顯示賽程" , "重新安排賽程" , "調整隊伍","查詢"};
		for(int i = 0 ; i < 4 ; i++){
			button[i] = new JButton(buttonStr[i]);
			button[i].addActionListener(this);
			mainPanel.add(button[i]);
			GBL.setConstraints(button[i], GBC);
		}
		
	}
	
	/*設定顯示畫面——賽程表*/
	private void setOpenPanelShowSchedule() {
		//設定Panel layout
		openPanel.removeAll();
		GBC.gridheight = 1;
		GBC.gridwidth = 0;
		GBC.weightx = 0;
		GBC.weighty = 0;
		GBC.insets = new Insets(10,10,10,10);
		GBC.fill = GridBagConstraints.BOTH;
		
		/*讀取Schedule*/
		TreeSet<EventSchedule> ESSet = dataStore.getEventSchedule();
		JPanel panel = new JPanel();
		panel.setLayout(GBL);
		for(EventSchedule j: ESSet){
			
			for(Event i :j. getEvents()){
				JLabel timeJLabel = new JLabel(i.getTime().toString());
				//Label layout
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
			
		}
		
		//加入openPanel
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
	
	/*設定重新排列選項*/
	private void setOpenPanelReplan() {
		//replanSchedule();
		//setOpenPanelShowSchedule();
		GBC.gridheight = 10;
		GBC.gridwidth = 10;
		GBC.weightx = 1;
		GBC.weighty = 1;
		openPanel.removeAll();
		JPanel editSchedulePanel = new EditSchedulePanel(dataStore);
		openPanel.add(editSchedulePanel);
		GBL.setConstraints(editSchedulePanel, GBC);
		openPanel.updateUI();
	}
	
	/*按鈕三Panel*/
	private void setOpenPanelButton3() {
		openPanel.removeAll();
		GBC.gridheight = 10;
		GBC.gridwidth = 10;
		GBC.weightx = 1;
		GBC.weighty = 1;
		JPanel panel = new AddPanel(dataStore);
		openPanel.add(panel);
		GBL.setConstraints(panel, GBC);
		openPanel.updateUI();
		
	}
	private void setOpenPanelSearch() {
		openPanel.removeAll();
		JPanel panel =new SearchPanel();
		openPanel.add(panel);
		GBL.setConstraints(panel, GBC);
		openPanel.updateUI();
	}

	/*事件監視器*/
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
		case "查詢":
			setOpenPanelSearch();
			break;
		}
	}
	
		/*設定隨機排程產生器*/
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
	
	/*重新安排賽程*/
	private void replanSchedule(){
		
		
	}
	
	/*設定全域UI字形*/
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
