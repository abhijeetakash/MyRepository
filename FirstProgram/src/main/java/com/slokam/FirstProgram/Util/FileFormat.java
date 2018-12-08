package com.slokam.FirstProgram.Util;

public enum FileFormat {
	
	PDF(".pdf",1), WORD(".doc",2), EXCEL(".xls",3),ZIP(".zip",4);
	
	private String formate;
	private Integer value;
	
	FileFormat(String formate,Integer value )
	{
		this.formate = formate;
		this.value = value;
	}

	public String getFormate() {
		return formate;
	}

	public Integer getValue() {
		return value;
	}
	
	

}
