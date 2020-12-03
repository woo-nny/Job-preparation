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

			// 1.드라이버 로딩(동적 로딩)
			// 이 부분에 컴파일러가 올때까지 컴파일러는 어떠한 DB의 드라이버를 쓰는지 모름
			// Class.forName의 코드를 만나는 순간 매개변수의 경로로 드라이버를 찾아감
			// 문제점이 발생할 수 있다 1.경로가 잘못될 수 있음 2.드라이버클래스를 안넣었을 때
			// 위 경우 둘다 드라이버 클래스를 찾지 못하는 경우가 발생함
			// 그래서 예외 처리가 필요함

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.데이터베이스와 연결(Connection 가져오기)
			// 드라이버매니저를 통해 Connection객체를 가져올 수 있다.
			// Connection 객체는 Java 파일과 DB를 연결시켜주는 객체이다.
			// 드라이버 매니저를 통해 Connection객체를 가져오기 위해서는
			// DB가 위치한 경로, DB 접근 id,pw 3가지의 정보가 필요하다.
			String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";
			// Connection 객체를 가져 올 때 url, id, pw가 틀렸을 경우가 존재한다
			// 그에 따른 예외처리를 해줘야 한다.

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
			// ResultSet은 커서가 맨 처음에 존재함
			// 아래로 내려간다는 건 값이 있다는 뜻
			// 아래로 내려갈 수 있다는 것을 알려줌 rs.next();
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
