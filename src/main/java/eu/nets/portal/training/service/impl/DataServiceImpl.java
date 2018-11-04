package eu.nets.portal.training.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.nets.portal.training.cache.CacheProvider;
import eu.nets.portal.training.entity.ResponseDto;
import eu.nets.portal.training.service.DataService;

@Service
@PropertySource(value = "classpath:application.properties")
public class DataServiceImpl implements DataService {

	@Value("${ssb.api.url}")
	private String ssbApiUrl;

	@Autowired
	private CacheProvider cacheProvider;

	public Map getSsbData() throws IOException {

		Map dataMap = cacheProvider.getDataMap();

		if (cacheProvider.isCacheValid()) {
			return dataMap;
		}

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(ssbApiUrl, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode rootNode = root.path("dataset").path("dimension");
		dataMap = formatSsbData(rootNode);
		cacheProvider.setDataMap(dataMap);
		cacheProvider.setLastAccessed(System.currentTimeMillis());
		return dataMap;
	}

	/**
	 * <p>
	 * Formatting the data set from api {@link DataServiceImpl#ssbApiUrl} to a form that can be directly repeat from UI.
	 * If the data is already cached it will return. This can be configured in application.properties
	 * {@link DataServiceImpl#isCacheEnabled}
	 * </p>
	 * 
	 * @param rootNode
	 * @return
	 * @throws IOException
	 */
	private Map formatSsbData(JsonNode rootNode) throws IOException {

		/**
		 * TODO Optimize the code for faster serializing
		 */
		Map dataMap = new HashMap();
		ObjectMapper mapper = new ObjectMapper();

		JsonNode indexNode = rootNode.path("Region").path("category").path("index");
		JsonNode labelNode = rootNode.path("Region").path("category").path("label");

		Map<String, Integer> indexMap = mapper.treeToValue(indexNode, Map.class);
		Map<String, String> labelmap = mapper.treeToValue(labelNode, Map.class);
		List regionStatList = new ArrayList();

		for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {

			String indexKey = entry.getKey();
			Integer indexVal = entry.getValue();
			String label = labelmap.get(indexKey);

			ResponseDto.ResponseDtoStat statistics = new ResponseDto.ResponseDtoStat();
			statistics.setIndex(indexKey);
			statistics.setLabel(label);
			regionStatList.add(statistics);
		}

		ResponseDto dto = new ResponseDto();
		dto.setLabel("Region");
		dto.setStatistics(regionStatList);
		dataMap.put("region", dto);

		List quarterList = new ArrayList();
		JsonNode quarterIndexNode = rootNode.path("Tid").path("category").path("index");
		JsonNode quarterLabelNode = rootNode.path("Tid").path("category").path("label");
		Map<String, Integer> quarterIndexMap = mapper.treeToValue(quarterIndexNode, Map.class);
		Map<String, String> quarterLabelmap = mapper.treeToValue(quarterLabelNode, Map.class);

		for (Map.Entry<String, Integer> entry : quarterIndexMap.entrySet()) {

			String indexKey = entry.getKey();
			Integer indexVal = entry.getValue();
			String label = quarterLabelmap.get(indexKey);

			ResponseDto.ResponseDtoStat statistics = new ResponseDto.ResponseDtoStat();
			statistics.setIndex(indexKey);
			statistics.setLabel(label);
			quarterList.add(statistics);
		}

		dto = new ResponseDto();
		dto.setLabel("Quarter");
		dto.setStatistics(quarterList);
		dataMap.put("Quarter", dto);

		List contentsList = new ArrayList();
		JsonNode contentIndexNode = rootNode.path("ContentsCode").path("category").path("index");
		JsonNode contentLabelNode = rootNode.path("ContentsCode").path("category").path("label");
		Map<String, Integer> contentIndexMap = mapper.treeToValue(contentIndexNode, Map.class);
		Map<String, String> contentLabelmap = mapper.treeToValue(contentLabelNode, Map.class);

		for (Map.Entry<String, Integer> entry : contentIndexMap.entrySet()) {

			String indexKey = entry.getKey();
			Integer indexVal = entry.getValue();
			String label = contentLabelmap.get(indexKey);

			ResponseDto.ResponseDtoStat statistics = new ResponseDto.ResponseDtoStat();
			statistics.setIndex(indexKey);
			statistics.setLabel(label);
			contentsList.add(statistics);
		}

		dto = new ResponseDto();
		dto.setLabel("Contents");
		dto.setStatistics(contentsList);
		dataMap.put("Contents", dto);

		return dataMap;

	}

	public String getSsbApiUrl() {
		return ssbApiUrl;
	}

	public void setSsbApiUrl(String ssbApiUrl) {
		this.ssbApiUrl = ssbApiUrl;
	}

	public CacheProvider getCacheProvider() {
		return cacheProvider;
	}

	public void setCacheProvider(CacheProvider cacheProvider) {
		this.cacheProvider = cacheProvider;
	}
}
