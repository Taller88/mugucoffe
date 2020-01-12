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
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.sist.joba.order.dao.OrderDAO;
import kr.sist.joba.user.dao.NonMemberVO;

/**
 * <pre>
 * PackageName : kr.sist.joba.orderhistory.controller
 * ClassName : OrderHistoryLoginController.java
 * Description : 주문내역 로그인화면 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-03    홍승민         	최초생성
 *  2019-12-17    홍승민         	개발완료
 *  
 * </pre>
 * @since : 2019-12-03
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class OrderHistoryLoginController implements Initializable {

	@FXML
	Button btnToOrderHistory1, btnToOrderHistory2, btnUserIdFind;
	@FXML
	TextField logID, nonMemCellPhone;
	@FXML
	PasswordField logPW, nonMemberPw;
	@FXML
	Label lbBack, lbNonMember, lbSignUp, lbMessage, lbNonMessage;
	@FXML
	ImageView imStart;
	
	OrderDAO orderDao = new OrderDAO();
	Alert alert = new Alert(AlertType.WARNING);
	
	/**
	 * title       initialize
	 * Description 화면 출력될때 사용되는 메서드
	 * @param 		location, resources
	 */
	public void initialize(URL location, ResourceBundle resources) {
		btnToOrderHistory1.setOnAction(event -> toOrderHistoryAction(event));
		btnToOrderHistory2.setOnAction(event -> toOrderHistory2Action(event));
		imStart.setOnMousePressed(event -> startAction(event));
		lbBack.setOnMousePressed(event -> backAction(event));
		lbNonMember.setOnMousePressed(event -> nonMemberAction(event));
		lbSignUp.setOnMousePressed(event -> signUpAction(event));
		btnUserIdFind.setOnAction(event -> userIdFindAction(event));
	}

	/**
	 * title       toOrderHistoryAction
	 * Description 회원 주문내역 가기
	 * @param 		event
	 */
	public void toOrderHistoryAction(ActionEvent event) {
		MemberDao dao=new MemberDao(new HROraConnectionMaker());
		String memId = logID.getText().trim();
		String pass = this.logPW.getText().trim();
		
		//ID 입력 Validation
		while(null == memId || "".equals(memId) && (null == pass || "".equals(pass))) {
			alert.setContentText("아이디와 비밀번호를 입력해 주세요");
			alert.showAndWait();
			logID.requestFocus();
			break;
		}
		
		MemberVO  inVO=new MemberVO();
		inVO.setGrpDiv("1");//조구분 
		inVO.setMemId(memId);//ID
		inVO.setPw(pass);//비번
	
		//Login
		MemberVO outVO =dao.do_login(inVO);
		alert.setContentText(outVO.getMessage()+"\n"+outVO);
		alert.showAndWait();
		
		if(outVO.getMessageDiv().equals("11")&&outVO.getAuth().equals("9")) {//관리자일떄
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminStoreSelect.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) btnToOrderHistory1.getScene().getWindow();
				primaryStage.setScene(scene);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(outVO.getMessageDiv().equals("11")&&outVO.getAuth().equals("1")){//관리자가 아닐때
			if(orderDao.readOrderCheckParam(logID.getText())==false) { //주문내역없을때
				lbMessage.setText("");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("주의!");
				alert.setHeaderText("주문내역이 없습니다");
				alert.setContentText("주문후에 조회해주세요.");
		      
		        Optional<ButtonType> result = alert.showAndWait();
		        if(result.get() == ButtonType.OK) {
		        	PathData.lbHomeMethod(btnToOrderHistory1, OrderHistoryController.class);
		        }
			}else {
					try {
						PathData.pUserId = logID.getText();
						PathData.pHomeOrderHistory = false;             
						PathData.pStoreOrderHistory = false;             
						PathData.pOrderHistoryLoginOrderHistory = true;
						PathData.pMyPageOrderHistory = false;           
						Parent second = FXMLLoader
								.load(getClass().getResource("/kr/sist/joba/orderhistory/view/OrderHistory.fxml"));
						Scene scene = new Scene(second);
						Stage primaryStage = (Stage) btnToOrderHistory1.getScene().getWindow();
						primaryStage.setScene(scene);
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}//else
		
		}//관리자가 아닐때  
	}//--toOrderHistoryAction
	
	/**
	 * title       adminAuthority
	 * Description 관리자 권한 체크
	 * @param 		vsID
	 * @return 		check
	 */
	public boolean adminAuthority(String vsID) {
		MemberDao tDao=new MemberDao(new HROraConnectionMaker());
		
		SearchVO searchVO=new SearchVO();
		searchVO.setGrpDiv("1");//조구분
		
		List<MemberVO> dbData  = (List<MemberVO>) tDao.do_retrieve(searchVO);
		
		boolean check = true;
		String strAuth = "";
		for(int i=0;i<dbData.size();i++) {
			MemberVO vo = dbData.get(i);
			if(vo.getAuth().equals("9") && vo.getMemId().equals(logID.getText())) {//권한이 9인애들중에 id가 동일한 애들
					check = false;
				}
			}
		return check;	
	}
		
	/**
	 * title       toOrderHistory2Action
	 * Description 비회원 주문내역 가기
	 * @param 		event
	 */
	public void toOrderHistory2Action(ActionEvent event) {
		ArrayList<NonMemberVO> list1 = nonReadFile("src/data/NonMember.csv");
		for (int i = 0; i < list1.size(); i++) {
			if(nonMemCellPhone.getText().isEmpty() || nonMemberPw.getText().isEmpty()) {
				lbNonMessage.setText("휴대폰번호와 비밀번호를 입력해 주세요");
			} else if(!nonMemCellPhone.getText().equals(list1.get(i).getNonMemberCellPhone()) || !nonMemberPw.getText().equals(list1.get(i).getNonMemberPw())) {
				lbNonMessage.setText("휴대폰번호 또는 비밀번호가 일치하지 않습니다");
			} else {
				if(orderDao.readNonMemOrderCheck(nonMemCellPhone.getText())==false) {
					lbNonMessage.setText("");
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("주의!");
					alert.setHeaderText("주문이력이 없습니다");
					alert.setContentText("주문후에 조회해주세요.");
			        alert.showAndWait();
					break;
				} else {
					PathData.pHomeOrderHistory = false;             
					PathData.pStoreOrderHistory = false;             
					PathData.pOrderHistoryLoginOrderHistory = true;
					PathData.pMyPageOrderHistory = false;
					PathData.pUserCellPhone = nonMemCellPhone.getText();
					try {
						Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/orderhistory/view/OrderHistory.fxml"));
						Scene scene = new Scene(second);
						Stage primaryStage = (Stage) btnToOrderHistory2.getScene().getWindow();
						primaryStage.setScene(scene);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}//--toOrderHistory2
	
	/**
	 * title       nonReadFile
	 * Description 비회원 리드파일
	 * @param 		path
	 * @return 		list
	 */
	static ArrayList<NonMemberVO> nonReadFile(String path) {
		ArrayList<NonMemberVO> list = new ArrayList<NonMemberVO>();
		try {
			Scanner scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				String input = scanner.nextLine();
				// System.out.println(input);
				String[] inArray = input.split(",");
				NonMemberVO dto = new NonMemberVO(inArray[0], inArray[1], inArray[2]);
				list.add(dto);
			} // while
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}//--nonReadFile
	
	/**
	 * title       backAction
	 * Description 뒤로가기 액션 이벤트
	 * @param 		event
	 */
	public void backAction(MouseEvent event) {
		//Start.fxml 가기
		if(PathData.pStartOrderHistoryLogin==true) {
			PathData.logOutMethod(lbBack, OrderHistoryLoginController.class);
		//NonMember.fxml 가기
		} else if(PathData.pNonMemberOrderHistoryLogin==true) {
			PathData.nonMemMethod(lbBack, OrderHistoryLoginController.class);
		//SignUp.fxml 가기
		} else if(PathData.pSignUpOrderHistoryLogin==true) {
			PathData.signUpMethod(lbBack, OrderHistoryLoginController.class);
		//LogIn.fxml 가기
		} else if(PathData.pLogInOrderHistoryLogin==true) {
			PathData.logInMethod(lbBack, OrderHistoryLoginController.class);
		//UserIdFind.fxml 가기
		} else if(PathData.pUserIdFindOrderHistoryLogin==true) {
			PathData.userIdFindMethod(lbBack, OrderHistoryLoginController.class);
		}
	} //--backAction
	
	/**
	 * title       startAction
	 * Description Start로 가는 액션 이벤트
	 * @param 		event
	 */
	public void startAction(MouseEvent event) {
		PathData.startMethod(imStart, OrderHistoryLoginController.class);
	} //--startAction
	
	/**
	 * title       nonMemberAction
	 * Description NonMember로 가는 액션 이벤트
	 * @param 		event
	 */
	public void nonMemberAction(MouseEvent event) {
		PathData.pStartNonMember = false;            
		PathData.pOrderHistoryLogInNonMember = true;
		PathData.pSignUpNonMember = false;           
		PathData.pLogInNonMember = false;      
		PathData.pUserIdFindNonMember = false;
		PathData.nonMemMethod(lbNonMember, OrderHistoryLoginController.class);
	} //--nonMemberAction
	
	/**
	 * title       signUpAction
	 * Description SignUp으로 가는 액션 이벤트
	 * @param 		event
	 */
	public void signUpAction(MouseEvent event) {
		PathData.pStartSignUp = false;            
		PathData.pNonMemberSignUp = false;        
		PathData.pOrderHistoryLogInSignUp = true;
		PathData.pLogInSingUp = false; 
		PathData.pUserIdFindSignUp = false;
		PathData.signUpMethod(lbSignUp, OrderHistoryLoginController.class);
	} //--signUpAction
	
	/**
	 * title       userIdFindAction
	 * Description UserIdFind 가기 액션 이벤트
	 * @param 		event
	 */
	public void userIdFindAction(ActionEvent event) {
		PathData.pLogInUserIdFine = false;            
		PathData.pOrderHistoryLogInUserIdFind = true;
		PathData.userIdFindMethod(btnUserIdFind, OrderHistoryLoginController.class);
	} //--userIdFindAction
	
}