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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.PathData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.sist.joba.user.dao.NonMemberDAO;
import kr.sist.joba.user.dao.NonMemberVO;

/**
 * <pre>
 * PackageName : kr.sist.joba.user.controller
 * ClassName : NonMemController.java
 * Description : 비회원 주문시 정보 입력하는 페이지(NonMember.fxml)의 컨트롤러
 *  ======Modification Information======
 *  생성일                  생성자                  수정내용
 *  ----------  --------   -------------------------------
 *  2019-11-26  이지은                  최초 생성
 *  2019-12-18  이지은                  개발 완료
 * </pre>
 * @since : 2019-11-26
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class NonMemController implements Initializable {
	@FXML
	ImageView imHome;
	@FXML
	Label nonMemMessage, lbBack, lbStart, lbOrderHistoryLogIn, lbSignUp, lbLogIn;
	@FXML
	TextField nonMemName, nonMemCellPhone;
	@FXML
	PasswordField nonMemPW01, nonMemPW02;
	@FXML
	TextField nonMemArea, nonMemAddress;
	@FXML
	Button btnNonOk;
	
	NonMemberDAO nonMemberDao = new NonMemberDAO();
	
	/**
     * title       initialize
     * description initialize
     * @param       location, resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imHome.setOnMousePressed(event -> homeAction(event));
		lbBack.setOnMousePressed(event -> backAction(event));
		lbOrderHistoryLogIn.setOnMousePressed(event -> orderHistoryLogInAction(event));
		lbSignUp.setOnMousePressed(event -> signUpAction(event));
		lbLogIn.setOnMousePressed(event -> logInAction(event));
		btnNonOk.setOnAction(event -> nonOkAction(event));
	}
	
	/**
     * title       nonOkAction
     * description 비회원 정보 유효성 검사 및 화면 전환 메소드
     * @param       event
     */
	public void nonOkAction(ActionEvent event) {
		String cellPhone = nonMemCellPhone.getText().trim();
		try {
			//전체 공란 확인
			if (spaceCheck() == false) {
				nonMemMessage.setText("공란을 입력해 주세요");
			//PW userPw == userPw2 확인
			} else if(!(nonMemPW01.getText().equals(nonMemPW02.getText()))) { 
				nonMemMessage.setText("비밀번호가 일치하지 않습니다"); 
			//비밀번호 4자, 영문 또는 숫자
			} else if(pwRegEx() == false) {
				nonMemMessage.setText("비밀번호는 영문 또는 숫자로 4자입니다");
			//핸드폰 번호 모두 숫자, 최소 10자~11자
			} else if(!(10<=cellPhone.length() && cellPhone.length()<=11)) {
				nonMemMessage.setText("휴대폰 번호는 10~11자입니다");
			} else if(cellPhoneCheck() == false) {
				nonMemMessage.setText("휴대폰 번호는 모두 숫자로 입력해 주세요");
			//주소 데이터 내에서 시, 구 사이에 스페이스 바가 들어갔는지 확인
			} else if(areaRegEx(nonMemArea.getText()) == false) {
				nonMemMessage.setText("주소 형식을 확인해주세요 ex)서울특별시 서초구 또는 서울시 서초구");
			//비회원정보 기입 성공 후 StoreInfo.fxml 가기
			} else {
				//static 변수 비회원 정보 임시 저장
				PathData.pUserName = nonMemName.getText().trim();
				PathData.pUserArea = nonMemArea.getText().trim();
				PathData.pUserAddress = nonMemAddress.getText().trim();
				PathData.pUserCellPhone = nonMemCellPhone.getText().trim();
				PathData.pHomeStore = false;        
				PathData.pNonMemberStore = true;   
				PathData.pOrderHistoryStore = false;
				PathData.pMyPageStore = false;      
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/store/view/StoreInfo.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) btnNonOk.getScene().getWindow();
				primaryStage.setScene(scene);
				//기입한 비회원 정보가 NonMember.csv에 있으면 업데이트(덮어쓰기)
				if(nonMemberDao.nonMemberCellPhoneCheck(nonMemCellPhone.getText().trim()) == true) {
					NonMemberVO modVO = nonMemberDao.getUpdateInputData(nonMemCellPhone.getText().trim(), nonMemPW01.getText().trim());
					nonMemberDao.do_update(modVO);
				//기입한 비회원 정보가 NonMember.csv에 없으면 새롭게 저장
				} else {
					String[] inputData = {nonMemName.getText().trim(), nonMemPW01.getText().trim(), nonMemCellPhone.getText().trim()};
					NonMemberVO vo = new NonMemberVO(inputData[0],inputData[1],inputData[2]);
					nonMemberDao.do_save(vo);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} //--nonOkAction
	
	/**
     * title       spaceCheck
     * description 비회원 주문 전  입력된 정보가 공란인지 확인하는 메소드
     */
	public boolean spaceCheck() {
		boolean check = true;
		//nonMemName, nonMemPW01, nonMemPW02, nonMemArea, nonMemAddress, nonMemAddress
		if (nonMemName.getText().isEmpty() || nonMemPW01.getText().isEmpty()
		|| nonMemPW02.getText().isEmpty() || nonMemArea.getText().isEmpty() 
		|| nonMemAddress.getText().isEmpty() || nonMemCellPhone.getText().isEmpty()) {
			check = false;
		}
		return check;
	} //--spaceCheck

	/**
     * title       cellPhoneCheck
     * description 비회원 주문 전 입력된 휴대폰 번호가 모두 숫자인지 확인하는 메소드
     * @return      isNumber
     */
	public boolean cellPhoneCheck() {
		boolean isNumber = true;
		String cellPhone = nonMemCellPhone.getText();
		char cellPhoneCh = ' ';
		for(int i=0; i<cellPhone.length(); i++) {
			//숫자인지 비교
			cellPhoneCh = cellPhone.charAt(i);
			if(!('0'<=cellPhoneCh && cellPhoneCh<='9')) {
				isNumber = false;
				break;
			}
		}
		return isNumber;
	} //--cellPhoneCheck
	
	/**
     * title       pwRegEx
     * description 비회원 주문 전 입력된 비밀번호를 정규표현식으로 유효성 검사하는 메소드
     * @return      check
     */
	public boolean pwRegEx() {
		Pattern pwPattern = null;
		Matcher pwMatch = null;
		boolean check = false;
		//영문 또는 숫자 4자
		String source = "^[a-zA-Z0-9]{4}$";
		String strPw = nonMemPW01.getText();
		pwPattern = Pattern.compile(source);
		pwMatch = pwPattern.matcher(strPw);
		while(true) {
			if(pwMatch.matches()) {
				check = true;
				break;
			} else {
				check = false;
				break;
			}
		}
		return check;
	} //--pwRegEx
	
	/**
     * title       areaRegEx
     * description 비회원 주문 전 입력된 주소(Area) 정규표현식으로 유효성 검사하는 메소드
     * @param       areaText
     * @return      check
     */
	public boolean areaRegEx(String areaText) {
		Pattern areaPattern = null;
		Matcher areaMatch = null;
		boolean check = false;
		//한글'시'* 공백문자(1) 한글'구'*
		String source = "^[가-힣]*\\s{1}[가-힣]*$";
		String strArea = areaText.trim();
		areaPattern = Pattern.compile(source);
		areaMatch = areaPattern.matcher(strArea);
		String word01 = strArea.split("\\s")[0];
		String word02 = strArea.split("\\s")[1];
		while(true) {
			if(areaMatch.matches()) {
				if((word01.lastIndexOf("시")==2 || word01.lastIndexOf("시")==4)
				&& (word02.lastIndexOf("구")==2 || word02.lastIndexOf("구")==3)) {
					check = true;
					break;
				} else {
					check = false;
					break;
				}
			} else {
				check = false;
				break;
			}
		}
		return check;
	} //--areaRegEx
	
	/**
     * title       backAction
     * description 뒤로가기 액션 이벤트
     * @param       event
     */
	public void backAction(MouseEvent event) {
		//Start.fxml 이동
		if(PathData.pStartNonMember==true) {
			PathData.logOutMethod(lbBack, NonMemController.class);
		//OrderHistoryLogIn.fxml 이동
		} else if(PathData.pOrderHistoryLogInNonMember==true) {
			PathData.orderHisLogInMethod(lbBack, NonMemController.class);
		//SignUp.fxml 이동
		} else if(PathData.pSignUpNonMember==true) {
			PathData.signUpMethod(lbBack, NonMemController.class);
		//LogIn.fxml 이동
		} else if(PathData.pLogInNonMember==true) {
			PathData.logInMethod(lbBack, NonMemController.class);
		}
	} //--backAction
	
	/**
     * title       startAction
     * description Start.fxml 이동 액션 이벤트
     * @param       event
     */
	public void homeAction(MouseEvent event) {
		PathData.startMethod(imHome, NonMemController.class);
	} //--homeAction
	
	/**
     * title       orderHistoryLogInAction
     * description OrderHistoryLogIn.fxml 이동 액션 이벤트
     * @param       event
     */
	public void orderHistoryLogInAction(MouseEvent event) {
		PathData.pStartOrderHistoryLogin = false;    
		PathData.pNonMemberOrderHistoryLogin = true;
		PathData.pSignUpOrderHistoryLogin = false;   
		PathData.pLogInOrderHistoryLogin = false;    
		PathData.orderHisLogInMethod(lbOrderHistoryLogIn, NonMemController.class);
	} //--orderHistoryLogInAction
	
	/**
     * title       signUpAction
     * description SignUp.fxml 이동 액션 이벤트
     * @param       event
     */
	public void signUpAction(MouseEvent event) {
		PathData.pStartSignUp = false;            
		PathData.pNonMemberSignUp = true;        
		PathData.pOrderHistoryLogInSignUp = false;
		PathData.pLogInSingUp = false;            
		PathData.signUpMethod(lbSignUp, NonMemController.class);
	} //--signUpAction
	
	/**
     * title       logInAction
     * description Login.fxml 이동 액션 이벤트
     * @param       event
     */
	public void logInAction(MouseEvent event) {
		PathData.pStartLogIn = false;            
		PathData.pNonMemberLogIn = true;        
		PathData.pOrderHistoryLogInLogIn = false;
		PathData.pSignUpLogIn = false;
		PathData.pUserIdFindLogIn = false;
		PathData.logInMethod(lbLogIn, NonMemController.class);
	} //--logInAction
}
