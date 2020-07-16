package quanly.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;

import quanly.controller.DangNhapController;
import quanly.controller.DanhSachPhongController;
import quanly.controller.LayDanhSachKhachHang;
import quanly.model.DangNhap;
import quanly.model.KhachHang;
import quanly.vendors.BCrypt;
import quanly.vendors.CustomTabbedPaneUI;
import quanly.vendors.CustomTabbedPaneUIMain;

import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Toolkit;

public class GiaoDienNhanVien extends JFrame {
	private static ImageIcon imgFreeRoom = new ImageIcon(
			DanhSachPhongController.class.getClassLoader().getResource("hotel_freeRoom.png"));
	private static ImageIcon imgOccupied = new ImageIcon(
			DanhSachPhongController.class.getClassLoader().getResource("hotel_occupiedRoom.png"));

	private JPanel contentPane, panelCheckIn, panelCheckOut;

	private static JPanel panel1stFloor, panel2ndFloor, panel3rdFloor, panel1stFloorInside, panel2ndFloorInside,
			panel3rdFloorInside, panelRoomList, panelDashBoard, panelListClients, panelClients, panelFillFull;
	private static JButton btnExit, btnMinimize, btnRestoreDown, btnGetDateCheckIn, btnGetDateCheckOut, btnAddClient,
			btnSearchInfo, btnCheckOut, btnUpdateInfo, btnThemDichVu, btnRefresh, btnGetDateCheckInInternal,
			btnGetDateCheckOutInternal, btnUpdateInternal, btnConfirmChangePass;

	private static JLabel lblBackground1stFloor, lblBackground2ndFloor, lblBackground3rdFloor, lblCheckIn,
			lblNameClient, lblGender, lblPhoneNum, lblTimeCheckIn, lblTimeCheckOut, lblRoomNumber, lblBackApp,
			lblIndentityCheckOut, lblINameCheckOut, lblIIndentityCheckOut, lblPhoneNumCheckOut, lblIServiceUsed,
			lblICheckInTime1, lblCheckOutTime1, lblDiscount, lblTotal, lblCheckOut, lblCountCurrentGuests,
			lblCurrentClients, lblEmptyRoom, lblCountCurrentFreeRoom, lblSoNguoi, lblCurrentFreeRoom,
			lblTotalOccupiedRoom, lblTimeCheckOutInternal, lblPhoneNumInternal, lblNationalityInternal,
			lblRoomNumberInternal, lblRoomBooked, lblHotelLogo, lblCurentDate, lblGoodDay, lblIcon, lblPercentageBook,
			lblUsername, lblNewPass, lblCurrentPass, lblConfirmPass;

	private static JTabbedPane tabbedPane, tabbedPaneFloor, tabbedPaneCheckInCheckOut;
	private static JDateChooser dateChooserIn, dateChooserOut, dateChooserInInternal, dateChooserOutInternal;
	private static JTextField textFieldName, textFieldCMND, textFieldPhoneNum, textFieldCheckInDate,
			textFieldCheckOutDate, textFieldNameOut, textFieldNationNalityCheckOut, textFieldPhoneNumCheckOut,
			textFieldTotal, textFieldTimeIntoOut, textFieldTimeOuttoCheck, textFieldDiscount, textFieldSoNguoi,
			textFieldCheckInDateInternal, textFieldCheckOutDateInternal, textFieldPhoneInternal, textFieldCMNDInternal,
			textFieldSoNguoiInternal, textFieldNameClientInternal, textFieldUsername;
	private static JPasswordField textFieldCurrentPass, textFieldConfirmNewPass, textFieldNewPass;
	private static JComboBox cbbListRoom, cbbListServicesUsed, cbbListRoomCheckOut, cbbListRoomInternal;
	private static JLocaleChooser localeChooser, localeChooserInternal;
	private static JRadioButton rdbtnMale, rdbtnFemale, rdbtnMaleInternal, rdbtnFemaleInternal;
	private static JScrollPane jScrollPane;
	// get real time day
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private static DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	LocalDateTime now = LocalDateTime.now();
	private String currentDateAndTime = dtf.format(now);
	private String currtentTime = currentDateAndTime.substring(10);
	private static String userName;
	private static ImageIcon imgBackGroundPanel = new ImageIcon(
			GiaoDienNhanVien.class.getClassLoader().getResource("backChinh.png"));

	private static int countClickRestore = 0, countCurrentGuest = 0, IDKhachHang, currentRoom;

	private static ArrayList<JLabel> labelRoom;
	private static ArrayList<String> occupiedRoom;
	private static ArrayList<String> freeRoom;
	// private static ArrayList<JPanel> panelRoom;
	private static LinkedHashMap<Integer, JButton> phong;
	private static JTable table;
	private static DefaultTableModel defaultTableModel;

	// list empty room
	private static HashMap<Integer, Integer> danhSachPhongTrong;
	private static LinkedHashMap<Integer, JPanel> panelRoom;
	private static Vector<KhachHang> khachHang;
	private static JInternalFrame internalFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienNhanVien frame = new GiaoDienNhanVien("quanly");
					frame.setVisible(true);
					hienthiPanelPhong();
					hienThiDanhSachKhachHang();
					addHoverBtn();
					addButton();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GiaoDienNhanVien(String role) {
		userName = role;
		ImageIcon imgIcon = new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("hotel_icon.png"));
		this.setIconImage(imgIcon.getImage());
		this.setTitle("Giao Diện Nhân Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 1431, 744);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTitleBar = new JPanel();
		panelTitleBar.setBackground(new Color(51, 51, 51));
		panelTitleBar.setBounds(0, 0, 1369, 32);
		panelTitleBar.setLayout(null);
		contentPane.add(panelTitleBar);

		btnExit = new JButton(exitAction);
		btnExit.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		btnExit.setFocusPainted(false);
		btnExit.setOpaque(false);
		btnExit.setText("x");
		btnExit.setBackground(new Color(0, 0, 0, 125));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBorder(null);
		btnExit.setBounds(1325, -3, 42, 35);
		panelTitleBar.add(btnExit);

		btnRestoreDown = new JButton("");
		btnRestoreDown
				.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("restoreDownBtn.png")));
		btnRestoreDown.setFont(new Font("Segoe MDL2 Assets", Font.BOLD, 16));
		btnRestoreDown.setOpaque(false);
		btnRestoreDown.setBackground(new Color(0, 0, 0, 125));
		btnRestoreDown.setFocusPainted(false);
		btnRestoreDown.setBounds(1281, 0, 44, 32);
		btnRestoreDown.setBorder(null);
		panelTitleBar.add(btnRestoreDown);

		btnMinimize = new JButton(minimizeAction);
		btnMinimize.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnMinimize.setOpaque(false);
		btnMinimize.setText("-");
		btnMinimize.setFocusPainted(false);
		btnMinimize.setBackground(new Color(0, 0, 0, 125));
		btnMinimize.setForeground(Color.WHITE);
		btnMinimize.setBorder(null);
		btnMinimize.setBounds(1235, 0, 44, 32);
		panelTitleBar.add(btnMinimize);

		JLabel lblNewLabel = new JLabel("Hotel Management");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(40, 0, 232, 32);
		panelTitleBar.add(lblNewLabel);

		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("hotel_iconTop.png")));
		lblIcon.setBounds(0, 0, 44, 32);
		panelTitleBar.add(lblIcon);

		/*-------------------------Date---------------------*/

		JPanel panelList = new JPanel();
		panelList.setOpaque(false);
		panelList.setBackground(new Color(0, 0, 0, 125));
		panelList.setBounds(0, 114, 1365, 659);
		contentPane.add(panelList);
		panelList.setLayout(null);
		ImageIcon imgCalendar = new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("calendar.png"));

		/*-------------------------TAB LIST---------------------*/
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(-5, 68, 1398, 731);
		tabbedPane.setUI(new CustomTabbedPaneUIMain());
		panelList.add(tabbedPane);

		/*-------------------------ROOM LIST---------------------*/

		panelRoomList = new JPanel();
		tabbedPane.setFont(new Font("Verdana", Font.BOLD, 13));
		ImageIcon imgRoomList = new ImageIcon(getClass().getClassLoader().getResource("hotel_room1.png"));
		tabbedPane.addTab("              Phòng            ", imgRoomList, panelRoomList, null);
		tabbedPane.setOpaque(false);
		tabbedPane.setBackgroundAt(0, new Color(2, 132, 68));
		tabbedPane.setForegroundAt(0, Color.white);
		// tabbedPane.setBackgroundAt(0, new Color(213, 243, 254));
		panelRoomList.setLayout(null);

		ChangeListener changeListener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				if (sourceTabbedPane.getSelectedIndex() == 1) {
					UIManager.put("TabbedPane.selected", new Color(51, 51, 51));
					panelList.setBackground(new Color(51, 51, 51));
				} else if (sourceTabbedPane.getSelectedIndex() == 2) {

					panelList.setBackground(new Color(51, 51, 51));

				} else if (sourceTabbedPane.getSelectedIndex() == 3) {

					panelList.setBackground(new Color(51, 51, 51));
				} else if (sourceTabbedPane.getSelectedIndex() == 5) {

					panelList.setBackground(new Color(51, 51, 51));
				}
			}
		};
		tabbedPane.addChangeListener(changeListener);

		tabbedPaneFloor = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneFloor.setBounds(0, 0, 1114, 634);
		panelRoomList.add(tabbedPaneFloor);
		tabbedPaneFloor.setBackground(new Color(255, 255, 255));
		tabbedPaneFloor.setUI(new CustomTabbedPaneUI());

		panel1stFloor = new JPanel();
		panel1stFloor.setBackground(new Color(0, 100, 0, 100));
		panel1stFloor.setLayout(null);
		tabbedPaneFloor.addTab("Tầng 1", null, panel1stFloor, null);

		lblBackground1stFloor = new JLabel("");
		lblBackground1stFloor.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground1stFloor.setIcon(imgBackGroundPanel);
		lblBackground1stFloor.setBounds(0, 0, 1106, 588);
		panel1stFloor.add(lblBackground1stFloor);
		tabbedPaneFloor.setFont(new Font("Verdana", Font.BOLD, 17));

		panel2ndFloor = new JPanel();
		panel2ndFloor.setBackground(new Color(220, 221, 229));
		panel2ndFloor.setLayout(null);
		tabbedPaneFloor.addTab("Tầng 2", null, panel2ndFloor, null);

		lblBackground2ndFloor = new JLabel("");
		lblBackground2ndFloor.setIcon(imgBackGroundPanel);
		lblBackground2ndFloor.setBounds(0, 0, 1088, 571);
		panel2ndFloor.add(lblBackground2ndFloor);

		panel3rdFloor = new JPanel();
		panel3rdFloor.setBackground(new Color(220, 221, 229));
		panel3rdFloor.setLayout(null);
		tabbedPaneFloor.addTab("Tầng 3", null, panel3rdFloor, null);

		lblBackground3rdFloor = new JLabel("");
		lblBackground3rdFloor.setIcon(imgBackGroundPanel);
		lblBackground3rdFloor.setBounds(0, 0, 1088, 571);
		panel3rdFloor.add(lblBackground3rdFloor);

		/*-------------------------CHECK IN / CHECK OUT LIST---------------------*/

		/*----------------CHECK IN ---------------------------------*/

		JPanel panelCheckInCheckOut = new JPanel();
		ImageIcon imgBooking = new ImageIcon(getClass().getClassLoader().getResource("hotel_booking.png"));
		tabbedPane.addTab("Check in / Check out", imgBooking, panelCheckInCheckOut, null);
		tabbedPane.setForegroundAt(1, Color.white);
		tabbedPane.setBackgroundAt(1, new Color(2, 132, 68));
		panelCheckInCheckOut.setLayout(null);

		tabbedPaneCheckInCheckOut = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCheckInCheckOut.setBounds(0, 0, 1114, 750);
		panelCheckInCheckOut.add(tabbedPaneCheckInCheckOut);
		tabbedPaneCheckInCheckOut.setUI(new CustomTabbedPaneUI());
		tabbedPaneCheckInCheckOut.setFont(new Font("Verdana", Font.BOLD, 17));

		panelCheckIn = new JPanel();
		tabbedPaneCheckInCheckOut.addTab("Check In", null, panelCheckIn, null);
		panelCheckIn.setLayout(null);

		lblCheckIn = new JLabel("");
		lblCheckIn.setIcon(imgBackGroundPanel);
		lblCheckIn.setBounds(0, -78, 1154, 708);
		panelCheckIn.add(lblCheckIn);

		lblNameClient = new JLabel("Họ và Tên");
		lblNameClient.setForeground(Color.WHITE);
		lblNameClient.setHorizontalAlignment(SwingConstants.LEFT);
		lblNameClient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNameClient.setBounds(15, 73, 137, 48);
		lblCheckIn.add(lblNameClient);

		textFieldName = new JTextField();
		textFieldName.setToolTipText("");
		textFieldName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldName.setForeground(Color.WHITE);
		textFieldName.setOpaque(false);
		textFieldName.setBackground(new Color(0, 0, 0, 125));
		textFieldName.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		textFieldName.setBounds(96, 125, 240, 32);
		lblCheckIn.add(textFieldName);
		textFieldName.setColumns(10);

		lblGender = new JLabel("Giới Tính");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblGender.setBounds(15, 188, 137, 48);
		lblCheckIn.add(lblGender);

		rdbtnFemale = new JRadioButton("Nữ");
		rdbtnFemale.setOpaque(false);
		rdbtnFemale.setBackground(new Color(0, 0, 0, 125));
		rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setBounds(287, 240, 49, 23);
		lblCheckIn.add(rdbtnFemale);

		rdbtnMale = new JRadioButton("Nam");
		rdbtnMale.setOpaque(false);
		rdbtnMale.setBackground(new Color(0, 0, 0, 125));
		rdbtnMale.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnMale.setBounds(96, 240, 63, 23);
		rdbtnMale.setForeground(Color.WHITE);
		lblCheckIn.add(rdbtnMale);

		ButtonGroup btnGroupGenders = new ButtonGroup();
		btnGroupGenders.add(rdbtnMale);
		btnGroupGenders.add(rdbtnFemale);

		JLabel lblNationality = new JLabel("Quốc Tịch");
		lblNationality.setHorizontalAlignment(SwingConstants.LEFT);
		lblNationality.setForeground(Color.WHITE);
		lblNationality.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNationality.setBounds(15, 289, 137, 48);
		lblCheckIn.add(lblNationality);

		JLabel lblCMND = new JLabel("Số CMND");
		lblCMND.setHorizontalAlignment(SwingConstants.LEFT);
		lblCMND.setForeground(Color.WHITE);
		lblCMND.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblCMND.setBounds(615, 73, 137, 48);
		lblCheckIn.add(lblCMND);

		textFieldCMND = new JTextField();
		textFieldCMND.setForeground(Color.WHITE);
		textFieldCMND.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldCMND.setColumns(10);
		textFieldCMND.setOpaque(false);
		textFieldCMND.setBackground(new Color(128, 128, 128));
		textFieldCMND.setBorder(new LineBorder(null, 2));
		textFieldCMND.setBounds(736, 125, 240, 32);
		lblCheckIn.add(textFieldCMND);

		lblPhoneNum = new JLabel("Số Điện Thoại");
		lblPhoneNum.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNum.setForeground(Color.WHITE);
		lblPhoneNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPhoneNum.setBounds(615, 188, 137, 48);
		lblCheckIn.add(lblPhoneNum);

		textFieldPhoneNum = new JTextField();
		textFieldPhoneNum.setForeground(Color.WHITE);
		textFieldPhoneNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldPhoneNum.setColumns(10);
		textFieldPhoneNum.setOpaque(false);
		textFieldPhoneNum.setBorder(new LineBorder(null, 2));
		textFieldPhoneNum.setBounds(736, 240, 240, 32);
		lblCheckIn.add(textFieldPhoneNum);

		lblTimeCheckIn = new JLabel("Ngày Đến");
		lblTimeCheckIn.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeCheckIn.setForeground(Color.WHITE);
		lblTimeCheckIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblTimeCheckIn.setBounds(615, 299, 137, 48);
		lblCheckIn.add(lblTimeCheckIn);

		dateChooserIn = new JDateChooser();
		dateChooserIn.getCalendarButton().setOpaque(false);
		dateChooserIn.getCalendarButton().setBackground(new Color(0, 0, 0, 125));
		dateChooserIn.setBounds(976, 349, 25, 32);
		lblCheckIn.add(dateChooserIn);

		textFieldCheckInDate = new JTextField();
		textFieldCheckInDate.setForeground(Color.WHITE);
		textFieldCheckInDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldCheckInDate.setColumns(10);
		textFieldCheckInDate.setOpaque(false);
		textFieldCheckInDate.setBackground(new Color(0, 0, 0, 125));
		textFieldCheckInDate.setBorder(new LineBorder(null, 2));
		textFieldCheckInDate.setBounds(738, 349, 240, 32);
		lblCheckIn.add(textFieldCheckInDate);

		btnGetDateCheckIn = new JButton("->");
		btnGetDateCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldCheckInDate.setText("  " + df.format(dateChooserIn.getDate()));
			}
		});
		btnGetDateCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnGetDateCheckIn.setBounds(1000, 350, 25, 30);
		lblCheckIn.add(btnGetDateCheckIn);

		lblTimeCheckOut = new JLabel("Ngày Đi(dự kiến)");
		lblTimeCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeCheckOut.setForeground(Color.WHITE);
		lblTimeCheckOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblTimeCheckOut.setBounds(615, 410, 170, 48);
		lblCheckIn.add(lblTimeCheckOut);

		textFieldCheckOutDate = new JTextField();
		textFieldCheckOutDate.setForeground(Color.WHITE);
		textFieldCheckOutDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldCheckOutDate.setColumns(10);
		textFieldCheckOutDate.setOpaque(false);
		textFieldCheckOutDate.setBackground(new Color(0, 0, 0, 125));
		textFieldCheckOutDate.setBorder(new LineBorder(null, 2));
		textFieldCheckOutDate.setBounds(738, 470, 240, 32);
		lblCheckIn.add(textFieldCheckOutDate);

		dateChooserOut = new JDateChooser();
		dateChooserOut.getCalendarButton().setOpaque(false);
		dateChooserOut.getCalendarButton().setBackground(new Color(73, 112, 111));
		dateChooserOut.setBounds(976, 470, 25, 32);
		lblCheckIn.add(dateChooserOut);

		btnGetDateCheckOut = new JButton("->");
		btnGetDateCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println("ngày nhận: " + dateFormat.format(dateChooserIn.getDate()));
				System.out.println("ngày trả: " + dateFormat.format(dateChooserOut.getDate()));
				String checkInDate = dateFormat.format(dateChooserIn.getDate());
				String checkOutDate = dateFormat.format(dateChooserOut.getDate());
				if (checkOutDate.compareTo(checkInDate) < 0) {
					JOptionPane.showMessageDialog(null, "Ngày Check Out phải xảy ra sau ngày Check In");
					textFieldCheckOutDate.setText("");
				} else {
					textFieldCheckOutDate.setText("  " + df.format(dateChooserOut.getDate()));
				}

			}
		});
		btnGetDateCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnGetDateCheckOut.setBounds(1000, 470, 25, 30);
		lblCheckIn.add(btnGetDateCheckOut);

		lblRoomNumber = new JLabel("Số Phòng");
		lblRoomNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNumber.setForeground(Color.WHITE);
		lblRoomNumber.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblRoomNumber.setBounds(15, 410, 137, 48);
		lblCheckIn.add(lblRoomNumber);

		localeChooser = new JLocaleChooser();
		localeChooser.setMaximumRowCount(6);
		localeChooser.setForeground(Color.WHITE);
		localeChooser.setFont(new Font("Tahoma", Font.PLAIN, 17));
		localeChooser.setOpaque(false);
		localeChooser.setBackground(new Color(0, 0, 0, 125));
		localeChooser.setBorder(new LineBorder(null, 2));
		localeChooser.setBounds(96, 349, 240, 32);
		lblCheckIn.add(localeChooser);

		cbbListRoom = new JComboBox();
		cbbListRoom.setMaximumRowCount(6);
		cbbListRoom.setForeground(new Color(255, 255, 255));
		JScrollPane jScrollPaneListRoom = new JScrollPane(cbbListRoom, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPaneListRoom.setBounds(96, 470, 240, 32);
		jScrollPaneListRoom.setOpaque(false);
		cbbListRoom.setBackground(new Color(0, 0, 0, 125));
		cbbListRoom.setBorder(new LineBorder(null, 2));
		lblCheckIn.add(jScrollPaneListRoom);

		btnAddClient = new JButton("");
		btnAddClient.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("confirmBtn.png")));
		btnAddClient.setBounds(950, 540, 111, 53);
		lblCheckIn.add(btnAddClient);

		lblSoNguoi = new JLabel("Số Người");
		lblSoNguoi.setForeground(Color.WHITE);
		lblSoNguoi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblSoNguoi.setBounds(10, 520, 105, 41);
		lblCheckIn.add(lblSoNguoi);

		textFieldSoNguoi = new JTextField();
		textFieldSoNguoi.setToolTipText("");
		textFieldSoNguoi.setOpaque(false);
		textFieldSoNguoi.setForeground(Color.WHITE);
		textFieldSoNguoi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldSoNguoi.setColumns(10);
		textFieldSoNguoi.setBorder(new LineBorder(null, 2, true));
		textFieldSoNguoi.setBackground(new Color(0, 0, 0, 125));
		textFieldSoNguoi.setBounds(93, 570, 240, 32);
		lblCheckIn.add(textFieldSoNguoi);

		panelCheckOut = new JPanel();
		tabbedPaneCheckInCheckOut.addTab("Check Out", null, panelCheckOut, null);
		panelCheckOut.setLayout(null);

		/*----------------CHECK OUT ---------------------------------*/

		lblCheckOut = new JLabel("");
		lblCheckOut.setIcon(imgBackGroundPanel);
		lblCheckOut.setBounds(0, -50, 1154, 680);
		panelCheckOut.add(lblCheckOut);

		lblIndentityCheckOut = new JLabel("Phòng");
		lblIndentityCheckOut.setForeground(Color.WHITE);
		lblIndentityCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblIndentityCheckOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblIndentityCheckOut.setBounds(10, 62, 75, 48);
		lblCheckOut.add(lblIndentityCheckOut);

		cbbListRoomCheckOut = new JComboBox();
		JScrollPane jScrollPaneCheckOut = new JScrollPane(cbbListRoomCheckOut, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPaneCheckOut.setBounds(82, 109, 208, 33);
		lblCheckOut.add(jScrollPaneCheckOut);

		lblINameCheckOut = new JLabel("Họ Tên");
		lblINameCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblINameCheckOut.setForeground(Color.WHITE);
		lblINameCheckOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblINameCheckOut.setBounds(10, 171, 208, 48);
		lblCheckOut.add(lblINameCheckOut);

		textFieldNameOut = new JTextField();
		textFieldNameOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldNameOut.setEditable(false);
		textFieldNameOut.setBounds(82, 225, 208, 33);
		lblCheckOut.add(textFieldNameOut);
		textFieldNameOut.setColumns(10);

		lblIIndentityCheckOut = new JLabel("Quốc Tịch");
		lblIIndentityCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblIIndentityCheckOut.setForeground(Color.WHITE);
		lblIIndentityCheckOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblIIndentityCheckOut.setBounds(10, 283, 208, 48);
		lblCheckOut.add(lblIIndentityCheckOut);

		textFieldNationNalityCheckOut = new JTextField();
		textFieldNationNalityCheckOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		textFieldNationNalityCheckOut.setEditable(false);
		textFieldNationNalityCheckOut.setColumns(10);
		textFieldNationNalityCheckOut.setBounds(82, 342, 208, 33);
		lblCheckOut.add(textFieldNationNalityCheckOut);

		lblPhoneNumCheckOut = new JLabel("Số Điện Thoại");
		lblPhoneNumCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumCheckOut.setForeground(Color.WHITE);
		lblPhoneNumCheckOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPhoneNumCheckOut.setBounds(10, 413, 208, 48);
		lblCheckOut.add(lblPhoneNumCheckOut);

		textFieldPhoneNumCheckOut = new JTextField();
		textFieldPhoneNumCheckOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldPhoneNumCheckOut.setEditable(false);
		textFieldPhoneNumCheckOut.setColumns(10);
		textFieldPhoneNumCheckOut.setBounds(82, 472, 208, 33);
		lblCheckOut.add(textFieldPhoneNumCheckOut);

		lblIServiceUsed = new JLabel("Dịch Vụ Sử Dụng");
		lblIServiceUsed.setHorizontalAlignment(SwingConstants.LEFT);
		lblIServiceUsed.setForeground(Color.WHITE);
		lblIServiceUsed.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblIServiceUsed.setBounds(664, 62, 174, 48);
		lblCheckOut.add(lblIServiceUsed);

		cbbListServicesUsed = new JComboBox();
		cbbListServicesUsed.setBounds(813, 109, 206, 31);
		lblCheckOut.add(cbbListServicesUsed);

		lblICheckInTime1 = new JLabel("Ngày Đến");
		lblICheckInTime1.setHorizontalAlignment(SwingConstants.LEFT);
		lblICheckInTime1.setForeground(Color.WHITE);
		lblICheckInTime1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblICheckInTime1.setBounds(666, 171, 208, 48);
		lblCheckOut.add(lblICheckInTime1);

		textFieldTimeIntoOut = new JTextField();
		textFieldTimeIntoOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldTimeIntoOut.setEditable(false);
		textFieldTimeIntoOut.setColumns(10);
		textFieldTimeIntoOut.setBounds(813, 225, 208, 33);
		lblCheckOut.add(textFieldTimeIntoOut);

		lblCheckOutTime1 = new JLabel("Ngày Đi");
		lblCheckOutTime1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckOutTime1.setForeground(Color.WHITE);
		lblCheckOutTime1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblCheckOutTime1.setBounds(664, 283, 208, 48);
		lblCheckOut.add(lblCheckOutTime1);

		textFieldTimeOuttoCheck = new JTextField();
		textFieldTimeOuttoCheck.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldTimeOuttoCheck.setEditable(false);
		textFieldTimeOuttoCheck.setColumns(10);
		textFieldTimeOuttoCheck.setBounds(813, 342, 208, 33);
		lblCheckOut.add(textFieldTimeOuttoCheck);

		lblDiscount = new JLabel("Giảm Giá");
		lblDiscount.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiscount.setForeground(Color.WHITE);
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblDiscount.setBounds(664, 413, 208, 48);
		lblCheckOut.add(lblDiscount);

		textFieldDiscount = new JTextField();
		textFieldDiscount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldDiscount.setColumns(10);
		textFieldDiscount.setText("");
		textFieldDiscount.setBounds(813, 472, 208, 33);
		lblCheckOut.add(textFieldDiscount);

		lblTotal = new JLabel("Tổng");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblTotal.setBounds(388, 500, 208, 33);
		lblCheckOut.add(lblTotal);

		textFieldTotal = new JTextField();
		textFieldTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		textFieldTotal.setEditable(false);
		textFieldTotal.setBounds(455, 537, 243, 33);
		lblCheckOut.add(textFieldTotal);
		textFieldTotal.setColumns(10);

		btnSearchInfo = new JButton("Tìm");
		btnSearchInfo.setBounds(317, 109, 89, 33);
		lblCheckOut.add(btnSearchInfo);

		btnCheckOut = new JButton("");
		btnCheckOut.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("confirmBtn.png")));
		btnCheckOut.setBounds(908, 542, 111, 53);
		lblCheckOut.add(btnCheckOut);

		/*-------------------------CLIENTS---------------------*/
		panelClients = new JPanel();
		panelClients.setOpaque(false);
		panelClients.setBackground(new Color(0, 0, 0, 0));
		ImageIcon imgClients = new ImageIcon(getClass().getClassLoader().getResource("hotel_clients.png"));
		tabbedPane.addTab("      Khách Hàng             ", imgClients, panelClients, null);
		panelClients.setLayout(null);

		panelListClients = new JPanel();
		panelListClients.setOpaque(false);
		panelListClients.setBackground(new Color(0, 0, 0, 0));
		panelListClients.setBounds(0, 100, 1152, 526);
		panelClients.add(panelListClients);
		panelListClients.setLayout(null);

		btnUpdateInfo = new JButton("Sửa Thông Tin");
		btnUpdateInfo.setFocusPainted(false);
		btnUpdateInfo.setBounds(100, 11, 120, 46);
		panelClients.add(btnUpdateInfo);

		btnThemDichVu = new JButton("Thêm Dịch Vụ");
		btnThemDichVu.setFocusPainted(false);
		btnThemDichVu.setBounds(871, 11, 114, 46);
		panelClients.add(btnThemDichVu);

		btnRefresh = new JButton("Làm Mới");
		btnRefresh.setFocusPainted(false);
		btnRefresh.setBounds(495, 11, 120, 46);
		panelClients.add(btnRefresh);

		tabbedPane.setForegroundAt(2, Color.white);
		tabbedPane.setBackgroundAt(2, new Color(2, 132, 68));

		/*-------------------------SERVICES---------------------*/
		JPanel panelService = new JPanel();
		ImageIcon imgService = new ImageIcon(getClass().getClassLoader().getResource("hotel_service.png"));
		tabbedPane.addTab("             Dịch Vụ              ", imgService, panelService, null);
		panelService.setLayout(null);
		tabbedPane.setForegroundAt(3, Color.white);
		tabbedPane.setBackgroundAt(3, new Color(2, 132, 68));

		/*-------------------------HISTORY---------------------*/
		JPanel panelHistory = new JPanel();
		panelHistory.setBackground(Color.WHITE);
		ImageIcon imgHistory = new ImageIcon(getClass().getClassLoader().getResource("hotel_statics.png"));
		tabbedPane.addTab("Thống Kê Khách Hàng  ", imgHistory, panelHistory, null);
		panelHistory.setLayout(null);

		lblCurrentClients = new JLabel("");
		lblCurrentClients
				.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("currentGuests.png")));
		lblCurrentClients.setBackground(Color.WHITE);
		lblCurrentClients.setBounds(63, 46, 243, 120);
		panelHistory.add(lblCurrentClients);

		lblCountCurrentGuests = new JLabel();
		lblCountCurrentGuests.setForeground(Color.WHITE);
		lblCountCurrentGuests.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblCountCurrentGuests.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountCurrentGuests.setBounds(18, 40, 130, 47);
		lblCurrentClients.add(lblCountCurrentGuests);
		tabbedPane.setForegroundAt(4, Color.white);
		tabbedPane.setBackgroundAt(4, new Color(2, 132, 68));

		lblEmptyRoom = new JLabel("");
		lblEmptyRoom.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("currentFreeRoom.png")));
		lblEmptyRoom.setBounds(418, 46, 243, 120);
		panelHistory.add(lblEmptyRoom);

		lblCountCurrentFreeRoom = new JLabel();
		lblCountCurrentFreeRoom.setForeground(Color.WHITE);
		lblCountCurrentFreeRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountCurrentFreeRoom.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblCountCurrentFreeRoom.setBounds(18, 40, 130, 47);
		lblEmptyRoom.add(lblCountCurrentFreeRoom);

		JLabel lblCurrentOccupiedRoom = new JLabel("");
		lblCurrentOccupiedRoom
				.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("currentOccupiedRoom.png")));
		lblCurrentOccupiedRoom.setBounds(782, 46, 243, 120);
		panelHistory.add(lblCurrentOccupiedRoom);

		lblTotalOccupiedRoom = new JLabel("");
		lblTotalOccupiedRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalOccupiedRoom.setForeground(Color.WHITE);
		lblTotalOccupiedRoom.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTotalOccupiedRoom.setBounds(18, 40, 122, 48);
		lblCurrentOccupiedRoom.add(lblTotalOccupiedRoom);

		lblPercentageBook = new JLabel("");
		lblPercentageBook.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("round.png")));
		lblPercentageBook.setBounds(316, 266, 460, 227);
		panelHistory.add(lblPercentageBook);

		lblRoomBooked = new JLabel("Room Booked");
		lblRoomBooked.setForeground(Color.GRAY);
		lblRoomBooked.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomBooked.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		lblRoomBooked.setBounds(405, 250, 257, 36);
		panelHistory.add(lblRoomBooked);

		lblRoomBooked = new JLabel();
		lblRoomBooked.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomBooked.setOpaque(false);
		lblRoomBooked.setBackground(new Color(0, 0, 0, 125));
		lblRoomBooked.setForeground(Color.WHITE);
		lblRoomBooked.setFont(new Font("Tekton Pro Cond", Font.BOLD, 75));
		lblRoomBooked.setBounds(165, 80, 173, 107);
		lblPercentageBook.add(lblRoomBooked);

		JPanel panelManageAccount = new JPanel();
		panelManageAccount.setBackground(new Color(2, 132, 68));
		ImageIcon imgBill = new ImageIcon(getClass().getClassLoader().getResource("hotel_bill.png"));
		tabbedPane.addTab("Quản lý tài khoản            ", imgBill, panelManageAccount, null);
		panelManageAccount.setLayout(null);

		JPanel panelChangePass = new JPanel();
		panelChangePass.setBackground(Color.WHITE);
		panelChangePass.setBounds(340, 11, 411, 543);
		panelManageAccount.add(panelChangePass);
		panelChangePass.setLayout(null);

		JLabel lblLogoInAdmin = new JLabel("");
		lblLogoInAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoInAdmin.setIcon(new ImageIcon(getClass().getClassLoader().getResource("hotel_logo.png")));
		lblLogoInAdmin.setBounds(0, 0, 411, 121);
		panelChangePass.add(lblLogoInAdmin);

		lblUsername = new JLabel("Tên đăng nhập");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblUsername.setBounds(10, 115, 176, 35);
		lblUsername.setForeground(new Color(2, 132, 68));
		panelChangePass.add(lblUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldUsername.setForeground(Color.GRAY);
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldUsername.setText(role);
		textFieldUsername.setEditable(false);
		textFieldUsername.setBounds(74, 161, 275, 35);
		panelChangePass.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		lblCurrentPass = new JLabel("Mật khẩu hiện tại");
		lblCurrentPass.setForeground(new Color(2, 132, 68));
		lblCurrentPass.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblCurrentPass.setBounds(10, 217, 176, 35);
		panelChangePass.add(lblCurrentPass);

		textFieldCurrentPass = new JPasswordField();
		textFieldCurrentPass.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldCurrentPass.setBackground(new Color(235, 238, 243));
		textFieldCurrentPass.setForeground(Color.black);
		textFieldCurrentPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldCurrentPass.setColumns(10);
		textFieldCurrentPass.setBounds(74, 263, 275, 35);
		panelChangePass.add(textFieldCurrentPass);

		lblNewPass = new JLabel("Mật khẩu mới");
		lblNewPass.setForeground(new Color(2, 132, 68));
		lblNewPass.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewPass.setBounds(10, 309, 176, 35);
		panelChangePass.add(lblNewPass);

		textFieldNewPass = new JPasswordField();
		textFieldNewPass.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldNewPass.setBackground(new Color(235, 238, 243));
		textFieldNewPass.setForeground(Color.black);
		textFieldNewPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldNewPass.setColumns(10);
		textFieldNewPass.setBounds(74, 355, 275, 35);
		panelChangePass.add(textFieldNewPass);

		lblConfirmPass = new JLabel("Xác nhận mật khẩu");
		lblConfirmPass.setForeground(new Color(2, 132, 68));
		lblConfirmPass.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblConfirmPass.setBounds(10, 401, 176, 35);
		panelChangePass.add(lblConfirmPass);

		textFieldConfirmNewPass = new JPasswordField();
		textFieldConfirmNewPass.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldConfirmNewPass.setBackground(new Color(235, 238, 243));
		textFieldConfirmNewPass.setForeground(Color.black);
		textFieldConfirmNewPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldConfirmNewPass.setColumns(10);
		textFieldConfirmNewPass.setBounds(74, 447, 275, 35);
		panelChangePass.add(textFieldConfirmNewPass);

		btnConfirmChangePass = new JButton("Xác Nhận");
		btnConfirmChangePass.setFocusPainted(false);
		btnConfirmChangePass.setBounds(297, 493, 104, 39);
		panelChangePass.add(btnConfirmChangePass);
		tabbedPane.setForegroundAt(5, Color.white);
		tabbedPane.setBackgroundAt(5, new Color(2, 132, 68));

		panelFillFull = new JPanel();
		panelFillFull.setBackground(new Color(2, 132, 68));
		ImageIcon imgFillFull = new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("fillFull.png"));
		tabbedPane.addTab("", imgFillFull, panelFillFull, null);

		JPanel panelBorder = new JPanel();
		panelBorder.setBackground(new Color(2, 132, 68));
		panelBorder.setBounds(-5, 61, 1387, 10);
		panelList.add(panelBorder);

		lblCurentDate = new JLabel(dtf.format(now));
		lblCurentDate.setBounds(388, 0, 177, 43);
		panelList.add(lblCurentDate);
		lblCurentDate.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblSlogan = new JLabel("Our world is your playground");
		lblSlogan.setBounds(978, -19, 387, 90);
		panelList.add(lblSlogan);
		lblSlogan.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 24));
		lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);

		lblHotelLogo = new JLabel("");
		lblHotelLogo.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("hotelLogo.png")));
		lblHotelLogo.setBounds(0, 30, 286, 147);
		contentPane.add(lblHotelLogo);

		JButton btnCalendar = new JButton("");
		btnCalendar.setBounds(306, 73, 73, 73);
		contentPane.add(btnCalendar);
		btnCalendar.setFocusPainted(false);
		btnCalendar.setOpaque(false);
		btnCalendar.setBorder(null);
		btnCalendar.setCursor(Cursor.getPredefinedCursor(12));
		btnCalendar.setBackground(new Color(0, 0, 0, 125));
		btnCalendar.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("calendar.png")));

		String welcome = "Hello " + role + ",";
		JLabel lblHello = new JLabel(welcome);
		lblHello.setForeground(Color.GRAY);
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setFont(new Font("Sitka Subheading", Font.ITALIC, 15));
		lblHello.setBounds(1198, 50, 154, 48);
		contentPane.add(lblHello);

		lblGoodDay = new JLabel("  Have a good day!");
		lblGoodDay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGoodDay.setForeground(Color.GRAY);
		lblGoodDay.setFont(new Font("Sitka Subheading", Font.ITALIC, 15));
		lblGoodDay.setBounds(1230, 90, 135, 30);
		contentPane.add(lblGoodDay);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("hotel_logo.png")));
		lblLogo.setBounds(560, 40, 399, 137);
		contentPane.add(lblLogo);

	}

	public static void hienthiPanelPhong() {
		int xPanel = 25, yPanel = 20;
		int i = 100;
		labelRoom = new ArrayList<JLabel>();
		panelRoom = new LinkedHashMap<Integer, JPanel>();
		while (i < 310) {
			if (i == 104 || i == 204 || i == 304) {
				xPanel = 25;
				yPanel = 210;
			} else if (i == 108 || i == 208 || i == 308) {
				xPanel = 298;
				yPanel = 400;
			} else if (i == 110 || i == 210) {
				xPanel = 25;
				yPanel = 20;
				i += 90;
			}
			JPanel pnlRoom = new JPanel();
			pnlRoom.setBackground(new Color(255, 255, 255));
			pnlRoom.setBounds(xPanel, yPanel, 226, 145);
			pnlRoom.setLayout(null);

			if (i % 100 == 0 || i == 101 || i == 102 || i == 201 || i == 202 || i == 301 || i == 302) {
				JLabel lblRoom = new JLabel("Phòng " + i + " VIP ");
				lblRoom.setFont(new Font("Tekton Pro Ext", Font.BOLD, 20));
				lblRoom.setHorizontalAlignment(SwingConstants.CENTER);
				lblRoom.setForeground(new Color(47, 79, 79));
				lblRoom.setBounds(0, 107, 226, 38);

				pnlRoom.add(lblRoom);

				labelRoom.add(lblRoom);
			} else {
				JLabel lblRoom = new JLabel("Phòng " + i);
				lblRoom.setFont(new Font("Tekton Pro Ext", Font.BOLD, 20));
				lblRoom.setHorizontalAlignment(SwingConstants.CENTER);
				lblRoom.setForeground(new Color(47, 79, 79));
				lblRoom.setBounds(0, 107, 226, 38);

				pnlRoom.add(lblRoom);

				labelRoom.add(lblRoom);
			}

			panelRoom.put(i, pnlRoom);
			xPanel += 273;

			i++;
		}

		int countFill = 0;
		for (JPanel showPanel : panelRoom.values()) {
			if (countFill < 10) {
				lblBackground1stFloor.add(showPanel);
			} else if (countFill >= 10 && countFill < 20) {
				lblBackground2ndFloor.add(showPanel);
			} else {
				lblBackground3rdFloor.add(showPanel);
			}
			countFill++;
		}
		truyXuatTinhTrangPhong();

	}

	public static void truyXuatTinhTrangPhong() {

		// hiển thị danh sách phòng trang 1
		int count = 0;
		DanhSachPhongController danhSachPhongController = new DanhSachPhongController();
		phong = danhSachPhongController.kiemTraPhong();
		Integer roomNum = new Integer(100);
		while (roomNum < 310) {
			if (roomNum == 110 || roomNum == 210) {
				roomNum += 90;
			}
			panelRoom.get(roomNum).add(phong.get(roomNum));
			roomNum++;
		}

		// hiển thị phòng đang trống trang 2 và trang update
		cbbListRoomInternal = new JComboBox();
		cbbListRoomInternal.removeAllItems();
		cbbListRoomInternal.addItem("Phòng hiện tại");
		DanhSachPhongController phongTrong = new DanhSachPhongController();
		danhSachPhongTrong = phongTrong.layDanhSachPhongTrong();
		cbbListRoom.removeAllItems();
		cbbListRoomCheckOut.removeAllItems();
		for (Map.Entry<Integer, Integer> entry : danhSachPhongTrong.entrySet()) {
			if (entry.getValue() == 0) {
				if (entry.getKey() % 100 == 0 || entry.getKey() == 101 || entry.getKey() == 102 || entry.getKey() == 201
						|| entry.getKey() == 202 || entry.getKey() == 301 || entry.getKey() == 302) {
					cbbListRoomCheckOut.addItem(entry.getKey() + " VIP ");

				} else {
					cbbListRoomCheckOut.addItem(entry.getKey().toString());
				}

			} else {
				if (entry.getKey() % 100 == 0 || entry.getKey() == 101 || entry.getKey() == 102 || entry.getKey() == 201
						|| entry.getKey() == 202 || entry.getKey() == 301 || entry.getKey() == 302) {

					cbbListRoom.addItem(entry.getKey() + " VIP ");
					cbbListRoomInternal.addItem(entry.getKey() + " VIP ");
				} else {
					cbbListRoom.addItem(entry.getKey().toString());
					cbbListRoomInternal.addItem(entry.getKey().toString());
				}
			}
		}

	}

	public static void addHoverBtn() {
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnExit.setOpaque(true);
				btnExit.setBackground(new Color(232, 17, 35));
				// btnExit.setBackground(new Color(188, 52, 84));
				btnExit.setBorder(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnExit.setOpaque(false);
				btnExit.setBackground(new Color(0, 0, 0, 125));
			}

		});

		btnMinimize.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnMinimize.setOpaque(true);
				btnMinimize.setBackground(new Color(72, 72, 72));
				btnMinimize.setBorder(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnMinimize.setOpaque(false);
				btnMinimize.setBackground(new Color(0, 0, 0, 125));
			}

		});

		btnRestoreDown.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnRestoreDown.setOpaque(true);
				btnRestoreDown.setBackground(new Color(72, 72, 72));
				if (countClickRestore % 2 == 0) {
					btnRestoreDown.setIcon(
							new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("restoreDownBtn.png")));
				} else {
					btnRestoreDown.setIcon(
							new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("restoreDownToBtn.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnRestoreDown.setOpaque(false);
				btnRestoreDown.setBackground(new Color(0, 0, 0, 125));
				if (countClickRestore % 2 == 0) {
					btnRestoreDown.setIcon(
							new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("restoreDownBtn.png")));
				} else {
					btnRestoreDown.setIcon(
							new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("restoreDownToBtn.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				countClickRestore++;
				if (countClickRestore % 2 != 0) {
					btnRestoreDown.setIcon(
							new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("restoreDownToBtn.png")));
					btnRestoreDown.setBorder(null);
				} else {
					btnRestoreDown.setIcon(
							new ImageIcon(GiaoDienNhanVien.class.getClassLoader().getResource("restoreDownBtn.png")));
				}

			}

		});

	}

	public static void addButton() {

		// add clinet to database
		btnAddClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (textFieldName.getText().equals("") || textFieldPhoneNum.getText().equals("")
						|| textFieldCMND.getText().equals("") || !(rdbtnFemale.isSelected() || rdbtnMale.isSelected())
						|| textFieldCheckInDate.getText().equals("") || textFieldCheckOutDate.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Phải điền đầy đủ thông tin");
				} else {
					String nameClient = textFieldName.getText();
					String gender = "";
					if (rdbtnFemale.isSelected()) {
						gender = "Nữ";
					} else if (rdbtnMale.isSelected()) {
						gender = "Nam";
					}
					String nationality = localeChooser.getSelectedItem().toString();
					String identityCard = textFieldCMND.getText();
					String phoneNum = textFieldPhoneNum.getText();
					Integer rooomNumber = Integer.parseInt(cbbListRoom.getSelectedItem().toString().substring(0, 3));
					int indexCbbListroom = cbbListRoom.getSelectedIndex();

					SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
					String getDateCheckIn = sdf1.format(dateChooserIn.getDate()).toString();
					String getDateCheckOut = sdf1.format(dateChooserOut.getDate()).toString();
					int soNguoi = Integer.parseInt(textFieldSoNguoi.getText());
					java.util.Date date;
					try {
						date = sdf1.parse(getDateCheckIn);
						java.sql.Date sqlDateCheckIn = new java.sql.Date(date.getTime());
						date = sdf1.parse(getDateCheckOut);
						java.sql.Date sqlDateCheckOut = new java.sql.Date(date.getTime());
						KhachHang addClient = new KhachHang(nameClient, gender, nationality, identityCard, phoneNum,
								rooomNumber, sqlDateCheckIn, sqlDateCheckOut, soNguoi);
						LayDanhSachKhachHang addClienToDataBase = new LayDanhSachKhachHang();
						boolean checkAddClient = addClienToDataBase.themKhachHang(addClient);
						if (checkAddClient) {
							int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thêm?",
									"Warning", JOptionPane.YES_NO_OPTION);
							if (dialogResult == JOptionPane.YES_OPTION) {
								JOptionPane.showMessageDialog(null, "Lưu thành công");
								textFieldName.setText("");
								textFieldCMND.setText("");
								textFieldPhoneNum.setText("");
								textFieldSoNguoi.setText("");
								phong.get(rooomNumber).setIcon(imgOccupied);
								cbbListRoomCheckOut.addItem(cbbListRoom.getSelectedItem());
								cbbListRoom.removeItem(cbbListRoom.getSelectedItem());
								danhSachKhachHangRealTime();

							}

						} else {
							JOptionPane.showMessageDialog(null, "Check In thất bại");
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		// addBtn search info client
		btnSearchInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int roomNumber = Integer.parseInt(cbbListRoomCheckOut.getSelectedItem().toString().substring(0, 3));
				LayDanhSachKhachHang danhSachKhachHang = new LayDanhSachKhachHang();
				KhachHang khachHang = danhSachKhachHang.thongTinMotKhachHang(roomNumber);
				textFieldNameOut.setText(khachHang.getTenKhachHang());
				textFieldNationNalityCheckOut.setText(khachHang.getQuocTich());
				textFieldPhoneNumCheckOut.setText(khachHang.getSoDienThoai());
				textFieldTimeIntoOut.setText(khachHang.getNgayDen().toString());
				textFieldTimeOuttoCheck.setText(khachHang.getNgayDi().toString());
				String price = "";
				if (roomNumber % 100 == 0 || roomNumber == 101 || roomNumber == 102 || roomNumber == 201
						|| roomNumber == 202 || roomNumber == 301 || roomNumber == 302) {
					price = "1000000";
				} else {
					price = "500000";
				}
				if (textFieldDiscount.getText().equals("")) {
					textFieldDiscount.setText("0");
				}
				int discount = Integer.parseInt(textFieldDiscount.getText());
				long diff = khachHang.getNgayDi().getTime() - khachHang.getNgayDen().getTime();
				long khoangCach = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				int total = (int) ((Integer.parseInt(price) * khoangCach) - discount);
				textFieldTotal.setText(String.valueOf(total));

			}
		});

		// addBtn check Out client
		btnCheckOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int dialogResult = JOptionPane.showConfirmDialog(null, "Xác nhận Check Out", "Check Out",
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {

					LayDanhSachKhachHang danhSachKhachHang = new LayDanhSachKhachHang();
					int roomCheckOut = Integer
							.parseInt(cbbListRoomCheckOut.getSelectedItem().toString().substring(0, 3));
					System.out.println(roomCheckOut);
					boolean khachHangCheckOut = danhSachKhachHang.xoaKhachHang(roomCheckOut);
					if (khachHangCheckOut) {
						JOptionPane.showMessageDialog(null,
								"Anh(chị) " + textFieldNameOut.getText() + " Check out thành công");
						phong.get(roomCheckOut).setIcon(imgFreeRoom);
						cbbListRoom.addItem(cbbListRoomCheckOut.getSelectedItem());
						cbbListRoomCheckOut.removeItem(cbbListRoomCheckOut.getSelectedItem());
						textFieldNameOut.setText("");
						textFieldNationNalityCheckOut.setText("");
						textFieldPhoneNumCheckOut.setText("");
						textFieldTimeIntoOut.setText("");
						textFieldTimeOuttoCheck.setText("");
						textFieldTotal.setText("");
						textFieldDiscount.setText("");
						danhSachKhachHangRealTime();
					} else {
						JOptionPane.showMessageDialog(null,
								"Anh(chị) " + textFieldNameOut.getText() + " Check out thất bại");
					}
				}
			}
		});

		// choose addBtn update info
		btnUpdateInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Bạn cần chọn khách hàng muốn chỉnh sửa thông tin");
				} else {
					KhachHang khachHangUpdate = new KhachHang();
					khachHangUpdate.setIdKhachHang(defaultTableModel.getValueAt(selectedRow, 0).toString());
					khachHangUpdate.setTenKhachHang(defaultTableModel.getValueAt(selectedRow, 1).toString());
					khachHangUpdate.setGioiTinh(defaultTableModel.getValueAt(selectedRow, 2).toString());
					khachHangUpdate.setQuocTich(defaultTableModel.getValueAt(selectedRow, 3).toString());
					khachHangUpdate.setCmnd(defaultTableModel.getValueAt(selectedRow, 4).toString());
					khachHangUpdate.setSoDienThoai(defaultTableModel.getValueAt(selectedRow, 5).toString());
					khachHangUpdate.setSoPhong(
							Integer.parseInt(defaultTableModel.getValueAt(selectedRow, 6).toString().substring(0, 3)));
					khachHangUpdate.setNgayDen((java.sql.Date) defaultTableModel.getValueAt(selectedRow, 7));
					khachHangUpdate.setNgayDi((java.sql.Date) defaultTableModel.getValueAt(selectedRow, 8));
					khachHangUpdate.setSoNguoi((Integer) defaultTableModel.getValueAt(selectedRow, 9));

					int currentlyRoom = khachHangUpdate.getSoPhong();
					int IDKhacHang = Integer.parseInt(defaultTableModel.getValueAt(selectedRow, 0).toString());

					internalFrame(khachHangUpdate);
					table.setVisible(false);
					jScrollPane.setVisible(false);
				}

			}
		});

		// addBtn update client info

		// addBtn refresh
		btnRefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				truyXuatTinhTrangPhong();
				danhSachKhachHangRealTime();
			}

		});

		// addBtn confirm change pass
		btnConfirmChangePass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (textFieldCurrentPass.getText().isEmpty() || textFieldConfirmNewPass.getText().isEmpty()
						|| textFieldNewPass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin");
				} else {
					DangNhapController dangNhapController = new DangNhapController();
					DangNhap account = dangNhapController.login(textFieldUsername.getText());
					if (account != null && BCrypt.checkpw(textFieldCurrentPass.getText(), account.getPassWord())) {
						if (textFieldNewPass.getText().equals(textFieldConfirmNewPass.getText())) {
							DangNhapController dangNhapController2 = new DangNhapController();
							boolean noti = dangNhapController2.changePassword(textFieldUsername.getText(),
									textFieldNewPass.getText());
							if (noti) {
								JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
							} else {
								JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Xác nhậnkhông trùng khớp");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Sai thông tin đăng nhập");
					}
				}
			}
		});
	}

	public static void hienThiDanhSachKhachHang() {
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(32, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setRowHeight(25);
		table.setShowGrid(false);
		table.setFocusable(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setSelectionBackground(new Color(232, 57, 95));
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "H\u1ECD T\u00EAn", "Gi\u1EDBi T\u00EDnh", "Qu\u1ED1c T\u1ECBch",
						"CMND / Passport", "S\u0110T", "Ph\u00F2ng", "Ng\u00E0y \u0110\u1EBFn", "Ng\u00E0y \u0110i",
						"Số Người", "D\u1ECBch V\u1EE5" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, Integer.class, String.class, String.class, Integer.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(88);
		table.getColumnModel().getColumn(2).setPreferredWidth(54);
		table.getColumnModel().getColumn(3).setPreferredWidth(76);
		DefaultTableCellRenderer centerRenderers = new DefaultTableCellRenderer();
		centerRenderers.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		for (int i = 0; i < 11; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderers);
		}
		jScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(0, 0, 1116, 553);
		table.setBounds(0, 0, 1116, 553);
		panelListClients.add(jScrollPane);
		danhSachKhachHangRealTime();
	}

	public static void danhSachKhachHangRealTime() {
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		LayDanhSachKhachHang layDanhSachKhachHang = new LayDanhSachKhachHang();
		khachHang = layDanhSachKhachHang.danhSachKhachHang();
		for (KhachHang kHang : khachHang) {
			if (kHang.getSoPhong() % 100 == 0 || kHang.getSoPhong() == 101 || kHang.getSoPhong() == 102
					|| kHang.getSoPhong() == 201 || kHang.getSoPhong() == 202 || kHang.getSoPhong() == 301
					|| kHang.getSoPhong() == 302) {
				Object[] khach = { kHang.getIdKhachHang(), kHang.getTenKhachHang(), kHang.getGioiTinh(),
						kHang.getQuocTich(), kHang.getCmnd(), kHang.getSoDienThoai(), kHang.getSoPhong() + " VIP",
						kHang.getNgayDen(), kHang.getNgayDi(), kHang.getSoNguoi(), kHang.getDichVu() };
				defaultTableModel.addRow(khach);
			} else {
				Object[] khach = { kHang.getIdKhachHang(), kHang.getTenKhachHang(), kHang.getGioiTinh(),
						kHang.getQuocTich(), kHang.getCmnd(), kHang.getSoDienThoai(), kHang.getSoPhong(),
						kHang.getNgayDen(), kHang.getNgayDi(), kHang.getSoNguoi(), kHang.getDichVu() };
				defaultTableModel.addRow(khach);
			}

		}
		lblTotalOccupiedRoom.setText("" + defaultTableModel.getRowCount());
		lblCountCurrentFreeRoom.setText("" + (30 - defaultTableModel.getRowCount()));
		lblRoomBooked.setText("" + (defaultTableModel.getRowCount() * 100 / 30) + "%");
		int sumOfPeople = 0;
		for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
			sumOfPeople += Integer.parseInt(defaultTableModel.getValueAt(i, 9).toString());
		}
		lblCountCurrentGuests.setText(String.valueOf(sumOfPeople));
	}

	// add event close
	private final AbstractAction exitAction = new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

	// add event minimize
	private final AbstractAction minimizeAction = new AbstractAction("") {

		@Override
		public void actionPerformed(ActionEvent e) {
			setState(JFrame.ICONIFIED);
		}
	};

	public static void internalFrame(KhachHang khachHang) {
		internalFrame = new JInternalFrame("Chỉnh sửa thông tin");
		internalFrame.setBounds(0, 59, 1078, 540);
		panelClients.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);

		JLabel lblIDkhachHangInternal = new JLabel("ID Khách Hàng: " + khachHang.getIdKhachHang());
		IDKhachHang = Integer.parseInt(khachHang.getIdKhachHang());
		lblIDkhachHangInternal.setHorizontalAlignment(SwingConstants.CENTER);
		lblIDkhachHangInternal.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblIDkhachHangInternal.setBounds(300, 0, 371, 48);
		internalFrame.getContentPane().add(lblIDkhachHangInternal);

		JLabel lblNameClientInternal = new JLabel("Họ và Tên");
		lblNameClientInternal.setHorizontalAlignment(SwingConstants.LEFT);
		lblNameClientInternal.setForeground(Color.BLACK);
		lblNameClientInternal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNameClientInternal.setBounds(0, 50, 137, 48);
		internalFrame.getContentPane().add(lblNameClientInternal);

		textFieldNameClientInternal = new JTextField();
		textFieldNameClientInternal.setToolTipText("");
		textFieldNameClientInternal.setText(khachHang.getTenKhachHang());
		textFieldNameClientInternal.setOpaque(false);
		textFieldNameClientInternal.setForeground(Color.BLACK);
		textFieldNameClientInternal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldNameClientInternal.setColumns(10);
		textFieldNameClientInternal.setBorder(new LineBorder(null, 2, true));
		textFieldNameClientInternal.setBackground(new Color(0, 0, 0, 125));
		textFieldNameClientInternal.setBounds(50, 100, 240, 32);
		internalFrame.getContentPane().add(textFieldNameClientInternal);

		JLabel lblGenderInternal = new JLabel("Giới Tính");
		lblGenderInternal.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenderInternal.setForeground(Color.BLACK);
		lblGenderInternal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblGenderInternal.setBounds(0, 140, 137, 48);
		internalFrame.getContentPane().add(lblGenderInternal);

		rdbtnMaleInternal = new JRadioButton("Nam");
		rdbtnMaleInternal.setOpaque(false);
		rdbtnMaleInternal.setForeground(Color.BLACK);
		rdbtnMaleInternal.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnMaleInternal.setBackground(new Color(0, 0, 0, 125));
		rdbtnMaleInternal.setBounds(50, 190, 63, 23);
		internalFrame.getContentPane().add(rdbtnMaleInternal);

		rdbtnFemaleInternal = new JRadioButton("Nữ");
		rdbtnFemaleInternal.setOpaque(false);
		rdbtnFemaleInternal.setForeground(Color.BLACK);
		rdbtnFemaleInternal.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFemaleInternal.setBackground(new Color(0, 0, 0, 125));
		rdbtnFemaleInternal.setBounds(200, 190, 49, 23);
		internalFrame.getContentPane().add(rdbtnFemaleInternal);

		if (khachHang.getGioiTinh().equals("Nam")) {
			rdbtnMaleInternal.doClick();
		} else {
			rdbtnFemaleInternal.doClick();
		}

		lblNationalityInternal = new JLabel("Quốc Tịch");
		lblNationalityInternal.setHorizontalAlignment(SwingConstants.LEFT);
		lblNationalityInternal.setForeground(Color.BLACK);
		lblNationalityInternal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNationalityInternal.setBounds(0, 220, 137, 48);
		internalFrame.getContentPane().add(lblNationalityInternal);

		localeChooserInternal = new JLocaleChooser();
		localeChooserInternal.setOpaque(false);
		localeChooserInternal.setMaximumRowCount(6);
		localeChooserInternal.setForeground(Color.BLACK);
		localeChooserInternal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		localeChooserInternal.setBorder(new LineBorder(null, 2));
		localeChooserInternal.setBackground(Color.WHITE);
		localeChooserInternal.setBounds(50, 270, 240, 32);
		internalFrame.getContentPane().add(localeChooserInternal);

		lblRoomNumberInternal = new JLabel();
		if (khachHang.getSoPhong() % 100 == 0 || khachHang.getSoPhong() == 101 || khachHang.getSoPhong() == 102
				|| khachHang.getSoPhong() == 201 || khachHang.getSoPhong() == 202 || khachHang.getSoPhong() == 301
				|| khachHang.getSoPhong() == 302) {
			lblRoomNumberInternal.setText("Số Phòng (Phòng hiện tại: " + khachHang.getSoPhong() + " VIP)");
		} else {
			lblRoomNumberInternal.setText("Số Phòng (Phòng hiện tại: " + khachHang.getSoPhong() + ")");
		}
		currentRoom = khachHang.getSoPhong();
		lblRoomNumberInternal.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoomNumberInternal.setForeground(Color.BLACK);
		lblRoomNumberInternal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblRoomNumberInternal.setBounds(0, 310, 325, 48);
		internalFrame.getContentPane().add(lblRoomNumberInternal);

		cbbListRoomInternal.setMaximumRowCount(6);
		cbbListRoomInternal.setSelectedItem(khachHang.getSoPhong());
		int currentRoom = khachHang.getSoPhong();
		cbbListRoomInternal.setForeground(Color.BLACK);
		JScrollPane jScrollPaneListRoom = new JScrollPane(cbbListRoomInternal, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPaneListRoom.setBounds(50, 360, 240, 32);
		jScrollPaneListRoom.setOpaque(false);
		cbbListRoomInternal.setBackground(Color.WHITE);
		cbbListRoomInternal.setBorder(new LineBorder(null, 2));
		internalFrame.getContentPane().add(jScrollPaneListRoom);

		JLabel lblSoNguoi_1 = new JLabel("Số Người");
		lblSoNguoi_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblSoNguoi_1.setBounds(0, 400, 104, 32);
		internalFrame.getContentPane().add(lblSoNguoi_1);

		textFieldSoNguoiInternal = new JTextField();
		textFieldSoNguoiInternal.setToolTipText("");
		textFieldSoNguoiInternal.setText(String.valueOf(khachHang.getSoNguoi()));
		textFieldSoNguoiInternal.setOpaque(false);
		textFieldSoNguoiInternal.setForeground(Color.BLACK);
		textFieldSoNguoiInternal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldSoNguoiInternal.setColumns(10);
		textFieldSoNguoiInternal.setBorder(new LineBorder(null, 2, true));
		textFieldSoNguoiInternal.setBackground(new Color(0, 0, 0, 125));
		textFieldSoNguoiInternal.setBounds(50, 450, 240, 32);
		internalFrame.getContentPane().add(textFieldSoNguoiInternal);

		JLabel lblCMND_1 = new JLabel("Số CMND");
		lblCMND_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCMND_1.setForeground(Color.BLACK);
		lblCMND_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblCMND_1.setBackground(Color.BLACK);
		lblCMND_1.setBounds(700, 50, 137, 48);
		internalFrame.getContentPane().add(lblCMND_1);

		textFieldCMNDInternal = new JTextField();
		textFieldCMNDInternal.setText(khachHang.getCmnd());
		textFieldCMNDInternal.setOpaque(false);
		textFieldCMNDInternal.setForeground(Color.BLACK);
		textFieldCMNDInternal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldCMNDInternal.setColumns(10);
		textFieldCMNDInternal.setBorder(new LineBorder(null, 2));
		textFieldCMNDInternal.setBackground(new Color(0, 0, 0, 125));
		textFieldCMNDInternal.setBounds(740, 100, 240, 32);
		internalFrame.getContentPane().add(textFieldCMNDInternal);

		lblPhoneNumInternal = new JLabel("Số Điện Thoại");
		lblPhoneNumInternal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumInternal.setForeground(Color.BLACK);
		lblPhoneNumInternal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPhoneNumInternal.setBackground(Color.BLACK);
		lblPhoneNumInternal.setBounds(700, 150, 137, 48);
		internalFrame.getContentPane().add(lblPhoneNumInternal);

		textFieldPhoneInternal = new JTextField();
		textFieldPhoneInternal.setText(khachHang.getSoDienThoai());
		textFieldPhoneInternal.setOpaque(false);
		textFieldPhoneInternal.setForeground(Color.BLACK);
		textFieldPhoneInternal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldPhoneInternal.setColumns(10);
		textFieldPhoneInternal.setBorder(new LineBorder(null, 2));
		textFieldPhoneInternal.setBackground(new Color(0, 0, 0, 125));
		textFieldPhoneInternal.setBounds(740, 200, 240, 32);
		internalFrame.getContentPane().add(textFieldPhoneInternal);

		JLabel lblTimeCheckIn_1 = new JLabel("Ngày Đến");
		lblTimeCheckIn_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeCheckIn_1.setForeground(Color.BLACK);
		lblTimeCheckIn_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblTimeCheckIn_1.setBounds(700, 250, 137, 48);
		internalFrame.getContentPane().add(lblTimeCheckIn_1);
		internalFrame.setVisible(true);

		dateChooserInInternal = new JDateChooser();
		dateChooserInInternal.getCalendarButton().setOpaque(false);
		dateChooserInInternal.setDate(khachHang.getNgayDen());
		dateChooserInInternal.getCalendarButton().setBackground(new Color(0, 0, 0, 125));
		dateChooserInInternal.setBounds(976, 300, 25, 32);
		internalFrame.getContentPane().add(dateChooserInInternal);

		textFieldCheckInDateInternal = new JTextField();
		textFieldCheckInDateInternal.setText(khachHang.getNgayDen().toString());
		textFieldCheckInDateInternal.setForeground(Color.BLACK);
		textFieldCheckInDateInternal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldCheckInDateInternal.setColumns(10);
		textFieldCheckInDateInternal.setOpaque(false);
		textFieldCheckInDateInternal.setBackground(new Color(0, 0, 0, 125));
		textFieldCheckInDateInternal.setBorder(new LineBorder(null, 2));
		textFieldCheckInDateInternal.setBounds(738, 300, 240, 32);
		internalFrame.getContentPane().add(textFieldCheckInDateInternal);

		btnGetDateCheckInInternal = new JButton("->");
		btnGetDateCheckInInternal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldCheckInDateInternal.setText("  " + df.format(dateChooserInInternal.getDate()));
			}
		});
		btnGetDateCheckInInternal.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnGetDateCheckInInternal.setBounds(1000, 300, 25, 30);
		internalFrame.getContentPane().add(btnGetDateCheckInInternal);

		lblTimeCheckOutInternal = new JLabel("Ngày Đi(dự kiến)");
		lblTimeCheckOutInternal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeCheckOutInternal.setForeground(Color.BLACK);
		lblTimeCheckOutInternal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblTimeCheckOutInternal.setBounds(700, 350, 170, 48);
		internalFrame.getContentPane().add(lblTimeCheckOutInternal);

		textFieldCheckOutDateInternal = new JTextField();
		textFieldCheckOutDateInternal.setText(khachHang.getNgayDi().toString());
		textFieldCheckOutDateInternal.setForeground(Color.BLACK);
		textFieldCheckOutDateInternal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldCheckOutDateInternal.setColumns(10);
		textFieldCheckOutDateInternal.setOpaque(false);
		textFieldCheckOutDateInternal.setBackground(new Color(0, 0, 0, 125));
		textFieldCheckOutDateInternal.setBorder(new LineBorder(null, 2));
		textFieldCheckOutDateInternal.setBounds(740, 400, 240, 32);
		internalFrame.getContentPane().add(textFieldCheckOutDateInternal);

		dateChooserOutInternal = new JDateChooser();
		dateChooserOutInternal.setDate(khachHang.getNgayDi());
		dateChooserOutInternal.getCalendarButton().setOpaque(false);
		dateChooserOutInternal.getCalendarButton().setBackground(new Color(73, 112, 111));
		dateChooserOutInternal.setBounds(976, 400, 25, 32);
		internalFrame.getContentPane().add(dateChooserOutInternal);

		btnGetDateCheckOutInternal = new JButton("->");
		btnGetDateCheckOutInternal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String checkInDate = dateFormat.format(dateChooserInInternal.getDate());
				String checkOutDate = dateFormat.format(dateChooserOutInternal.getDate());
				if (checkOutDate.compareTo(checkInDate) < 0) {
					JOptionPane.showMessageDialog(null, "Ngày Check Out phải xảy ra sau ngày Check In");
					textFieldCheckOutDate.setText("");
				} else {
					textFieldCheckOutDateInternal.setText("  " + df.format(dateChooserOutInternal.getDate()));
				}

			}
		});
		btnGetDateCheckOutInternal.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnGetDateCheckOutInternal.setBounds(1000, 400, 25, 30);
		internalFrame.getContentPane().add(btnGetDateCheckOutInternal);

		btnUpdateInternal = new JButton("Cập Nhật");
		btnUpdateInternal.setBounds(450, 430, 137, 52);
		btnUpdateInternal.setFocusPainted(false);
		internalFrame.getContentPane().add(btnUpdateInternal);

		JButton btnCloseInternal = new JButton("X");
		btnCloseInternal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				internalFrame.setVisible(false);
				table.setVisible(true);
				jScrollPane.setVisible(true);
			}
		});
		btnCloseInternal.setBounds(1010, 15, 50, 50);
		btnCloseInternal.setFocusPainted(false);
		internalFrame.getContentPane().add(btnCloseInternal);

		btnUpdateInternal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (textFieldNameClientInternal.getText().equals("") || textFieldPhoneInternal.getText().equals("")
						|| textFieldCMNDInternal.getText().equals("")
						|| !(rdbtnFemaleInternal.isSelected() || rdbtnMaleInternal.isSelected())
						|| textFieldCheckInDateInternal.getText().equals("")
						|| textFieldCheckOutDateInternal.getText().equals("")
						|| textFieldSoNguoiInternal.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Phải điền đầy đủ thông tin");
				} else {
					String nameClient = textFieldNameClientInternal.getText();
					String gender = "";
					if (rdbtnFemaleInternal.isSelected()) {
						gender = "Nữ";
					} else if (rdbtnMaleInternal.isSelected()) {
						gender = "Nam";
					}
					String nationality = localeChooserInternal.getSelectedItem().toString();
					String identityCard = textFieldCMNDInternal.getText();
					String phoneNum = textFieldPhoneInternal.getText();

					int roomNumber;
					if (cbbListRoomInternal.getSelectedItem().toString().equals("Phòng hiện tại")) {
						roomNumber = currentRoom;
					} else {
						roomNumber = Integer.parseInt(cbbListRoomInternal.getSelectedItem().toString().substring(0, 3));
					}

					int indexCbbListroom = cbbListRoomInternal.getSelectedIndex();

					SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
					String getDateCheckIn = sdf1.format(dateChooserInInternal.getDate()).toString();
					String getDateCheckOut = sdf1.format(dateChooserOutInternal.getDate()).toString();
					int soNguoi = Integer.parseInt(textFieldSoNguoiInternal.getText());
					java.util.Date date;
					try {
						date = sdf1.parse(getDateCheckIn);
						java.sql.Date sqlDateCheckIn = new java.sql.Date(date.getTime());
						date = sdf1.parse(getDateCheckOut);
						java.sql.Date sqlDateCheckOut = new java.sql.Date(date.getTime());
						KhachHang addClient = new KhachHang(nameClient, gender, nationality, identityCard, phoneNum,
								roomNumber, sqlDateCheckIn, sqlDateCheckOut, soNguoi);

						LayDanhSachKhachHang addClienToDataBase = new LayDanhSachKhachHang();
						boolean checkAddClient = addClienToDataBase.updateThongTinKhachHang(addClient, IDKhachHang);
						if (checkAddClient) {
							int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn cập nhật?",
									"Warning", JOptionPane.YES_NO_OPTION);
							if (dialogResult == JOptionPane.YES_OPTION) {
								JOptionPane.showMessageDialog(null, "Cập nhật thành công");
								textFieldNameClientInternal.setText("");
								textFieldCMNDInternal.setText("");
								textFieldPhoneInternal.setText("");
								if (currentRoom != roomNumber) {
									phong.get(currentRoom).setIcon(imgFreeRoom);
									phong.get(roomNumber).setIcon(imgOccupied);
									LayDanhSachKhachHang danhSachKhachHang = new LayDanhSachKhachHang();
									danhSachKhachHang.thayDoiTrangThaiPhongSauKhiUpdate(currentRoom, roomNumber);

								}
							}

						} else {
							JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thất bại");
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
	}

}
