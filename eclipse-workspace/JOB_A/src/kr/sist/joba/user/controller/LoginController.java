/**
 *<pre>
 * PackageName : kr.sist.joba.user.controller
 * Description : 유저 컨트롤러 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22 
 * @version 1.0
 * Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.user.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sist.hr.cmn.HROraConnectionMaker;
import com.sist.hr.member.dao.MemberDao;
import com.sist.hr.member.domain.MemberVO;

import data.PathData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * <pre>
 * PackageName : kr.sist.joba.user.controller
 * ClassName : LoginController.java
 * Description : 로그인 페이지(Login.fxml)의 컨트롤러
 *  ======Modification Information======
 *  생성일                  생성자                  수정내용
 *  ----------  --------   -------------------------------
 *  2019-11-26  이지은                  최초 생성
 *  2019-12-20  이지은                  개발 완료
 * </pre>
 * @since : 2019-11-26
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class LoginController implements Initializable {
	@FXML 
	ImageView imStart;
	@FXML 
	Label lbMessage, lbBack, lbStart, lbNonMember, lbOrderHistoryLogIn, lbSignUp, lbLogIn;
	@FXML 
	TextField logID;
	@FXML 
	PasswordField logPW;
	@FXML 
	Button btnLogin, btnUserIdFind;
	
	//알림창
	Alert alert = new Alert(AlertType.WARNING);
	
	/**
     * title       initialize
     * description initialize
     * @param       location, resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//상단 이미지 클릭시 Start.fxml 이동
		imStart.setOnMousePressed(event -> startAction(event));
		//상단 라벨 클릭시 뒤로 가기
		lbBack.setOnMousePressed(event -> backAction(event));
		//상단 라벨 클릭시 NonMember.fxml 이동
		lbNonMember.setOnMousePressed(event -> nonMemberAction(event));
		//상단 라벨 클릭시 OrderHistoryLogin.fxml 이동
		lbOrderHistoryLogIn.setOnMousePressed(event -> orderHistoryLogInAction(event));
		//상단 라벨 클릭시 SignUp.fxml 이동
		lbSignUp.setOnMousePressed(event -> signUpAction(event));
		//버튼 클릭시 Login 기능(toHomeAction)
		btnLogin.setOnAction(event -> toHomeAction(event));
		//버튼 클릭시 UserIdFind.fxml 이동
		btnUserIdFind.setOnAction(event -> userIdFindAction(event));
	} //--initialize
	
	/**
     * title       toHomeAction
     * description DB에 연결된 데이터를 바탕으로 로그인 기능 실행
     * @param       event
     */
	public void toHomeAction(ActionEvent event) {
		MemberDao dao = new MemberDao(new HROraConnectionMaker());
		String memId = logID.getText().trim();
		String memPw = this.logPW.getText().trim();
		//아이디, 비밀번호 입력 유효성 검사
		while(null == memId || "".equals(memId) && (null == memPw || "".equals(memPw))) {
			alert.setContentText("아이디와 비밀번호를 입력해 주세요");
			alert.showAndWait();
			logID.requestFocus();
			break;
		}
		//로그인 정보
		MemberVO inVO = new MemberVO();
		inVO.setGrpDiv("1"); //조 구분 
		inVO.setMemId(memId); //아이디
		inVO.setPw(memPw); //비밀번호
		//DB 로그인 메소드 사용
		MemberVO outVO = dao.do_login(inVO);
		alert.setContentText(outVO.getMessage()+"\n"+outVO);
		alert.showAndWait();
		//관리자 아이디인 경우
		if(outVO.getMessageDiv().equals("11") && outVO.getAuth().equals("9")) {
			//AdminStoreSelect.fxml 이동
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminStoreSelect.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
				primaryStage.setScene(scene);
				PathData.pUserId = logID.getText();
			} catch (IOException e) {
				e.printStackTrace();
			}
		//회원 아이디인 경우
		} else if(outVO.getMessageDiv().equals("11") && outVO.getAuth().equals("1")){
			//Home.fxml 이동
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/main/view/Home.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
				primaryStage.setScene(scene);
				PathData.pUserId = logID.getText();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} //--toHomeAction
	
	/**
     * title       backAction
     * description 뒤로가기 액션 이벤트
     * @param       event
     */
	public void backAction(MouseEvent event) {
		//Start.fxml 이동
		if(PathData.pStartLogIn==true) {
			PathData.logOutMethod(lbBack, LoginController.class);
		//NonMember.fxml 이동
		} else if(PathData.pNonMemberLogIn) {
			PathData.nonMemMethod(lbBack, LoginController.class);
		//OrderHistoryLogIn.fxml 이동
		} else if(PathData.pOrderHistoryLogInLogIn) {
			PathData.orderHisLogInMethod(lbBack, LoginController.class);
		//SignUp.fxml 이동
		} else if(PathData.pSignUpLogIn) {
			PathData.signUpMethod(lbBack, LoginController.class);
		//UserIdFind.fxml 이동
		} else if(PathData.pUserIdFindLogIn) {
			PathData.userIdFindMethod(lbBack, LoginController.class);
		}
	} //--backAction
	
	/**
     * title       startAction
     * description Start(첫 번째 메인 페이지).fxml 이동 액션 이벤트
     * @param       event
     */
	public void startAction(MouseEvent event) {
		PathData.startMethod(imStart, LoginController.class);
	} //--startAction
	
	/**
     * title       nonMemberAction
     * description NonMember.fxml 이동 액션 이벤트
     * @param       event
     */
	public void nonMemberAction(MouseEvent event) {
		PathData.pStartNonMember = false;            
		PathData.pOrderHistoryLogInNonMember = false;
		PathData.pSignUpNonMember = false;           
		PathData.pLogInNonMember = true; //제외하고 초기화
		PathData.pUserIdFindNonMember = false;
		//NonMember.fxml 이동
		PathData.nonMemMethod(lbNonMember, LoginController.class);
	} //--nonBackStartAction
	
	/**
     * title       orderHistoryLogInAction
     * description OrderHistoryLogIn.fxml 이동 액션 이벤트
     * @param       event
     */
	public void orderHistoryLogInAction(MouseEvent event) {
		PathData.pStartOrderHistoryLogin = false;    
		PathData.pNonMemberOrderHistoryLogin = false;
		PathData.pSignUpOrderHistoryLogin = false;   
		PathData.pLogInOrderHistoryLogin = true; //제외 초기화 
		PathData.pUserIdFindOrderHistoryLogin = false;
		//OrderHistoryLogin.fxml 이동
		PathData.orderHisLogInMethod(lbOrderHistoryLogIn, LoginController.class);
	} //--orderHistoryLogInAction
	
	/**
     * title       signUpAction
     * description SignUp.fxml 이동 액션 이벤트
     * @param       event
     */
	public void signUpAction(MouseEvent event) {
		PathData.pStartSignUp = false;            
		PathData.pNonMemberSignUp = false;        
		PathData.pOrderHistoryLogInSignUp = false;
		PathData.pLogInSingUp = true; //제외하고 초기화
		PathData.pUserIdFindSignUp = false;
		//SignUp.fxml 이동
		PathData.signUpMethod(lbSignUp, LoginController.class);
	} //--signUpAction
	
	/**
     * title       userIdFindAction
     * description UserIdFind.fxml 이동 액션 이벤트
     * @param       event
     */
	public void userIdFindAction(ActionEvent event) {
		PathData.pLogInUserIdFine = true; //제외하고 초기화          
		PathData.pOrderHistoryLogInUserIdFind = false;
		//UserIdFind.fxml 이동
		PathData.userIdFindMethod(btnUserIdFind, LoginController.class);
	} //--userIdFindAction
}

///**LogIn 버튼 액션(DAO)*/
//public void toHomeAction(ActionEvent event) {
//	ArrayList<UserVO> list = readFile("src/data/User.csv");
//	UserDAO userDao = new UserDAO();
//	try {
//		for(int i=0; i<list.size(); i++) {
//			if(logID.getText().isEmpty() && logPW.getText().isEmpty()) {
//				lbMessage.setText("아이디와 비밀번호를 입력해 주세요");
//			} else if(logID.getText().equals(list.get(i).getUserId()) && logPW.getText().equals(list.get(i).getUserPw())) {
//				if(userDao.adminAuthority(logID.getText()) == true) {
//					//PathData에 로그인 정보 보내기
//					PathData.pUserId = logID.getText();
//					Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/main/view/Home.fxml"));
//					Scene scene = new Scene(second);
//					Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
//					primaryStage.setScene(scene);
//				} else if(userDao.adminAuthority(logID.getText()) == false) {
//					Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminPage.fxml"));
//					Scene scene = new Scene(second);
//					Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
//					primaryStage.setScene(scene);
//				}
//			} else {
//				lbMessage.setText("아이디 또는 비밀번호가 일치하지 않습니다");
//			}
//		}
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//} //--toHomeAction