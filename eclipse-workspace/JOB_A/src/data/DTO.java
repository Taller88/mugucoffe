/**
 *<pre>
 * @PackageName : data
 * @Description : Value Object 조상 : 모든 Value Object DTO 상속
 * @author 쌍용교육센터 E반 1조 JOB_A
 * @since 2019-11-22
 * @version 1.0
 *  Copyright (C) by JOB_A All right reserved.
 * </pre>
 */
package data;

public class DTO {
	/**글 번호 */
	private String no;
	/** 검색구분*/
	private String searchDiv;
	/** 검색어*/
	private String searchWord;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	@Override
	public String toString() {
		return "DTO [no=" + no + ", searchDiv=" + searchDiv + ", searchWord=" + searchWord + "]";
	}
}
