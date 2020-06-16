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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.DTO;
import data.PathData;
import data.WorkDiv;

/**
 * <pre>
 * PackageName : kr.sist.joba.user.dao
 * ClassName : UserPointDAO.java
 * description : 유저 마일리지 포인트 DAO
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
public class UserPointDAO implements WorkDiv {
	
	private List<UserPointVO> UserInfo = new ArrayList<UserPointVO>();
	private final String ADD_FILE = "src/data/UserPoint.csv";

	/**
	 * title       UserPointDAO
	 * description readFile 메서드에 UserPoint.csv 경로를 연결 시키는 메서드
	 */
	public UserPointDAO() {
		UserInfo = readFile(ADD_FILE);
	}
	
	/**
	 * title       readFile
	 * description csv 정보를 List에 담는 메서드
	 * @param 		filePath
	 * @return 		UserData
	 */
	public List<UserPointVO> readFile(String filePath){
		List<UserPointVO> UserData = new ArrayList<UserPointVO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while((line=br.readLine()) != null) {
				//번호, ID, PW, 이름, 주소, 상세주소, 핸드폰 번호, 마일리지
				String[] dataArray = line.split(",");
				UserPointVO vo = new UserPointVO(dataArray[0],dataArray[1]);
				UserData.add(vo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null !=br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return UserData;
	}
	
	/**
	 * title       saveFile
	 * description csv에 입력 정보를 저장하는 메서드
	 * @param 		path
	 * @return 		cnt
	 */
	public int saveFile(String path)  {
		File file = new File(path);
		FileWriter writer = null;
		BufferedWriter bw = null;
		int cnt = 0;
		try {
			writer = new FileWriter(file);
			bw = new BufferedWriter(writer);
	        
			for(int i=0;i<UserInfo.size();i++) {
				UserPointVO vo =UserInfo.get(i);
				//쉼표, 개행 추가
				StringBuilder sb=new StringBuilder();
				//abc,0
				sb.append(vo.getUserId()+","+vo.getUserPoint());
				//마지막 라인에 "\n" 제거
				if((i+1)!=UserInfo.size()) {
					sb.append("\n");
				} //if
				cnt++;
				bw.write(sb.toString());
			} //for
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null !=bw) {
					bw.close();
				}					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	/**
	 * title       readUserPoint
	 * description User의 보유 포인트가 얼마인지 읽어오는 메서드
	 * @param 		vsId
	 * @return 		strNowPoint
	 */
	public String readUserPoint(String vsId) {
		String strNowPoint = null;
		for(int i=0;i<UserInfo.size();i++) {
			UserPointVO vo = UserInfo.get(i);
			if(vo.getUserId().equals(vsId)) {
				strNowPoint = vo.getUserPoint();
			}
		}
		return strNowPoint;
	}
	
	/**
	 * title       getUpdatePointInputData
	 * description 결제시 유저마일리지 업데이트 메서드
	 * @param 		usePoint
	 * @return 		outVo
	 */
	public UserPointVO getUpdatePointInputData(String usePoint) {
		UserPointVO outVo = null;
		String inputData = PathData.pUserId+","+usePoint;
		inputData = inputData.trim();//앞뒤 공간 삭제
		String[] dataArray = inputData.split(",");
		outVo  = new UserPointVO(dataArray[0],
								 dataArray[1]
		);
		return outVo;
	}
	
	/**
	 * title       getUpdatePointInputData02
	 * description 취소시 유저마일리지 업데이트 메서드
	 * @param 		vsId, usePoint
	 * @return 		outVo
	 */
	public UserPointVO getUpdatePointInputData02(String vsId, String usePoint) {
		UserPointVO outVo = null;
		String inputData = "";
		for(int i=0;i<UserInfo.size();i++) {
			UserPointVO vo = UserInfo.get(i);
			if(vo.getUserId().equals(vsId)) {
				int userNowPoint = Integer.parseInt(vo.getUserPoint());
				int intUsePoint = Integer.parseInt(usePoint);
				inputData = vsId+","+(userNowPoint+intUsePoint);
			}
		}
		inputData = inputData.trim();//앞뒤 공간 삭제
		String[] dataArray = inputData.split(",");
		outVo  = new UserPointVO(dataArray[0],
								 dataArray[1]
		);
		return outVo;
	}
	
	/**
	 * title       isMemberExists
	 * description 취소시 유저마일리지 업데이트 메서드
	 * @param 		vo
	 * @return 		flag
	 */
	public boolean isMemberExists(UserPointVO vo){
		boolean flag = false;
		for(int i=0;i<UserInfo.size();i++) {
			UserPointVO vsVO = UserInfo.get(i);
			//이메일을 비교, 데이터가 있으면 loop종료
			if(vsVO.getUserId().equals(vo.getUserId())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * title       do_save
	 * description UserPoint.csv에 정보를 저장하는 메서드
	 * @param 		dto
	 * @return 		saveCnt
	 */
	@Override
	public int do_save(DTO dto) {
		UserPointVO vo = (UserPointVO) dto;
		UserInfo.add(vo);
		int saveCnt = saveFile(ADD_FILE);
		return saveCnt;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return null;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return null;
	}

	/**
	 * title       do_update
	 * description UserPoint.csv에 정보를 수정하는 메서드
	 * @param 		dto
	 * @return 		flag
	 */
	@Override
	public int do_update(DTO dto) {
		int flag = 0;
		UserPointVO modVO = (UserPointVO) dto;
		if(isMemberExists(modVO)) {
			flag = do_delete(modVO);
			if(flag>0) {
				do_save(modVO);
			}
		}
		return flag;
	}

	/**
	 * title       do_update
	 * description UserPoint.csv에 정보를 삭제하는 메서드
	 * @param 		dto
	 * @return 		flag
	 */
	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		UserPointVO delVO = (UserPointVO) dto;
		//반드시 역순으로 찾고 삭제 해야함.
		for(int i=UserInfo.size()-1;i>=0;i--) {
			UserPointVO vsVO = UserInfo.get(i);
			if(delVO.getUserId().equals(vsVO.getUserId())) {
				UserInfo.remove(i);
				flag++;
			}
		}
		//파일에 저장
		if(flag != 0)saveFile(ADD_FILE);
		return flag;
	}
	
}
