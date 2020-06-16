/**
 *<pre>
 * PackageName : kr.sist.joba.admin.controller
 * description : 관리자 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-12-05
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.admin.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import data.PathData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kr.sist.joba.order.dao.OrderDAO;
import kr.sist.joba.order.dao.OrderVO;
import kr.sist.joba.store.dao.StoreDAO;
import kr.sist.joba.user.dao.UserPointDAO;
import kr.sist.joba.user.dao.UserPointVO;

/**
 * <pre>
 * PackageName : kr.sist.joba.admin.controller
 * ClassName : AdminPageController.java
 * description : 관리자 주문내역 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-05    박종훈         	최초생성
 *  2019-12-20    박종훈         	개발완료
 *  
 * </pre>
 * @since : 2019-12-05
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class AdminPageController implements Initializable {

	@FXML
	Button btnMoreCancle, btnOrderReady, btnOrderStart, btnOrderComplete, btnOrderCancle;
	@FXML
	Pane pnMore, pnCover;
	@FXML
	ComboBox<String> comboBox;
	@FXML
	ListView<String> listView;
	@FXML
	Label lbBack, lbStoreSelect, lbChart, lbLogOut,
		  lbStoreName, lbUserName, lbOrderNum, lbPreparing,
		  lbOrderName01, lbOrderName02, lbOrderName03, lbOrderName04,
		  lbOrderCount01, lbOrderCount02, lbOrderCount03, lbOrderCount04,
		  lbOrderPrice01, lbOrderPrice02, lbOrderPrice03, lbOrderPrice04,
		  lbTotalPrice, lbUsePoint, lbOrderDivision, lbUserCellPhone, lbUserArea, lbUserAddress, lbOrderRequests;
	@FXML
	ImageView imStart;
	
	DecimalFormat formatter = new DecimalFormat("###,###");
	OrderDAO orderDao = new OrderDAO();
	StoreDAO storeDao = new StoreDAO();
	
	/**
	 * title       initialize
	 * description 화면 출력될때 사용되는 메서드
	 * @param 		location, resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imStart.setOnMousePressed(event -> startAction(event));
		lbBack.setOnMousePressed(event -> backAction(event));
		lbStoreSelect.setOnMousePressed(event -> storeSelectAction(event));
		lbChart.setOnMousePressed(event -> chartAction(event));
		lbLogOut.setOnMousePressed(event -> logOutAction(event));
		lbStoreName.setText(PathData.pStoreName);
		ObservableList<String> comboList = FXCollections.observableArrayList("상품준비","배송출발","배송완료","취소요청","취소완료");
		comboBox.setItems(comboList);
		comboBox.setOnAction(event -> comboAction(event));
		listView.setOnMouseClicked(event -> readMoreAction(event));
		btnMoreCancle.setOnAction(event -> readMoreCancleAction(event));
		btnOrderReady.setOnAction(event -> orderReadyAction(event));
		btnOrderStart.setOnAction(event -> orderStartAction(event));
		btnOrderComplete.setOnAction(event -> orderCompleteAction(event));
		btnOrderCancle.setOnAction(event -> orderCancleAction(event));
	}
	
	/**
	 * title       startAction
	 * description 스타트로 가는 액션 이벤트
	 * @param 		event
	 */
	public void startAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("홈페이지로 돌아갑니다.");
		alert.setContentText("정말로 로그아웃 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
			PathData.startMethod(imStart, AdminPageController.class);
        }
	}
	
	/**
	 * title       backAction
	 * description 뒤로가기 액션 이벤트
	 * @param 		event
	 */
	public void backAction(MouseEvent event) {
		if(PathData.pStoreSelectAdminPage==true) {
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminStoreSelect.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) lbBack.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(PathData.pAdminChartPage==true) {
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminChart.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) lbBack.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * title       storeSelectAction
	 * description 매장선택 페이지 가는 액션 이벤트
	 * @param 		event
	 */
	public void storeSelectAction(MouseEvent event) {
		try {
			Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminStoreSelect.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) lbChart.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * title       chartAction
	 * description 차트 페이지 가는 액션 이벤트
	 * @param 		event
	 */
	public void chartAction(MouseEvent event) {
		PathData.pStoreSelectAdminChart = false;
		PathData.pAdminPageChart = true;
		try {
			Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminChart.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) lbChart.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * title       logOutAction
	 * description 로그아웃 액션 이벤트
	 * @param 		event
	 */
	public void logOutAction(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주의!");
		alert.setHeaderText("홈페이지로 돌아갑니다.");
		alert.setContentText("정말로 로그아웃 하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
			PathData.logOutMethod(lbLogOut, AdminPageController.class);
        }
	}
	
	/**
	 * title       readMoreCancleAction
	 * description 자세히보기 끄는 메서드
	 * @param 		event
	 */
	public void readMoreCancleAction(ActionEvent event) {
		pnCover.toFront();
	}

	/**
	 * title       comboAction
	 * description 배송상태 콤보박스 선택 액션 이벤트
	 * @param 		event
	 */
	public void comboAction(ActionEvent event) {
		if(orderDao.readOrderCheckAdminPrepairing(lbStoreName.getText(), comboBox.getValue().toString()) == false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("주문이력이 없습니다.");
			alert.setContentText("");
	        alert.showAndWait();
		} else {
			ObservableList<String> list = FXCollections.observableArrayList(orderDao.readAdminOrderList(lbStoreName.getText(), comboBox.getValue().toString()));
			listView.setItems(list);
		}
	}
	
	/**
	 * title       readMoreAction
	 * description 주문 자세히 보기 메서드
	 * @param 		event
	 */
	public void readMoreAction(MouseEvent event) {
		String item = listView.getSelectionModel().getSelectedItem();
		if(!listView.getItems().isEmpty()) {
			if(item != null) {
				lbOrderNum.setText(item.substring(5, 17));
				lbUserName.setText(orderDao.readUseId(lbOrderNum.getText()));
				lbPreparing.setText(orderDao.readOrderPreparing(lbOrderNum.getText()));
				Label[] orderName = {lbOrderName01, lbOrderName02, lbOrderName03, lbOrderName04};
				Label[] orderCount = {lbOrderCount01, lbOrderCount02, lbOrderCount03, lbOrderCount04};
				Label[] orderPrice = {lbOrderPrice01, lbOrderPrice02, lbOrderPrice03, lbOrderPrice04};
				String[] arrOrderName = orderDao.readOrderName(lbOrderNum.getText());
				String[] arrOrderCount = orderDao.readOrderCount(lbOrderNum.getText());
				String[] arrOrderPrice = orderDao.readOrderPrice(lbOrderNum.getText());
				for(int i=0;i<orderName.length;i++) {
					orderName[i].setText("");
					orderCount[i].setText("");
					orderPrice[i].setText("");
				}
				for(int i=0;i<arrOrderName.length;i++) {
					orderName[i].setText(arrOrderName[i]);
					orderCount[i].setText(arrOrderCount[i]);
					orderPrice[i].setText(formatter.format(Integer.parseInt(arrOrderPrice[i]))+"원");
				}
				// 비회원
				if(orderDao.readUsePointUserOrNonMemCheck(lbOrderNum.getText())==true) {
					lbTotalPrice.setText(formatter.format(orderDao.readNonMemberTotalPrice(lbOrderNum.getText())));
					lbUsePoint.setText("비회원");
				// 회원
				} else {
					lbTotalPrice.setText(formatter.format(orderDao.readTotalPrice(lbOrderNum.getText())));
					lbUsePoint.setText(formatter.format(Integer.parseInt(orderDao.readUsePoint(lbOrderNum.getText()))));
				}
				lbOrderDivision.setText(orderDao.readOrderDivision(lbOrderNum.getText()));
				if(orderDao.readUserCellPhone(lbOrderNum.getText()).length()==10) {
					lbUserCellPhone.setText(orderDao.readUserCellPhone(lbOrderNum.getText()).substring(0,3)+"-"+
							orderDao.readUserCellPhone(lbOrderNum.getText()).substring(3,6)+"-"+
							orderDao.readUserCellPhone(lbOrderNum.getText()).substring(6,10));
				} else {
					lbUserCellPhone.setText(orderDao.readUserCellPhone(lbOrderNum.getText()).substring(0,3)+"-"+
							orderDao.readUserCellPhone(lbOrderNum.getText()).substring(3,7)+"-"+
							orderDao.readUserCellPhone(lbOrderNum.getText()).substring(7,11));
				}
				lbUserArea.setText(orderDao.readUserArea(lbOrderNum.getText()));
				lbUserAddress.setText(orderDao.readUserAddress(lbOrderNum.getText()));
				lbOrderRequests.setText(orderDao.readOrderRequests(lbOrderNum.getText()));
				pnMore.toFront();
			}
		}
	}
	
	/**
	 * title       orderReadyAction
	 * description 상품준비 액션 이벤트
	 * @param 		event
	 */
	public void orderReadyAction(ActionEvent event) {
		// 이미 상품준비인 경우
		if(lbPreparing.getText().equals("상품준비")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("이미 상품준비 상태입니다.");
	        alert.showAndWait();
	    // 상품준비 요청
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("주의!");
			alert.setHeaderText("상품준비 상태로 변경하시겠습니까?");
		    Optional<ButtonType> result = alert.showAndWait();
		    if(result.get() == ButtonType.OK) {
		    	String[] arrOrderName = orderDao.readOrderName(lbOrderNum.getText());
				for(int i=0;i<arrOrderName.length;i++) {
					OrderVO modVO = orderDao.getUpdatePreparingInputData(lbOrderNum.getText(), btnOrderReady.getText(), arrOrderName[i]);
					orderDao.do_update02(modVO);
				}
				Alert alert02 = new Alert(AlertType.WARNING);
				alert02.setTitle("주의!");
				alert02.setHeaderText("상품준비 상태로 변경되었습니다.");
		        alert02.showAndWait();
		        if(result.get() == ButtonType.OK) {
		        	if(orderDao.readOrderCheckAdminPrepairing(lbStoreName.getText(), comboBox.getValue().toString()) == false) {
		    			Alert alert03 = new Alert(AlertType.WARNING);
		    			alert03.setTitle("주의!");
		    			alert03.setHeaderText("주문이력이 없습니다.");
		    			alert03.setContentText("");
		    			alert03.showAndWait();
		    			pnCover.toFront();
		    			listView.setItems(null);
		    		} else {
		    			pnCover.toFront();
			        	ObservableList<String> list = FXCollections.observableArrayList(orderDao.readAdminOrderList(lbStoreName.getText(), comboBox.getValue().toString()));
		    			listView.setItems(list);
		    		}
		        }
		    }
		}
	}
	
	/**
	 * title       orderStartAction
	 * description 배송시작 액션 이벤트
	 * @param 		event
	 */
	public void orderStartAction(ActionEvent event) {
		// 이미 배송시작 된 경우
		if(lbPreparing.getText().equals("배송시작")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("이미 배송이 시작되었습니다.");
	        alert.showAndWait();
	    // 배송시작 요청
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("주의!");
			alert.setHeaderText("배송시작 상태로 변경하시겠습니까?.");
		    Optional<ButtonType> result = alert.showAndWait();
		    if(result.get() == ButtonType.OK) {
		    	String[] arrOrderName = orderDao.readOrderName(lbOrderNum.getText());
				for(int i=0;i<arrOrderName.length;i++) {
					OrderVO modVO = orderDao.getUpdatePreparingInputData(lbOrderNum.getText(), btnOrderStart.getText(), arrOrderName[i]);
					orderDao.do_update02(modVO);
				}
				Alert alert02 = new Alert(AlertType.WARNING);
				alert02.setTitle("주의!");
				alert02.setHeaderText("배송이 시작 되었습니다.");
		        alert02.showAndWait();
		        if(result.get() == ButtonType.OK) {
		        	if(orderDao.readOrderCheckAdminPrepairing(lbStoreName.getText(), comboBox.getValue().toString()) == false) {
		    			Alert alert03 = new Alert(AlertType.WARNING);
		    			alert03.setTitle("주의!");
		    			alert03.setHeaderText("주문이력이 없습니다.");
		    			alert03.setContentText("");
		    			alert03.showAndWait();
		    			pnCover.toFront();
		    			listView.setItems(null);
		    		} else {
		    			pnCover.toFront();
			        	ObservableList<String> list = FXCollections.observableArrayList(orderDao.readAdminOrderList(lbStoreName.getText(), comboBox.getValue().toString()));
		    			listView.setItems(list);
		    		}
		        }
		    }
		}
	}
	
	/**
	 * title       orderCompleteAction
	 * description 배송완료 액션 이벤트
	 * @param 		event
	 */
	public void orderCompleteAction(ActionEvent event) {
		// 이미 배송 완료 된 경우
		if(lbPreparing.getText().equals("배송완료")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("이미 배송이 완료되었습니다.");
	        alert.showAndWait();
	    // 배송완료 요청
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("주의!");
			alert.setHeaderText("배송완료 상태로 변경하시겠습니까?");
		    Optional<ButtonType> result = alert.showAndWait();
		    if(result.get() == ButtonType.OK) {
		    	String[] arrOrderName = orderDao.readOrderName(lbOrderNum.getText());
				for(int i=0;i<arrOrderName.length;i++) {
					OrderVO modVO = orderDao.getUpdatePreparingInputData(lbOrderNum.getText(), btnOrderComplete.getText(), arrOrderName[i]);
					orderDao.do_update02(modVO);
				}
				Alert alert02 = new Alert(AlertType.WARNING);
				alert02.setTitle("주의!");
				alert02.setHeaderText("배송이 완료 되었습니다.");
		        alert02.showAndWait();
		        if(result.get() == ButtonType.OK) {
		        	if(orderDao.readOrderCheckAdminPrepairing(lbStoreName.getText(), comboBox.getValue().toString()) == false) {
		    			Alert alert03 = new Alert(AlertType.WARNING);
		    			alert03.setTitle("주의!");
		    			alert03.setHeaderText("주문이력이 없습니다.");
		    			alert03.setContentText("");
		    			alert03.showAndWait();
		    			pnCover.toFront();
		    			listView.setItems(null);
		    		} else {
		    			pnCover.toFront();
			        	ObservableList<String> list = FXCollections.observableArrayList(orderDao.readAdminOrderList(lbStoreName.getText(), comboBox.getValue().toString()));
		    			listView.setItems(list);
		    		}
		        }
		    }
		}
	}
	
	/**
	 * title       orderCancleAction
	 * description 취소완료 액션 이벤트
	 * @param 		event
	 */
	public void orderCancleAction(ActionEvent event) {
		String[] arrOrderName = orderDao.readOrderName(lbOrderNum.getText());
		if(lbUsePoint.equals("비회원")) {
			// 이미 취소완료 된 경우
			if(lbPreparing.getText().equals("배송완료")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("주의!");
				alert.setHeaderText("이미 취소가 완료되었습니다.");
		        alert.showAndWait();
		    // 취소완료 요청
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("주의!");
				alert.setHeaderText("취소완료 상태로 변경하시겠습니까?.");
			    Optional<ButtonType> result = alert.showAndWait();
			    if(result.get() == ButtonType.OK) {
			    	for(int i=0;i<arrOrderName.length;i++) {
						OrderVO modVO = orderDao.getUpdatePreparingInputData(lbOrderNum.getText(), btnOrderCancle.getText(), arrOrderName[i]);
						orderDao.do_update02(modVO);
					}
			    	Alert alert02 = new Alert(AlertType.WARNING);
					alert02.setTitle("주의!");
					alert02.setHeaderText("취소가 완료 되었습니다.");
			        alert02.showAndWait();
			        if(result.get() == ButtonType.OK) {
			        	if(orderDao.readOrderCheckAdminPrepairing(lbStoreName.getText(), comboBox.getValue().toString()) == false) {
			    			Alert alert03 = new Alert(AlertType.WARNING);
			    			alert03.setTitle("주의!");
			    			alert03.setHeaderText("주문이력이 없습니다.");
			    			alert03.setContentText("");
			    			alert03.showAndWait();
			    			pnCover.toFront();
			    			listView.setItems(null);
			    		} else {
			    			pnCover.toFront();
				        	ObservableList<String> list = FXCollections.observableArrayList(orderDao.readAdminOrderList(lbStoreName.getText(), comboBox.getValue().toString()));
			    			listView.setItems(list);
			    		}
			        }
			    }
			}
		} else {
			// 이미 취소완료 된 경우
			if(lbPreparing.getText().equals("취소완료")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("주의!");
				alert.setHeaderText("이미 취소가 완료되었습니다.");
		        alert.showAndWait();
		    // 취소완료 요청
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("주의!");
				alert.setHeaderText("취소완료 상태로 변경하시겠습니까?.");
			    Optional<ButtonType> result = alert.showAndWait();
			    if(result.get() == ButtonType.OK) {
			    	for(int i=0;i<arrOrderName.length;i++) {
						OrderVO modVO = orderDao.getUpdatePreparingInputData(lbOrderNum.getText(), btnOrderCancle.getText(), arrOrderName[i]);
						orderDao.do_update02(modVO);
					}
					UserPointDAO userPointDao = new UserPointDAO();
					UserPointVO modVO02 = userPointDao.getUpdatePointInputData02(orderDao.readUseId(lbOrderNum.getText()), orderDao.readUsePoint(lbOrderNum.getText()));
					userPointDao.do_update(modVO02);
					Alert alert02 = new Alert(AlertType.WARNING);
					alert02.setTitle("주의!");
					alert02.setHeaderText("취소가 완료 되었습니다.");
			        alert02.showAndWait();
			        if(result.get() == ButtonType.OK) {
			        	if(orderDao.readOrderCheckAdminPrepairing(lbStoreName.getText(), comboBox.getValue().toString()) == false) {
			    			Alert alert03 = new Alert(AlertType.WARNING);
			    			alert03.setTitle("주의!");
			    			alert03.setHeaderText("주문이력이 없습니다.");
			    			alert03.setContentText("");
			    			alert03.showAndWait();
			    			pnCover.toFront();
			    			listView.setItems(null);
			    		} else {
			    			pnCover.toFront();
				        	ObservableList<String> list = FXCollections.observableArrayList(orderDao.readAdminOrderList(lbStoreName.getText(), comboBox.getValue().toString()));
			    			listView.setItems(list);
			    		}
			        }
			    }
			}
		}
	}
}
