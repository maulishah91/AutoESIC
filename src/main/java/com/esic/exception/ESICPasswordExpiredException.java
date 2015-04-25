package com.esic.exception;

public class ESICPasswordExpiredException extends ESICException {

	public String orgNumber;

	private static final long serialVersionUID = 6281958986513580248L;

	public ESICPasswordExpiredException(String orgString) {
		super("PAssword Expired for " + orgString, null);

	}

}
