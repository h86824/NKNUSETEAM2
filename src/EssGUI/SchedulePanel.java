package EssGUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import EssIO.DataStore;
import EssObject.Country;
import EssObject.Event;
import EssObject.EventSchedule;
import EssObject.Team;
import EssObject.Time;

public class SchedulePanel extends JPanel{
	private DataStore dataStore;
	private GridBagConstraints GBC = new GridBagConstraints();
	private GridBagLayout GBL = new GridBagLayout();
	private JPanel optionJPanel = new JPanel(GBL);
	private JPanel optionJPanelDate = new JPanel(GBL);
	private JPanel optionJPanelCountry = new JPanel(GBL);
	private JPanel optionJPanelTeam = new JPanel(GBL);
	private JButton commitJButton = new JButton("確認");
	private DefaultComboBoxModel<String> dateJComboBoxModel = new DefaultComboBoxModel<String>();
	private JComboBox<String> dateJComboBox = new JComboBox<String>(dateJComboBoxModel);
	private DefaultComboBoxModel<String> countryJComboBoxModel = new DefaultComboBoxModel<String>();
	private JComboBox<String> countryJComboBox = new JComboBox<String>(countryJComboBoxModel);
	private DefaultComboBoxModel<String> teamJComboBoxModel = new DefaultComboBoxModel<String>();
	private JComboBox<String> teamJComboBox = new JComboBox<String>(teamJComboBoxModel);
	private JPanel showScheduleJPanel = new JPanel(GBL);
	private JScrollPane showScheduleJScrollPane = new JScrollPane(showScheduleJPanel);
	
	SchedulePanel(DataStore dataStore){
		/*基本設定*/
		this.dataStore = dataStore;
		this.setLayout(GBL);
		this.setBackground(Color.gray);
		
		/*選項Panel*/
		this.add(optionJPanel);
		setGBC("optionJPanel");
		GBL.setConstraints(optionJPanel, GBC);
		
		/*選項Panel裡的Panel*/
		setGBC("InsideOptionJPanel");
		optionJPanel.add(optionJPanelDate);
		GBL.setConstraints(optionJPanelCountry, GBC);
		optionJPanel.add(optionJPanelCountry);
		GBL.setConstraints(optionJPanelDate, GBC);
		optionJPanel.add(optionJPanelTeam);
		GBL.setConstraints(optionJPanelTeam, GBC);
		
		setGBC("commitJButton");
		optionJPanel.add(commitJButton);
		GBL.setConstraints(commitJButton, GBC);
		commitJButton.addActionListener(new commiteJButtonListener());
		
		/**時間欄位**/
		JLabel dateJLabel = new JLabel("日期",0);
		setGBC("optionLabel");
		optionJPanelDate.add(dateJLabel);
		GBL.setConstraints(dateJLabel, GBC);
		setGBC("optionComboBox");
		optionJPanelDate.add(dateJComboBox);
		GBL.setConstraints(dateJComboBox, GBC);
		dateJComboBoxModel.addElement("--");
		dateJComboBox.addActionListener(new dateComboBoxListener());
		
		/**國家欄位**/
		JLabel countryJLabel = new JLabel("國家",0);
		setGBC("optionLabel");
		optionJPanelCountry.add(countryJLabel);
		GBL.setConstraints(countryJLabel, GBC);
		setGBC("optionComboBox");
		optionJPanelCountry.add(countryJComboBox);
		countryJComboBox.setEditable(true);
		GBL.setConstraints(countryJComboBox, GBC);
		countryJComboBoxModel.addElement("");
		countryJComboBox.addActionListener(new countryComboBoxListener());
		
		/**隊伍欄位**/
		JLabel teamJLabel = new JLabel("隊伍",0);
		setGBC("optionLabel");
		optionJPanelTeam.add(teamJLabel);
		GBL.setConstraints(teamJLabel, GBC);
		setGBC("optionComboBox");
		teamJComboBox.setEditable(true);
		optionJPanelTeam.add(teamJComboBox);
		GBL.setConstraints(teamJComboBox, GBC);
		teamJComboBoxModel.addElement("");
		
		/*顯示賽程Panel*/
		setGBC("showScheduleJPanel");
		this.add(showScheduleJScrollPane);
		GBL.setConstraints(showScheduleJScrollPane, GBC);
		
		setDateComboBox();
		setCountryComboBox();
		setTeamComboBox();
	}
	
	private void setGBC(String n){
		switch(n){
		case "optionJPanel":
			GBC.insets = new Insets(8,8,8,8);
			GBC.ipadx = 10;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 1.0;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.HORIZONTAL;
			break;
		case "InsideOptionJPanel":
			GBC.insets = new Insets(8,8,8,8);
			GBC.ipadx = 2;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 3;
			GBC.weightx = 1.0;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.HORIZONTAL;
			break;
		case "commitJButton":
			GBC.insets = new Insets(8,8,8,8);
			GBC.ipadx = 1;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 1;
			GBC.weightx = 1.0;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.NONE;
			break;
		case "optionLabel":
			GBC.insets = new Insets(8,8,8,0);
			GBC.ipadx = 1;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 1;
			GBC.weightx = 0.5;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.NONE;
			break;
		case "optionComboBox":
			GBC.insets = new Insets(8,0,8,8);
			GBC.ipadx = 1;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 2;
			GBC.weightx = 1.0;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.HORIZONTAL;
			break;
		case "showScheduleJPanel":
			GBC.insets = new Insets(0,8,8,8);
			GBC.ipadx = 10;
			GBC.ipady = 9;
			GBC.gridheight = 0;
			GBC.gridwidth = 0;
			GBC.weightx = 1.0;
			GBC.weighty = 1.0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case "insideShowScheduleJPanel":
			GBC.insets = new Insets(0,8,8,8);
			GBC.ipadx = 10;
			GBC.ipady = 1;
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 1.0;
			GBC.weighty = 0.0;
			GBC.fill = GridBagConstraints.HORIZONTAL;
			break;
		}
	}
	
	private void setDateComboBox(){
		TreeSet<String> timeSet = new TreeSet<String>();
		dateJComboBoxModel.removeAllElements();
		dateJComboBoxModel.addElement("--");
		for(EventSchedule es : dataStore.getEventSchedule()){
			for(Event event : es.getEvents()){
				timeSet.add(String.format("%d/%02d/%02d"
						, event.getTime().getYear()
						, event.getTime().getMonth()
						, event.getTime().getDay()));
			}
		}
		
		for(String i : timeSet){
			dateJComboBoxModel.addElement(i);
		}
	}
	
	private void setCountryComboBox(){
		countryJComboBoxModel.removeAllElements();
		countryJComboBox.setSelectedItem("");
		for(Country i : dataStore.getCountry()){
			countryJComboBoxModel.addElement(i.getName());
		}
	}
	
	private void setTeamComboBox(){
		TreeSet<String> teamSet = new TreeSet<String>();
		teamJComboBoxModel.removeAllElements();
		teamJComboBoxModel.setSelectedItem("");
		for(Country i : dataStore.getCountry()){
			for(Team j : i.getTeam()){
				teamSet.add(j.getName());
			}
		}
		
		for(String i : teamSet){
			teamJComboBoxModel.addElement(i);
		}
	}
	
	private class dateComboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String select = (String)dateJComboBoxModel.getSelectedItem();
			if(select == null || select.equals("--")){
				setCountryComboBox();
				setTeamComboBox();
				return;
			}
			int year = Integer.parseInt(select.split("/")[0]);
			int month = Integer.parseInt(select.split("/")[1]);
			int date = Integer.parseInt(select.split("/")[2]);
			countryJComboBoxModel.removeAllElements();
			TreeSet<String> countrySet = new TreeSet<String>();
			for(EventSchedule es : dataStore.getEventSchedule()){
				for(Event event : es.getEvents()){
					if(event.getTime().getYear() == year &&
							event.getTime().getMonth() == month &&
							event .getTime().getDay() == date){
						countrySet.add(event.getTeamA());
						countrySet.add(event.getTeamB());
					}
				}
			}
			countryJComboBoxModel.addElement("");
			for(String i : countrySet){
				countryJComboBoxModel.addElement(i);
			}
		}
	}
	
	private class countryComboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String select = (String)countryJComboBoxModel.getSelectedItem();

			teamJComboBoxModel.removeAllElements();
			Country country = dataStore.getReference(select);
			teamJComboBoxModel.setSelectedItem("");
			if(country == null){
				setTeamComboBox();
				return;
			}
			for(Team i : country.getTeam()){
				teamJComboBoxModel.addElement(i.getName());
			}
		}
	}
	
	private class commiteJButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			showScheduleJPanel.removeAll();
			
			String selectDate = (String)dateJComboBoxModel.getSelectedItem();
			String selectCountry = (String)countryJComboBoxModel.getSelectedItem();
			String selectTeam = (String)teamJComboBoxModel.getSelectedItem();
			
			int year = 0;
			int month = 0;
			int day = 0;
			if(!selectDate.equals("--")){
				year = Integer.parseInt(selectDate.split("/")[0]);
				month = Integer.parseInt(selectDate.split("/")[1]);
				day = Integer.parseInt(selectDate.split("/")[2]);
			}
			
			ArrayList<EventPanel> eventPanelSet = new ArrayList<EventPanel>();
			
			for(EventSchedule es : dataStore.getEventSchedule()){
				if(!selectTeam.equals("") && es.getProject().indexOf(selectTeam)<0){
					continue;
				}
				for(Event event : es.getEvents()){
					if((selectDate.equals("--") || (event.getTime().getYear() == year && event.getTime().getMonth() == month && event.getTime().getDay() == day) )
							&& (selectCountry.equals("") || (event.getTeamA().indexOf(selectCountry)>=0 || event.getTeamB().indexOf(selectCountry)>=0))){
						eventPanelSet.add(new EventPanel(event, es.getProject() , es.getName()));
					}
				}
			}
			
			Collections.sort(eventPanelSet);
			
			for(JPanel i : eventPanelSet){
				showScheduleJPanel.add(i);
				
				setGBC("insideShowScheduleJPanel");
				GBL.setConstraints(i, GBC);
			}
			
			if(eventPanelSet.size() == 0){
				JPanel notFound = new EventPanel();
				showScheduleJPanel.add(notFound);
				
				setGBC("insideShowScheduleJPanel");
				GBL.setConstraints(notFound, GBC);
			}
			
			showScheduleJPanel.updateUI();
		}
	}
	
	private class EventPanel extends JPanel implements Comparable<EventPanel>{
		private Event event;
		private String project;
		private String title;
		public EventPanel(){
			this.setLayout(new GridLayout(1,1));
			this.add(new JLabel("查無資料",0));
		}
		public EventPanel(Event event, String project, String title){
			this.event = event;
			this.project = project;
			this.title = title;
			this.setLayout(new GridLayout(1,5));
			
			this.add(new JLabel(String.format("%d/%02d/%02d %02d:%02d"
					, event.getTime().getYear()
					, event.getTime().getMonth()
					, event.getTime().getDay()
					, event.getTime().getHour()
					, event.getTime().getMinute()) , 0));
			
			this.add(new JLabel(project, 0));
			
			this.add(new JLabel(String.format("%s vs %s"
					, event.getTeamA()
					, event.getTeamB()), 0));
			
			this.add(new JLabel(title, 0));
			this.add(new JLabel(event.getSite(), 0));
		}
		
		@Override
		public int compareTo(EventPanel o) {
			return this.event.compareTo(o.event);
		}
	}
	
}
