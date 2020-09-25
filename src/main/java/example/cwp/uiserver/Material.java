package example.cwp.uiserver;

import java.util.Date;

public class Material {

	private String materialCode;
	
	private int quantity;
	
	private Date rosDate;
	
	private String poNumber;
	
	private int poQuantity;
	
	private String vendorName;
	
	private Date etaDate;
	
	private boolean checked = false;
	
	public Material() {
	}
	
	public Material(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getRosDate() {
		return rosDate;
	}

	public void setRosDate(Date date) {
		this.rosDate = date;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public int getPoQuantity() {
		return poQuantity;
	}

	public void setPoQuantity(int poQuantity) {
		this.poQuantity = poQuantity;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public Date getEtaDate() {
		return etaDate;
	}

	public void setEtaDate(Date etaDate) {
		this.etaDate = etaDate;
	}


	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}