package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NewsDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/spring4fs";

	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "spring4", "Spring4!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<NewsVO> listNews() {
		connect();
		ArrayList<NewsVO> list = new ArrayList<>();
		String sql = "select * from news order by id";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				NewsVO news = new NewsVO();
				news.setId(rs.getInt("id"));
				news.setBody(rs.getString("body"));
				news.setReadcnt(rs.getInt("readcnt"));
				news.setRegdate(rs.getString("regdate"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				list.add(news);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			disconnect();
		}
		return list;
	}

	public void UpdateNews(NewsVO newsVO) {
		System.out.println("sql start");
		connect();
		String sql = "update news set writer = ?, title = ?, body= ?  where id = ?";
		//String sql2 = "select sysdate from dual";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newsVO.getWriter());
			pstmt.setString(2, newsVO.getTitle());
			pstmt.setString(3, newsVO.getBody());
			pstmt.setInt(4, newsVO.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			disconnect();
		}
	}

	public NewsVO ReadNews(int id, int control) {
		connect();
		NewsVO news = null;
		String sql = "select * from news where id = ?;";
		String sql_plus = "update news set readcnt = readcnt + 1 where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				news = new NewsVO();
				news.setId(rs.getInt("id"));
				news.setBody(rs.getString("body"));
				news.setReadcnt(rs.getInt("readcnt"));
				news.setRegdate(rs.getString("regdate"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
			}
			rs.close();
			if (control == 1) {
				pstmt = conn.prepareStatement(sql_plus);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			disconnect();
		}
		return news;
	}
}