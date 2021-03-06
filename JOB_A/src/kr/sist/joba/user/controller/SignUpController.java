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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sist.hr.cmn.HROraConnectionMaker;
import com.sist.hr.cmn.SearchVO;
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
import kr.sist.joba.user.dao.UserPointDAO;
import kr.sist.joba.user.dao.UserPointVO;

/**
 * <pre>
 * PackageName : kr.sist.joba.user.controller
 * ClassName : SignUpController.java
 * Description : 회원가입 페이지(SignUp.fxml)의 컨트롤러
 *  ======Modification Information======
 *  생성일                  생성자                  수정내용
 *  ----------  --------   -------------------------------
 *  2019-11-26  이지은                  최초 생성
 *  2019-12-23  이지은                  개발 완료
 * </pre>
 * @since : 2019-11-26
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class SignUpController implements Initializable {
	@FXML
	ImageView imStart;
	@FXML
	Button btnIdCheck, btnCellPhoneCheck, btnSignUpOk;
	@FXML
	TextField userId, userName, userArea, userAddress, userCellPhone;
	@FXML
	PasswordField userPw01, userPw02;
	@FXML
	Label signUpMessage, lbBack, lbNonMember, lbOrderHistoryLogIn, lbLogIn, lbKorean, lbEnglish;
	@FXML //다국어용 label
	Label labelSignUp, labelID, labelPW, labelName, labelAddress, labelHP;

	//아이디, 핸드폰 중복 확인
	boolean checkedId = true;
	boolean checkedHp = true;
	
	//다국어
	String profFile="Language.properties";
	
	UserPointDAO userPointDao = new UserPointDAO();
	Alert alert = new Alert(AlertType.INFORMATION);
	
	/**
     * title       initialize
     * description initialize
     * @param       location, resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imStart.setOnMousePressed(event -> startAction(event));
		lbBack.setOnMousePressed(event -> backAction(event));
		lbNonMember.setOnMousePressed(event -> nonMemberAction(event));
		lbOrderHistoryLogIn.setOnMousePressed(event -> orderHistoryLogInAction(event));
		lbLogIn.setOnMousePressed(event -> logInAction(event));
		//아이디 중복 체크
		btnIdCheck.setOnAction(event -> btnIdCheckAction(event));
		//핸드폰 중복체크
		btnCellPhoneCheck.setOnAction(event -> btnCellPhoneCheckAction(event));
		//회원가입 성공
		btnSignUpOk.setOnAction(event -> signUpOkAction(event));
		//다국어
		lbKorean.setOnMousePressed(event -> korChangeAction(event));
		lbEnglish.setOnMousePressed(event -> engChangeAction(event));		
	} //--initialize

	/**
     * title       btnIdCheckAction
     * description 아이디 중복 확인 메소드
     * @param       event
     * @return      check
     */
	public boolean btnIdCheckAction(ActionEvent event) {
		boolean check = true;
		if(userId.getText().isEmpty()){
			signUpMessage.setText("아이디를 입력해 주세요");
		} else if(idOverLapCheck(userId.getText()) == true) {
			signUpMessage.setText("중복된 아이디입니다");
			check = true;
		} else if((idOverLapCheck(userId.getText()) == false) && (idRegEx() == true)) {
			signUpMessage.setText("사용 가능한 아이디입니다");
			check = false;
			checkedId = false;
		} else if(idRegEx() == false) {
			signUpMessage.setText("아이디는 영문 또는 숫자로 3~10자입니다");
			check = true;
		}
		return check;
	} //--btnIdCheckAction

	/**
     * title       btnCellPhoneCheckAction
     * description 휴대폰 번호 중복 확인 메소드
     * @param       event
     * @return      check
     */
	public boolean btnCellPhoneCheckAction(ActionEvent event) {
		boolean check = true;
		if(userCellPhone.getText().isEmpty()){
			signUpMessage.setText("핸드폰 번호를 입력해 주세요.");
		} else if(cellPhoneOverLapCheck(userCellPhone.getText()) == true) {
			signUpMessage.setText("입력하신 번호로 가입한 이력이 있습니다.");
			check = true;
		} else if((cellPhoneOverLapCheck(userCellPhone.getText()) == false) && (cellPhoneCheck() == true)) {
			signUpMessage.setText("인증이 완료 되었습니다.");
			check = false;
			checkedHp = false;
		}
		return check;
	} //--btnCellPhoneCheckAction
	
	/**
     * title       idOverLapCheck
     * description DB 데이터 아이디 중복 검사 메소드
     * @param       inputId
     * @return      check
     */
	public boolean idOverLapCheck(String inputId) {
		boolean check = false;
		//DB DAO
		MemberDao dbDao = new MemberDao(new HROraConnectionMaker());
		//DB에 있는 데이터값을 List<MemberVO>형식으로 반환
		SearchVO searchVO = new SearchVO();
		searchVO.setGrpDiv("1");
		//"1" getfield com.sist.hr.cmn.SearchVO.searchDiv
		searchVO.setSearchDiv("1");
		searchVO.setSearchWord(inputId.trim());
    	List<MemberVO> dbData = (List<MemberVO>) dbDao.do_retrieve(searchVO);
    	for(int i=0;i<dbData.size();i++){
            if(dbData.get(i).getMemId().equals(userId.getText())) {
            	check = true;
            } else {
            	check = false;
            }
    	}
		return check;
	} //--idOverLapCheck
	
	/**
     * title       cellPhoneOverLapCheck
     * description DB 데이터 휴대폰 번호 중복 검사 메소드
     * @param       cellphone
     * @return      check
     */
	public boolean cellPhoneOverLapCheck(String cellphone) {
		boolean check = false;
		//DB DAO
		MemberDao dbDao = new MemberDao(new HROraConnectionMaker());
		//DB에 있는 데이터값을 List<MemberVO>형식으로 반환
		SearchVO searchVO = new SearchVO();
		searchVO.setGrpDiv("1");
		//"1" getfield com.sist.hr.cmn.SearchVO.searchDiv
		searchVO.setSearchDiv("4");
		searchVO.setSearchWord(cellphone.trim());
    	List<MemberVO> dbData = (List<MemberVO>) dbDao.do_retrieve(searchVO);
    	for(int i=0;i<dbData.size();i++){
            if(dbData.get(i).getCellphone().equals(cellphone)) {
            	check = true;
            } else {
            	check = false;
            }
    	}
		return check;
	} //--cellPhoneOverLapCheck
	
	/**
     * title       signUpOkAction
     * description 회원 가입 유효성 검사 메소드
     * @param       event
     */
	public void signUpOkAction(ActionEvent event) {
		String cellPhone = userCellPhone.getText().trim();
		//전체 공란 확인
		if (spaceCheck() == false) {
			signUpMessage.setText("공란을 입력해 주세요");
		//PW userPw == userPw2 확인
		} else if(!(userPw01.getText().equals(userPw02.getText()))) { 
			signUpMessage.setText("비밀번호가 일치하지 않습니다");
		//아이디 영문 또는 숫자, 3자~10자
		} else if(idRegEx() == false) {
			signUpMessage.setText("아이디는 영문 또는 숫자로 3~10자입니다");
		//비밀번호 영문 또는 숫자, 4자~10자
		} else if(pwRegEx() == false) {
			signUpMessage.setText("비밀번호는 영문 또는 숫자로 4~10자입니다");
		//핸드폰 번호 모두 숫자, 최소 10자~11자
		} else if(!(10<=cellPhone.length() && cellPhone.length()<=11)) {
			signUpMessage.setText("휴대폰 번호는 숫자 10~11자입니다");
		} else if(cellPhoneCheck() == false) {
			signUpMessage.setText("휴대폰 번호는 모두 숫자로 입력해 주세요");
		//주소 데이터 내에서 시, 구 사이에 스페이스 바가 들어갔는지 확인
		} else if(areaRegEx(userArea.getText()) == false) {
			signUpMessage.setText("주소 형식을 확인해주세요 ex)서울특별시 서초구 또는 서울시 서초구");
		//아이디 중복 체크 검사 확인
		} else if(btnIdCheckAction(event) == true && checkedId == true) {
			signUpMessage.setText("아이디 중복 검사를 해주세요");
		//핸드폰 번호 중복 체크 검사 확인
		} else if(btnCellPhoneCheckAction(event) == true && checkedHp == true) {
			signUpMessage.setText("핸드폰 번호 인증을 해주세요");
		//회원 정보 기입 완료 후 화면 전환
		} else {
			if(checkedId == false && checkedHp == false) {
				alert.setHeaderText("회원 가입 완료!");
				alert.setContentText("회원가입이 성공적으로 완료 되었습니다.");
				alert.showAndWait();
				//Login.fxml 가기
				if(PathData.pLogInSingUp == true) {
					singUpMethod("/kr/sist/joba/user/view/Login.fxml");
				//OrderHistoryLogin.fxml 가기
				} else if(PathData.pOrderHistoryLogInSignUp == true) {
					singUpMethod("/kr/sist/joba/orderhistory/view/OrderHistoryLogin.fxml");
				//Start.fxml 가기
				} else if(PathData.pStartSignUp == true) {
					singUpMethod("/kr/sist/joba/main/view/Start.fxml");
				//NonMember.fxml 가기
				} else if(PathData.pNonMemberSignUp == true) {
					singUpMethod("/kr/sist/joba/user/view/NonMember.fxml");
				}
			}
		}
	} //--signUpAction
	
	/**
     * title       singUpMethod
     * description 입력된 회원가입 정보 DB에 저장하는 메소드
     * @param       path
     */
	public void singUpMethod(String path) {
		MemberDao dbDao = new MemberDao(new HROraConnectionMaker());
		//앞뒤 공백 제거한 후 회원정보 배열 생성 //ID, PW, 이름, 권한, 조, Email, 휴대폰 번호, 주소, 상세주소, 마일리지
		MemberVO inVO = new MemberVO(userId.getText().trim(), "1", userPw01.getText().trim(), userName.getText().trim(), "1", "NO Use", userCellPhone.getText().trim(), userArea.getText().trim(), userAddress.getText().trim(), "0", "", "admin", "", "admin");
		dbDao.do_save(inVO);
		
	    //마일리지 저장
	    String[] inputDataUserPoint = {userId.getText().trim(), "0"};
	    UserPointVO voUserPoint = new UserPointVO(inputDataUserPoint[0], inputDataUserPoint[1]);
	    userPointDao.do_save(voUserPoint);
		try {
			 Parent second = FXMLLoader.load(getClass().getResource(path));
			 Scene scene = new Scene(second);
		     Stage primaryStage = (Stage) btnSignUpOk.getScene().getWindow();
		     primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} //--singUpMethod

	/**
     * title       spaceCheck
     * description 회원가입 페이지의 입력된 정보가 공란인지 확인하는 메소드
     * @return      check
     */
	public boolean spaceCheck() {
		boolean check = true;
		// userId, userPw01, userPw02, userName, userArea, userAddress, userCellPhone
		if (userId.getText().isEmpty() || userPw01.getText().isEmpty() || userPw02.getText().isEmpty()
		|| userName.getText().isEmpty() || userArea.getText().isEmpty() 
		|| userAddress.getText().isEmpty() || userCellPhone.getText().isEmpty()) {
			check = false;
		}
		return check;
	} //--spaceCheck
	
	/**
     * title       cellPhoneCheck
     * description 회원가입 페이지의 입력된 휴대폰 번호가 모두 숫자인지 확인하는 메소드
     * @return      isNumber
     */
	public boolean cellPhoneCheck() {
		boolean isNumber = true;
		String cellPhone = userCellPhone.getText();
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
     * title       idRegEx
     * description 회원가입 페이지의 입력된 아이디를 정규표현식으로 유효성 검사하는 메소드
     * @return      check
     */
	public boolean idRegEx() {
		Pattern idPattern = null;
		Matcher idMatch = null;	
		boolean check = false;
		//영문 또는 숫자 3~10자
		String source = "^[a-zA-Z0-9]{3,10}$";
		String strPw = userId.getText();
		idPattern = Pattern.compile(source);
		idMatch = idPattern.matcher(strPw);
		while(true) {
			if(idMatch.matches()) {
				check = true;
				break;
			} else {
				check = false;
				break;
			}
		}
		return check;
	} //--idRegEx
	
	/**
     * title       pwRegEx
     * description 회원가입 페이지의 입력된 비밀번호를 정규표현식으로 유효성 검사하는 메소드
     * @return      check
     */
	public boolean pwRegEx() {
		Pattern pwPattern = null;
		Matcher pwMatch = null;	
		boolean check = false;
		//영문 또는 숫자 4~10자
		String source = "^[a-zA-Z0-9]{4,10}$";
		String strPw = userPw01.getText();
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
     * description 회원가입 페이지의 입력된 주소(Area) 정규표현식으로 유효성 검사하는 메소드
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
		//LogIn.fxml 이동
		if(PathData.pLogInSingUp==true) {
			PathData.logInMethod(lbBack, SignUpController.class);
		//OrderHistoryLogIn.fxml 이동
		}else if(PathData.pOrderHistoryLogInSignUp==true) {
			PathData.orderHisLogInMethod(lbBack, SignUpController.class);
		//NonMember.fxml 이동
		}else if(PathData.pNonMemberSignUp==true) {
			PathData.nonMemMethod(lbBack, SignUpController.class);
		//Start.fxml 이동
		} else if(PathData.pStartSignUp==true) {
			PathData.logOutMethod(lbBack, SignUpController.class);
		//UserIdFind.fxml 이동
		} else if(PathData.pUserIdFindSignUp==true) {
			PathData.userIdFindMethod(lbBack, SignUpController.class);
		}
	} //--backAction
	
	/**
     * title       startAction
     * description Start.fxml 이동 액션 이벤트
     * @param       event
     */
	public void startAction(MouseEvent event) {
		PathData.startMethod(imStart, SignUpController.class);
	} //--homeAction
	
	/**
     * title       nonMemberAction
     * description NonMember.fxml 이동 액션 이벤트
     * @param       event
     */
	public void nonMemberAction(MouseEvent event) {
		PathData.pStartNonMember = false;            
		PathData.pOrderHistoryLogInNonMember = false;
		PathData.pSignUpNonMember = true;           
		PathData.pLogInNonMember = false;
		PathData.pUserIdFindNonMember = false;
		PathData.nonMemMethod(lbNonMember, SignUpController.class);
	} //--nonMemberAction
	
	/**
     * title       orderHistoryLogInAction
     * description OrderHistoryLogIn.fxml 이동 액션 이벤트
     * @param       event
     */
	public void orderHistoryLogInAction(MouseEvent event) {
		PathData.pStartOrderHistoryLogin = false;    
		PathData.pNonMemberOrderHistoryLogin = false;
		PathData.pSignUpOrderHistoryLogin = true;   
		PathData.pLogInOrderHistoryLogin = false;
		PathData.pUserIdFindOrderHistoryLogin = false;
		PathData.orderHisLogInMethod(lbOrderHistoryLogIn, SignUpController.class);
	} //--orderHistoryLogInAction
	
	/**
     * title       logInAction
     * description LogIn.fxml 이동 액션 이벤트
     * @param       event
     */
	public void logInAction(MouseEvent event) {
		PathData.pStartLogIn = false;            
		PathData.pNonMemberLogIn = false;        
		PathData.pOrderHistoryLogInLogIn = false;
		PathData.pSignUpLogIn = true;           
		PathData.pUserIdFindLogIn = false;
		PathData.logInMethod(lbLogIn, SignUpController.class);
	} //--logInAction
	
	/**
     * title       engChangeAction
     * description 다국어 기능 영어
     * @param       event
     */
	public void engChangeAction(MouseEvent event) {
		Properties props = new Properties();
		BufferedInputStream bis = null;
		try {
			FileInputStream fis = new FileInputStream(profFile);
			bis = new BufferedInputStream(fis);
			props.load(bis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		lbNonMember.setText(props.get("LbNonMemOrder.eng")+"");
		lbOrderHistoryLogIn.setText(props.get("LbOrderHistory.eng")+"");
		lbLogIn.setText(props.get("LbLogin.eng")+"");
		lbKorean.setText(props.get("KoreanLanguage.eng")+"");
		lbEnglish.setText(props.get("EnglishLanguage.eng")+"");
		labelSignUp.setText(props.get("LbSignUp.eng")+"");
		labelID.setText(props.get("LbID.eng")+"");
		labelPW.setText(props.get("LbPassword.eng")+"");
		labelName.setText(props.get("LbName.eng")+"");
		labelAddress.setText(props.get("LbAddress.eng")+"");
		labelHP.setText(props.get("LbHP.eng")+"");
		btnIdCheck.setText(props.get("BtnIdCheck.eng")+"");
		btnSignUpOk.setText(props.get("BtnSignUpOK.eng")+"");
		userId.setPromptText(props.get("ID.eng")+"");
		userPw01.setPromptText(props.get("PW01.eng")+"");
		userPw02.setPromptText(props.get("PW02.eng")+"");
		userName.setPromptText(props.get("Name.eng")+"");
		userArea.setPromptText(props.get("Area.eng")+"");
		userAddress.setPromptText(props.get("Address.eng")+"");
		userCellPhone.setPromptText(props.get("Cellphone.eng")+"");
		btnCellPhoneCheck.setText(props.get("btnCellPhoneCheck.eng")+"");
	} //--engChangeAction
	
	/**
     * title       korChangeAction
     * description 다국어 기능 한국어
     * @param       event
     */
	public void korChangeAction(MouseEvent event) {
		Properties props = new Properties();
		BufferedInputStream bis = null;
		try {
			FileInputStream fis = new FileInputStream(profFile);
			bis = new BufferedInputStream(fis);
			props.load(bis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		lbNonMember.setText(props.get("LbNonMemOrder.kor")+"");
		lbOrderHistoryLogIn.setText(props.get("LbOrderHistory.kor")+"");
		lbLogIn.setText(props.get("LbLogin.kor")+"");
		lbKorean.setText(props.get("KoreanLanguage.kor")+"");
		lbEnglish.setText(props.get("EnglishLanguage.kor")+"");
		labelSignUp.setText(props.get("LbSignUp.kor")+"");
		labelID.setText(props.get("LbID.kor")+"");
		labelPW.setText(props.get("LbPassword.kor")+"");
		labelName.setText(props.get("LbName.kor")+"");
		labelAddress.setText(props.get("LbAddress.kor")+"");
		labelHP.setText(props.get("LbHP.kor")+"");
		btnIdCheck.setText(props.get("BtnIdCheck.kor")+"");
		btnSignUpOk.setText(props.get("BtnSignUpOK.kor")+"");
		userId.setPromptText(props.get("ID.kor")+"");
		userPw01.setPromptText(props.get("PW01.kor")+"");
		userPw02.setPromptText(props.get("PW02.kor")+"");
		userName.setPromptText(props.get("Name.kor")+"");
		userArea.setPromptText(props.get("Area.kor")+"");
		userAddress.setPromptText(props.get("Address.kor")+"");
		userCellPhone.setPromptText(props.get("Cellphone.kor")+"");
		btnCellPhoneCheck.setText(props.get("btnCellPhoneCheck.kor")+"");
	} //--korChangeAction
}