package example.cwp.uiserver;

import java.util.HashMap;
import java.util.Map;

public class Project {

	private String projectCode;
	
	private String projectName;
	
	private Map<String, ConstructionWorkPacking> cwpMap;
	
	public Project() {
		cwpMap = new HashMap<>();
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Map<String, ConstructionWorkPacking> getCwpMap() {
		return cwpMap;
	}

	public void setCwpList(Map<String, ConstructionWorkPacking> cwpMap) {
		this.cwpMap = cwpMap;
	}
	
	public void putConstructionWorkPacking(String cwpCode, ConstructionWorkPacking cwp) {
		this.cwpMap.put(cwpCode, cwp);
	}
	
	public ConstructionWorkPacking getConstructionWorkPacking(String cwpCode) {
		return this.cwpMap.get(cwpCode);
	}
}