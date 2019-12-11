package com.finexus.automation.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "label", "data", "color" })
public class DonutDataModel implements Serializable {

	@JsonProperty("label")
	private String label;

	@JsonProperty("data")
	private Integer data;

	@JsonProperty("color")
	private String color;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	private final static long serialVersionUID = 9125513571926332047L;

	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	@JsonProperty("label")
	public void setLabel(String label) {
		this.label = label;
	}

	@JsonProperty("data")
	public Integer getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(Integer data) {
		this.data = data;
	}

	@JsonProperty("color")
	public String getColor() {
		return color;
	}

	@JsonProperty("color")
	public void setColor(String color) {
		this.color = color;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}