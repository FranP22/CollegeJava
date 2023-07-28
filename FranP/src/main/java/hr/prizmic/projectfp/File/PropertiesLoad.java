package hr.prizmic.projectfp.File;

import hr.prizmic.projectfp.Exceptions.FileReadException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoad{
    private static final String PROPERTIES_FILE = "dat/data.properties";

    public Properties getProperties() throws FileReadException {
        try{
            Properties prop = new Properties();
            prop.load(new FileReader(PROPERTIES_FILE));

            return prop;
        }
        catch (IOException e){
            String msg = "Couldn't load properties";
            throw new FileReadException(msg, e);
        }
    }
}
