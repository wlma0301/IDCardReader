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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;

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
	private JTextField photoTitleTextField;

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
	private JComboBox beginRowComboBox;
	private JComboBox beginColumnComboBox;
	private JComboBox endRowComboBox;
	private JComboBox endColumnComboBox;
	private JComboBox photoRowComboBox;
	private JComboBox photoColumnComboBox;


	Map columnIndexMap = null;
	Map rowIndexMap = null;
	private JComboBox writeTypeComboBox;
	private JLabel label_1;
	private JLabel label_isTitle;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_2;
	private JLabel label_8;
	private JLabel photoLabel;
	
	private JLabel label_startline;
	private JComboBox startLineComboBox;
	private JCheckBox isTtitleCheckBox;

	private JCheckBox nameExportCheckBox;
	private JCheckBox cardNoExportCheckBox;
	private JCheckBox sexExportCheckBox;
	private JCheckBox folkExportCheckBox;
	private JCheckBox birthdayExportCheckBox;
	private JCheckBox addressExportCheckBox;
	private JCheckBox newAddressExportCheckBox;
	private JCheckBox issueOrganExportCheckBox;
	private JCheckBox beginExportCheckBox;
	private JCheckBox endExportCheckBox;
	private JCheckBox photoExportCheckBox;
	
	private String sModelType, sWriteType, sModelName, sModelPath;
	private int iStartLine;
	private boolean bIsInuse, bExportTitle;
	private String sCardNoTitle, sNameTitle, sSexTitle, sFolkTitle, sBirthdayTitle, sAddressTitle,
		sNewAddressTitle, sIssueOrganTitle, sBeginDateTitle, sEndDateTitle, sPhotoTitle;
	private int iCardNoRow, iCardNoColumn, iNameRow, iNameColumn, iSexRow, iSexColumn, iFolkRow, iFolkColumn, iBirthdayRow, iBirthdayColumn,
		iAddressRow, iAddressColumn, iNewAddressRow, iNewAddressColumn, iIssueOrganRow, iIssueOrganColumn, iBeginDateRow, iBeginDateColumn,
		iEndDateRow, iEndDateColumn, iPhotoRow, iPhotoColumn;
	private boolean bCardNoExport, bNameExport, bSexExport, bFolkExport, bBirthdayExport, bAddressExport,
		bNewAddressExport, bIssueOrganExport, bBeginDateExport, bEndDateExport, bPhotoExport;
	/**
	 * Create the panel.
	 */
	public ModelSettingPanel() {
		setLayout(new GridLayout(6, 12, 3, 3));
		
		writeTypeComboBox = new JComboBox();
		writeTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5217\u8868", "\u8BE6\u60C5"}));
		writeTypeComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String sWriteType = (String) writeTypeComboBox.getSelectedItem();
					switchWriteType(sWriteType);
				}
				
			}
		});
		add(writeTypeComboBox);
		
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
		
		photoLabel = new JLabel("\u7167\u7247");
		add(photoLabel);
		
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
		
		photoTitleTextField = new JTextField();
		photoTitleTextField.setText("\u7167\u7247");
		photoTitleTextField.setColumns(10);
		add(photoTitleTextField);
		
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
		
		beginRowComboBox = new JComboBox();
		setRowComboxItem(beginRowComboBox);
		beginRowComboBox.setSelectedIndex(3);
		add(beginRowComboBox);
		
		endRowComboBox = new JComboBox();
		setRowComboxItem(endRowComboBox);
		endRowComboBox.setSelectedIndex(3);
		add(endRowComboBox);
		
		photoRowComboBox = new JComboBox();
		setRowComboxItem(photoRowComboBox);
		photoRowComboBox.setSelectedIndex(3);
		add(photoRowComboBox);
		
		label_startline = new JLabel("\u8D77\u59CB\u884C\u53F7");
		add(label_startline);
		
		startLineComboBox = new JComboBox();
		setRowComboxItem(startLineComboBox);
		add(startLineComboBox);
		
		label_1 = new JLabel("");
		add(label_1);
		
		label_isTitle = new JLabel("\u662F\u5426\u5BFC\u8868\u5934");
		add(label_isTitle);
		
		isTtitleCheckBox = new JCheckBox("");
		isTtitleCheckBox.setSelected(true);
		isTtitleCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(isTtitleCheckBox);
		
		label_3 = new JLabel("");
		add(label_3);
		
		label_4 = new JLabel("");
		add(label_4);
		
		label_5 = new JLabel("");
		add(label_5);
		
		label_6 = new JLabel("");
		add(label_6);
		
		label_7 = new JLabel("");
		add(label_7);
		
		label_2 = new JLabel("");
		add(label_2);
		
		label_8 = new JLabel("");
		add(label_8);
		
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
		
		photoColumnComboBox = new JComboBox();
		setColumnComboxItem(photoColumnComboBox);
		photoColumnComboBox.setSelectedIndex(3);
		add(photoColumnComboBox);
		
		JLabel label_isexport = new JLabel("\u662F\u5426\u5BFC\u51FA");
		add(label_isexport);
		
		nameExportCheckBox = new JCheckBox("");
		nameExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(nameExportCheckBox);
		
		cardNoExportCheckBox = new JCheckBox("");
		cardNoExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(cardNoExportCheckBox);
		
		sexExportCheckBox = new JCheckBox("");
		sexExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(sexExportCheckBox);
		
		folkExportCheckBox = new JCheckBox("");
		folkExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(folkExportCheckBox);
		
		birthdayExportCheckBox = new JCheckBox("");
		birthdayExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(birthdayExportCheckBox);
		
		addressExportCheckBox = new JCheckBox("");
		addressExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(addressExportCheckBox);
		
		newAddressExportCheckBox = new JCheckBox("");
		newAddressExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(newAddressExportCheckBox);
		
		issueOrganExportCheckBox = new JCheckBox("");
		issueOrganExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(issueOrganExportCheckBox);
		
		beginExportCheckBox = new JCheckBox("");
		beginExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(beginExportCheckBox);
		
		endExportCheckBox = new JCheckBox("");
		endExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(endExportCheckBox);
		
		photoExportCheckBox = new JCheckBox("");
		photoExportCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(photoExportCheckBox);

		switchWriteType((String) writeTypeComboBox.getSelectedItem());
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

	public void fillData(Connection conn, String sID) {
		ResultSet rs = null;
		Statement stat;
		String sql = "select * from ModelInfo where id = '" + sID + "'";
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				writeTypeComboBox.setSelectedItem(rs.getString("WriteType"));
				startLineComboBox.setSelectedIndex(rs.getInt("StartLine"));
				isTtitleCheckBox.setSelected(rs.getBoolean("ExportTitle"));
				//�������֤��
				cardNoTitleTextField.setText(rs.getString("CardNoTitle"));
				cardNoRowComboBox.setSelectedIndex(rs.getInt("CardNoRow"));
				cardNoColumnComboBox.setSelectedIndex(rs.getInt("CardNoColumn"));
				cardNoExportCheckBox.setSelected(rs.getBoolean("CardNoExport"));
				//����
				nameTitleTextField.setText(rs.getString("nameTitle"));
				nameRowComboBox.setSelectedIndex(rs.getInt("nameRow"));
				nameColumnComboBox.setSelectedIndex(rs.getInt("nameColumn"));
				nameExportCheckBox.setSelected(rs.getBoolean("nameExport"));
				//�Ա�
				sexTitleTextField.setText(rs.getString("sexTitle"));
				sexRowComboBox.setSelectedIndex(rs.getInt("sexRow"));
				sexColumnComboBox.setSelectedIndex(rs.getInt("sexColumn"));
				sexExportCheckBox.setSelected(rs.getBoolean("sexExport"));
				//����
				folkTitleTextField.setText(rs.getString("folkTitle"));
				folkRowComboBox.setSelectedIndex(rs.getInt("folkRow"));
				folkColumnComboBox.setSelectedIndex(rs.getInt("folkColumn"));
				folkExportCheckBox.setSelected(rs.getBoolean("folkExport"));
				//��������
				birthdayTitleTextField.setText(rs.getString("birthdayTitle"));
				birthdayRowComboBox.setSelectedIndex(rs.getInt("birthdayRow"));
				birthdayColumnComboBox.setSelectedIndex(rs.getInt("birthdayColumn"));
				birthdayExportCheckBox.setSelected(rs.getBoolean("birthdayExport"));
				//��ַ
				addressTitleTextField.setText(rs.getString("addressTitle"));
				addressRowComboBox.setSelectedIndex(rs.getInt("addressRow"));
				addressColumnComboBox.setSelectedIndex(rs.getInt("addressColumn"));
				addressExportCheckBox.setSelected(rs.getBoolean("addressExport"));
				//�µ�ַ
				newAddressTitleTextField.setText(rs.getString("newAddressTitle"));
				newAddressRowComboBox.setSelectedIndex(rs.getInt("newAddressRow"));
				newAddressColumnComboBox.setSelectedIndex(rs.getInt("newAddressColumn"));
				newAddressExportCheckBox.setSelected(rs.getBoolean("newAddressExport"));
				//ǩ������
				issueOrganTitleTextField.setText(rs.getString("issueOrganTitle"));
				issueOrganRowComboBox.setSelectedIndex(rs.getInt("issueOrganRow"));
				issueOrganColumnComboBox.setSelectedIndex(rs.getInt("issueOrganColumn"));
				issueOrganExportCheckBox.setSelected(rs.getBoolean("issueOrganExport"));
				//��Ч����
				beginTitleTextField.setText(rs.getString("beginDateTitle"));
				beginRowComboBox.setSelectedIndex(rs.getInt("beginDateRow"));
				beginColumnComboBox.setSelectedIndex(rs.getInt("beginDateColumn"));
				beginExportCheckBox.setSelected(rs.getBoolean("beginDateExport"));
				//��Ч��ֹ
				endTitleTextField.setText(rs.getString("endDateTitle"));
				endRowComboBox.setSelectedIndex(rs.getInt("endDateRow"));
				endColumnComboBox.setSelectedIndex(rs.getInt("endDateColumn"));
				endExportCheckBox.setSelected(rs.getBoolean("endDateExport"));
				//��Ƭ
				photoTitleTextField.setText(rs.getString("photoTitle"));
				photoRowComboBox.setSelectedIndex(rs.getInt("photoRow"));
				photoColumnComboBox.setSelectedIndex(rs.getInt("photoColumn"));
				photoExportCheckBox.setSelected(rs.getBoolean("photoExport"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	private void switchWriteType(String writeType) {
		if ("����".equals(writeType)) {
			//������
			startLineComboBox.setEnabled(false);
			isTtitleCheckBox.setEnabled(false);
			//����
			nameRowComboBox.setEnabled(true);
			cardNoRowComboBox.setEnabled(true);
			sexRowComboBox.setEnabled(true);
			folkRowComboBox.setEnabled(true);
			birthdayRowComboBox.setEnabled(true);
			addressRowComboBox.setEnabled(true);
			newAddressRowComboBox.setEnabled(true);
			issueOrganRowComboBox.setEnabled(true);
			beginRowComboBox.setEnabled(true);
			endRowComboBox.setEnabled(true);
			photoRowComboBox.setEnabled(true);
		} else {
			//����
			startLineComboBox.setEnabled(true);
			isTtitleCheckBox.setEnabled(true);
			//������
			nameRowComboBox.setEnabled(false);
			cardNoRowComboBox.setEnabled(false);
			sexRowComboBox.setEnabled(false);
			folkRowComboBox.setEnabled(false);
			birthdayRowComboBox.setEnabled(false);
			addressRowComboBox.setEnabled(false);
			newAddressRowComboBox.setEnabled(false);
			issueOrganRowComboBox.setEnabled(false);
			beginRowComboBox.setEnabled(false);
			endRowComboBox.setEnabled(false);
			photoRowComboBox.setEnabled(false);
		}
	}
}
