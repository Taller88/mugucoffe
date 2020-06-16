/**
 *<pre>
 * PackageName : kr.sist.joba.admin.controller
 * Description : 관리자 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-12-05
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.admin.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import kr.sist.joba.order.dao.OrderDAO;
import kr.sist.joba.store.dao.StoreDAO;

/**
 * <pre>
 * PackageName : kr.sist.joba.admin.controller
 * ClassName : AdminStoreSelectController.java
 * Description : 관리자 매장 선택 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-19    박종훈         	최초생성
 *  2019-12-20    박종훈         	개발완료
 *  
 * </pre>
 * @since : 2019-12-19
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class AdminStoreSelectController implements Initializable {
	@FXML
	Label lbLogOut;
	@FXML
	ImageView imStart;
	@FXML
	ComboBox<String> comboBox;
	@FXML
	ListView<String> listView;
	@FXML
	Button btnAdminOrders, btnAdminChart;
	
	StoreDAO storeDao = new StoreDAO();
	OrderDAO orderDao = new OrderDAO();
	
	/**
	 * title       initialize
	 * Description 화면 출력될때 사용되는 메서드
	 * @param 		arg0, arg1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		imStart.setOnMousePressed(event -> startAction(event));
		btnAdminOrders.setOnAction(event -> adminOrdersAction(event));
		btnAdminChart.setOnAction(event -> adminChartAction(event));
		lbLogOut.setOnMousePressed(event -> logOutAction(event));
		ObservableList<String> comboList = FXCollections.observableArrayList(storeDao.readStoreAreaAll());
		comboBox.setItems(comboList);
		comboBox.setOnAction(event -> comboAction(event));
		listView.setOnMouseClicked(event -> itemSelect(event));
	}
	
	/**
	 * title       comboAction
	 * Description 매장지역 콤보박스 선택 액션 이벤트
	 * @param 		event
	 */
	public void comboAction(ActionEvent event) {
		ObservableList<String> list = FXCollections.observableArrayList(storeDao.readStoreName(comboBox.getValue()));
		listView.setItems(list);
	}
	
	/**
	 * title       itemSelect
	 * Description 콤보박스 클릭시 실행 액션 이벤트
	 * @param 		event
	 */
	public void itemSelect(MouseEvent event) {
		String item = listView.getSelectionModel().getSelectedItem();
		PathData.pStoreName = item;
	}

	/**
	 * title       startAction
	 * Description 스타트로 가는 액션 이벤트
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
	 * title       adminOrdersAction
	 * Description 어드민페이지 가는 액션 이벤트
	 * @param 		event
	 */
	public void adminOrdersAction(ActionEvent event) {
		if(PathData.pStoreName == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("매장을 선택하지 않으셨습니다.");
			alert.setContentText("매장을 선택해주세요.");
	        alert.showAndWait();
		} else {
			if(orderDao.readOrderCheckAdminStoreName(PathData.pStoreName)==false) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("주의!");
				alert.setHeaderText("주문내역이 없습니다.");
				alert.setContentText("");
		        alert.showAndWait();
			} else {
				PathData.pStoreSelectAdminPage = true;
				PathData.pAdminChartPage = false;
				try {
					Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminPage.fxml"));
					Scene scene = new Scene(second);
					Stage primaryStage = (Stage) btnAdminOrders.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * title       adminChartAction
	 * Description 차트 페이지 가는 액션 이벤트
	 * @param 		event
	 */
	public void adminChartAction(ActionEvent event) {
		if(PathData.pStoreName == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("매장을 선택하지 않으셨습니다.");
			alert.setContentText("매장을 선택해주세요.");
	        alert.showAndWait();
		} else {
			if(orderDao.readOrderCheckAdminStoreName(PathData.pStoreName)==false) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("주의!");
				alert.setHeaderText("주문내역이 없습니다.");
				alert.setContentText("");
		        alert.showAndWait();
			} else {
				PathData.pStoreSelectAdminChart = true;
				PathData.pAdminPageChart = false;
				try {
					Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminChart.fxml"));
					Scene scene = new Scene(second);
					Stage primaryStage = (Stage) btnAdminChart.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * title       logOutAction
	 * Description 로그아웃 액션 이벤트
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

}
