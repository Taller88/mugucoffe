/**
 *<pre>
 * PackageName : kr.sist.joba.order.controller
 * Description : 오더 컨트롤러 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22 
 * @version 1.0
 * Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.order.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import data.PathData;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import kr.sist.joba.main.controller.CompleteController;
import kr.sist.joba.order.dao.PaymentDataDAO;
import kr.sist.joba.order.dao.ProductDAO;

/**
 * <pre>
 * PackageName : kr.sist.joba.order.controller
 * ClassName : OrderController.java
 * Description : 주문 상품을 선택하는 페이지(OrderList.fxml)의 컨트롤러
 *  ======Modification Information======
 *  생성일                  생성자                  수정내용
 *  ----------  --------   -------------------------------
 *  2019-11-18  정진우                  최초 생성
 *  2019-12-18  정진우                  개발 완료
 * </pre>
 * @since : 2019-11-26
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class OrderController implements Initializable {
	@FXML
	private Button btnToPayment;
	@FXML //버튼 라벨
	private Label lbBackStore, lbHisLog, lbSignMy, lbLogInOut;
	@FXML //버튼 이미지뷰
	ImageView imStartHome;
	@FXML //음료 이미지, 아메리카노 Plus/Minus 빼놨음
	public ImageView americanoPlus, cafeLattePlus, cafeMochaPlus, hazelnutPlus, strawberrySmoothiePlus, 
			grapeAdePlus, hotChocoPlus, lemonAdePlus, chocoCakePlus, tiramisuPlus, strawberryCakePlus, 
			americanoMinus, cafelatteMinus, cafeMochaMinus, hazelnutMinus, strawberrySmoothieMinus,
			grapeAdeMinus, hotChocoMinus, lemonAdeMinus, chocoCakeMinus, tiramisuMinus, strawberryCakeMinus;
	@FXML
	public Label orderProduct1, productCount1, productPrice1, orderProduct2, productCount2, productPrice2,
			orderProduct3, productCount3, productPrice3, orderProduct4, productCount4, productPrice4;
	@FXML //음료 VBox
	private VBox americano, cafeLatte, cafeMocha, hazelnut, strawberrySmoothie, grapeAde, hotChoco, lemonAde,
			chocoCake, tiramisu, strawberryCake;
	@FXML //음료명 라벨
	private Label americanoId, cafeLatteId, cafeMochaId, hazelnutId, strawberrySmoothieId, grapeAdeId, hotChocoId,
			lemonAdeId, chocoCakeId, tiramisuId, strawberryCakeId, selectStoreName;
	@FXML //주문 상품 이미지
	private ImageView orderCount1Plus, orderCount2Plus, orderCount3Plus, orderCount4Plus, orderCount1Minus,
			orderCount2Minus, orderCount3Minus, orderCount4Minus;
	@FXML
	private ImageView orderImg1, orderImg2, orderImg3, orderImg4;
	private Popup popup;
	
	boolean orderChecked1 = true;
	boolean orderChecked2 = true;
	boolean orderChecked3 = true;
	boolean orderChecked4 = true;

	static List<ProductDAO> productDAO = new ArrayList<ProductDAO>();
	static List<PaymentDataDAO> paymentData = new ArrayList<PaymentDataDAO>();
	static ProductDAO dao = new ProductDAO();

	DecimalFormat formatter = new DecimalFormat("###,###");
	
	/**
     * title       initialize
     * Description initialize
     * @param       arg0, arg1
     */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectStoreName.setText(PathData.pStoreName);
//		countUp(Label orderCountUp, Label orderCount, int index);
		Label orderProduct[] = { orderProduct1, orderProduct2, orderProduct3, orderProduct4 };
		Label productCount[] = { productCount1, productCount2, productCount3, productCount4 };
		Label productPrice[] = { productPrice1, productPrice2, productPrice3, productPrice4 };
		ImageView orderCountMinus[] = { orderCount1Minus, orderCount2Minus, orderCount3Minus, orderCount4Minus };
		ImageView orderCountPlus[] = { orderCount1Plus, orderCount2Plus, orderCount3Plus, orderCount4Plus };
		ImageView orderImg[] = { orderImg1, orderImg2, orderImg3, orderImg4 };
		boolean orderChecked[] = { true, true, true, true };
		VBox[] productVbox = { americano, cafeLatte, cafeMocha, hazelnut, strawberrySmoothie, grapeAde, 
				hotChoco, lemonAde, chocoCake, tiramisu, strawberryCake };
		Label[] productLabel = { americanoId, cafeLatteId, cafeMochaId, hazelnutId, strawberrySmoothieId, grapeAdeId,
				hotChocoId, lemonAdeId, chocoCakeId, tiramisuId, strawberryCakeId };
		// 비회원 메뉴
		if (PathData.pUserId == null) {
			lbHisLog.setText("주문내역");
			lbSignMy.setText("회원가입");
			lbLogInOut.setText("로그인");
			imStartHome.setOnMousePressed(event -> startAction(event));
			lbHisLog.setOnMousePressed(event -> orderHistoryLogInAction(event));
			lbSignMy.setOnMousePressed(event -> signUpAction(event));
			lbLogInOut.setOnMousePressed(event -> logInAction(event));
		// 회원 메뉴
		} else {
			lbHisLog.setText("주문내역");
			lbSignMy.setText("마이페이지");
			lbLogInOut.setText("로그아웃");
			imStartHome.setOnMousePressed(event -> homeAction(event));
			lbHisLog.setOnMousePressed(event -> orderHistoryAction(event));
			lbSignMy.setOnMousePressed(event -> myPageAction(event));
			lbLogInOut.setOnMousePressed(event -> logOutAction(event));
		}

		for (int i = 0; i < orderProduct.length; i++) {
			countUp(orderCountPlus[i], productCount[i], productPrice[i], i);
			countDown(orderCountMinus[i], orderProduct, productCount, productPrice, orderImg, orderChecked, i);
			//Payment에서 주문내역을 저장하기 위해서 PathData값을 처음에 불러옴
			orderInitialize(orderProduct[i], productCount[i], productPrice[i], orderImg[i], i);
		}

		productDAO = readFile("src\\data\\product.csv");
		imStartHome.setOnMousePressed(event -> homeAction(event)); //홈 가기
		lbBackStore.setOnMousePressed(e -> handleBackStore(e)); //뒤로 가기
		btnToPayment.setOnAction(e -> handleBtnPayment(e)); //Payment로 가기

		//마지막 OrderList가 false값이 아닐 때만 Plus버튼 작동
		for (int i = 0; i < 11; i++) {
			countUpImage(productVbox[i], productLabel[i]);
		}
	} //--initialize

	/**
     * title       orderInitialize
     * Description 
     * @param       arg0, arg1
     * @return      
     */
	private void orderInitialize(Label orderProductName, Label productCount, Label productPrice, ImageView orderImg, int index) {
		orderProductName.setText(PathData.pProductName[index]);
		productCount.setText(PathData.pProductCount[index] + "");
		productPrice.setText(PathData.pProductPrice[index] + "");
		Image image = new Image(PathData.pOrderImg[index]);
		orderImg.setImage(image);
	} //--orderInitialize

	/**
     * title       countUp
     * Description 주문내역에 있는 +버튼을 클릭했을 때 실행하는 메소드
     * @param       orderCountUp, orderCount, orderPrice, index
     * @return      void
     */
	@SuppressWarnings("unchecked")
	private void countUp(ImageView orderCountUp, Label orderCount, Label orderPrice, int index) {
		orderCountUp.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event event) {
				if(Integer.parseInt(orderCount.getText())==0) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("주의!");
					alert.setHeaderText("상품을 선택하지 않았습니다.");
					alert.setContentText("상품을 다시 선택해 주세요.");
			        alert.showAndWait();
				} else {
				int price = PathData.pProductPrice[index] / PathData.pProductCount[index];
				PathData.pProductCount[index] = PathData.pProductCount[index] + 1;
				orderCount.setText(PathData.pProductCount[index] + "");
				orderPrice.setText(formatter.format(price * PathData.pProductCount[index] )+ "");
				PathData.pProductPrice[index] = price * PathData.pProductCount[index];
				}
			}
		});
	} //--countUp

	/**
     * title       countDown
     * Description 주문내역에 있는 -버튼을 클릭했을 때 실행하는 메소드
     * @param       orderCountDown, orderName, orderName, orderPrice, orderImg, orderChecked, index
     * @return      void
     */
	@SuppressWarnings("unchecked")
	private void countDown(ImageView orderCountDown, Label[] orderName, Label[] orderCount, Label[] orderPrice,
	ImageView[] orderImg, boolean[] orderChecked, int index) {

		orderCountDown.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event event) {
				if (PathData.pProductCount[index] > 0) {
					int price = PathData.pProductPrice[index] / PathData.pProductCount[index];
					// 제품 하나에 대한 가격을 저장(복잡하게 부르는 것보다 PathData에 있는 가격 나누기 개수를 통해 산출)
					PathData.pProductCount[index] = PathData.pProductCount[index] - 1;
					orderCount[index].setText(PathData.pProductCount[index] + "");
					PathData.pProductPrice[index] = price * PathData.pProductCount[index];
					orderPrice[index].setText(formatter.format(price * PathData.pProductCount[index]) + "");
				// PathData를 삭제한 값을 제외하고 나머지 값들로 순서를 맞춘 뒤에 재배열
				} else if (PathData.pProductCount[index] == 0) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("요거슨 Alter!");
					alert.setHeaderText("수량이 0개입니다!");
					alert.setContentText("해당 상품을 삭제하시겠습니까?");

					Optional<ButtonType> result = alert.showAndWait();
					//삭제할 경우
					if (result.get() == ButtonType.OK) {
						//PathData 초기화
						for (int i = index; i < 4; i++) {
							if (i != 3) {
								//뒤에 값이 있고 현재 Count값이 0일 때
								if (PathData.pProductCount[i] == 0 && PathData.pProductCount[i + 1] != 0) {
									PathData.pProductName[i] = PathData.pProductName[i + 1];
									PathData.pProductCount[i] = PathData.pProductCount[i + 1];
									PathData.pProductPrice[i] = PathData.pProductPrice[i + 1];
									PathData.pOrderImg[i] = PathData.pOrderImg[i + 1];
									PathData.porderChecked[i] = false;
									vboxInitialize(i + 1);
								//뒤에 값이 없을 때
								} else {
									vboxInitialize(i);
								}
							} else {//i가 3
								vboxInitialize(i);
							}
						} //--for
						//초기화한 PathData의 값으로 넣어주는 과정
						for (int i = 0; i < PathData.pProductCount.length; i++) {
							orderName[i].setText(PathData.pProductName[i]);
							orderCount[i].setText(PathData.pProductCount[i] + "");
							orderPrice[i].setText(formatter.format(PathData.pProductPrice[i]) + "");
							Image image = new Image(PathData.pOrderImg[i]);
							orderImg[i].setImage(image);
						} //--for
					//삭제 안 할 경우
					} else {
						PathData.pProductCount[index] = 1;
						orderCount[index].setText(PathData.pProductCount[index] + "");
					}
				}
			}
		});
	} //--countDown

	/**
     * title       vboxInitialize
     * Description 
     * @param       i
     * @return      void
     */
	private void vboxInitialize(int i) {
		PathData.pProductName[i] = "주문상품";
		PathData.pProductCount[i] = 0;
		PathData.pProductPrice[i] = 0;
		PathData.porderChecked[i] = true;
		PathData.pOrderImg[i] = "img/imgInit.png";
	} //--vboxInitialize

	/**
     * title       countUpImage
     * Description 
     * @param       plusImageView, productLabel
     * @return      void
     */
	private void countUpImage(VBox plusImageView, Label productLabel) {
		plusImageView.setOnMousePressed(new EventHandler() {
			/**
		     * title       vboxInitialize
		     * Description 
		     * @param       i
		     * @return      void
		     */
			@Override
			public void handle(Event event) {
				orderListCheck(productLabel);
			}
		});
	} //--countUpImage

	/**
     * title       handleBtnPayment
     * Description 
     * @param       event
     * @return      void
     */
	private void handleBtnPayment(ActionEvent event) {
		if(Integer.parseInt(productCount1.getText()) ==0) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("상품을 선택하지 않았습니다.");
			alert.setContentText("상품을 한 개 이상 선택해 주세요.");
	        alert.showAndWait();
		} else {
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/user/view/Payment.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) btnToPayment.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} //--handleBtnPayment

	/**
     * title       handleBackStore
     * Description 
     * @param       event
     * @return      void
     */
	private void handleBackStore(MouseEvent event) {
		try {
			Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/store/view/StoreInfo.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) lbBackStore.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //--handleBackStore

	/**
     * title       orderListSetting
     * Description PathData에 order 항목을 하나씩 저장하는 메소드
     * @param       name, count, price, img, productName, index
     */
	public void orderListSetting(Label name, Label count, Label price, ImageView img, String productName, int index) {
		PathData.pProductName[index] = productName;
		name.setText(PathData.pProductName[index]);

		for (int i = 0; i < productDAO.size(); i++) {
			if (productDAO.get(i).getProductName().equals(productName)) {
				PathData.pProductCount[index] = PathData.pProductCount[index] + 1;
				count.setText((PathData.pProductCount[index]) + "");

				price.setText(formatter.format(productDAO.get(i).getProductPrice() * PathData.pProductCount[index] )+ "");
				PathData.pProductPrice[index] = productDAO.get(i).getProductPrice() * PathData.pProductCount[index];
				Image image = new Image(productDAO.get(i).getImgName());
				PathData.pOrderImg[index] = productDAO.get(i).getImgName();
				img.setImage(image);
			}
		}
	} //--orderListSetting

	/**
     * title       readFile
     * Description 
     * @param       filePath
     * @return      productData
     */
	public List<ProductDAO> readFile(String filePath) {
		List<ProductDAO> productData = new ArrayList<ProductDAO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				// 홍길동,jamesol@paran.com,16321203,010-1234-5678
				String[] dataArray = line.split(",");
				ProductDAO vo = new ProductDAO(dataArray[0], Integer.parseInt(dataArray[1]), dataArray[2]);
				productData.add(vo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return productData;
	} //--readFile

	/**
     * title       readPaymentFile
     * Description 
     * @param       filePath
     * @return      productData
     */
	public List<PaymentDataDAO> readPaymentFile(String filePath) {
		List<PaymentDataDAO> productData = new ArrayList<PaymentDataDAO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				// 홍길동,jamesol@paran.com,16321203,010-1234-5678
				String[] dataArray = line.split(",");
				PaymentDataDAO vo = new PaymentDataDAO(dataArray[0], Integer.parseInt(dataArray[1]));
				productData.add(vo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return productData;
	} //--readPaymentFile

	/**
     * title       orderListCheck
     * Description 
     * @param       vboxProductName
     * @return      void
     */
	private void orderListCheck(Label vboxProductName) {
		if (orderProduct1.getText().equals(vboxProductName.getText()) || PathData.porderChecked[0]) {
			orderListSetting(orderProduct1, productCount1, productPrice1, orderImg1, vboxProductName.getText(), 0);
			PathData.porderChecked[0] = false;
		} else if (orderProduct2.getText().equals(vboxProductName.getText()) || PathData.porderChecked[1]) {
			orderListSetting(orderProduct2, productCount2, productPrice2, orderImg2, vboxProductName.getText(), 1);
			PathData.porderChecked[1] = false;
		} else if (orderProduct3.getText().equals(vboxProductName.getText()) || PathData.porderChecked[2]) {
			orderListSetting(orderProduct3, productCount3, productPrice3, orderImg3, vboxProductName.getText(), 2);
			PathData.porderChecked[2] = false;
		} else if (orderProduct4.getText().equals(vboxProductName.getText()) || PathData.porderChecked[3]) {
			orderListSetting(orderProduct4, productCount4, productPrice4, orderImg4, vboxProductName.getText(), 3);
			PathData.porderChecked[3] = false;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("안내");
			alert.setHeaderText("온라인으로는 4개까지 상품 주문이 가능합니다.");
			alert.setContentText("추가 주문은 해당 주문을 완료한 후 재주문해 주세요.");
	        Optional<ButtonType> result = alert.showAndWait();
//	        if(result.get() == ButtonType.OK) {
//	        	PathData.startMethod(imStartHome, CompleteController.class);
//	        }
		}
	} //--orderListCheck

	/**
     * title       saveFile
     * Description 
     * @param       path, productName, productCount
     * @return      void
     */
	public int saveFile(String path, String productName, int productCount) {
		int cnt = 0;
		FileWriter writer = null;
		BufferedWriter bw = null;
		File file = new File(path);
		try {
			writer = new FileWriter(file);
			bw = new BufferedWriter(writer);
			for (int i = 0; i < 4; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(productName + "," + productCount);
				// 마지막 라인에 "\n" 제거
				if ((i + 1) != 4) {
					sb.append("\n");
				}
				cnt++;
				bw.write(sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bw) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	} //--saveFile
	
	/**
     * title       startAction
     * Description Start.fxml 이동 액션 이벤트
     * @param       event
     * @return      void
     */
	private void startAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다.");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.startMethod(imStartHome, CompleteController.class);
        }
	} //--startAction
	
	/**
     * title       orderHistoryLogInAction
     * Description OrderHistoryLogIn.fxml 이동 액션 이벤트
     * @param       event
     * @return      void
     */
	private void orderHistoryLogInAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다.");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pStartOrderHistoryLogin = true;    
    		PathData.pNonMemberOrderHistoryLogin = false;
    		PathData.pSignUpOrderHistoryLogin = false;   
    		PathData.pLogInOrderHistoryLogin = false;    
    		for(int i=0; i<PathData.pProductName.length; i++) {
    			PathData.pProductName[i]="주문상품";
    			PathData.pProductCount[i]=0;
    			PathData.pProductPrice[i]=0; 
    			PathData.porderChecked[i]=true;
    			PathData.pOrderImg[i]= "img/imgInit.png";
			}
    		PathData.orderHisLogInMethod(lbHisLog, CompleteController.class);
        }
	} //--orderHistoryLogInAction
	
	/**
     * title       signUpAction
     * Description SignUp.fxml 이동 액션 이벤트
     * @param       event
     * @return      void
     */
	private void signUpAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다.");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pStartSignUp = true;            
    		PathData.pNonMemberSignUp = false;        
    		PathData.pOrderHistoryLogInSignUp = false;
    		PathData.pLogInSingUp = false;            
    		for(int i=0; i<PathData.pProductName.length; i++) {
    			PathData.pProductName[i]="주문상품";
    			PathData.pProductCount[i]=0;
    			PathData.pProductPrice[i]=0; 
    			PathData.porderChecked[i]=true;
    			PathData.pOrderImg[i]= "img/imgInit.png";
			}
    		PathData.signUpMethod(lbSignMy, CompleteController.class);
        }
	} //--signMyAction
	
	/**
     * title       logInAction
     * Description LogIn.fxml 이동 액션 이벤트
     * @param       event
     * @return      void
     */
	private void logInAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다.");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pStartLogIn = true;            
    		PathData.pNonMemberLogIn = false;        
    		PathData.pOrderHistoryLogInLogIn = false;
    		PathData.pSignUpLogIn = false;           
    		for(int i=0; i<PathData.pProductName.length; i++) {
    			PathData.pProductName[i]="주문상품";
    			PathData.pProductCount[i]=0;
    			PathData.pProductPrice[i]=0; 
    			PathData.porderChecked[i]=true;
    			PathData.pOrderImg[i]= "img/imgInit.png";
			}
    		PathData.logInMethod(lbLogInOut, CompleteController.class);
        }
	} //--logInAction
	
	/**
     * title       homeAction
     * Description Home.fxml 이동 액션 이벤트
     * @param       event
     * @return      void
     */
	private void homeAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다.");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.homeMethod(imStartHome, CompleteController.class);
        }
	} //--homeAction
	
	/**
     * title       orderHistoryAction
     * Description OrderHistory.fxml 이동 액션 이벤트
     * @param       event
     * @return      void
     */
	private void orderHistoryAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다.");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pHomeOrderHistory = false;             
    		PathData.pStoreOrderHistory = true;            
    		PathData.pOrderHistoryLoginOrderHistory = false;
    		PathData.pMyPageOrderHistory = false;      
    		for(int i=0; i<PathData.pProductName.length; i++) {
    			PathData.pProductName[i]="주문상품";
    			PathData.pProductCount[i]=0;
    			PathData.pProductPrice[i]=0; 
    			PathData.porderChecked[i]=true;
    			PathData.pOrderImg[i]= "img/imgInit.png";
			}
    		PathData.orderHistoryMethod(lbHisLog, CompleteController.class);
        }
	} //--orderHistoryAction
	
	/**
     * title       myPageAction
     * Description MyPage.fxml 이동 액션 이벤트
     * @param       event
     * @return      void
     */
	private void myPageAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("주문 상태가 초기화 됩니다.");
		alert.setContentText("그래도 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.pHomeMyPage = false;        
    		PathData.pStoreMyPage = true;       
    		PathData.pOrderHistoryMyPage = false;
    		for(int i=0; i<PathData.pProductName.length; i++) {
    			PathData.pProductName[i]="주문상품";
    			PathData.pProductCount[i]=0;
    			PathData.pProductPrice[i]=0; 
    			PathData.porderChecked[i]=true;
    			PathData.pOrderImg[i]= "img/imgInit.png";
			}
    		PathData.myPageMethod(lbSignMy, CompleteController.class);
        }
	} //--myPageAction
	
	/**
     * title       logOutAction
     * Description 로그아웃 액션 이벤트
     * @param       event
     * @return      void
     */
	private void logOutAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("홈페이지로 돌아갑니다.");
		alert.setContentText("로그아웃 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
        	PathData.logOutMethod(lbLogInOut, CompleteController.class);
        }
	} //--logOutAction
	
}