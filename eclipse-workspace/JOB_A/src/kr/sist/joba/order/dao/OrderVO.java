/**
 *<pre>
 * PackageName : kr.sist.joba.order.dao
 * description : 주문관련 DAO 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.order.dao;

import data.DTO;

/**
 * <pre>
 * PackageName : kr.sist.joba.order.dao
 * ClassName : OrderVO.java
 * description : Order.csv VO 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-11-22    박종훈         	최초생성
 *  2019-12-20    박종훈         	개발완료
 *  
 * </pre>
 * @since : 2019-11-22
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class OrderVO extends DTO {

	private String orderNum;
	
	private String userCellPhone;
	
	private String userArea;
	
	private String userAddress;

	private String orderCount;
	
	private String orderPrice;
	
	private String productName;
	
	private String userId;
	
	private String storeName;
	
	private String orderDivision;
	
	private String usePoint;
	
	private String OrderRequests;
	
	private String orderPreparing;

	public OrderVO() {}

	/**
	 * title       OrderVO
	 * description OrderVO 생성자로 정보를 입력받는 인자 설정 메서드
	 * @param 		orderNum, userCellPhone, userArea, userAddress, orderCount, orderPrice, productName, userId, storeName, orderDivision, usePoint, orderRequests, orderPreparing
	 */
	public OrderVO(String orderNum, String userCellPhone, String userArea, String userAddress, String orderCount,
			String orderPrice, String productName, String userId, String storeName, String orderDivision,
			String usePoint, String orderRequests, String orderPreparing) {
		super();
		this.orderNum = orderNum;
		this.userCellPhone = userCellPhone;
		this.userArea = userArea;
		this.userAddress = userAddress;
		this.orderCount = orderCount;
		this.orderPrice = orderPrice;
		this.productName = productName;
		this.userId = userId;
		this.storeName = storeName;
		this.orderDivision = orderDivision;
		this.usePoint = usePoint;
		OrderRequests = orderRequests;
		this.orderPreparing = orderPreparing;
	}

	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the userCellPhone
	 */
	public String getUserCellPhone() {
		return userCellPhone;
	}

	/**
	 * @param userCellPhone the userCellPhone to set
	 */
	public void setUserCellPhone(String userCellPhone) {
		this.userCellPhone = userCellPhone;
	}

	/**
	 * @return the userArea
	 */
	public String getUserArea() {
		return userArea;
	}

	/**
	 * @param userArea the userArea to set
	 */
	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	/**
	 * @return the userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	/**
	 * @return the orderCount
	 */
	public String getOrderCount() {
		return orderCount;
	}

	/**
	 * @param orderCount the orderCount to set
	 */
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}

	/**
	 * @return the orderPrice
	 */
	public String getOrderPrice() {
		return orderPrice;
	}

	/**
	 * @param orderPrice the orderPrice to set
	 */
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the orderDivision
	 */
	public String getOrderDivision() {
		return orderDivision;
	}

	/**
	 * @param orderDivision the orderDivision to set
	 */
	public void setOrderDivision(String orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * @return the usePoint
	 */
	public String getUsePoint() {
		return usePoint;
	}

	/**
	 * @param usePoint the usePoint to set
	 */
	public void setUsePoint(String usePoint) {
		this.usePoint = usePoint;
	}

	/**
	 * @return the orderRequests
	 */
	public String getOrderRequests() {
		return OrderRequests;
	}

	/**
	 * @param orderRequests the orderRequests to set
	 */
	public void setOrderRequests(String orderRequests) {
		OrderRequests = orderRequests;
	}

	/**
	 * @return the orderPreparing
	 */
	public String getOrderPreparing() {
		return orderPreparing;
	}

	/**
	 * @param orderPreparing the orderPreparing to set
	 */
	public void setOrderPreparing(String orderPreparing) {
		this.orderPreparing = orderPreparing;
	}
	
	/**
	 * title       toString
	 * description OrderVO toString
	 * @return 	orderNum, userCellPhone, userArea, userAddress, orderCount, orderPrice, productName, userId, storeName, orderDivision, usePoint, OrderRequests, orderPreparing, super.toString()
	 */
	@Override
	public String toString() {
		return "OrderVO [orderNum=" + orderNum + ", userCellPhone=" + userCellPhone + ", userArea=" + userArea
				+ ", userAddress=" + userAddress + ", orderCount=" + orderCount + ", orderPrice=" + orderPrice
				+ ", productName=" + productName + ", userId=" + userId + ", storeName=" + storeName
				+ ", orderDivision=" + orderDivision + ", usePoint=" + usePoint + ", OrderRequests=" + OrderRequests
				+ ", orderPreparing=" + orderPreparing + ", toString()=" + super.toString() + "]";
	}

}
