/**
 *<pre>
 * PackageName : kr.sist.joba.user.controller
 * description : 유저 컨트롤러 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.user.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sist.hr.cmn.HROraConnectionMaker;
import com.sist.hr.cmn.SearchVO;
import com.sist.hr.member.dao.MemberDao;
import com.sist.hr.member.domain.MemberVO;

import data.PathData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * <pre>
 * PackageName : kr.sist.joba.user.controller
 * ClassName : UserIdFindController.java
 * description : 아이디, 비밀번호 찾기 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-19    박종훈         	최초생성
 *  2019-12-23    박종훈         	개발완료
 *  
 * </pre>
 * @since : 2019-11-22
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class UserIdFindController implements Initializable {
	@FXML
	ImageView imStart;
	@FXML
	Label lbBack, lbNonMember, lbOrderHistoryLogIn, lbSignUp, lbLogIn;
	@FXML
	TextField tfCellPhone01, tfCellPhone02, tfUserId;
	@FXML
	Button btnIdFind, btnPwFind;
	
	/**
	 * title       initialize
	 * description 화면 출력될때 사용되는 메서드
	 * @param 		arg0, arg1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		imStart.setOnMousePressed(event -> startAction(event));
		lbBack.setOnMousePressed(event -> backAction(event));
		lbNonMember.setOnMousePressed(event -> orderHistoryLogInAction(event));
		lbOrderHistoryLogIn.setOnMousePressed(event -> orderHistoryLogInAction(event));
		lbSignUp.setOnMousePressed(event -> signUpAction(event));
		lbLogIn.setOnMousePressed(event -> logInAction(event));
		btnIdFind.setOnAction(event -> idFindAction(event));
		btnPwFind.setOnAction(event -> pwFindAction(event));
	} //--initialize
	
	/**
	 * title       idFindAction
	 * description 아이디 찾기 버튼 액션 이벤트
	 * @param 		event
	 */
	public void idFindAction(ActionEvent event) {
		if(tfCellPhone01.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("휴대폰번호를 입력해주세요.");
	        alert.showAndWait();
		} else {
			MemberDao dao = new MemberDao(new HROraConnectionMaker());
			SearchVO inVO = new SearchVO();
			inVO.setGrpDiv("1");// 조 구분
			inVO.setSearchDiv("4");
			inVO.setSearchWord(tfCellPhone01.getText());// 휴대폰번호 구분
		    List<MemberVO> list = (List<MemberVO>) dao.do_retrieve(inVO);
		    if(list.size()<=0) {
		    	 Alert alert1 = new Alert(AlertType.WARNING);
				    alert1.setTitle("아이디 조회!");
				    alert1.setHeaderText("조회하려는 아이디가 없습니다.");
				    alert1.setContentText("회원가입을 진행해 주세요.");
			        alert1.showAndWait();
		    }else  if(list.size()>0){
		    Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("아이디 조회!");
		    alert.setHeaderText("ID : "+list.get(0).getMemId());
	        alert.showAndWait();
		    }
		}
	} //--idFindAction
	
	/**
	 * title       pwFindAction
	 * description 비밀번호 찾기 버튼 액션 이벤트
	 * @param 		event
	 */
	public void pwFindAction(ActionEvent event) {
		if(tfCellPhone02.getText().equals("") || tfUserId.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("휴대폰번호와 아이디를 입력해 주세요.");
	        alert.showAndWait();
		} else {
			MemberDao dao = new MemberDao(new HROraConnectionMaker());
			SearchVO inVO = new SearchVO();
		    inVO.setGrpDiv("1");// 조 구분
		    inVO.setSearchDiv("1");// 아이디 구분
		    inVO.setSearchWord(tfUserId.getText());// 휴대폰번호 구분
		    List<MemberVO> list = (List<MemberVO>) dao.do_retrieve(inVO);
		    // 입력한 휴대폰번호 또는 아이디가 DB에 없는 경우
		    if((!tfUserId.getText().equals(list.get(0).getMemId()))&&(!tfCellPhone02.getText().equals(list.get(0).getCellphone()))) {
		    	 Alert alert1 = new Alert(AlertType.WARNING);
				    alert1.setTitle("비밀번호 조회!");
				    alert1.setHeaderText("조회한 휴대폰 또는 아이디가 없습니다.");
				    alert1.setContentText("회원가입을 진행해 주세요.");
			        alert1.showAndWait();
			// 입력한 휴대폰번호가 DB와 다를 경우
		    }else if((tfUserId.getText().equals(list.get(0).getMemId()))&&(!tfCellPhone02.getText().equals(list.get(0).getCellphone()))) {
		    	 Alert alert1 = new Alert(AlertType.WARNING);
				    alert1.setTitle("비밀번호 조회!");
				    alert1.setHeaderText("휴대폰 번호가 다릅니다.");
			        alert1.showAndWait();
			// 입력한 아이디가 DB와 다를 경우
		    }else if((!tfUserId.getText().equals(list.get(0).getMemId()))&&(tfCellPhone02.getText().equals(list.get(0).getCellphone()))) {
		    	 Alert alert1 = new Alert(AlertType.WARNING);
				    alert1.setTitle("비밀번호 조회");
				    alert1.setHeaderText("아이디가 다릅니다.");
			        alert1.showAndWait();
			// 검사 통과 후 알림창 출력
		    }else{
		    	 Alert alert2 = new Alert(AlertType.WARNING);
				    alert2.setTitle("비밀번호");
				    alert2.setHeaderText("PW : "+list.get(list.size()-1).getPw());
			        alert2.showAndWait();
		    }
		}
	} //--pwFindAction
	
	/**
	 * title       backAction
	 * description 뒤로가기 액션 이벤트
	 * @param 		event
	 */
	public void backAction(MouseEvent event) {
		// 로그인으로 가기
		if(PathData.pLogInUserIdFine == true) {
			PathData.logInMethod(lbBack, UserIdFindController.class);
		// 주문내역로그인으로 가기
		} else if(PathData.pOrderHistoryLogInUserIdFind == true) {
			PathData.orderHisLogInMethod(lbBack, UserIdFindController.class);
		}
	} //--backAction
	
	/**
	 * title       startAction
	 * description Start 가기 액션 이벤트
	 * @param 		event
	 */
	public void startAction(MouseEvent event) {
		PathData.startMethod(imStart, UserIdFindController.class);
	} //--startAction
	
	/**
	 * title       nonMemberAction
	 * description NonMember 가는 액션 이벤트
	 * @param 		event
	 */
	public void nonMemberAction(MouseEvent event) {
		PathData.pStartNonMember = false;            
		PathData.pOrderHistoryLogInNonMember = false;
		PathData.pSignUpNonMember = false;           
		PathData.pLogInNonMember = false;            
		PathData.pUserIdFindNonMember = true;       
		PathData.nonMemMethod(lbNonMember, LoginController.class);
	} //--nonMemberAction
	
	/**
	 * title       orderHistoryLogInAction
	 * description OrderHistoryLogIn 가는 액션 이벤트
	 * @param 		event
	 */
	public void orderHistoryLogInAction(MouseEvent event) {
		PathData.pStartOrderHistoryLogin = false;     
		PathData.pNonMemberOrderHistoryLogin = false; 
		PathData.pSignUpOrderHistoryLogin = false;    
		PathData.pLogInOrderHistoryLogin = false;     
		PathData.pUserIdFindOrderHistoryLogin = true;
		PathData.orderHisLogInMethod(lbOrderHistoryLogIn, UserIdFindController.class);
	} //--orderHistoryLogInAction
	
	/**
	 * title       signUpAction
	 * description SignUp 가는 액션 이벤트
	 * @param 		event
	 */
	public void signUpAction(MouseEvent event) {
		PathData.pStartSignUp = false;            
		PathData.pNonMemberSignUp = false;        
		PathData.pOrderHistoryLogInSignUp = false;
		PathData.pLogInSingUp = false;            
		PathData.pUserIdFindSignUp = true;       
		PathData.signUpMethod(lbSignUp, UserIdFindController.class);
	} //--signUpAction
	
	/**
	 * title       logInAction
	 * description LogIn 가는 액션 이벤트
	 * @param 		event
	 */
	public void logInAction(MouseEvent event) {
		PathData.pStartLogIn = false;
		PathData.pNonMemberLogIn = false;
		PathData.pOrderHistoryLogInLogIn = false;
		PathData.pSignUpLogIn = false;
		PathData.pUserIdFindLogIn = true;
		PathData.logInMethod(lbLogIn, UserIdFindController.class);
	} //--logInAction

}
