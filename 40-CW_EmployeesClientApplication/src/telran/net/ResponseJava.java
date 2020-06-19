package telran.net;

import java.io.Serializable;

public class ResponseJava implements Serializable {

	public ResponseJava(TcpResponseCode code, Serializable responseData) {
		this.code = code;
		this.responseData = responseData;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TcpResponseCode code;
	public Serializable responseData;
	

}


