package com.wlma.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.wlma.dao.DBConnection;

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
import java.awt.GridLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SettingUI extends JFrame {

	private ResultSet rs = null;
	private Connection conn = null;
	private Statement stat;

	private JPanel contentPane;

	private DefaultListModel<String> listModel = new DefaultListModel<String>();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 455);
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
		
		JPanel modelSettingPanel = new ModelSettingPanel();
		modelSettingPanel.setPreferredSize(new Dimension(600, 120));
		splitPane_1.setLeftComponent(modelSettingPanel);
//		JPanel panel_1 = new JPanel();
//		splitPane_1.setLeftComponent(panel_1);
//		panel_1.setLayout(new GridLayout(4, 11, 2, 2));
//		
//		JLabel lblNewLabel = new JLabel("New label");
//		panel_1.add(lblNewLabel);
//		
//		JLabel lblNewLabel_2 = new JLabel("New label");
//		panel_1.add(lblNewLabel_2);
//		
//		JLabel label_1 = new JLabel("New label");
//		panel_1.add(label_1);
//		
//		JLabel label = new JLabel("New label");
//		panel_1.add(label);
//		
//		JLabel label_7 = new JLabel("New label");
//		panel_1.add(label_7);
//		
//		JLabel label_6 = new JLabel("New label");
//		panel_1.add(label_6);
//		
//		JLabel label_5 = new JLabel("New label");
//		panel_1.add(label_5);
//		
//		JLabel label_4 = new JLabel("New label");
//		panel_1.add(label_4);
//		
//		JLabel label_3 = new JLabel("New label");
//		panel_1.add(label_3);
//		
//		JLabel label_2 = new JLabel("New label");
//		panel_1.add(label_2);
//		
//		JLabel lblNewLabel_3 = new JLabel("New label");
//		panel_1.add(lblNewLabel_3);
//		
//		JLabel lblNewLabel_1 = new JLabel("New label");
//		panel_1.add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_4 = new JLabel("New label");
//		panel_1.add(lblNewLabel_4);
//		
//		JLabel lblNewLabel_5 = new JLabel("New label");
//		panel_1.add(lblNewLabel_5);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("\u9009\u62E9Excel\u6A21\u7248");
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(5, 5));
		
		JList listExcelModel = new JList(listModel);
		listExcelModel.getSize().setSize(100, 599);
		listExcelModel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6A21\u7248\u5217\u8868\uFF1A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(listExcelModel, BorderLayout.CENTER);
		
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
		init();
	}

	private void init() {
		conn = new DBConnection().getConnection();
		String sql = "";
		String sModelName = "";
		try {
			stat = conn.createStatement();
			sql = "select * from ModelInfo where ModelType like 'excel%'";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				sModelName = rs.getString("ModelName");
				listModel.addElement(sModelName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
