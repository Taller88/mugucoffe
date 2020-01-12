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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import data.PathData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kr.sist.joba.order.dao.OrderDAO;
import kr.sist.joba.store.dao.StoreDAO;

/**
 * <pre>
 * PackageName : kr.sist.joba.admin.controller
 * ClassName : AdminChartController.java
 * Description : 관리자 차트 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-12    박종훈         	최초생성
 *  2019-12-20    박종훈         	개발완료
 *  
 * </pre>
 * @since : 2019-12-12
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class AdminChartController implements Initializable {
	@FXML
    private VBox root;
	@FXML
	BarChart dayBarChart, monthBarChart;
	@FXML
	LineChart lineChart;
	@FXML 
	Label lbBack, lbStoreSelect, lbAdminPage, lbLogOut, lbStoreName, lbDayMenuChart, lbMonthMenuChart, lbMonthSalesChart;
	@FXML
	DatePicker datePicker;
	@FXML
	ComboBox<String> barYearComboBox, barMonthComboBox, lineComboBox;
	@FXML
	Pane pnCover, pnDayMenuChart, pnMonthMenuChart, pnMonthSalesChart;
	@FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    Button btnMoreCancle01, btnMoreCancle02, btnMoreCancle03;
    @FXML
    ImageView imStart;

	OrderDAO orderDao = new OrderDAO();
	StoreDAO storeDao = new StoreDAO();
	
	/**
	 * title       initialize
	 * Description 화면 출력될때 사용되는 메서드
	 * @param 		arg0, arg1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		imStart.setOnMousePressed(event -> startAction(event));
		lbBack.setOnMousePressed(event -> backAction(event));
		lbStoreSelect.setOnMousePressed(event -> storeSelectAction(event));
		lbAdminPage.setOnMousePressed(event -> adminPageAction(event));
		lbLogOut.setOnMousePressed(event -> logOutAction(event));
		lbStoreName.setText(PathData.pStoreName);
		lbDayMenuChart.setOnMousePressed(event -> dayMenuFrontAction(event));
		datePicker.setOnAction(event -> dayChart(event));
		lbMonthMenuChart.setOnMousePressed(event -> monthMenuFrontAction(event));
		ObservableList<String> barYearComboList = FXCollections.observableArrayList(orderDao.readYearAll(lbStoreName.getText()));
		barYearComboBox.setItems(barYearComboList);
		barYearComboBox.setOnAction(event -> barYearComboAction(event));
		lbMonthSalesChart.setOnMousePressed(event -> momthSalesFrontAction(event));
		ObservableList<String> lineComboList = FXCollections.observableArrayList(orderDao.readYearAll(lbStoreName.getText()));
		lineComboBox.setItems(lineComboList);
		lineComboBox.setOnAction(event -> lineComboAction(event));
		btnMoreCancle01.setOnAction(event -> coverFrontAction(event));
		btnMoreCancle02.setOnAction(event -> coverFrontAction(event));
		btnMoreCancle03.setOnAction(event -> coverFrontAction(event));
	}
	
	/**
	 * title       dayChart
	 * Description 일간 메뉴별매출 차트 데이트피커 메서드
	 * @param 		event
	 * @return 		void
	 */
	private void dayChart(ActionEvent event) {
		dayBarChart.getData().clear();
		String year = datePicker.getValue().toString().trim().substring(0, 4);
		String month = datePicker.getValue().toString().trim().substring(5, 7);
		String day = datePicker.getValue().toString().trim().substring(8, 10);
		if(orderDao.readOrderCheckAdminYearMonthDay(lbStoreName.getText(), year, month, day)==false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("주의!");
			alert.setHeaderText("주문내역이 없습니다.");
			alert.setContentText("");
	        alert.showAndWait();
		} else {
			//아메리카노
			XYChart.Series americano = new XYChart.Series();
			americano.setName("아메리카노");
			List americanoData = new ArrayList();
			americanoData.add(new XYChart.Data(datePicker.getValue().toString(), orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"아메리카노")));
			ObservableList<Data> americanoListData = FXCollections.observableArrayList(americanoData);
			americano.setData(americanoListData);
			//카페라떼
			XYChart.Series cafeLatte = new XYChart.Series();
			cafeLatte.setName("카페라떼");
			List cafeLatteData = new ArrayList();
			cafeLatteData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"카페라떼")));
			ObservableList<Data> cafeLatteListData = FXCollections.observableArrayList(cafeLatteData);
			cafeLatte.setData(cafeLatteListData);
			//카페모카
			XYChart.Series mochaChoco = new XYChart.Series();
			mochaChoco.setName("카페모카");
			List mochaChocoData = new ArrayList();
			mochaChocoData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"카페모카")));
			ObservableList<Data> mochaChocoListData = FXCollections.observableArrayList(mochaChocoData);
			mochaChoco.setData(mochaChocoListData);
			//헤이즐넛
			XYChart.Series hazelnuts = new XYChart.Series();
			hazelnuts.setName("헤이즐넛");
			List hazelnutsData = new ArrayList();
			hazelnutsData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"헤이즐넛")));
			ObservableList<Data> hazelnutsListData = FXCollections.observableArrayList(hazelnutsData);
			hazelnuts.setData(hazelnutsListData);
			//딸기스무디
			XYChart.Series strawberrySmoothie = new XYChart.Series();
			strawberrySmoothie.setName("딸기스무디");
			List strawberrySmoothieData = new ArrayList();
			strawberrySmoothieData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"딸기스무디")));
			ObservableList<Data> strawberrySmoothieListData = FXCollections.observableArrayList(strawberrySmoothieData);
			strawberrySmoothie.setData(strawberrySmoothieListData);
			//청포도에이드
			XYChart.Series greenGrapesAde = new XYChart.Series();
			greenGrapesAde.setName("청포도에이드");
			List greenGrapesAdeData = new ArrayList();
			greenGrapesAdeData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"청포도에이드")));
			ObservableList<Data> greenGrapesAdeListData = FXCollections.observableArrayList(greenGrapesAdeData);
			greenGrapesAde.setData(greenGrapesAdeListData);
			//레몬에이드
			XYChart.Series lemonAde = new XYChart.Series();
			lemonAde.setName("레몬에이드");
			List lemonAdeData = new ArrayList();
			lemonAdeData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"레몬에이드")));
			ObservableList<Data> lemonAdeListData = FXCollections.observableArrayList(lemonAdeData);
			lemonAde.setData(lemonAdeListData);
			//핫초코
			XYChart.Series hotChoco = new XYChart.Series();
			hotChoco.setName("핫초코");
			List hotChocoData = new ArrayList();
			hotChocoData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"핫초코")));
			ObservableList<Data> hotChocoListData = FXCollections.observableArrayList(hotChocoData);
			hotChoco.setData(hotChocoListData);
			//초코케이크
			XYChart.Series chocoCake = new XYChart.Series();
			chocoCake.setName("초코케이크");
			List chocoCakeData = new ArrayList();
			chocoCakeData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"초코케이크")));
			ObservableList<Data> chocoCakeListData = FXCollections.observableArrayList(chocoCakeData);
			chocoCake.setData(chocoCakeListData);
			//티라미슈
			XYChart.Series tiramisu = new XYChart.Series();
			tiramisu.setName("티라미슈");
			List tiramisuData = new ArrayList();
			tiramisuData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"티라미슈")));
			ObservableList<Data> tiramisuListData = FXCollections.observableArrayList(tiramisuData);
			tiramisu.setData(tiramisuListData);
			//딸기케이크
			XYChart.Series strawberryCake = new XYChart.Series();
			strawberryCake.setName("딸기케이크");
			List strawberryCakeData = new ArrayList();
			strawberryCakeData.add(new XYChart.Data(datePicker.getValue().toString(),orderDao.readMenuChart(year,month,day,lbStoreName.getText(),"딸기케이크")));
			ObservableList<Data> strawberryCakeListData = FXCollections.observableArrayList(strawberryCakeData);
			strawberryCake.setData(strawberryCakeListData);
			dayBarChart.getData().add(americano);
			dayBarChart.getData().add(cafeLatte);
			dayBarChart.getData().add(mochaChoco);
			dayBarChart.getData().add(hazelnuts);
			dayBarChart.getData().add(strawberrySmoothie);
			dayBarChart.getData().add(greenGrapesAde);
			dayBarChart.getData().add(lemonAde);
			dayBarChart.getData().add(hotChoco);
			dayBarChart.getData().add(chocoCake);
			dayBarChart.getData().add(tiramisu);
			dayBarChart.getData().add(strawberryCake);
		}
	}
	
	/**
	 * title       barYearComboAction
	 * Description 월간 메뉴별 차트 콤보박스 (년도) 클릭 메서드
	 * @param 		event
	 */
	public void barYearComboAction(Event event) {
		String item = barMonthComboBox.getSelectionModel().getSelectedItem();
		item = null;
		String year = barYearComboBox.getValue().trim().toString().substring(0,barYearComboBox.getValue().length()-1);
		ObservableList<String> barMonthComboList = FXCollections.observableArrayList(orderDao.readMenuMonthAll(lbStoreName.getText(), year));
		barMonthComboBox.setItems(barMonthComboList);
//		if(item != null) {
			barMonthComboBox.setOnAction(new EventHandler<ActionEvent>() {
				/**
				 * title       handle
				 * Description 월간 메뉴별 차트 콤보박스 (월) 클릭 메서드
				 * @param 		event
				 * @return 		void
				 */
				@Override
				public void handle(ActionEvent event) {
					monthBarChart.getData().clear();
					String month = barMonthComboBox.getValue().toString().trim().substring(0, barMonthComboBox.getValue().toString().length()-1);
					//아메리카노
					XYChart.Series americano = new XYChart.Series();
					americano.setName("아메리카노");
					List americanoData = new ArrayList();
					americanoData.add(new XYChart.Data(barMonthComboBox.getValue().toString(), orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"아메리카노")));
					ObservableList<Data> americanoListData = FXCollections.observableArrayList(americanoData);
					americano.setData(americanoListData);
					//카페라떼
					XYChart.Series cafeLatte = new XYChart.Series();
					cafeLatte.setName("카페라떼");
					List cafeLatteData = new ArrayList();
					cafeLatteData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"카페라떼")));
					ObservableList<Data> cafeLatteListData = FXCollections.observableArrayList(cafeLatteData);
					cafeLatte.setData(cafeLatteListData);
					//카페모카
					XYChart.Series mochaChoco = new XYChart.Series();
					mochaChoco.setName("카페모카");
					List mochaChocoData = new ArrayList();
					mochaChocoData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"카페모카")));
					ObservableList<Data> mochaChocoListData = FXCollections.observableArrayList(mochaChocoData);
					mochaChoco.setData(mochaChocoListData);
					//헤이즐넛
					XYChart.Series hazelnuts = new XYChart.Series();
					hazelnuts.setName("헤이즐넛");
					List hazelnutsData = new ArrayList();
					hazelnutsData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"헤이즐넛")));
					ObservableList<Data> hazelnutsListData = FXCollections.observableArrayList(hazelnutsData);
					hazelnuts.setData(hazelnutsListData);
					//딸기스무디
					XYChart.Series strawberrySmoothie = new XYChart.Series();
					strawberrySmoothie.setName("딸기스무디");
					List strawberrySmoothieData = new ArrayList();
					strawberrySmoothieData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"딸기스무디")));
					ObservableList<Data> strawberrySmoothieListData = FXCollections.observableArrayList(strawberrySmoothieData);
					strawberrySmoothie.setData(strawberrySmoothieListData);
					//청포도에이드
					XYChart.Series greenGrapesAde = new XYChart.Series();
					greenGrapesAde.setName("청포도에이드");
					List greenGrapesAdeData = new ArrayList();
					greenGrapesAdeData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"청포도에이드")));
					ObservableList<Data> greenGrapesAdeListData = FXCollections.observableArrayList(greenGrapesAdeData);
					greenGrapesAde.setData(greenGrapesAdeListData);
					//레몬에이드
					XYChart.Series lemonAde = new XYChart.Series();
					lemonAde.setName("레몬에이드");
					List lemonAdeData = new ArrayList();
					lemonAdeData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"레몬에이드")));
					ObservableList<Data> lemonAdeListData = FXCollections.observableArrayList(lemonAdeData);
					lemonAde.setData(lemonAdeListData);
					//핫초코
					XYChart.Series hotChoco = new XYChart.Series();
					hotChoco.setName("핫초코");
					List hotChocoData = new ArrayList();
					hotChocoData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"핫초코")));
					ObservableList<Data> hotChocoListData = FXCollections.observableArrayList(hotChocoData);
					hotChoco.setData(hotChocoListData);
					//초코케이크
					XYChart.Series chocoCake = new XYChart.Series();
					chocoCake.setName("초코케이크");
					List chocoCakeData = new ArrayList();
					chocoCakeData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"초코케이크")));
					ObservableList<Data> chocoCakeListData = FXCollections.observableArrayList(chocoCakeData);
					chocoCake.setData(chocoCakeListData);
					//티라미슈
					XYChart.Series tiramisu = new XYChart.Series();
					tiramisu.setName("티라미슈");
					List tiramisuData = new ArrayList();
					tiramisuData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"티라미슈")));
					ObservableList<Data> tiramisuListData = FXCollections.observableArrayList(tiramisuData);
					tiramisu.setData(tiramisuListData);
					//딸기케이크
					XYChart.Series strawberryCake = new XYChart.Series();
					strawberryCake.setName("딸기케이크");
					List strawberryCakeData = new ArrayList();
					strawberryCakeData.add(new XYChart.Data(barMonthComboBox.getValue().toString(),orderDao.readMonthMenuChart(year,month,lbStoreName.getText(),"딸기케이크")));
					ObservableList<Data> strawberryCakeListData = FXCollections.observableArrayList(strawberryCakeData);
					strawberryCake.setData(strawberryCakeListData);
					monthBarChart.getData().add(americano);
					monthBarChart.getData().add(cafeLatte);
					monthBarChart.getData().add(mochaChoco);
					monthBarChart.getData().add(hazelnuts);
					monthBarChart.getData().add(strawberrySmoothie);
					monthBarChart.getData().add(greenGrapesAde);
					monthBarChart.getData().add(lemonAde);
					monthBarChart.getData().add(hotChoco);
					monthBarChart.getData().add(chocoCake);
					monthBarChart.getData().add(tiramisu);
					monthBarChart.getData().add(strawberryCake);
				}
			});
//		}
	}
	
	/**
	 * title       lineComboAction
	 * Description 연간 월별매출 차트 콤보박스메서드
	 * @param 		event
	 */
	public void lineComboAction(Event event) {
		lineChart.getData().clear();
		String year = lineComboBox.getValue().toString().substring(0,lineComboBox.getValue().toString().length()-1);
		ObservableList<XYChart.Series<Number, Number>> list = FXCollections.observableArrayList();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("First Data");
        // x = 월, y = 매출금액
        series1.getData().add(new XYChart.Data("1월", orderDao.readMonthlySales(lbStoreName.getText(), year+"01")));
        series1.getData().add(new XYChart.Data("2월", orderDao.readMonthlySales(lbStoreName.getText(), year+"02")));
        series1.getData().add(new XYChart.Data("3월", orderDao.readMonthlySales(lbStoreName.getText(), year+"03")));
        series1.getData().add(new XYChart.Data("4월", orderDao.readMonthlySales(lbStoreName.getText(), year+"04")));
        series1.getData().add(new XYChart.Data("5월", orderDao.readMonthlySales(lbStoreName.getText(), year+"05")));
        series1.getData().add(new XYChart.Data("6월", orderDao.readMonthlySales(lbStoreName.getText(), year+"06")));
        series1.getData().add(new XYChart.Data("7월", orderDao.readMonthlySales(lbStoreName.getText(), year+"07")));
        series1.getData().add(new XYChart.Data("8월", orderDao.readMonthlySales(lbStoreName.getText(), year+"08")));
        series1.getData().add(new XYChart.Data("9월", orderDao.readMonthlySales(lbStoreName.getText(), year+"09")));
        series1.getData().add(new XYChart.Data("10월", orderDao.readMonthlySales(lbStoreName.getText(), year+"10")));
        series1.getData().add(new XYChart.Data("11월", orderDao.readMonthlySales(lbStoreName.getText(), year+"11")));
        series1.getData().add(new XYChart.Data("12월", orderDao.readMonthlySales(lbStoreName.getText(), year+"12")));
        list.addAll(series1);
        lineChart.setData(list);
	}
	
	/**
	 * title       dayMenuFrontAction
	 * Description 일간 메뉴별매출 차트 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void dayMenuFrontAction(MouseEvent event) {
		pnDayMenuChart.toFront();
	}
	
	/**
	 * title       monthMenuFrontAction
	 * Description 월간 메뉴별매출 차트 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void monthMenuFrontAction(MouseEvent event) {
		pnMonthMenuChart.toFront();
	}
	
	/**
	 * title       momthSalesFrontAction
	 * Description 연간 월별매출 차트 액션 이벤트
	 * @param 		event
	 * @return 		void
	 */
	private void momthSalesFrontAction(MouseEvent event) {
		pnMonthSalesChart.toFront();
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
			PathData.startMethod(imStart, AdminChartController.class);
        }
	}
	
	/**
	 * title       backAction
	 * Description 뒤로가기 액션 이벤트
	 * @param 		event
	 */
	public void backAction(MouseEvent event) {
		if(PathData.pStoreSelectAdminChart==true) {
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminStoreSelect.fxml"));
				Scene scene = new Scene(second);
				Stage primaryStage = (Stage) lbBack.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(PathData.pAdminPageChart==true) {
			try {
				Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminPage.fxml"));
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
	 * Description 매장선택 가는 액션 이벤트
	 * @param 		event
	 */
	public void storeSelectAction(MouseEvent event) {
		try {
			Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminStoreSelect.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) lbStoreSelect.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * title       adminPageAction
	 * Description 어드민페이지 가는 액션 이벤트
	 * @param 		event
	 */
	public void adminPageAction(MouseEvent event) {
		PathData.pStoreSelectAdminPage = false;
		PathData.pAdminChartPage = true;
		try {
			Parent second = FXMLLoader.load(getClass().getResource("/kr/sist/joba/admin/view/AdminPage.fxml"));
			Scene scene = new Scene(second);
			Stage primaryStage = (Stage) lbBack.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
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
			PathData.logOutMethod(lbLogOut, AdminChartController.class);
        }
	}
	
	/**
	 * title       coverFrontAction
	 * Description 자세히보기 끄는 메서드
	 * @param 		event
	 */
	public void coverFrontAction(ActionEvent event) {
		pnCover.toFront();
	}
	
}
