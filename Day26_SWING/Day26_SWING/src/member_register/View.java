package member_register;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import member_register.jdbc.JdbcUtil;

public class View extends JFrame implements ActionListener, MouseListener {

	CustomerDao dao = new CustomerDao(JdbcUtil.getConnection());

	JPanel contentPane;// ����
	JPanel typeInpane = new JPanel();// �����Է� �г�
	JPanel buttonPane = new JPanel(new FlowLayout(2, 10, 1));// ��ư �г�

	JLabel name = new JLabel("이름:");
	JLabel tel = new JLabel("전화번호:");
	JLabel email = new JLabel("이메일:");
	JLabel address = new JLabel("주소:");

	JTextField tfName = new JTextField(15);
	JTextField tfTel1 = new JTextField(4);
	JTextField tfTel2 = new JTextField(4);
	JTextField tfTel3 = new JTextField(4);
	JTextField tfEmail = new JTextField(14);
	JTextField tfAddress = new JTextField(15);

	JButton inputBtn = new JButton("추가");
	JButton outputBtn = new JButton("전체보기");
	JButton searchBtn = new JButton("검색");
	JButton modifyBtn = new JButton("수정");
	JButton deleteBtn = new JButton("삭제");
	JButton endBtn = new JButton("종료");
	JButton resetBtn = new JButton("Reset");
	JButton imageBtn = new JButton("IMG 업로드");

	JLabel imgLb = new JLabel();
	JFileChooser imgChooser = new JFileChooser();

	Vector<Vector> vector = new Vector<>();
	Vector<String> ColumnName = new Vector<>();

	DefaultTableModel model;
	JTable table;
	JScrollPane scrollpane;

	{
		ColumnName.add("이름");
		ColumnName.add("전화번호");
		ColumnName.add("이메일");
		ColumnName.add("주소");
	}
	
	// _____________________________________________________________
	public View() {
		setTitle("Contact List");
		setSize(900, 580);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = (JPanel) getContentPane();

		vector = dao.getStudentList(null);
		model = new DefaultTableModel(vector, ColumnName);// data를 만들고
		table = new JTable(model);//테이블 생성
		table.setAutoCreateRowSorter(true);// 테이블 정렬 기능 추가
	
		scrollpane = new JScrollPane(table);//1.데이터준비 2.테이블 넣기 3.스크롤팬에 넣기
		init();
		start();
		getDto();
	}

	public void init() {
		contentPane.setLayout(null);
		contentPane.add(scrollpane);
		scrollpane.setBounds(350, 5, 500, 400);
		scrollpane.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Information"));

		contentPane.add(typeInpane);
		typeInpane.setBounds(20, 5, 300, 460);
		typeInpane.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "입력사항"));

		typeInpane.setLayout(null);
		typeInpane.add(imgLb);
		imgLb.setBounds(30, 30, 240, 240);
		imgLb.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "사진"));

		imgLb.setIcon(new ImageIcon(
				new ImageIcon("default.JPG").getImage().getScaledInstance(230, 220, Image.SCALE_DEFAULT)));

		typeInpane.add(name);
		name.setBounds(30, 280, 180, 25);
		typeInpane.add(tfName);
		tfName.setBounds(90, 280, 180, 25);

		typeInpane.add(tel);
		tel.setBounds(30, 310, 180, 25);
		typeInpane.add(tfTel1);
		tfTel1.setBounds(90, 310, 55, 25);
		typeInpane.add(tfTel2);
		tfTel2.setBounds(150, 310, 57, 25);
		typeInpane.add(tfTel3);
		tfTel3.setBounds(215, 310, 55, 25);
		typeInpane.add(email);
		email.setBounds(30, 340, 180, 25);
		typeInpane.add(tfEmail);
		tfEmail.setBounds(90, 340, 180, 25);
		typeInpane.add(address);
		address.setBounds(30, 370, 180, 25);
		typeInpane.add(tfAddress);
		tfAddress.setBounds(90, 370, 180, 25);
		typeInpane.add(resetBtn);
		resetBtn.setBounds(90, 410, 70, 30);
		typeInpane.add(imageBtn);
		imageBtn.setBounds(170, 410, 100, 30);

		contentPane.add(buttonPane);
		buttonPane.setLocation(350, 409);
		buttonPane.setSize(500, 60);
		buttonPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "버튼"));

		buttonPane.add(inputBtn);
		buttonPane.add(outputBtn);
		buttonPane.add(searchBtn);
		buttonPane.add(modifyBtn);
		buttonPane.add(deleteBtn);
		buttonPane.add(endBtn);

		vector = dao.getStudentList(null);

	}////// end of init

	public void changeData() {
		Vector<CustomerDto> data = dao.selectAll();
		model.setDataVector(data, vector);
		model.fireTableDataChanged();
	}

	public CustomerDto getDto() {
		CustomerDto dto = new CustomerDto();

		String name = tfName.getText().trim();
		String tel = (tfTel1.getText().trim()) + "-" + (tfTel2.getText().trim()) + "-" + (tfTel3.getText().trim());
		String email = tfEmail.getText().trim();
		String address = tfAddress.getText().trim();

		dto.setName(name);
		dto.setTel(tel);
		dto.setEmail(email);
		dto.setAddress(address);
		return dto;
	}

	public void reset() {
		tfName.setText("");
		tfTel1.setText("");
		tfTel2.setText("");
		tfTel3.setText("");
		tfEmail.setText("");
		tfAddress.setText("");

		tfName.setEditable(true);
		tfTel1.setEditable(true);
		tfTel2.setEditable(true);
		tfTel3.setEditable(true);
		tfEmail.setEditable(true);
		tfAddress.setEditable(true);

		tfName.requestFocus();

	}

	public void start() {
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = table.getSelectedRow();

				String name = (String) table.getValueAt(row, 0).toString();
				String tel = (String) table.getValueAt(row, 1).toString();
				String email = (String) table.getValueAt(row, 2).toString();
				String address = (String) table.getValueAt(row, 3).toString();

				tfName.setText(name);
				String[] tels = tel.split("-");
				tfTel1.setText(tels[0]);
				tfTel2.setText(tels[1]);
				tfTel3.setText(tels[2]);
				tfEmail.setText(email);
				tfAddress.setText(address);

			}
		});

		inputBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("추가 버튼");
				String name = tfName.getText().trim();
				String tel = (tfTel1.getText().trim()) + "-" + (tfTel2.getText().trim()) + "-"
						+ (tfTel3.getText().trim());
				String email = tfEmail.getText().trim();
				String address = tfAddress.getText().trim();

				dao.insert(new CustomerDto(name, tel, email, address));

				model = new DefaultTableModel(dao.getStudentList(getDto()), ColumnName);
				table.setModel(model);
				reset();
			}
		});

		outputBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("출력버튼");
				// Vector<CustomerDto> list = dao.selectAll();
				// for (CustomerDto value : list) {
				// System.out.println(value);
				// }
				model = new DefaultTableModel(dao.getStudentList(getDto()), ColumnName);
				table.setModel(model);
				reset();
			}
		});

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("검색 버튼");

				String name = JOptionPane.showInputDialog("검색할 이름을 입력하세요.");

				CustomerDto dto = new CustomerDto(name, "", "", "");

				model = new DefaultTableModel(dao.selectOne(dto), ColumnName);
																			
				table.setModel(model);
				reset();
			}
		});

		modifyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("수정버튼");

				int row = table.getSelectedRow();

				String namefx = tfName.getText().trim();
				String telfx = (tfTel1.getText().trim()) + "-" + (tfTel2.getText().trim()) + "-"
						+ (tfTel3.getText().trim());
				String emailfx = tfEmail.getText().trim();
				String addressfx = tfAddress.getText().trim();

				CustomerDto dto = new CustomerDto(namefx, telfx, emailfx, addressfx);
				dao.update(dto);

				model = new DefaultTableModel(dao.getStudentList(getDto()), ColumnName);
				table.setModel(model);
				reset();

			}

		});

		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("삭제 버튼");
				String name = JOptionPane.showInputDialog("삭제할 이름을 입력하세요.");
				if (name == null) {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요.");
					return;
				}
				CustomerDto dto = new CustomerDto(name, "", "", "");

				int x = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);

				if (x == JOptionPane.OK_OPTION) {
					dao.delete(dto);
					model.fireTableDataChanged();
				} else {
					JOptionPane.showMessageDialog(null,"삭제를 취소하였습니다.");
				}
				model = new DefaultTableModel(dao.getStudentList(getDto()), ColumnName);
				table.setModel(model);
				reset();
			}
		});

		endBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("종료 버튼");

				dispose();
				System.exit(0);

			}
		});
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfName.setText("");
				tfTel1.setText("");
				tfTel2.setText("");
				tfTel3.setText("");
				tfEmail.setText("");
				tfAddress.setText("");

				tfName.setEditable(true);
				tfTel1.setEditable(true);
				tfTel2.setEditable(true);
				tfTel3.setEditable(true);
				tfEmail.setEditable(true);
				tfAddress.setEditable(true);

				tfName.requestFocus();

			}
		});
		imageBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF", "jpg", "gif");
				imgChooser.setFileFilter(filter);
				int ret = imgChooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String pathName = imgChooser.getSelectedFile().getPath();
				imgLb.setIcon(new ImageIcon(pathName));
			

			}
		});
	}
public static void main(String[] args) {
	new View().setVisible(true);
}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
	
}