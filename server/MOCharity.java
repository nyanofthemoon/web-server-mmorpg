import java.io.*;import javax.servlet.*;import javax.servlet.http.*;import java.lang.String;import java.util.StringTokenizer;public class MOCharity extends HttpServlet { public void doPost(HttpServletRequest chrtreq, HttpServletResponse chrtes) throws ServletException, IOException {String charityact = "";String locmoney = "";String CHnextLine = "";String chreply = "";String CharityLegacy = "";try {ObjectInputStream oischarity = new ObjectInputStream(chrtreq.getInputStream());CharityLegacy = (String)oischarity.readObject();oischarity.close();} catch (Exception CharityException) {}if (CharityLegacy.equals("null") || CharityLegacy.equals("") || CharityLegacy.equals("index.ssi")) { return; } else {}String[] charityrvtks = new String[5];StringTokenizer charityrvtkss = new StringTokenizer(CharityLegacy,"*",false);int charityrvtkssize = 0;while (charityrvtkss.hasMoreTokens()) {charityrvtks[charityrvtkssize] = charityrvtkss.nextToken();charityrvtkssize = charityrvtkssize + 1; }String chlocationfile = "data/charity/" + charityrvtks[1] + "/" + charityrvtks[2] + "/" + charityrvtks[3];try { BufferedReader chlocationbr = new BufferedReader(new FileReader(chlocationfile));	while ((CHnextLine = chlocationbr.readLine()) != null) {		locmoney = CHnextLine.toString(); }    chlocationbr.close();} catch ( Exception readloc ) { return; }if (charityrvtks[0].equals("get")) {chreply = locmoney;} else if (charityrvtks[0].equals("beg")) {// BEG - REMOVE FROM LOCATIONlocmoney = new Integer((Integer.parseInt(locmoney) - Integer.parseInt(charityrvtks[4]))).toString();if (Integer.parseInt(locmoney) < 0) { locmoney = "0"; } else {}chreply = locmoney;PrintWriter chbeg = new PrintWriter( new FileWriter(chlocationfile) );chbeg.println( locmoney );chbeg.close();} else { // DONATE - ADD TO LOCATIONlocmoney = new Integer((Integer.parseInt(locmoney) + Integer.parseInt(charityrvtks[4]))).toString();if (Integer.parseInt(locmoney) < 0) { locmoney = "0"; } else {}chreply = locmoney;PrintWriter chdon = new PrintWriter( new FileWriter(chlocationfile));chdon.println( locmoney );chdon.close();}try {chrtes.setContentType("text/plain");OutputStream chos = chrtes.getOutputStream();chos.write(chreply.getBytes());chos.flush();chos.close();}catch (Exception CHSend) { return; }return;}// DoGet() Method Errorpublic void doGet(HttpServletRequest chrtbreq, HttpServletResponse chrtbres)throws IOException, ServletException{ chrtbres.sendRedirect("http://localhost/inexistent.html"); return; }}