package com.esic.exception;

public class ESICWebException  extends ESICException{


	private static final long serialVersionUID = -7288999794678358064L;

	public ESICWebException(String string, Exception e) {
		super(string, e);
	}

}
