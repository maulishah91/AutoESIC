package com.esic.domain.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface ESICExcelColumns {
	
	/**
	 * 
	 * members of this enums should be in same order same as order of fields in excel..
	 * @author meet
	 *
	 */
	enum ColumnNames{
		srNo,
		esicUserName,
		esicPassword,
		empCode,
		insuranceNo,
		employeeName,
		gender,
		dateOfBirth,
		fatherName,
		fatherDateOfBirth,
		montherName,
		motherDateOfBirth,
		matitalStatus,
		spouseName,
		spouseDateOfBirth,
		sonName,
		sonDateOfBirth,
		daughterName,
		daughterDateOfBirth,
		localAddress, 
		localPinCode,
		permanentAddress,
		permanentPinCode,
		contactNo,
		dateOfAppointment,
		nomineeName,
		nomineeRelationship,		
		sendingLocation,
		oldESICNo,
		esicDispensaryName,
		autoEsicStatus,
		autoEsicComments
		
	}
	
	
	/**
	 * simple value to store potition of field.
	 * @return
	 */
	  ColumnNames value();
	  
	  

}
