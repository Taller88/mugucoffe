/**
 *<pre>
 * PackageName : kr.sist.joba.user.dao
 * description : 유저 DAO 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-26
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.user.dao;

import data.DTO;

/**
 * <pre>
 * PackageName : kr.sist.joba.user.dao
 * ClassName : UserPointVO.java
 * description : UserPoint.csv VO 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-11-26    박종훈         	최초생성
 *  2019-12-20    박종훈         	개발완료
 *  
 * </pre>
 * @since : 2019-11-26
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class UserPointVO extends DTO {

	private String userId;
	
	private String userPoint;

	public UserPointVO() {}
	
	/**
	 * title       UserPointVO
	 * description UserPointVO 생성자로 정보를 입력받는 인자 설정 메서드
	 * @param 		userId, userPoint
	 */
	public UserPointVO(String userId, String userPoint) {
		super();
		this.userId = userId;
		this.userPoint = userPoint;
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
	 * @return the userPoint
	 */
	public String getUserPoint() {
		return userPoint;
	}

	/**
	 * @param userPoint the userPoint to set
	 */
	public void setUserPoint(String userPoint) {
		this.userPoint = userPoint;
	}
	
	/**
	 * title       toString
	 * description UserPointVO toString
	 * @return 		userId, userPoint, super.toString()
	 */
	@Override
	public String toString() {
		return "UserPointVO [userId=" + userId + ", userPoint=" + userPoint + ", toString()=" + super.toString() + "]";
	}
	
}
