package com.wlma.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.wlma.dao.DBConnectionFactory;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class ExcelTablePanel extends JPanel {
	private JTable table;
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement ps;

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

	private Vector<Vector<Object>> tableData = new Vector<>();
	private Map<String, InputStream> photo = new HashMap<String, InputStream>();
	/**
	 * Create the panel.
	 */
	public ExcelTablePanel() {
//		setLayout(new BorderLayout(0, 0));
		super(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null, null},
//				{null, null, null, null, null, null},
//				{null, null, null, null, null, null},
//				{null, null, null, null, null, null},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column", "New column"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
		scrollPane.setViewportView(table);

		//Set up column sizes.
//        initColumnSizes(table);
        
        JPanel panel_1 = new JPanel();
        scrollPane.setRowHeaderView(panel_1);
        
        JLabel lblNewLabel = new JLabel("New label");
        panel_1.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        panel_1.add(lblNewLabel_1);
        
        JPanel panel_2 = new JPanel();
        scrollPane.setColumnHeaderView(panel_2);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
        panel_2.add(chckbxNewCheckBox);
	}

	private void init() {
		String sSqlCard = "";
		String sSqlSetting = "";
		try {
			conn = DBConnectionFactory.getConnection();
			sSqlCard = "select * from CardInfo where 1=1";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //获取数据库连接
//		PreparedStatement ps = conn.prepareStatement(pSql);
	}
	public void fillData(Connection conn, String sID) {
		this.conn = conn;
		String sSqlCard = "select * from CardInfo where 1=1";
		String sSqlSetting = "select * from ModelInfo where id = '" + sID + "'";
		ResultSet rsCard = null;
		ResultSet rsSetting = null;
		try {
			PreparedStatement psCard = conn.prepareStatement(sSqlCard);
//			PreparedStatement psSetting = conn.prepareStatement(sSqlSetting);
			rsCard = psCard.executeQuery();
//			rsSetting = psSetting.executeQuery();
//			if (rsSetting.next()) {
//			}
			while (rsCard.next()) {
				Vector<Object> line = new Vector<>();
				String cardNo = rsCard.getString("cardNo");
				//InputStream in = rs.getBinaryStream("Photo");
				System.out.println(cardNo);
				//System.out.println(in);
				line.add(false);
				line.add(rsCard.getString("cardName"));
				line.add(cardNo);
				line.add(rsCard.getString("Sex"));
				line.add(rsCard.getString("Folk"));
				line.add(rsCard.getString("Birthday"));
				line.add(rsCard.getString("Address"));
				line.add(rsCard.getString("NewAddress"));
				line.add(rsCard.getString("IssueOrgan"));
				line.add(rsCard.getString("availabilityBegin"));
				line.add(rsCard.getString("availabilityEnd"));
				line.add(rsCard.getString("controlNum"));
				//
				//photo.put(cardNo, in);
				//
				tableData.add(line);
				
			}
			table.setModel(new MyTableModel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    class MyTableModel extends DefaultTableModel {
        
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
//        Object[][] datas = null;
//        //= { { a++, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Object[] colums = {"是否导出", "姓名", "身份证号", "性别", "民族", "出生日期", "住址", "新住址", "签发机关", "有效期起", "有效期止", "读卡时间"};
        Vector<Object> columns = new Vector<>(Arrays.asList(colums));
        
        Class[] columnTypes = new Class[] {
        		Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
        public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
        public MyTableModel() {
        	this.setDataVector(tableData, columns);
        }
    }
}
