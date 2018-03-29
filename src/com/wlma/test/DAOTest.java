package com.wlma.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.wlma.dao.DBConnection;
import com.wlma.export.excel.Excel2003Write;
import com.wlma.export.excel.Excel2007Write;
import com.wlma.export.excel.XCell;
import com.wlma.export.excel.XRow;
import com.wlma.export.word.Word2003Write;



public class DAOTest {

	public static void main(String[] args) {
		ResultSet rs = null;
		Connection conn = new DBConnection().getConnection();
		Statement stat;
		Map<Integer, XRow> xRows = new HashMap<Integer, XRow>();
		try {
			stat = conn.createStatement();
			String sql = "select CardNo,cardName,birthday,sex,Photo from CardInfo";
			rs = stat.executeQuery(sql);
			InputStream in = null;
			int index = 1;
			XCell xCell = null;
			while (rs.next()) {
				xCell = null;
				XRow xRow = new XRow(index);
				xRow.addXCell(new XCell(0, 0, 3, rs.getString("Cardname")));
				xRow.addXCell(new XCell(1, 3, 4, rs.getString("cardName")));
				xRow.addXCell(new XCell(2, 5, 5, rs.getString("birthday")));
				xRow.addXCell(new XCell(3, 6, 6, rs.getString("sex")));
				in = rs.getBinaryStream("Photo");
				xCell = new XCell(4, 6, 5, in);
				xRows.put(index, xRow);
				index ++;
			}
			XRow[] xRowList = xRows.values().toArray(new XRow[0]);
			String filePath = "C:/±Òºõ.doc";
			//Excel2003Write excelWrite = new Excel2003Write(filePath, xRowList);
			Word2003Write excelWrite = new Word2003Write(filePath, xRowList);
			excelWrite.writeInfo();
			excelWrite.wirteImage(xCell);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}