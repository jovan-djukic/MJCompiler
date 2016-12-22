package rs.ac.bg.etf.pp1.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLoggerFactory {
	private static String logFilenameProperty = "logFilename";
	private static String logFolder = "logs/";
	private static String separator = "-";
	
	public static Logger getLogger(Class<?> classObject) {
		String className = classObject.getSimpleName();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy|HH:mm:ss");
		String timestamp = dateFormat.format(new Date());
		
		System.setProperty(logFilenameProperty, logFolder + className + separator + timestamp);
		
		return LogManager.getLogger(classObject);
	}
}
