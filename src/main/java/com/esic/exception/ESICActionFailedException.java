package com.esic.exception;

import com.esic.selenium.action.SearchAndOpenRegisteredESICAction;

public class ESICActionFailedException  extends ESICException{

	
	

	private static final long serialVersionUID = -7288999794678358064L;

	public ESICActionFailedException(String string, Exception e, SearchAndOpenRegisteredESICAction searchAndOpenRegisteredESICAction) {
		super(string, e);
	}

}
