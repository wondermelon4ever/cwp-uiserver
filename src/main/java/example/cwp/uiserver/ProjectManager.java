package example.cwp.uiserver;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;

public class ProjectManager {
	
	private static String[][] cwpList = new String[][] {
		new String[] { "CWA041-H0-ASP-L01", "CWA041-H0-ASP-L01", "A0041", "B001", "20200713", "20200719", "7", "0", "null"},
		new String[] { "CWA041-H0-ASP-S01", "CWA041-H0-ASP-S01", "A0041", "B001", "20200720", "20200724", "5", "0", "CWA041-H0-ASP-L01"},
		new String[] { "CWA042-H0-ASP-L01", "CWA042-H0-ASP-L01", "A0042", "B002", "20200720", "20200726", "7", "0", "CWA041-H0-ASP-L01"},
		new String[] { "CWA047-H0-AFD-L01", "CWA047-H0-AFD-L01", "A0047", "B004", "20200725", "20200728", "6", "0", "CWA041-H0-ASP-S01"},
		new String[] { "CWA047-H0-ASP-L01", "CWA047-H0-ASP-L01", "A0047", "B003", "20200729", "20200802", "5", "0", "CWA047-H0-AFD-L01"},
		new String[] { "CWA047-H0-ASP-S01", "CWA047-H0-ASP-S01", "A0047", "B003", "20200727", "20200731", "5", "0", "CWA042-H0-ASP-L01"},
		new String[] { "CWA047-H0-ASP-T01", "CWA047-H0-ASP-T01", "A0047", "B004", "20200803", "20200809", "7", "0", "CWA047-H0-AFD-L01, CWA047-H0-ASP-S01"}
	};
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private static ProjectManager manager;
	
	private Map<String, Project> projectMap;
	
	private ProjectManager() {
		projectMap = new HashMap<>();
	}
	
	public static ProjectManager getManager() {
		if(manager == null) {
			manager = new ProjectManager();
		}
		return manager;
	}
	
	public void addProject(Project project) {
		this.projectMap.put(project.getProjectCode(), project);
	}
	
	public Project retrieveProject(String projectId) {
		return this.projectMap.get(projectId);
	}
	
	public void loadProjects() {
		// we have only on project
		Project project = new Project();
		project.setProjectCode("PJT-2020072201");
		project.setProjectName("Sample Project");
		
		// we have only 7 cwpes
		for(int i = 0; i < 7; i++) {
			String[] cwpInfo = cwpList[i];
			
			ConstructionWorkPacking cwp = new ConstructionWorkPacking();
			cwp.setProjectCode(project.getProjectCode());
			cwp.setCwpCode(cwpInfo[0]);
			cwp.setCwpName(cwpInfo[1]);
			cwp.setAreaCode(cwpInfo[2]);
			cwp.setBookCode(cwpInfo[3]);
			try {
				cwp.setStartDate(sdf.parse(cwpInfo[4]));
				cwp.setEndDate(sdf.parse(cwpInfo[5]));
			} catch (ParseException e) {
				cwp.setStartDate(null);
				cwp.setEndDate(null);
			}
			
			cwp.setDuration(Integer.parseInt(cwpInfo[6]));
			cwp.setPercentCompleted(0f);
			
			String[] dependencies = cwpInfo[8].split(",");
			for(int j = 0; j < dependencies.length; j++) cwp.addDependency(dependencies[j]);
			
			project.putConstructionWorkPacking(cwp.getCwpCode(), cwp);
		}
		
		this.loadCwpMaterials(project);
		this.projectMap.put(project.getProjectCode(), project);
	}
	
	private void loadCwpMaterials(Project project) {
		String filePath = "D:\\Works\\cwp_materials.csv";
		
		String [] nextLine;
		try {
			CSVReader reader = new CSVReader(new FileReader(filePath));
			// skip header
			reader.readNext();
			
			while ((nextLine = reader.readNext()) != null) {
				String cwpCode = null, materialCode = null, rosDate = null; int quantity = 0;
				
				for (int index = 0; index < nextLine.length; index++) {
	            	if(index < 2 || index > 8) continue;
	            	
	            	switch(index) { 
					case Constants.INDEX_PJT_CODE: 
						break; 
					case Constants.INDEX_AREA_CODE:
						break; 
					case Constants.INDEX_BOOK_CODE:
						break;
					case Constants.INDEX_WCP_CODE:
						cwpCode = nextLine[index].trim();
						break;
					case Constants.INDEX_MATERIALS_CODE:
						materialCode = nextLine[index].trim();
						break;
					case Constants.INDEX_NET_QUANTITY:
						quantity = Integer.parseInt(nextLine[index].trim());
						break;
					case Constants.INDEX_ROS_DATE:
						rosDate = nextLine[index].trim();
						break;
					default:
						// Nothing to do...
					}
	            }
				Material material = new Material();
				material.setMaterialCode(materialCode);
				material.setQuantity(quantity);
				material.setRosDate(sdf.parse(rosDate));
				material.setChecked(false);
				
				ConstructionWorkPacking cwp = project.getConstructionWorkPacking(cwpCode);
				cwp.addMaterial(material);
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}