package com.alliebooks.fbo;

public class UnitInfo {
	private String tenantName;
	private double currentRent;
	
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public double getCurrentRent() {
		return currentRent;
	}
	public void setCurrentRent(double currentRent) {
		this.currentRent = currentRent;
	}
	@Override
	public String toString() {
		return "UnitInfo [tenantName=" + tenantName + ", currentRent=" + currentRent + "]";
	}
	
}
