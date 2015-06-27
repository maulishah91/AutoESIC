package com.esic.util;

import com.esic.ObjectStore;
import com.esic.domain.ESICRecord;

public class ESICRecordUtil {

	/**
	 * checks if record's username is in blocked list because of bad password.
	 * 
	 * @param record
	 * @return
	 */
	public static boolean isRecordLoginBlocked(ESICRecord record) {

	

		return ObjectStore.blockedUsers.contains(record.getEsicUserName());

	}

	/**
	 * check is login details are present.
	 * @param record
	 * @return
	 */
	public static boolean isLoginDetailPresent(ESICRecord record)
	{
		
		//sanity check also.
		if (record.getEsicUserName() == null
				|| record.getEsicUserName().trim().isEmpty()) {
			return true;
		}

		if (record.getEsicPassword() == null
				|| record.getEsicPassword().trim().isEmpty()) {
			return true;
		}
		
		return false;
		
	}
}
