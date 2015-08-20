import java.net.*;
import java.io.*;
import java.util.*;

public class MOServer {

private ServerSocket serverSOCKET = null;

private Hashtable myClientSocketsList = new Hashtable(); // name, socket
private Hashtable myClientWritersList = new Hashtable(); // socket, name
public boolean started = false;

public void RemoveClient(Socket dieName) {
synchronized (myClientWritersList) {
System.out.println( "Closing Connection for " + myClientWritersList.get(dieName) );
try { 
((Socket)myClientSocketsList.get(dieName)).close(); myClientSocketsList.remove(dieName); myClientWritersList.remove(myClientSocketsList.remove(dieName)); System.out.println("Closed Connection for " + dieName);
} catch (Exception d) { System.out.println("Close Connection Error for " + dieName + ": " + d); }
}
} // END public static void RemoveClient



public Enumeration ClientSocketsList() { return myClientSocketsList.elements(); }
public Enumeration ClientWritersList() { return myClientWritersList.elements(); }



public void PrivateMsg(String pmsg, Socket sendrsocket, String rcvername) {
synchronized(myClientSocketsList) {

try {
PrintWriter dout = new PrintWriter(new OutputStreamWriter(( (Socket)myClientSocketsList.get(rcvername) ).getOutputStream()));
dout.println(pmsg); dout.flush(); dout.close();
} catch( Exception f ) {}

} // END SYNCRONIZED
} // END void Broadcast(String msg)



// INITIALIZE SERVER ROUTINES
static public void main( String args[] ) throws Exception { 
System.out.println("-= Multi-Threaded Server =-");
System.out.println("Server Starting Up...");
int port = 8889; new MOServer(port); }


public MOServer(int port) throws IOException { listen(port); }
// END INITIALIZE SERVER ROUTINES


private void listen(int port) throws IOException {

if (started == true) { } else {
try { serverSOCKET = new ServerSocket(port);
started = true;
System.out.println("Server Listening on port " + port + " for incoming connections.\n");
} catch (Exception a) { System.out.println("Server Socket Listen Error: " + a); }}

while (true) {
try {
String clientNAME = "";

Socket clientSOCKET = serverSOCKET.accept();
DataOutputStream clientOUT = new DataOutputStream( clientSOCKET.getOutputStream() );

BufferedReader clientBRDNAME = new BufferedReader(new InputStreamReader(clientSOCKET.getInputStream()));
if (clientBRDNAME.ready() == true) { clientNAME = clientBRDNAME.readLine(); } else {}

myClientWritersList.put(clientSOCKET, clientNAME);
myClientSocketsList.put(clientNAME, clientSOCKET);
new MOServerThread(this, clientSOCKET);

PrintWriter fcout = new PrintWriter(new OutputStreamWriter(clientSOCKET.getOutputStream()));
fcout.println("CONNECTED"); fcout.flush(); fcout.close();

System.out.println("Connection Accepted for " + clientNAME);

} catch (Exception b) {}
// IF WHILE LISTENING == FALSE
continue;
} // END while (listening)
//try { serverSOCKET.close(); } catch (Exception g) { System.out.println("Closing Server Listen Socket: " + g); }
}  // END private void listen

}
