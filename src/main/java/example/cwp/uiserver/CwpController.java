package example.cwp.uiserver;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CwpController {

	private Logger logger = LoggerFactory.getLogger(CwpController.class);
	
	@RequestMapping(value="/projects/{projectId}", method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@CrossOrigin
	public Collection<ConstructionWorkPacking> retrieveConstructionWorkPacking(@PathVariable(value="projectId") String projectId) {
		logger.debug("########### CwpController.retrieveConstructionWorkPacking is called with projectId {}.", projectId);
		return new CwpService().searchConstructionWorkPacking(projectId);
	}
	
	@RequestMapping(value="/projects/cwp-po-mapping/{projectId}", method=RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@CrossOrigin
	public Collection<ConstructionWorkPacking> searchCwpPoMappingResult(@PathVariable(value="projectId") String projectId)  {
		logger.debug("########### CwpController.searchCwpPoMappingResult is called with projectId {}.", projectId);
		return new CwpService().searchCwpPoMappingResult(projectId);
	}
}
