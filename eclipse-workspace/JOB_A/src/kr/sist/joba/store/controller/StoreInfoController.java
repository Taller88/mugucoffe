/**
 *<pre>
 * PackageName : kr.sist.joba.store.controller
 * Description : 매장선택 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-30
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.store.controller;
 
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import data.PathData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.sist.joba.store.dao.StoreDAO;

/**
 * <pre>
 * PackageName : kr.sist.joba.main.controller
 * ClassName : StoreInfoController.java
 * Description : 결제완료 확인 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-11-30    홍승민         	최초생성
 *  2019-12-17    홍승민         	개발완료
 *  
 * </pre>
 * @since : 2019-11-30
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class StoreInfoController implements Initializable {
	@FXML
	Button btnNext;
	@FXML
	Label lbBack, lbHisLog, lbSignMy, lbLogInOut;
	@FXML
	ComboBox<String> comboBox;
	@FXML
	ListView<String> listView;
	@FXML
	ImageView imStartHome;
	
	StoreDAO storeDao = new StoreDAO();
	ObservableList<String> comboList = FXCollections.observableArrayList(storeDao.readStoreAreaAll());

	/**
	 * title       initialize
	 * Description 화면 출력될때 사용되는 메서드
	 * @param 		arg0, arg1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboBox.setItems(comboList);
		btnNext.setOnAction(event -> btnOrderListAction(event));
		lbBack.setOnMousePressed(event -> backAction(event));
		//비회원
		if(PathData.pUserId==null) {
			lbHisLog.setText("주문내역");
			lbSignMy.setText("회원가입");
			lbLogInOut.setText("로그인");
			imStartHome.setOnMousePressed(event -> startAction(event));
			lbHisLog.setOnMousePressed(event -> orderHistoryLogInAction(event));
			lbSignMy.setOnMousePressed(event -> signUpAction(event));
			lbLogInOut.setOnMousePressed(event -> logInAction(event));
		//회원
		} else {
			lbHisLog.setText("주문내역");
			lbSignMy.setText("마이페이지");
			lbLogInOut.setText("로그아웃");
			imStartHome.setOnMousePressed(event -> homeAction(event));
			lbHisLog.setOnMousePressed(event -> orderHistoryAction(event));
			lbSignMy.setOnMousePressed(event -> myPageAction(event));
			lbLogInOut.setOnMousePressed(event -> logOutAction(event));
		}
	}
	
	/**
	 * title       comboAction
	 * Description 지역구 콤보박스 클릭 액션 이벤트
	 * @param 		event
	 */
	public void comboAction(ActionEvent event) {
		ObservableList<String> list = FXCollections.observableArrayList(storeDao.readStoreName(comboBox.getValue()));
		listView.setItems(list);
	}
	
	/**
	 * title       itemSelect
	 * Description 매장명 리스트뷰 클릭 액션 이벤트
	 */
	public void itemSelect() {
		String item = listView.getSelectionModel().getSelectedItem();
		PathData.pStoreName = item;
	}
	
	/**
	 * title       btnOrderListAction
	 * Description OrderList로 가는 액션 이벤트
	 * @param 		event
	 */
	public void btnOrderListAction(ActionEvent event) {
		String item = listView.getSelectionModel().getSelectedItem();
		if(item == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("매장을 선택하지 않으셨습니다.");
			alert.setContentText("지역과 매장을 선택해주세요.");
	        alert.showAndWait();
		} else {
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/order/view/OrderList.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) btnNext.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * title       backAction
	 * Description 뒤로가기 액션 이벤트
	 * @param 		event
	 */
	public void backAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	//Home.fxml 가기
    		if(PathData.pHomeStore==true) {
    			PathData.lbHomeMethod(lbBack, StoreInfoController.class);
    		//NonMember.fxml 가기
    		} else if(PathData.pNonMemberStore==true) {
    			PathData.nonMemMethod(lbBack, StoreInfoController.class);
    		//MyPage.fxml 가기
    		} else if(PathData.pMyPageStore==true) {
    			PathData.myPageMethod(lbBack, StoreInfoController.class);
    		//OrderHistory.fxml 가기
    		} else if(PathData.pOrderHistoryStore==true) {
    			PathData.orderHistoryMethod(lbBack, StoreInfoController.class);
    		}
        }
	} //--backAction
	
	/**
	 * title       startAction
	 * Description Start 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void startAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.startMethod(imStartHome, StoreInfoController.class);
        }
	} //--startAction
	
	/**
	 * title       orderHistoryLogInAction
	 * Description OrderHistoryLogIn 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void orderHistoryLogInAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pStartOrderHistoryLogin = true;    
    		PathData.pNonMemberOrderHistoryLogin = false;
    		PathData.pSignUpOrderHistoryLogin = false;   
    		PathData.pLogInOrderHistoryLogin = false;
        	PathData.orderHisLogInMethod(lbHisLog, StoreInfoController.class);
        }
	} //--orderHistoryLogInAction
	
	/**
	 * title       signUpAction
	 * Description SignUp 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	/**SignUp 가는 액션 이벤트*/
	private void signUpAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pStartSignUp = true;            
    		PathData.pNonMemberSignUp = false;        
    		PathData.pOrderHistoryLogInSignUp = false;
    		PathData.pLogInSingUp = false;
        	PathData.signUpMethod(lbSignMy, StoreInfoController.class);
        }
	} //--signMyAction
	
	/**
	 * title       logInAction
	 * Description LogIn 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void logInAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pStartLogIn = true;            
    		PathData.pNonMemberLogIn = false;        
    		PathData.pOrderHistoryLogInLogIn = false;
    		PathData.pSignUpLogIn = false;
        	PathData.logInMethod(lbLogInOut, StoreInfoController.class);
        }
	} //--logInAction
	
	/**
	 * title       homeAction
	 * Description Home 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void homeAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.homeMethod(imStartHome, StoreInfoController.class);
        }
	} //--homeAction
	
	/**
	 * title       orderHistoryAction
	 * Description OrderHistory 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void orderHistoryAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pHomeOrderHistory = false;             
    		PathData.pStoreOrderHistory = true;            
    		PathData.pOrderHistoryLoginOrderHistory = false;
    		PathData.pMyPageOrderHistory = false;           
    		PathData.orderHistoryMethod(lbHisLog, StoreInfoController.class);
        }
	} //--orderHistoryAction
	
	/**
	 * title       myPageAction
	 * Description MyPage 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void myPageAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pHomeMyPage = false;        
    		PathData.pStoreMyPage = true;       
    		PathData.pOrderHistoryMyPage = false;
    		PathData.myPageMethod(lbSignMy, StoreInfoController.class);
        }
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
        	PathData.logOutMethod(lbLogInOut, StoreInfoController.class);
        }
	} //--logOutAction
	
}
