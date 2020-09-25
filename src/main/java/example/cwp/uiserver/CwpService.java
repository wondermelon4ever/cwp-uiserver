package example.cwp.uiserver;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CwpService {
	
	private Logger logger = LoggerFactory.getLogger(CwpService.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public Collection<ConstructionWorkPacking> searchConstructionWorkPacking(String projectId) {
		logger.debug("########### CwpService.searchConstructionWorkPacking is called with projectId {}.", projectId);
		
		return ProjectManager.getManager().retrieveProject(projectId).getCwpMap().values();
	}
	
	public Collection<ConstructionWorkPacking> searchCwpPoMappingResult(String projectId)  {
		logger.debug("########### CwpService.searchCwpPoMappingResult is called with projectId {}.", projectId);

		String filePath = "D:\\mapping-result.csv";
		String [] nextLine;
		
		try {
			CSVReader reader = new CSVReader(new FileReader(filePath));
			reader.readNext(); // skip header
			
			while ((nextLine = reader.readNext()) != null) {
				String projectCode = null, cwpCode = null, materialCode = null, rosDate = null, poNumber = null, vendorName = null, etaDate = null;
				int poQuantity = 0;
				
				for (int index = 0; index < nextLine.length; index++) {
					
					switch(index) { 
					case CwpPoMappingInfo.INDEX_PROJECT_CODE:
						projectCode = nextLine[index].trim();
						break;
					case CwpPoMappingInfo.INDEX_CWP_CODE:
						cwpCode = nextLine[index].trim();
						break;
					case CwpPoMappingInfo.INDEX_MATERIAL_CODE:
						materialCode = nextLine[index].trim();
						break;
					case CwpPoMappingInfo.INDEX_NET_QUANTITY:
						break;
					case CwpPoMappingInfo.INDEX_ROS_DATE:
						rosDate = nextLine[index].trim();
						break;
					case CwpPoMappingInfo.INDEX_PO_NUMBER:
						poNumber = nextLine[index].trim();
						break;
					case CwpPoMappingInfo.INDEX_VENDOR_NAME:
						vendorName = nextLine[index].trim();
						break;
					case CwpPoMappingInfo.INDEX_PO_QUANTITY:
						poQuantity = Integer.parseInt(nextLine[index].trim());
						break;
					case CwpPoMappingInfo.INDEX_ETA_DATE:
						etaDate = nextLine[index].trim();
						break;						
					default:
						continue;
					}
				}
				
				Project project = ProjectManager.getManager().retrieveProject(projectCode);
				ConstructionWorkPacking cwp = project.getConstructionWorkPacking(cwpCode);
				
				if(cwp == null) continue;
				Material material = cwp.getMaterial(materialCode, rosDate);
				if(material == null) continue;
				
				material.setMaterialCode(materialCode);
				material.setPoNumber(poNumber);
				material.setPoQuantity(poQuantity);
				material.setVendorName(vendorName);
				
				try {
					material.setEtaDate(sdf.parse(etaDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				if(material.getQuantity() == material.getPoQuantity()) material.setChecked(true);
			}
			
			Project project = ProjectManager.getManager().retrieveProject(projectId);
			project.getCwpMap().forEach((cwpCode, cwp)->{
				cwp.convertMaterialMapToList();
			});
			
			reader.close();
		} catch (CsvValidationException | IOException e) {
			e.printStackTrace();
			return null;
		} 
		
		return ProjectManager.getManager().retrieveProject(projectId).getCwpMap().values();
	}
}