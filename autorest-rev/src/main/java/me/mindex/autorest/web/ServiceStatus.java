package me.mindex.autorest.web;

public enum ServiceStatus {
	SUCCESS("0"), FAILURE("-1");
	private String status;
	ServiceStatus(String status){
		this.status = status;
	}
	public ServiceStatus fromStatus(String statusStr){
		switch(statusStr){
		case "0" :
			return ServiceStatus.SUCCESS;
		case "-1" :
			return ServiceStatus.FAILURE;
		default : 
			return null;
		}
	}
	
	@Override
	public String toString() {
		return status;
	}
}
