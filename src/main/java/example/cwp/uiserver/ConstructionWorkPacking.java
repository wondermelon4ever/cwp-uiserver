package example.cwp.uiserver;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructionWorkPacking {

	private String projectCode;
	
	private String areaCode;
	
	private String bookCode;
	
	private String cwpCode;
	
	private String cwpName;
	
	private Date startDate;
	
	private Date EndDate;
	
	private int duration;
	
	private float percentCompleted;
	
	private List<String> dependencies;
	
	private Map<String, Material> materialMap;
	
	private Collection<Material> materialList;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	public ConstructionWorkPacking() {
		this.materialMap = new HashMap<>();
		this.dependencies= new ArrayList<>();
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getCwpCode() {
		return cwpCode;
	}

	public void setCwpCode(String cwpCode) {
		this.cwpCode = cwpCode;
	}

	public String getCwpName() {
		return cwpName;
	}

	public void setCwpName(String cwpName) {
		this.cwpName = cwpName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getPercentCompleted() {
		return percentCompleted;
	}

	public void setPercentCompleted(float percentCompleted) {
		this.percentCompleted = percentCompleted;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}
	
	public void addDependency(String dependency) {
		this.dependencies.add(dependency);
	}

	public Map<String, Material> getMaterialMap() {
		return materialMap;
	}

	public void setMaterialMap(Map<String, Material> materialMap) {
		this.materialMap = materialMap;
	}
	
	public void addMaterial(Material material) {
		Material existed = materialMap.get(material.getMaterialCode() +"-"+ sdf.format(material.getRosDate()));
		if(existed != null) {
			existed.setQuantity(existed.getQuantity()+material.getQuantity());
		}
		
		this.materialMap.put(material.getMaterialCode()+"-"+ sdf.format(material.getRosDate()), material);
	}
	
	public Material getMaterial(String materialCode, String rosDate) {
		return this.materialMap.get(materialCode +"-"+ rosDate);
	}
	
	public void convertMaterialMapToList() {
		this.setMaterialList(this.materialMap.values());
	}

	public Collection<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(Collection<Material> materialList) {
		this.materialList = materialList;
	}
}