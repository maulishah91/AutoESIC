package com.esic.exception;

public class ESICRecordSkipException  extends ESICException{


	private static final long serialVersionUID = -7288999794678358064L;

	public ESICRecordSkipException(String string, Exception e) {
		super(string, e);
	}

}
