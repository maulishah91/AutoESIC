package com.esic.domain;

public class Dependent {

	@Override
	public String toString() {
		return "Dependent ["
				+ (name != null ? "name=" + name + ", " : "")
				+ (dob != null ? "dob=" + dob + ", " : "")
				+ (relationship != null ? "relationship=" + relationship + ", "
						: "") + "residingWithHim=" + residingWithHim + ", "
				+ (town != null ? "town=" + town + ", " : "")
				+ (state != null ? "state=" + state + ", " : "")
				+ (aadharID != null ? "aadharID=" + aadharID : "") + "]";
	}
	private String name;
	private ESICDate dob;
	private String relationship;
	private boolean residingWithHim;
	private String town;
	private String state;
	private String aadharID;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ESICDate getDob() {
		return dob;
	}
	public void setDob(ESICDate dob) {
		this.dob = dob;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public boolean isResidingWithHim() {
		return residingWithHim;
	}
	public void setResidingWithHim(boolean residingWithHim) {
		this.residingWithHim = residingWithHim;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAadharID() {
		return aadharID;
	}
	public void setAadharID(String aadharID) {
		this.aadharID = aadharID;
	}

}
