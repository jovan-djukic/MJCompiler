package rs.ac.bg.etf.pp1;

import org.apache.logging.log4j.Logger;
import rs.ac.bg.etf.pp1.utilities.MyLoggerFactory;

public class LoggerTest {
	
	private static Logger logger = MyLoggerFactory.getLogger(LoggerTest.class);
	
	public static void main(String[] args) {
		logger.info("HELLO");
	}

}