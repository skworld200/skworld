package entryPoint;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import constants.Constants;
import factory.SearchFactory;
import service.Search;

public class UserScreen extends JFrame implements ActionListener {

	private JTextField tfSearchStr=null;
	private JTextArea taSearchResult=null;
	private JTextField tfElapsedTime=null;

	public static void main(String[] args) {
		UserScreen userScreen = new UserScreen();
		userScreen.getScreen();
	}

	private void getScreen() {
		//Features of the main Frame
		setLayout(new GridLayout(4,1));
		setSize(500,500); 
		setResizable(false);
		setTitle("Search Engine"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Panels to support every section
		JPanel plSearchStr = new JPanel();				
		JPanel plSearchButton = new JPanel();
		JPanel plSearchResult = new JPanel();
		JPanel plElapsedTime = new JPanel();		

		//User can provide the search string here
		JLabel lbSearchStr = new JLabel("Enter the search term: ");		
		tfSearchStr = new JTextField(20);
		JButton butClear = new JButton("Clear");
		butClear.addActionListener(this);
		plSearchStr.add(lbSearchStr);
		plSearchStr.add(tfSearchStr);
		plSearchStr.add(butClear);

		//Search methods
		JLabel lbSearchMethod = new JLabel("Search Method: ");	
		JButton butStringSearch = new JButton("String Match"); 
		JButton butRegExpSearch = new JButton("Regular Expression"); 
		JButton butIndexedSearch = new JButton("Indexed"); 

		butStringSearch.addActionListener(this);
		butRegExpSearch.addActionListener(this);
		butIndexedSearch.addActionListener(this);

		plSearchButton.add(lbSearchMethod);
		plSearchButton.add(butStringSearch);
		plSearchButton.add(butRegExpSearch);
		plSearchButton.add(butIndexedSearch);

		//Search results
		taSearchResult = new JTextArea(300,40);		
		taSearchResult.setEnabled(false);
		taSearchResult.setBackground(Color.black);
		plSearchResult.add(taSearchResult);

		tfElapsedTime = new JTextField(25);
		tfElapsedTime.setEnabled(false);
		tfElapsedTime.setBackground(Color.black);
		plElapsedTime.add(tfElapsedTime);

		add(plSearchStr);
		add(plSearchButton);
		add(plSearchResult);
		add(plElapsedTime);

		setLocationRelativeTo(null);  
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		String userSearchStr=null;

		switch(e.getActionCommand()){	
		case "Clear":
			tfSearchStr.setText(null);
			break;
		case Constants.STRING_SEARCH:
			userSearchStr = this.tfSearchStr.getText();				
			clearResults();
			callSearch(userSearchStr, Constants.STRING_SEARCH);
			break;
		case Constants.REG_EXPR_SEARCH:
			userSearchStr = this.tfSearchStr.getText();		
			clearResults();
			callSearch(userSearchStr, Constants.REG_EXPR_SEARCH);
			break;
		case Constants.INDEXED_SEARCH:
			userSearchStr = this.tfSearchStr.getText();		
			clearResults();
			callSearch(userSearchStr, Constants.INDEXED_SEARCH);
			break;
		}
	}	

	private void clearResults() {
		taSearchResult.setText(null);
		tfElapsedTime.setText(null);
	}

	private void callSearch(String userSearchStr, String searchMethod) {
		if(null == userSearchStr || "".equals(userSearchStr)) {
			JOptionPane.showMessageDialog(null, 
					"Please Provide the Search term", 
					"", 
					JOptionPane.WARNING_MESSAGE);
		}else {
			long start_time=System.currentTimeMillis();			
			SearchFactory factory = new SearchFactory();
			String searchType = searchMethod;
			Search search = factory.getSearch(searchType);
			search.getSearchResult(userSearchStr, Pattern.compile(userSearchStr).matcher(""));			
			taSearchResult.setText(search.print().toString());
			tfElapsedTime.setText(search.printEndTime(start_time).toString());
		}

	}
}
