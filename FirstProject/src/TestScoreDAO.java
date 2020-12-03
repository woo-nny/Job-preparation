import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestScoreDAO {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	private void getConnection() {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(db_url, db_id, db_pw);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

	private void close() {

		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int add(TestScoreVO vo) {
		int cnt = 0;
		getConnection();

		try {

			String sql = "insert into test values(?,?,?,?,?)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId_number());
			psmt.setString(2, vo.getDay_date());
			psmt.setString(3, vo.getT_score());
			psmt.setString(4, vo.getN_score());
			psmt.setString(5, vo.getK_score());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public ArrayList<TestScoreVO> select() {

		ArrayList<TestScoreVO> list = new ArrayList<TestScoreVO>();

		getConnection();

		try {

			String sql = "select t_score,day_date from test order by id_number";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String t_score = rs.getString(1);
				String day_date = rs.getString(2);

				TestScoreVO m = new TestScoreVO(t_score, day_date);

				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

}
