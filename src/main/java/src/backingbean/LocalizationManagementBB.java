package src.backingbean;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class LocalizationManagementBB implements Serializable {


	private Locale currentLocale;	
	private ResourceBundle props;
	private String propertiesPath;
	
	public String getString(String prop){
		return props.getString(prop);
	}
	
	@PostConstruct
	public void init(){
		propertiesPath = "bundle.bundleprops";
		currentLocale = Locale.getDefault();
		props = ResourceBundle.getBundle(propertiesPath, currentLocale);
	}
	


}
