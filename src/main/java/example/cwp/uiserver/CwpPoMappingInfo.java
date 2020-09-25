package example.cwp.uiserver;

public class CwpPoMappingInfo {
	
	public static final int INDEX_PROJECT_CODE = 0;
	
	public static final int INDEX_CWP_CODE = 1;
	
	public static final int INDEX_MATERIAL_CODE = 2;

	public static final int INDEX_NET_QUANTITY = 3;

	public static final int INDEX_ROS_DATE = 4;

	public static final int INDEX_PO_NUMBER = 5;

	public static final int INDEX_VENDOR_NAME = 6;

	public static final int INDEX_PO_QUANTITY = 7;

	public static final int INDEX_ETA_DATE = 8;

	private String projectCode;
	
	private String cwpCode;

	private String materialCode;

	private int netQuantity;
	
	private String rosDate;
	
	private String poNumber;
	
	private String vendorName;
	
	private int poQuantity;
	
	private String etaDate;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getCwpCode() {
		return cwpCode;
	}

	public void setCwpCode(String cwpCode) {
		this.cwpCode = cwpCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public int getNetQuantity() {
		return netQuantity;
	}

	public void setNetQuantity(int netQuantity) {
		this.netQuantity = netQuantity;
	}

	public String getRosDate() {
		return rosDate;
	}

	public void setRosDate(String rosDate) {
		this.rosDate = rosDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public int getPoQuantity() {
		return poQuantity;
	}

	public void setPoQuantity(int poQuantity) {
		this.poQuantity = poQuantity;
	}

	public String getEtaDate() {
		return etaDate;
	}

	public void setEtaDate(String etaDate) {
		this.etaDate = etaDate;
	}
}