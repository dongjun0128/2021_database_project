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

public class Student extends JFrame implements ActionListener {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//SQL 연결
	Connection con;// 클래스 booklist를 선언한다. java.sql의 Connection 객체 con을 선언한다.
	PreparedStatement psmt;
	Statement stmt,stmt1;
	ResultSet rs;
	ResultSet rs2;
	String Driver="";
	String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
	String userid="madang";
	String pwd="madang";
	private JTextField torepair; // 필요없으면 지우기
	private JTextField tofix;
	public void conDB() { 
	  try {
	    Class.forName("com.mysql.cj.jdbc.Driver");   
	    //System.out.println("드라이버 로드 성공");
	  } catch(ClassNotFoundException e1) {
	      e1.printStackTrace();
	  }
	try {
	    //System.out.println("데이터베이스 연결 준비...");
	    con=DriverManager.getConnection(url, userid, pwd); 
	    System.out.println("학생 CONNECTION SUCCESFUL\nREADY TO USE PROGRAM");
	  } catch(SQLException e1) {
	      e1.printStackTrace();
	    }
	}
	
	JButton btnOk1,btnOk2,btnOk3,btnOk4,btnOk5,btnOk6, btn_insert, btnReset,btnreset_table, btn_insert_data;
	JTextField txt_value1, txt_value2, txt_value3, txt_name;
	JTextArea txtResult, txtStatus;
	JPanel pn1, insert_pn;
	JButton btn;
	JButton backbtn,btn_stuname;
	String stu_name=null;
	int stu_id;
		
	public Student() {
		setTitle("신동준 이정규 - 학생 페이지");
		conDB();
		layInit();
	    setBounds(200, 200, 1000, 600); //가로위치,세로위치,가로길이,세로길이
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    backbtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbtn.setBackground(Color.gray);
		backbtn.setBounds(0, 0, 103, 23);
		
	}
	
	public void layInit() {
	      btnOk1 = new JButton("수강과목(입력)");
	      btnOk2 = new JButton("시간표");
	      btnOk3 = new JButton("동아리");
	      btnOk4 = new JButton("성적표");
	      backbtn = new JButton("《 뒤로가기");
	      btn_insert = new JButton("insert");
	      btnreset_table=new JButton("table 리셋");
	      btnReset = new JButton("화면초기화");
	      
	      txtResult = new JTextArea(); //수정 ㄴ
	      txtStatus = new JTextArea(5,30);
	      
	      pn1 = new JPanel();

	      
	      insert_pn = new JPanel();
	      insert_pn.setLayout(new GridLayout(10,5));
	      
	      txt_value1 = new JTextField(20);
	      txt_value2 = new JTextField(20);
	      txt_name=new JTextField(20);
	      btn_insert_data = new JButton("연도/학기 입력");
	      btn_stuname=new JButton("이름 입력");
	      
	      insert_pn.add(new JLabel("이름(String"));
	      insert_pn.add(txt_name);
	      insert_pn.add(btn_stuname);
	      
	      insert_pn.add(new JLabel("연도(INT)"));
	      insert_pn.add(txt_value1);
	      insert_pn.add(new JLabel("학기(INT)"));
	      insert_pn.add(txt_value2);
	      insert_pn.add(btn_insert_data);   
	      btn_insert_data.addActionListener(this);
	      btn_stuname.addActionListener(this);
		     
	      pn1.add(backbtn);
	      pn1.add(btnOk2);
	      pn1.add(btnOk3);
	      pn1.add(btnOk4);
	      

	      
	      txtResult.setEditable(false);
	      txtStatus.setEditable(false);
	      JScrollPane scrollPane = new JScrollPane(txtResult);
	      JScrollPane scrollPane2 = new JScrollPane(txtStatus);
	      
	      add("North", pn1);
	      add("Center", scrollPane);
	      add("South", scrollPane2);
	      add("East",insert_pn);

	      
	      btnOk2.addActionListener(this);
	      btnOk3.addActionListener(this);
	      btnOk4.addActionListener(this);
	      btn_insert.addActionListener(this);
	      btnReset.addActionListener(this);
	      btnreset_table.addActionListener(this);
	   }

	public void returnresult(){
		/*try {
			listarea.setText("캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n");
			stmt = con.createStatement();
			String query="SELECT * FROM rentcar_list;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"만원\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
                listarea.append(str);
             }
		}catch(Exception e1) {
			System.out.println(e1);
		}*/ 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btn_stuname) {
       	 try {
       		 stu_name=txt_name.getText();
       		 JOptionPane.showMessageDialog(btn_stuname, "입력 완료!");
       	 }
       	 
       	 catch(Exception e3){
       		 JOptionPane.showMessageDialog(btn_stuname, "올바른 형식으로 입력해주세요!");
       	 }
		}

		if(stu_name==null) JOptionPane.showMessageDialog(btn_insert_data, "이름을 먼저 입력해주세요");
		
		else {
	      try {
	         stmt = con.createStatement();
	         
	         
	          if (e.getSource() == btnOk2) {
	        	 String query = "select s_name, c_id, c_name, thenumberofstudent, c_chair, c_professor, c_room from Student, Club\r\n"
	        	 		+ "where Student.Club_c_id = Club.c_id;";
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
	        	 String query = "select * from Club";
	        	 rs = stmt.executeQuery(query);
	        	 int club_number=0;
	        	 while (rs.next()) {
	        		 club_number++;
	        	 }
	        		 
	        	 query = "select s_name, c_id, c_name, thenumberofstudent, c_chair, c_professor, c_room from Student, Club\r\n"
	        	 		+ "where Student.Club_c_id = Club.c_id and s_name = \""+ stu_name+ "\";";
	             txtResult.setText("");
	             txtResult.setText("s_name	c_id	c_name	numofstudent	c_chair	c_professor	c_room\n");
	             rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String s_name,c_chair;
	            	
	                s_name=rs.getString(1);
	            	 c_chair=rs.getString(5);
	            	String str = s_name + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t" + club_number + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getInt(7) +"\n";
	                txtResult.append(str);
	                //System.out.println("실행");
	                
	                
	             }
	             
	             
	             if(stu_name.equals("이정규")) {
	             txtResult.append("\nDB 동아리 회장 이정규\n");
	             
	             query="select c_id, c_name, s_id, s_name, s_address, s_phone, s_email, s_department, s_professor, s_account\r\n"
             			+ "from Student, Club\r\n"
             			+ "where Student.Club_c_id = Club.c_id\r\n"
             			+ "and c_id = " + 1 +" "
             			+ "order by c_id, s_id;";
             	
             	rs2=stmt.executeQuery(query);
             	txtResult.append("c_id	c_name	s_id	s_name	s_address	s_phone	s_email	s_department	s_professor	s_account\n");
	             
             	while (rs2.next()) {
             		String str2 = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getInt(3) + "\t" + rs2.getString(4)+ "\t" + rs2.getString(5) + "\t" + rs2.getString(6) + "\t" + rs2.getString(7) + "\t" + rs2.getString(8)+ "\t" + rs2.getString(9)+ "\t" + rs2.getString(10)
	                      + "\n";
             		txtResult.append(str2);
 	                
             	}
	            }
	             
	            if(stu_name.equals("신동준")) {
             	txtResult.append("\nC 동아리 회장 신동준\n");
	             
	            query="select c_id, c_name, s_id, s_name, s_address, s_phone, s_email, s_department, s_professor, s_account\r\n"
            			+ "from Student, Club\r\n"
            			+ "where Student.Club_c_id = Club.c_id\r\n"
            			+ "and c_id = " + 2 +" "
            			+ "order by c_id, s_id;";
            	
            	rs2=stmt.executeQuery(query);
            	txtResult.append("c_id	c_name	s_id	s_name	s_address	s_phone	s_email	s_department	s_professor	s_account\n");
	             
            	while (rs2.next()) {
            		String str2 = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getInt(3) + "\t" + rs2.getString(4)+ "\t" + rs2.getString(5) + "\t" + rs2.getString(6) + "\t" + rs2.getString(7) + "\t" + rs2.getString(8)+ "\t" + rs2.getString(9)+ "\t" + rs2.getString(10)
	                      + "\n";
            		txtResult.append(str2);
	                
            	}
	            }
	            
	            
	            if(stu_name.equals("원종서")) {
            	txtResult.append("\nFurier 동아리 회장 원종서\n");
	             
	             String query2="select c_id, c_name, s_id, s_name, s_address, s_phone, s_email, s_department, s_professor, s_account\r\n"
            			+ "from Student, Club\r\n"
            			+ "where Student.Club_c_id = Club.c_id\r\n"
            			+ "and c_id = " + 3 +" "
            			+ "order by c_id, s_id;";
            	
            	rs2=stmt.executeQuery(query2);
            	txtResult.append("c_id	c_name	s_id	s_name	s_address	s_phone	s_email	s_department	s_professor	s_account\n");
	             
            	while (rs2.next()) {
            		String str2 = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getInt(3) + "\t" + rs2.getString(4)+ "\t" + rs2.getString(5) + "\t" + rs2.getString(6) + "\t" + rs2.getString(7) + "\t" + rs2.getString(8)+ "\t" + rs2.getString(9)+ "\t" + rs2.getString(10)
	                      + "\n";
            		txtResult.append(str2);
	                
            	}
	            }
	          }
	         
	         else if (e.getSource() == btnOk4) {
	        	 String query = "select s_name, l_id, l_name, credit, grade \r\n"
	        	 		+ "from Student, Lecture, Course_History \r\n"
	        	 		+ "where Student.s_id = Course_History.Student_s_id\r\n"
	        	 		+ "and s_name = \"" + stu_name +"\""
	        	 		+ " and Course_History.Lecture_l_id = Lecture.l_id;";
	             txtResult.setText("");
	             txtResult.setText("s_name	l_id	l_name	creadit	grade	GPA\n");
	             rs = stmt.executeQuery(query);
	             while (rs.next()) {
	            	double GPA = 0;
	            	String grade;
	                String str = rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) +"\t";
	                
	                grade=rs.getString(5);
	                      
	                str=str+grade+"\t";
	                
	                if(grade.equals("A")) GPA = 4.0;
	                else if(grade.equals("B")) GPA = 3.0;
	                else if(grade.equals("C")) GPA = 2.0;
	                else if(grade.equals("D")) GPA = 1.0;
	                else if(grade.equals("F")) GPA = 0.0;
	                
	                grade=Double.toString(GPA);
	                
	                str=str+grade+"\n";
	                
	                txtResult.append(str);
	             }
	          }
	         
	         else if(e.getSource() == btn_insert_data) {
	        	 try {
	        		 int year,semester;
	        		 
	        		 year=Integer.parseInt(txt_value1.getText());
	        		 semester=Integer.parseInt(txt_value2.getText());
	        		 JOptionPane.showMessageDialog(btn_insert_data, "입력완료");
	 	        	
	        		 String query = "select s_id, s_name, year, semester, l_id, dcnum, l_name, l_day,\r\n"
	        		 		+ "l_period, credit, l_time, l_room, l_professor, l_department\r\n"
	        		 		+ "from Student, Student_has_Professor, Lecture\r\n"
	        		 		+ "where Student.s_id = Student_has_Professor.Student_s_id\r\n"
	        		 		+ "and Student_has_Professor.Professor_p_id = Lecture.Professor_p_id\r\n"
	        		 		+ "and year = "+ year+" and semester = " + semester 
	        		 		+ " and s_name = \"" + stu_name +"\";";
	        		 //System.out.println(query);
		             txtResult.setText("");
		             txtResult.setText("s_id	s_name	year	semester	l_id	dcnum	l_name	l_day	l_period	credit	l_time	l_room	l_professor	l_department\n");
		             rs = stmt.executeQuery(query);
		             while (rs.next()) {
		                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t" +rs.getInt(5) + "\t" +rs.getInt(6) + "\t" +rs.getString(7) + "\t" +rs.getString(8) + "\t" +rs.getInt(9) + "\t" +rs.getInt(10) + "\t" +rs.getInt(11) + "\t" +rs.getInt(12) + "\t" +rs.getString(13) + "\t" +rs.getString(14) + "\t" + "\n";
		                txtResult.append(str);
		             }
	        		 
	        	 }
	        	 
	        	 catch(Exception e3){
	        		 JOptionPane.showMessageDialog(btn_insert_data, "오류!!");
	        	 }
	        	 
	        	 
	        	 
	        	
	         }
	          
	         
	      } catch (Exception e2) {
	         System.out.println("쿼리 읽기 실패 :" + e2);
	      }	
	      
	}
		
		
	}
}
