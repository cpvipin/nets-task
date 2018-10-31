package eu.nets.portal.training.entity;

import java.util.List;
import java.util.Map;

public class ResponseDto {

	private String label;

	private List<ResponseDtoStat> statistics;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<ResponseDtoStat> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<ResponseDtoStat> statistics) {
		this.statistics = statistics;
	}

	public static class ResponseDtoStat {

		private String index;

		private String label;

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

}
