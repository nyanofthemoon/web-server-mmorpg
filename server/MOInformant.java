import java.io.*;import javax.servlet.*;import javax.servlet.http.*;import java.lang.String;import java.util.StringTokenizer;public class MOInformant extends HttpServlet { public void doPost(HttpServletRequest hinfreq, HttpServletResponse hinfres) throws ServletException, IOException {String RawInformantLegacy = "";String informantresponse = "";String nexthistoryline = "";String historyfile = "";String informantresarray[] = new String[ 80 ];int informantresarraysize = 0;String informantnextLine;try {ObjectInputStream moinform = new ObjectInputStream(hinfreq.getInputStream());RawInformantLegacy = (String)moinform.readObject();moinform.close();}catch (Exception MOInformExc) { return; }if (RawInformantLegacy.equals("null") || RawInformantLegacy.equals("") || RawInformantLegacy.equals("index.ssi")) { return; } else {}String[] informantsrvtks = new String[2];StringTokenizer informantsrvtkss = new StringTokenizer(RawInformantLegacy,"*",false);int informantsrvtkssize = 0;while (informantsrvtkss.hasMoreTokens()) {informantsrvtks[informantsrvtkssize] = informantsrvtkss.nextToken();informantsrvtkssize = informantsrvtkssize + 1; }if (informantsrvtks[1].equals("0")) {// Fetch Character Historyhistoryfile = "data/characters/" + informantsrvtks[0] + "/history";try { BufferedReader infbr = new BufferedReader(new FileReader(historyfile));	while ((nexthistoryline = infbr.readLine()) != null) {		informantresponse = informantresponse + nexthistoryline.toString() + "*"; 		}infbr.close();}catch ( IOException InfHist ) { informantresponse = "An Error has occured... Case A."; }try {hinfres.setContentType("text/plain");OutputStream infhist = hinfres.getOutputStream();infhist.write(informantresponse.getBytes());infhist.flush();infhist.close(); }catch (Exception InfHistb) { informantresponse = "An Error has occured... Case 0."; }                           } else if (informantsrvtks[1].equals("1") || informantsrvtks[1].equals("2") || informantsrvtks[1].equals("3") || informantsrvtks[1].equals("4") || informantsrvtks[1].equals("5") || informantsrvtks[1].equals("6")) {try { BufferedReader infstatusfilebr = new BufferedReader(new FileReader("data/characters/" + informantsrvtks[0] + "/status"));	while ((informantnextLine = infstatusfilebr.readLine()) != null) {		informantresponse = informantresponse + informantnextLine.toString() + "*";										                                    }    infstatusfilebr.close();} catch ( Exception InfStatusfleer ) { return; }} else if (informantsrvtks[1].equals("8") || informantsrvtks[1].equals("9") || informantsrvtks[1].equals("10") || informantsrvtks[1].equals("11") || informantsrvtks[1].equals("12")) {try { BufferedReader infstatusfilebr = new BufferedReader(new FileReader("data/characters/" + informantsrvtks[0] + "/stats"));	while ((informantnextLine = infstatusfilebr.readLine()) != null) {		informantresponse = informantresponse + informantnextLine.toString() + "*";										                                    }    infstatusfilebr.close();} catch ( Exception InfStatusfleer ) { return; }} else if (informantsrvtks[1].equals("7")) {// Fetch Alliesinformantresponse = "0*";} else { informantresponse = "An error has occured. Case 13."; }try {hinfres.setContentType("text/plain");OutputStream infgen = hinfres.getOutputStream();infgen.write(informantresponse.getBytes());infgen.flush();infgen.close();}catch (Exception InfHistc) { return; }                           return;}// DoGet() Method Errorpublic void doGet(HttpServletRequest gstreqb, HttpServletResponse gstresb)throws IOException, ServletException{ gstresb.sendRedirect("http://localhost/inexistent.html"); return; }}