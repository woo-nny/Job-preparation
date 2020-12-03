
public class TestScoreVO {
	private int num;
	private String id_number;
	private String day_date;
	private String t_score;
	private String n_score;
	private String k_score;

	public TestScoreVO(String id_number, String day_date, String t_score, String n_score, String k_score) {
		super();
		this.id_number = id_number;
		this.day_date = day_date;
		this.t_score = t_score;
		this.n_score = n_score;
		this.k_score = k_score;
	}

	public TestScoreVO(String t_score, String day_date) {
		super();

		this.t_score = t_score;
		this.day_date = day_date;

	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDay_date() {
		return day_date;
	}

	public void setDay_date(String day_date) {
		this.day_date = day_date;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getN_score() {
		return n_score;
	}

	public void setN_score(String n_score) {
		this.n_score = n_score;
	}

	public String getK_score() {
		return k_score;
	}

	public void setK_score(String k_score) {
		this.k_score = k_score;
	}

	public String getT_score() {
		return t_score;
	}

	public void setT_score(String t_score) {
		this.t_score = t_score;
	}

}