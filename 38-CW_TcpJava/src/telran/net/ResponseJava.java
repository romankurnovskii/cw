package telran.net;

import java.io.Serializable;

public class ResponseJava implements Serializable {

	public ResponseJava(TcpResponseCode code, Serializable responseData) {
		super();
		this.code = code;
		this.responseData = responseData;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5494414969946137865L;

	public TcpResponseCode code;
	public Serializable responseData;
	
	
}
