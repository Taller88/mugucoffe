/**
 *<pre>
 * PackageName : kr.sist.joba.order.dao
 * Description : 주문관련 DAO 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.order.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.DTO;
import data.WorkDiv;

/**
 * <pre>
 * PackageName : kr.sist.joba.order.dao
 * ClassName : OrderNumDAO.java
 * Description : 주문번호 DAO 클래스
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
public class OrderNumDAO implements WorkDiv {

	private List<OrderNumVO> addressBook = new ArrayList<OrderNumVO>();
	private final String ADD_FILE = "src/data/OrderNum.csv";
	
	/**
	 * title       OrderNumDAO
	 * Description readFile 메서드에 OrderNum.csv 경로를 연결 시키는 메서드
	 */
	public OrderNumDAO() {
		addressBook = readFile01(ADD_FILE);
	}
	
	/**
	 * title       readFile01
	 * Description get(i)와 getVO정보들 분리 배열
	 * @param 		filePath
	 * @return 		addressData
	 */
	public List<OrderNumVO> readFile01(String filePath){
		List<OrderNumVO> addressData = new ArrayList<OrderNumVO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while( (line=br.readLine()) != null ) {
				//홍길동,jamesol@paran.com,16321203,010-1234-5678
				String[] dataArray = line.split(",");
				OrderNumVO vo = new OrderNumVO(dataArray[0]);
				addressData.add(vo);
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
		return addressData;
	}
	
	/**
	 * title       saveFile
	 * Description csv에 입력 정보를 저장하는 메서드
	 * @param 		path
	 * @return 		flag
	 */
	public int saveFile(String path)  {
		File file = new File(path);
		FileWriter      fw = null;
		BufferedWriter  bw = null;
		int flag = 0;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
		//--------------------------------------
			for(int i=0;i<addressBook.size();i++) {
				OrderNumVO vo = addressBook.get(i);
				StringBuilder sb = new StringBuilder();
				sb.append(vo.getOrderNum()
				);
				//마지막 라인에 "\n" 제거
				if((i+1) != addressBook.size()) {
					sb.append("\n");
				}
				flag++;
				bw.write(sb.toString());
			}
		//--------------------------------------
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * title       saveOrderNum
	 * Description 주문번호 생성 메서드
	 * @return 		ab+db, ab+1001
	 */
	public String saveOrderNum() {
		Date date01 = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YYYYMMdd");
		OrderNumVO vo = null;
		for(int i=0;i<addressBook.size();i++) {
			vo = addressBook.get(i);
		}
		String ab = date.format(date01);
		String bb = vo.getOrderNum().substring(8,12);
		if(date.format(date01).equals(vo.getOrderNum().substring(0,8))) {
			int cb = Integer.parseInt(bb)+1;
			String db = Integer.toString(cb);
			return ab+db;
		} else {
			return ab+1001;
		}
	}
	
	/**
	 * title       do_save
	 * Description OrderNum.csv에 정보를 저장하는 메서드
	 * @param 		dto
	 * @return 		flag
	 */
	@Override
	public int do_save(DTO dto) {
		OrderNumVO vo = (OrderNumVO)dto;
		addressBook.add(vo);
		int flag = saveFile(ADD_FILE);
		return flag;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return null;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return null;
	}

	@Override
	public int do_update(DTO dto) {
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		return 0;
	}
	
}
