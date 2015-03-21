package demo.com.esic.logging;

import org.apache.log4j.Logger;

public class LoggingDemo {

	
	final static Logger logger = Logger
			.getLogger(LoggingDemo.class);

	
	
	
	public static void main(String[] args) {
		logger.info("Hello");
	}
	
}
