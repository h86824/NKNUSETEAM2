package EssGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.TreeSet;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import EssObject.*;
import EssObject.Event;
import EssProcess.RandomScheduleBuilder;

public class MainFrame extends JFrame implements ActionListener{
	RandomScheduleBuilder RSB;
	JPanel mainPanel = new JPanel();
	JPanel openPanel = new JPanel();
	EventSchedule ES;
	
	public MainFrame(){	
		setRandomScheduleBuilder();
		replanSchedule();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUIFont(new FontUIResource("新細明體",Font.CENTER_BASELINE,28));
		this.setSize(1920,1080);
		this.setBounds(10, 10,1920, 1080);
		setMainPanel();
		this.getContentPane().add(openPanel, BorderLayout.CENTER);
		this.getContentPane().add(mainPanel, BorderLayout.WEST);
		this.setVisible(true);
	}
	
	private void setMainPanel(){
		mainPanel = new JPanel(new GridLayout(3,1));
		JButton button1 = new JButton( "顯示賽程" );
		JButton button2 = new JButton( "重新安排賽程" );
		JButton button3 = new JButton( "button3" );
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		mainPanel.setBounds(10, 10, 50, 200);
		mainPanel.add(button1);
		mainPanel.add(button2);
		mainPanel.add(button3);
	}
	
	private void setOpenPanelShowSchedule() {
		openPanel.removeAll();
		TreeSet<Event> ESSet = ES.getEvents();
		JPanel panel = new JPanel(new GridLayout(ESSet.size(),6));
		for(Event i : ESSet){
			String [] tempString = i.toString().split(" ");
			for(String j : tempString){
				panel.add(new JLabel(j));
			}
		}
		openPanel.add(panel);
		openPanel.updateUI();
	}
	
	private void setOpenPanelReplan() {
		replanSchedule();
		setOpenPanelShowSchedule();
	}
	
	private void setOpenPanelButton3() {
		openPanel.removeAll();
		JPanel panelButton3 = new JPanel();
		panelButton3.add( new JLabel("button3"));
		openPanel.add(panelButton3);
		openPanel.updateUI();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Event");
		switch(e.getActionCommand()){
		case "顯示賽程":
			System.out.println("顯示賽程");
			setOpenPanelShowSchedule();
			break;
		case "重新安排賽程":
			System.out.println("重新安排賽程");
			setOpenPanelReplan();
			break;
		case "button3":
			System.out.println("press button3");
			setOpenPanelButton3();
			break;
		}
	}
	
	private void setRandomScheduleBuilder(){
		String[] team = new String[10];
		Time[] time = new Time[5];
		for(int i = 0 ; i < 10 ; i++)
			team[i] = new String(String.format("team%d" ,i + 1));
		for(int i = 0 ; i < 5 ; i++)
			time[i] = new Time(2016, 12, 5, 10, i*10);
		RSB = new RandomScheduleBuilder(team, time);
	}
	
	private void replanSchedule(){
		ES = RSB.getSchedule();
		for(Event i : ES.getEvents()){
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
