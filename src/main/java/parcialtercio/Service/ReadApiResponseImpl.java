package parcialtercio.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadApiResponseImpl implements ReadApiResponse {
	
	@Override
	public StringBuilder ObtainWeather(String city) throws MalformedURLException {
	StringBuilder sb = new StringBuilder();
	URL site = new URL("api.openweathermap.org/data/2.5/weather?q="+city+"&appid=bb172fdb23943aab393ccec6c291b430");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(site.openStream()))) {
        String inputLine = null;
        
        while ((inputLine = reader.readLine()) != null) {
        	sb.append(inputLine);
        }
       
    } catch (IOException x) {
        System.err.println(x);
    }
	return sb;

}
	
}
