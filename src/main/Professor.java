package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class Professor extends JFrame implements ActionListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Professor frame = new Professor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//SQL ����
	Connection con;// Ŭ���� booklist�� �����Ѵ�. java.sql�� Connection ��ü con�� �����Ѵ�.
	PreparedStatement psmt;
	Statement stmt,stmt1;
	ResultSet rs;
	String Driver="";
	String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
	String userid="madang";
	String pwd="madang";
	private JTextField torepair; // �ʿ������ �����
	private JTextField tofix;
	public void conDB() { 
	  try {
	    Class.forName("com.mysql.cj.jdbc.Driver");   
	    //System.out.println("����̹� �ε� ����");
	  } catch(ClassNotFoundException e1) {
	      e1.printStackTrace();
	  }
	try {
	    //System.out.println("�����ͺ��̽� ���� �غ�...");
	    con=DriverManager.getConnection(url, userid, pwd); 
	    System.out.println("���� CONNECTION SUCCESFUL\nREADY TO USE PROGRAM");
	  } catch(SQLException e1) {
	      e1.printStackTrace();
	    }
	}
		
	JButton btnOk1,btnOk2,btnOk3,btnOk4,btnOk5,btnOk6,btnOk7, btn_insert, btnReset,btnreset_table, btn_insert_data;
	JButton btn_insert_grade;
	JTextField txt_value1, txt_value2, txt_value3, txt_name;
	JTextArea txtResult, txtStatus;
	JPanel pn1, insert_pn;
	JButton btn,btn_proname;
	JButton backbtn;
	String pro_name=null;
	
	public Professor() {
		setTitle("�ŵ��� ������ - ���� ������");
		conDB();
		layInit();
	    setBounds(200, 200, 1000, 600); //������ġ,������ġ,���α���,���α���
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    backbtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbtn.setBackground(Color.gray);
		backbtn.setBounds(0, 0, 103, 23);
	}
	
	public void layInit() {
	      btnOk1 = new JButton("������ ����(�Է�)");
	      btnOk2 = new JButton("�����ϴ� �л�");
	      btnOk3 = new JButton("\"����\"�ϴ� �л�");
	      btnOk4 = new JButton("��� ���� ����");
	      btnOk5 = new JButton("�Ҽ� �а� ����");
	      btnOk6 = new JButton("�ð�ǥ");
	      btnOk7 = new JButton("����(�Է�)");
	      
	      
	      backbtn = new JButton("�� �ڷΰ���");
	      btn_insert = new JButton("insert");
	      btnreset_table=new JButton("table ����");
	      btnReset = new JButton("ȭ���ʱ�ȭ");
	      
	      txtResult = new JTextArea(); //���� ��
	      txtStatus = new JTextArea(5,30);
	      
	      pn1 = new JPanel();

	      txt_name = new JTextField(20);
	      txt_value1 = new JTextField(20);
	      txt_value2 = new JTextField(20);
	      txt_value3 = new JTextField(20);
	      btn_proname = new JButton("���� �̸� �Է�");
	      btn_insert_data = new JButton("����/�б� �Է�");
	      btn_insert_grade = new JButton("���� �Է�");
	      
	      
	      insert_pn = new JPanel();
	      insert_pn.setLayout(new GridLayout(15,5));
	      
	      insert_pn.add(new JLabel("�̸�(String"));
	      insert_pn.add(txt_name);
	      insert_pn.add(btn_proname);
	      insert_pn.add(new JLabel("����(INT)"));
	      insert_pn.add(txt_value1);
	      insert_pn.add(new JLabel("�б�(INT)"));
	      insert_pn.add(txt_value2);
	      insert_pn.add(btn_insert_data);
	      
	      insert_pn.add(new JLabel("����(Char)"));
	      insert_pn.add(txt_value3);
	      insert_pn.add(btn_insert_grade);
	      
	      add("East",insert_pn);

	      //�Է¹�ư �̺�Ʈ �߰�
	      btn_insert_data.addActionListener(this);
	      btn_insert_grade.addActionListener(this);
	      btn_proname.addActionListener(this);
	      
	      pn1.add(backbtn);
	      pn1.add(btnOk2);
	      pn1.add(btnOk3);
	      pn1.add(btnOk4);
	      pn1.add(btnOk5);
	      pn1.add(btnOk6);
	     
	      txtResult.setEditable(false);
	      txtStatus.setEditable(false);
	      JScrollPane scrollPane = new JScrollPane(txtResult);
	      JScrollPane scrollPane2 = new JScrollPane(txtStatus);
	      
	      add("North", pn1);
	      add("Center", scrollPane);
	      add("South", scrollPane2);
	      
	      
	      btnOk1.addActionListener(this);
	      btnOk2.addActionListener(this);
	      btnOk3.addActionListener(this);
	      btnOk4.addActionListener(this);
	      btnOk5.addActionListener(this);
	      btnOk6.addActionListener(this);
	      btnOk7.addActionListener(this);
	   }
	
	public void returnresult(){
		/*try {
			listarea.setText("ķ��īID \t ���� \t ������ȣ \t �����ο��� \t ����ȸ�� \t �������� \t ��������Ÿ� \t �뿩��� \tķ��ī������� \t �뿩ȸ��ID \n");
			stmt = con.createStatement();
			String query="SELECT * FROM rentcar_list;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"����\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
                listarea.append(str);
             }
		}catch(Exception e1) {
			System.out.println(e1);
		}*/ 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
	         stmt = con.createStatement();
	         
	         if (e.getSource() == btnOk1) {
	        	String query = "SELECT * FROM Book ";
	            txtResult.setText("");
	            txtResult.setText("BOOKNO           BOOK NAME       PUBLISHER      PRICE\n");
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4)
	                     + "\n";
	               txtResult.append(str);
	            }
	         }
	         else if (e.getSource() == btnOk2) {
	        	 String query = "SELECT * FROM orders ";
	             txtResult.setText("");
	             txtResult.setText("order_id           cust_id           book_id                   saleprice                 orderdate\n");
	             rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)+ "\t" + rs.getString(5)
	                      + "\n";
	                txtResult.append(str);
	             }
	          }
	         else if (e.getSource() == btnOk3) {
	        	 
	        	 if(pro_name == null) {
        			 JOptionPane.showMessageDialog(btn_insert_data,"���� �̸��� ���� �Է����ּ���!"); 
        		 } 
	        	 String query = "select p_name '��������', s_id, s_name '�л�', s_address, s_phone,\r\n"
	        	 		+ "s_email, Club_c_id, s_department, s_account\r\n"
	        	 		+ "from Professor, Student\r\n"
	        	 		+ "where Student.s_professor = Professor.p_name and p_name = \"" + pro_name + "\";";
	             txtResult.setText("");
	             txtResult.setText("���� ����	s_id	�л�	s_address	s_phone	s_email	Club_c_id	s_department	s_account\n");
	             rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)+ "\t" + rs.getInt(7)+ "\t" + rs.getString(8)+ "\t" + rs.getString(9)
	                      + "\n";
	                txtResult.append(str);
	             }
	          }
	         
	         else if (e.getSource() == btnOk5) {
	        	 
	        	 if(pro_name == null) {
        			 JOptionPane.showMessageDialog(btn_insert_data,"���� �̸��� ���� �Է����ּ���!"); 
        		 } 
	        	 String query = "select p_name, d_id, d_name, d_phone, d_office, d_chair, d_lecture\r\n"
	        	 		+ "from Professor, Department\r\n"
	        	 		+ "where Professor.department_d_id = department.d_id\r\n"
	        	 		+ "and Department.d_id =\r\n"
	        	 		+ "(select department_d_id from Professor where p_name = \""+ pro_name+ "\");;";
	             txtResult.setText("");
	             txtResult.setText("p_name	d_id	d_name	d_phone	d_office	d_chair	d_lecture\n");
	             rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)+ "\t" + rs.getInt(5)+ "\t" + rs.getString(6)+ "\t" + rs.getString(7)
	                      + "\n";
	                txtResult.append(str);
	             }
	          }
	         
	         else if (e.getSource() == btn_proname) {
	            pro_name=txt_name.getText();
	            JOptionPane.showMessageDialog(btn_proname, "�Է� �Ϸ�!");
	            //System.out.println(pro_name);
	         }
	         
	         
	         //�Է� �� ��ư Ŭ�� �� ����
	         else if(e.getSource() == btn_insert_data) {
	        	 try {
	        		 int year,semester;
	        		 
	        		 if(pro_name == null) {
	        			 JOptionPane.showMessageDialog(btn_insert_data,"���� �̸��� ���� �Է����ּ���!"); 
	        		 }
	        		 
	        		 year=Integer.parseInt(txt_value1.getText());
	        		 semester=Integer.parseInt(txt_value2.getText());
	 	        	
	        		 String query = "select p_id, p_name, year, semester, l_id, dcnum, l_name, l_day,\r\n"
	        		 		+ " l_period, credit, l_time, l_room, l_department\r\n"
	        		 		+ "from Professor, Student_has_Professor, Lecture\r\n"
	        		 		+ "where Professor.p_id = Student_has_Professor.Professor_p_id\r\n"
	        		 		+ "and Professor.p_id = Lecture.Professor_p_id\r\n"
	        		 		+ "and p_name = \"" + pro_name + "\" "
	        		 		+ "and year = "+ year+" and semester = " + semester +";";
		             txtResult.setText("");
		             txtResult.setText("p_id	p_name	year	semester	l_id	dcnum	l_name	l_day	l_period	credit	l_time	l_room	l_department\n");
		             rs = stmt.executeQuery(query);
		             while (rs.next()) {
		                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t" +rs.getInt(5) + "\t" +rs.getInt(6) + "\t" +rs.getString(7) + "\t" +rs.getString(8) + "\t" +rs.getInt(9) + "\t" +rs.getInt(10) + "\t" +rs.getInt(11) + "\t" +rs.getInt(12) + "\t" +rs.getString(13) + "\n";
		                txtResult.append(str);
		             }
	        		 
	        	 }
	        	 
	        	 catch(Exception e3){
	        		 System.out.println(e3);
	        		 JOptionPane.showMessageDialog(btn_insert_data, "�ùٸ� �������� �Է����ּ���!");
	        	 }
	         }
	         
	         else if(e.getSource() == btn_insert_grade) {
	        	 try {
	        	 if(pro_name == null) {
        			 JOptionPane.showMessageDialog(btn_insert_grade,"���� �̸��� ���� �Է����ּ���!"); 
        		 }
	        	 
	        	 String txt;
	        	 txt=txt_value3.getText();
	        	 String name;
	        	 char grade;
	        	 
	        	 name=txt.substring(0,3);
	        	 grade=txt.charAt(4);

	        	 String query = "UPDATE Course_History\r\n"
	        	 		+ "SET grade = '"+grade + "'\r\n"
	        	 		+ "where Course_History.Professor_p_id = \r\n"
	        	 		+ "(select p_id from Professor where p_name = \""+pro_name+"\")\r\n"
	        	 		+ "and Course_History.Student_s_id = \r\n"
	        	 		+ "(select s_id from Student where s_name = \""+name+"\");";
	        	 //System.out.println(query);
	        	 
	        	 stmt.executeUpdate(query);
	        	 
	        	 JOptionPane.showMessageDialog(btn_insert_data, "�Է¿Ϸ�");
	        	 }
	        	 catch(Exception e3) {
	        		 System.out.println(e3);
	        		 JOptionPane.showMessageDialog(btn_insert_grade,"����!!");
	        	 }

	        	 
	         }

	      } catch (Exception e2) {
	         System.out.println("���� �б� ���� :" + e2);
	 }  
	
	}
	
}
