import java.io.*;import java.net.*;import javax.servlet.*;import javax.servlet.http.*;import java.util.Date;public class MOForgotPwd extends HttpServlet{   public void doPost (HttpServletRequest fpreq, HttpServletResponse fpres)    throws ServletException, IOException{String myfopwdusername = fpreq.getParameter("fopwdusername");// Read the file into rawfileString fpwdrawfilearray[] = new String[ 37 ];int fwdrawarraysize = 0;String fpwdfile = "data/players/" + myfopwdusername;String lineSep = System.getProperty("line.separator");String nextfpwdLine;if (myfopwdusername.length() < 3) {fpres.sendRedirect("http://localhost/forgotpwdmissingvalues.html");return;}// Possible Hole Checkchar[] fpwdunnarray = new char[myfopwdusername.length()];myfopwdusername.getChars( 0, myfopwdusername.length(), fpwdunnarray, 0);for ( int sizepwdun = 0; sizepwdun < myfopwdusername.length(); sizepwdun++ ) {if (Character.isLetterOrDigit(fpwdunnarray[sizepwdun]) == false) { fpres.sendRedirect("http:/localhost/forgotpwdwrongname.html"); return; }}try { BufferedReader fpbr = new BufferedReader(new FileReader(fpwdfile));    // Convert String rawfile into rawfilearray[]	while ((nextfpwdLine = fpbr.readLine()) != null) {		fpwdrawfilearray[fwdrawarraysize] = nextfpwdLine.toString();		fwdrawarraysize++;		}	  fpbr.close();	}	  catch ( IOException e ) {  // Returns IOException Errorfpres.sendRedirect("http://localhost/forgotpwdwrongname.html");return; }if (fpwdrawfilearray[1].length() < 1) { fpwdrawfilearray[1] = "No clue was provided at registration."; } else { }		ServletOutputStream fpwdout = fpres.getOutputStream();fpwdout.println("<html><head><title></title></head><body text=\"#dcdcdc\" background=\"http://localhost/Images/bkgnd.gif\"><center><font size=\"-2\" face=\"Verdana\"><br><br><img src=\"http://localhost/Images/pwdclue.gif\" border=\"0\"></font><font size=\"-2\" face=\"Verdana\"><br><br><br>Your Password Clue is: " + fpwdrawfilearray[1] + "</font></center></body></html>");fpwdout.close();return;}public void LogError( String plrerror, String myscrname ) throws IOException {String errorslog = "data/logs/Errors/";Date errosdte = new Date();int errmon = errosdte.getMonth();int errday = errosdte.getDate();int erryear = errosdte.getYear();int errhour = errosdte.getHours();int errmin = errosdte.getMinutes();int errsec = errosdte.getSeconds();errorslog = errorslog + erryear + "-" + errmon + "-" + errday + " at " + errhour + "H " + errmin + "M " + errsec + "S";PrintWriter errlogslog = new PrintWriter( new FileWriter(errorslog) );errlogslog.println( "<html><head></head><body bgcolor=black text=red><font size=-2 face=Verdana><center><br>Script Name: " + myscrname + "<br>Error: " + plrerror + "</font></center></body></html>");errlogslog.close();}// DoGet() Method Errorpublic void doGet(HttpServletRequest gfpreq, HttpServletResponse gfpres)throws IOException, ServletException{ gfpres.sendRedirect("http://localhost/inexistent.html"); return; }}