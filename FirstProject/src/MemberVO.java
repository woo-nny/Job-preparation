import java.util.Locale;

public class MemberVO {

	private String id_number;
	private String id;
	private String pw;
	private String name;
	private String job;
	private String test_list;
	private int test_num;

	public int getTest_num() {
		return test_num;
	}

	public void setTest_num(int test_num) {
		this.test_num = test_num;
	}

	public String getTest_list() {
		return test_list;
	}

	public void setTest_list(String test_list) {
		this.test_list = test_list;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public MemberVO(String id, String pw, String name, String job, String test_list) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.job = job;
		this.test_list = test_list;

	}

	public MemberVO(String id, String pw, String name, String job, int test_num) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.job = job;
		this.test_num = test_num;

	}

	public MemberVO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

	}

}
