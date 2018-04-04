package com.wlma.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.wlma.dao.DBConnection;

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


	private Map columnIndexMap = null;
	private Map rowIndexMap = null;
	private Connection conn = null;
	private String sID = null;
	
	
	private JComboBox writeTypeComboBox;
	private JLabel photoLabel;

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
	private JLabel label;
	private JComboBox startLineComboBox;
	private JLabel label_1;
	private JLabel label_2;
	private JCheckBox isTtitleCheckBox;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JButton saveButton;
	/**
	 * Create the panel.
	 */
	public ModelSettingPanel() {
		setLayout(new GridLayout(6, 12, 5, 5));
		
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
		
		label = new JLabel("\u8D77\u59CB\u884C\u53F7");
		add(label);
		
		startLineComboBox = new JComboBox();
		setRowComboxItem(startLineComboBox);
		add(startLineComboBox);
		
		label_1 = new JLabel("");
		add(label_1);
		
		label_2 = new JLabel("\u662F\u5426\u5BFC\u8868\u5934");
		add(label_2);
		
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
		
		saveButton = new JButton("\u4FDD\u5B58");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveData();
			}
		});
		add(saveButton);
		
		label_8 = new JLabel("");
		add(label_8);

		switchWriteType((String) writeTypeComboBox.getSelectedItem());
	}


	public void fillData(Connection conn, String sID) {
		this.conn = conn;
		this.sID = sID;
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
				//居民身份证号
				cardNoTitleTextField.setText(rs.getString("CardNoTitle"));
				cardNoRowComboBox.setSelectedIndex(rs.getInt("CardNoRow"));
				cardNoColumnComboBox.setSelectedIndex(rs.getInt("CardNoColumn"));
				cardNoExportCheckBox.setSelected(rs.getBoolean("CardNoExport"));
				//姓名
				nameTitleTextField.setText(rs.getString("nameTitle"));
				nameRowComboBox.setSelectedIndex(rs.getInt("nameRow"));
				nameColumnComboBox.setSelectedIndex(rs.getInt("nameColumn"));
				nameExportCheckBox.setSelected(rs.getBoolean("nameExport"));
				//性别
				sexTitleTextField.setText(rs.getString("sexTitle"));
				sexRowComboBox.setSelectedIndex(rs.getInt("sexRow"));
				sexColumnComboBox.setSelectedIndex(rs.getInt("sexColumn"));
				sexExportCheckBox.setSelected(rs.getBoolean("sexExport"));
				//民族
				folkTitleTextField.setText(rs.getString("folkTitle"));
				folkRowComboBox.setSelectedIndex(rs.getInt("folkRow"));
				folkColumnComboBox.setSelectedIndex(rs.getInt("folkColumn"));
				folkExportCheckBox.setSelected(rs.getBoolean("folkExport"));
				//出生日期
				birthdayTitleTextField.setText(rs.getString("birthdayTitle"));
				birthdayRowComboBox.setSelectedIndex(rs.getInt("birthdayRow"));
				birthdayColumnComboBox.setSelectedIndex(rs.getInt("birthdayColumn"));
				birthdayExportCheckBox.setSelected(rs.getBoolean("birthdayExport"));
				//地址
				addressTitleTextField.setText(rs.getString("addressTitle"));
				addressRowComboBox.setSelectedIndex(rs.getInt("addressRow"));
				addressColumnComboBox.setSelectedIndex(rs.getInt("addressColumn"));
				addressExportCheckBox.setSelected(rs.getBoolean("addressExport"));
				//新地址
				newAddressTitleTextField.setText(rs.getString("newAddressTitle"));
				newAddressRowComboBox.setSelectedIndex(rs.getInt("newAddressRow"));
				newAddressColumnComboBox.setSelectedIndex(rs.getInt("newAddressColumn"));
				newAddressExportCheckBox.setSelected(rs.getBoolean("newAddressExport"));
				//签发机构
				issueOrganTitleTextField.setText(rs.getString("issueOrganTitle"));
				issueOrganRowComboBox.setSelectedIndex(rs.getInt("issueOrganRow"));
				issueOrganColumnComboBox.setSelectedIndex(rs.getInt("issueOrganColumn"));
				issueOrganExportCheckBox.setSelected(rs.getBoolean("issueOrganExport"));
				//有效期起
				beginTitleTextField.setText(rs.getString("beginDateTitle"));
				beginRowComboBox.setSelectedIndex(rs.getInt("beginDateRow"));
				beginColumnComboBox.setSelectedIndex(rs.getInt("beginDateColumn"));
				beginExportCheckBox.setSelected(rs.getBoolean("beginDateExport"));
				//有效期止
				endTitleTextField.setText(rs.getString("endDateTitle"));
				endRowComboBox.setSelectedIndex(rs.getInt("endDateRow"));
				endColumnComboBox.setSelectedIndex(rs.getInt("endDateColumn"));
				endExportCheckBox.setSelected(rs.getBoolean("endDateExport"));
				//照片
				photoTitleTextField.setText(rs.getString("photoTitle"));
				photoRowComboBox.setSelectedIndex(rs.getInt("photoRow"));
				photoColumnComboBox.setSelectedIndex(rs.getInt("photoColumn"));
				photoExportCheckBox.setSelected(rs.getBoolean("photoExport"));
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveData() {
		
		sWriteType = (String) writeTypeComboBox.getSelectedItem();
		iStartLine = startLineComboBox.getSelectedIndex();
		bExportTitle = isTtitleCheckBox.isSelected();
		//居民身份证号
		sCardNoTitle = cardNoTitleTextField.getText().trim();
		iCardNoRow = cardNoRowComboBox.getSelectedIndex();
		iCardNoColumn = cardNoColumnComboBox.getSelectedIndex();
		bCardNoExport = cardNoExportCheckBox.isSelected();
		//姓名
		sNameTitle = nameTitleTextField.getText().trim();
		iNameRow = nameRowComboBox.getSelectedIndex();
		iNameColumn = nameColumnComboBox.getSelectedIndex();
		bNameExport = nameExportCheckBox.isSelected();
		//性别
		sSexTitle = sexTitleTextField.getText().trim();
		iSexRow = sexRowComboBox.getSelectedIndex();
		iSexColumn = sexColumnComboBox.getSelectedIndex();
		bSexExport = sexExportCheckBox.isSelected();
		//民族
		sFolkTitle = folkTitleTextField.getText().trim();
		iFolkRow = folkRowComboBox.getSelectedIndex();
		iFolkColumn = folkColumnComboBox.getSelectedIndex();
		bFolkExport = folkExportCheckBox.isSelected();
		//出生日期
		sBirthdayTitle = birthdayTitleTextField.getText().trim();
		iBirthdayRow = birthdayRowComboBox.getSelectedIndex();
		iBirthdayColumn = birthdayColumnComboBox.getSelectedIndex();
		bBirthdayExport = birthdayExportCheckBox.isSelected();
		//地址
		sAddressTitle = addressTitleTextField.getText().trim();
		iAddressRow = addressRowComboBox.getSelectedIndex();
		iAddressColumn = addressColumnComboBox.getSelectedIndex();
		bAddressExport = addressExportCheckBox.isSelected();
		//新地址
		sNewAddressTitle = newAddressTitleTextField.getText().trim();
		iNewAddressRow = newAddressRowComboBox.getSelectedIndex();
		iNewAddressColumn = newAddressColumnComboBox.getSelectedIndex();
		bNewAddressExport = newAddressExportCheckBox.isSelected();
		//签发机构
		sIssueOrganTitle = issueOrganTitleTextField.getText().trim();
		iIssueOrganRow = issueOrganRowComboBox.getSelectedIndex();
		iIssueOrganColumn = issueOrganColumnComboBox.getSelectedIndex();
		bIssueOrganExport = issueOrganExportCheckBox.isSelected();
		//有效期起
		sBeginDateTitle = beginTitleTextField.getText().trim();
		iBeginDateRow = beginRowComboBox.getSelectedIndex();
		iBeginDateColumn = beginColumnComboBox.getSelectedIndex();
		bBeginDateExport = beginExportCheckBox.isSelected();
		//有效期止
		sEndDateTitle = endTitleTextField.getText().trim();
		iEndDateRow = endRowComboBox.getSelectedIndex();
		iEndDateColumn = endColumnComboBox.getSelectedIndex();
		bEndDateExport = endExportCheckBox.isSelected();
		//照片
		sPhotoTitle = photoTitleTextField.getText().trim();
		iPhotoRow = photoRowComboBox.getSelectedIndex();
		iPhotoColumn = photoColumnComboBox.getSelectedIndex();
		bPhotoExport = photoExportCheckBox.isSelected();
		//
		String pSql = " update ModelInfo" +
						" set WriteType = ?, StartLine = ?, ExportTitle = ?," +
						" CardNoTitle = ?, CardNoRow = ?, CardNoColumn = ?, CardNoExport = ?," +
						" NameTitle = ?, NameRow = ?, NameColumn = ?, NameExport = ?," +
						" SexTitle = ?, SexRow = ?, SexColumn = ?, SexExport = ?," +
						" FolkTitle = ?, FolkRow = ?, FolkColumn = ?, FolkExport = ?," +
						" BirthdayTitle = ?, BirthdayRow = ?, BirthdayColumn = ?, BirthdayExport = ?," +
						" AddressTitle = ?, AddressRow = ?, AddressColumn = ?, AddressExport = ?," +
						" NewAddressTitle = ?, NewAddressRow = ?, NewAddressColumn = ?, NewAddressExport = ?," +
						" IssueOrganTitle = ?, IssueOrganRow = ?, IssueOrganColumn = ?, IssueOrganExport = ?," +
						" BeginDateTitle = ?, BeginDateRow = ?, BeginDateColumn = ?, BeginDateExport = ?," +
						" EndDateTitle = ?, EndDateRow = ?, EndDateColumn = ?, EndDateExport = ?," +
						" PhotoTitle = ?, PhotoRow = ?, PhotoColumn = ?, PhotoExport = ?" +
					" where ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(pSql);
			int count = 1;
			ps.setString(count ++, sWriteType);
			ps.setInt(count ++, iStartLine);
			ps.setBoolean(count ++, bExportTitle);
			
			ps.setString(count ++, sCardNoTitle);
			ps.setInt(count ++, iCardNoRow);
			ps.setInt(count ++, iCardNoColumn);
			ps.setBoolean(count ++, bCardNoExport);

			ps.setString(count ++, sNameTitle);
			ps.setInt(count ++, iNameRow);
			ps.setInt(count ++, iNameColumn);
			ps.setBoolean(count ++, bNameExport);

			ps.setString(count ++, sSexTitle);
			ps.setInt(count ++, iSexRow);
			ps.setInt(count ++, iSexColumn);
			ps.setBoolean(count ++, bSexExport);

			ps.setString(count ++, sFolkTitle);
			ps.setInt(count ++, iFolkRow);
			ps.setInt(count ++, iFolkColumn);
			ps.setBoolean(count ++, bFolkExport);

			ps.setString(count ++, sBirthdayTitle);
			ps.setInt(count ++, iBirthdayRow);
			ps.setInt(count ++, iBirthdayColumn);
			ps.setBoolean(count ++, bBirthdayExport);

			ps.setString(count ++, sAddressTitle);
			ps.setInt(count ++, iAddressRow);
			ps.setInt(count ++, iAddressColumn);
			ps.setBoolean(count ++, bAddressExport);

			ps.setString(count ++, sNewAddressTitle);
			ps.setInt(count ++, iNewAddressRow);
			ps.setInt(count ++, iNewAddressColumn);
			ps.setBoolean(count ++, bNewAddressExport);

			ps.setString(count ++, sIssueOrganTitle);
			ps.setInt(count ++, iIssueOrganRow);
			ps.setInt(count ++, iIssueOrganColumn);
			ps.setBoolean(count ++, bIssueOrganExport);

			ps.setString(count ++, sBeginDateTitle);
			ps.setInt(count ++, iBeginDateRow);
			ps.setInt(count ++, iBeginDateColumn);
			ps.setBoolean(count ++, bBeginDateExport);

			ps.setString(count ++, sEndDateTitle);
			ps.setInt(count ++, iEndDateRow);
			ps.setInt(count ++, iEndDateColumn);
			ps.setBoolean(count ++, bEndDateExport);

			ps.setString(count ++, sPhotoTitle);
			ps.setInt(count ++, iPhotoRow);
			ps.setInt(count ++, iPhotoColumn);
			ps.setBoolean(count ++, bPhotoExport);
			
			ps.setString(count ++, this.sID);
			ps.execute();
			JOptionPane.showMessageDialog(this, "配置保存成功" , null, JOptionPane.INFORMATION_MESSAGE);
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "配置保存失败，原因：" + e.getMessage() , null, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
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
	
	private void switchWriteType(String writeType) {
		if ("详情".equals(writeType)) {
			//不可用
			startLineComboBox.setEnabled(false);
			isTtitleCheckBox.setEnabled(false);
			//可用
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
			//可用
			startLineComboBox.setEnabled(true);
			isTtitleCheckBox.setEnabled(true);
			//不可用
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
