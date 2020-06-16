/**
 *<pre>
 * PackageName : kr.sist.joba.orderhistory.controller
 * Description : 주문내역 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-12-03
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.orderhistory.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.sist.hr.cmn.HROraConnectionMaker;
import com.sist.hr.cmn.SearchVO;
import com.sist.hr.member.dao.MemberDao;
import com.sist.hr.member.domain.MemberVO;

import data.PathData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kr.sist.joba.order.dao.OrderDAO;
import kr.sist.joba.order.dao.OrderVO;

/**
 * <pre>
 * PackageName : kr.sist.joba.orderhistory.controller
 * ClassName : OrderHistoryController.java
 * Description : 주문내역 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-08    홍승민         	최초생성
 *  2019-12-18    홍승민         	개발완료
 *  
 * </pre>
 * @since : 2019-12-08
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class OrderHistoryController implements Initializable {
	@FXML
	Label lbBack, lbNonMemStore, lbSignMy, lbLogInOut,
		  lbUserName, lbStoreName, lbOrderNum, lbOrderType, lbOrderStatus, 
		  lbTotalPrice, lbUsePoint, lbPayType, lbUserCellPhone,
		  lbUserArea, lbUserAddress, lbRequests,
		  lbOrderName01, lbOrderName02, lbOrderName03, lbOrderName04,
		  lbOrderCount01, lbOrderCount02, lbOrderCount03, lbOrderCount04,
		  lbOrderPrice01, lbOrderPrice02, lbOrderPrice03, lbOrderPrice04;
	@FXML
	ComboBox<String> comboBox;
	@FXML
	ListView<String> listView;
	@FXML
	ImageView imStartHome;
	@FXML
	Button btnMoreCancle, btnOrderCancle;
	@FXML
	Pane pnCover, pnMore;
	
	DecimalFormat formatter = new DecimalFormat("###,###");
	
	private List<OrderVO> addressBook = new ArrayList<OrderVO>();
	private final String ADD_FILE = "src/data/Order.csv";
	
	private Stage stage;
	
	OrderDAO orderDao = new OrderDAO();
	
	List<MemberVO> dbDataList=null;
		
	/**
	 * title       initialize
	 * Description 화면 출력될때 사용되는 메서드
	 * @param 		arg0, arg1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbBack.setOnMousePressed(event -> backAction(event));
		listView.setOnMouseClicked(event -> listViewAction(event));
		btnMoreCancle.setOnAction(event -> moreCancleAction(event));
		btnOrderCancle.setOnAction(event -> orderCancleAtion(event));
		
		
		// 비회원
		if(PathData.pUserId==null) {
			lbNonMemStore.setText("비회원주문");
			lbSignMy.setText("회원가입");
			lbLogInOut.setText("로그인");
			imStartHome.setOnMousePressed(event -> startAction(event));
			lbNonMemStore.setOnMousePressed(event -> nonMemberAction(event));
			lbSignMy.setOnMousePressed(event -> signUpAction(event));
			lbLogInOut.setOnMousePressed(event -> logInAction(event));
			if(PathData.pUserCellPhone.length()==10) {
				lbUserName.setText(PathData.pUserCellPhone.substring(0,3)+"-"+
						PathData.pUserCellPhone.substring(3,6)+"-"+
						PathData.pUserCellPhone.substring(6,10));
			} else {
				lbUserName.setText(PathData.pUserCellPhone.substring(0,3)+"-"+
						PathData.pUserCellPhone.substring(3,7)+"-"+
						PathData.pUserCellPhone.substring(7,11));
			}
			ObservableList<String> comboList = FXCollections.observableArrayList(orderDao.readNonmemberOrderMonthAll(PathData.pUserCellPhone));
			comboBox.setItems(comboList);
		// 회원
		} else if(PathData.pUserId!=null) {
			lbNonMemStore.setText("주문하기");
			lbSignMy.setText("마이페이지");
			lbLogInOut.setText("로그아웃");
			imStartHome.setOnMousePressed(event -> homeAction(event));
			lbNonMemStore.setOnMousePressed(event -> storeAction(event));
			lbSignMy.setOnMousePressed(event -> myPageAction(event));
			lbLogInOut.setOnMousePressed(event -> logOutAction(event));
			lbUserName.setText(PathData.pUserId);
			ObservableList<String> comboList = FXCollections.observableArrayList(orderDao.readUserOrderMonthAll(PathData.pUserId));
			comboBox.setItems(comboList);
		}
	}
	
	/**
	 * title       comboAction
	 * Description 월별 콤보박스 클릭 액션 이벤트
	 * @param 		event
	 */
	public void comboAction(ActionEvent event) {
		// 비회원 주문조회
		if(PathData.pUserId==null) {
			ObservableList<String> list = FXCollections.observableArrayList(orderDao.readNonMemberOrderList(PathData.pUserCellPhone ,comboBox.getValue().substring(0, comboBox.getValue().length()-1)));
			listView.setItems(list);
		// 회원 주문조회
		} else if(PathData.pUserId != null) {
			ObservableList<String> list = FXCollections.observableArrayList(orderDao.readUserOrderList(PathData.pUserId ,comboBox.getValue().substring(0, comboBox.getValue().length()-1)));
			listView.setItems(list);
		}
	} //--comboAction
	
	/**
	 * title       listViewAction
	 * Description 주문번호 리스트뷰 클릭 액션 이벤트
	 * @param 		event
	 */
	public void listViewAction(MouseEvent event) {
		String item = listView.getSelectionModel().getSelectedItem();
		if(!listView.getItems().isEmpty()) {
			if(item != null) {
				MemberDao tDao=new MemberDao(new HROraConnectionMaker());
				ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
				//강사님 DB에 있는 데이터값 List<MemberVO>형식으로 반환
				SearchVO searchVO=new SearchVO();
				searchVO.setGrpDiv("1");
				
				dbDataList   = (List<MemberVO>) tDao.do_retrieve(searchVO);

				lbOrderNum.setText(item.substring(5,17));
				Label[] orderName = {lbOrderName01, lbOrderName02, lbOrderName03, lbOrderName04};
				Label[] orderCount = {lbOrderCount01, lbOrderCount02, lbOrderCount03, lbOrderCount04};
				Label[] orderPrice = {lbOrderPrice01, lbOrderPrice02, lbOrderPrice03, lbOrderPrice04};
				String[] arrOrderName = orderDao.readOrderName(lbOrderNum.getText());
				String[] arrOrderCount = orderDao.readOrderCount(lbOrderNum.getText());
				String[] arrOrderPrice = orderDao.readOrderPrice(lbOrderNum.getText());
				for(int i=0;i<orderName.length;i++) {
					orderName[i].setText("");
					orderCount[i].setText("");
					orderPrice[i].setText("");
				}
				for(int i=0;i<arrOrderName.length;i++) {
					orderName[i].setText(arrOrderName[i]);
					orderCount[i].setText(arrOrderCount[i]);
					orderPrice[i].setText(formatter.format(Integer.parseInt(arrOrderPrice[i]))+"원");
				}
				for(int i = 0; i < list.size(); i++) {
					if(lbOrderNum.getText().equals(list.get(i).getOrderNum())) {
						lbStoreName.setText(list.get(i).getStoreName());
						lbOrderStatus.setText(list.get(i).getStoreName());
//						txUserCellPhone.setText(list.get(i).getUserCellPhone());
						// 비회원
						if(PathData.pUserId==null) {
							lbUsePoint.setText("비회원");
							lbTotalPrice.setText(formatter.format(orderDao.readNonMemberTotalPrice(lbOrderNum.getText())));
							if(PathData.pUserCellPhone.length()==10) {
								lbUserCellPhone.setText(PathData.pUserCellPhone.substring(0,3)+"-"+
										PathData.pUserCellPhone.substring(3,6)+"-"+
										PathData.pUserCellPhone.substring(6,10));
							} else {
								lbUserCellPhone.setText(PathData.pUserCellPhone.substring(0,3)+"-"+
										PathData.pUserCellPhone.substring(3,7)+"-"+
										PathData.pUserCellPhone.substring(7,11));
							}
						// 회원
						} else {
							lbUsePoint.setText(formatter.format(Integer.parseInt(list.get(i).getUsePoint())));
							lbTotalPrice.setText(formatter.format(orderDao.readTotalPrice(lbOrderNum.getText())));
							if(userInfo().getCellphone().length()==10) {
								lbUserCellPhone.setText(userInfo().getCellphone().substring(0,3)+"-"+
										userInfo().getCellphone().substring(3,6)+"-"+
										userInfo().getCellphone().substring(6,10));
							} else {
								lbUserCellPhone.setText(userInfo().getCellphone().substring(0,3)+"-"+
										userInfo().getCellphone().substring(3,7)+"-"+
										userInfo().getCellphone().substring(7,11));
							}
						}
						lbRequests.setText(list.get(i).getOrderRequests());
						lbPayType.setText(list.get(i).getOrderDivision());
						lbOrderStatus.setText(list.get(i).getOrderPreparing());
						lbUserArea.setText(list.get(i).getUserArea());
						lbUserAddress.setText(list.get(i).getUserAddress());
					}
				}
				pnMore.toFront();
			}
		}
	}
	
	/**
	 * title       orderCancleAtion
	 * Description 주문취소 버튼 클릭 액션 이벤트
	 * @param 		event
	 */
	public void orderCancleAtion(ActionEvent event) {
		// 이미 취소요청을 했을 경우 경고창 출력
		if(!lbOrderStatus.getText().equals("상품준비")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("상품이 준비상태일때만 주문취소가 가능합니다.");
	        alert.showAndWait();
	    // 취소 요청
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("주의!");
			alert.setHeaderText("정말로 주문을 취소하시겠습니까?.");
			alert.setContentText("취소하시려면 OK를 눌러주세요.");
		    Optional<ButtonType> result = alert.showAndWait();
		    if(result.get() == ButtonType.OK) {
		    	String[] arrOrderName = orderDao.readOrderName(lbOrderNum.getText());
				OrderDAO orderDao = new OrderDAO();
				ArrayList<OrderVO> list = readFile02("src/data/Order.csv");
				String item = listView.getSelectionModel().getSelectedItem();
				lbOrderNum.setText(item.substring(5,17));				
				for(int i=0;i<arrOrderName.length;i++) {
					OrderVO modVO = orderDao.getUpdatePreparingInputData(lbOrderNum.getText(),btnOrderCancle.getText(),arrOrderName[i] );
					orderDao.do_update02(modVO);
				}
				Alert alert02 = new Alert(AlertType.WARNING);
				alert02.setTitle("주의!");
				alert02.setHeaderText("주문 취소요청이 완료되었습니다.");
		        alert02.showAndWait();
		        if(result.get() == ButtonType.OK) {
		        	// 비회원
		        	if(PathData.pUserId==null) {
		        		ObservableList<String> listNonMember = FXCollections.observableArrayList(orderDao.readNonMemberOrderList(PathData.pUserCellPhone ,comboBox.getValue().substring(0, comboBox.getValue().length()-1)));
		    			listView.setItems(listNonMember);
		        		pnCover.toFront();
					// 회원
		        	} else {
		        		ObservableList<String> listUser = FXCollections.observableArrayList(orderDao.readUserOrderList(PathData.pUserId ,comboBox.getValue().substring(0, comboBox.getValue().length()-1)));
		    			listView.setItems(listUser);
		        		pnCover.toFront();
		        	}
		        }
		    }
        }
	}
	
	/**
	 * title       moreCancleAction
	 * Description 자세히보기판 x버튼 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void moreCancleAction(ActionEvent event) {
		pnCover.toFront();
	}
	
	/**
	 * title       readFile02
	 * Description OrderVO 리드파일
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
	 * title       backAction
	 * Description 뒤로가기 액션 이벤트
	 * @param 		event
	 */
	public void backAction(MouseEvent event) {
		//Home.fxml 가기
		if(PathData.pHomeOrderHistory==true) {
			PathData.lbHomeMethod(lbBack, OrderHistoryController.class);
		//Store.fxml 가기
		} else if(PathData.pStoreOrderHistory==true) {
			PathData.storeMethod(lbBack, OrderHistoryController.class);
		//OrderHistoryLogIn.fxml 가기
		} else if(PathData.pOrderHistoryLoginOrderHistory==true) {
			PathData.orderHisLogInMethod(lbBack, OrderHistoryController.class);
		//MyPage.fxml 가기
		} else if(PathData.pMyPageOrderHistory==true) {
			PathData.myPageMethod(lbBack, OrderHistoryController.class);
		}
	} //--backAction 	
	
	/**
	 * title       startAction
	 * Description Start 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void startAction(MouseEvent event) {
		PathData.startMethod(imStartHome, OrderHistoryController.class);
	} //--startAction
	
	/**
	 * title       nonMemberAction
	 * Description NonMember 가는 액션 이벤트
	 * @param 		event
	 */
	public void nonMemberAction(MouseEvent event) {
		PathData.nonMemMethod(lbNonMemStore, OrderHistoryController.class);
	} //--nonMemberAction
	
	/**
	 * title       signUpAction
	 * Description SignUp 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void signUpAction(MouseEvent event) {
		PathData.signUpMethod(lbSignMy, OrderHistoryController.class);
	} //--signUpAction
	
	/**
	 * title       logInAction
	 * Description LogIn 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void logInAction(MouseEvent event) {
		PathData.logInMethod(lbLogInOut, OrderHistoryController.class);
	} //--logInAction
	
	/**
	 * title       homeAction
	 * Description Home 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void homeAction(MouseEvent event) {
		PathData.homeMethod(imStartHome, OrderHistoryController.class);
	} //--homeAction
	
	/**
	 * title       storeAction
	 * Description Store 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void storeAction(MouseEvent event) {
		PathData.pHomeStore = false;        
		PathData.pNonMemberStore = false;   
		PathData.pOrderHistoryStore = true;
		PathData.pMyPageStore = false;      
		PathData.storeMethod(lbNonMemStore, OrderHistoryController.class);
	} //--homeAction
	
	/**
	 * title       myPageAction
	 * Description MyPage 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void myPageAction(MouseEvent event) {
		PathData.pHomeMyPage = false;        
		PathData.pStoreMyPage = false;       
		PathData.pOrderHistoryMyPage = true;
		PathData.myPageMethod(lbSignMy, OrderHistoryController.class);
	} //--myPageAction
	
	/**
	 * title       logOutAction
	 * Description 로그아웃 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void logOutAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("홈페이지로 돌아갑니다.");
		alert.setContentText("정말로 로그아웃 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.logOutMethod(lbLogInOut, OrderHistoryController.class);
        }
	} //--logOutAction
	
	/**
	 * title       userInfo
	 * Description DB List에 담는 메서드
	 * @param 		
	 * @return 		outvo
	 */
	private MemberVO userInfo() {
		MemberVO outvo=null;
		MemberDao tDao=new MemberDao(new HROraConnectionMaker());//강사님 DBDAO
		SearchVO searchVO=new SearchVO();
		
		searchVO.setGrpDiv("1");//조구분
    	
    	List<MemberVO> dbData  = (List<MemberVO>) tDao.do_retrieve(searchVO);
    	for(int i=0; i<dbData.size(); i++) {
    		if(PathData.pUserId.equals(dbData.get(i).getMemId())) {
    			outvo= dbData.get(i);
    		}
    	}
		return outvo;
	} //--userInfo
	
}