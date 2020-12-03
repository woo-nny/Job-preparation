import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	private void getConnection() {
		try {

			// 1.����̹� �ε�(���� �ε�)
			// �� �κп� �����Ϸ��� �ö����� �����Ϸ��� ��� DB�� ����̹��� ������ ��
			// Class.forName�� �ڵ带 ������ ���� �Ű������� ��η� ����̹��� ã�ư�
			// �������� �߻��� �� �ִ� 1.��ΰ� �߸��� �� ���� 2.����̹�Ŭ������ �ȳ־��� ��
			// �� ��� �Ѵ� ����̹� Ŭ������ ã�� ���ϴ� ��찡 �߻���
			// �׷��� ���� ó���� �ʿ���

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.�����ͺ��̽��� ����(Connection ��������)
			// ����̹��Ŵ����� ���� Connection��ü�� ������ �� �ִ�.
			// Connection ��ü�� Java ���ϰ� DB�� ��������ִ� ��ü�̴�.
			// ����̹� �Ŵ����� ���� Connection��ü�� �������� ���ؼ���
			// DB�� ��ġ�� ���, DB ���� id,pw 3������ ������ �ʿ��ϴ�.
			String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";
			// Connection ��ü�� ���� �� �� url, id, pw�� Ʋ���� ��찡 �����Ѵ�
			// �׿� ���� ����ó���� ����� �Ѵ�.

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

	public int join(MemberVO vo) {
		int cnt = 0;
		getConnection();

		try {

			String sql = "insert into login values(sc_num.nextval,?,?,?,?,?)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getJob());
//			psmt.setString(5, vo.getTest_list());
			psmt.setInt(5, vo.getTest_num());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public String login(MemberVO vo) {

		String name = null;

		getConnection();

		try {

			String sql = "select name from login where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			rs = psmt.executeQuery();
			// ResultSet�� Ŀ���� �� ó���� ������
			// �Ʒ��� �������ٴ� �� ���� �ִٴ� ��
			// �Ʒ��� ������ �� �ִٴ� ���� �˷��� rs.next();
			if (rs.next()) {
				name = rs.getString(1);
			}

			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return name;
	}

}
