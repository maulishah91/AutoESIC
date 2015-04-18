package com.esic.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;


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
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}
	
	
	
	
	
	
	
	
	
	
	

	//AUTO GENERATE CODE....
	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getSrNo(){
	return this.get("srNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getEsicUserName(){
	return this.get("esicUserName"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getEsicPassword(){
	return this.get("esicPassword"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getEmpCode(){
	return this.get("empCode"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getESICNo(){
	return this.get("ESICNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getAutoEsicStatus(){
	return this.get("autoEsicStatus"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getAutoEsicComments(){
	return this.get("autoEsicComments"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getEsicLocation(){
	return this.get("esicLocation"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getEsicDispensaryName(){
	return this.get("esicDispensaryName"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getIMP(){
	return this.get("IMP"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getEmployeeName(){
	return this.get("employeeName"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getHusbandOrFatherName(){
	return this.get("husbandOrFatherName"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getIsHusband(){
	return this.get("isHusband"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDateOfBirth(){
	return this.get("dateOfBirth"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getMatitalStatus(){
	return this.get("matitalStatus"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getGender(){
	return this.get("gender"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDateOfAppointment(){
	return this.get("dateOfAppointment"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getAadharID(){
	return this.get("aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_Address1(){
	return this.get("presentAddress_Address1"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_Address2(){
	return this.get("presentAddress_Address2"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_Address3(){
	return this.get("presentAddress_Address3"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_District(){
	return this.get("presentAddress_District"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_State(){
	return this.get("presentAddress_State"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_Pin(){
	return this.get("presentAddress_Pin"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_Code(){
	return this.get("presentAddress_Code"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_PhoneNo(){
	return this.get("presentAddress_PhoneNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_MobileNo(){
	return this.get("presentAddress_MobileNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPresentAddress_emailID(){
	return this.get("presentAddress_emailID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_Address1(){
	return this.get("permanntAddress_Address1"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_Address2(){
	return this.get("permanntAddress_Address2"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_Address3(){
	return this.get("permanntAddress_Address3"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_District(){
	return this.get("permanntAddress_District"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_State(){
	return this.get("permanntAddress_State"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_PinCode(){
	return this.get("permanntAddress_PinCode"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_PhoneNo(){
	return this.get("permanntAddress_PhoneNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_MobileNo(){
	return this.get("permanntAddress_MobileNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getPermanntAddress_emailID(){
	return this.get("permanntAddress_emailID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getNomineeName(){
	return this.get("nomineeName"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getNomineeRelationship(){
	return this.get("nomineeRelationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getNomineeAddress(){
	return this.get("nomineeAddress"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getNomineePinCode(){
	return this.get("nomineePinCode"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getNomineePhoneNo(){
	return this.get("nomineePhoneNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getNomineeMobileNo(){
	return this.get("nomineeMobileNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getNomineeEmailID(){
	return this.get("nomineeEmailID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getNomineeAadharID(){
	return this.get("nomineeAadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_1_Name(){
	return this.get("dependent_1_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_1_DOB(){
	return this.get("dependent_1_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_1_Relationship(){
	return this.get("dependent_1_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_1_residingWithHimYes(){
	return this.get("dependent_1_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_1_residingWithHimNo(){
	return this.get("dependent_1_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_1_town(){
	return this.get("dependent_1_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_1_state(){
	return this.get("dependent_1_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_1_aadharID(){
	return this.get("dependent_1_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_2_Name(){
	return this.get("dependent_2_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_2_DOB(){
	return this.get("dependent_2_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_2_Relationship(){
	return this.get("dependent_2_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_2_residingWithHimYes(){
	return this.get("dependent_2_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_2_residingWithHimNo(){
	return this.get("dependent_2_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_2_town(){
	return this.get("dependent_2_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_2_state(){
	return this.get("dependent_2_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_2_aadharID(){
	return this.get("dependent_2_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_3_Name(){
	return this.get("dependent_3_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_3_DOB(){
	return this.get("dependent_3_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_3_Relationship(){
	return this.get("dependent_3_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_3_residingWithHimYes(){
	return this.get("dependent_3_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_3_residingWithHimNo(){
	return this.get("dependent_3_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_3_town(){
	return this.get("dependent_3_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_3_state(){
	return this.get("dependent_3_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_3_aadharID(){
	return this.get("dependent_3_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_4_Name(){
	return this.get("dependent_4_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_4_DOB(){
	return this.get("dependent_4_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_4_Relationship(){
	return this.get("dependent_4_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_4_residingWithHimYes(){
	return this.get("dependent_4_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_4_residingWithHimNo(){
	return this.get("dependent_4_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_4_town(){
	return this.get("dependent_4_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_4_state(){
	return this.get("dependent_4_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_4_aadharID(){
	return this.get("dependent_4_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_5_Name(){
	return this.get("dependent_5_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_5_DOB(){
	return this.get("dependent_5_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_5_Relationship(){
	return this.get("dependent_5_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_5_residingWithHimYes(){
	return this.get("dependent_5_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_5_residingWithHimNo(){
	return this.get("dependent_5_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_5_town(){
	return this.get("dependent_5_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_5_state(){
	return this.get("dependent_5_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_5_aadharID(){
	return this.get("dependent_5_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_6_Name(){
	return this.get("dependent_6_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_6_DOB(){
	return this.get("dependent_6_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_6_Relationship(){
	return this.get("dependent_6_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_6_residingWithHimYes(){
	return this.get("dependent_6_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_6_residingWithHimNo(){
	return this.get("dependent_6_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_6_town(){
	return this.get("dependent_6_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_6_state(){
	return this.get("dependent_6_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_6_aadharID(){
	return this.get("dependent_6_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_7_Name(){
	return this.get("dependent_7_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_7_DOB(){
	return this.get("dependent_7_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_7_Relationship(){
	return this.get("dependent_7_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_7_residingWithHimYes(){
	return this.get("dependent_7_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_7_residingWithHimNo(){
	return this.get("dependent_7_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_7_town(){
	return this.get("dependent_7_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_7_state(){
	return this.get("dependent_7_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_7_aadharID(){
	return this.get("dependent_7_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_8_Name(){
	return this.get("dependent_8_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_8_DOB(){
	return this.get("dependent_8_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_8_Relationship(){
	return this.get("dependent_8_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_8_residingWithHimYes(){
	return this.get("dependent_8_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_8_residingWithHimNo(){
	return this.get("dependent_8_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_8_town(){
	return this.get("dependent_8_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_8_state(){
	return this.get("dependent_8_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_8_aadharID(){
	return this.get("dependent_8_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_9_Name(){
	return this.get("dependent_9_Name"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_9_DOB(){
	return this.get("dependent_9_DOB"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_9_Relationship(){
	return this.get("dependent_9_Relationship"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_9_residingWithHimYes(){
	return this.get("dependent_9_residingWithHimYes"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_9_residingWithHimNo(){
	return this.get("dependent_9_residingWithHimNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_9_town(){
	return this.get("dependent_9_town"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_9_state(){
	return this.get("dependent_9_state"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getDependent_9_aadharID(){
	return this.get("dependent_9_aadharID"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getBankAccountNo(){
	return this.get("bankAccountNo"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getBankAccountType(){
	return this.get("bankAccountType"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getBankAccountBankName(){
	return this.get("bankAccountBankName"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getBankAccountBranchName(){
	return this.get("bankAccountBranchName"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getBankAccountMICR(){
	return this.get("bankAccountMICR"); }

	/** 
	  *    see  ESICREcordMapMethodgenerator 
	 */ 

	 public String getBankAccountIFSC(){
	return this.get("bankAccountIFSC"); }

	

}
