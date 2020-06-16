/**
 *<pre>
 * PackageName : kr.sist.joba.main.controller
 * Description : 메인 컨트롤러 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22 
 * @version 1.0
 * Copyright (C) by JOB_A All right reserved.
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * <pre>
 * PackageName : kr.sist.joba.main.controller
 * ClassName : HomeController.java
 * Description : 회원 로그인 후 (두 번째) 메인 화면 페이지(Home.fxml)의 컨트롤러
 *  ======Modification Information======
 *  생성일                  생성자                  수정내용
 *  ----------  --------   -------------------------------
 *  2019-11-26  이지은                  최초 생성
 *  2019-12-16  이지은                  개발 완료
 * </pre>
 * @since : 2019-11-26
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class HomeController implements Initializable {
	@FXML
	Label lbLogOut, lbMyPage, lbStore, lbOrderHistory;

	/**
     * title       initialize
     * description initialize
     * @param      arg0, arg1
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbStore.setOnMousePressed(event -> storeAction(event));
		lbOrderHistory.setOnMousePressed(event -> orderHistoryAction(event));
		lbMyPage.setOnMousePressed(event -> myPageAction(event));
		lbLogOut.setOnMousePressed(event -> logOutAction(event));
		//OrderList에 있는 기존 주문내역을 초기화하는 메소드
		orderListInitialize();
	}
	
	/**
     * title       orderListInitialize
     * description OrderList에 있는 기존 주문내역 초기화
     */
	private void orderListInitialize() {
		for(int i=0; i<PathData.pProductName.length; i++) {
		PathData.pProductName[i] = "주문상품";
		PathData.pProductCount[i] = 0;
		PathData.pProductPrice[i] = 0; 
		PathData.porderChecked[i] = true;
		PathData.pOrderImg[i] = "img/imgInit.png";
		}
	}

	/**
     * title       storeAction
     * description StoreInfo.fxml 이동하는 액션 이벤트
     * @param       event
     */
	private void storeAction(MouseEvent event) {
		PathData.pHomeStore = true;        
		PathData.pNonMemberStore = false;   
		PathData.pOrderHistoryStore = false;
		PathData.pMyPageStore = false;      
		PathData.storeMethod(lbStore, HomeController.class);
	} //--storeAction

	/**
     * title       orderHistoryAction
     * description OrderHistory.fxml 이동하는 액션 이벤트
     * @param       event
     */
	private void orderHistoryAction(MouseEvent event) {
		PathData.pHomeOrderHistory = true;             
		PathData.pStoreOrderHistory = false;             
		PathData.pOrderHistoryLoginOrderHistory = false;
		PathData.pMyPageOrderHistory = false;           
		PathData.orderHistoryMethod(lbOrderHistory, HomeController.class);
	} //--orderHistoryAction
	
	/**
     * title       myPageAction
     * description MyPage.fxml 이동하는 액션 이벤트
     * @param       event
     */
	private void myPageAction(MouseEvent event) {
		PathData.pHomeMyPage = true;        
		PathData.pStoreMyPage = false;       
		PathData.pOrderHistoryMyPage = false;
		PathData.myPageMethod(lbMyPage, HomeController.class);
	} //--myPageAction
	
	/**
     * title       logOutAction
     * description 로그아웃 액션 이벤트
     * @param       event
     */
	private void logOutAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("홈페이지로 돌아갑니다.");
		alert.setContentText("정말로 로그아웃 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.logOutMethod(lbLogOut, HomeController.class);
        }
	} //--logOutAction
}
