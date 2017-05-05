package mx.com.anzen.genericbank.api;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import mx.com.anzen.mongo.services.MongoService;

import java.util.HashMap;
import java.util.Map;
   
@RestController
public class ServiceLayoutController {
	
	@Autowired
	private MongoService mongo;
	

private static Logger log = Logger.getLogger(ServiceLayoutController.class);
	   
	@RequestMapping(value="/Layout")
    public Map<String,Object> layout(@RequestBody JSONObject  json ) { 
		
		Map<String,Object> map=new HashMap();
		Map<String,Object> mapResult=null;
		try{
			map.put("fileDefinition.idFileType",json.get("IdFileType")); 
			mapResult=mongo.consulta("ADBancaGenerica",map); 
			  
		}catch (HttpMessageNotReadableException e) {
			System.out.println("exception");
			log.info("Error: "+e.getMessage());
			mapResult.put("CodigoError: ",100);
			mapResult.put("Error: ",e.getMessage()); 
			 
		}catch (Exception e) {
			log.info("Error: "+e.getMessage());
			mapResult.put("CodigoError: ",100);
			mapResult.put("Error: ",e.getMessage()); 
		}
		
        return mapResult;
    }
}
