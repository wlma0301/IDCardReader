package com.wlma.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.wlma.dao.DBConnection;
import com.wlma.dao.DBConnectionFactory;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SettingUI extends JFrame {

	private ResultSet rs = null;
	private Connection conn = null;
	private Statement stat;

	private JPanel contentPane;
	private JList listExcelModel;
	private ModelSettingPanel modelSettingPanel;

//	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private Map excelListMap = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingUI frame = new SettingUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SettingUI() {
		try {
			//当前系统风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1000, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane excelScrollPane = new JScrollPane();
		tabbedPane.addTab("Excel\u6A21\u7248\u7BA1\u7406", null, excelScrollPane, null);
		
		JSplitPane splitPane = new JSplitPane();
		excelScrollPane.setViewportView(splitPane);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		modelSettingPanel = new ModelSettingPanel();
		modelSettingPanel.setPreferredSize(new Dimension(750, 120));
		splitPane_1.setLeftComponent(modelSettingPanel);
		
		ExcelTablePanel tablePanel = new ExcelTablePanel();
		//tablePanel.setPreferredSize(new Dimension(600, 600));
		splitPane_1.setRightComponent(tablePanel);

		
		JPanel panel = new JPanel();
		panel.setToolTipText("\u9009\u62E9Excel\u6A21\u7248");
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(5, 5));
		
		listExcelModel = new JList();
		setExcelListItem(listExcelModel);
		//listExcelModel.getSize().setSize(100, 599);
		listExcelModel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6A21\u7248\u5217\u8868\uFF1A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listExcelModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					excelList_valueChanged();
				}
			}
		});
		panel.add(listExcelModel, BorderLayout.CENTER);
		//默认选中第一行
		listExcelModel.setSelectedIndex(0);
		excelList_valueChanged();
		
		JPanel panel_excelBtn = new JPanel();
		panel.add(panel_excelBtn, BorderLayout.SOUTH);
		
		JButton btnAddExcelModel = new JButton("\u65B0\u589E\u6A21\u7248");
		panel_excelBtn.add(btnAddExcelModel);
		
		JButton btnRemoveExcelModel = new JButton("\u5220\u9664\u6A21\u7248");
		panel_excelBtn.add(btnRemoveExcelModel);
		
		JScrollPane wordScrollPane = new JScrollPane();
		tabbedPane.addTab("Word\u6A21\u7248\u7BA1\u7406", null, wordScrollPane, null);
		
		JToolBar toolBarFoot = new JToolBar();
		contentPane.add(toolBarFoot, BorderLayout.SOUTH);
		
		JLabel labelStatus = new JLabel("\u8FD9\u4E2A\u662F\u72B6\u6001\u680F");
		toolBarFoot.add(labelStatus);
	}

	private void setExcelListItem(JList jList) {  
		excelListMap = new HashMap();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		String sql = "";
		String sModelName = "";
		String sID = "";
		sql = "select * from ModelInfo where ModelType like 'excel%'";
		try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				sID = rs.getString("ID");
				sModelName = rs.getString("ModelName");
				excelListMap.put(sID + sModelName, sID);
				listModel.addElement(sID + sModelName);
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     jList.setModel(listModel);
	}
	
	private void init() {
		try {
			conn = DBConnectionFactory.getConnection(); //获取数据库连接
			stat = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void excelList_valueChanged() {
		modelSettingPanel.fillData(conn, (String) excelListMap.get(listExcelModel.getSelectedValue()));
	}
}
