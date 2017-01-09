package EssGUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import EssIO.DataStore;
import EssObject.Country;
import EssObject.Event;
import EssObject.EventSchedule;
import EssObject.Team;

public class AddEventScheduleDialog extends JDialog implements ActionListener{
	private DataStore dataStore;
	private TreeSet<Event> eventSet = new TreeSet<Event>();
	private GridBagConstraints GBC = new GridBagConstraints();
	private GridBagLayout GBL = new GridBagLayout();
	private JLabel nameJLabel;
	private JPanel informationPanel;
	private JTextField nameJTextField;
	private JLabel projectJLabel;
	private DefaultComboBoxModel<String> projectJComboBoxModel;
	private JComboBox<String> projectJComboBox;
	private JScrollPane unpickScrollPane;
	private JScrollPane pickedScrollPane;
	private DefaultListModel<String> unpickJlistModel = new DefaultListModel<String>();
	private JList<String> unpickJlist;
	private DefaultListModel<String> pickedJlistModel = new DefaultListModel<String>();
	private JList<String> pickedJlist;
	private JPanel listLabelPanel;
	private JPanel buttonPanel;
	private JButton toRightJButton;
	private JButton toLeftJButton;
	private JScrollPane eventsJScrollPane;
	private JPanel eventsInformationPanel;
	private JButton addEventJButton;
	private JPanel eventsPanel;
	private JLabel unpickJLabel;
	private JLabel pickedJLabel =new JLabel("已選擇(0)",0);
	private JPanel optionPanel;
	private JButton enterJButton;
	private JButton cancelJButton;
	private JButton randomJButton;
	
	public AddEventScheduleDialog(DataStore dataStore, EventSchedule eventSchedule){
		/*設定大小*/
		java.awt.Dimension scr_size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(scr_size.width /2 , scr_size.height /2);
		this.setLocation(
				   (scr_size.width - this.getWidth()) / 2,
				   (scr_size.height - this.getHeight()) / 2);
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(GBL);
		this.dataStore = dataStore;
		informationPanel = new JPanel(new GridLayout(1,4));
		nameJLabel = new JLabel("名稱：",SwingConstants.CENTER);
		projectJLabel = new JLabel("比賽類別：",0);
		nameJTextField = new JTextField("");
		
		projectJComboBoxModel = new DefaultComboBoxModel<String>();
		projectJComboBox = new JComboBox<String>(projectJComboBoxModel); 
		projectJComboBox.addActionListener(this);
		setProjectJComboBoxModel();
		
		informationPanel.add(nameJLabel);
		informationPanel.add(nameJTextField);
		informationPanel.add(projectJLabel);
		informationPanel.add(projectJComboBox);
		this.add(informationPanel);
		setGBC(0);
		GBL.setConstraints(informationPanel, GBC);
		
		listLabelPanel = new JPanel(new GridLayout(1,2));
		unpickJLabel = new JLabel("未選擇",0);
		listLabelPanel.add(unpickJLabel);
		listLabelPanel.add(pickedJLabel);
		
		
		unpickJlist = new JList<String>(unpickJlistModel);
		pickedJlist = new JList<String>(pickedJlistModel);
		unpickScrollPane = new JScrollPane(unpickJlist);
		pickedScrollPane = new JScrollPane(pickedJlist);
		
		buttonPanel = new JPanel(new GridLayout(2,1));
		toRightJButton = new JButton(">");
		toRightJButton.addActionListener(this);
		buttonPanel.add(toRightJButton);
		toLeftJButton = new JButton("<");
		toLeftJButton.addActionListener(this);
		buttonPanel.add(toLeftJButton);
		
		this.add(listLabelPanel);
		setGBC(0);
		GBL.setConstraints(listLabelPanel, GBC);
		setGBC(6);
		this.add(unpickScrollPane);
		GBL.setConstraints(unpickScrollPane, GBC);
		setGBC(7);
		this.add(buttonPanel);
		GBL.setConstraints(buttonPanel, GBC);
		this.add(pickedScrollPane);
		setGBC(8);
		GBL.setConstraints(pickedScrollPane, GBC);
		
		eventsInformationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addEventJButton = new JButton("新增比賽");
		addEventJButton.addActionListener(this);
		eventsInformationPanel.add(addEventJButton);
		randomJButton = new JButton("隨機選擇");
		randomJButton.addActionListener(this);
		eventsInformationPanel.add(randomJButton);
		this.add(eventsInformationPanel);
		setGBC(4);
		GBL.setConstraints(eventsInformationPanel, GBC);
		
		eventsPanel = new JPanel(GBL);
		eventsJScrollPane = new JScrollPane(eventsPanel);
		this.add(eventsJScrollPane);
		setGBC(9);
		GBL.setConstraints(eventsJScrollPane, GBC);
		
		optionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		enterJButton = new JButton("確認");
		enterJButton.addActionListener(this);
		optionPanel.add(enterJButton);
		GBL.setConstraints(enterJButton, GBC);
		cancelJButton = new JButton("取消");
		cancelJButton.addActionListener(this);
		optionPanel.add(cancelJButton);
		this.add(optionPanel);
		setGBC(10);
		GBL.setConstraints(optionPanel, GBC);
		
		if(eventSchedule != null){
			setEvent(eventSchedule);
		}
		/*開啟*/
		this.setVisible(true);
	}
	
	public void setEvent(EventSchedule eventSchedule){
		nameJTextField.setText(eventSchedule.getName());
		projectJComboBox.setSelectedItem(eventSchedule.getProject());
		projectJComboBox.enable(false);
		projectJComboBox.setEditable(false);
		eventsPanel.removeAll();
		eventSet = eventSchedule.getEvents();
		TreeSet<String> pickedSet = new TreeSet<String>();
		for(Event i : eventSet){
			pickedSet.add(i.getTeamA());
			pickedSet.add(i.getTeamB());
			JPanel panel = new EventsPanel(i, this);
			setGBC(0);
			eventsPanel.add(panel);
			GBL.setConstraints(panel, GBC);
		}
		for(String i : pickedSet){
			pickedJlistModel.addElement(i);
			unpickJlistModel.removeElement(i);
		}
		pickedJLabel.setText("已選擇("+pickedJlistModel.size()+")");
	}
		
	private class EventsPanel extends JPanel{
		Event event;
		public EventsPanel(Event event , ActionListener actionListener){
			this.event = event;
			this.setLayout(new GridLayout(1,6));
	
			this.add(new JLabel(event.getTeamB()),0);
			this.add(new JLabel("vs"),0);
			this.add(new JLabel(event.getTeamA()),0);
			this.add(new JLabel(event.getSite()),0);
			this.add(new JLabel(event.getTime().toString()),0);
				
			JButton deleteJButton = new JButton("刪除");
			deleteJButton.addActionListener(new deleteListener(event));
			this.add(deleteJButton);
		}
	}

	private void setProjectJComboBoxModel(){
		TreeSet<String> projectSet = new TreeSet<String>();
			
		for(Country i : dataStore.getCountry()){
			for(Team j : i.getTeam()){
				projectSet.add(j.getName());
			}
		}
		for(String i : projectSet)
			projectJComboBoxModel.addElement(i);
	}	
	
	private void setGBC(int num){
		GBC.ipadx = 0;
		GBC.ipady = 0;
		switch(num){
		case 0:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 1;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.HORIZONTAL;
			break;
		case 1:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 1;
			GBC.gridwidth = 2;
			GBC.weightx = 1;
			GBC.weighty = 0;
			//GBC.anchor = GridBagConstraints.WEST;
			GBC.fill = GridBagConstraints.NONE;
			break;
		case 2:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 9;
			GBC.gridwidth = 2;
			GBC.weightx = 1;
			GBC.weighty = 1;
			//GBC.anchor = GridBagConstraints.WEST;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 3:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 9;
			GBC.gridwidth = 6;
			GBC.weightx = 1;
			GBC.weighty = 1;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 4:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 1;
			GBC.weighty = 0;
			//GBC.anchor = GridBagConstraints.WEST;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 5:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 1;
			GBC.gridwidth = 4;
			GBC.weightx = 1;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 6:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 2;
			GBC.gridwidth = 3;
			GBC.weightx = 1;
			GBC.weighty = 0.4;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 7:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 2;
			GBC.gridwidth = 1;
			GBC.weightx = 0;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 8:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 2;
			GBC.gridwidth = 0;
			GBC.weightx = 1;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 9:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 8;
			GBC.gridwidth = 0;
			GBC.weightx = 1;
			GBC.weighty = 1;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 10:
			GBC.insets = new Insets(5,5,5,5);
			GBC.gridheight = 1;
			GBC.gridwidth = 7;
			GBC.weightx = 1;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.HORIZONTAL;
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TreeSet<String> stringSet1;
		TreeSet<String> stringSet2;
		if(e.getSource().getClass() == new JButton().getClass()){
			System.out.println(e.getActionCommand());
			switch(e.getActionCommand()){
			case">":
				String unpickJlistgSelected = unpickJlist.getSelectedValue();
				if(unpickJlistgSelected == null)
					break;
				unpickJlistModel.removeElement(unpickJlistgSelected);
				stringSet1 = new TreeSet<String>();
				for(int i = 0 ; i < unpickJlistModel.size() ; i++){
					stringSet1.add(unpickJlistModel.getElementAt(i));
				}
				unpickJlistModel.removeAllElements();
				for(String i:stringSet1){
					unpickJlistModel.addElement(i);
				}
				stringSet2 = new TreeSet<String>();
				stringSet2.add(unpickJlistgSelected);
				for(int i = 0 ; i < pickedJlistModel.size() ; i++){
					stringSet2.add(pickedJlistModel.getElementAt(i));
				}
				pickedJlistModel.removeAllElements();
				for(String i:stringSet2){
					pickedJlistModel.addElement(i);
				}
				pickedJLabel.setText("已選擇("+pickedJlistModel.size()+")");
				break;
			case"<":
				String pickedJlistgSelected = pickedJlist.getSelectedValue();
				if(pickedJlistgSelected == null)
					break;
				pickedJlistModel.removeElement(pickedJlistgSelected);
				stringSet1 = new TreeSet<String>();
				for(int i = 0 ; i < pickedJlistModel.size() ; i++){
					stringSet1.add(pickedJlistModel.getElementAt(i));
				}
				pickedJlistModel.removeAllElements();
				for(String i:stringSet1){
					pickedJlistModel.addElement(i);
				}
				stringSet2 = new TreeSet<String>();
				stringSet2.add(pickedJlistgSelected);
				for(int i = 0 ; i < unpickJlistModel.size() ; i++){
					stringSet2.add(unpickJlistModel.getElementAt(i));
				}
				unpickJlistModel.removeAllElements();
				for(String i:stringSet2){
					unpickJlistModel.addElement(i);
				}
				pickedJLabel.setText("已選擇("+pickedJlistModel.size()+")");
				break;
			case"新增比賽":
				if(pickedJlistModel.size() <= eventSet.size()*2){
					JOptionPane.showMessageDialog(null, "比賽已滿");
					break;
				}
				if(pickedJlistModel.size()%2 == 1){
					JOptionPane.showMessageDialog(null, "請選擇偶數隊伍");
					break;
				}
				
				stringSet1 = new TreeSet<String>();
				for(int i = 0 ; i < pickedJlistModel.size() ; i++){
					stringSet1.add(pickedJlistModel.getElementAt(i));
				}
				for(Event i : eventSet){
					stringSet1.remove(i.getTeamA());
					stringSet1.remove(i.getTeamB());
				}
				Event inputEvent = new Event(null, null, null, null);
				new AddEventDialog(inputEvent, stringSet1);
				if(inputEvent.getTime() != null && inputEvent.getSite() != null && inputEvent.getTeamA() != null && inputEvent.getTeamB() != null){
					System.out.println(inputEvent);
					if(eventSet.add(inputEvent) == false)
						JOptionPane.showMessageDialog(null, "已有重複選項，新增失敗");
					updateEventPanel();
				}
				break;
			case"確認":
				enterButton();
				break;
			case"取消":
				this.dispose();
				break;
			case"隨機選擇":
				randomChoose();
				break;
			}
		}
		else if(e.getSource().getClass() == new JComboBox<String>().getClass()){
			String project = (String)((JComboBox<String>)e.getSource()).getSelectedItem();
			pickedJlistModel.removeAllElements();
			pickedJLabel.setText("已選擇(0)");
			unpickJlistModel.removeAllElements();
			for(Country i : dataStore.getCountry()){
				for(Team j : i.getTeam()){
					if(j.getName().equals(project)){
						unpickJlistModel.addElement(i.getName());
						break;
					}
				}
			}
		}
	}
	private void enterButton(){
		if(nameJTextField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "請輸入賽程名");
			return;
		}
		if(eventSet.size() == 0){
			JOptionPane.showMessageDialog(null, "請加入比賽");
			return;
		}
		EventSchedule saveEventSchedule = new EventSchedule(nameJTextField.getText(), (String)projectJComboBox.getSelectedItem());
		for(Event i : eventSet){
			saveEventSchedule.addEvent(i);
		}
		randomChoose();
		dataStore.addEventSchedule(saveEventSchedule);
		this.dispose();
	}
	
	private class deleteListener implements ActionListener{
		Event event;
		public deleteListener(Event event){
			this.event = event;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			eventSet.remove(event);
			eventsPanel.removeAll();
			for(Event i : eventSet){
				JPanel panel = new EventsPanel(i, this);
				setGBC(0);
				eventsPanel.add(panel);
				GBL.setConstraints(panel, GBC);
			}
			eventsPanel.updateUI();
		}
		
	}
	
	private void randomChoose(){
		TreeSet<String> stringSet = new TreeSet<String>();
		for(int i = 0 ; i < pickedJlistModel.size() ; i++){
			stringSet.add(pickedJlistModel.getElementAt(i));
		}
		for(Event i : eventSet){
			stringSet.remove(i.getTeamA());
			stringSet.remove(i.getTeamB());
		}
		ArrayList<String> randomArray = new ArrayList<String>();
		randomArray.addAll(stringSet);
		long seed = System.nanoTime();
		Collections.shuffle(randomArray, new Random(seed));
		System.out.println(randomArray.size());
		for(Event i : eventSet){
			if(i.getTeamA() == "隨機" && randomArray.size() > 0){
				i.setTeaA(randomArray.get(0));
				randomArray.remove(0);
			}
			if(i.getTeamB() == "隨機" && randomArray.size() > 0){
				i.setTeaB(randomArray.get(0));
				randomArray.remove(0);
			}
		}
		updateEventPanel();
	}
	
	private void updateEventPanel(){
		eventsPanel.removeAll();
		for(Event i : eventSet){
			JPanel newEventPanel = new EventsPanel(i, this);
			eventsPanel.add(newEventPanel);
			setGBC(0);
			GBL.setConstraints(newEventPanel, GBC);
		}
		eventsPanel.updateUI();
	}
}

