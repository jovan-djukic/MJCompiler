package optionDriver;

import com.beust.jcommander.IStringConverter;

import java.io.File;

public final class FileConverter implements IStringConverter<File> {
	
	@Override
	public File convert ( String s ) {
		return new File ( s );
	}
}


