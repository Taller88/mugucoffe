/**
 *<pre>
 * @PackageName : kr.sist.joba.order.dao
 * description : 주문관련 DAO 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.order.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import data.DTO;
import data.PathData;
import data.WorkDiv;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kr.sist.joba.store.dao.StoreVO;
import kr.sist.joba.user.dao.UserPointVO;

/**
 * <pre>
 * PackageName : kr.sist.joba.order.dao
 * ClassName : OrderDAO.java
 * description : 주문관련 DAO 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-11-22    박종훈         	최초생성
 *  2019-12-20    박종훈         	개발완료
 *  
 * </pre>
 * @since : 2019-11-22
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class OrderDAO implements WorkDiv {

	private List<OrderVO> addressBook = new ArrayList<OrderVO>();
	private final String ADD_FILE = "src/data/Order.csv";
	
	/**
	 * title       OrderDAO
	 * description readFile 메서드에 Order.csv 경로를 연결 시키는 메서드
	 */
	public OrderDAO() {
		addressBook = readFile01(ADD_FILE);
	}
	
	/**
	 * title       readFile01
	 * description get(i)와 getVO정보들 분리 배열
	 * @param 		filePath
	 * @return 		addressData
	 */
	public List<OrderVO> readFile01(String filePath){
		List<OrderVO> addressData = new ArrayList<OrderVO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while( (line=br.readLine()) != null ) {
				//홍길동,jamesol@paran.com,16321203,010-1234-5678
				String[] dataArray = line.split(",");
				OrderVO vo = new OrderVO(dataArray[0],
										 dataArray[1],
										 dataArray[2],
										 dataArray[3],
										 dataArray[4],
										 dataArray[5],
										 dataArray[6],
										 dataArray[7],
										 dataArray[8],
										 dataArray[9],
										 dataArray[10],
										 dataArray[11],
										 dataArray[12]
				);
				addressData.add(vo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null !=br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return addressData;
	}
	
	/**
	 * title       readFile02
	 * description get(i)와 getVO정보들 한줄에 적는 배열
	 * @param 		path
	 * @return 		list
	 */
	static ArrayList<OrderVO> readFile02(String path) {
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		try {
			Scanner scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				String input = scanner.nextLine();
	            //System.out.println(input);
	            String[] inArray = input.split(",");
	            OrderVO dto = new OrderVO(inArray[0],
					            		  inArray[1],
					            		  inArray[2],
					            		  inArray[3],
					            		  inArray[4],
					            		  inArray[5],
					            		  inArray[6],
					            		  inArray[7],
					            		  inArray[8],
					            		  inArray[9],
					            		  inArray[10],
					            		  inArray[11],
					            		  inArray[12]
	            );
	            list.add(dto);
	        } //while
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	    }
	      return list;
	} //--readFile02
	
	/**
	 * title       saveFile
	 * description csv에 입력 정보를 저장하는 메서드
	 * @param 		path
	 * @return 		flag
	 */
	public int saveFile(String path)  {
		File file = new File(path);
		FileWriter      fw = null;
		BufferedWriter  bw = null;
		int flag = 0;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
		//--------------------------------------
			for(int i=0;i<addressBook.size();i++) {
				OrderVO vo = addressBook.get(i);
				StringBuilder sb = new StringBuilder();
				sb.append(vo.getOrderNum()+","+
						  vo.getUserCellPhone()+","+
						  vo.getUserArea()+","+
						  vo.getUserAddress()+","+
						  vo.getOrderCount()+","+
						  vo.getOrderPrice()+","+
						  vo.getProductName()+","+
						  vo.getUserId()+","+
						  vo.getStoreName()+","+
						  vo.getOrderDivision()+","+
						  vo.getUsePoint()+","+
						  vo.getOrderRequests()+","+
						  vo.getOrderPreparing()
				);
				//마지막 라인에 "\n" 제거
				if((i+1) != addressBook.size()) {
					sb.append("\n");
				}
				flag++;
				bw.write(sb.toString());
			}
		//--------------------------------------
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * title       readUsePointUserOrNonMemCheck
	 * description 주문번호에 따른 주문에 사용포인트가 비회원인지 회원인지 체크하는 메서드
	 * @param 		vsOrderNum
	 * @return 		true, false
	 */
	public boolean readUsePointUserOrNonMemCheck(String vsOrderNum) {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo= addressBook.get(i);
			if(vo.getOrderNum().equals(vsOrderNum) && vo.getUsePoint().equals("비회원")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * title       readUsePointCheck
	 * description 유저 사용포인트가 0인지 체크하는 메서드
	 * @return 		true, false
	 */
	public boolean readUsePointCheck() {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getUserId().equals(PathData.pUserId)) {
				if(!vo.getUsePoint().equals("0") && !vo.getUsePoint().equals("비회원")) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * title       readOrderCheckAdminPrepairing
	 * description 매장명과 배송상태에 따른 주문이력 체크
	 * @param 		vsStoreName, vsPrepairing
	 * @return 		true, false
	 */
	public boolean readOrderCheckAdminPrepairing(String vsStoreName, String vsPrepairing) {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getStoreName().equals(vsStoreName) && vo.getOrderPreparing().equals(vsPrepairing)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * title       readOrderCheckAdminStoreName
	 * description 매장명에 따른 주문이력 체크
	 * @param 		vsStoreName
	 * @return 		true, false
	 */
	public boolean readOrderCheckAdminStoreName(String vsStoreName) {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo =  addressBook.get(i);
			if(vo.getStoreName().contentEquals(vsStoreName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * title       readOrderCheckAdminYearMonthDay
	 * description 일별에 따른 주문이력 체크
	 * @param 		vsStoreName, vsYear, vsMonth, vsDay
	 * @return 		true, false
	 */
	public boolean readOrderCheckAdminYearMonthDay(String vsStoreName, String vsYear, String vsMonth, String vsDay) {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo =  addressBook.get(i);
			if(vo.getStoreName().contentEquals(vsStoreName) && vo.getOrderNum().substring(0,4).equals(vsYear) && vo.getOrderNum().substring(4,6).equals(vsMonth) && vo.getOrderNum().substring(6,8).equals(vsDay)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * title       readOrderCheckAdminYear
	 * description 월별에 따른 주문이력 체크
	 * @param 		vsStoreName, vsYear
	 * @return 		true, false
	 */
	public boolean readOrderCheckAdminYear(String vsStoreName, String vsYear) {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo =  addressBook.get(i);
			if(vo.getStoreName().contentEquals(vsStoreName) && vo.getOrderNum().substring(0,4).equals(vsYear)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * title       readOrderCheck
	 * description 회원 주문이력 체크 (파람없음)
	 * @return 		true, false
	 */
	public boolean readOrderCheck() {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getUserId().equals(PathData.pUserId)) {
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * title       readOrderCheckParam
	 * description 회원 주문이력 체크 (파람있음)
	 * @param 		vsId
	 * @return 		true, false
	 */
	public boolean readOrderCheckParam(String vsId) {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getUserId().equals(vsId)) {
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * title       readNonMemOrderCheck
	 * description 비회원 주문이력 체크
	 * @param 		vsCellPhone
	 * @return 		true, false
	 */
	public boolean readNonMemOrderCheck(String vsCellPhone) {
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getUserCellPhone().equals(vsCellPhone)) {
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * title       readOrderPreparing
	 * description 관리자페이지 배송상태 읽는 메서드
	 * @param 		OrderNum
	 * @return 		orderPreparing
	 */
	public String readOrderPreparing(String OrderNum) {
		String orderPreparing = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(OrderNum)) {
				orderPreparing = vo.getOrderPreparing();
			}
		}
		return orderPreparing;
	}
	
	/**
	 * title       readUsePoint
	 * description 관리자페이지 포인트 사용액 보는 메서드
	 * @param 		orderNum
	 * @return 		usePoint
	 */
	public String readUsePoint(String orderNum) {
		String usePoint = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(orderNum) && !vo.getUsePoint().equals("비회원")) {
				usePoint = vo.getUsePoint();
			} else {
				usePoint = "0";
			}
		}
		return usePoint;
	}
	
	/**
	 * title       readOrderDivision
	 * description 관리자페이지 결제구분 읽는 메서드
	 * @param 		OrderNum
	 * @return 		orderDivision
	 */
	public String readOrderDivision(String OrderNum) {
		String orderDivision = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(OrderNum)) {
				orderDivision = vo.getOrderDivision();
			}
		}
		return orderDivision;
	}
	
	/**
	 * title       readUserCellPhone
	 * description 관리자페이지 주문자 휴대폰번호 읽는 메서드
	 * @param 		OrderNum
	 * @return 		userCellPhone
	 */
	public String readUserCellPhone(String OrderNum) {
		String userCellPhone = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(OrderNum)) {
				userCellPhone = vo.getUserCellPhone();
			}
		}
		return userCellPhone;
	}
	
	/**
	 * title       readUserArea
	 * description 관리자페이지 주문자 주소 읽는 메서드
	 * @param 		OrderNum
	 * @return 		userArea
	 */
	public String readUserArea(String OrderNum) {
		String userArea = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(OrderNum)) {
				userArea = vo.getUserArea();
			}
		}
		return userArea;
	}
	
	/**
	 * title       readUserAddress
	 * description 관리자페이지 주문자 상세주소 읽는 메서드
	 * @param 		OrderNum
	 * @return 		userAddress
	 */
	public String readUserAddress(String OrderNum) {
		String userAddress = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(OrderNum)) {
				userAddress = vo.getUserAddress();
			}
		}
		return userAddress;
	}
	
	/**
	 * title       readOrderRequests
	 * description 관리자페이지 주문자 요청사항 읽는 메서드
	 * @param 		OrderNum
	 * @return 		orderRequests
	 */
	public String readOrderRequests(String OrderNum) {
		String orderRequests = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(OrderNum)) {
				orderRequests = vo.getOrderRequests();
			}
		}
		return orderRequests;
	}
	
	/**
	 * title       readUseId
	 * description 관리자페이지 주문아이디 보는 메서드
	 * @param 		orderNum
	 * @return 		usePoint
	 */
	public String readUseId(String orderNum) {
		String usePoint = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(orderNum)) {
				usePoint = vo.getUserId();
			}
		}
		return usePoint;
	}
	
	/**
	 * title       readOrderName
	 * description 관리자페이지 주문 상품 이름 보는 메서드
	 * @param 		orderNum
	 * @return 		orderName
	 */
	public String[] readOrderName(String orderNum) {
		String strOrderName = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(orderNum)) {
				strOrderName += vo.getProductName()+",";
			}
		}
		strOrderName = strOrderName.substring(0,strOrderName.length()-1);
		strOrderName = strOrderName.trim();
		String[] orderName = strOrderName.split(",");
		return orderName;
	}
	
	/**
	 * title       readOrderCount
	 * description 관리자페이지 주문 상품 갯수 보는 메서드
	 * @param 		orderNum
	 * @return 		orderCount
	 */
	public String[] readOrderCount(String orderNum) {
		String strOrderCount = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(orderNum)) {
				strOrderCount += vo.getOrderCount()+",";
			}
		}
		strOrderCount = strOrderCount.substring(0,strOrderCount.length()-1);
		strOrderCount = strOrderCount.trim();
		String[] orderCount = strOrderCount.split(",");
		return orderCount;
	}
	
	/**
	 * title       readOrderPrice
	 * description 관리자페이지 주문 상품 가격 보는 메서드
	 * @param 		orderNum
	 * @return 		orderPrice
	 */
	public String[] readOrderPrice(String orderNum) {
		String strOrderPrice = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(orderNum)) {
				strOrderPrice += vo.getOrderPrice().substring(0, vo.getOrderPrice().length()-1)+",";
			}
		}
		strOrderPrice = strOrderPrice.substring(0,strOrderPrice.length()-1);
		strOrderPrice = strOrderPrice.trim();
		String[] orderPrice = strOrderPrice.split(",");
		return orderPrice;
	}
	
	/**
	 * title       readTotalPrice
	 * description 회원 주문 상품 합계금액 보는 메서드
	 * @param 		orderNum
	 * @return 		totalPrice
	 */
	public int readTotalPrice(String orderNum) {
		String strOrderPrice = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(orderNum)) {
				strOrderPrice += vo.getOrderPrice().substring(0,vo.getOrderPrice().length()-1)+",";
			}
		}
		strOrderPrice = strOrderPrice.substring(0,strOrderPrice.length()-1);
		strOrderPrice = strOrderPrice.trim();
		String[] arrStrTotalPrice = strOrderPrice.split(",");
		int[] arrIntTotalPrice = Arrays.stream(arrStrTotalPrice).mapToInt(Integer::parseInt).toArray();
		int sum = 0;
		for(int i=0;i<arrIntTotalPrice.length;i++) {
			sum += arrIntTotalPrice[i];
		}
		int totalPrice = 0;
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(orderNum)) {
				totalPrice = sum - Integer.parseInt(vo.getUsePoint());
			}
		}
		return totalPrice;
	}
	
	/**
	 * title       readNonMemberTotalPrice
	 * description 비회원 주문 상품 합계금액 보는 메서드
	 * @param 		orderNum
	 * @return 		sum
	 */
	public int readNonMemberTotalPrice(String orderNum) {
		String strOrderPrice = "";
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().equals(orderNum)) {
				strOrderPrice += vo.getOrderPrice().substring(0,vo.getOrderPrice().length()-1)+",";
			}
		}
		strOrderPrice = strOrderPrice.substring(0,strOrderPrice.length()-1);
		strOrderPrice = strOrderPrice.trim();
		String[] arrStrTotalPrice = strOrderPrice.split(",");
		int[] arrIntTotalPrice = Arrays.stream(arrStrTotalPrice).mapToInt(Integer::parseInt).toArray();
		int sum = 0;
		for(int i=0;i<arrIntTotalPrice.length;i++) {
			sum += arrIntTotalPrice[i];
		}
		return sum;
	}
	
	/**
	 * title       readYearAll
	 * description 관리자차트 년도 보는 메서드 (콤보박스)
	 * @param 		vsStoreName
	 * @return 		set
	 */
	public Set<String> readYearAll(String vsStoreName) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderYear = "";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getStoreName().equals(vsStoreName)) {
				strOrderYear += list.get(i).getOrderNum().substring(0, 4)+"년"+",";
			}
		}
		strOrderYear = strOrderYear.substring(0, strOrderYear.length()-1);
		strOrderYear = strOrderYear.trim();
		String[] arrayAb = strOrderYear.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readMonthlySales
	 * description 관리자차트 Line 차트 y축:월별 매출액 보는 메서드
	 * @param 		vsStoreName, vsYearMonth
	 * @return 		MonthlySales
	 */
	public int readMonthlySales(String vsStoreName, String vsYearMonth) {
		int MonthlySales = 0;
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getStoreName().equals(vsStoreName)&& vo.getOrderNum().substring(0,6).equals(vsYearMonth)) {
				MonthlySales += Integer.parseInt(vo.getOrderPrice().substring(0,vo.getOrderPrice().length()-1));
			}
		}
		return MonthlySales;
	}
	
	/**
	 * title       readMenuMonthAll
	 * description 관리자차트 MonthBar 차트 월별 보는 메서드 (콤보박스)
	 * @param 		vsStoreName, year
	 * @return 		set
	 */
	public Set<String> readMenuMonthAll(String vsStoreName, String year) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderMonth = "";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getStoreName().equals(vsStoreName) && list.get(i).getOrderNum().substring(0, 4).equals(year)) {
				strOrderMonth += list.get(i).getOrderNum().substring(4, 6)+"월"+",";
			}
		}
		strOrderMonth = strOrderMonth.substring(0, strOrderMonth.length()-1);
		strOrderMonth = strOrderMonth.trim();
		String[] arrayAb = strOrderMonth.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}

	/**
	 * title       readMenuChart
	 * description 관리자차트 Bar 차트 메뉴 금액 보는 메서드
	 * @param 		vsYear, vsMonth, vsDay, vsStoreName, vsProductName
	 * @return 		menuCount
	 */
	public int readMenuChart(String vsYear, String vsMonth, String vsDay, String vsStoreName, String vsProductName) {
		int menuCount = 0;
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().substring(0,4).equals(vsYear) && vo.getOrderNum().substring(4,6).equals(vsMonth) && vo.getOrderNum().substring(6,8).equals(vsDay) && vo.getStoreName().equals(vsStoreName) && vo.getProductName().equals(vsProductName)) {
				menuCount += Integer.parseInt(vo.getOrderCount().substring(0, vo.getOrderCount().length()-1));
			}
		}
		return menuCount;
	}
	
	/**
	 * title       readMonthMenuChart
	 * description 관리자차트 Bar 차트 월별 메뉴 금액 보는 메서드
	 * @param 		vsYear, vsMonth, vsStoreName, vsProductName
	 * @return 		menuCount
	 */
	public int readMonthMenuChart(String vsYear, String vsMonth, String vsStoreName, String vsProductName) {
		int menuCount = 0;
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			if(vo.getOrderNum().substring(0,4).equals(vsYear) && vo.getOrderNum().substring(4,6).equals(vsMonth) && vo.getStoreName().equals(vsStoreName) && vo.getProductName().equals(vsProductName)) {
				menuCount += Integer.parseInt(vo.getOrderCount().substring(0, vo.getOrderCount().length()-1));
			}
		}
		return menuCount;
	}
	
	/**
	 * title       saveOrderNum
	 * description 페이먼트 주문번호 저장 메서드
	 * @return 		dateF+orderNum, dateF+1001
	 */
	public String saveOrderNum() {
		Date date01 = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YYYYMMdd");
		OrderVO vo = null;
		for(int i=0;i<addressBook.size();i++) {
			vo = addressBook.get(i);
		}
		String dateF = date.format(date01);
		String strOrderNum = vo.getOrderNum().substring(8,12);
		if(date.format(date01).equals(vo.getOrderNum().substring(0,8))) {
			int intOrderNum = Integer.parseInt(strOrderNum)+1;
			String orderNum = Integer.toString(intOrderNum);
			return dateF+orderNum;
		} else {
			return dateF+1001;
		}
	}
	
	/**
	 * title       readOrderNumLast
	 * description 맨 마지막 주문번호 읽는 메서드
	 * @return 		lastOrderNum
	 */
	public String readOrderNumLast() {
		String lastOrderNum = null;
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vo = addressBook.get(i);
			lastOrderNum = vo.getOrderNum();
		}
		return lastOrderNum;
	}
	
	/**
	 * title       readOrderPreparingAll
	 * description 배송상태 전부 읽는 메서드
	 * @return 		set
	 */
	public Set<String> readOrderPreparingAll() {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderPreparing = "";
		for(int i=0;i<list.size();i++) {
			strOrderPreparing += list.get(i).getOrderPreparing()+",";
		}
		strOrderPreparing = strOrderPreparing.substring(0,strOrderPreparing.length()-1);
		strOrderPreparing = strOrderPreparing.trim();
		String[] arrayAb = strOrderPreparing.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readAdminOrderList
	 * description 관리자페이지 주문 조회 (중복제거)
	 * @param 		vsStoreName, vsPreParing
	 * @return 		set
	 */
	public Set<String> readAdminOrderList(String vsStoreName, String vsPreParing) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderNum = "";
		for(int i=list.size()-1;i>0;i--) {
			if(list.get(i).getStoreName().equals(vsStoreName) && list.get(i).getOrderPreparing().equals(vsPreParing)) {
				strOrderNum += "주문번호:"+list.get(i).getOrderNum()+",";
			}
		}
		strOrderNum = strOrderNum.substring(0, strOrderNum.length()-1);
		strOrderNum = strOrderNum.trim();
		String[] arrayAb = strOrderNum.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readNonmemberOrderMonthAll
	 * description 비회원 월별 주문 내역 (콤보박스)
	 * @param 		vsCellPhone
	 * @return 		set
	 */
	public Set<String> readNonmemberOrderMonthAll(String vsCellPhone) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strYearMonth = "";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUserCellPhone().equals(vsCellPhone)) {
				strYearMonth += list.get(i).getOrderNum().substring(0, 4)+"년 "+list.get(i).getOrderNum().substring(4, 6)+"월"+",";
			}
		}
		strYearMonth = strYearMonth.substring(0,strYearMonth.length()-1);
		strYearMonth = strYearMonth.trim();
		String[] arrayAb = strYearMonth.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readNonMemberOrderList02
	 * description 비회원 월별 주문 내역 조회 (리스트뷰)
	 * @param 		vsCellPhone, vsMonth
	 * @return 		orderList
	 */
	public String[] readNonMemberOrderList02(String vsCellPhone, String vsMonth) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderList = "";
		String[] orderList = null;;
		for(int i=list.size()-1;i>0;i--) {
			if(list.get(i).getUserId().equals(vsCellPhone) && Integer.parseInt(list.get(i).getOrderNum().substring(4, 6))==Integer.parseInt(vsMonth)) {
				strOrderList += "주문번호:"+list.get(i).getOrderNum()+
					  "   상품명:"+list.get(i).getProductName()+
					  "   갯수:"+list.get(i).getOrderCount()+
					  "   결제구분:"+list.get(i).getOrderDivision()+
					  "   요청사항:"+list.get(i).getOrderRequests()+
					  "   배송상태:"+list.get(i).getOrderPreparing()+",";
			}
		}
		strOrderList = strOrderList.substring(0, strOrderList.length()-1);
		strOrderList = strOrderList.trim();
		orderList = strOrderList.split(",");
		return orderList;
	}
	
	/**
	 * title       readNonMemberOrderList
	 * description 비회원 주문번호 읽는 메서드 (중복제거, 콤보박스)
	 * @param 		vsCellPhone, vsMonth
	 * @return 		set
	 */
	public Set<String> readNonMemberOrderList(String vsCellPhone, String vsMonth) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderNum = "";
		for(int i=list.size()-1;i>0;i--) {
			if(list.get(i).getUserCellPhone().equals(vsCellPhone) && list.get(i).getUsePoint().equals("비회원") && Integer.parseInt(list.get(i).getOrderNum().substring(4, 6))==Integer.parseInt(vsMonth.substring(6, 8))) {
				strOrderNum += "주문번호:"+list.get(i).getOrderNum()+",";
			}
		}
		strOrderNum = strOrderNum.substring(0, strOrderNum.length()-1);
		strOrderNum = strOrderNum.trim();
		String[] arrayAb = strOrderNum.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readUserOrderMonthAll
	 * description 회원 월별 주문 내역 (콤보박스)
	 * @param 		vsId
	 * @return 		set
	 */
	public Set<String> readUserOrderMonthAll(String vsId) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderNum = "";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUserId().equals(vsId)) {
				strOrderNum += list.get(i).getOrderNum().substring(0, 4)+"년 "+list.get(i).getOrderNum().substring(4, 6)+"월"+",";
			}
		}
		strOrderNum = strOrderNum.substring(0, strOrderNum.length()-1);
		strOrderNum = strOrderNum.trim();
		String[] arrayAb = strOrderNum.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readUserOrderList
	 * description 회원 월별 주문 내역 조회 (리스트뷰)
	 * @param 		vsId, vsMonth
	 * @return 		set
	 */
	public Set<String> readUserOrderList(String vsId, String vsMonth) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderNum = "";
		for(int i=list.size()-1;i>0;i--) {
			if(list.get(i).getUserId().equals(vsId) && !list.get(i).getUsePoint().equals("비회원") && Integer.parseInt(list.get(i).getOrderNum().substring(4, 6))==Integer.parseInt(vsMonth.substring(6, 8))) {
				strOrderNum += "주문번호:"+list.get(i).getOrderNum()+",";
			}
		}
		strOrderNum = strOrderNum.substring(0, strOrderNum.length()-1);
		strOrderNum = strOrderNum.trim();
		String[] arrayAb = strOrderNum.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readMonthAll
	 * description Order.csv 년, 월(콤보박스)
	 * @return 		set
	 */
	public Set<String> readMonthAll() {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strOrderNum = "";
		for(int i=0;i<list.size();i++) {
			if(!list.get(i).getUsePoint().equals("0") && !list.get(i).getUsePoint().equals("비회원")) {
				strOrderNum += list.get(i).getOrderNum().substring(0, 4)+"년 "+list.get(i).getOrderNum().substring(4, 6)+"월"+",";
			}
		}
		strOrderNum = strOrderNum.substring(0,strOrderNum.length()-1);
		strOrderNum = strOrderNum.trim();
		String[] arrayAb = strOrderNum.split(",");
		Set<String> set = new HashSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readUsePointList
	 * description 마이페이지 사용 마일리지 내역 월별 조회 (리스트뷰)
	 * @param 		vsId, vsMonth
	 * @return 		set
	 */
	public Set<String> readUsePointList(String vsId, String vsMonth) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		String strUsePoint = "";
		for(int i=list.size()-1;i>0;i--) {
			if(list.get(i).getUserId().equals(vsId) && list.get(i).getOrderNum().substring(0,4).equals(vsMonth.substring(0,4)) && Integer.parseInt(list.get(i).getOrderNum().substring(4, 6))==Integer.parseInt(vsMonth.substring(6, 8)) && !list.get(i).getUsePoint().equals("0") && !list.get(i).getOrderPreparing().equals("취소완료")) {
				strUsePoint += "사용일:"+list.get(i).getOrderNum().substring(0,4)+"년"+list.get(i).getOrderNum().substring(4,6)+"월"+list.get(i).getOrderNum().substring(6,8)+"일"+
					  "   주문번호:"+list.get(i).getOrderNum()+
					  "   사용포인트:"+list.get(i).getUsePoint()+",";
			}
		}
		strUsePoint = strUsePoint.substring(0, strUsePoint.length()-1);
		strUsePoint = strUsePoint.trim();
		String[] arrayAb = strUsePoint.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       getUpdatePreparingInputData
	 * description 관리자페이지 업데이트 메서드
	 * @param 		vsOrderNum, orderPreparing, productName
	 * @return 		outVo
	 */
	public OrderVO getUpdatePreparingInputData(String vsOrderNum, String orderPreparing, String productName) {
		ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
		OrderVO outVo = null;
		String inputData = "";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getOrderNum().equals(vsOrderNum) && list.get(i).getProductName().equals(productName)) {
				inputData = list.get(i).getOrderNum()+","+
							list.get(i).getUserCellPhone()+","+
							list.get(i).getUserArea()+","+
							list.get(i).getUserAddress()+","+
							list.get(i).getOrderCount()+","+
							list.get(i).getOrderPrice()+","+
							productName+","+
							list.get(i).getUserId()+","+
							list.get(i).getStoreName()+","+
							list.get(i).getOrderDivision()+","+
							list.get(i).getUsePoint()+","+
							list.get(i).getOrderRequests()+","+
							orderPreparing;
			}
		}
		inputData = inputData.trim();//앞뒤 공간 삭제
		String[] dataArray = inputData.split(",");
		outVo  = new OrderVO(dataArray[0],
							 dataArray[1],
							 dataArray[2],
							 dataArray[3],
							 dataArray[4],
							 dataArray[5],
							 dataArray[6],
				      		 dataArray[7],
				      		 dataArray[8],
				      		 dataArray[9],
				      		 dataArray[10],
				      		 dataArray[11],
				      		 dataArray[12]
		);
		return outVo;
	}
	
	/**
	 * title       isMemberExists
	 * description 취소시 주문내역 업데이트 메서드
	 * @param 		vo
	 * @return 		flag
	 */
	public boolean isMemberExists(OrderVO vo){
		boolean flag = false;
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vsVO = addressBook.get(i);
			//이메일을 비교, 데이터가 있으면 loop종료
			if(vsVO.getOrderNum().equals(vo.getOrderNum())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * title       isMemberExists02
	 * description 배송상태 업데이트 메서드
	 * @param 		vo
	 * @return 		flag
	 */
	public boolean isMemberExists02(OrderVO vo){
		boolean flag = false;
		for(int i=0;i<addressBook.size();i++) {
			OrderVO vsVO = addressBook.get(i);
			//주문번호, 상품명, 데이터가 있으면 loop종료
			if(vsVO.getOrderNum().equals(vo.getOrderNum()) && vsVO.getProductName().equals(vo.getProductName())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * title       do_save
	 * description Order.csv에 정보를 저장하는 메서드
	 * @param 		dto
	 * @return 		flag
	 */
	@Override
	public int do_save(DTO dto) {
		OrderVO vo = (OrderVO)dto;
		addressBook.add(vo);
		int flag = saveFile(ADD_FILE);
		return flag;
	}
	
	@Override
	public List<?> do_retrieve(DTO dto) {
		return null;
	}
	
	@Override
	public DTO do_selectOne(DTO dto) {
		return null;
	}
	
	/**
	 * title       do_update
	 * description Order.csv에 정보를 수정하는 메서드
	 * @param 		dto
	 * @return 		flag
	 */
	@Override
	public int do_update(DTO dto) {
		int flag = 0;
		OrderVO modVO = (OrderVO) dto;
		if(isMemberExists(modVO)) {
			flag = do_delete(modVO);
			if(flag>0) {
				do_save(modVO);
			}
		}
		return flag;
	}
	
	/**
	 * title       do_update02
	 * description Order.csv에 배송상태 수정하는 메서드
	 * @param 		dto
	 * @return 		flag
	 */
	public int do_update02(DTO dto) {
		int flag = 0;
		OrderVO modVO = (OrderVO) dto;
		if(isMemberExists02(modVO)) {
			flag = do_delete02(modVO);
			if(flag>0) {
				do_save(modVO);
			}
		}
		return flag;
	}

	/**
	 * title       do_delete
	 * description Order.csv에 정보를 삭제하는 메서드
	 * @param 		dto
	 * @return 		flag
	 */
	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		OrderVO delVo = (OrderVO) dto;
		//반드시 역순으로 찾고 삭제 해야함.
		for(int i=addressBook.size()-1;i>=0;i--) {
			OrderVO vsVo = addressBook.get(i);
			if(delVo.getOrderNum().equals(vsVo.getOrderNum())) {
				addressBook.remove(i);
				flag++;
			}
		}
		//파일에 저장
		if(flag != 0)saveFile(ADD_FILE);
		return flag;
	}
	
	/**
	 * title       do_delete02
	 * description Order.csv에 배송상태 정보를 삭제하는 메서드 2번쨰
	 * @param 		dto
	 * @return 		flag
	 */
	public int do_delete02(DTO dto) {
		int flag = 0;
		OrderVO delVo = (OrderVO) dto;
		//반드시 역순으로 찾고 삭제 해야함.
		for(int i=addressBook.size()-1;i>=0;i--) {
			OrderVO vsVo = addressBook.get(i);
			if(delVo.getOrderNum().equals(vsVo.getOrderNum()) && delVo.getProductName().equals(vsVo.getProductName())) {
				addressBook.remove(i);
				flag++;
			}
		}
		//파일에 저장
		if(flag != 0)saveFile(ADD_FILE);
		return flag;
	}
	
}
