package kalyani.websitetest.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;

public class DataReader {
	
	public List<HashMap<String, String>> getjsonData() throws IOException
	{
		String jsoncontent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/kalyani/websitetest/data/data.json"),StandardCharsets.UTF_8);
		
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}

}
