package EssGUI;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.TreeSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import EssIO.DataStore;
import EssObject.Athlete;
import EssObject.Country;
import EssObject.Team;

public class AddPanel extends JPanel implements ActionListener , WindowListener{
	DataStore dataStore;
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	GridBagLayout gridBagLayout = new GridBagLayout();
	JList<String> countryJList;
	JList<String> teamJList;
	JList<String> athleteJList;
	
	public AddPanel(DataStore dataStore){
		this.dataStore = dataStore;
		this.setBackground(Color.GRAY);
		this.setLayout(gridBagLayout);
		buildCountryList();
	}
	
	private void buildCountryList(){
		
		String[] countryList = new String[dataStore.getCountry().size()];
		int count = 0;
		for(Country i : dataStore.getCountry()){
			countryList[count++] = i.getName();
		}
		
		gridBagConstraints.ipadx = 0;
		gridBagConstraints.ipady = 0;
		gridBagConstraints.insets = new Insets(5,5,5,5);
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		/*國家標題欄*/
		JLabel counrtyJLabel = new JLabel("國家");
		JLabel teamJLabel = new JLabel("隊伍");
		JLabel athleteJLabel = new JLabel("選手(雙擊開啟詳細資訊)");
		this.add(counrtyJLabel, gridBagConstraints);
		gridBagLayout.setConstraints(counrtyJLabel, gridBagConstraints);
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 1;
		JButton addCountryButton = new JButton("+國家");
		JButton subCountryButton = new JButton("-國家");
		addCountryButton.addActionListener(this);
		this.add(addCountryButton);
		gridBagLayout.setConstraints(addCountryButton, gridBagConstraints);
		subCountryButton.addActionListener(this);
		this.add(subCountryButton);
		gridBagLayout.setConstraints(subCountryButton, gridBagConstraints);
		
		/*隊伍標題欄*/
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 3;
		this.add(teamJLabel, gridBagConstraints);
		gridBagLayout.setConstraints(teamJLabel, gridBagConstraints);
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 1;
		JButton addTeamButton = new JButton("+隊伍");
		JButton subTeamButton = new JButton("-隊伍");
		addTeamButton.addActionListener(this);
		this.add(addTeamButton);
		gridBagLayout.setConstraints(addTeamButton, gridBagConstraints);
		subTeamButton.addActionListener(this);
		this.add(subTeamButton);
		gridBagLayout.setConstraints(subTeamButton, gridBagConstraints);
		
		/*選手標題欄*/
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 1;
		this.add(athleteJLabel, gridBagConstraints);
		gridBagLayout.setConstraints(athleteJLabel, gridBagConstraints);
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 1;
		JButton addAthleteButton = new JButton("+選手");
		JButton subAthleteButton = new JButton("-選手");
		addAthleteButton.addActionListener(this);
		this.add(addAthleteButton);
		gridBagLayout.setConstraints(addAthleteButton, gridBagConstraints);
		gridBagConstraints.gridwidth = 0;
		subAthleteButton.addActionListener(this);
		this.add(subAthleteButton);
		gridBagLayout.setConstraints(subAthleteButton, gridBagConstraints);
		
		gridBagConstraints.ipadx = 0;
		gridBagConstraints.ipady = 0;
		gridBagConstraints.insets = new Insets(5,5,5,5);
		gridBagConstraints.gridheight = 8;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		/*國家清單*/
		countryJList = new JList<String>(countryList);
		countryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		countryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane countryJScrollPane = new JScrollPane(countryJList);
		this.add(countryJScrollPane);
		gridBagLayout.setConstraints(countryJScrollPane, gridBagConstraints);
		
		/*隊伍清單*/
		teamJList = new JList<String>();
		teamJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		teamJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane teamJScrollPane = new JScrollPane(teamJList);
		this.add(teamJScrollPane);
		gridBagLayout.setConstraints(teamJScrollPane, gridBagConstraints);
		
		/*選手清單*/
		athleteJList = new JList<String>();
		athleteJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		athleteJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane athleteJScrollPane = new JScrollPane(athleteJList);
		this.add(athleteJScrollPane);
		gridBagConstraints.gridwidth = 3;
		gridBagLayout.setConstraints(athleteJScrollPane, gridBagConstraints);
		
		/*國家清單事件*/
		countryJList.addListSelectionListener(new ListSelectionListener(){
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				cleenTeamJList();
				cleenAthleteJList();
				System.out.println(countryJList.getSelectedValue());
				for(Country i : dataStore.getCountry()){
					if(i.getName().equals(countryJList.getSelectedValue())){
						if(i.getTeam().size() > 0){
							String[] teamArray = new String[i.getTeam().size()];
							int count = 0;
							for(Team j : i.getTeam()){
								teamArray[count++] = j.getName();
							}
							teamJList.setListData(teamArray);
							updateUI();
						}
					}
				}
				;
			}
			
		});
		
		/*隊伍清單事件*/
		teamJList.addListSelectionListener(new ListSelectionListener(){
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(countryJList.getSelectedValue());
				for(Country i : dataStore.getCountry()){
					System.out.println(countryJList.getSelectedValue());
					if(i.getName().equals(countryJList.getSelectedValue())){
						System.out.println(i.getName());
						for(Team j : i.getTeam()){
							if(j.getName().equals(teamJList.getSelectedValue())){
								System.out.println(j.getName());
								String[] athleteArray = new String[j.getAthlete().size()];
								int count = 0;
								for(Athlete k : j.getAthlete()){
									athleteArray[count++] = k.getName();
								}
								cleenAthleteJList();
								athleteJList.setListData(athleteArray);
								updateUI();
							}
						}
					}
				}
			}
			
		});
		
		/*選手清單事件*/
		athleteJList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				 if(e.getClickCount() == 2){
					 System.out.println("two Click event");
					 for(Country i :dataStore.getCountry()){
						 if(i.getName().equals(countryJList.getSelectedValue())){
							 for(Team j : i.getTeam()){
								 if(j.getName().equals(teamJList.getSelectedValue())){
									 for(Athlete k : j.getAthlete()){
										 if(k.getName().equals(athleteJList.getSelectedValue())){
											 new AthleteInformationFrame(dataStore, i.getName(), j.getName(), k ,false);
										 }
									 }
								 }
							 }
						 }
					 }
				 }
			 }
		});
	}
	
	private void cleenTeamJList(){
		teamJList.setModel(new DefaultListModel<String>());
		DefaultListModel<String> model = (DefaultListModel<String>)teamJList.getModel();
		model.removeAllElements();
		updateUI();
	}
	
	private void cleenAthleteJList(){
		athleteJList.setModel(new DefaultListModel<String>());
		DefaultListModel<String> model = (DefaultListModel<String>)athleteJList.getModel();
		model.removeAllElements();
		updateUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String selectCountry = countryJList.getSelectedValue();
		String selectTeam = teamJList.getSelectedValue();
		String selectAthlete = athleteJList.getSelectedValue();
		switch(e.getActionCommand()){
		case"+國家":
			String country = JOptionPane.showInputDialog("請輸入國家名稱：");
			if(country!=null){
				dataStore.addCountry(country);
				
				String[] countryList = new String[dataStore.getCountry().size()];
				int count = 0;
				for(Country i : dataStore.getCountry()){
					countryList[count++] = i.getName();
				}
				
				countryJList.setListData(countryList);
				cleenTeamJList();
				updateUI();
			}
			break;
			
		case"-國家":
			if(selectCountry != null){
				int result = JOptionPane.showConfirmDialog(null, "確定要刪除\"" + selectCountry + "\"嗎？" , "警告",JOptionPane.YES_NO_OPTION);
				if(result == 0){
					dataStore.deleteCountry(selectCountry);
					
					String[] tempList = new String[dataStore.getCountry().size()];
					int counttemp = 0;
					for(Country i : dataStore.getCountry()){
						tempList[counttemp++] = i.getName();
						countryJList.setListData(tempList);
						cleenTeamJList();
						cleenAthleteJList();
						updateUI();
					}
					
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "請選擇國家");
			}
			break;
			
		case"+隊伍":
			if(selectCountry != null){
				String teamName = JOptionPane.showInputDialog("請輸入隊伍名稱：");
				if(teamName!=null){
					dataStore.addTeam(selectCountry, teamName);
					cleenTeamJList();
					for(Country i : dataStore.getCountry()){
						if(i.getName().equals(selectCountry)){
							int tempCount = 0;
							String[] teamList = new String[i.getTeam().size()];
							for(Team j : i.getTeam()){
								teamList[tempCount++] = j.getName();
							}
							
							teamJList.setListData(teamList);
							updateUI();
							break;
						}
					}
				}
				break;
			}
			else{
				JOptionPane.showMessageDialog(null, "請選擇國家");
			}
			break;
		case"-隊伍":
			if(selectCountry != null && selectTeam!= null){
				int result = JOptionPane.showConfirmDialog(null, "確定要刪除\"" + selectTeam + "\"嗎？" , "警告",JOptionPane.YES_NO_OPTION);
				if(result == 0){
					dataStore.deleteTeam(selectCountry, selectTeam);
					for(Country i : dataStore.getCountry()){
						if(i.getName().equals(selectCountry)){
							String[] teamList = new String[i.getTeam().size()];
							int tempCount = 0;
							for(Team j : i.getTeam()){
								teamList[tempCount++] = j.getName();
							}
							teamJList.setListData(teamList);
							cleenAthleteJList();
							updateUI();
						}
					}
					
				}
			}
			else if(selectCountry == null){
				JOptionPane.showMessageDialog(null, "請選擇國家");
			}
			else{
				JOptionPane.showMessageDialog(null, "請選擇隊伍");
			}
			break;
		case"+選手":
			if(selectCountry != null && selectTeam!= null){
				JFrame athleteInformationFrame = new AthleteInformationFrame(dataStore, selectCountry, selectTeam
						, new Athlete("", "", 0, selectTeam, 0, 0, selectCountry, null, null)
						, true);
				athleteInformationFrame.addWindowListener(this);
			}
			else if(selectCountry == null){
				JOptionPane.showMessageDialog(null, "請選擇國家");
			}
			else{
				JOptionPane.showMessageDialog(null, "請選擇隊伍");
			}
			break;
		case"-選手":
			if(selectCountry != null && selectTeam != null && selectAthlete != null){
				TreeSet<Athlete> athleteSet = new TreeSet<Athlete>();
				Team refTeam = dataStore.getReference(selectCountry, selectTeam);
				athleteSet = refTeam.getAthlete();
				for(Athlete i : athleteSet){
					if(i.getName().equals(selectAthlete)){
						dataStore.deleteAthlete(selectCountry, selectTeam, i);
						break;
					}
				}
				String[] athleteArray = new String[athleteSet.size()];
				int athletetemp = 0;
				for(Athlete i :athleteSet){
					athleteArray[athletetemp++] = i.getName();
				}
				cleenAthleteJList();
				athleteJList.setListData(athleteArray);
			}
			else if(selectCountry == null){
				JOptionPane.showMessageDialog(null, "請選擇國家");
			}
			else if(selectTeam == null){
				JOptionPane.showMessageDialog(null, "請選擇隊伍");
			}
			else{
				JOptionPane.showMessageDialog(null, "請選擇選手");
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		TreeSet<Athlete> athleteSet = dataStore.getReference(countryJList.getSelectedValue(), teamJList.getSelectedValue()).getAthlete();
		String[] athleteArray = new String[athleteSet.size()];
		int athletetemp = 0;
		for(Athlete i :athleteSet){
			athleteArray[athletetemp++] = i.getName();
		}
		cleenAthleteJList();
		athleteJList.setListData(athleteArray);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
