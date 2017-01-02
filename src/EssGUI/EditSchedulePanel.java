package EssGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import EssIO.DataStore;
import EssObject.Country;
import EssObject.Event;
import EssObject.EventSchedule;
import EssObject.Team;

public class EditSchedulePanel extends JPanel implements ActionListener{
	private DataStore dataStore;
	private GridBagConstraints GBC = new GridBagConstraints();
	private GridBagLayout GBL = new GridBagLayout();
	
	private DefaultListModel<String> projectModel;
	private JScrollPane projectJScrollPane;
	private JList<String> projectJList;
	private JScrollPane shecduleListJScorePane;

	private JPanel optionPanel;
	private JList<JPanel> sheduleJList;
	private JButton addScheduleJButton;
	private JButton editScheduleJButton;
	private JButton deleteScheduleJButton;
	private JList<String> scheduleJList;
	private DefaultListModel<String> scheduletModel;
	private JPanel scheduleInformationPanel;
	private JScrollPane scheduleJScrollPane;
	private JScrollPane scheduleInformationScrollPane;
	private JLabel projectLabel;
	private JLabel scheduleNameLabel;
	private JLabel scheduleInformationLabel;
	
	public EditSchedulePanel(DataStore dataStore){
		this.dataStore =dataStore;
		this.setLayout(GBL);
		this.setBackground(Color.gray);
		
		/*比賽Label*/
		projectLabel = new JLabel("比賽",0);
		this.add(projectLabel);
		setGBC(1);
		GBL.setConstraints(projectLabel, GBC);
		
		/*賽程Label*/
		scheduleNameLabel = new JLabel("賽程");
		this.add(scheduleNameLabel);
		setGBC(1);
		GBL.setConstraints(scheduleNameLabel, GBC);
		
		/*賽事資訊Label*/
		scheduleInformationLabel = new JLabel("賽程資訊",0);
		this.add(scheduleInformationLabel);
		setGBC(5);
		GBL.setConstraints(scheduleInformationLabel, GBC);
		
		/**按鈕**/
		addScheduleJButton = new JButton("新增賽程");
		addScheduleJButton.addActionListener(this);
		this.add(addScheduleJButton);
		setGBC(7);
		GBL.setConstraints(addScheduleJButton, GBC);
		
		editScheduleJButton = new JButton("編輯賽程");
		editScheduleJButton.addActionListener(this);
		this.add(editScheduleJButton);
		setGBC(7);
		GBL.setConstraints(editScheduleJButton, GBC);
		
		deleteScheduleJButton = new JButton("刪除賽程");
		deleteScheduleJButton.addActionListener(this);
		this.add(deleteScheduleJButton);
		setGBC(0);
		GBL.setConstraints(deleteScheduleJButton, GBC);
		
		/*顯示所有賽程*/
		
		/**比賽類別List**/
		DefaultListModel<String> projectModel = new DefaultListModel<String>();
		projectJList = new JList<String>(projectModel);
		projectJList.addListSelectionListener(new projectListSelectionListener());
		
		TreeSet<String> projectSet = new TreeSet<String>();
		for(EventSchedule i : dataStore.getEventSchedule()){
			projectSet.add(i.getProject());
		}
		projectJList.setListData((String[])projectSet.toArray(new String[projectSet.size()]));
		
		projectJScrollPane = new JScrollPane(projectJList);
		this.add(projectJScrollPane);
		setGBC(2);
		GBL.setConstraints(projectJScrollPane, GBC);
		
		/**賽程List**/
		scheduletModel = new DefaultListModel<String>();
		scheduleJList = new JList<String>(scheduletModel);
		scheduleJList.addListSelectionListener(new scheduleListSelectionListener());
		scheduleJScrollPane = new JScrollPane(scheduleJList);
		this.add(scheduleJScrollPane);
		setGBC(2);
		GBL.setConstraints(scheduleJScrollPane, GBC);
		
		/**賽事資訊Panel**/
		scheduleInformationPanel = new ScheduleInformationPanel();
		scheduleInformationScrollPane = new JScrollPane(scheduleInformationPanel);
		this.add(scheduleInformationScrollPane);
		setGBC(3);
		GBL.setConstraints(scheduleInformationScrollPane, GBC);
		
	}
	
	private void setGBC(int num){
		switch(num){
		case 0:
			GBC.insets = new Insets(5,5,0,0);
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 0;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.HORIZONTAL;
			break;
		case 1:
			GBC.insets = new Insets(5,0,0,5);
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
			GBC.insets = new Insets(5,5,0,0);
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 1;
			GBC.weighty = 0;
			//GBC.anchor = GridBagConstraints.WEST;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 5:
			GBC.insets = new Insets(5,5,0,0);
			GBC.gridheight = 1;
			GBC.gridwidth = 3;
			GBC.weightx = 1;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 6:
			GBC.insets = new Insets(5,5,0,0);
			GBC.gridheight = 1;
			GBC.gridwidth = 3;
			GBC.weightx = 1;
			GBC.weighty = 1;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 7:
			GBC.insets = new Insets(5,5,0,0);
			GBC.gridheight = 1;
			GBC.gridwidth = 1;
			GBC.weightx = 0;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 8:
			GBC.insets = new Insets(5,5,0,0);
			GBC.gridheight = 1;
			GBC.gridwidth = 0;
			GBC.weightx = 1;
			GBC.weighty = 0;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		case 9:
			GBC.insets = new Insets(5,5,0,0);
			GBC.gridheight = 0;
			GBC.gridwidth = 0;
			GBC.weightx = 1;
			GBC.weighty = 1;
			GBC.fill = GridBagConstraints.BOTH;
			break;
		}
	}
	
	private class ImageListCellRenderer implements ListCellRenderer<JPanel>{

		@Override
		public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof JPanel)
	           {
	               Component component = (Component) value;
	               component.setBackground (isSelected ? Color.cyan : Color.white);//設定JList被選取時的底色改變
	               return component;
	           }
			else
				return new JLabel("???");
		}
	 }
	
	private class projectListSelectionListener implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent e) {
			String select = projectJList.getSelectedValue();
			scheduletModel.removeAllElements();
			for(EventSchedule i : dataStore.getEventSchedule()){
				if(i.getProject().equals(select)){
					scheduletModel.addElement(i.getName());
				}
			}
			updateUI();
		}
		
	}
	
	private class scheduleListSelectionListener  implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			String projectSelect = projectJList.getSelectedValue();
			String scheduleSelect = scheduleJList.getSelectedValue();
			for(EventSchedule i : dataStore.getEventSchedule()){
				if(i.getProject().equals(projectSelect) && i.getName().equals(scheduleSelect)){
					((ScheduleInformationPanel) scheduleInformationPanel).setEvent(i);
					updateUI();
				}
			}
		}
	}
	
	private class ScheduleInformationPanel extends JPanel{
		
		private JLabel nameJLabel;
		private JPanel informationPanel;
		private JTextField nameJTextField;
		private JLabel projectJLabel;
		private DefaultComboBoxModel<String> projectJComboBoxModel;
		private JTextField projectTextField;
		private JScrollPane unpickScrollPane;
		private JScrollPane pickedScrollPane;
		private DefaultListModel<String> unpickJlistModel;
		private JList<String> unpickJlist;
		private DefaultListModel<String> pickedJlistModel;
		private JList<String> pickedJlist;
		private JPanel listLabelPanel;
		private JPanel buttonPanel;
		private JButton toRightJButton;
		private JButton toLeftJButton;
		private JScrollPane eventsJScrollPane;
		private JPanel eventsPanel;
		private JLabel unpickJLabel;
		private JLabel pickedJLabel;
		
		public ScheduleInformationPanel(){
			this.setLayout(GBL);
			
			informationPanel = new JPanel(new GridLayout(1,4));
			nameJLabel = new JLabel("名稱：",SwingConstants.CENTER);
			projectJLabel = new JLabel("比賽：",0);
			nameJTextField = new JTextField();
			
			projectJComboBoxModel = new DefaultComboBoxModel<String>();
			projectTextField = new JTextField();
			setProjectJComboBoxModel();
			
			informationPanel.add(nameJLabel);
			informationPanel.add(nameJTextField);
			informationPanel.add(projectJLabel);
			informationPanel.add(projectTextField);
			this.add(informationPanel);
			setGBC(0);
			GBL.setConstraints(informationPanel, GBC);
			
			eventsPanel = new JPanel(GBL);
			eventsJScrollPane = new JScrollPane(eventsPanel);
			this.add(eventsJScrollPane);
			setGBC(9);
			GBL.setConstraints(eventsJScrollPane, GBC);
		}
		
		public ScheduleInformationPanel(EventSchedule eventSchedule){
			this();
			setEvent(eventSchedule);
		}
		
		public void setEvent(EventSchedule eventSchedule){
			nameJTextField.setText(eventSchedule.getName());
			nameJTextField.setEditable(false);
			projectTextField.setText(eventSchedule.getProject());
			projectTextField.setEditable(false);
			eventsPanel.removeAll();
			for(Event i : eventSchedule.getEvents()){
				JPanel panel = new EventsPanel(i);
				setGBC(0);
				eventsPanel.add(panel);
				GBL.setConstraints(panel, GBC);
			}
			updateUI();
		}
		
		private class EventsPanel extends JPanel{
			Event event;
			public EventsPanel(Event event){
				this.event = event;
				this.setLayout(new GridLayout(1,5));
	
				this.add(new JLabel(event.getTeamB()),0);
				this.add(new JLabel("vs"),0);
				this.add(new JLabel(event.getTeamA()),0);
				this.add(new JLabel(event.getSite()+" "),0);
				this.add(new JLabel(event.getTime().toString()+" "),0);
				
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "新增賽程":
			new AddEventScheduleDialog(dataStore, null);
			updateProjectJList();
			updateUI();
			break;
		case "編輯賽程":
			if(scheduleJList.getSelectedValue() == null){
				JOptionPane.showMessageDialog(null, "請選擇賽程");
				break;
			}
			for(EventSchedule i : dataStore.getEventSchedule()){
				if(i.getProject().equals(projectJList.getSelectedValue()) && i.getName().equals(scheduleJList.getSelectedValue())){
					new AddEventScheduleDialog(dataStore, i);
				}
			}
			updateProjectJList();
			updateUI();
			break;
		case "刪除賽程":
			System.out.println("刪除賽程");
			deleteSchedule();
			break;
		}
	}
	
	private void deleteSchedule(){
		if(projectJList.getSelectedValue()==null){
			JOptionPane.showMessageDialog(null,"請選擇比賽");
			return;
		}
		if(scheduleJList.getSelectedValue()==null){
			JOptionPane.showMessageDialog(null,"請選擇賽程");
			return;
		}
		int result = JOptionPane.showConfirmDialog(null, "確定要刪除\"" + scheduleJList.getSelectedValue() + "\"嗎？" , "警告",JOptionPane.YES_NO_OPTION);
		if(result == 1)
			return;
		System.out.println(projectJList.getSelectedValue());
		for(EventSchedule i : dataStore.getEventSchedule()){
			if(i.getProject().equals(projectJList.getSelectedValue()) && i.getName().equals(scheduleJList.getSelectedValue())){
				System.out.println("remove" + i.getName());
				dataStore.deleteEventSchedule(i);
				TreeSet<String> projectSet = new TreeSet<String>();
				for(EventSchedule j : dataStore.getEventSchedule()){
					projectSet.add(j.getProject());
				}
				projectJList.setListData((String[])projectSet.toArray(new String[projectSet.size()]));
				scheduletModel.removeElement(i.getName());
				updateUI();
			}
		}
	}
	
	private void updateProjectJList(){
		TreeSet<String> projectSet = new TreeSet<String>();
		for(EventSchedule i : dataStore.getEventSchedule()){
			projectSet.add(i.getProject());
		}
		projectJList.setListData((String[])projectSet.toArray(new String[projectSet.size()]));
	}
}
