package com.currencycloud.coolpay.model;

public class ResponseService {

	private int status;

	private String header;

	private String body;

	public ResponseService() {
		super();
	}

	public ResponseService(int status, String header, String body) {
		super();
		this.status = status;
		this.header = header;
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}