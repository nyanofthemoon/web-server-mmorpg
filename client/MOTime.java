import java.awt.*;
import java.applet.Applet;
import java.lang.String;

public class MOTime extends java.applet.Applet implements Runnable {

protected Thread MOTimeThread = null;
protected Font font = new Font("Verdana", Font.PLAIN, 15);
protected Color color = Color.white;
  
String daystate[] = {"Midnight", "Night", "Night", "Night", "Night", "Dawn", "Dawn", "Dawn", "Morning", "Morning", "Morning", "Morning", "Noon", "Day", "Day", "Day", "Day", "Dusk", "Dusk", "Evening", "Evening", "Evening", "Night", "Night"};
String mohour, moday, mominute, mosecond, moseason, day, myear = "";
int hour, minute, second, mtime, mtimeday, mday, mhour, mmin, msec = 0;

public void init() {
setLayout( new FlowLayout( FlowLayout.CENTER, 1, 1 ) );
setBackground(new Color(0,0,0));

hour = Integer.parseInt(getParameter("hour"));
minute = Integer.parseInt(getParameter("minute"));
second = Integer.parseInt(getParameter("second")); 
day = getParameter("day");
myear = getParameter("year");
 
mtime = 12 * ( hour * 3600 + minute * 60 + second );
mtimeday = mtime / 3600;
mday = ( (mtimeday / 24) + Integer.parseInt(day) );

mhour = mtimeday % 24;
mmin = (mtime % 3600) / 60;
msec = (mtime % 3600) % 60;
}

public void start() {
if (MOTimeThread == null) {   
MOTimeThread = new Thread(this);
MOTimeThread.start(); }
}

public void stop() {
MOTimeThread = null;
destroy();
}

public void run() {
while (Thread.currentThread()  ==  MOTimeThread) {
repaint();
try {
Thread.currentThread().sleep(1000);
} catch (InterruptedException e) {} }
}

public void paint(Graphics t) {
msec = msec + 12;
        
if (msec >= 60) { mmin = mmin + 1; msec = msec - 60; }
if (mmin >= 60) { mhour = mhour + 1; mmin = mmin - 60; }
if (mhour >= 24) { mday = mday + 1; mhour = mhour - 24; }
if (mday >= 361) { myear = myear + 1; mday = mday - 360; }
  
if (mday <= 360) { moseason = "King"; }
if (mday <= 301) { moseason = "Shield"; }
if (mday <= 241) { moseason = "Dead"; }
if (mday <= 181) { moseason = "Dragon"; }
if (mday <= 121) { moseason = "Wizard"; }
if (mday <= 61) { moseason = "Sword"; }
         
t.setFont(font);
t.setColor(color);
    
moday = "" + mday;
    
if (mhour < 10) { mohour = "0" + mhour; } else { mohour = "" + mhour; }
if (mmin < 10) { mominute = "0" + mmin; } else { mominute = "" + mmin; }
if (msec < 10) { mosecond = "0" + msec; } else { mosecond = "" + msec; }
       
t.drawString(mohour + ":" + mominute + ":" + mosecond + " - " + daystate[mhour] + ", on day " + moday + " of the year " + myear + " <Season of the " + moseason + ">", 5, 12);
  }

public String getAppletInfo() {
return "";
}

}
