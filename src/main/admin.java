package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JSlider;
import java.awt.List;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Window.Type;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class admin extends JFrame implements ActionListener {

	
	JTextArea returnresulttxt=new JTextArea();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
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
	    System.out.println("관리자 CONNECTION SUCCESFUL\nREADY TO USE PROGRAM");
	  } catch(SQLException e1) {
	      e1.printStackTrace();
	    }
	}

	JButton btnOk1,btnOk2,btnOk3,btnOk4,btnOk5,btnOk6, btn_insert, btnReset,btnreset_table, btn_insert_data;
	JButton btn_delete_data,btn_change_data;
	JTextField txt_value1, txt_value2, txt_value3;
	JTextArea txtResult, txtStatus;
	JPanel pn1, insert_pn;
	JButton btn;
	JButton backbtn;
	
	
	public admin() {
		setTitle("신동준 이정규 - 관리자 페이지");
		conDB();
		layInit();
	    setBounds(200, 200, 1000, 600); //가로위치,세로위치,가로길이,세로길이
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    backbtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbtn.setBackground(Color.gray);
		backbtn.setBounds(0, 0, 103, 23);
		
	}
	
	public void layInit() {
		  btnOk1 = new JButton("초기화");
	      btnOk2 = new JButton("입력/삭제/변경");
	      btnOk3 = new JButton("전체 테이블 보기");
	      backbtn = new JButton("《 뒤로가기");
	      btn_insert = new JButton("insert");
	      btnreset_table=new JButton("table 리셋");
	      btnReset = new JButton("화면초기화");
	      
	      txtResult = new JTextArea(); //수정 ㄴ
	      txtStatus = new JTextArea(5,30);
	      
	    //입력 부분
	      //1. 패널 안에 컴포넌트 생성
	      txt_value1 = new JTextField(20);
	      txt_value2 = new JTextField(20);
	      txt_value3 = new JTextField(20);
	      btn_insert_data = new JButton("입력");
	      btn_delete_data = new JButton("삭제");
	      btn_change_data = new JButton("변경");
	      
	      //패널 생성 후 layout 설정
	      insert_pn = new JPanel();
	      insert_pn.setLayout(new GridLayout(4,3));
	      
	      //생성한 패널에 컴포넌트 추가
	      insert_pn.add(new JLabel("value1"));
	      insert_pn.add(txt_value1);
	      insert_pn.add(btn_insert_data);
	      
	      insert_pn.add(new JLabel("value2"));
	      insert_pn.add(txt_value2);
	      insert_pn.add(btn_delete_data);
	      
	      insert_pn.add(new JLabel("value3"));
	      insert_pn.add(txt_value3);
	      insert_pn.add(btn_change_data);
	      
	      
	      
	      
	      //메인 패널의 동쪽에 새로운 패널 위치
	      add("East",insert_pn);

	      //입력버튼 이벤트 추가
	      btn_insert_data.addActionListener(this);
	      btn_delete_data.addActionListener(this);
	      btn_change_data.addActionListener(this);

	      
	      
	      
	      pn1 = new JPanel();

	      
	      pn1.add(backbtn);
	      pn1.add(btnOk1);
	      pn1.add(btnOk3);
	      
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
	   }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
	         stmt = con.createStatement();
	         
	         if (e.getSource() == btnOk1) {
	        	
	        			try {
	        				stmt1 = con.createStatement();
	        				String query="DROP SCHEMA IF EXISTS `madang` ;";
	        				stmt1.executeUpdate(query);
	        				
	        				stmt1 = con.createStatement();
	        				query="CREATE SCHEMA IF NOT EXISTS `madang` DEFAULT CHARACTER SET utf8 ;";
	        				stmt1.executeUpdate(query);
	        				
	        				stmt1 = con.createStatement();
	        				query="USE `madang`;";
	        				stmt1.executeUpdate(query);
	        				
	        				query="SET FOREIGN_KEY_CHECKS=0;"; 
	        	            stmt1.executeUpdate(query);
	        				
	        				stmt1 = con.createStatement();
	        	            query="DROP TABLE IF EXISTS `Student`;";
	        	             stmt1.executeUpdate(query);
	        	             query="DROP TABLE IF EXISTS `Professor`;";
	        	             stmt1.executeUpdate(query);
	        	             query="DROP TABLE IF EXISTS `Club`;";
	        	             stmt1.executeUpdate(query);
	        	             query="DROP TABLE IF EXISTS `Department`;";
	        	             stmt1.executeUpdate(query);
	        	             query="DROP TABLE IF EXISTS `Lecture`;";
	        	             stmt1.executeUpdate(query);
	        	             query="DROP TABLE IF EXISTS `Tuition_Payment`;";
	        	             stmt1.executeUpdate(query);
	        	             query="DROP TABLE IF EXISTS `Course_History`;";
	        	             stmt1.executeUpdate(query);
	        	             query="DROP TABLE IF EXISTS `Student_has_Professor`;"; 
	        	             stmt1.executeUpdate(query);
	        	            
	        	             //System.out.println("삭제완료!");
	        	             
	        	             
	        	                
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Department` (\r\n"
	        	           		+ "  `d_id` INT NOT NULL,\r\n"
	        	           		+ "  `d_name` VARCHAR(25) NOT NULL,\r\n"
	        	           		+ "  `d_phone` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `d_office` INT NOT NULL,\r\n"
	        	           		+ "  `d_chair` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `d_lecture` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  PRIMARY KEY (`d_id`))\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Department 생성완료!");
	        	           
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Student` (\r\n"
	        	           		+ "  `s_id` INT NOT NULL,\r\n"
	        	           		+ "  `s_name` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `s_address` VARCHAR(35) NOT NULL,\r\n"
	        	           		+ "  `s_phone` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `s_email` VARCHAR(35) NOT NULL,\r\n"
	        	           		+ "  `Department_d_id` INT NOT NULL,\r\n"
	        	           		+ "  `Club_c_id` INT NOT NULL,\r\n"
	        	           		+ "  `s_department` VARCHAR(25) NOT NULL,\r\n"
	        	           		+ "  `s_professor` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `s_account` VARCHAR(20) NOT NULL,\r\n"
	        	           		+ "  PRIMARY KEY (`s_id`),\r\n"
	        	           		+ "  INDEX `fk_Student_Department1_idx` (`Department_d_id` ASC) VISIBLE,\r\n"
	        	           		+ "  INDEX `fk_Student_Club1_idx` (`Club_c_id` ASC) VISIBLE,\r\n"
	        	           		+ "  CONSTRAINT `fk_Student_Department1`\r\n"
	        	           		+ "    FOREIGN KEY (`Department_d_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Department` (`d_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION,\r\n"
	        	           		+ "  CONSTRAINT `fk_Student_Club1`\r\n"
	        	           		+ "    FOREIGN KEY (`Club_c_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Club` (`c_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION)\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Student 생성완료!");
	        	           
	        	           
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Professor` (\r\n"
	        	           		+ "  `p_id` INT NOT NULL,\r\n"
	        	           		+ "  `p_name` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `p_address` VARCHAR(35) NOT NULL,\r\n"
	        	           		+ "  `p_phone` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `p_email` VARCHAR(35) NOT NULL,\r\n"
	        	           		+ "  `Department_d_id` INT NOT NULL,\r\n"
	        	           		+ "  `p_department` VARCHAR(25) NOT NULL,\r\n"
	        	           		+ "  `p_lecture` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  PRIMARY KEY (`p_id`),\r\n"
	        	           		+ "  INDEX `fk_Professor_Department1_idx` (`Department_d_id` ASC) VISIBLE,\r\n"
	        	           		+ "  CONSTRAINT `fk_Professor_Department1`\r\n"
	        	           		+ "    FOREIGN KEY (`Department_d_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Department` (`d_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION)\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Professor 생성완료!");
	        	           
	        	           
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Lecture` (\r\n"
	        	           		+ "  `Department_d_id` INT NOT NULL,\r\n"
	        	           		+ "  `Professor_p_id` INT NOT NULL,\r\n"
	        	           		+ "  `l_id` INT NOT NULL,\r\n"
	        	           		+ "  `dcnum` INT NOT NULL,\r\n"
	        	           		+ "  `l_name` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `l_day` VARCHAR(10) NOT NULL,\r\n"
	        	           		+ "  `l_period` VARCHAR(10) NOT NULL,\r\n"
	        	           		+ "  `credit` INT NOT NULL,\r\n"
	        	           		+ "  `l_time` INT NOT NULL,\r\n"
	        	           		+ "  `l_room` INT NOT NULL,\r\n"
	        	           		+ "  `l_professor` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `l_department` VARCHAR(25) NOT NULL,\r\n"
	        	           		+ "  PRIMARY KEY (`l_id`),\r\n"
	        	           		+ "  INDEX `fk_Department_has_Professor_Professor1_idx` (`Professor_p_id` ASC) VISIBLE,\r\n"
	        	           		+ " /* INDEX `fk_Department_has_Professor_Department1_idx` (`Department_d_id` ASC) VISIBLE,\r\n"
	        	           		+ "   UNIQUE INDEX `Department_d_id_UNIQUE` (`Department_d_id` ASC) VISIBLE,*/\r\n"
	        	           		+ "  UNIQUE INDEX `Professor_p_id_UNIQUE` (`Professor_p_id` ASC) VISIBLE,\r\n"
	        	           		+ "/*  CONSTRAINT `fk_Department_has_Professor_Department1`\r\n"
	        	           		+ "    FOREIGN KEY (`Department_d_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Department` (`d_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION,*/\r\n"
	        	           		+ "  CONSTRAINT `fk_Department_has_Professor_Professor1`\r\n"
	        	           		+ "    FOREIGN KEY (`Professor_p_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Professor` (`p_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION)\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Lecture 생성완료!");
	        	           
	        	           
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Club` (\r\n"
	        	           		+ "  `c_id` INT NOT NULL,\r\n"
	        	           		+ "  `c_name` VARCHAR(25) NOT NULL,\r\n"
	        	           		+ "  `thenumberofstudent` INT NOT NULL,\r\n"
	        	           		+ "  `c_chair` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `c_professor` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `c_room` INT NOT NULL,\r\n"
	        	           		+ "  `Professor_p_id` INT NOT NULL,\r\n"
	        	           		+ "  PRIMARY KEY (`c_id`),\r\n"
	        	           		+ "  INDEX `fk_Club_Professor1_idx` (`Professor_p_id` ASC) VISIBLE,\r\n"
	        	           		+ "  UNIQUE INDEX `Professor_p_id_UNIQUE` (`Professor_p_id` ASC) VISIBLE,\r\n"
	        	           		+ "  CONSTRAINT `fk_Club_Professor1`\r\n"
	        	           		+ "    FOREIGN KEY (`Professor_p_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Professor` (`p_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION)\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Club 생성완료!");
	        	           
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Lecture` (\r\n"
	        	           		+ "  `Department_d_id` INT NOT NULL,\r\n"
	        	           		+ "  `Professor_p_id` INT NOT NULL,\r\n"
	        	           		+ "  `l_id` INT NOT NULL,\r\n"
	        	           		+ "  `dcnum` INT NOT NULL,\r\n"
	        	           		+ "  `l_name` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `l_day` VARCHAR(10) NOT NULL,\r\n"
	        	           		+ "  `l_period` VARCHAR(10) NOT NULL,\r\n"
	        	           		+ "  `credit` INT NOT NULL,\r\n"
	        	           		+ "  `l_time` INT NOT NULL,\r\n"
	        	           		+ "  `l_room` INT NOT NULL,\r\n"
	        	           		+ "  `l_professor` VARCHAR(15) NOT NULL,\r\n"
	        	           		+ "  `l_department` VARCHAR(25) NOT NULL,\r\n"
	        	           		+ "  PRIMARY KEY (`l_id`),\r\n"
	        	           		+ "  INDEX `fk_Department_has_Professor_Professor1_idx` (`Professor_p_id` ASC) VISIBLE,\r\n"
	        	           		+ " /* INDEX `fk_Department_has_Professor_Department1_idx` (`Department_d_id` ASC) VISIBLE,\r\n"
	        	           		+ "   UNIQUE INDEX `Department_d_id_UNIQUE` (`Department_d_id` ASC) VISIBLE,*/\r\n"
	        	           		+ "  UNIQUE INDEX `Professor_p_id_UNIQUE` (`Professor_p_id` ASC) VISIBLE,\r\n"
	        	           		+ " /* CONSTRAINT `fk_Department_has_Professor_Department1`\r\n"
	        	           		+ "    FOREIGN KEY (`Department_d_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Department` (`d_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION,*/\r\n"
	        	           		+ "  CONSTRAINT `fk_Department_has_Professor_Professor1`\r\n"
	        	           		+ "    FOREIGN KEY (`Professor_p_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Professor` (`p_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION)\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           
	        	           //System.out.println("Lecutre 생성완료!");
	        	           
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Course_History` (\r\n"
	        	           		+ "  `attendance_score` INT NOT NULL,\r\n"
	        	           		+ "  `midterm_score` INT NOT NULL,\r\n"
	        	           		+ "  `Student_s_id` INT NOT NULL,\r\n"
	        	           		+ "  `Lecture_l_id` INT NOT NULL,\r\n"
	        	           		+ "  `Professor_p_id` INT NOT NULL,\r\n"
	        	           		+ "  `finals_score` INT NOT NULL,\r\n"
	        	           		+ "  `etc_score` INT NOT NULL,\r\n"
	        	           		+ "  `total_score` INT NOT NULL,\r\n"
	        	           		+ "  `grade` CHAR(1) NOT NULL,\r\n"
	        	           		+ "  `tuitionpaymentY/N` VARCHAR(2) NOT NULL,\r\n"
	        	           		+ "  INDEX `fk_Course_History_Student1_idx` (`Student_s_id` ASC) VISIBLE,\r\n"
	        	           		+ "  INDEX `fk_Course_History_Lecture1_idx` (`Lecture_l_id` ASC) VISIBLE,\r\n"
	        	           		+ "  INDEX `fk_Course_History_Professor1_idx` (`Professor_p_id` ASC) VISIBLE,\r\n"
	        	           		+ "  PRIMARY KEY (`Student_s_id`),\r\n"
	        	           		+ "  CONSTRAINT `fk_Course_History_Student1`\r\n"
	        	           		+ "    FOREIGN KEY (`Student_s_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Student` (`s_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION,\r\n"
	        	           		+ "  CONSTRAINT `fk_Course_History_Lecture1`\r\n"
	        	           		+ "    FOREIGN KEY (`Lecture_l_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Lecture` (`l_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION,\r\n"
	        	           		+ "  CONSTRAINT `fk_Course_History_Professor1`\r\n"
	        	           		+ "    FOREIGN KEY (`Professor_p_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Professor` (`p_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION)\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Course_History 생성완료!");
	        	           
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Tuition_Payment` (\r\n"
	        	           		+ "  `tp_year` INT NOT NULL,\r\n"
	        	           		+ "  `tp_semester` INT NOT NULL,\r\n"
	        	           		+ "  `t_total` INT NOT NULL,\r\n"
	        	           		+ "  `p_total` INT NOT NULL,\r\n"
	        	           		+ "  `tp_finaldate` DATE NOT NULL,\r\n"
	        	           		+ "  `Student_s_id` INT NOT NULL,\r\n"
	        	           		+ "  `Course_History_Student_s_id` INT NOT NULL,\r\n"
	        	           		+ "  INDEX `fk_Tuition_Payment_Student1_idx` (`Student_s_id` ASC) VISIBLE,\r\n"
	        	           		+ "  PRIMARY KEY (`Student_s_id`),\r\n"
	        	           		+ "  INDEX `fk_Tuition_Payment_Course_History1_idx` (`Course_History_Student_s_id` ASC) VISIBLE,\r\n"
	        	           		+ "  CONSTRAINT `fk_Tuition_Payment_Student1`\r\n"
	        	           		+ "    FOREIGN KEY (`Student_s_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Student` (`s_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION,\r\n"
	        	           		+ "  CONSTRAINT `fk_Tuition_Payment_Course_History1`\r\n"
	        	           		+ "    FOREIGN KEY (`Course_History_Student_s_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Course_History` (`Student_s_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION)\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Tuition_Payment 생성완료!");
	        	           
	        	           
	        	           query="CREATE TABLE IF NOT EXISTS `madang`.`Student_has_Professor` (\r\n"
	        	           		+ "  `Student_s_id` INT NOT NULL,\r\n"
	        	           		+ "  `Professor_p_id` INT NOT NULL,\r\n"
	        	           		+ "  `year` INT NOT NULL,\r\n"
	        	           		+ "  `semester` INT NOT NULL,\r\n"
	        	           		+ "  PRIMARY KEY (`Student_s_id`, `Professor_p_id`),\r\n"
	        	           		+ "  INDEX `fk_Student_has_Professor_Professor1_idx` (`Professor_p_id` ASC) VISIBLE,\r\n"
	        	           		+ "  INDEX `fk_Student_has_Professor_Student1_idx` (`Student_s_id` ASC) VISIBLE,\r\n"
	        	           		+ "  CONSTRAINT `fk_Student_has_Professor_Student1`\r\n"
	        	           		+ "    FOREIGN KEY (`Student_s_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Student` (`s_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION,\r\n"
	        	           		+ "  CONSTRAINT `fk_Student_has_Professor_Professor1`\r\n"
	        	           		+ "    FOREIGN KEY (`Professor_p_id`)\r\n"
	        	           		+ "    REFERENCES `madang`.`Professor` (`p_id`)\r\n"
	        	           		+ "    ON DELETE NO ACTION\r\n"
	        	           		+ "    ON UPDATE NO ACTION)\r\n"
	        	           		+ "ENGINE = InnoDB;";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Student_has_Professor 생성완료!");
	        	           
	        	           //System.out.println("테이블 초기화 완료");
	        	            
	        	           query="INSERT INTO Student VALUES(1, '이정규', '서울시 광진구 능동', '01044559622', '5552269@sejong.ac.kr', 1, 1, '컴퓨터공학과', '신동일', '110423053231');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(2, '신동준', '서울시 광진구 능동', '01056888109', 'dongjun@sejong.ac.kr', 1, 2, '컴퓨터공학과', '임필옥', '110423053232');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(3, '원종서', '서울시 광진구 능동', '01099043322', 'jongseo@sejong.ac.kr', 1, 3, '컴퓨터공학과', '유영환', '110423053233');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(4, '한세웅', '서울시 광진구 능동', '01034190437', 'sewoong@sejong.ac.kr', 1, 1, '컴퓨터공학과', '나중채', '110423053234');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(5, '고상현', '서울시 광진구 능동', '01097253188', 'sanghyun@sejong.ac.kr', 1, 2, '컴퓨터공학과', '김도년', '110423053235');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(6, '박정민', '서울시 광진구 화양동', '01032384123', 'jungmin@sejong.ac.kr', 1, 3, '컴퓨터공학과', '박기호', '110423053236');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(7, '김원희', '서울시 광진구 화양동', '01038931767', 'wonhee@sejong.ac.kr', 2, 1, '소프트웨어학과', '김청원', '110423053237');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(8, '장은영', '서울시 광진구 화양동', '01019343437', 'eunyoung@sejong.ac.kr', 2, 2, '소프트웨어학과', '김원일', '110423053238');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(9, '이가은', '서울시 광진구 자양동', '01039087237', 'gaeun@sejong.ac.kr', 1, 3, '컴퓨터공학과', '신동일', '110423053239');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(10, '김종찬', '서울시 광진구 자양동', '01019293847', 'jongchan@sejong.ac.kr', 1, 1, '컴퓨터공학과', '임필옥', '110423053240');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(11, '민지은', '서울시 광진구 자양동', '01019283848', 'jeeun@sejong.ac.kr', 1, 2, '컴퓨터공학과', '유영환', '110423053241');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(12, '박철준', '서울시 광진구 자양동', '01019283849', 'chuljun@sejong.ac.kr', 2, 3, '소프트웨어학과', '김청원', '110423053242');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(13, '임예진', '서울시 광진구 화양동', '01098113680', 'yejin@sejong.ac.kr', 3, 1, '건설환경공학과', '배덕효', '110423053243');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(14, '김효범', '아산시', '01042119422', 'hyobum@sejong.ac.kr', 4, 2, '전자공학과', '김도년', '110423053244');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(15, '박진성', '천안시 성정동', '01045918232', 'dubupark@sejong.ac.kr', 4, 3, '전자공학과', '박기호', '110423053245');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(16, '김영방', '대전시 둔산동', '01064265571', 'youngbang@sejong.ac.kr', 5, 1, '기계공학과', '김청원', '110423053246');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(17, '강병준', '천안시 성정동', '01033425077', 'byoungjun@sejong.ac.kr', 5, 2, '기계공학과', '김원일', '110423053247');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(18, '박로찬', '세종시', '01050489503', 'rochan@sejong.ac.kr', 4, 3, '전자공학과', '김도년', '110423053248');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(19, '신동주', '대전시 궁동', '01050183199', 'dongju@sejong.ac.kr', 4, 1, '전자공학과', '박기호', '110423053249');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(20, '윤재상', '하남시', '01091177696', 'jaesang@sejong.ac.kr', 4, 2, '전자공학과', '김도년', '110423053250');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(21, '윤진훈', '아산시', '01068000496', 'jinhoon@sejong.ac.kr', 4, 3, '전자공학과', '박기호', '110423053251');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(22, '이시형', '천안시 신방동', '01024215113', 'seehyung@sejong.ac.kr', 5, 1, '기계공학과', '김청원', '110423053252');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(23, '이형노', '천안시 신안동', '01044204941', 'hyungno@sejong.ac.kr', 5, 2, '기계공학과', '김원일', '110423053253');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(24, '장태환', '천안시 목천면', '01050173900', 'taehwan@sejong.ac.kr', 5, 3, '기계공학과', '김원일', '110423053254');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student VALUES(25, '한재영', '천안시 두정동', '01087123594', 'jaeyoung@sejong.ac.kr', 5, 1, '기계공학과', '김청원', '110423053255');";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Student 입력완료!");
	        	           
	        	           query="INSERT INTO Department VALUES(1, '컴퓨터공학과', '02-111-1111', 101, '신동일', '데이터베이스, 자료구조');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(2, '소프트웨어학과', '02-111-1112', 201, '김원일', '기계학습, 영상처리');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(3, '건설환경공학과', '02-111-1113', 301, '배덕효', '수자원공학, 토목시공학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(4, '전자공학과', '02-111-1114', 401, '박기호', '전기전자학, 전기학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(5, '기계공학과', '02-111-1115', 501, '김청원', '기계학, 선형대수학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(6, '국어국문학과', '02-111-1116', 601, '주경희', '국문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(7, '영어영문학과', '02-111-1117', 701, '곽은주', '영문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(8, '일어일문학과', '02-111-1118', 801, '이동영', '일문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(9, '중국통상학과', '02-111-1119', 901, '김수연', '중문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(10, '역사학과', '02-111-1120', 1001, '이경량', '역사학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(11, '교육학과', '02-111-1121', 1101, '정영희', '교육학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(12, '미디어커뮤니케이션학과', '02-111-1122', 1201, '탁진영', '미디어통계');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(13, '호텔경영학과', '02-111-1123', 1301, '김동호', '호텔경영학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(14, '외식경영학과', '02-111-1124', 1401, '문동호', '외식경영학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(15, '호텔관광학과', '02-111-1125', 1501, '이선호', '호텔관광학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(16, '호텔외식비지니스학과', '02-111-1126', 1601, '오장헌', '호텔외식학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(17, '글로벌조리학과', '02-111-1127', 1701, '이승연', '글로벌조리학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(18, '수학과', '02-111-1128', 1801, '이언경', '대수학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(19, '응용통계학과', '02-111-1129', 1901, '하길찬', '통계학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(20, '물리천문학과', '02-111-1130', 2001, '차영준', '천문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(21, '화학과', '02-111-1131', 2101, '유호석', '화학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(22, '지능기전공학과', '02-111-1132', 2201, '김주명', '스마트기기');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(23, '정보보호학과', '02-111-1133', 2301, '김영갑', '암호학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(24, '데이터사이언스학과', '02-111-1134', 2401, '신동규', '빅데이터');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Department VALUES(25, '인공지능학과', '02-111-1135', 2501, '문현준', '인공지능');";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Department 입력완료!");
	        	           
	        	           
	        	           query="INSERT INTO Club VALUES(1, 'DB', 15, '이정규', '신동일', 111, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(2, 'C', 20, '신동준', '임필옥', 211, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(3, 'Furier', 10, '원종서', '이영렬', 311, 3);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(4, '!', 30, '이정규', '문현준', 411, 7);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(5, '@', 30, '이정규', '신동규', 511, 8);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(6, '#', 30, '이정규', '김영갑', 611, 9);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(7, '$', 30, '이정규', '김주명', 711, 10);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(8, '%', 30, '이정규', '유호석', 811, 11);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(9, '^', 30, '이정규', '차영준', 911, 12);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(10, '&', 30, '이정규', '하길찬', 1011, 13);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(11, '*', 30, '이정규', '이언경', 1111, 14);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(12, '-', 30, '이정규', '이승연', 1211, 15);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(13, '_', 30, '이정규', '오장헌', 1311, 16);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(14, '+', 30, '이정규', '이선호', 1411, 17);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(15, '/', 30, '이정규', '문동호', 1511, 18);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(16, '<', 30, '이정규', '김동호', 1611, 19);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(17, '>', 30, '이정규', '탁진영', 1711, 20);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(18, '?', 30, '이정규', '정영희', 1811, 21);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(19, ';', 30, '이정규', '이경량', 1911, 22);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(20, ':', 30, '이정규', '김수연', 2011, 23);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(21, 'qwer', 30, '이정규', '이동영', 2111, 24);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(22, 'asdf', 30, '이정규', '곽은주', 2211, 25);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(23, 'zxc', 30, '이정규', '주경희', 2311, 26);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(24, 'vbn', 30, '이정규', '김청원', 2411, 27);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Club VALUES(25, 'ret', 30, '이정규', '박기호', 2511, 28);";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Club 입력완료!");
	        	           
	        	           query="INSERT INTO Professor VALUES(1, '신동일', '서울시 광진구 능동', '01010101010', 'di@sejong.ac.kr', 1, '컴퓨터공학과', '데이터베이스');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(2, '임필옥', '서울시 광진구 화양동', '01010101011', 'po@sejong.ac.kr', 1, '컴퓨터공학과', '자료구조');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(3, '이영렬', '서울시 광진구 자양동', '01010101012', 'yl@sejong.ac.kr', 1, '컴퓨터공학과', '영상처리');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(4, '나중채', '서울시 광진구 군자동', '01010101013', 'jc@sejong.ac.kr', 1, '컴퓨터공학과', '알고리즘');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(5, '김원일', '서울시 광진구 중곡동', '01010101014', '11@sejong.ac.kr', 2, '소프트웨어학과', '기계학습');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(6, '배덕효', '서울시 송파구 오금동', '01010101015', 'dh@sejong.ac.kr', 3, '건설환경공학과', '수자원공학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(7, '문현준', '서울시 광진구 능동', '01010101016', 'mhj@sejong.ac.kr', 25, '인공지능학과', '인공지능');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(8, '신동규', '서울시 광진구 능동', '01010101017', 'dk@sejong.ac.kr', 24, '데이터사이언스학과', '빅데이터');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(9, '김영갑', '서울시 광진구 능동', '01010101018', 'kyg@sejong.ac.kr', 23, '정보보호학과', '암호학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(10, '김주명', '서울시 광진구 능동', '01010101019', 'jm@sejong.ac.kr', 22, '지능기전공학과', '스마트기기');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(11, '유호석', '서울시 광진구 능동', '01010101020', 'hs@sejong.ac.kr', 21, '화학과', '화학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(12, '차영준', '서울시 광진구 능동', '01010101021', 'yg@sejong.ac.kr', 20, '천문학과', '천문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(13, '하길찬', '서울시 광진구 능동', '01010101022', 'gc@sejong.ac.kr', 19, '통계학과', '통계학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(14, '이언경', '서울시 광진구 능동', '01010101023', 'lug@sejong.ac.kr', 18, '수학과', '대수학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(15, '이승연', '서울시 광진구 능동', '01010101024', 'lsy@sejong.ac.kr', 17, '글로벌조리학과', '글로벌조리학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(16, '오장헌', '서울시 광진구 능동', '01010101025', 'jh@sejong.ac.kr', 16, '호텔외식비지니스과', '호텔외식학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(17, '이선호', '서울시 광진구 능동', '01010101026', 'sh@sejong.ac.kr', 15, '호텔관광학과', '호텔관광학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(18, '문동호', '서울시 광진구 능동', '01010101027', 'mdh@sejong.ac.kr', 14, '외식경영학과', '외식경영학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(19, '김동호', '서울시 광진구 능동', '01010101028', 'kdh@sejong.ac.kr', 13, '호텔경영학과', '호텔경영학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(20, '탁진영', '서울시 광진구 능동', '01010101029', 'jy@sejong.ac.kr', 12, '미디어커뮤니케이션학과', '미디어통계');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(21, '정영희', '서울시 광진구 능동', '01010101030', 'yh@sejong.ac.kr', 11, '교육학과', '교육학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(22, '이경량', '서울시 광진구 능동', '01010101031', 'kr@sejong.ac.kr', 10, '역사학과', '역사학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(23, '김수연', '서울시 광진구 능동', '01010101032', 'sy@sejong.ac.kr', 9, '중국통상학과', '중문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(24, '이동영', '서울시 광진구 능동', '01010101033', 'dy@sejong.ac.kr', 8, '일어일문학과', '일문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(25, '곽은주', '서울시 광진구 능동', '01010101034', 'ej@sejong.ac.kr', 7, '영어영문학과', '영문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(26, '주경희', '서울시광진구 능동', '01010101035', 'kh@sejong.ac.kr', 6, '국어국문학과', '국문학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(27, '김청원', '서울시 광진구 능동', '01010101036', 'cw@sejong.ac.kr', 5, '기계공학과', '기계학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(28, '박기호', '서울시광진구 능동', '01010101037', 'gh@sejong.ac.kr', 4, '전자공학과', '전기전자학');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(29, '유영환', '서울시 광진구 능동', '01010101038', 'yh@sejong.ac.kr', 1, '컴퓨터공학과', '확률통계');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Professor VALUES(30, '김도년', '서울시 광진구 능동', '01010101039', 'dk@sejong.ac.kr', 1, '컴퓨터공학과', 'C언어');";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Professor 입력완료!");
	        	           
	        	           
	        	           query="INSERT INTO Lecture VALUES(1, 1, 1, 001, '데이터베이스', '월', 1, 3, 3, 102, '신동일', '컴퓨터공학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(1, 2, 2, 001, '자료구조', '화', 1, 3, 3, 104, '임필옥', '컴퓨터공학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(1, 4, 3, 001, '알고리즘', '수', 1, 3, 3, 106, '나중채', '컴퓨터공학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(1, 3, 4, 001, '영상처리', '목', 1, 3, 3, 108, '이영렬', '컴퓨터공학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(2, 5, 5, 001, '기계학습', '금', 1, 3, 3, 202, '김원일', '컴퓨터공학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(3, 6, 6, 001, '수자원공학', '월', 4, 3, 3, 204, '배덕효', '컴퓨터공학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(25, 7, 7, 001, '인공지능', '화', 4, 3, 3, 206, '문현준', '인공지능학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(24, 8, 8, 001, '빅데이터', '수', 4, 3, 3, 208, '신동규', '데이터사이언스학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(23, 9, 9, 001, '암호화', '목', 4, 3, 3, 302, '김영갑', '정보보호학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(22, 10, 10, 001, '스마트기기', '금', 4, 3, 3, 304, '김주명', '지능기전학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(21, 11, 11, 001, '화학', '월', 7, 3, 3, 306, '유효석', '화학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(20, 12, 12, 001, '천문학', '화', 7, 3, 3, 308, '차영준', '천문학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(19, 13, 13, 001, '통계학', '수', 7, 3, 3, 402, '하길찬', '통계학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(18, 14, 14, 001, '대수학', '목', 7, 3, 3, 404, '이언경', '수학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(17, 15, 15, 001, '글로벌조리학', '금', 7, 3, 3, 406, '이승연', '글로벌조리학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(16, 16, 16, 001, '호텔외식학', '월', 2, 3, 3, 408, '오장헌', '호텔외식비지니스학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(15, 17, 17, 001, '호텔관광학', '화', 2, 3, 3, 502, '이선호', '호텔관광학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(14, 18, 18, 001, '외식경영학', '수', 2, 3, 3, 504, '문동호', '외식경영학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(13, 19, 19, 001, '호텔경영학', '목', 2, 3, 3, 506, '김동호', '호텔경영학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(12, 20, 20, 001, '미디어통계', '금', 2, 3, 3, 508, '탁진영', '미디어커뮤니케이션학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(11, 21, 21, 001, '교육학', '월', 5, 3, 3, 602, '정영희', '교육학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(10, 22, 22, 001, '역사학', '화', 5, 3, 3, 604, '이경량', '역사학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(9, 23, 23, 001, '중문학', '수', 5, 3, 3, 606, '김수연', '중국통상학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(8, 24, 24, 001, '일문학', '목', 5, 3, 3, 608, '이동영', '일어영문학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(7, 25, 25, 001, '영문학', '금', 5, 3, 3, 702, '곽은주', '영어영문학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(6, 26, 26, 001, '국문', '월', 8, 3, 3, 704, '주경희', '국어국문학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(5, 27, 27, 001, '기계학', '화', 8, 3, 3, 706, '김청원', '기계공학과');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Lecture VALUES(4, 28, 28, 001, '전기전자학', '수', 8, 3, 3, 708, '박기호', '전자공학과');";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Lecture 입력완료!");
	        	           
	        	           query="INSERT INTO Student_has_Professor VALUES(1, 1, 2021, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(2, 2, 2020, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(3, 3, 2020, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(4, 4, 2019, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(5, 5, 2019, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(6, 1, 2018, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(7, 2, 2020, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(8, 2, 2020, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(9, 1, 2019, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(10, 1, 2019, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(11, 1, 2020, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(12, 2, 2019, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(13, 6, 2020, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(14, 28, 2021, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(15, 28, 2020, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(16, 27, 2021, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(17, 27, 2020, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(18, 28, 2020, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(19, 28, 2019, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(20, 28, 2019, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(21, 28, 2020, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(22, 27, 2020, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(23, 27, 2020, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(24, 27, 2019, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Student_has_Professor VALUES(25, 27, 2019, 1);";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Student_has_Professor 입력완료!");
	        	           
	        	           query="INSERT INTO Course_History VALUES(100, 100, 1, 1, 1, 100, 0, 100, 'A', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(100, 100, 2, 2, 2, 100, 0, 100, 'A', 'N');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(95, 90, 3, 1, 3, 95, 0, 95, 'A', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(95, 90, 4, 2, 4, 95, 0, 95, 'A', 'N');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(90, 90, 5, 1, 5, 95, 0, 90, 'A', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(90, 90, 6, 2, 1, 95, 0, 90, 'A', 'N');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(85, 90, 7, 1, 2, 95, 0, 85, 'A', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(85, 90, 8, 2, 3, 95, 0, 85, 'A', 'N');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(80, 90, 9, 1, 4, 95, 0, 80, 'B', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(80, 90, 10, 2, 5, 95, 0, 80, 'B', 'N');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(75, 80, 11, 1, 1, 90, 0, 75, 'B', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(75, 80, 12, 2, 2, 90, 0, 75, 'B', 'N');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(70, 80, 13, 6, 6, 90, 0, 70, 'B', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(70, 80, 14, 28, 7, 90, 0, 70, 'B', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(65, 80, 15, 28, 7, 90, 0, 65, 'C', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(65, 80, 16, 27, 8, 90, 0, 65, 'C', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(60, 80, 17, 27, 8, 90, 0, 65, 'C', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(60, 80, 18, 28, 7, 90, 0, 60, 'C', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(55, 70, 19, 28, 7, 85, 0, 55, 'D', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(55, 70, 20, 28, 7, 85, 0, 55, 'D', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(50, 70, 21, 28, 7, 85, 0, 50, 'D', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(50, 70, 22, 27, 8, 85, 0, 50, 'D', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(45, 70, 23, 27, 8, 85, 0, 45, 'F', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(45, 70, 24, 27, 8, 85, 0, 45, 'F', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Course_History VALUES(40, 70, 25, 27, 8, 85, 0, 40, 'F', 'Y');";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           //System.out.println("Course_History 입력완료!");
	        	           
	        	           query="INSERT INTO Tuition_Payment VALUES(2021, 1, 400, 400, STR_TO_DATE('2021-01-01','%Y-%m-%d'), 1, 1);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2021, 1, 400, 350, STR_TO_DATE('2021-01-01','%Y-%m-%d'), 2, 2);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2021, 1, 400, 400, STR_TO_DATE('2021-01-01','%Y-%m-%d'), 3, 3);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2021, 1, 400, 300, STR_TO_DATE('2021-01-01','%Y-%m-%d'), 4, 4);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2021, 1, 400, 400, STR_TO_DATE('2021-01-01','%Y-%m-%d'), 5, 5);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2021, 1, 400, 350, STR_TO_DATE('2021-01-01','%Y-%m-%d'), 6, 6);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 400, 400, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 7, 7);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 350, 300, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 8, 8);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 350, 350, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 9, 9);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 350, 300, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 10, 10);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 350, 350, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 11, 11);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 350, 300, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 12, 12);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 350, 350, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 13, 13);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 350, 350, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 14, 14);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 2, 350, 350, STR_TO_DATE('2020-07-01','%Y-%m-%d'), 15, 15);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 1, 350, 350, STR_TO_DATE('2020-01-01','%Y-%m-%d'), 16, 16);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 1, 350, 350, STR_TO_DATE('2020-01-01','%Y-%m-%d'), 17, 17);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 1, 350, 350, STR_TO_DATE('2020-01-01','%Y-%m-%d'), 18, 18);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 1, 350, 350, STR_TO_DATE('2020-01-01','%Y-%m-%d'), 19, 19);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2020, 1, 350, 350, STR_TO_DATE('2020-01-01','%Y-%m-%d'), 20, 20);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2019, 2, 300, 300, STR_TO_DATE('2019-07-01','%Y-%m-%d'), 21, 21);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2019, 2, 300, 300, STR_TO_DATE('2019-07-01','%Y-%m-%d'), 22, 22);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2019, 2, 300, 300, STR_TO_DATE('2019-07-01','%Y-%m-%d'), 23, 23);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2019, 2, 300, 300, STR_TO_DATE('2019-07-01','%Y-%m-%d'), 24, 24);";
	        	           stmt1.executeUpdate(query);
	        	           query="INSERT INTO Tuition_Payment VALUES(2019, 2, 300, 300, STR_TO_DATE('2019-07-01','%Y-%m-%d'), 25, 25);";
	        	           stmt1.executeUpdate(query);
	        	           
	        	           System.out.println("초기화 완료!");
	        	           
	        	        }catch(Exception Insert) {
	        	           System.out.println("데이터 입력에 오류 발생!\n"+Insert);
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
	        	 
	        	 //학생 Table 출력
	        	 String query = "select * from Student;";
	             txtResult.setText("");
	        	 txtResult.append("Student Table\n");
	        	 txtResult.append("s_id	s_name	s_address 		s_phone		s_email		Department_d_id   Club_c_id  s_deoartment s_professor	s_account\n");
	             rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getInt(6) + "\t" + rs.getInt(7) + "\t" + rs.getString(8) + "\t" + rs.getString(9) + "\t" + rs.getString(10) + "\t" + "\n";
	                txtResult.append(str);
	             }
	             
	             //교수 테이블 출력
	             query = "select * from Professor;";
	             
	             txtResult.append("\nProfessor Table\n");
	        	 txtResult.append("p_id	p_name	p_address 		p_phone		p_email		Department_d_id   p_department	p_lecture\n");
	        	 rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getInt(6) + "\t" + rs.getString(7) + "\t" + rs.getString(8) + "\t" + "\n";
	                txtResult.append(str);
	             }
	             
	             //강의 테이블 출력
	             
	             query = "select * from Lecture;";
	             
	             txtResult.append("\nLecture Table\n");
	        	 txtResult.append("Department_d_id Professor_p_id l_id dcnum 	l_name   l_day	l_period	credit	l_time	l_room	l_professor	l_department\n");
	        	 rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getInt(7) + "\t" + rs.getInt(8) + "\t" + rs.getInt(9) + "\t" + rs.getInt(10) + "\t" + rs.getString(11) + "\t" + rs.getString(12)  +  "\n";
	                txtResult.append(str);
	             }
	             
	             //학과 출력
	             
	             query = "select * from Department;";
	             
	             txtResult.append("\nDepartment\n");
	        	 txtResult.append("d_id 	d_name d_phone	d_office	d_chair	d_lecture\n");
	        	 rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +"\n";
	                txtResult.append(str);
	             }
	             
	             
	             //강의 동아리 출력
	             
	             query = "select * from Club;";
	             
	             txtResult.append("\nClub\n");
	        	 txtResult.append("c_id 	c_name numofstudent 	c_chair	c_professor	c_room	Professor_p_id\n");
	        	 rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getInt(7) + "\t" +"\n";
	                txtResult.append(str);
	             }
	             
	             //수강내역 출력
	             
	             query = "select * from Course_History;";
	             
	             txtResult.append("\nCourse_History\n");
	        	 txtResult.append("attendance_score midterm_score Student_s_id Lecture_l_id Professor_p_id finals_score etc_score total_score grade tuitionpayment(Y/N)\n");
	        	 rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t" + rs.getInt(5) + "\t" + rs.getInt(6) + "\t" + rs.getInt(7) + "\t" + rs.getInt(8) + "\t" + rs.getString(9) + "\t"+ rs.getString(10) + "\t" +"\n";
	                txtResult.append(str);
	             }
	             
	             //수업료 출력
	             
	             query = "select * from Tuition_Payment;";
	             
	             txtResult.append("\nTuition_Payment\n");
	        	 txtResult.append("tp_year	tp_semester	t_total	p_total	tp_finaldate	Student_s_id	Course_History_Student_s_id\n");
	        	 rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getInt(6) + "\t" + rs.getInt(7) + "\n";
	                txtResult.append(str);
	             }
	             
	             //담당교수 출력
	             
	             query = "select * from Student_has_Professor;";
	             
	             txtResult.append("\nStudent_has_Professor\n");
	        	 txtResult.append("Student_s_id	Professor_p_id year	semester\n");
	        	 rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\n";
	                txtResult.append(str);
	             }
	             
	          }
	         
	         else if (e.getSource() == btnReset) {
	            txtResult.setText("");
	         }
	         
	         
	         //입력 후 버튼 클릭 시 실행
	         else if(e.getSource() == btn_insert_data) {
	        	 String query;
	        	 char id;
	        	 query=txt_value1.getText();
	        	 String D = query.substring(12, 33);
	        	 id=query.charAt(41);
	        	 int credit=3;
	        	 
	        	 if(D.equals("Student_has_Professor")) {
	        		 String query2;
	        		 query2 = "select * from Student_has_Professor where Student_s_id = "+id+";";
	        		 rs = stmt.executeQuery(query2);
	        		 while (rs.next()) {
	        			 credit+=3;
	        		 }
	        		 System.out.println(credit);
	        		 if(credit<=10) {
	        			 try {
	    	        		 stmt.execute(query);
	    	        		 JOptionPane.showMessageDialog(btn_insert_data, "입력완료");
	    	        	 }
	    	        	 
	    	        	 catch(Exception e3) {
	    	        		 JOptionPane.showMessageDialog(btn_insert_data, "오류!!");
	    	        	 }
	        		 }
	        		 
	        		 else {
	        			 JOptionPane.showMessageDialog(btn_insert_data, "10학점을 초과했습니다!");
	        		 }
	        		 
	        	 }
	        	 else {
		        	 try {
		        		 System.out.println(query);
		        		 stmt.execute(query);
		        		 JOptionPane.showMessageDialog(btn_insert_data, "입력완료");
		        	 }
		        	 
		        	 catch(Exception e3) {
		        		 JOptionPane.showMessageDialog(btn_insert_data, "오류!!");
		        	 }
	        	 }
	         }

	         else if(e.getSource() == btn_delete_data) {
	        	 String query;
	        	 query=txt_value2.getText();
	        	 
	        	 try {
	        		 System.out.println(query);
	        		 stmt.execute(query);
	        		 JOptionPane.showMessageDialog(btn_insert_data, "입력완료");
	        	 }
	        	 
	        	 catch(Exception e3) {
	        		 JOptionPane.showMessageDialog(btn_insert_data, "오류!!");
	        	 }
	        	 
	        	 
		     }
	         
	         else if(e.getSource() == btn_change_data) {
	        	 String query;
	        	 query=txt_value3.getText();
	        	 
	        	 try {
	        		 System.out.println(query);
	        		 stmt.executeUpdate(query);
	        		 JOptionPane.showMessageDialog(btn_change_data, "입력완료");
	        	 }
	        	 
	        	 catch(Exception e3) {
	        		 JOptionPane.showMessageDialog(btn_change_data, "오류!!");
	        	 }
		     }
	         
	      } catch (Exception e2) {
	         System.out.println("쿼리 읽기 실패 :" + e2);
	 }  
	}
}
