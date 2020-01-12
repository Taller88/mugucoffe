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

public class UserDAO implements WorkDiv {
	
	private List<UserVO> UserInfo = new ArrayList<UserVO>();
	private final String ADD_FILE = "src/data/User.csv";
	
	public UserDAO() {
		UserInfo = readFile(ADD_FILE);
	}
		
	public List<UserVO> readFile(String filePath){
		List<UserVO> UserData = new ArrayList<UserVO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while((line=br.readLine()) != null) {
				//번호, ID, PW, 이름, 주소, 상세주소, 핸드폰 번호, 마일리지
				String[] dataArray = line.split(",");
				UserVO vo = new UserVO(dataArray[0],dataArray[1],dataArray[2],dataArray[3],dataArray[4],dataArray[5],dataArray[6],dataArray[7],dataArray[8],dataArray[9],dataArray[10],dataArray[11]);
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
	
	/**get(i)와 getVO정보들 한줄에 적는 배열*/
	static ArrayList<UserVO> readFile02(String path) {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		try {
			Scanner scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				String input = scanner.nextLine();
	            //System.out.println(input);
	            String[] inArray = input.split(",");
	            UserVO dto = new UserVO(inArray[0],
					            		  inArray[1],
					            		  inArray[2],
					            		  inArray[3],
					            		  inArray[4],
					            		  inArray[5],
					            		  inArray[6],
					            		  inArray[7],
					            		  inArray[8],
					            		  inArray[9],
					            		  inArray[10],
					            		  inArray[11]
	            );
	            list.add(dto);
	        } //while
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	    }
	      return list;
	} //--readFile02

	public int saveFile(String path)  {
		File file = new File(path);
		FileWriter writer = null;
		BufferedWriter bw = null;
		
		int cnt = 0;
		try {
			writer = new FileWriter(file);
			bw = new BufferedWriter(writer);
			for(int i=0;i<UserInfo.size();i++) {
				UserVO vo =UserInfo.get(i);
				//쉼표, 개행 추가
				StringBuilder sb=new StringBuilder();
				//ID, PW, 이름, 권한, 조, Email, 휴대폰 번호, 주소, 상세주소, 마일리지, 등록일, 등록자
				sb.append(vo.getUserId()+","+vo.getUserPw()+","+vo.getUserName()+","+vo.getUserAuthority()+","+vo.getTeamNum()+","+vo.getUserEmail()+","+vo.getUserCellPhone()+","+vo.getUserArea()+","+vo.getUserAddress()+","+vo.getUserPoint()+","+vo.getUserRegistration()+","+vo.getUserRegistId());
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
	
	@Override
	public int do_save(DTO dto) {
		UserVO vo = (UserVO) dto;
		UserInfo.add(vo);
		int saveCnt = saveFile(ADD_FILE);
		return saveCnt;
	}

	@Override
	public int do_update(DTO dto) {
		int flag = 0;
		UserVO modVO = (UserVO) dto;
		if(isMemberExists(modVO)) {
			flag = do_delete(modVO);
			if(flag>0) {
				do_save(modVO);
			}
		}
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		UserVO delVo = (UserVO) dto;
		//반드시 역순으로 찾고 삭제
		for(int i=UserInfo.size()-1;i>=0;i--) {
			UserVO vsVo = UserInfo.get(i);
			if(delVo.getUserId().equals(vsVo.getUserId())) {
				UserInfo.remove(i);
				flag++;
			}
		}
		//파일에 저장
		if(flag != 0)saveFile(ADD_FILE);
		return flag;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		UserVO outVO = null;
		return outVO;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		List<UserVO> list = new ArrayList<UserVO>();
		return list;
	}
	
	/**ID 중복 체크 메소드*/
	public boolean idCheck(String inputId) {
		boolean check = false;
		for(int i=0; i<UserInfo.size(); i++) {
			UserVO idVO = UserInfo.get(i);
			if(idVO.getUserId().equals(inputId)) {
				check = true;
				break;
			} else {
				check = false;
			}
		}
		return check;
	}
	
	/**사용자 이름*/
	public String readUserName(String ab) {
		String bb = null;
		/**비회원*/
		if(ab == null) {
			bb = PathData.pUserName;
			return bb;
		}
		/**회원*/
		else {
			for(int i=0;i<UserInfo.size();i++) {
				UserVO vo = UserInfo.get(i);
				if(vo.getUserId().equals(ab)) {
					bb = vo.getUserName();
				}
			}
			return bb;
		}
	}
	
	/**사용자 주소*/
	public String readUserArea(String ab) {
		String bb = null;
		/**비회원*/
		if(ab == null) {
			bb = PathData.pUserArea;
			return bb;
		}
		/**회원*/
		else {
			for(int i=0;i<UserInfo.size();i++) {
				UserVO vo = UserInfo.get(i);
				if(vo.getUserId().equals(ab)) {
					bb = vo.getUserArea();
				}
			}
			return bb;
		}
	}
	
	/**사용자 상세주소*/
	public String readUserAddress(String ab) {
		String bb = null;
		/**비회원*/
		if(ab == null) {
			bb = PathData.pUserAddress;
			return bb;
		}
		/**회원*/
		else {
			for(int i=0;i<UserInfo.size();i++) {
				UserVO vo = UserInfo.get(i);
				if(vo.getUserId().equals(ab)) {
					bb = vo.getUserAddress();
				}
			}
			return bb;
		}
	}
	
	/**사용자 휴대폰*/
	public String readUserCellPhone(String ab) {
		String bb = null;
		/**비회원*/
		if(ab == null) {
			bb = PathData.pUserCellPhone;
			return bb;
		}
		/**회원*/
		else {
			for(int i=0;i<UserInfo.size();i++) {
				UserVO vo = UserInfo.get(i);
				if(vo.getUserId().equals(ab)) {
					bb = vo.getUserCellPhone();
				}
			}
			return bb;
		}
	}
	
	/**관리자 권한 체크*/
	public boolean adminAuthority(String ab) {
		boolean check = true;
		String cb = "";
		for(int i=0;i<UserInfo.size();i++) {
			UserVO vo = UserInfo.get(i);
			if(vo.getUserId().equals(ab)) {
				cb = vo.getUserAuthority();
				if("9".equals(vo.getUserAuthority())) {
					check = false;
				}
			}
		}
		return check;
	}
	
	/**현재 비밀번호 확인*/
	public boolean currentPwCheck(String userId, String inputPw) {
		boolean check = true;
		for(int i=0; i<UserInfo.size(); i++) {
			UserVO pwVO = UserInfo.get(i);
			if(pwVO.getUserId().equals(userId) && !pwVO.getUserPw().equals(inputPw)) {
				check = false;
			}
		}
		return check;
	}
	
	/**회원 정보 수정*/
	public UserVO getUpdateUserInfoInputData(String vsId, String inputPw, String inputCellPhone, String inputArea, String inputAddress) {
		ArrayList<UserVO> list = readFile02("src/data/User.csv");
		UserVO outVo = null;
		String inputData = "";
		// 비번 수정
		if(!inputPw.equals("")) {
			// 비번만 수정
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getUserId().equals(vsId)) {
					inputData = list.get(i).getUserId()+","+inputPw+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+list.get(i).getUserCellPhone()+","+list.get(i).getUserArea()+","+list.get(i).getUserAddress()+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
				}
			}
			// 비번, 휴대폰번호 수정
			if(!inputCellPhone.equals("")) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getUserId().equals(vsId)) {
						inputData = list.get(i).getUserId()+","+inputPw+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+list.get(i).getUserArea()+","+list.get(i).getUserAddress()+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
					}
				}
			// 비번, 휴대폰번호, 상세주소 수정
				if(!inputAddress.equals("")) {
					for(int i=0;i<list.size();i++) {
						if(list.get(i).getUserId().equals(vsId)) {
							inputData = list.get(i).getUserId()+","+inputPw+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+list.get(i).getUserArea()+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
						}
					}
			// 비번, 휴대폰번호, 주소, 상세주소 수정
					if(!inputArea.equals("")) {
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getUserId().equals(vsId)) {
								inputData = list.get(i).getUserId()+","+inputPw+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+inputArea+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
							}
						}
					}
				}
			// 비번, 상세주소만 수정
			} else if(!inputAddress.equals("")) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getUserId().equals(vsId)) {
						inputData = list.get(i).getUserId()+","+inputPw+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+list.get(i).getUserCellPhone()+","+list.get(i).getUserArea()+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
					}
				}
			// 비번, 주소, 상세주소 수정
				if(!inputArea.equals("")) {
					for(int i=0;i<list.size();i++) {
						if(list.get(i).getUserId().equals(vsId)) {
							inputData = list.get(i).getUserId()+","+inputPw+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+list.get(i).getUserCellPhone()+","+inputArea+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
						}
					}
				}
			}
		// 비번 수정 안함
		} else if(inputPw.equals("") || inputPw.equals(null)) {
			// 휴대폰번호만 수정
			if(!inputCellPhone.equals("")) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getUserId().equals(vsId)) {
						inputData = list.get(i).getUserId()+","+list.get(i).getUserPw()+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+list.get(i).getUserArea()+","+list.get(i).getUserAddress()+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
					}
				}
			// 휴대폰번호, 상세주소 수정
				if(!inputAddress.equals("")) {
					for(int i=0;i<list.size();i++) {
						if(list.get(i).getUserId().equals(vsId)) {
							inputData = list.get(i).getUserId()+","+list.get(i).getUserPw()+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+list.get(i).getUserArea()+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
						}
					}
			// 휴대폰번호, 주소, 상세주소 수정
					if(!inputArea.equals("")) {
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getUserId().equals(vsId)) {
								inputData = list.get(i).getUserId()+","+list.get(i).getUserPw()+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+inputArea+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
							}
						}
					}
				}
			}
		// 비번, 휴대폰번호 수정 안함
		} else if((inputPw.equals("")||inputPw.equals(null)) && (inputCellPhone.equals("")||inputCellPhone.equals(null))) {
			// 상세주소만 수정
			if(!inputArea.equals("")) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getUserId().equals(vsId)) {
						inputData = list.get(i).getUserId()+","+list.get(i).getUserPw()+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+list.get(i).getUserCellPhone()+","+list.get(i).getUserArea()+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
					}
				}
			// 주소, 상세주소 수정
				if(!inputAddress.equals("")) {
					for(int i=0;i<list.size();i++) {
						if(list.get(i).getUserId().equals(vsId)) {
							inputData = list.get(i).getUserId()+","+list.get(i).getUserPw()+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+list.get(i).getUserCellPhone()+","+inputArea+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
						}
					}
				}
			}
		// 비번, 주소 수정 안함
		} else if((inputPw.equals("")||inputPw.equals(null)) && (inputArea.equals("")||inputArea.equals(null))) {
			// 휴대폰번호만 수정
			if(!inputCellPhone.equals("")) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getUserId().equals(vsId)) {
						inputData = list.get(i).getUserId()+","+list.get(i).getUserPw()+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+list.get(i).getUserArea()+","+list.get(i).getUserAddress()+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
					}
				}
			// 휴대폰번호, 상세주소 수정
				if(!inputAddress.equals("")) {
					for(int i=0;i<list.size();i++) {
						if(list.get(i).getUserId().equals(vsId)) {
							inputData = list.get(i).getUserId()+","+list.get(i).getUserPw()+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+list.get(i).getUserArea()+","+inputAddress+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
						}
					}
				}
			}
		// 비번, 주소, 상세주소 수정 안함
		} else {
			// 휴대폰번호만 수정
			if(!inputCellPhone.equals("")) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getUserId().equals(vsId)) {
						inputData = list.get(i).getUserId()+","+list.get(i).getUserPw()+","+list.get(i).getUserName()+","+list.get(i).getUserAuthority()+","+list.get(i).getTeamNum()+","+list.get(i).getUserEmail()+","+inputCellPhone+","+list.get(i).getUserArea()+","+list.get(i).getUserAddress()+","+list.get(i).getUserPoint()+","+list.get(i).getUserRegistration()+","+list.get(i).getUserRegistId();
					}
				}
			}
		}
		inputData = inputData.trim();//앞뒤 공간 삭제
		String[] dataArray = inputData.split(",");
		outVo  = new UserVO(dataArray[0],
							dataArray[1],
							dataArray[2],
							dataArray[3],
							dataArray[4],
							dataArray[5],
							dataArray[6],
				      		dataArray[7],
				      		dataArray[8],
				      		dataArray[9],
				      		dataArray[10],
				      		dataArray[11]
		);
		return outVo;
	}
	
	public boolean isMemberExists(UserVO vo){
		boolean flag = false;
		for(int i=0;i<UserInfo.size();i++) {
			UserVO vsVO = UserInfo.get(i);
			//이메일을 비교, 데이터가 있으면 반복 종료
			if(vsVO.getUserId().equals(vo.getUserId())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
}
