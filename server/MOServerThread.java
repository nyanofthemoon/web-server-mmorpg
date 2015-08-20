import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class MOServerThread extends Thread {

private MOServer server;
private Socket socket;
private String name;

public MOServerThread(MOServer server, Socket socket) {
this.server = server;
this.socket = socket;
start();
} // END public MOServerThread(MOServer server, Socket socket)


public void run() {

while (true) {

try {
String clientMSG = "";
BufferedReader clientBRD = new BufferedReader(new InputStreamReader(socket.getInputStream()));

if (clientBRD.ready() == true) { clientMSG = clientBRD.readLine(); } else {}

if ((clientMSG = clientBRD.readLine()) == null) { } else {
System.out.println(clientMSG);

String[] MOActionArray = new String [10];
StringTokenizer rcvdtkns = new StringTokenizer(clientMSG,"|",false);
int rcvdtknssize = 0;

while (rcvdtkns.hasMoreTokens() && rcvdtknssize < MOActionArray.length) {
MOActionArray[rcvdtknssize] = rcvdtkns.nextToken();
rcvdtknssize = rcvdtknssize + 1;
}

if (clientMSG.equals("MOLOGOUT")) { server.RemoveClient(socket);
} else if (clientMSG.equals("ACTION")) { server.PrivateMsg(clientMSG, socket, MOActionArray[1]);
} else {}

}
} catch(Exception c) {
//server.RemoveClient(socket);
}
} // END WHILE
} // END public void run()



static public void main( String args[] ) { }

} // END CLASS MOServerThread
