package com.eeit162.FWBweb.hc.advertising.model.bean;

public class AdvertisementRender {

	private String url;

	private String img;

	private String errors;

	public AdvertisementRender(String url, String img, String errors) {
		this.url = url;
		this.img = img;
		this.errors = errors;
	}

	public AdvertisementRender() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

}
