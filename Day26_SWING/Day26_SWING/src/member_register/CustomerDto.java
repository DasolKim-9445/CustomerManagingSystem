package member_register;

import java.util.Vector;

import javax.swing.ButtonModel;

public class CustomerDto {

	private String name;
	private String tel;
	private String email;
	private String address;

	public CustomerDto() {
		this("", "", "", "");
	}

	public CustomerDto(String name, String tel, String email, String address) {

		this.name = name;
		this.tel = tel;
		this.email = email;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", tel=" + tel + ", email=" + email + ", address=" + address + "]";
	}

	public Vector<Object> getList() {
		Vector<Object> list = new Vector();
		list.add(name);
		list.add(tel);
		list.add(email);
		list.add(address);

		return list;
	}

}
