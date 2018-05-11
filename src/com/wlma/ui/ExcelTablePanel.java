package com.wlma.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.wlma.dao.DBConnectionFactory;
import com.wlma.export.excel.Excel2003Write;
import com.wlma.export.excel.XCell;
import com.wlma.export.excel.XRow;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JLabel photeLable;
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

	private Map<String, Object> settingMap = null;
	private Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
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
		
		JButton exportAsButton = new JButton("\u5BFC\u51FA\u65B0\u6587\u4EF6");
		exportAsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportData();
			}
		});
		panel.add(exportAsButton);
		
		
		JButton exportButton = new JButton("\u5BFC\u51FA\u5230\u6587\u4EF6");
		panel.add(exportButton);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){  
			  
//            @Override  
//            public void valueChanged(ListSelectionEvent e)  
//            {  
//                int row = table.getSelectedRow();  
//                if(row != -1)  
//                {  
//                    XXXModel model = (XXXModel)tbEmpList.getModel();  
//                }  
//            }

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();  
				if (row != -1) {  
                  XXXModel model = (XXXModel)tbEmpList.getModel();
				}  
			}  
        });  
		scrollPane.setViewportView(table);

		//Set up column sizes.
//        initColumnSizes(table);
        
        JPanel panel_1 = new JPanel();
        scrollPane.setRowHeaderView(panel_1);
        
        photeLable = new JLabel("New label");
        panel_1.add(photeLable);
        
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
	public void fillData(Connection conn, String sID, Map settingMap) {
		this.conn = conn;
		this.settingMap = settingMap;
		String sSqlCard = "select * from CardInfo where 1=1";
//		String sSqlSetting = "select * from ModelInfo where id = '" + sID + "'";
		ResultSet rsCard = null;
//		ResultSet rsSetting = null;
		try {
			PreparedStatement psCard = conn.prepareStatement(sSqlCard);
//			PreparedStatement psSetting = conn.prepareStatement(sSqlSetting);
			rsCard = psCard.executeQuery();
//			rsSetting = psSetting.executeQuery();
//			if (rsSetting.next()) {
				
//			}
			int index  //TODO 显示图片 put按序列号？
			while (rsCard.next()) {
				Vector<Object> line = new Vector<Object>();
				String cardNo = rsCard.getString("cardNo");
				InputStream in = rsCard.getBinaryStream("Photo");
				System.out.println(cardNo);
				System.out.println(in);
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
				photo.put(cardNo, in);
				//
				tableData.add(line);
				
			}
			table.setModel(new MyTableModel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void exportData() {
		boolean isExport = false;
		Map<Integer, XRow> xRows = new HashMap<Integer, XRow>();
		int index = 1;
		XCell xCell = null;
//		for (int i = 0; i < table.getRowCount(); i++) {
//			isExport = table.row
//		}
		for (Vector<Object> row : tableData) {
			isExport = (Boolean) row.get(0);
			System.out.println(isExport);
			if (isExport) {
				xCell = null;
				XRow xRow = new XRow(index);
				//姓名
				System.out.println(settingMap.get("nameExport"));
				if ((Boolean) settingMap.get("nameExport")) {
					xRow.addXCell(new XCell(0, (Integer) settingMap.get("nameRow"), (Integer) settingMap.get("nameColumn"), row.get(1)));
				}
				//身份证号
				if ((Boolean) settingMap.get("CardNoExport")) {
					xRow.addXCell(new XCell(1, (Integer) settingMap.get("CardNoRow"), (Integer) settingMap.get("CardNoColumn"), row.get(2)));
				}
				//性别
				if ((Boolean) settingMap.get("sexExport")) {
					xRow.addXCell(new XCell(2, (Integer) settingMap.get("sexRow"), (Integer) settingMap.get("sexColumn"), row.get(3)));
				}
				//民族
				if ((Boolean) settingMap.get("folkExport")) {
					xRow.addXCell(new XCell(3, (Integer) settingMap.get("folkRow"), (Integer) settingMap.get("folkColumn"), row.get(4)));
				}
				//出生日期
				if ((Boolean) settingMap.get("birthdayExport")) {
					xRow.addXCell(new XCell(4, (Integer) settingMap.get("birthdayRow"), (Integer) settingMap.get("birthdayColumn"), row.get(5)));
				}
				//地址
				if ((Boolean) settingMap.get("addressExport")) {
					xRow.addXCell(new XCell(5, (Integer) settingMap.get("addressRow"), (Integer) settingMap.get("addressColumn"), row.get(6)));
				}
				//新地址
				if ((Boolean) settingMap.get("newAddressExport")) {
					xRow.addXCell(new XCell(6, (Integer) settingMap.get("newAddressRow"), (Integer) settingMap.get("newAddressColumn"), row.get(7)));
				}
				//签发机构
				if ((Boolean) settingMap.get("issueOrganExport")) {
					xRow.addXCell(new XCell(7, (Integer) settingMap.get("issueOrganRow"), (Integer) settingMap.get("issueOrganColumn"), row.get(8)));
				}
				//有效期起
				if ((Boolean) settingMap.get("beginDateExport")) {
					xRow.addXCell(new XCell(8, (Integer) settingMap.get("beginDateRow"), (Integer) settingMap.get("beginDateColumn"), row.get(9)));
				}
				//有效期止
				if ((Boolean) settingMap.get("endDateExport")) {
					xRow.addXCell(new XCell(9, (Integer) settingMap.get("endDateRow"), (Integer) settingMap.get("endDateColumn"), row.get(10)));
				}
				if ((Boolean) settingMap.get("photoExport")) {
					xCell = new XCell(4, 1, 7, photo.get(row.get(2)));
				}
				xRows.put(index, xRow);
				index ++;
			}
		}
		XRow[] xRowList = xRows.values().toArray(new XRow[0]);
		String filePath = "C:/币乎.xls";
		Excel2003Write excelWrite = new Excel2003Write(filePath, xRowList);
		excelWrite.writeList();
	}
	public void setSettingMap(Map settingMap) {
		this.settingMap = settingMap;
	}

    class MyTableModel extends DefaultTableModel {
        
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
//        Object[][] datas = null;
//        //= { { a++, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Object[] colums = {"是否导出", "姓名", "身份证号", "性别", "民族", "出生日期", "住址", "新住址", "签发机关", "有效期起", "有效期止", "读卡时间"};
        Vector<Object> columns = new Vector<Object>(Arrays.asList(colums));
        
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
