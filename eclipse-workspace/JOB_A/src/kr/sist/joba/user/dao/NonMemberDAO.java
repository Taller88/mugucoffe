/**
 *<pre>
 * PackageName : kr.sist.joba.user.dao
 * Description : 유저 다오 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22 
 * @version 1.0
 * Copyright (C) by JOB_A All right reserved.
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

import data.DTO;
import data.WorkDiv;

/**
 * <pre>
 * PackageName : kr.sist.joba.user.dao
 * ClassName : NonMemberDAO.java
 * Description : 비회원 정보를 NonMember.csv에 저장하는 클래스
 *  ======Modification Information======
 *  생성일                  생성자                  수정내용
 *  ----------  --------   -------------------------------
 *  2019-11-26  이지은                  최초 생성
 *
 * </pre>
 * @since : 2019-11-26
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class NonMemberDAO implements WorkDiv {

	private List<NonMemberVO> NonMemberInfo = new ArrayList<NonMemberVO>();
	private final String ADD_FILE = "src/data/NonMember.csv";

	public NonMemberDAO() {
		NonMemberInfo = readFile(ADD_FILE);
	}
	
	/**
     * title       saveFile
     * description 입력받은 비회원 정보를 NonMember.csv에 save하는 메소드  
     * @param       path
     * @return      cnt
     */
	public int saveFile(String path) {
		File file = new File(path);
		FileWriter writer = null;
		BufferedWriter bw = null;
		int cnt = 0;
		try {
			writer = new FileWriter(file);
			bw = new BufferedWriter(writer);
			
			for(int i=0; i<NonMemberInfo.size(); i++) {
				NonMemberVO vo = NonMemberInfo.get(i);
				//쉼표, 개행 추가
				StringBuilder sb = new StringBuilder();
				//이름, PW, 휴대폰 번호
				sb.append(vo.getNonMemberName()+","+vo.getNonMemberPw()+","+vo.getNonMemberCellPhone());
				//마지막 라인에 "\n" 제거
				if((i+1) != NonMemberInfo.size()) {
					sb.append("\n");
				} //if
				cnt++;
				bw.write(sb.toString());
			} //for
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != bw) {
					bw.close();
				}					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	/**
     * title       readFile
     * description NonMember.csv에 저장된 비회원 정보를 read하는 메소드  
     * @param       filePath
     * @return      UserData
     */
	public List<NonMemberVO> readFile(String filePath){
		List<NonMemberVO> UserData = new ArrayList<NonMemberVO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while((line=br.readLine()) != null) {
				//이름, PW, 휴대폰 번호
				String[] dataArray = line.split(",");
				NonMemberVO vo = new NonMemberVO(dataArray[0],dataArray[1],dataArray[2]);
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
     * title       nonMemberCellPhoneCheck
     * description NonMember.csv에 휴대폰 번호가 있는지 확인하는 메서드
     * @param       cellPhone
     * @return      check
     */
	public boolean nonMemberCellPhoneCheck(String cellPhone) {
		boolean check = false;
		for(int i=0;i<NonMemberInfo.size();i++) {
			NonMemberVO vo = NonMemberInfo.get(i);
			if(vo.getNonMemberCellPhone().equals(cellPhone)) {
				return check = true;
			}
		}
		return false;
	}
	
	/**
     * title       getUpdateInputData
     * description 비회원 주문시 NonMember.csv에 동일한 비밀번호 이력이 있으면 업데이트(덮어쓰기)하는 메소드 
     * @param       nonMemberCellPhone, nonMemberPW
     * @return      outVo
     */
	public NonMemberVO getUpdateInputData(String nonMemberCellPhone, String nonMemberPW) {
		NonMemberVO outVo = null;
		String inputData = "";
		for(int i=0; i<NonMemberInfo.size(); i++) {
			NonMemberVO vo = NonMemberInfo.get(i);
			if(vo.getNonMemberCellPhone().equals(nonMemberCellPhone)) {
				inputData = vo.getNonMemberName()+","+
							nonMemberPW+","+
							vo.getNonMemberCellPhone();
			}
		}
		inputData = inputData.trim();//앞뒤 공백 제거
		String[] dataArray = inputData.split(",");
		outVo  = new NonMemberVO(dataArray[0],
								 dataArray[1],
								 dataArray[2]
		);
		return outVo;
	}

	/**
     * title       isMemberExists
     * description NonMember.csv에 동일한 휴대폰 번호가 있는지 확인하는 메소드
     * @param       vo
     * @return      flag
     */
	public boolean isMemberExists(NonMemberVO vo){
		boolean flag = false;
		for(int i=0; i<NonMemberInfo.size(); i++) {
			NonMemberVO vsVO = NonMemberInfo.get(i);
			if(vsVO.getNonMemberCellPhone().equals(vo.getNonMemberCellPhone())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
     * title       do_save
     * description NonMember.csv에 비회원 정보 저장 메소드
     * @param       dto
     * @return      saveCnt
     */
	@Override
	public int do_save(DTO dto) {
		NonMemberVO vo = (NonMemberVO) dto;
		NonMemberInfo.add(vo);
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
     * description NonMember.csv에 비회원 정보 수정 메소드
     * @param       dto
     * @return      flag
     */
	@Override
	public int do_update(DTO dto) {
		int flag = 0;
		NonMemberVO modVO = (NonMemberVO) dto;
		if(isMemberExists(modVO)) {
			flag = do_delete(modVO);
			if(flag>0) {
				do_save(modVO);
			}
		}
		return flag;
	}

	/**
     * title       do_delete
     * description NonMember.csv에 비회원 정보 삭제 메소드
     * @param       dto
     * @return      flag
     */
	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		NonMemberVO delVO = (NonMemberVO) dto;
		//반드시 역순으로 찾고 삭제
		for(int i=NonMemberInfo.size()-1;i>=0;i--) {
			NonMemberVO vsVO = NonMemberInfo.get(i);
			if(delVO.getNonMemberCellPhone().equals(vsVO.getNonMemberCellPhone())) {
				NonMemberInfo.remove(i);
				flag++;
			}
		}
		if(flag != 0) saveFile(ADD_FILE);
		return flag;
	}

}
