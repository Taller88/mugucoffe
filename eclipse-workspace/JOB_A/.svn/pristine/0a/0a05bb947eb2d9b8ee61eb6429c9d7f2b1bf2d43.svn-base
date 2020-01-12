/**
 *<pre>
 * @PackageName : kr.sist.joba.main.controller
 * @Description : 메인 컨트롤러 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22 
 * @version 1.0
 * Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.PathData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * <pre>
 * PackageName : kr.sist.joba.main.controller
 * ClassName : StartController.java
 * Description : 첫 메인 화면 페이지(Start.fxml)의 컨트롤러
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
public class StartController implements Initializable {
	@FXML
	Label lbNonMember, lbOrderHistoryLogin, lbSignUp, lbLogIn;
	
	/**
    * title       initialize
    * description initialize
    * @param       location, resources
    */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//상단 라벨 클릭시 NonMember.fxml 이동
		lbNonMember.setOnMousePressed(event -> nonMemberAction(event));
		//상단 라벨 클릭시 OrderHistoryLogin.fxml 이동
		lbOrderHistoryLogin.setOnMousePressed(event -> orderHistoryLogInAction(event));
		//상단 라벨 클릭시 SignUp.fxml 이동
		lbSignUp.setOnMousePressed(event -> signUpAction(event));
		//상단 라벨 클릭시 Login.fxml 이동
		lbLogIn.setOnMousePressed(event -> logInAction(event));
	} //--initialize

	/**
	 * title      	StartController
	 * description NonMember.fxml 이동하는 액션 이벤트
	 * @param 		event
	 */
	private void nonMemberAction(MouseEvent event) {
		PathData.pStartNonMember = true; //제외하고 초기화
		PathData.pOrderHistoryLogInNonMember = false;   
		PathData.pSignUpNonMember = false;              
		PathData.pLogInNonMember = false;
		//NonMember 이동
		PathData.nonMemMethod(lbNonMember, StartController.class);
	} //--nonMemberAction
	
	/**
	 * title      	orderHistoryLogInAction
	 * description OrderHistoryLogIn.fxml 이동하는 액션 이벤트
	 * @param 		event
	 */
	private void orderHistoryLogInAction(MouseEvent event) {
		PathData.pStartOrderHistoryLogin = true; //제외하고 초기화
		PathData.pNonMemberOrderHistoryLogin = false;   
		PathData.pSignUpOrderHistoryLogin = false;      
		PathData.pLogInOrderHistoryLogin = false;
		//OrderHistoryLogin 이동
		PathData.orderHisLogInMethod(lbOrderHistoryLogin, StartController.class);
	} //--orderHistoryLogInAction
	
	/**
	 * title      	signUpAction
	 * description SignUp.fxml 이동하는 액션 이벤트
	 * @param 		event
	 */
	private void signUpAction(MouseEvent event) {
		PathData.pStartSignUp = true; //제외하고 초기화
		PathData.pNonMemberSignUp = false;        
		PathData.pOrderHistoryLogInSignUp = false;
		PathData.pLogInSingUp = false;
		//SignUp 이동
		PathData.signUpMethod(lbSignUp, StartController.class);
	} //--signUpAction
	
	/**
	 * title      	logInAction
	 * description LogIn.fxml 이동하는 액션 이벤트
	 * @param 		event
	 */
	private void logInAction(MouseEvent event) {
		PathData.pStartLogIn = true; //제외하고 초기화
		PathData.pNonMemberLogIn = false;        
		PathData.pOrderHistoryLogInLogIn = false;
		PathData.pSignUpLogIn = false;
		//Login 이동
		PathData.logInMethod(lbLogIn, StartController.class);
	} //--logInAction
}
