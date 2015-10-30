package cn.anthony.boot.doman;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PHONEHEAD")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PhoneHead {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, precision = 10)
	private long id;
	@Column(nullable = false, length = 6)
	private String province;
	@Column(nullable = false, length = 10)
	private String city;
	@Column(nullable = false, length = 3)
	private String postcode;
	@Column(nullable = false, length = 7)
	//@Index(name = "phoneHead_idx") //注释是因为hibernate不会去创建索引，需要手动到数据库内创建索引
	private String head;

	public PhoneHead() {

	}

	public PhoneHead(String province, String city, String postcode, String head) {
		super();
		this.province = province;
		this.city = city;
		this.postcode = postcode;
		this.head = head;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

}
