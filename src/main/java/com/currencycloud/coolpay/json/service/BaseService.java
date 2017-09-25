package com.currencycloud.coolpay.json.service;

import javax.faces.context.FacesContext;

/**
 * This <b>BaseService</b> provides the <i>URLs</i> for the several
 * environments, which are "Mock", "Production" and "Debugging Proxy".
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.1
 * @see LoginService
 * @see PaymentService
 * @see RecipientService
 */
public class BaseService {

	/**
	 * This constant defines the <b>MockEnv</b> in order to set up dynamically the
	 * system.
	 */
	public static final String APP_MOCK = "MockEnv";

	/**
	 * This constant defines the <b>DebuggingProxyEnv</b> in order to set up
	 * dynamically the system.
	 */
	public static final String APP_DEBUGGING_PROXY = "DebuggingProxyEnv";

	/**
	 * This constant defines the <b>ProductionEnv</b> in order to set up dynamically
	 * the system.
	 */
	public static final String APP_PRODUCTION = "ProductionEnv";

	/**
	 * Variable which defines the <i>MockEnv</i> base url.
	 */
	private String mockEnvBaseUrl = "https://private-anon-0093f2d130-coolpayapi.apiary-mock.com/api";

	/**
	 * Variable which defines the <i>DebuggingProxyEnv</i> base url.
	 */
	private String debuggingProxyBaseUrl = "https://private-anon-0093f2d130-coolpayapi.apiary-proxy.com/api";

	/**
	 * Variable which defines the <i>ProductionEnv</i> base url.
	 */
	private String productionBaseUrl = "https://coolpay.herokuapp.com/api";

	/**
	 * Here is defined in which ambient the system is running
	 */
	private String appMode = this.getServerEnvironment();

	/**
	 * This method returns the "URLs" for the <i>login</i> process
	 * 
	 * @return String
	 */
	protected String getBaseUrl() {
		String result = null;
		switch (appMode) {
		case BaseService.APP_PRODUCTION:
			result = productionBaseUrl;
			break;
		case BaseService.APP_MOCK:
			result = mockEnvBaseUrl;
			break;
		case BaseService.APP_DEBUGGING_PROXY:
			result = debuggingProxyBaseUrl;
			break;
		default:
			result = "";
			break;
		}
		return result;
	}

	/**
	 * This method build up the string in order to authenticate the requisitions on
	 * the system.
	 * 
	 * @param token
	 * @return String
	 */
	protected String getBearerAuthenticator(String token) {
		StringBuilder sb = new StringBuilder();
		sb.append("Bearer ");
		sb.append(token);
		return sb.toString();
	}

	/**
	 * In order to define dynamically, through "context-param" in the web.xml file,
	 * which environment the system is operating, this method was created.
	 * 
	 * @return String
	 */
	private String getServerEnvironment() {
		String result = BaseService.APP_DEBUGGING_PROXY;
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			String serverEnvironment = ctx.getExternalContext().getInitParameter("ServerEnvironment");
			if (serverEnvironment != null && serverEnvironment != "") {
				result = serverEnvironment;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}