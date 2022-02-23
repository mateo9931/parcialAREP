package parcialtercio.HttpServer;

import java.net.*;
import java.io.*;

public class HttpServer {
  public static void main(String[] args) throws IOException {
   ServerSocket serverSocket = null;
   try { 
      serverSocket = new ServerSocket(36000);
   } catch (IOException e) {
      System.err.println("Could not listen on port: 35000.");
      System.exit(1);
   }

   Socket clientSocket = null;
   try {
       System.out.println("Listo para recibir ...");
       clientSocket = serverSocket.accept();
   } catch (IOException e) {
       System.err.println("Accept failed.");
       System.exit(1);
   }
  
   PrintWriter out = new PrintWriter(
                         clientSocket.getOutputStream(), true);
   BufferedReader in = new BufferedReader(
                         new InputStreamReader(clientSocket.getInputStream()));
   String inputLine, outputLine;
   while ((inputLine = in.readLine()) != null) {
      System.out.println("Recibí: " + inputLine);
      if (!in.ready()) {break; }
   }
   outputLine = 
		   "HTTP/1.1 200 OK\r\n" 
					+"Content-Type: text/html\r\n" 
					+ "\r\n" 
					+ "<!DOCTYPE html>" 
					+ "<html>"
					+ "<head>"
					+ "<meta charset=\"UTF-8\">"
					+ "<title>Title of the document</title>\n"
					+ "</head>"+
					"<div>" +
                    "<h2>OBTEN EL CLIMA DADA LA CIUDAD QUE INGRESES</h2>" +
                    "</div>"+
                    "<form action=\"/consulta\" method=\"get\">" +
                    "<div>" +
                    " <input required name=\"ciudad\" id=\"datos\" value=\"\">" +
                    "</div>" +
                    "</br>"+
                    "<button onclick=\"getApiInfo()\">OBTENER</button>" +
                    "</form>"+
                    "</body>"+
                    "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\" type=\"text/javascript\"></script>"+
                    "<script>"+
   "function getApiInfo(){\n"
   + "		var city = $(\"#datos\").val();\n"
   + "		$.ajax({\n"
   + "			url: \"api.openweathermap.org/data/2.5/weather?q=\"+city+\"&appid=bb172fdb23943aab393ccec6c291b430\",\n"
   + "			method: \"GET\",\n"
   + "			success: function(response) { \n"
   + "				console.log(response);  \n"
   + "				var e = JSON.parse(response);\n"
   + "			}\n"
   + "		}); \n"
   + "	}"+
   					"</script>"+
   
					"</html>"; 
    out.println(outputLine);
    out.close(); 
    in.close(); 
    clientSocket.close(); 
    serverSocket.close(); 
    }
  }
