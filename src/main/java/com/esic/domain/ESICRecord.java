package com.esic.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.esic.domain.annotations.ESICExcelColumns;
import com.esic.util.DateUtil;


public class ESICRecord extends HashMap<String, String> {

	private static final long serialVersionUID = -8654523891587959352L;
	
	/**
	 * BAckreference object...( for updation purpose)
	 */
	private Row excelRow;

	public Row getExcelRow() {
		return excelRow;
	}

	public void setExcelRow(Row excelRow) {
		this.excelRow = excelRow;
	}
	

	private List<Dependent> dependents;
	
	
	
	
	
	public List<Dependent> getDependents() {
		
		if(dependents == null)
		{
			dependents = new ArrayList<Dependent>();
		}
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}
	
	
	
	public void setAutoEsicStatus(String value) {
		 this.put(ESICExcelColumns.autoEsicStatus.toString(), value);
		Cell cell = this.excelRow.getCell(ESICExcelColumns.autoEsicStatus.ordinal());
		
		if(cell == null){
			 cell = this.excelRow.createCell(ESICExcelColumns.autoEsicStatus.ordinal());
		}
		
		cell.setCellValue(value);
	}

	public void setAutoEsicComments(String value) {
		 this.put(ESICExcelColumns.autoEsicComments.toString(), value);
			Cell cell = this.excelRow.getCell(ESICExcelColumns.autoEsicStatus.ordinal());
			
		 
		if (cell == null) {
			cell = this.excelRow.createCell(ESICExcelColumns.autoEsicComments.ordinal());
		}
			
			cell.setCellValue(value);
	}

	public boolean isCheckBoxTrueForPermanentDetails(){
		String yesOrNo=this.getCopyPresentDetailsToPermanent();
		if(yesOrNo.trim().equalsIgnoreCase("yes")){
			return true;
		}
		else{
			return false;
		}
	}

	public ESICDate getDateOfAppointmentESICDate() {
		String date = this.getDateOfAppointment();
		return DateUtil.getDate(date);
	}

	public ESICDate getDateOfBirthESICDate(){
		String date = this.getDateOfBirth();
		return DateUtil.getDate(date);
	}

	@Override
	public String toString() {
		return "ESICRecord ["
				+ (dependents != null ? "dependents=" + dependents + ", " : "")
				+ (getExcelRow() != null ? "getExcelRow()=" + getExcelRow()
						+ ", " : "")
				+ (getDependents() != null ? "getDependents()="
						+ getDependents() + ", " : "")
				+ (getDateOfAppointmentESICDate() != null ? "getDateOfAppointmentESICDate()="
						+ getDateOfAppointmentESICDate() + ", "
						: "")
				+ (getDateOfBirthESICDate() != null ? "getDateOfBirthESICDate()="
						+ getDateOfBirthESICDate() + ", "
						: "")
				+ (getSrNo() != null ? "getSrNo()=" + getSrNo() + ", " : "")
				+ (getEsicUserName() != null ? "getEsicUserName()="
						+ getEsicUserName() + ", " : "")
				+ (getEsicPassword() != null ? "getEsicPassword()="
						+ getEsicPassword() + ", " : "")
				+ (getEmpCode() != null ? "getEmpCode()=" + getEmpCode() + ", "
						: "")
				+ (getESICNo() != null ? "getESICNo()=" + getESICNo() + ", "
						: "")
				+ (getAutoEsicStatus() != null ? "getAutoEsicStatus()="
						+ getAutoEsicStatus() + ", " : "")
				+ (getAutoEsicComments() != null ? "getAutoEsicComments()="
						+ getAutoEsicComments() + ", " : "")
				+ (getEsicLocation() != null ? "getEsicLocation()="
						+ getEsicLocation() + ", " : "")
				+ (getEsicDispensaryName() != null ? "getEsicDispensaryName()="
						+ getEsicDispensaryName() + ", " : "")
				+ (getIMP() != null ? "getIMP()=" + getIMP() + ", " : "")
				+ (getEmployeeName() != null ? "getEmployeeName()="
						+ getEmployeeName() + ", " : "")
				+ (getHusbandOrFatherName() != null ? "getHusbandOrFatherName()="
						+ getHusbandOrFatherName() + ", "
						: "")
				+ (getIsHusband() != null ? "getIsHusband()=" + getIsHusband()
						+ ", " : "")
				+ (getDateOfBirth() != null ? "getDateOfBirth()="
						+ getDateOfBirth() + ", " : "")
				+ (getMatitalStatus() != null ? "getMatitalStatus()="
						+ getMatitalStatus() + ", " : "")
				+ (getGender() != null ? "getGender()=" + getGender() + ", "
						: "")
				+ (getDateOfAppointment() != null ? "getDateOfAppointment()="
						+ getDateOfAppointment() + ", " : "")
				+ (getAadharID() != null ? "getAadharID()=" + getAadharID()
						+ ", " : "")
				+ (getPresentAddress_Address() != null ? "getPresentAddress_Address()="
						+ getPresentAddress_Address() + ", "
						: "")
				+ (getPresentAddress_District() != null ? "getPresentAddress_District()="
						+ getPresentAddress_District() + ", "
						: "")
				+ (getPresentAddress_State() != null ? "getPresentAddress_State()="
						+ getPresentAddress_State() + ", "
						: "")
				+ (getPresentAddress_PinCode() != null ? "getPresentAddress_PinCode()="
						+ getPresentAddress_PinCode() + ", "
						: "")
				+ (getPresentAddress_PhoneNo() != null ? "getPresentAddress_PhoneNo()="
						+ getPresentAddress_PhoneNo() + ", "
						: "")
				+ (getPresentAddress_MobileNo() != null ? "getPresentAddress_MobileNo()="
						+ getPresentAddress_MobileNo() + ", "
						: "")
				+ (getPresentAddress_emailID() != null ? "getPresentAddress_emailID()="
						+ getPresentAddress_emailID() + ", "
						: "")
				+ (getCopyPresentDetailsToPermanent() != null ? "getCopyPresentDetailsToPermanent()="
						+ getCopyPresentDetailsToPermanent() + ", "
						: "")
				+ (getPermanntAddress_Address() != null ? "getPermanntAddress_Address()="
						+ getPermanntAddress_Address() + ", "
						: "")
				+ (getPermanntAddress_District() != null ? "getPermanntAddress_District()="
						+ getPermanntAddress_District() + ", "
						: "")
				+ (getPermanntAddress_State() != null ? "getPermanntAddress_State()="
						+ getPermanntAddress_State() + ", "
						: "")
				+ (getPermanntAddress_PinCode() != null ? "getPermanntAddress_PinCode()="
						+ getPermanntAddress_PinCode() + ", "
						: "")
				+ (getPermanntAddress_PhoneNo() != null ? "getPermanntAddress_PhoneNo()="
						+ getPermanntAddress_PhoneNo() + ", "
						: "")
				+ (getPermanntAddress_MobileNo() != null ? "getPermanntAddress_MobileNo()="
						+ getPermanntAddress_MobileNo() + ", "
						: "")
				+ (getPermanntAddress_emailID() != null ? "getPermanntAddress_emailID()="
						+ getPermanntAddress_emailID() + ", "
						: "")
				+ (getNomineeName() != null ? "getNomineeName()="
						+ getNomineeName() + ", " : "")
				+ (getNomineeRelationship() != null ? "getNomineeRelationship()="
						+ getNomineeRelationship() + ", "
						: "")
				+ (getNomineeAddress() != null ? "getNomineeAddress()="
						+ getNomineeAddress() + ", " : "")
				+ (getIsnomineeAFamilyMember() != null ? "getIsnomineeAFamilyMember()="
						+ getIsnomineeAFamilyMember() + ", "
						: "")
				+ (getNomineeState() != null ? "getNomineeState()="
						+ getNomineeState() + ", " : "")
				+ (getNomineeDistrict() != null ? "getNomineeDistrict()="
						+ getNomineeDistrict() + ", " : "")
				+ (getNomineePinCode() != null ? "getNomineePinCode()="
						+ getNomineePinCode() + ", " : "")
				+ (getNomineePhoneNo() != null ? "getNomineePhoneNo()="
						+ getNomineePhoneNo() + ", " : "")
				+ (getNomineeMobileNo() != null ? "getNomineeMobileNo()="
						+ getNomineeMobileNo() + ", " : "")
				+ (getNomineeAadharID() != null ? "getNomineeAadharID()="
						+ getNomineeAadharID() + ", " : "")
				+ (getDependent_1_Name() != null ? "getDependent_1_Name()="
						+ getDependent_1_Name() + ", " : "")
				+ (getDependent_1_DOB() != null ? "getDependent_1_DOB()="
						+ getDependent_1_DOB() + ", " : "")
				+ (getDependent_1_Relationship() != null ? "getDependent_1_Relationship()="
						+ getDependent_1_Relationship() + ", "
						: "")
				+ (getDependent_1_residingWithHimYes() != null ? "getDependent_1_residingWithHimYes()="
						+ getDependent_1_residingWithHimYes() + ", "
						: "")
				+ (getDependent_1_residingWithHimNo() != null ? "getDependent_1_residingWithHimNo()="
						+ getDependent_1_residingWithHimNo() + ", "
						: "")
				+ (getDependent_1_town() != null ? "getDependent_1_town()="
						+ getDependent_1_town() + ", " : "")
				+ (getDependent_1_state() != null ? "getDependent_1_state()="
						+ getDependent_1_state() + ", " : "")
				+ (getDependent_1_aadharID() != null ? "getDependent_1_aadharID()="
						+ getDependent_1_aadharID() + ", "
						: "")
				+ (getDependent_2_Name() != null ? "getDependent_2_Name()="
						+ getDependent_2_Name() + ", " : "")
				+ (getDependent_2_DOB() != null ? "getDependent_2_DOB()="
						+ getDependent_2_DOB() + ", " : "")
				+ (getDependent_2_Relationship() != null ? "getDependent_2_Relationship()="
						+ getDependent_2_Relationship() + ", "
						: "")
				+ (getDependent_2_residingWithHimYes() != null ? "getDependent_2_residingWithHimYes()="
						+ getDependent_2_residingWithHimYes() + ", "
						: "")
				+ (getDependent_2_residingWithHimNo() != null ? "getDependent_2_residingWithHimNo()="
						+ getDependent_2_residingWithHimNo() + ", "
						: "")
				+ (getDependent_2_town() != null ? "getDependent_2_town()="
						+ getDependent_2_town() + ", " : "")
				+ (getDependent_2_state() != null ? "getDependent_2_state()="
						+ getDependent_2_state() + ", " : "")
				+ (getDependent_2_aadharID() != null ? "getDependent_2_aadharID()="
						+ getDependent_2_aadharID() + ", "
						: "")
				+ (getDependent_3_Name() != null ? "getDependent_3_Name()="
						+ getDependent_3_Name() + ", " : "")
				+ (getDependent_3_DOB() != null ? "getDependent_3_DOB()="
						+ getDependent_3_DOB() + ", " : "")
				+ (getDependent_3_Relationship() != null ? "getDependent_3_Relationship()="
						+ getDependent_3_Relationship() + ", "
						: "")
				+ (getDependent_3_residingWithHimYes() != null ? "getDependent_3_residingWithHimYes()="
						+ getDependent_3_residingWithHimYes() + ", "
						: "")
				+ (getDependent_3_residingWithHimNo() != null ? "getDependent_3_residingWithHimNo()="
						+ getDependent_3_residingWithHimNo() + ", "
						: "")
				+ (getDependent_3_town() != null ? "getDependent_3_town()="
						+ getDependent_3_town() + ", " : "")
				+ (getDependent_3_state() != null ? "getDependent_3_state()="
						+ getDependent_3_state() + ", " : "")
				+ (getDependent_3_aadharID() != null ? "getDependent_3_aadharID()="
						+ getDependent_3_aadharID() + ", "
						: "")
				+ (getDependent_4_Name() != null ? "getDependent_4_Name()="
						+ getDependent_4_Name() + ", " : "")
				+ (getDependent_4_DOB() != null ? "getDependent_4_DOB()="
						+ getDependent_4_DOB() + ", " : "")
				+ (getDependent_4_Relationship() != null ? "getDependent_4_Relationship()="
						+ getDependent_4_Relationship() + ", "
						: "")
				+ (getDependent_4_residingWithHimYes() != null ? "getDependent_4_residingWithHimYes()="
						+ getDependent_4_residingWithHimYes() + ", "
						: "")
				+ (getDependent_4_residingWithHimNo() != null ? "getDependent_4_residingWithHimNo()="
						+ getDependent_4_residingWithHimNo() + ", "
						: "")
				+ (getDependent_4_town() != null ? "getDependent_4_town()="
						+ getDependent_4_town() + ", " : "")
				+ (getDependent_4_state() != null ? "getDependent_4_state()="
						+ getDependent_4_state() + ", " : "")
				+ (getDependent_4_aadharID() != null ? "getDependent_4_aadharID()="
						+ getDependent_4_aadharID() + ", "
						: "")
				+ (getDependent_5_Name() != null ? "getDependent_5_Name()="
						+ getDependent_5_Name() + ", " : "")
				+ (getDependent_5_DOB() != null ? "getDependent_5_DOB()="
						+ getDependent_5_DOB() + ", " : "")
				+ (getDependent_5_Relationship() != null ? "getDependent_5_Relationship()="
						+ getDependent_5_Relationship() + ", "
						: "")
				+ (getDependent_5_residingWithHimYes() != null ? "getDependent_5_residingWithHimYes()="
						+ getDependent_5_residingWithHimYes() + ", "
						: "")
				+ (getDependent_5_residingWithHimNo() != null ? "getDependent_5_residingWithHimNo()="
						+ getDependent_5_residingWithHimNo() + ", "
						: "")
				+ (getDependent_5_town() != null ? "getDependent_5_town()="
						+ getDependent_5_town() + ", " : "")
				+ (getDependent_5_state() != null ? "getDependent_5_state()="
						+ getDependent_5_state() + ", " : "")
				+ (getDependent_5_aadharID() != null ? "getDependent_5_aadharID()="
						+ getDependent_5_aadharID() + ", "
						: "")
				+ (getDependent_6_Name() != null ? "getDependent_6_Name()="
						+ getDependent_6_Name() + ", " : "")
				+ (getDependent_6_DOB() != null ? "getDependent_6_DOB()="
						+ getDependent_6_DOB() + ", " : "")
				+ (getDependent_6_Relationship() != null ? "getDependent_6_Relationship()="
						+ getDependent_6_Relationship() + ", "
						: "")
				+ (getDependent_6_residingWithHimYes() != null ? "getDependent_6_residingWithHimYes()="
						+ getDependent_6_residingWithHimYes() + ", "
						: "")
				+ (getDependent_6_residingWithHimNo() != null ? "getDependent_6_residingWithHimNo()="
						+ getDependent_6_residingWithHimNo() + ", "
						: "")
				+ (getDependent_6_town() != null ? "getDependent_6_town()="
						+ getDependent_6_town() + ", " : "")
				+ (getDependent_6_state() != null ? "getDependent_6_state()="
						+ getDependent_6_state() + ", " : "")
				+ (getDependent_6_aadharID() != null ? "getDependent_6_aadharID()="
						+ getDependent_6_aadharID() + ", "
						: "")
				+ (getDependent_7_Name() != null ? "getDependent_7_Name()="
						+ getDependent_7_Name() + ", " : "")
				+ (getDependent_7_DOB() != null ? "getDependent_7_DOB()="
						+ getDependent_7_DOB() + ", " : "")
				+ (getDependent_7_Relationship() != null ? "getDependent_7_Relationship()="
						+ getDependent_7_Relationship() + ", "
						: "")
				+ (getDependent_7_residingWithHimYes() != null ? "getDependent_7_residingWithHimYes()="
						+ getDependent_7_residingWithHimYes() + ", "
						: "")
				+ (getDependent_7_residingWithHimNo() != null ? "getDependent_7_residingWithHimNo()="
						+ getDependent_7_residingWithHimNo() + ", "
						: "")
				+ (getDependent_7_town() != null ? "getDependent_7_town()="
						+ getDependent_7_town() + ", " : "")
				+ (getDependent_7_state() != null ? "getDependent_7_state()="
						+ getDependent_7_state() + ", " : "")
				+ (getDependent_7_aadharID() != null ? "getDependent_7_aadharID()="
						+ getDependent_7_aadharID() + ", "
						: "")
				+ (getDependent_8_Name() != null ? "getDependent_8_Name()="
						+ getDependent_8_Name() + ", " : "")
				+ (getDependent_8_DOB() != null ? "getDependent_8_DOB()="
						+ getDependent_8_DOB() + ", " : "")
				+ (getDependent_8_Relationship() != null ? "getDependent_8_Relationship()="
						+ getDependent_8_Relationship() + ", "
						: "")
				+ (getDependent_8_residingWithHimYes() != null ? "getDependent_8_residingWithHimYes()="
						+ getDependent_8_residingWithHimYes() + ", "
						: "")
				+ (getDependent_8_residingWithHimNo() != null ? "getDependent_8_residingWithHimNo()="
						+ getDependent_8_residingWithHimNo() + ", "
						: "")
				+ (getDependent_8_town() != null ? "getDependent_8_town()="
						+ getDependent_8_town() + ", " : "")
				+ (getDependent_8_state() != null ? "getDependent_8_state()="
						+ getDependent_8_state() + ", " : "")
				+ (getDependent_8_aadharID() != null ? "getDependent_8_aadharID()="
						+ getDependent_8_aadharID() + ", "
						: "")
				+ (getDependent_9_Name() != null ? "getDependent_9_Name()="
						+ getDependent_9_Name() + ", " : "")
				+ (getDependent_9_DOB() != null ? "getDependent_9_DOB()="
						+ getDependent_9_DOB() + ", " : "")
				+ (getDependent_9_Relationship() != null ? "getDependent_9_Relationship()="
						+ getDependent_9_Relationship() + ", "
						: "")
				+ (getDependent_9_residingWithHimYes() != null ? "getDependent_9_residingWithHimYes()="
						+ getDependent_9_residingWithHimYes() + ", "
						: "")
				+ (getDependent_9_residingWithHimNo() != null ? "getDependent_9_residingWithHimNo()="
						+ getDependent_9_residingWithHimNo() + ", "
						: "")
				+ (getDependent_9_town() != null ? "getDependent_9_town()="
						+ getDependent_9_town() + ", " : "")
				+ (getDependent_9_state() != null ? "getDependent_9_state()="
						+ getDependent_9_state() + ", " : "")
				+ (getDependent_9_aadharID() != null ? "getDependent_9_aadharID()="
						+ getDependent_9_aadharID() + ", "
						: "")
				+ (getBankAccountNo() != null ? "getBankAccountNo()="
						+ getBankAccountNo() + ", " : "")
				+ (getBankAccountType() != null ? "getBankAccountType()="
						+ getBankAccountType() + ", " : "")
				+ (getBankAccountBankName() != null ? "getBankAccountBankName()="
						+ getBankAccountBankName() + ", "
						: "")
				+ (getBankAccountBranchName() != null ? "getBankAccountBranchName()="
						+ getBankAccountBranchName() + ", "
						: "")
				+ (getBankAccountMICR() != null ? "getBankAccountMICR()="
						+ getBankAccountMICR() + ", " : "")
				+ (getBankAccountIFSC() != null ? "getBankAccountIFSC()="
						+ getBankAccountIFSC() : "") + "]";
	}
	
	//AUTO GENERATE CODE....
	
	
	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getSrNo(){
	return this.get("srNo"); }


	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getEsicUserName(){
	return this.get("esicUserName"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getEsicPassword(){
	return this.get("esicPassword"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getEmpCode(){
	return this.get("empCode"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getESICNo(){
	return this.get("ESICNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getAutoEsicStatus(){
	return this.get("autoEsicStatus"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getAutoEsicComments(){
	return this.get("autoEsicComments"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getEsicLocation(){
	return this.get("esicLocation"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getEsicDispensaryName(){
	return this.get("esicDispensaryName"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getIMP(){
	return this.get("IMP"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getEmployeeName(){
	return this.get("employeeName"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getHusbandOrFatherName(){
	return this.get("husbandOrFatherName"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getIsHusband(){
	return this.get("isHusband"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDateOfBirth(){
	return this.get("dateOfBirth"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getMatitalStatus(){
	return this.get("matitalStatus"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getGender(){
	return this.get("gender"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDateOfAppointment(){
	return this.get("dateOfAppointment"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getAadharID(){
	return this.get("aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPresentAddress_Address(){
	return this.get("presentAddress_Address"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPresentAddress_District(){
	return this.get("presentAddress_District"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPresentAddress_State(){
	return this.get("presentAddress_State"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPresentAddress_PinCode(){
	return this.get("presentAddress_PinCode"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPresentAddress_PhoneNo(){
	return this.get("presentAddress_PhoneNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPresentAddress_MobileNo(){
	return this.get("presentAddress_MobileNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPresentAddress_emailID(){
	return this.get("presentAddress_emailID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getCopyPresentDetailsToPermanent(){
	return this.get("copyPresentDetailsToPermanent"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPermanntAddress_Address(){
	return this.get("permanntAddress_Address"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPermanntAddress_District(){
	return this.get("permanntAddress_District"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPermanntAddress_State(){
	return this.get("permanntAddress_State"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPermanntAddress_PinCode(){
	return this.get("permanntAddress_PinCode"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPermanntAddress_PhoneNo(){
	return this.get("permanntAddress_PhoneNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPermanntAddress_MobileNo(){
	return this.get("permanntAddress_MobileNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getPermanntAddress_emailID(){
	return this.get("permanntAddress_emailID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineeName(){
	return this.get("nomineeName"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineeRelationship(){
	return this.get("nomineeRelationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineeAddress(){
	return this.get("nomineeAddress"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getIsnomineeAFamilyMember(){
	return this.get("isnomineeAFamilyMember"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineeState(){
	return this.get("nomineeState"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineeDistrict(){
	return this.get("nomineeDistrict"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineePinCode(){
	return this.get("nomineePinCode"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineePhoneNo(){
	return this.get("nomineePhoneNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineeMobileNo(){
	return this.get("nomineeMobileNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getNomineeAadharID(){
	return this.get("nomineeAadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_1_Name(){
	return this.get("dependent_1_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_1_DOB(){
	return this.get("dependent_1_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_1_Relationship(){
	return this.get("dependent_1_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_1_residingWithHimYes(){
	return this.get("dependent_1_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_1_residingWithHimNo(){
	return this.get("dependent_1_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_1_town(){
	return this.get("dependent_1_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_1_state(){
	return this.get("dependent_1_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_1_aadharID(){
	return this.get("dependent_1_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_2_Name(){
	return this.get("dependent_2_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_2_DOB(){
	return this.get("dependent_2_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_2_Relationship(){
	return this.get("dependent_2_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_2_residingWithHimYes(){
	return this.get("dependent_2_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_2_residingWithHimNo(){
	return this.get("dependent_2_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_2_town(){
	return this.get("dependent_2_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_2_state(){
	return this.get("dependent_2_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_2_aadharID(){
	return this.get("dependent_2_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_3_Name(){
	return this.get("dependent_3_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_3_DOB(){
	return this.get("dependent_3_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_3_Relationship(){
	return this.get("dependent_3_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_3_residingWithHimYes(){
	return this.get("dependent_3_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_3_residingWithHimNo(){
	return this.get("dependent_3_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_3_town(){
	return this.get("dependent_3_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_3_state(){
	return this.get("dependent_3_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_3_aadharID(){
	return this.get("dependent_3_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_4_Name(){
	return this.get("dependent_4_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_4_DOB(){
	return this.get("dependent_4_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_4_Relationship(){
	return this.get("dependent_4_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_4_residingWithHimYes(){
	return this.get("dependent_4_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_4_residingWithHimNo(){
	return this.get("dependent_4_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_4_town(){
	return this.get("dependent_4_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_4_state(){
	return this.get("dependent_4_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_4_aadharID(){
	return this.get("dependent_4_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_5_Name(){
	return this.get("dependent_5_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_5_DOB(){
	return this.get("dependent_5_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_5_Relationship(){
	return this.get("dependent_5_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_5_residingWithHimYes(){
	return this.get("dependent_5_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_5_residingWithHimNo(){
	return this.get("dependent_5_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_5_town(){
	return this.get("dependent_5_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_5_state(){
	return this.get("dependent_5_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_5_aadharID(){
	return this.get("dependent_5_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_6_Name(){
	return this.get("dependent_6_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_6_DOB(){
	return this.get("dependent_6_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_6_Relationship(){
	return this.get("dependent_6_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_6_residingWithHimYes(){
	return this.get("dependent_6_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_6_residingWithHimNo(){
	return this.get("dependent_6_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_6_town(){
	return this.get("dependent_6_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_6_state(){
	return this.get("dependent_6_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_6_aadharID(){
	return this.get("dependent_6_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_7_Name(){
	return this.get("dependent_7_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_7_DOB(){
	return this.get("dependent_7_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_7_Relationship(){
	return this.get("dependent_7_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_7_residingWithHimYes(){
	return this.get("dependent_7_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_7_residingWithHimNo(){
	return this.get("dependent_7_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_7_town(){
	return this.get("dependent_7_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_7_state(){
	return this.get("dependent_7_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_7_aadharID(){
	return this.get("dependent_7_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_8_Name(){
	return this.get("dependent_8_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_8_DOB(){
	return this.get("dependent_8_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_8_Relationship(){
	return this.get("dependent_8_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_8_residingWithHimYes(){
	return this.get("dependent_8_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_8_residingWithHimNo(){
	return this.get("dependent_8_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_8_town(){
	return this.get("dependent_8_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_8_state(){
	return this.get("dependent_8_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_8_aadharID(){
	return this.get("dependent_8_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_9_Name(){
	return this.get("dependent_9_Name"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_9_DOB(){
	return this.get("dependent_9_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_9_Relationship(){
	return this.get("dependent_9_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_9_residingWithHimYes(){
	return this.get("dependent_9_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_9_residingWithHimNo(){
	return this.get("dependent_9_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_9_town(){
	return this.get("dependent_9_town"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_9_state(){
	return this.get("dependent_9_state"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getDependent_9_aadharID(){
	return this.get("dependent_9_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getBankAccountNo(){
	return this.get("bankAccountNo"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getBankAccountType(){
	return this.get("bankAccountType"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getBankAccountBankName(){
	return this.get("bankAccountBankName"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getBankAccountBranchName(){
	return this.get("bankAccountBranchName"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getBankAccountMICR(){
	return this.get("bankAccountMICR"); }

	/** 
	  *    see  ESICREcordMapMethodGenerator 
	 */ 

	 public String getBankAccountIFSC(){
	return this.get("bankAccountIFSC"); }

}
