package eu.nets.portal.training.rest;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import eu.nets.portal.training.service.DataService;

@RestController
@RequestMapping("/rest")
public class StartController {

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/greeting", produces = "text/plain")
	public ResponseEntity<String> index() {
		return ResponseEntity.ok("Greetings from Spring Boot!");
	}

	/**
	 * <p>
	 * Load formatted SSB data from 
	 * <see>eu.nets.portal.training.service.impl.DataServiceImpl.class</see>
	 * </p>
	 * 
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/ssbData",method=RequestMethod.GET)
	public ResponseEntity loadSsbData() {
		Map dataMap = null;
		try {
			dataMap = dataService.getSsbData();
		}
		catch (Exception e) { 
			//TODO Create custom exception class and catch specific exceptions. 
			//Log error using logger
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity(dataMap, HttpStatus.OK);
	}

}
