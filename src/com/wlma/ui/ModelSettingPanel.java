package com.wlma.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ModelSettingPanel extends JPanel {

	private JTextField nameTitleTextField;
	private JTextField cardNoTitleTextField;
	private JTextField sexTitleTextField;
	private JTextField newAddressTitleTextField;
	private JTextField folkTitleTextField;
	private JTextField birthdayTitleTextField;
	private JTextField addressTitleTextField;
	private JTextField issueOrganTitleTextField;
	private JTextField beginTitleTextField;
	private JTextField endTitleTextField;

	private JComboBox nameRowComboBox;
	private JComboBox nameColumnComboBox;
	private JComboBox cardNoRowComboBox;
	private JComboBox cardNoColumnComboBox;
	private JComboBox sexRowComboBox;
	private JComboBox sexColumnComboBox;
	private JComboBox folkRowComboBox;
	private JComboBox folkColumnComboBox;
	private JComboBox birthdayRowComboBox;
	private JComboBox birthdayColumnComboBox;
	private JComboBox addressRowComboBox;
	private JComboBox addressColumnComboBox;
	private JComboBox newAddressRowComboBox;
	private JComboBox newAddressColumnComboBox;
	private JComboBox issueOrganRowComboBox;
	private JComboBox issueOrganColumnComboBox;
	private JComboBox begnRowComboBox;
	private JComboBox beginColumnComboBox;
	private JComboBox endRowComboBox;
	private JComboBox endColumnComboBox;

	Map columnIndexMap = null;
	Map rowIndexMap = null;

	/**
	 * Create the panel.
	 */
	public ModelSettingPanel() {
		setLayout(new GridLayout(5, 11, 3, 3));
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("row   :" + rowIndexMap.get(nameRowComboBox.getSelectedItem()));
				System.out.println("column:" + columnIndexMap.get(nameColumnComboBox.getSelectedItem()));
			}
		});
		add(btnNewButton);
		
		JLabel nameLabel = new JLabel("\u59D3\u540D");
		add(nameLabel);
		
		JLabel cardNoLabel = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		add(cardNoLabel);
		
		JLabel sexLabel = new JLabel("\u6027\u522B");
		add(sexLabel);
		
		JLabel folkLabel = new JLabel("\u6C11\u65CF");
		add(folkLabel);
		
		JLabel birthdayLabel = new JLabel("\u51FA\u751F\u65E5\u671F");
		add(birthdayLabel);
		
		JLabel addressLabel = new JLabel("\u4F4F\u5740");
		add(addressLabel);
		
		JLabel newAddressLabel = new JLabel("\u65B0\u4F4F\u5740");
		add(newAddressLabel);
		
		JLabel issueOrganLabel = new JLabel("\u7B7E\u53D1\u673A\u5173");
		add(issueOrganLabel);
		
		JLabel beginLabel = new JLabel("\u6709\u6548\u671F\u8D77");
		add(beginLabel);
		
		JLabel endLabel = new JLabel("\u6709\u6548\u671F\u6B62");
		add(endLabel);
		
		JLabel label_title = new JLabel("\u8868\u5934\u540D");
		add(label_title);
		
		nameTitleTextField = new JTextField();
		nameTitleTextField.setText("\u59D3\u540D");
		add(nameTitleTextField);
		nameTitleTextField.setColumns(10);
		
		cardNoTitleTextField = new JTextField();
		cardNoTitleTextField.setText("\u8EAB\u4EFD\u8BC1\u53F7");
		cardNoTitleTextField.setColumns(10);
		add(cardNoTitleTextField);
		
		sexTitleTextField = new JTextField();
		sexTitleTextField.setText("\u6027\u522B");
		sexTitleTextField.setColumns(10);
		add(sexTitleTextField);
		
		folkTitleTextField = new JTextField();
		folkTitleTextField.setText("\u6C11\u65CF");
		folkTitleTextField.setColumns(10);
		add(folkTitleTextField);
		
		birthdayTitleTextField = new JTextField();
		birthdayTitleTextField.setText("\u51FA\u751F\u65E5\u671F");
		birthdayTitleTextField.setColumns(10);
		add(birthdayTitleTextField);
		
		addressTitleTextField = new JTextField();
		addressTitleTextField.setText("\u4F4F\u5740");
		addressTitleTextField.setColumns(10);
		add(addressTitleTextField);
		
		newAddressTitleTextField = new JTextField();
		newAddressTitleTextField.setText("\u65B0\u4F4F\u5740");
		newAddressTitleTextField.setColumns(10);
		add(newAddressTitleTextField);
		
		issueOrganTitleTextField = new JTextField();
		issueOrganTitleTextField.setText("\u7B7E\u53D1\u673A\u5173");
		issueOrganTitleTextField.setColumns(10);
		add(issueOrganTitleTextField);
		
		beginTitleTextField = new JTextField();
		beginTitleTextField.setText("\u6709\u6548\u671F\u8D77");
		beginTitleTextField.setColumns(10);
		add(beginTitleTextField);
		
		endTitleTextField = new JTextField();
		endTitleTextField.setText("\u6709\u6548\u671F\u6B62");
		endTitleTextField.setColumns(10);
		add(endTitleTextField);
		
		JLabel label_row = new JLabel("\u586B\u5145\u884C\u53F7");
		add(label_row);
		
		nameRowComboBox = new JComboBox();
		setRowComboxItem(nameRowComboBox);
		nameRowComboBox.setSelectedIndex(3);
		add(nameRowComboBox);
		
		cardNoRowComboBox = new JComboBox();
		setRowComboxItem(cardNoRowComboBox);
		cardNoRowComboBox.setSelectedIndex(3);
		add(cardNoRowComboBox);
		
		sexRowComboBox = new JComboBox();
		setRowComboxItem(sexRowComboBox);
		sexRowComboBox.setSelectedIndex(3);
		add(sexRowComboBox);
		
		folkRowComboBox = new JComboBox();
		setRowComboxItem(folkRowComboBox);
		folkRowComboBox.setSelectedIndex(3);
		add(folkRowComboBox);
		
		birthdayRowComboBox = new JComboBox();
		setRowComboxItem(birthdayRowComboBox);
		birthdayRowComboBox.setSelectedIndex(3);
		add(birthdayRowComboBox);
		
		addressRowComboBox = new JComboBox();
		setRowComboxItem(addressRowComboBox);
		addressRowComboBox.setSelectedIndex(3);
		add(addressRowComboBox);
		
		newAddressRowComboBox = new JComboBox();
		setRowComboxItem(newAddressRowComboBox);
		newAddressRowComboBox.setSelectedIndex(3);
		add(newAddressRowComboBox);
		
		issueOrganRowComboBox = new JComboBox();
		setRowComboxItem(issueOrganRowComboBox);
		issueOrganRowComboBox.setSelectedIndex(3);
		add(issueOrganRowComboBox);
		
		begnRowComboBox = new JComboBox();
		setRowComboxItem(begnRowComboBox);
		begnRowComboBox.setSelectedIndex(3);
		add(begnRowComboBox);
		
		endRowComboBox = new JComboBox();
		setRowComboxItem(endRowComboBox);
		endRowComboBox.setSelectedIndex(3);
		add(endRowComboBox);
		
		JLabel label_column = new JLabel("\u586B\u5145\u5217\u53F7");
		add(label_column);
		
		nameColumnComboBox = new JComboBox();
		setColumnComboxItem(nameColumnComboBox);
		nameColumnComboBox.setSelectedIndex(2);
		add(nameColumnComboBox);
		
		cardNoColumnComboBox = new JComboBox();
		setColumnComboxItem(cardNoColumnComboBox);
		cardNoColumnComboBox.setSelectedIndex(2);
		add(cardNoColumnComboBox);
		
		sexColumnComboBox = new JComboBox();
		setColumnComboxItem(sexColumnComboBox);
		sexColumnComboBox.setSelectedIndex(3);
		add(sexColumnComboBox);
		
		folkColumnComboBox = new JComboBox();
		setColumnComboxItem(folkColumnComboBox);
		folkColumnComboBox.setSelectedIndex(3);
		add(folkColumnComboBox);
		
		birthdayColumnComboBox = new JComboBox();
		setColumnComboxItem(birthdayColumnComboBox);
		birthdayColumnComboBox.setSelectedIndex(3);
		add(birthdayColumnComboBox);
		
		addressColumnComboBox = new JComboBox();
		setColumnComboxItem(addressColumnComboBox);
		addressColumnComboBox.setSelectedIndex(3);
		add(addressColumnComboBox);
		
		newAddressColumnComboBox = new JComboBox();
		setColumnComboxItem(newAddressColumnComboBox);
		newAddressColumnComboBox.setSelectedIndex(3);
		add(newAddressColumnComboBox);
		
		issueOrganColumnComboBox = new JComboBox();
		setColumnComboxItem(issueOrganColumnComboBox);
		issueOrganColumnComboBox.setSelectedIndex(3);
		add(issueOrganColumnComboBox);
		
		beginColumnComboBox = new JComboBox();
		setColumnComboxItem(beginColumnComboBox);
		beginColumnComboBox.setSelectedIndex(3);
		add(beginColumnComboBox);
		
		endColumnComboBox = new JComboBox();
		setColumnComboxItem(endColumnComboBox);
		endColumnComboBox.setSelectedIndex(3);
		add(endColumnComboBox);
		
		JLabel label_isexport = new JLabel("\u662F\u5426\u5BFC\u51FA");
		add(label_isexport);
		
		JCheckBox nameExportCheckBox = new JCheckBox("");
		nameExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(nameExportCheckBox);
		
		JCheckBox cardNoExportCheckBox = new JCheckBox("");
		cardNoExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(cardNoExportCheckBox);
		
		JCheckBox sexExportCheckBox = new JCheckBox("");
		sexExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(sexExportCheckBox);
		
		JCheckBox folkExportCheckBox = new JCheckBox("");
		folkExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(folkExportCheckBox);
		
		JCheckBox birtthdayExportCheckBox = new JCheckBox("");
		birtthdayExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(birtthdayExportCheckBox);
		
		JCheckBox addressExportCheckBox = new JCheckBox("");
		addressExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(addressExportCheckBox);
		
		JCheckBox newAddressExportCheckBox = new JCheckBox("");
		newAddressExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(newAddressExportCheckBox);
		
		JCheckBox issueOrganExportCheckBox = new JCheckBox("");
		issueOrganExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(issueOrganExportCheckBox);
		
		JCheckBox beginExportCheckBox = new JCheckBox("");
		beginExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(beginExportCheckBox);
		
		JCheckBox endExportCheckBox = new JCheckBox("");
		endExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(endExportCheckBox);

	}

	private void setColumnComboxItem(JComboBox comboBox) {  
	     columnIndexMap = new HashMap();
	     columnIndexMap.put("A", 0);
	     columnIndexMap.put("B", 1);
	     columnIndexMap.put("C", 2);
	     columnIndexMap.put("D", 3);
	     columnIndexMap.put("E", 4);
	     columnIndexMap.put("F", 5);
	     columnIndexMap.put("G", 6);
	     columnIndexMap.put("H", 7);
	     columnIndexMap.put("I", 8);
	     columnIndexMap.put("J", 9);
	     columnIndexMap.put("K", 10);
	     columnIndexMap.put("L", 11);
	     columnIndexMap.put("M", 12);
	     columnIndexMap.put("N", 13);
	     columnIndexMap.put("O", 14);
	     columnIndexMap.put("P", 15);
	     columnIndexMap.put("Q", 16);
	     columnIndexMap.put("R", 17);
	     columnIndexMap.put("S", 18);
	     columnIndexMap.put("T", 19);
	     columnIndexMap.put("U", 20);
	     columnIndexMap.put("V", 21);
	     columnIndexMap.put("W", 22);
	     columnIndexMap.put("X", 23);
	     columnIndexMap.put("Y", 24);
	     columnIndexMap.put("Z", 25);
	     columnIndexMap.put("AA", 26);
	     columnIndexMap.put("AB", 27);
	     columnIndexMap.put("AC", 28);
	     columnIndexMap.put("AD", 29);
	     columnIndexMap.put("AE", 30);
	     columnIndexMap.put("AF", 31);
	     columnIndexMap.put("AG", 32);
	     Iterator<String> e = columnIndexMap.keySet().iterator();
	     while (e.hasNext()) {
	    	 comboBox.addItem(e.next());
	     }
	}

	private void setRowComboxItem(JComboBox comboBox) {  
	     rowIndexMap = new HashMap();
	     for (int i = 0; i < 40; i ++) {
	    	 rowIndexMap.put(i + 1, i);
	     }
	     Iterator<Integer> e = rowIndexMap.keySet().iterator();
	     while (e.hasNext()) {
	    	 comboBox.addItem(e.next());
	     }
	}
}
