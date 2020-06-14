package telran.net;

import java.io.Serializable;

public class RequestJava implements Serializable {

	public RequestJava(String requestType, Serializable requestData) {
		super();
		this.requestType = requestType;
		this.requestData = requestData;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String requestType;
	public Serializable requestData;
	

}
