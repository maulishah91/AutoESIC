package com.esic.domain;

import com.esic.domain.annotations.ESICExcelColumns;
import com.esic.domain.annotations.ESICExcelColumns.ColumnNames;

public class ESICRecord {



	/**
	 * SR. NO.
	 */

	@ESICExcelColumns(ColumnNames.srNo)
	public String srNo;

	/**
	 * ESIC User
	 */
	@ESICExcelColumns(ColumnNames.esicUserName)
	public String esicUserName;

	/**
	 * ESIC Password
	 */
	@ESICExcelColumns(ColumnNames.esicPassword)
	public String esicPassword;

	/**
	 * EMP CODE
	 */
	@ESICExcelColumns(ColumnNames.empCode)
	public String empCode;

	/**
	 * INS NO
	 */
	@ESICExcelColumns(ColumnNames.insuranceNo)
	public String insuranceNo;

	/**
	 * EMPLOYEE NAME
	 */
	@ESICExcelColumns(ColumnNames.employeeName)
	public String employeeName;

	/**
	 * Gender
	 */
	@ESICExcelColumns(ColumnNames.gender)
	public String gender;

	/**
	 * DATE OF BIRTH
	 */
	@ESICExcelColumns(ColumnNames.dateOfBirth)
	public String dateOfBirth;

	/**
	 * FATHER NAME
	 */
	@ESICExcelColumns(ColumnNames.fatherName)
	public String fatherName;

	/**
	 * DATE OF BIRTH OF FATHER.HUSBAND
	 */
	@ESICExcelColumns(ColumnNames.fatherDateOfBirth)
	public String fatherDateOfBirth;

	/**
	 * NAME OF MOTHER
	 */
	@ESICExcelColumns(ColumnNames.montherName)
	public String montherName;

	/**
	 * DATE OF BIRTH OF MOTHER
	 */
	@ESICExcelColumns(ColumnNames.motherDateOfBirth)
	public String motherDateOfBirth;

	/**
	 * MARITAL STATUS
	 */
	@ESICExcelColumns(ColumnNames.matitalStatus)
	public String matitalStatus;

	/**
	 * NAME OF SPOUSE
	 */
	@ESICExcelColumns(ColumnNames.spouseName)
	public String spouseName;

	/**
	 * DATE OF BIRTH OF SPOUSE
	 */
	@ESICExcelColumns(ColumnNames.spouseDateOfBirth)
	public String spouseDateOfBirth;

	/**
	 * NAME OF SON
	 */
	@ESICExcelColumns(ColumnNames.sonName)
	public String sonName;

	/**
	 * 
	 * DATE OF BIRTH OF SON
	 */
	@ESICExcelColumns(ColumnNames.sonDateOfBirth)
	public String sonDateOfBirth;

	/**
	 * NAME OF DAUGHTER
	 */
	@ESICExcelColumns(ColumnNames.daughterName)
	public String daughterName;

	/**
	 * DATE OF BIRTH OF DAUGHTER
	 */
	@ESICExcelColumns(ColumnNames.daughterDateOfBirth)
	public String daughterDateOfBirth;

	/**
	 * LOCAL ADDRESS
	 */
	@ESICExcelColumns(ColumnNames.localAddress)
	public String localAddress;
	/**
	 * PIN CODE
	 */
	@ESICExcelColumns(ColumnNames.localPinCode)
	public String localPinCode;
	/**
	 * PERMANENT ADDRESS
	 */
	@ESICExcelColumns(ColumnNames.permanentAddress)
	public String permanentAddress;

	/**
	 * PIN CODE
	 */
	@ESICExcelColumns(ColumnNames.permanentPinCode)
	public String permanentPinCode;

	/**
	 * CONTACT NO
	 */

	@ESICExcelColumns(ColumnNames.contactNo)
	public String contactNo;
	/**
	 * DATE OF APPOINTMENT
	 */

	@ESICExcelColumns(ColumnNames.dateOfAppointment)
	public String dateOfAppointment;

	/**
	 * NAME OF NOMINEE
	 */
	@ESICExcelColumns(ColumnNames.nomineeName)
	public String nomineeName;

	/**
	 * RELATIONSHIP WITH THE EMPLOYEE
	 */
	@ESICExcelColumns(ColumnNames.nomineeRelationship)
	public String nomineeRelationship;

	/**
	 * SENDING LOCATION
	 */

	@ESICExcelColumns(ColumnNames.sendingLocation)
	public String sendingLocation;

	/**
	 * OLD ESIC NO
	 */
	@ESICExcelColumns(ColumnNames.oldESICNo)
	public String oldESICNo;

	/**
	 * ESIC DISPENSARY NAME
	 */
	@ESICExcelColumns(ColumnNames.esicDispensaryName)
	public String esicDispensaryName;

}
