package Propertymethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class DatafromPropertyFile {
	static Properties p=new Properties();
public static String ReadfromPropertyfile(String file,String element) throws FileNotFoundException, IOException{
p.load(new FileReader(file));
    return 	p.getProperty(element);
    }
public static String WritetoPropertyfile(String file,String key,String element) throws FileNotFoundException, IOException{

	//For adding new elements
	p.load(new FileReader(file));
	p.setProperty(key,element);
	p.store(new FileWriter("Login.properties"),"Adding new elements");
	return 	p.getProperty(element);
    }

}
