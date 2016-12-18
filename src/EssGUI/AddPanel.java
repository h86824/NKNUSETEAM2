package EssGUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import EssIO.DataStore;
import EssObject.Athlete;
import EssObject.Country;
import EssObject.Team;

public class AddPanel extends JPanel{
	DataStore dataStore;
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	GridBagLayout gridBagLayout = new GridBagLayout();
	
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
		JLabel athleteJLabel = new JLabel("選手");
		this.add(counrtyJLabel, gridBagConstraints);
		gridBagLayout.setConstraints(counrtyJLabel, gridBagConstraints);
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 1;
		JButton addCountryButton = new JButton("+國家");
		JButton subCountryButton = new JButton("-國家");
		this.add(addCountryButton);
		gridBagLayout.setConstraints(addCountryButton, gridBagConstraints);
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
		JButton addTeamButton = new JButton("+球隊");
		JButton subTeamButton = new JButton("-球隊");
		this.add(addTeamButton);
		gridBagLayout.setConstraints(addTeamButton, gridBagConstraints);
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
		gridBagConstraints.gridwidth = 3;
		JButton addAthleteButton = new JButton("+選手");
		JButton subAthleteButton = new JButton("-選手");
		this.add(addAthleteButton);
		gridBagLayout.setConstraints(addAthleteButton, gridBagConstraints);
		gridBagConstraints.gridwidth = 0;
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
		
		JList<String> countryJList = new JList<String>(countryList);
		countryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		countryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		this.add(countryJList);
		gridBagLayout.setConstraints(countryJList, gridBagConstraints);
		
		JList<String> teamJList = new JList<String>();
		teamJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		teamJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		this.add(teamJList);
		gridBagLayout.setConstraints(teamJList, gridBagConstraints);
		
		JList<String> athleteJList = new JList<String>();
		athleteJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		athleteJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		this.add(athleteJList);
		gridBagConstraints.gridwidth = 0;
		gridBagLayout.setConstraints(athleteJList, gridBagConstraints);
		
		countryJList.addListSelectionListener(new ListSelectionListener(){
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				athleteJList.setListData(new String[1]);
				updateUI();
				System.out.println(countryJList.getSelectedValue());
				for(Country i : dataStore.getCountry()){
					if(i.getName().equals(countryJList.getSelectedValue())){
						String[] teamArray = new String[i.getTeam().size()];
						int count = 0;
						for(Team j : i.getTeam()){
							teamArray[count++] = j.getName();
						}
						teamJList.setListData(teamArray);
						updateUI();
					}
				}
				;
			}
			
		});
		
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
								athleteJList.setListData(athleteArray);
								updateUI();
							}
						}
					}
				}
			}
			
		});
		
	}
	
}
