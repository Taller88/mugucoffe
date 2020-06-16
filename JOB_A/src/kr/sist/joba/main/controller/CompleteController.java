/**
 *<pre>
 * PackageName : kr.sist.joba.main.controller
 * Description : 메인 컨트롤러 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.main.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import data.PathData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import kr.sist.joba.order.dao.OrderDAO;
import kr.sist.joba.store.dao.StoreDAO;

/**
 * <pre>
 * PackageName : kr.sist.joba.main.controller
 * ClassName : CompleteController.java
 * Description : 결제완료 확인 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-11-22    박종훈         	최초생성
 *  2019-12-17    박종훈         	개발완료
 *  
 * </pre>
 * @since : 2019-11-22
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class CompleteController implements Initializable {
	@FXML
	Label lbBack, lbNonMemStore, lbHisLog, lbSignMy, lbLogInOut;
	@FXML
	Text txOrderNum, txStoreName, txStoreCellPhone;
	@FXML
	ImageView imHome;
	
	/**
	 * title       initialize
	 * Description 화면 출력될때 사용되는 메서드
	 * @param 		location, resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i=0; i<PathData.pProductName.length; i++) {
			PathData.pProductName[i]="주문상품";
			PathData.pProductCount[i]=0;
			PathData.pProductPrice[i]=0; 
			PathData.porderChecked[i]=true;
			PathData.pOrderImg[i]= "img/imgInit.png";
		}
		OrderDAO orderDao = new OrderDAO();
		StoreDAO storeDao = new StoreDAO();
		txOrderNum.setText(orderDao.readOrderNumLast());
		txStoreName.setText(PathData.pStoreName);
		txStoreCellPhone.setText(storeDao.readStoreCellPhone(txStoreName.getText()));
		//비회원
		if(PathData.pUserId==null) {
			lbNonMemStore.setText("비회원주문");
			lbHisLog.setText("주문내역");
			lbSignMy.setText("회원가입");
			lbLogInOut.setText("로그인");
			imHome.setOnMousePressed(event -> startAction(event));
			lbNonMemStore.setOnMousePressed(event -> nonMemberAction(event));
			lbHisLog.setOnMousePressed(event -> orderHistoryLogInAction(event));
			lbSignMy.setOnMousePressed(event -> signUpAction(event));
			lbLogInOut.setOnMousePressed(event -> logInAction(event));
			lbBack.setOnMousePressed(event -> lbStartAction(event));
		//회원
		} else {
			lbNonMemStore.setText("주문하기");
			lbHisLog.setText("주문내역");
			lbSignMy.setText("마이페이지");
			lbLogInOut.setText("로그아웃");
			imHome.setOnMousePressed(event -> homeAction(event));
			lbNonMemStore.setOnMousePressed(event -> storeAction(event));
			lbHisLog.setOnMousePressed(event -> orderHistoryAction(event));
			lbSignMy.setOnMousePressed(event -> myPageAction(event));
			lbLogInOut.setOnMousePressed(event -> logOutAction(event));
			lbBack.setOnMousePressed(event -> lbHomeAction(event));
		}
	}
	
	/**
	 * title       startAction
	 * Description Start로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void startAction(MouseEvent event) {
		PathData.startMethod(imHome, CompleteController.class);
	} //--startAction
	
	/**
	 * title       lbStartAction
	 * Description Label Back 눌렀을때 Start로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void lbStartAction(MouseEvent event) {
		PathData.logOutMethod(lbBack, CompleteController.class);
	} //--nonMemberAction
	
	/**
	 * title       nonMemberAction
	 * Description NonMember로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void nonMemberAction(MouseEvent event) {
		PathData.pStartNonMember = true;               
		PathData.pOrderHistoryLogInNonMember = false;   
		PathData.pSignUpNonMember = false;              
		PathData.pLogInNonMember = false;
		PathData.nonMemMethod(lbNonMemStore, CompleteController.class);
	} //--nonMemberAction
	
	/**
	 * title       orderHistoryLogInAction
	 * Description OrderHistoryLogIn으로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void orderHistoryLogInAction(MouseEvent event) {
		PathData.pStartOrderHistoryLogin = true;    
		PathData.pNonMemberOrderHistoryLogin = false;
		PathData.pSignUpOrderHistoryLogin = false;   
		PathData.pLogInOrderHistoryLogin = false;    
		PathData.orderHisLogInMethod(lbHisLog, CompleteController.class);
	} //--orderHistoryLogInAction
	
	/**
	 * title       signUpAction
	 * Description SignUp으로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void signUpAction(MouseEvent event) {
		PathData.pStartSignUp = true;            
		PathData.pNonMemberSignUp = false;        
		PathData.pOrderHistoryLogInSignUp = false;
		PathData.pLogInSingUp = false;            
		PathData.signUpMethod(lbSignMy, CompleteController.class);
	} //--signMyAction
	
	/**
	 * title       logInAction
	 * Description LogIn으로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void logInAction(MouseEvent event) {
		PathData.pStartLogIn = true;            
		PathData.pNonMemberLogIn = false;        
		PathData.pOrderHistoryLogInLogIn = false;
		PathData.pSignUpLogIn = false;           
		PathData.logInMethod(lbLogInOut, CompleteController.class);
	} //--logInAction
	
	/**
	 * title       homeAction
	 * Description Home으로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void homeAction(MouseEvent event) {
		PathData.homeMethod(imHome, CompleteController.class);
	} //--homeAction
	
	/**
	 * title       lbHomeAction
	 * Description Label Back 눌렀을때 Home으로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void lbHomeAction(MouseEvent event) {
		PathData.lbHomeMethod(lbBack, CompleteController.class);
	} //--nonMemberAction
	
	/**
	 * title       storeAction
	 * Description StoreInfo로 가는 액션 이벤트
	 * @param 	   event
	 */
	public void storeAction(MouseEvent event) {
		PathData.pHomeStore = true;        
		PathData.pNonMemberStore = false;   
		PathData.pOrderHistoryStore = false;
		PathData.pMyPageStore = false;      
		PathData.storeMethod(lbNonMemStore, CompleteController.class);
	} //--storeAction
	
	/**
	 * title       orderHistoryAction
	 * Description OrderHistory으로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void orderHistoryAction(MouseEvent event) {
		PathData.pHomeOrderHistory = true;             
		PathData.pStoreOrderHistory = false;            
		PathData.pOrderHistoryLoginOrderHistory = false;
		PathData.pMyPageOrderHistory = false;           
		PathData.orderHistoryMethod(lbHisLog, CompleteController.class);
	} //--orderHistoryAction
	
	/**
	 * title       myPageAction
	 * Description MyPage으로 가는 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void myPageAction(MouseEvent event) {
		PathData.pHomeMyPage = true;        
		PathData.pStoreMyPage = false;       
		PathData.pOrderHistoryMyPage = false;
		PathData.myPageMethod(lbSignMy, CompleteController.class);
	} //--myPageAction
	
	/**
	 * title       logOutAction
	 * Description 로그아웃 액션 이벤트
	 * @param 		event
	 */
	private void logOutAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("홈페이지로 돌아갑니다.");
		alert.setContentText("정말로 로그아웃 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.logOutMethod(lbLogInOut, CompleteController.class);
        }
	} //--logOutAction

}