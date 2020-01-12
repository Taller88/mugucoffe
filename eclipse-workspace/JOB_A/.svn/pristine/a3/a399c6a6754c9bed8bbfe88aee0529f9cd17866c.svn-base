package data;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kr.sist.joba.order.dao.OrderDAO;

public class PathData {
	// 유저 및 스토어 임시 정보
	public static String pUserId = null;
	public static String pUserCellPhone = null;
	public static String pUserName = null;
	public static String pUserArea = null;
	public static String pUserAddress = null;
	public static String pStoreArea = null;
	public static String pStoreName = null;
	public static String pStoreTell = null;
	public static String pStoreAddress = null;
	
	// OrderList에서 사용
	public static String pProductName[] = {"주문상품","주문상품","주문상품","주문상품"};
	public static int pProductCount[] = new int[4];
	public static int pProductPrice[] = new int[4];
	public static String pOrderImg[]= {"img/imgInit.png","img/imgInit.png","img/imgInit.png","img/imgInit.png"};
	public static boolean porderChecked[]= {true,true,true,true};
	
	// 논멤버 가는 페이지
	public static boolean pStartNonMember = false;
	public static boolean pOrderHistoryLogInNonMember = false;
	public static boolean pSignUpNonMember = false;
	public static boolean pLogInNonMember = false;
	public static boolean pUserIdFindNonMember = false;
	
	// 스토어 가는 페이지
	public static boolean pHomeStore = false;
	public static boolean pNonMemberStore = false;
	public static boolean pOrderHistoryStore = false;
	public static boolean pMyPageStore = false;
		
	// 주문내역 로그인 가는 페이지
	public static boolean pStartOrderHistoryLogin = false;
	public static boolean pNonMemberOrderHistoryLogin = false;
	public static boolean pSignUpOrderHistoryLogin = false;
	public static boolean pLogInOrderHistoryLogin = false;
	public static boolean pUserIdFindOrderHistoryLogin = false;
	
	// 주문내역 가는 페이지
	public static boolean pHomeOrderHistory = false;
	public static boolean pStoreOrderHistory = false;
	public static boolean pOrderHistoryLoginOrderHistory = false;
	public static boolean pMyPageOrderHistory = false;
	
	// 사인업 가는 페이지
	public static boolean pStartSignUp = false;
	public static boolean pNonMemberSignUp = false;
	public static boolean pOrderHistoryLogInSignUp = false;
	public static boolean pLogInSingUp = false;
	public static boolean pUserIdFindSignUp = false;
	
	// 마이페이지 가는 페이지
	public static boolean pHomeMyPage = false;
	public static boolean pStoreMyPage = false;
	public static boolean pOrderHistoryMyPage = false;
	
	// 로그인 가는 페이지
	public static boolean pStartLogIn = false;
	public static boolean pNonMemberLogIn = false;
	public static boolean pOrderHistoryLogInLogIn = false;
	public static boolean pSignUpLogIn = false;
	public static boolean pUserIdFindLogIn = false;
	
	// 아이디 찾기 가는 페이지
	public static boolean pLogInUserIdFine = false;
	public static boolean pOrderHistoryLogInUserIdFind = false;
		
	// 관리자용 페이지 이동 스태틱 변수
	public static boolean pStoreSelectAdminPage = false;
	public static boolean pStoreSelectAdminChart = false;
	public static boolean pAdminPageChart = false;
	public static boolean pAdminChartPage = false;
	
	/**홈*/
	public static void lbHomeMethod(Label homeLabel, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/main/view/Home.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) homeLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**홈*/
	public static void lbHomeMethod(Button homeLabel,Class className) {
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/main/view/Home.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) homeLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**논멤버 로그인*/
	public static void nonMemMethod(Label nonMemLabel, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/user/view/NonMember.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) nonMemLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**스토어*/
	public static void storeMethod(Label storeLabel, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/store/view/StoreInfo.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) storeLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**주문내역 로그인*/
	public static void orderHisLogInMethod(Label orderHisLogInLabel, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/orderhistory/view/OrderHistoryLogin.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) orderHisLogInLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**주문내역*/
	public static void orderHistoryMethod(Label orderHistoryLabel, Class className) {
		OrderDAO orderDao = new OrderDAO();
		if(orderDao.readOrderCheck()==false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("주문이력이 없습니다");
			alert.setContentText("주문후에 조회해주세요.");
	        alert.showAndWait();
		} else {
			try {
				Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/orderhistory/view/OrderHistory.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) orderHistoryLabel.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**사인업*/
	public static void signUpMethod(Label sigUpLabel, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/user/view/SignUp.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) sigUpLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**마이페이지*/
	public static void myPageMethod(Label myPageLabel, Class className) {
		OrderDAO orderDao = new OrderDAO();
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/main/view/MyPage.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) myPageLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**로그인*/
	public static void logInMethod(Label logInLabel, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/user/view/Login.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) logInLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**아이디찾기*/
	public static void userIdFindMethod(Label logInLabel, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getResource("/kr/sist/joba/user/view/UserIdFind.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) logInLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**아이디찾기(버튼)*/
	public static void userIdFindMethod(Button logInBtn, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getResource("/kr/sist/joba/user/view/UserIdFind.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) logInBtn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**로그아웃(Label logOutLabel, Class className)*/
	public static void logOutMethod(Label logOutLabel, Class className) {
		pUserId = null;
		pUserCellPhone = null;
		pUserName = null;
		pUserArea = null;
		pUserAddress = null;
		pStoreArea = null;
		pStoreName = null;
		pStoreTell = null;
		pStoreAddress = null;
		
		for(int i=0; i<pProductName.length; i++) {
			pProductName[i]="주문상품";
			pProductCount[i]=0;
			pProductPrice[i]=0; 
			porderChecked[i]=true;
			pOrderImg[i]= "img/imgInit.png";
		}
		
		//주문내역 로그인 가는 페이지
		pStartOrderHistoryLogin = false;       
		pNonMemberOrderHistoryLogin = false;   
		pSignUpOrderHistoryLogin = false;      
		pLogInOrderHistoryLogin = false;       
		
		//논멤버 가는 페이지
		pStartNonMember = false;               
		pOrderHistoryLogInNonMember = false;   
		pSignUpNonMember = false;              
		pLogInNonMember = false;               
		
		//사인업 가는 페이지
		pStartSignUp = false;                  
		pOrderHistoryLogInSignUp = false;      
		pNonMemberSignUp = false;              
		pLogInSingUp = false;                  
		
		//로그인 가는 페이지
		pStartLogIn = false;                   
		pOrderHistoryLogInLogIn = false;       
		pNonMemberLogIn = false;               
		pSignUpLogIn = false;
		pUserIdFindLogIn = false;
		
		//마이페이지 가는 페이지
		pHomeMyPage = false;                   
		pOrderHistoryMyPage = false;           
		pStoreMyPage = false;                  
		
		//스토어 가는 페이지
		pNonMemberStore = false;               
		pHomeStore = false;                    
		pMyPageStore = false;                  
		pOrderHistoryStore = false;            
		
		//주문내역 가는 페이지
		pOrderHistoryLoginOrderHistory = false;
		pHomeOrderHistory = false;             
		pMyPageOrderHistory = false;           
		pStoreOrderHistory = false;  
		
		// 아이디 찾기 가는 페이지
		pLogInUserIdFine = false;
		pOrderHistoryLogInUserIdFind = false;
		
		//관리자용 페이지
		pStoreSelectAdminPage = false;
		pStoreSelectAdminChart = false;
		pAdminPageChart = false;
		pAdminChartPage = false;
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/main/view/Start.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) logOutLabel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**회원탈퇴용 로그아웃(Button userDeleteButton, Class className)*/
	public static void userDeleteMethod(Button button, Class className) {
		pUserId = null;
		pUserCellPhone = null;
		pUserName = null;
		pUserArea = null;
		pUserAddress = null;
		pStoreArea = null;
		pStoreName = null;
		pStoreTell = null;
		pStoreAddress = null;
		
		for(int i=0; i<pProductName.length; i++) {
			pProductName[i]="주문상품";
			pProductCount[i]=0;
			pProductPrice[i]=0; 
			porderChecked[i]=true;
			pOrderImg[i]= "img/imgInit.png";
		}
		
		//주문내역 로그인 가는 페이지
		pStartOrderHistoryLogin = false;       
		pNonMemberOrderHistoryLogin = false;   
		pSignUpOrderHistoryLogin = false;      
		pLogInOrderHistoryLogin = false;       
		
		//논멤버 가는 페이지
		pStartNonMember = false;               
		pOrderHistoryLogInNonMember = false;   
		pSignUpNonMember = false;              
		pLogInNonMember = false;               
		
		//사인업 가는 페이지
		pStartSignUp = false;                  
		pOrderHistoryLogInSignUp = false;      
		pNonMemberSignUp = false;              
		pLogInSingUp = false;                  
		
		//로그인 가는 페이지
		pStartLogIn = false;                   
		pOrderHistoryLogInLogIn = false;       
		pNonMemberLogIn = false;               
		pSignUpLogIn = false;
		pUserIdFindLogIn = false;
		
		//마이페이지 가는 페이지
		pHomeMyPage = false;                   
		pOrderHistoryMyPage = false;           
		pStoreMyPage = false;                  
		
		//스토어 가는 페이지
		pNonMemberStore = false;               
		pHomeStore = false;                    
		pMyPageStore = false;                  
		pOrderHistoryStore = false;            
		
		//주문내역 가는 페이지
		pOrderHistoryLoginOrderHistory = false;
		pHomeOrderHistory = false;             
		pMyPageOrderHistory = false;           
		pStoreOrderHistory = false;    
		
		//관리자용 페이지
		pStoreSelectAdminPage = false;
		pStoreSelectAdminChart = false;
		pAdminPageChart = false;
		pAdminChartPage = false;
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/main/view/Start.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) button.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**로고이미지 선택시 비회원 Start*/
	public static void startMethod(ImageView homeImage, Class className) {
		pUserId = null;
		pUserCellPhone = null;
		pUserName = null;
		pUserArea = null;
		pUserAddress = null;
		pStoreArea = null;
		pStoreName = null;
		pStoreTell = null;
		pStoreAddress = null;
		
		for(int i=0; i<pProductName.length; i++) {
			pProductName[i]="주문상품";
			pProductCount[i]=0;
			pProductPrice[i]=0; 
			porderChecked[i]=true;
			pOrderImg[i]= "img/imgInit.png";
		}
		
		//주문내역 로그인 가는 페이지
		pStartOrderHistoryLogin = false;       
		pNonMemberOrderHistoryLogin = false;   
		pSignUpOrderHistoryLogin = false;      
		pLogInOrderHistoryLogin = false;       
		
		//논멤버 가는 페이지
		pStartNonMember = false;               
		pOrderHistoryLogInNonMember = false;   
		pSignUpNonMember = false;              
		pLogInNonMember = false;               
		
		//사인업 가는 페이지
		pStartSignUp = false;                  
		pOrderHistoryLogInSignUp = false;      
		pNonMemberSignUp = false;              
		pLogInSingUp = false;                  
		
		//로그인 가는 페이지
		pStartLogIn = false;                   
		pOrderHistoryLogInLogIn = false;       
		pNonMemberLogIn = false;               
		pSignUpLogIn = false;
		pUserIdFindLogIn = false;
		
		//마이페이지 가는 페이지
		pHomeMyPage = false;                   
		pOrderHistoryMyPage = false;           
		pStoreMyPage = false;                  
		
		//스토어 가는 페이지
		pNonMemberStore = false;               
		pHomeStore = false;                    
		pMyPageStore = false;                  
		pOrderHistoryStore = false;            
		
		//주문내역 가는 페이지
		pOrderHistoryLoginOrderHistory = false;
		pHomeOrderHistory = false;             
		pMyPageOrderHistory = false;           
		pStoreOrderHistory = false;
		
		// 아이디 찾기 가는 페이지
		pLogInUserIdFine = false;
		pOrderHistoryLogInUserIdFind = false;
		
		//관리자용 페이지
		pStoreSelectAdminPage = false;
		pStoreSelectAdminChart = false;
		pAdminPageChart = false;
		pAdminChartPage = false;
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/main/view/Start.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) homeImage.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**로고이미지 선택시 회원 Home*/
	public static void homeMethod(ImageView homeImage, Class className) {
		try {
			Parent second = FXMLLoader.load(className.getClass().getResource("/kr/sist/joba/main/view/Home.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) homeImage.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
