import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;





public class UI extends JFrame  {

	
	//======= Frame variables 
	private static JPanel panel = new JPanel();
	private Border panelTitle = BorderFactory.createTitledBorder("Weather information");
	private static JTextArea weather = new JTextArea();
	private static String weatherinfo;
	private static JTextField test = new JTextField();
	private String APIkey = "JMS0hwr0QDXRxS5bqbHnJ6dn9g9O27xe9aMgdv4Lmnk";
	

	public UI(){
		super ("Weather information");
		//======================================================
		// UI Frame
		JFrame frame = new JFrame();
		frame.setSize(500,300);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		panel.setBorder(panelTitle);
		frame.setLayout(new FlowLayout());
		

		
		
		

		HttpURLConnection connection = null;
			

		BufferedReader br; 
		String line; 
		StringBuffer responseContent = new StringBuffer();
			  
			  try 
			  { 
				  
				  URL url = new URL("https://weather.ls.hereapi.com/weather/1.0/report.json?apiKey=" + APIkey + "&product=observation&name=London");
				  connection = (HttpURLConnection) url.openConnection(); //open connection to end point
				  	
				  
				  //Request Setup 
				  connection.setRequestMethod("GET"); 
				  connection.setConnectTimeout(5000); 
				  connection.setReadTimeout(5000);
				  
				  //Response 
				  int status = connection.getResponseCode();
				  System.out.println(status);
				  
				  if (status > 200) { // error output
					  br = new BufferedReader(new InputStreamReader(connection.getErrorStream())); 
					  while((line = br.readLine())!= null)
					  { 
						  responseContent.append(line); 
					  } 
					  br.close(); 
				  } 
				  else // success output
				  {
					 br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					 while((line = br.readLine()) != null) 
					 { 
						 responseContent.append(line); 
					 } 
				  }
				  br.close();
				  
				  //System.out.println(responseContent.toString()); 
				 parse(responseContent.toString());
			  
			  } 
			  
			  catch(MalformedURLException e) 
			  { 
				  e.printStackTrace(); 
			  } 
			  
			  catch (IOException e1) 
			  {
				  e1.printStackTrace();  
			  } 
			  
			  finally 
			  { 
				  connection.disconnect();
			
			  }
			  

		
				//===== info text area

				weather.append(weatherinfo);
				panel.add(weather);
				weather.setEditable(false);
			
		}
	
	
			 
			
			















		private static String parse(String responseBody) {

			
			  Gson gson = new GsonBuilder()
					  .setPrettyPrinting()
					  .create();
			  
			  JsonParser jp = new JsonParser(); 
			  JsonElement je = jp.parse(responseBody); //Reads the JSON as stream of tokens using JsonReader and returns the next value from the JSON stream as a parse tree.
			  String prettyJsonString = gson.toJson(je);
			  //System.out.println(prettyJsonString);
			  String [] array = prettyJsonString.split(",");
			  //System.out.println(Arrays.toString(array));
			  //for(int i = 0; i < array.length; i++)
			  //{
				  //System.out.println(array[i]);
				  //System.out.println(i);
				  
			  //}
			  
			  String country = (array[32]); 
			  String state = (array[33]);
			  String city = (array[34]); 
			  String temperature = (array[4]);
			  String highTemp = (array[7]);
			  String lowTemp = (array[8]);
			  String description = (array[48]);
			  
			  weatherinfo = country + state + city + temperature + highTemp + lowTemp + description;
			
			  
			  
			  return null;
			 
		}

		


		

}
	


		
