/**
 *<pre>
 * PackageName : kr.sist.joba.store.dao
 * Description : 매장선택 DAO 패키지
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-30
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package kr.sist.joba.store.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import data.DTO;
import data.WorkDiv;
import kr.sist.joba.store.dao.StoreVO;

/**
 * <pre>
 * PackageName : kr.sist.joba.store.dao
 * ClassName : StoreDAO.java
 * Description : 결제완료 확인 클래스
 * Modification Information
 * 
 *  수정일    	  	수정자               수정내용
 *  ---------   ---------   -------------------------------
 *  2019-11-30    홍승민         	최초생성
 *  2019-12-17    홍승민         	개발완료
 *  
 * </pre>
 * @since : 2019-11-30
 * @version : 1.0
 * @author : 쌍용교육센터 E반 1조 JOB_A
 */
public class StoreDAO implements WorkDiv {

	private static List<StoreVO> addressBook = new ArrayList<StoreVO>();
	private final String ADD_FILE = "src/data/Store.csv";

	/**
	 * title       StoreDAO
	 * Description readFile 메서드에 Store.csv 경로를 연결 시키는 메서드
	 */
	public StoreDAO() {
		addressBook = readFile01(ADD_FILE);
	}

	/**
	 * title       readFile01
	 * Description csv 정보를 List에 담는 메서드
	 * @param 		filePath
	 * @return 		addressData
	 */
	public List<StoreVO> readFile01(String filePath) {
		List<StoreVO> addressData = new ArrayList<StoreVO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				// 홍길동,jamesol@paran.com,16321203,010-1234-5678
				String[] dataArray = line.split(",");
				StoreVO vo = new StoreVO(dataArray[0], dataArray[1], dataArray[2]);
				addressData.add(vo);
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
		return addressData;
	}//--readFile01
	
	/**
	 * title       readFile02
	 * Description csv 정보를 ArrayList에 담는 메서드
	 * @param 		path
	 * @return 		list
	 */
	static ArrayList<StoreVO> readFile02(String path) {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		try {
			Scanner scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				String input = scanner.nextLine();
	            //System.out.println(input);
	            String[] inArray = input.split(",");
	            StoreVO dto = new StoreVO(inArray[0], inArray[1], inArray[2]);
	            list.add(dto);
	        } //while
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	    }
	      return list;
	} //--readFile02
	
	@Override
	public int do_save(DTO dto) {
		return 0;
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
	
	/**
	 * title       readStoreCellPhone
	 * Description 매장명에 따른 전화번호 읽는 메서드
	 * @param 		vsStoreName
	 * @return 		storeCellPhone
	 */
	public String readStoreCellPhone(String vsStoreName) {
		String storeCellPhone = null;
		for (int i = 0; i < addressBook.size(); i++) {
			StoreVO vo = addressBook.get(i);
			if (vo.getStoreName().equals(vsStoreName)) {
				storeCellPhone = vo.getStoreCellPhone();
			}
		}
		return storeCellPhone;
	}
	
	/**
	 * title       readStoreAreaAll
	 * Description 매장 지역구 출력 메서드 (TreeSet)
	 * @return 	   set
	 */
	public Set<String> readStoreAreaAll() {
		ArrayList<StoreVO> list = readFile02("src/data/Store.csv");
		String strStoreArea = "";
		for(int i=0;i<list.size();i++) {
			strStoreArea += list.get(i).getStoreArea()+",";
		}
		strStoreArea = strStoreArea.substring(0,strStoreArea.length()-1);
		strStoreArea = strStoreArea.trim();
		String[] arrayAb = strStoreArea.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
	/**
	 * title       readStoreName
	 * Description 매장명 출력 메서드 (TreeSet)
	 * @param 		vsStoreArea
	 * @return 		set
	 */
	public Set<String> readStoreName(String vsStoreArea) {
		ArrayList<StoreVO> list = readFile02("src/data/Store.csv");
		String strStoreName = "";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getStoreArea().equals(vsStoreArea)) {
				strStoreName += list.get(i).getStoreName()+",";
			}
		}
		strStoreName = strStoreName.substring(0, strStoreName.length()-1);
		strStoreName = strStoreName.trim();
		String[] arrayAb = strStoreName.split(",");
		Set<String> set = new TreeSet<String>();
		for(String num : arrayAb) {
			set.add(num);
		}
		return set;
	}
	
}
