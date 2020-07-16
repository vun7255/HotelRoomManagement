package quanly.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import quanly.controller.DangNhapController;
import quanly.model.DangNhap;
import quanly.vendors.BCrypt;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class ManHinhDangNhap extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
	private static JPasswordField passwordField;
	private static JButton btnLogin;
	private static ManHinhDangNhap frame;
	private static JRadioButton rdbtnManager;
	private static JRadioButton rdbtnStaff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ManHinhDangNhap();
					frame.addEvent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManHinhDangNhap() {
		this.setTitle("Quản Lý Khách Sạn");
		ImageIcon imgIcon = new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("hotel_icon.png"));
		this.setIconImage(imgIcon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 344);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 132, 68));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelWelcome = new JPanel();
		panelWelcome.setBounds(210, 0, 322, 116);
		panelWelcome.setBackground(new Color(87, 173, 130));
		contentPane.add(panelWelcome);
		panelWelcome.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 312, 116);
		lblNewLabel.setForeground(new Color(28, 57, 43));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
		panelWelcome.add(lblNewLabel);
		
		rdbtnStaff = new JRadioButton("Staff");
		rdbtnStaff.setForeground(new Color(28, 57, 43));
		rdbtnStaff.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnStaff.setBackground(new Color(86 ,173, 130));
		rdbtnStaff.setBounds(255, 90, 61, 23);
		panelWelcome.add(rdbtnStaff);
		
		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setForeground(new Color(28, 57, 43));
		rdbtnManager.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnManager.setBackground(new Color(86, 173, 130));
		rdbtnManager.setBounds(150, 90, 100, 23);
		panelWelcome.add(rdbtnManager);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnManager);
		btnGroup.add(rdbtnStaff);

		JLabel lblAnh = new JLabel("");
		lblAnh.setBounds(0, 0, 209, 315);
		ImageIcon imgLogo = new ImageIcon(getClass().getClassLoader().getResource("LogoDangNhap.png"));
		lblAnh.setIcon(imgLogo);
		contentPane.add(lblAnh);

		JPanel panelText = new JPanel();
		panelText.setBackground(new Color(87, 173, 130));
		panelText.setForeground(new Color(25, 25, 112));
		panelText.setBounds(210, 114, 325, 134);
		contentPane.add(panelText);
		panelText.setLayout(null);

		JLabel lblUserName = new JLabel("Username");
		lblUserName.setForeground(new Color(28, 57, 43));
		lblUserName.setFont(new Font("Verdana", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(0, 0, 78, 34);
		panelText.add(lblUserName);

		textField = new JTextField();
		textField.setBounds(63, 40, 239, 25);
		panelText.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(63, 106, 239, 25);
		panelText.add(passwordField);

		JLabel lblUserLogo = new JLabel("");
		lblUserLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogo.setBounds(10, 40, 46, 25);
		ImageIcon imgUser = new ImageIcon(getClass().getClassLoader().getResource("LoginUser.png"));
		lblUserLogo.setIcon(imgUser);
		panelText.add(lblUserLogo);

		JLabel lblPassLogo = new JLabel("");
		lblPassLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassLogo.setBounds(10, 104, 46, 28);
		ImageIcon imgPass = new ImageIcon(getClass().getClassLoader().getResource("PassIcon.png"));
		lblPassLogo.setIcon(imgPass);
		panelText.add(lblPassLogo);
		
		JLabel lblPassWord = new JLabel("Password");
		lblPassWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassWord.setForeground(new Color(28, 57, 43));
		lblPassWord.setFont(new Font("Verdana", Font.BOLD, 14));
		lblPassWord.setBounds(0, 72, 78, 34);
		panelText.add(lblPassWord);

		JPanel panelButton = new JPanel();
		panelButton.setBackground(new Color(87, 173, 130));
		panelButton.setBounds(210, 247, 328, 68);
		contentPane.add(panelButton);
		panelButton.setLayout(null);

		btnLogin = new JButton("");
		btnLogin.setBounds(224, 25, 88, 34);
		ImageIcon igmLoginBtn = new ImageIcon(getClass().getClassLoader().getResource("LoginButton.png"));
		btnLogin.setIcon(igmLoginBtn);
		panelButton.add(btnLogin);
	}

	public static void addEvent() {
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dangNhap();
			}

		});
		
		passwordField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
				
			}
			
		});
	}
	
	public static void dangNhap() {
		if(rdbtnManager.isSelected()) {
			DangNhapController dangNhapQuanLyController = new DangNhapController();
			DangNhap dangnhap = dangNhapQuanLyController.login(textField.getText());
			if(dangnhap != null && BCrypt.checkpw(passwordField.getText(), dangnhap.getPassWord())) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GiaoDienNhanVien frameManager = new GiaoDienNhanVien(textField.getText());
							frameManager.setVisible(true);
							frameManager.hienthiPanelPhong();
							frameManager.hienThiDanhSachKhachHang();
							frameManager.addHoverBtn();
							frameManager.addButton();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Sai Thông Tin Đăng Nhập", "Login Failed", JOptionPane.ERROR_MESSAGE);
				passwordField.setText(null);
			}
		}
		else if(rdbtnStaff.isSelected()) {
			DangNhapController dangNhapNhanVienController = new DangNhapController();
			DangNhap dangNhap = dangNhapNhanVienController.login(textField.getText());
			System.out.println(bCypt(passwordField.getText()));
			if(dangNhap != null && BCrypt.checkpw(passwordField.getText(), dangNhap.getPassWord())) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GiaoDienNhanVien frameStaff = new GiaoDienNhanVien(textField.getText());
							frameStaff.setVisible(true);
							frameStaff.hienthiPanelPhong();
							frameStaff.hienThiDanhSachKhachHang();
							frameStaff.addHoverBtn();
							frameStaff.addButton();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Sai Thông Tin Đăng Nhập", "Login Failed", JOptionPane.ERROR_MESSAGE);
				passwordField.setText(null);
			}
			
		}
		else if(!rdbtnManager.isSelected() && !rdbtnStaff.isSelected()) {
			JOptionPane.showMessageDialog(null, "Bạn Phải Chọn Vai Trò", "Login Failed", JOptionPane.ERROR_MESSAGE);
			passwordField.setText(null);
		}
		else {
			JOptionPane.showMessageDialog(null, "Sai Thông Tin Đăng Nhập", "Login Failed", JOptionPane.ERROR_MESSAGE);
			passwordField.setText(null);
		}
	}

	private static String bCypt(String original) {
		String generatePasswordHash = BCrypt.hashpw(original, BCrypt.gensalt(12));
		return generatePasswordHash;
		
	}
}
