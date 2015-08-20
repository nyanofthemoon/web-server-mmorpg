import java.applet.Applet;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.ObjectOutputStream;
import java.io.DataInputStream;
import java.lang.String;
import java.util.StringTokenizer;
import netscape.javascript.JSObject;
import netscape.javascript.JSException;
               
public class MOMain extends java.applet.Applet {

public String res, maxres, mag, maxmag, mor, maxmor, fame, money = "loading...";

private JSObject basewindow, basedocument;
private String connectedstatus, myCharacter, myAccount, mybrowser, myplatform;
private String[] CharacterLegacy = new String [250];
private String[] RawTimeArray = new String [7];
private String[] LocationVars = new String [35];
private String[] CharacterStatus = new String [23];
private String[] CharacterStats = new String [70];
private String[] CharacterMoney = new String [27];
private String[] CharacterCoches = new String [13];
private int earnedmoney, receivedmoney, totalmoney;

private String[] Sluggish = {" ", "sad", "depressed", "weak", "weaker", "weakest"};
private String[] Tired = {" ", "tired", "drowsy", "sleepy", "asleep", "sleeping"};
private String[] Paralyzed = {" ", "partially paralyzed", "halfly paralyzed", "almost paralyzed", "paralyzed", "fully paralyzed"};
private String[] Blind = {" ", "shaded", "partially blinded", "halfly blinded", "almost blinded", "blinded"};
private String[] Poisoned = {" ", "lightly poisoned", "poisoned", "heavily poisoned", "severely poisoned", "mortally poisoned"};
private String[] Health = {" ", "not well", "sick", "ill", "very ill", "dying"};
private String[] Title = {"Outsider", "Peasant", "Citizen", "Trial", "Beginner", "Apprentice", "Novice", "Traveller", "Explorer", "Adventurer", "Professional", "Specialist", "Expert", "Champion", "Master", "Prodigy", "Idol", "Hero", "Legend"};
private String[] MODayState = {"Midnight", "Night", "Night", "Night", "Night", "Dawn", "Dawn", "Dawn", "Morning", "Morning", "Morning", "Morning", "Noon", "Day", "Day", "Day", "Day", "Dusk", "Dusk", "Evening", "Evening", "Evening", "Night", "Night"};
private String[] npcNames = {"Dark Eagle", "Kracht", "Shadow", "Kholonor", "Minion", "Ace", "Wind", "One Eye", "Bloody Hands", "Gork", "Nascix", "Klyn", "Vulture", "Eye of Lizard", "Dark Hand", "Diurn", "Narcim", "Karth", "Jade", "Gold Pouch", "Ruby Hand", "Darlek", "Dariel", "Zigtan", "Frock", "Zelbenghim", "Nola", "Nohan", "Phyto", "Rendrick", "Vwralak", "Kassyah", "Sariel", "Xheleandyr", "Korin", "Nix", "Edward", "Joseph", "Aika", "Myrrhe", "Deus", "Diana", "Alicia", "Throk", "Nagalhet", "Fuwys", "Narh", "Bolston", "Buyk", "Maahster"};
private String[] npcGreetingsBAD = {"I am the best you can get.", "I work fast.", "What kind of dirty work can you not do by yourself?", "Be quick.", "Hmm?", "What do you want from me?", "You better not be waisting my time.", "State your business with me.", "You better have a lot of money.", "What?", "Pay me and I will be loyal.", "What do you want?", "This has better be important.", "Waste my time and I will slay you myself.", "I hope you are not planning on staying near me too long.", "Well, well, well... ", "Who do you wish to harm today?", "I am in the illegal business.", "I have a deal just for you..."};
private String[] npcGreetingsGOOD = {"What task am I required for?", "What do you need accomplished?", "What is your request?", "Hmm?", "What do you need?", "How can I be of service?", "Hello. How can I help you?", "Can I help you?", "May I be of assistance?", "Do you require my assistance?", "Can I help you with something?", "Do you require any of my services?", "Yes?"};

private String fontset = "<font size=-2 face=Verdana>";
private String divc = "<div align=center>";
private String tbr = "<br><br><br>";
private String tbcc = "<table border=0 cellpadding=4 cellspacing=0>";
private String imgurl = "http://localhost/Images/";
private String nomon = "<b>You do not have enough money to pay for this</b>";
private String anerr = "<b>And error has occured... please try again.</b>";
private String starthtml = "<html><head><title></title></head><body text=white background=" + imgurl + "mobkgnd.jpg oncontextmenu=return false;>";
private String endhtml = "</body></html>";
private String closewindow = "<center><a href=javascript:self.close()><img src=" + imgurl + "closewindow.gif border=0></a></center>";

private int[] FameRequired = {250, 1000, 4000, 1500, 1500, 3000, 6000, 12000, 24000, 48000, 96000, 192000, 384000, 768000, 1536000, 3072000, 6144000, 12288000, 24576000};
private int[] InformantCost = {1, 50, 5, 10, 100, 75, 3, 25, 5000, 7500, 5000, 7500, 1000};
private int[] SageCost = {2, 2, 2, 2, 100, 100, 150, 100, 100, 100, 150, 100, 500, 500};
private int[] InnMeals = {25, 50, 100, 250, 500, 2500, 5000};
private int[] InnMor = {4, 5, 5, 8, 10, 25, 50};
private int[] InnMorMin = {1, 2, 5, 7, 10, 25, 50};
private int[] TavMeals = {15, 50, 175, 100, 500, 2500};
private int[] TavMor = {5, 10, 30, 10, 10, 25};
private int[] TavMorMin = {5, 10, 15, 5, 10, 25};

// FOR WANDERING FOES VARIABLES //
private String[] sizes = {"Great ", "Greater ", "Greatest ", "Major ", "Minor ", "Neo ", "Tiny ", "Small ", "Little ", "Large ", "Giant "};
private String[] colorscold = {"Black ", "Brown ", "Bronze ", "Dark ", "Grey ", "Pale ", "Silver ", "White ", "Blue "};
private String[] colorsmild = {"Black ", "Blue ", "Bronze ", "Brown ", "Dark ", "Golden ", "Green ", "Grey ", "Orange ", "Pale ", "Pink ", "Purple ", "Red ", "Silver ", "White ", "Yellow "};
private String[] colorshot = {"Black ", "Brown ", "Dark ", "Golden ", "Orange ", "Pink ", "Purple ", "Red ", "White ", "Yellow "};
private String[] locold = {"Northern ", "Tymerian ", "Kalles ", "Ikylee ", "Ghalvian"};
private String[] locmild = {"Southern ", "Cerebus ", "Cheknek ", "Ghatulian ", "Northern ", "Kalles ", "Ikylee ", "Ghalvian", "Eastern ", "Western "};
private String[] lochot = {"Southern ", "Cerebus ", "Cheknek ", "Ghatulian ", "Kylian "};
private String[] tpscold = {"Ice ", "Moon ", "Night ", "Shadow ", "Wind "};
private String[] tpsmild = {"Earth ", "Rock ", "Water ", "Marsh ", "Wood ", "Wind ", "Blood ", "Light ", "Shadow "};
private String[] tpshot = {"Fire ", "Lava ", "Light ", "Sand ", "Sun ", "Blood "};
/// /// /// /// ///


public void init() {
basewindow = JSObject.getWindow(this); // this=applet MOMain -> Object
basedocument = (JSObject)basewindow.getMember("document");
myCharacter = getParameter("myChar");
myAccount = getParameter("myAcct");
mybrowser = getParameter("mybrowser");
myplatform = getParameter("myplatform");
} // END void init();


public void start() {
if (myAccount.equals("") || myAccount.equals("null") || myCharacter.equals("") || myCharacter.equals("null")) { destroy(); } else {
GetCharacterInfos(myAccount, myCharacter);
connectedstatus = "online"; CharacterStatus[16] = " "; CharacterStatus[17] = " "; CharacterStatus[18] = " ";
UpdateStats(); }
} // END void start();


public void run() {
} // END void run()


public void stop() {
Save();
connectedstatus = "offline";
destroy();
} // END void stop()




		// SUBROUTINES BELOW


			// GAME FUNCTIONS


// Prints PrintNPCAttackScreen Screen
public void PrintNPCAttackScreen() {
VerifyOnlineStatus();
VerifySubLocationAction("wandering foes");
String wandfoehtml = "";

if (myplatform.equals("MAC")) {
wandfoehtml =  "<html><head><title></title><script language=Javascript>function GetNPCAttackValues() {  if (document.NPCAttackForm.foetype[0].checked==true) { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 0;} else if  (document.NPCAttackForm.foetype[1].checked==true) { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 1;} else if  (document.NPCAttackForm.foetype[2].checked==true) { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 2;} else if  (document.NPCAttackForm.foetypee[3].checked==true) { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 3;} else { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 4; }}</script></head><body background=" + imgurl + "mobkgnd.jpg text=white>" + divc + "<br><img src=" + imgurl + "wandfoes_title.gif width=275 height=33 border=0><br><form name=NPCAttackForm target=NPCAttack><input type=hidden value=a name=wfoes><br><table border=0 cellpadding=4 cellspacing=0 width=75% bgcolor=#222222><tr><td bgcolor=black align=center colspan=2>" + fontset + "Hunt for Wandering Foes</font></td></tr><tr><td align=right><input type=radio value=0 name=foetype></td><td width=100%><font size=-2 face=Verdana color=white>Coward Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack only the weakest foe you see.)</font></td></tr><tr><td align=right><input type=radio value=1 name=foetype></td><td width=100%><font size=-2 face=Verdana color=white>Safe Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack the first foe you see which will bring you a challenge.)</font></td></tr><tr><td align=right><input type=radio value=2 name=foetype checked></td><td width=100%><font size=-2 face=Verdana color=white>Adventurous Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack the first foe you see which will bring you a greater challenge.)</font></td></tr><tr><td align=right><input type=radio value=3 name=foetype></td><td width=100%><font size=-2 face=Verdana color=white>Bold Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack the first foe you see...)</font></td></tr><tr><td align=right><input type=radio value=4 name=foetype></td><td width=100%><font size=-2 face=Verdana color=white>Ultimate Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack only the strongest foe you see...)</font></td></tr></table><br><table border=0 cellpadding=2 cellspacing=0 bgcolor=black><tr><td>&nbsp;<a href=javascript:GetNPCAttackValues();window.opener.document.MOMain.NPCAttackNow(document.NPCAttackForm.wfoes.value) target=NPCAttack><img src=http://localhost/Images/submit_hunt.gif border=0></a>&nbsp;</td></tr></form></table></div><br><br><br>" + closewindow + endhtml;
} else {
wandfoehtml =  "<html><head><title></title><script language=Javascript>function GetNPCAttackValues() {  if (document.NPCAttackForm.foetype[0].checked==true) { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 0;} else if  (document.NPCAttackForm.foetype[1].checked==true) { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 1;} else if  (document.NPCAttackForm.foetype[2].checked==true) { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 2;} else if  (document.NPCAttackForm.foetypee[3].checked==true) { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 3;} else { document.NPCAttackForm.wfoes.value = document.NPCAttackForm.wfoes.value + 4; }}</script></head><body background=" + imgurl + "mobkgnd.jpg text=white>" + divc+ "<br><img src=" + imgurl + "wandfoes_title.gif width=275 height=33 border=0><br><form name=NPCAttackForm target=NPCAttack><input type=hidden value=a name=wfoes><br><table border=0 cellpadding=4 cellspacing=0 width=75% bgcolor=#222222><tr><td bgcolor=black align=center colspan=2>" + fontset + "Hunt for Wandering Foes</font></td></tr><tr><td align=right><input type=radio value=0 name=foetype></td><td width=100%><font size=-2 face=Verdana color=white>Coward Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack only the weakest foe you see.)</font></td></tr><tr><td align=right><input type=radio value=1 name=foetype></td><td width=100%><font size=-2 face=Verdana color=white>Safe Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack the first foe you see which will bring you a challenge.)</font></td></tr><tr><td align=right><input type=radio value=2 name=foetype checked></td><td width=100%><font size=-2 face=Verdana color=white>Adventurous Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack the first foe you see which will bring you a greater challenge.)</font></td></tr><tr><td align=right><input type=radio value=3 name=foetype></td><td width=100%><font size=-2 face=Verdana color=white>Bold Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack the first foe you see...)</font></td></tr><tr><td align=right><input type=radio value=4 name=foetype></td><td width=100%><font size=-2 face=Verdana color=white>Ultimate Hunt for Wandering Foes<br></font><font size=-2 face=Verdana color=yellow>(You will attack only the strongest foe you see...)</font></td></tr></table><br><table border=0 cellpadding=2 cellspacing=0 bgcolor=black><tr><td>&nbsp;<a href=# onClick=GetNPCAttackValues();window.opener.document.MOMain.NPCAttackNow(document.NPCAttackForm.wfoes.value) target=NPCAttack><img src=http://localhost/Images/submit_hunt.gif border=0></a>&nbsp;</td></tr></form></table></div><br><br><br>" + closewindow  + endhtml;
}

OutputHTML( wandfoehtml, "NPCAttack" );
wandfoehtml = null;
} // END void PrintNPCAttackScreen()


// Creates New Monster
protected String GenerateFoe() {
String myfoe = "";
String myfoename = "";
String myfoetype = "";
String myfoecolor = "";
String myfoesploc = "";
String myfoesatps = "";
String myfoeabls = "";

if (LocationVars[1].equals("sacredsite") || LocationVars[2].equals("m")) {
// magic
if (LocationVars[3].equals("H")) {
String[] foes = {"Death Scorpion", "Stinger", "Violin Spider", "Silverteeth Bat", "Alcyon", "Angka", "Gharuda", "Phoenix", "Cerberus", "Gnome", "Leprechaun", "Sluagh", "Sylph", "Biclop", "Cyclop", "Triclop", "Behemoth", "Colossus", "Titan", "Gnoll Shaman", "Golem", "Gremlin", "Chimaera", "Mythical Chimaera", "Ancient Chimaera", "Manticore", "Medusa", "Queen Medusa", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Death Scorpion", "Stinger", "Violin Spider", "Silverteeth Bat", "Sca", "Alcyon", "Angka", "Cockatrice", "Gharuda", "Harpy", "Rokh", "Cerberus", "Asrais", "Faery", "Fir Darrig", "Gnome", "Gwageth Anoon", "Leprechaun", "Sluagh", "Sylph", "Biclop", "Cyclop", "Triclop", "Ogre", "Colossus", "Gnoll Shaman", "Golem", "Gremlin", "Chimaera", "Mythical Chimaera", "Ancient Chimaera", "Manticore", "Medusa", "Queen Medusa", "Pegasus", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Death Scorpion", "Silverteeth Bat", "Kodiak", "Sca", "Angka", "Gharuda", "Harpy", "Rokh", "Asrais", "Faery", "Gnome", "Gwageth Anoon", "Leprechaun", "Sluagh", "Sylph", "Ogre", "Behemoth", "Colossus", "Titan", "Golem", "Gremlin", "Chimaera", "Mythical Chimaera", "Ancient Chimaera", "Medusa", "Queen Medusa", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("desert") || LocationVars[1].equals("canyon") || LocationVars[1].equals("crater")) {
// desert
if (LocationVars[3].equals("H")) {
String[] foes = {"Arachnae", "Burrowing Scorpion", "Crackling Scorpion", "Death Scorpion", "Fat-tail Scorpion", "Scorpion", "Stinger", "Thick-tail scorpion", "Buck Spoor Spider", "Button Spider", "Daddy Long Legs", "Funnel Web Spider", "Hexeye Spider", "Huntsmen Spider", "Lizard-eating Spider", "Recluse Spider", "Spider", "Violin Spider", "Wandering Spider", "Leaf-nosed Bat", "Angka", "Gharuda", "Harpy", "Pterodactyl", "Vulture", "Cerberus", "Coyote", "Dingo", "Fanger", "Hyena", "Jackal", "Rhinoceros", "Triceratops", "Gwyllion", "Lion", "Sabertooth", "Tiger", "Colossus", "Golem", "Chimaera", "Mythical Chimaera", "Ancient Chimaera", "Manticore", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bitter", "Craklen", "Antlion Larvae", "Burrowed Larvae", "Bark Louse", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Arachnae", "Flea", "Burrowing Scorpion", "Death Scorpion", "Fat-tail Scorpion", "Long-tail Scorpion", "Scorpion", "Stinger", "Thick-tail scorpion", "Whistling Scorpion", "Buck Spoor Spider", "Button Spider", "Crab Spider", "Daddy Long Legs", "Hexeye Spider", "Humped-back Spider", "Huntsmen Spider", "Lizard-eating Spider", "Recluse Spider", "Spider", "Tarentula", "Violin Spider", "Tick", "Leaf-nosed Bat", "Longears Bat", "Hissing Bat", "Angka", "Cockatrice", "Gharuda", "Harpy", "Rokh", "Coyote", "Dingo", "Fanger", "Hyena", "Jackal", "Wolf", "Triceratops", "Gwyllion", "Cheetah", "Leopard", "Panther", "Jaguar", "Lion", "Sabertooth", "Tiger", "Colossus", "Golem", "Chimaera", "Mythical Chimaera", "Ancient Chimaera", "Manticore", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bitter", "Craklen", "Antlion Larvae", "Burrowed Larvae", "Bark Louse", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Death Scorpion", "Spoor Spider", "Snow Bear", "Angka", "Gharuda", "Rokh", "Wolf", "Gwyllion", "Lion", "Sabertooth", "Tiger", "Colossus", "Golem", "Chimaera", "Mythical Chimaera", "Ancient Chimaera", "Bitter", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("snowfield")) {
// cold
String[] foes = {"Death Scorpion", "Spoor Spider", "Greentongue Bat", "Ghost-faced Bat", "Vampire Bat", "Kodiak", "Sca", "Snow Bear", "Angka", "Gharuda", "Owl", "Rokh", "Wolf", "Bison", "Mammoth", "Karkansver", "Asrais", "Brownie", "Bobcat", "Cougar", "Lynx", "Puma", "Wild Cat", "Lion", "Sabertooth", "Tiger", "Ogre", "Behemoth", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Chimaera", "Mythical Chimaera", "Ancient Chimaera", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];

} else if (LocationVars[1].equals("plain") || LocationVars[1].equals("field") || LocationVars[1].equals("meadow") || LocationVars[1].equals("valley") || LocationVars[1].equals("field") || LocationVars[1].equals("promenade") || LocationVars[1].equals("crescent") || LocationVars[1].equals("territory") || LocationVars[1].equals("vineyard") || LocationVars[1].equals("pass") || LocationVars[1].equals("shire") || LocationVars[1].equals("freehold")) {
// plain
if (LocationVars[3].equals("H")) {
String[] foes = {"Burrowing Scorpion", "Fat-tail Scorpion", "Scorpion", "Thick-tail scorpion", "Baboon Spider", "Button Spider", "Camel-backed Spider", "Comb-footed Spider", "Funnel Web Spider", "Hexeye Spider", "Huntsmen Spider", "Lizard-eating Spider", "Mygale", "Salt Spider", "Spider", "Spitting Spider", "Tarentula", "Violin Spider", "Wandering Spider", "Widow Spider", "Leaf-nosed Bat", "Vampire Bat", "Angka", "Gharuda", "Vulture", "Coyote", "Dingo", "Fanger", "Hyena", "Jackal", "Bull", "Elephant", "Rhinoceros", "Cheetah", "Leopard", "Panther", "Jaguar", "Lion", "Sabertooth", "Tiger", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Mud Ant", "Bee", "Bumblebee", "Dancing Bee", "Killer Bee", "Shining Bee", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bitter", "Bloodhopper", "Bug", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Earwig", "Fly", "Horsefly", "Mud Fly", "Hornet", "Wasp", "Antlion Larvae", "Burrowed Larvae", "Carnivorous Louse", "Louse", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Flea", "Long-tail Scorpion", "Scorpion", "Whistling Scorpion", "Baboon Spider", "Button Spider", "Comb-footed Spider", "Crab Spider", "Fishing Spider", "Flatty Spider", "Humped-back Spider", "Leaf-curling Spider", "Lizard-eating Spider", "Mygale", "Nursery-web Spider", "Rain Spider", "Recluse Spider", "Salt Spider", "Spider", "Spitting Spider", "Spoor Spider", "Tarentula", "Widow Spider", "Wolf Spider", "Tick", "Bat", "Hissing Bat", "Vampire Bat", "Howling Bear", "Angka", "Gharuda", "Coyote", "Dingo", "Fanger", "Fox", "Grome", "Wild Dog", "Wolf", "Bull", "Phouka", "Lion", "Sabertooth", "Tiger", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Maurie", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Mud Ant", "Bee", "Bumblebee", "Dancing Bee", "Killer Bee", "Shining Bee", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bitter", "Bloodhopper", "Bug", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Earwig", "Fly", "Horsefly", "Mud Fly", "Hornet", "Wasp", "Acid Larvae", "Dragon Larvae", "Flying Larvae", "Hard Larvae", "Jelly Larvae", "Larvae", "Pincing Larvae", "Shelled Larvae", "Spiked Larvae", "Antlion Larvae", "Burrowed Larvae", "Carnivorous Louse", "Louse", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Baboon Spider", "Spoor Spider", "Ghost-faced Bat", "Vampire Bat", "Snow Bear", "Angka", "Gharuda", "Fox", "Wolf", "Bison", "Mammoth", "Karkansver", "Lion", "Sabertooth", "Tiger", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Maurie", "Bitter", "Bug", "Fly", "Horsefly", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("battlefield") || LocationVars[1].equals("tomb") || LocationVars[1].equals("pyramid") || LocationVars[1].equals("ruins") || LocationVars[1].equals("cemetary")) {
// eerie
if (LocationVars[3].equals("H")) {
String[] foes = {"Arachnae", "Crackling Scorpion", "Death Scorpion", "Fat-tail Scorpion", "Scorpion", "Stinger", "Buck Spoor Spider", "Camel-backed Spider", "Cannibal Spider", "Daddy Long Legs", "Funnel Web Spider", "Hexeye Spider", "Orb-web Spider", "Spitting Spider", "Violin Spider", "Wandering Spider", "Widow Spider", "Greentongue Bat", "Silverteeth Bat", "Vampire Bat", "Angka", "Gharuda", "Harpy", "Cerberus", "Triceratops", "Gnome", "Leprechaun", "Sluagh", "Spriggan", "Hobgoblin", "Golem", "Gremlin", "Medusa", "Queen Medusa", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bug", "Fly", "Horsefly", "Bark Louse", "Carnivorous Louse", "Louse", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Arachnae", "Crackling Scorpion", "Death Scorpion", "Stinger", "Whistling Scorpion", "Buck Spoor Spider", "Cannibal Spider", "Daddy Long Legs", "Hexeye Spider", "Orb-web Spider", "Spitting Spider", "Violin Spider", "Widow Spider", "Bat", "Greentongue Bat", "Silverteeth Bat", "Hissing Bat", "Vampire Bat", "Howling Bear", "Sca", "Angka", "Gharuda", "Harpy", "Cerberus", "Grome", "Wolf", "Triceratops", "Brownie", "Faery", "Gnome", "Gwageth Anoon", "Leprechaun", "Sluagh", "Spriggan", "Hobgoblin", "Golem", "Gremlin", "Maurie", "Medusa", "Queen Medusa", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bug", "Fly", "Horsefly", "Acid Larvae", "Dragon Larvae", "Flying Larvae", "Hard Larvae", "Jelly Larvae", "Larvae", "Pincing Larvae", "Shelled Larvae", "Spiked Larvae", "Bark Louse", "Carnivorous Louse", "Louse", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Death Scorpion", "Ghost-faced Bat", "Greentongue Bat", "Silverteeth Bat", "Vampire Bat", "Kodiak", "Sca", "Angka", "Gharuda", "Wolf", "Brownie", "Faery", "Gnome", "Gwageth Anoon", "Leprechaun", "Sluagh", "Spriggan", "Hobgoblin", "Golem", "Gremlin", "Maurie", "Medusa", "Queen Medusa", "Bug", "Fly", "Horsefly", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("mill") ||  LocationVars[1].equals("wood") || LocationVars[1].equals("forest") || LocationVars[1].equals("rainforest")) {
// forest
if (LocationVars[3].equals("H")) {
String[] foes = {"Arachnae", "Bark Scorpion", "Burrowing Scorpion", "Crackling Scorpion", "Fat-tail Scorpion", "Scorpion", "Thick-tail scorpion", "Baboon Spider", "Bark Spider", "Button Spider", "Camel-backed Spider", "Cannibal Spider", "Comb-footed Spider", "Daddy Long Legs", "Funnel Web Spider", "Hexeye Spider", "Huntsmen Spider", "Kite Spider", "Lizard-eating Spider", "Mygale", "Net-casting Spider", "Orb-web Spider", "Pirate Spider", "Sac Spider", "Salt Spider", "Spider", "Spitting Spider", "Tarentula", "Violin Spider",  "Wandering Spider", "Greentongue Bat", "Leaf-nosed Bat", "Silverteeth Bat", "Vampire Bat", "Vespertilionid Bat", "Angka", "Gharuda", "Griffin", "Dingo", "Fanger", "Dryads", "Elfe", "Leprechaun", "Pixie", "Cheetah", "Leopard", "Panther", "Jaguar", "Lion", "Sabertooth", "Tiger", "Biclop", "Cyclop", "Behemoth", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Griffon", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Mud Ant", "Tree Ant", "Bee", "Bumblebee", "Dancing Bee", "Killer Bee", "Shining Bee", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bloodhopper", "Bug", "Craklen", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Earwig", "Fly", "Horsefly", "Mud Fly", "Hornet", "Wasp", "Antlion Larvae", "Burrowed Larvae", "Leech", "Bark Louse", "Carnivorous Louse", "Louse", "Termite", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Arachnae", "Flea", "Bark Scorpion", "Long-tail Scorpion", "Scorpion", "Whistling Scorpion", "Baboon Spider", "Bark Spider", "Button Spider", "Comb-footed Spider", "Fishing Spider", "Flatty Spider", "Humped-back Spider", "Huntsmen Spider", "Kite Spider", "Leaf-curling Spider", "Lizard-eating Spider", "Mygale", "Net-casting Spider", "Nursery-web Spider", "Orb-web Spider", "Rain Spider", "Recluse Spider", "Salt Spider", "Spider", "Spitting Spider", "Tarentula", "Wolf Spider", "Tick", "Bark Bat", "Bat", "Greentongue Bat", "Longears Bat", "Silverteeth Bat", "Vampire Bat", "Bear", "Grizzly", "Howling Bear", "Pixie Bear", "Sca", "Angka", "Cockatrice", "Eagle", "Falcon", "Gharuda", "Griffin", "Hawk", "Owl", "Fox", "Grome", "Wild Dog", "Wolf", "Asrais", "Brownie", "Dryads", "Elfe", "Faery", "Fir Darrig", "Leprechaun", "Phouka", "Pixie", "Shefro", "Bobcat", "Cougar", "Lynx", "Puma", "Wild Cat", "Lion", "Sabertooth", "Tiger", "Biclop", "Cyclop", "Ogre", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Griffon", "Maurie", "Pegasus", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Mud Ant", "Tree Ant", "Bee", "Bumblebee", "Dancing Bee", "Killer Bee", "Shining Bee", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bloodhopper", "Bug", "Caterpillar", "Clawed Caterpillar", "Dazzling Caterpillar", "Devouring Caterpillar", "Gliding Caterpillar", "Razorspine Caterpillar", "Sour Caterpillar", "Spider Caterpillar", "Centipede", "Millipede", "Craklen", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Earwig", "Fly", "Horsefly", "Mud Fly", "Hornet", "Wasp", "Acid Larvae", "Dragon Larvae", "Flying Larvae", "Hard Larvae", "Jelly Larvae", "Larvae", "Pincing Larvae", "Shelled Larvae", "Spiked Larvae", "Antlion Larvae", "Burrowed Larvae", "Leech", "Bark Louse", "Carnivorous Louse", "Louse", "Termite", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Baboon Spider", "Ghost-faced Bat", "Greentongue Bat", "Silverteeth Bat", "Vampire Bat", "Bear", "Grizzly", "Kodiak", "Sca", "Angka", "Eagle", "Falcon", "Gharuda", "Griffin", "Hawk", "Owl", "Fox", "Wolf", "Mammoth", "Asrais", "Brownie", "Dryads", "Elfe", "Faery", "Leprechaun", "Pixie", "Shefro", "Bobcat", "Cougar", "Lynx", "Puma", "Wild Cat", "Ogre", "Behemoth", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Griffon", "Maurie", "Bug", "Fly", "Horsefly", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("hill") || LocationVars[1].equals("plateau") || LocationVars[1].equals("mount") || LocationVars[1].equals("mountain") || LocationVars[1].equals("quarry")) {
// mountain
if (LocationVars[3].equals("H")) {
String[] foes = {"Arachnae", "Bark Scorpion", "Crackling Scorpion", "Fat-tail Scorpion", "Scorpion", "Thick-tail scorpion", "Buck Spoor Spider", "Button Spider", "Comb-footed Spider", "Daddy Long Legs", "Kite Spider", "Lizard-eating Spider", "Ogre-faced Spider", "Pirate Spider", "Spider", "Tarentula", "Violin Spider", "Wandering Spider", "Leaf-nosed Bat", "Vampire Bat", "Vespertilionid Bat", "Angka", "Condor", "Gharuda", "Griffin", "Phoenix", "Pterodactyl", "Vulture", "Coyote", "Dingo", "Fanger", "Gwyllion", "Spriggan", "Lion", "Sabertooth", "Tiger", "Biclop", "Cyclop", "Triclop", "Behemoth", "Colossus", "Titan", "Gnoll", "Gnoll Pup", "Gnoll Shaman", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Griffon", "Manticore", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Tree Ant", "Bug", "Craklen", "Fly", "Horsefly", "Antlion Larvae", "Burrowed Larvae", "Bark Louse", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Arachnae", "Bark Scorpion", "Long-tail Scorpion", "Scorpion", "Whistling Scorpion", "Buck Spoor Spider", "Button Spider", "Comb-footed Spider", "Fishing Spider", "Flatty Spider", "Humped-back Spider", "Kite Spider", "Lizard-eating Spider", "Nursery-web Spider", "Ogre-faced Spider", "Rain Spider", "Spider", "Spoor Spider", "Tarentula", "Wolf Spider", "Bark Bat", "Bat", "Free-tailed Bat", "Longears Bat", "Vampire Bat", "Bear", "Grizzly", "Sca", "Angka", "Condor", "Eagle", "Falcon", "Gharuda", "Griffin", "Hawk", "Rokh", "Coyote", "Dingo", "Fanger", "Wild Dog", "Wolf", "Gwyllion", "Phouka", "Spriggan", "Bobcat", "Cougar", "Lynx", "Puma", "Wild Cat", "Lion", "Sabertooth", "Tiger", "Biclop", "Cyclop", "Triclop", "Ogre", "Colossus", "Gnoll", "Gnoll Pup", "Gnoll Shaman", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Griffon", "Manticore", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Tree Ant", "Bee", "Bumblebee", "Dancing Bee", "Killer Bee", "Shining Bee", "Bug", "Caterpillar", "Clawed Caterpillar", "Dazzling Caterpillar", "Devouring Caterpillar", "Gliding Caterpillar", "Razorspine Caterpillar", "Sour Caterpillar", "Spider Caterpillar", "Centipede", "Millipede", "Craklen", "Fly", "Horsefly", "Hornet", "Wasp", "Acid Larvae", "Dragon Larvae", "Flying Larvae", "Hard Larvae", "Jelly Larvae", "Larvae", "Pincing Larvae", "Shelled Larvae", "Spiked Larvae", "Antlion Larvae", "Burrowed Larvae", "Bark Louse", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Spoor Spider", "Vampire Bat", "Bear", "Grizzly", "Kodiak", "Sca", "Angka", "Eagle", "Falcon", "Gharuda", "Griffin", "Hawk", "Rokh", "Wolf", "Bison", "Karkansver", "Ram", "Gwyllion", "Spriggan", "Bobcat", "Cougar", "Lynx", "Puma", "Wild Cat", "Lion", "Sabertooth", "Tiger", "Ogre", "Behemoth", "Colossus", "Titan", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Griffon", "Bug", "Fly", "Horsefly", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("swamp")) {
// swamp
if (LocationVars[3].equals("H")) {
String[] foes = {"Arachnae", "Bark Scorpion", "Death Scorpion", "Scorpion", "Thick-tail scorpion", "Bark Spider", "Button Spider", "Daddy Long Legs", "Fishing Spider", "Funnel Web Spider", "Kite Spider", "Ogre-faced Spider", "Orb-web Spider", "Pirate Spider", "Spider", "Wandering Spider", "Greentongue Bat", "Vampire Bat", "Alcyon", "Angka", "Gharuda", "Harpy", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Manticore", "Medusa", "Queen Medusa", "Fungus Ant", "Mud Ant", "Bug", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Mud Fly", "Leech", "Bark Louse", "Carnivorous Louse", "Louse", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Arachnae", "Flea", "Bark Scorpion", "Death Scorpion", "Long-tail Scorpion", "Scorpion", "Bark Spider", "Button Spider", "Daddy Long Legs", "Fishing Spider", "Kite Spider", "Nursery-web Spider", "Ogre-faced Spider", "Orb-web Spider", "Spider", "Tick", "Bat", "Greentongue Bat", "Vampire Bat", "Alcyon", "Angka", "Gharuda", "Harpy", "Fir Darrig", "Gwageth Anoon", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Manticore", "Medusa", "Queen Medusa", "Fungus Ant", "Mud Ant", "Bug", "Caterpillar", "Clawed Caterpillar", "Dazzling Caterpillar", "Devouring Caterpillar", "Gliding Caterpillar", "Razorspine Caterpillar", "Sour Caterpillar", "Spider Caterpillar", "Centipede", "Millipede", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Mud Fly", "Acid Larvae", "Dragon Larvae", "Flying Larvae", "Hard Larvae", "Jelly Larvae", "Larvae", "Pincing Larvae", "Shelled Larvae", "Spiked Larvae", "Leech", "Bark Louse", "Carnivorous Louse", "Louse", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Death Scorpion", "Ghost-faced Bat", "Greentongue Bat", "Vampire Bat", "Angka", "Gharuda", "Gwageth Anoon", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Medusa", "Queen Medusa", "Bug", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("volcano")) {
// fire
String[] foes = {"Death Scorpion", "Stinger", "Spider", "Wandering Spider", "Vampire Bat", "Vespertilionid Bat", "Angka", "Gharuda", "Harpy", "Phoenix", "Pterodactyl", "Cerberus", "Triceratops", "Lion", "Sabertooth", "Tiger", "Biclop", "Cyclop", "Triclop", "Behemoth", "Gnoll", "Gnoll Pup", "Gnoll Shaman", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Chimaera", "Mythical Chimaera", "Ancient Chimaera", "Manticore", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Craklen", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];

} else if (LocationVars[1].equals("mine") || LocationVars[1].equals("smallcaves") || LocationVars[1].equals("largecaves") || LocationVars[1].equals("gazo") || LocationVars[1].equals("mine")) {
// underground
if (LocationVars[3].equals("H")) {
String[] foes = {"Arachnae", "Crackling Scorpion", "Fat-tail Scorpion", "Scorpion", "Thick-tail scorpion", "Buck Spoor Spider", "Button Spider", "Ogre-faced Spider", "Pirate Spider", "Spider", "Tarentula", "Violin Spider", "Wandering Spider", "Leaf-nosed Bat", "Vampire Bat", "Angka", "Gharuda", "Harpy", "Cerberus", "Gnome", "Gwyllion", "Colossus", "Gnoll", "Gnoll Pup", "Gnoll Shaman", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Manticore", "Medusa", "Queen Medusa", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bug", "Craklen", "Antlion Larvae", "Burrowed Larvae", "Leech", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Arachnae", "Long-tail Scorpion", "Scorpion", "Whistling Scorpion", "Buck Spoor Spider", "Button Spider", "Humped-back Spider", "Ogre-faced Spider", "Spider", "Tarentula", "Bat", "Free-tailed Bat", "Longears Bat", "Vampire Bat", "Angka", "Gharuda", "Harpy", "Gnome", "Gwyllion", "Colossus", "Gnoll", "Gnoll Pup", "Gnoll Shaman", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Manticore", "Medusa", "Queen Medusa", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bug", "Centipede", "Millipede", "Craklen", "Acid Larvae", "Dragon Larvae", "Flying Larvae", "Hard Larvae", "Jelly Larvae", "Larvae", "Pincing Larvae", "Shelled Larvae", "Spiked Larvae", "Antlion Larvae", "Burrowed Larvae", "Leech", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Vampire Bat", "Angka", "Gharuda", "Gnome", "Gwyllion", "Colossus", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Medusa", "Queen Medusa", "Bug", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("pond") || LocationVars[1].equals("oasis") || LocationVars[1].equals("lake") || LocationVars[1].equals("river") || LocationVars[1].equals("sea") || LocationVars[1].equals("coast") || LocationVars[1].equals("peninsula") || LocationVars[1].equals("island") || LocationVars[1].equals("archipelago") || LocationVars[1].equals("fall") || LocationVars[1].equals("bridge") || LocationVars[1].equals("port")) {
// water
if (LocationVars[3].equals("H")) {
String[] foes = {"Fishing Spider", "Alcyon", "Angka", "Gharuda", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Leech", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Fishing Spider", "Nursery-web Spider", "Alcyon", "Angka", "Gharuda", "Gwageth Anoon", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Leech", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Angka", "Gharuda", "Gwageth Anoon", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else if (LocationVars[1].equals("floatingisland") || LocationVars[1].equals("cliff") || LocationVars[1].equals("nest")) {
//air
if (LocationVars[3].equals("H")) {
String[] foes = {"Greentongue Bat", "Leaf-nosed Bat", "Silverteeth Bat", "Vampire Bat", "Vespertilionid Bat", "Alcyon", "Angka", "Condor", "Gharuda", "Griffin", "Harpy", "Phoenix", "Pterodactyl", "Vulture", "Tengu", "Griffon", "Manticore", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Hornet", "Wasp", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Bat", "Greentongue Bat", "Longears Bat", "Silverteeth Bat", "Hissing Bat", "Vampire Bat", "Alcyon", "Angka", "Condor", "Cockatrice", "Eagle", "Falcon", "Gharuda", "Griffin", "Harpy", "Hawk", "Owl", "Rokh", "Tengu", "Griffon", "Manticore", "Pegasus", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Hornet", "Wasp", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Ghost-faced Bat", "Greentongue Bat", "Silverteeth Bat", "Vampire Bat", "Angka", "Eagle", "Falcon", "Gharuda", "Griffin", "Hawk", "Owl", "Rokh", "Tengu", "Griffon", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }

} else {
//usual

if (LocationVars[3].equals("H")) {
String[] foes = {"Bark Scorpion", "Burrowing Scorpion", "Scorpion", "Thick-tail scorpion", "Button Spider", "Camel-backed Spider", "Comb-footed Spider", "Daddy Long Legs", "Funnel Web Spider", "Hexeye Spider", "Huntsmen Spider", "Jumping Spider", "Kite Spider", "Lizard-eating Spider", "Mygale", "Salt Spider", "Spider", "Spitting Spider", "Tarentula", "Violin Spider", "Wandering Spider", "Widow Spider", "Leaf-nosed Bat", "Vampire Bat", "Angka", "Griffin", "Vulture", "Coyote", "Hyena", "Jackal", "Bull", "Elephant", "Bogles", "Dryads", "Leprechaun", "Spriggan", "Cheetah", "Leopard", "Panther", "Jaguar", "Lion", "Sabertooth", "Tiger", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Mud Ant", "Tree Ant", "Bee", "Bumblebee", "Dancing Bee", "Killer Bee", "Shining Bee", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bitter", "Bloodhopper", "Bug", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Earwig", "Mud Fly", "Hornet", "Wasp", "Antlion Larvae", "Burrowed Larvae", "Bark Louse", "Carnivorous Louse", "Louse", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else if (LocationVars[3].equals("M")) {
String[] foes = {"Flea", "Bark Scorpion", "Long-tail Scorpion", "Scorpion", "Button Spider", "Comb-footed Spider", "Crab Spider", "Daddy Long Legs", "Fishing Spider", "Flatty Spider", "Humped-back Spider", "Jumping Spider", "Kite Spider", "Leaf-curling Spider", "Lizard-eating Spider", "Mygale", "Rain Spider", "Recluse Spider", "Salt Spider", "Spider", "Spitting Spider", "Spoor Spider", "Tarentula", "Widow Spider", "Wolf Spider", "Tick", "Bark Bat", "Bat", "Hissing Bat", "Vampire Bat", "Bear", "Angka", "Eagle", "Falcon", "Griffin", "Hawk", "Owl", "Coyote", "Fox", "Grome", "Wild Dog", "Wolf", "Bull", "Bogles", "Dryads", "Fir Darrig", "Leprechaun", "Phouka", "Pixie", "Shefro", "Spriggan", "Urisk", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Ant", "Ant Queen", "Ant Warrior", "Fungus Ant", "Mud Ant", "Tree Ant", "Bee", "Bumblebee", "Dancing Bee", "Killer Bee", "Shining Bee", "Beetle", "Blade Beetle", "Glass-back Beetle", "Horned Beetle", "Spitting Beetle", "Bitter", "Bloodhopper", "Bug", "Caterpillar", "Clawed Caterpillar", "Dazzling Caterpillar", "Devouring Caterpillar", "Gliding Caterpillar", "Razorspine Caterpillar", "Sour Caterpillar", "Spider Caterpillar", "Centipede", "Millipede", "Dragonfly", "Dwarf Dragonfly", "Singing Dragonfly", "Earwig", "Mud Fly", "Hornet", "Wasp", "Acid Larvae", "Dragon Larvae", "Flying Larvae", "Hard Larvae", "Jelly Larvae", "Larvae", "Pincing Larvae", "Shelled Larvae", "Spiked Larvae", "Antlion Larvae", "Burrowed Larvae", "Bark Louse", "Carnivorous Louse", "Louse", "Walkingstick"};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname];
} else {
String[] foes = {"Spoor Spider", "Ghost-faced Bat", "Vampire Bat", "Bear", "Angka", "Eagle", "Falcon", "Griffin", "Hawk", "Owl", "Fox", "Wolf", "Bogles", "Dryads", "Leprechaun", "Shefro", "Spriggan", "Bobcat", "Cougar", "Lynx", "Puma", "Wild Cat", "Lion", "Sabertooth", "Tiger", "Ogre", "Goblin", "Goblin Champion", "Goblin Knight", "Goblin Warlord", "Goblin Warrior", "Hobgoblin", "Tengu", "Golem", "Gremlin", "Bitter", "Bug", ""};
int rname = (int) ( Math.random() * foes.length );
myfoename = foes[rname]; }
} // end locations

// RANDOM GET WHAT STUFF
int foernd = (int) (Math.random() * 19);
int foewhich = 0;
int maxabls = 0;
String foeablstypes = "";
boolean size = false;
boolean color = false;
boolean loc = false;
boolean type = false;

if ( foernd == 0) {
// 3 adjectives
foewhich = (int) (Math.random() * 3);

if (foewhich == 0) { size = true; color = true; loc = true;
} else if (foewhich == 1) { size = true; color = true; type = true;
} else if (foewhich == 2) { size = true; loc = true; type = true;
} else { color = true; loc = true; type = true; }

} else if (foernd > 0 && foernd < 4) {
// 2 adjectives
foewhich = (int) (Math.random() * 5);

if (foewhich == 0) { size = true; color = true;
} else if (foewhich == 1) { size = true; loc = true;
} else if (foewhich == 2) { size = true; type = true;
} else if (foewhich == 3) { color = true; loc = true;
} else if (foewhich == 4) { color = true; type = true;
} else { loc = true; type = true; }

} else if (foernd > 3 && foernd < 10) {
// 0 adjective
} else {
// 1 adj
foewhich = (int) (Math.random() * 3);

if (foewhich == 0) { size = true;
} else if (foewhich == 1) { color = true;
} else if (foewhich == 2) { loc = true;
} else { type = true; }
}

if (size == true) {
int rnewfoersorst = (int) ( Math.random() * sizes.length );
myfoetype = sizes[rnewfoersorst];
} else {}

if (color == true) {
if (LocationVars[3].equals("H")) {
int newfoeclr = (int) ( Math.random() * colorshot.length );
myfoecolor = colorshot[newfoeclr];
} else if (LocationVars[3].equals("C")) {
int newfoeclr = (int) ( Math.random() * colorscold.length );
myfoecolor = colorscold[newfoeclr];
} else {
int newfoeclr = (int) ( Math.random() * colorsmild.length );
myfoecolor = colorsmild[newfoeclr];
}} else {}

if (loc == true) {
if (LocationVars[3].equals("H")) {
int newfoeloc = (int) ( Math.random() * lochot.length );
myfoesploc = lochot[newfoeloc];
} else if (LocationVars[3].equals("C")) {
int newfoeloc = (int) ( Math.random() * locold.length );
myfoesploc = locold[newfoeloc];
} else {
int newfoeloc = (int) ( Math.random() * locmild.length );
myfoesploc = locmild[newfoeloc]; }} else {}

if (type == true) {
if (LocationVars[3].equals("H")) {
int newfoetps = (int) ( Math.random() * tpshot.length );
myfoesatps = tpshot[newfoetps];
} else if (LocationVars[3].equals("C")) {
int newfoetps = (int) ( Math.random() * tpscold.length );
myfoesatps = tpscold[newfoetps];
} else {
int newfoetps = (int) ( Math.random() * tpsmild.length );
myfoesatps = tpsmild[newfoetps];
}} else {}

if (myfoename.equals("") || myfoename.equals("")) {

} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else if (myfoename.equals("")) {
} else {}

// Number of abilities to get for this foe...
maxabls = (int) ( 1 + Math.random() * maxabls );

foeablstypes = GenerateFoeAbility(foeablstypes, maxabls);

return myfoe;
} // END String GenerateFoe()



protected String GenerateFoeAbility(String seldlist, int maxabls) {
String gablist = "";
int newabl = 0;

String[] mandibles = {"Bite*bites*bite*you with*mandibles*", "Chop*chops*chop*through your body with*mandibles", "Pince*pinces*pince*you with*mandibles", "Snap*snaps*snap*you with*mandibles", "Chomp*chomps*chomp*through your body with*mandibles"};




String[] poisonbites = {"Poison Bite*bites*bite*you with* fangs", "Green Kiss*bites*bite*you with* fangs", "Green Embrace*bites*bite*you with* fangs", "Poisonned Kiss*bites*bite*you with* fangs", "Poisonned Embrace*bites*bite*you with*fangs"};
String[] sting = {"Sting*stings*sting*you with*dart", "Dart*stings*sting*you with*dart", "Poison Sting*stings*sting*you with*dart", "Poison Dart*stings*sting*you with*dart", "Poison*stings*sting*you with*dart", "Savage Sting*stings*sting*you savagely with*dart", "Severe Sting*stings*sting*you severely with*dart", "Deep Sting*stings*sting*you deeply with*dart", "Deep Dart*stings*sting*you deeply with*dart", "Doom Sting*stings*sting*you ferociously with*dart", "Doom Dart*stings*sting*you ferociously with*dart"};
String[] fangs = {"Fang*bites*bite*you with*fangs", "Fangs*bites*bite*you with*fangs", "Vicious Bite*viciously bites*bite*you with*fangs", "Bite*bites*bite*you with*fangs", "Devour*takes*take*a chunk from your body*with*fangs", "Striking Bite*strikes*strike*you with*fangs", "Razor Fangs*cuts*cut*through you with*fangs", "Hammer Fangs*pierces*pierce*you heavily with*fangs", "Shining Fangs*bites*bite*you efficiently with*fangs", "Quick Fangs*bites*bite*you rapidly with*fangs"};
String[] bite = {"Bite*bites*bite*you with violence*0", "Vicious Bite*viciously bites*bite*you with violence*0", "Chew*chews*chew*on you with violence*0", "Striking Bite*bites*bite*you with striking violence*0", "Violent Bite*bites*bite*you violently*0", "Deep Bite*deeply bites*bite*you with violence*0"};
String[] head = {"Bash*bashes*bash*you with*head", "Slam*slams*slam*you with*head", "Headbutt*hits*hit*you in the face with*head", "Head Slam*slams*slam*you with*head", "Head Bash*bashes*bash*you in the face with*head", "Hammer Head*hammers*hammer*you with*head"};
String[] hornimpale = {"Tear Limb*tears*tear*your flesh apart with*sharp horns", "Impale*impales*impale*you with*sharp horns", "Horn Strike*strikes*strike*you with*sharp horns", "Horn Lance*perforates*perforate*your body with*sharp horns"};
String[] hornbash = {"Bash*bashes*bash*you with*bulky horns", "Ram*rams*ram*you with*bulky horns", "Charge*charges*charge*on you with*bulky horns", "Horn Slam*slams*slam*you with*bulky horns", "Bone Crush*crushes*crush*your body with*bulky horns", "Horn Crush*crushes*crush*you with*bulky horns", "Head to Head*smashes*smash*your head with*bulky horns", "Hammer Horns*hammers*hammer*you with*bulky horns"};
String[] tackle = {"Tackle*tackles*tackle*you*0", "Body Slam*slams*slam*your body*0", "Slam*slams*slam*you*0", "Rush*rushes*rush*on you*0", "Charge*charges*charge*on you*0", "Demolish*charges*charge*on you furiously*0", "Stomp*tackles*tackle*you to the ground to stomp you*0", "Trample*tramples*trample*you*0"};
String[] punch = {"Punch*punches*punch*you with*fist", "Fist*punches*punch*you with*fist", "Chop*chops*chop*you with*fist", "Jab*jabs*jab*you with*fist", "Uppercut*punches*punch*you violently with*fist", "Slap*slaps*slap*you with*hand", "Bash*bashes*bash*you violently with*fists"};
String[] kick = {"Kick*kicks*kick*you with*foot", "Whip Kick*kicks*kick*you rapidly with*tibia", "Low Kick*kicks*kick*you with the sole of*foot", "High Kick*kicks*kick*you with*heel", "Side Kick*kicks*kick*you with the sole of*foot", "Round Kick*kicks*kick*you violently with the side of*foot"};
String[] jump = {"Jump*jumps*jump*on you*0", "Leap*leaps*leap*into the air*0", "Bounce*bounces*bounce*on you*0", "High Jump*jumps*jump*high to crush you*0", "High Leap*leaps*leap*into the air*0", "Leap Slash*leaps*leap*into the air to slash you*0"};
String[] tail = {"Tail Whip*whips*whip*you with*tail", "Tail Smash*smashes*smash*you with*tail", "Tail Bash*bashes*bash*you with*tail", "Tail*attacks*attack*you with*tail", "Tail Swing*swings*swing*you with*tail", "Tail Poke*pokes*poke*you with the end of*tail", "Tail Strike*strikes*strike*you with*tail", "Cutting Tail*cuts*cut*you with*tail", "Sharp Tail*cuts*cut*you with*tail"};
String[] bloodfoe = {"Blood Lust*lusts*lust*for your blood*0", "Blood Rush*strives for*get*more blood*0", "Blood Suck*sucks*suck*your blood*0", "Blood Drink*drinks*drink*your blood*0", "Devour*feeds*feed*on your blood*0"};
String[] breathfire = {"Fire Breath*spits*spit*a burning flame on you*0", "Fire*spits*spit*a burning flame on you*0", "Warm Breath*spits*spit*a burning flame on you*0", "Infernal Kiss*spits*spit*an infernal flame on you*0", "Incinerate*spits*spit*an incinerating flame on you*0", "Desintegrate*spits*spit*a powerful flame on you*0", "Fire Ball*spits*spit*a ball of fire on you*0", "Fire Pillar*launches*launch*a pillar of fire on you*0", "Fire Storm*storms*storm*you with*flames"};
String[] breathpoison = {"Poison Breath*spits*spit*very powerful poison on you*0", "Green Spit*spits*spit*powerful poison on you*0", "Poison*spits*spit*poison on you*0"};
String[] breathweb = {"Web Throw*throws*throw*a web on you*0", "Web Ball*spits*spit*a ball of gooey silk on you*0", "Web Weave*weaves*weave*an restraining web around you*0", "Weave*weaves*weave*a web around you*0", "Gooey Spit*spits*spit*a gooey ball on you*0", "Silk Thread*throws*throw*a restraining thread on you*0", "Silk Cage*spins*spin*a paralyzing web around you*0"};
String[] breathacid = {"Acid Breath*spits*spit*a powerful acid on you*0", "Acid*spits*spit*a burning acid on you*0", "Burning Spit*spits*spit*acid on you*0", "Acid Spit*spits*spit*a burning acid on you*0", "Acid Spray*sprays*spray*acid on you*0", "Burning Spray*sprays*spray*burning acid on you*0"};
String[] elbow = {"Elbow Smah*smashes*smash*you with*elbow", "Elbow Dive*hits*hit*you with*elbow", "Elbow*attacks*attack*you with*elbow", "Elbow Swing*swings*swing*with*elbow"};
String[] knee = {"Knee Smash*smashes*smash*you with*knee", "Knee Strike*strikes*strike*you with*knee", "Knee*attacks*attack*you with*knee", "Knee Rise*hits*hit*you hard with*knee", "Knee Swing*swings*swing*with*knee"};
String[] spike = {"Impale*impales*impale*you with*spike", "Spike*attacks*attack*you with*spike", "Pike*pierces*pierce*your body with*spike", "Spear Strike*strikes*strike*you with*spike", "Razor Spike*severely slashes*slash*your body with*spike", "Sharp Spike*impales*impale*you with*spike"};
String[] throwobj = {"Stone Throw*throws*throw*stone on you*0", "Rock Throw*throws*throw*rock on you*0", "Boulder*throws*throw*boulder on you*0", "Throw*quickly throws*throw*a hard projectile on you*0", "Projectile*quickly throws*throw*a heavy projectile on you*0", "Stick Throw*throws*throw*stick on you*0", "Sand Throw*throws*throw*sand in your face*0"};
String[] throwfoe = {"Grab*grabs and throws*grab and throw*you away*0", "Pitch*pitches*pitch*you on a hard surface*0", "Toss*tosses*toss*you afar*0", "Project*projects*project*you on a short distance*0"};
String[] disappear = {"Phasing*phases*phase*out of your senses*0", "Camouflage*fades*fade*out*0", "Hide*hides*hide*from you*0", "Stealth*disappears*disappear*from your senses with*stealth abilities", "Fade*fades*fade*away*0", "Disappear*disappears*disappear*..*0"};
String[] disappeardig = {"Cave-in*disappears*disappear*into the soil with*burrowing abilities", "Hide*hides*hide*in the soil with*digging abilities", "Dig*digs*dig*a hole into the ground*0", "Tunnel*tunnels*tunnel*way into the ground*0", "Hole*digs*dig*a hole into the ground*0"};
String[] shielddefmode = {"Parade*thwarts*thwart*your plans with*parade", "En Guarde*enhances*enhance* *defense", "Defend*enhances*enhance* *defense", "Shield*shields*shield* *body", "Evade*raises*raise* *evasion skills", "Evasive Maneuvers*raises*raise* *evasion skills", "Maneuvers*raises*raise* *evasion skills", "Dodge*raises*raise* *dodging skills"};
String[] shieldshell = {"Harden*hardens*harden* *shell", "Toughen*toughens*toughen* *shell", "Shell*shells*shell* *body", "Rock Skin*hardens*harden* *carapace", "Stiffen*stiffens*stiffen* *shell"};
String[] draintough = {"Life Feed*feasts*feast*on your vital essence*0", "Life Drain*drains*drain*on your vital essence*0", "Drain*drains*drain*your energy*0", "Feed*feeds*feed*on your your energy*0"};
String[] drainmag = {"Mind Drain*drains*drain*your inspiration with*mind", "Thought Eater*feasts*feast*on your thoughts*0", "Dream Eater*feasts*feast*on your inspiration*0", "Dream Hunter*drains*drain*your magic ressources*0", "Mind Feed*feeds*feed*on your thoughts*0"};
String[] claw = {"Claw*claws*claw*you with*claw", "Claws*claws*claw*you with*claws", "Cut*cuts*cut*you with*claws", "Strike*strikes*strike*you with*claws", "Claw Strike*violently strikes*strike*you with*claws", "Scratch*scratches*scratch*you with*claws", "Tear Skin*tears*tear*your skin with*claws", "Sharp Claws*shreads*shread*your skin with*sharp claws", "Cutting Edge*savagely rips*rip*your body with*claws"};
String[] vegetal = {"Pollen*spreads*spread* *pollen on you", "Entangle*entangles*entangle*you with*branches", "Poke*pokes*poke*you with*branch", "Engulf*engulfes*engulf*you with*roots", "Restrain*restrains*restrain*you with*dense folliage", "Thorn Ebrace*rips*rip*your body with*thorns"};
String[] vegcar = {"Pollen*spreads*spread* *pollen on you", "Entangle*entangles*entangle*you with*branches", "Poke*pokes*poke*you with*branch", "Engulf*engulfes*engulf*you with*roots", "Restrain*restrains*restrain*you with*dense folliage", "Thorn Ebrace*rips*rip*your body with*thorns", "Consume*consumes*consume*you with*trap mouth", "Limb Eater*chews*chew*on one of your limbs with*trap mouth", "Dissolve*burns*burn*a part of your body with*acid"};
String[] petrify = {"Stone Skin*turns*turn*your skin to stone*0", "Petrify*petrifies*petrify*you*0", "Petrification*petrifies*petrify*you*0"};
String[] petrifyeye = {"Glare*petrifies*petrify*you with*glare", "Stoning Glare*petrifies*petrify*you with*glare", "Rock Glare*petrifies*petrify*you with*glare"};
String[] paralyze = {"Paralyze*paralyzes*paralyze*you*0", "Stun*stuns*stun*you*0", "Numb*numbs*numb*you*0", "Restrain*restrains*restrain*you*0", "Immobilize*immobilizes*immobilize*you*0"};
String[] paralyzeeye = {"Paralyzing Glare*paralyzes*paralyze*you with*glare", "Stunning Glare*stuns*stun*you with*glare"};
String[] blind = {"Blind*blinds*blind*you*0", "Eyes Shut*renders*render*you sightless*0", "In the Dark*renders*render*you*0", "Shades*blinds*blind*you*0", "Shadow Land*renders*render*you sightless*0"};
String[] sleep = {"Dream*tires*tire*you*0", "Sleep*renders*render*you asleep*0", "Tired*renders*render*you sleepy*0", "Heavy Lids*makes*make*you sleepy*0", "Sandman*renders*render*you asleep*0", "Touch of Morpheus*forces*force*you to sleep*0"};
String[] regen = {"Regeneration*regenerates*regenerate*in front of you*0", "Heal Wounds*heals*heal* *wounds", "Lick Wounds*heals*heal* *wounds", "Cure Wounds*cures*cure* *wounds", "Recover*recovers*recover* *wounds", "Revival*heals*heal* *wounds"};
String[] Ice = {"Frozen Touch*freezes*freeze*you with*ice abilities", "Cold Bite*freezes*freeze*you with*ice abilities", "Ice Dust*projects*project*a cloud of ice sparks on you with*ice abilities", "Crystal*heals*heal*all wounds and conditions with*ice abilities", "Ice Blow*blows*blow*you away with*ice abilities", "Ice Shell*creates*create*a protective ice barrier with*ice abilities", "Ice Shield*creates*create*a protective ice shield with*ice abilities", "Avalanche*submerges*submerge*you under a mass of snow using*ice abilities"};
String[] Moon = {"Moon Dive*enhances*enhance* *accuracy drastically with moon abilities", "Moon Dust*conceals*conceal* *presence with mysterious dust", "Falling Moon*hits*hit*you severly with*moon abilities", "Waxing Moon*enhances*enhance*power drastically with*moon abilities", "Waning Moon*diminishes*diminish*your power drastically with*moon abilities", "Full Moon*enhances*enhance* *combat skills drastically", "Black Moon*diminishes*diminish*your combat skills drastically with*moon abilities"};
String[] Night = {"Night Veil*conceals*conceal* *presence with a veil of darkness", "Night Star*enhances*enhance* *accuracy", "Night Sky*blinds*blind*you with a veil of darkness*0", "Nightfall*strikes*strike*you with a stunning blow*0", "Midnight*strikes*strike*you with a blinding blow*0", "Midnight Mist*diminishes*diminish*your combat skills critically*0"};
String[] Shadow = {"Shadow*rushes*rush*on you with all of*might", "Shade Drop*hits*hit*you with a shade using*shadow abilities", "Mind Shading*hits*hit*your mind with a shadow blast*0", "Dancing Shadows*creates*create*dancing shadows around*, increasing his evasiveness", "Shadow Rush*rushes*rush*on you to deliver a shadow blast using*shadow abilities", "Fading Soul*hits*hit*your mind with a shadow blast*0"};
String[] Wind = {"Storm*blows*blow*you away with a strong wind*0", "Tempest*blows*blow*you away with a violent wind*0", "Hurricane*blows*blow*you away with a monstrous wind*0", "Tornado*blows*blow*you away with a tornado*0", "Twister*blows*blow*you away with a powerful tornado*0", "Wind*blows*blow*you away with wind*0"};
String[] Earth = {"Quake*hits*hit*you with a quake*earth abilities", "Earthquake*hits*hit*you with a powerful quake*earth abilities", "Fissure*crushes*crush*you with a fissure using*earth abilities", "Tremble*projects*project*you to the ground using*earth abilities", "Mud Slide*submerges*submerge*you in a sea of mud using*earth abilities", "Earth Strike*strikes*strike*you with a rising rock pillar using*earth abilities"};
String[] Rock = {"Rock Shell*creates*create*a protective barrier of rocks with*rock abilities", "Rock Slide*submerges*submerge*in an avalanche of rock*0", "Stone Fist*hits*hit*you violently with*rock fist", "Rock Hammer*hammers*hammer*you violently with*rock abilities"};
String[] Water = {"Water Blast*blasts*blast*you with a water pillar*using water abilities", "Waterfall*drops*drop*a powerful pillar of water on you*using water abilities", "Waterball*throws*throw*a powerful ball of water on you*using water abilities", "Power Geyser*projects*project*on a long distance with a power water geyser*using water abilities", "Steam Cloud*burns*burn*you severely with a steam cloud*using water abilities"};
String[] Marsh = {"Foul Gas*disgusts*disgust*you with*foul stench", "Evil Stench*disgusts*disgust*you with*evil stench", "Mud Shower*showers*shower*you with filthy mud*0", "Danse of Despair*demoralizes*demoralize*you with*foolish dance", "Filthball*hits*hit*you with a filthy ball using*marsh abilities", "Filthy Strike*hits*hit*you with a filthy ball using*marsh abilities"};
String[] Wood = {"Log Throw*throws*throw*a log on you using*wood abilities", "Wood Shell*creates*creat*a protective wood barrier using*wood abilities", "Deep Roots*restrains*restrain*you with thick roots using*wood abilities", "Roots of Life*heals*heal*some wounds with*wood abilities", "Falling Tree*throws*throw*you on a long distance with incredible force using*wood abilities", "Wrath of Nature*brings*bring*havoc to your surroundings using*wood abilities"};
String[] Blood = {"Bloodlust*cuts*cut*through your defenses with a furious rampage*0", "Blood Moon*increases*increase* *offensive skills drastically", "Blood Surge*rushes*rush*on you madly*0", "Blood Flow*cuts*cut*through your defenses with an intensive blitz using*blood abilities", "Black Blood*corrupts*corrupt*your body with a powerful slash*using*blood abilities", "Blue Blood*corrupts*corrupt*your mind with*uncanny charisma", "Blood Thirst*charges and pounds*charge*you using*blood abilities", "Bloody Strike*strikes*strike*your using*blood abilities", "Bloody Feast*feasts*feast*on you using*blood abilities"};
String[] Light = {"Light Ray*focuses*focus*a ray of light on you using*light abilities", "Illuminate*recovers*recover*from all conditions using*light abilities", "Light Blast*blasts*blast*you with a powerful ball of light using*light abilities", "Lightning*strikes*strike*you with a lightning thread using*light abilities", "Lightning Bolt*strikes*strike*you with a lightning bolt using*light abilities"};
String[] Fire = {"Fire*burns*burn*you with engulfing fire sparks using*fire abilities", "Fire Storm*incinerates*incinerate*your body with a storm of engulfing flames using*fire abilities", "Fireball*throws*throw*a ball of fire at you using*fire abilities", "Fire Blast*burns*burn*your body with an infernal blast using*fire abilities", "Burning Hell*brings*bring*havoc to your surroundings using*fire abilities", "Incinerate*incinerates*incinerate*your body with powerful flames using*fire abilities", "Desintegrate*engulfes*engulf*your body with raging flames using*fire abilities", "Fire Pillar*throws*throw*a pillar of fire on you using*fire abilities"};
String[] Lava = {"Magma*throws*throw*a stream of boiling magma on you using*lava abilities", "Lava Slide*submerges*submerge*you in boiling lava using*lava abilities", "Eruption*causes*cause*a minor eruption using*lava abilities", "Lava Storm*burns*burn*you with a storm of boiling lava drops using*lava abilities", "Ashes to Ashes*engulfes*engulf*you with a storm of burning ashes using*lava abilities"};
String[] Sand = {"Quicksand*submerges you gradually*submerge you*in a quicksand using*sand abilities", "Sand Storm*blinds*blind*you with a sandstorm using*sand abilities", "White Sand*heals*heal* *wounds", "Crystal Sand*recovers*recover*from all wounds and conditions using*sand abilities", "Silver Sand Storm*blinds and cuts*cut*you with a powerful storm of sand and silver shards using*sand abilities", "Veil of Dust*disappears*disappear*in a shroud of sand using*sand abilities"};
String[] Sun = {"Sunrise*knocks*knock*you with a powerful blow using*sun abilities", "Sun Ray*projects*project*a burning stream of light on you*sun abilities", "Sun Shine*enhances*enhance* *combat skills drastically", "Noon Sun*burns*burn*your body using*sun abilities", "Sunset*strikes*strike*you with a violent blow using*sun abilities"};
String[] bird = {"Peck*hits*hit*you with*sharp beak", "Dive*strikes*strike*you while dropping from the sky with*beak and claws", "Sky Attack*strikes*strike*you violently while dropping from the sky with*beak and claws", "Flying Claw*cuts*cut*you with*claws", "Wing Cut*cuts*cut*you with*powerful maneuvers", "Wing Slash*slashes*slash*you with*powerful maneuvers", "Wing Strike*strikes*strike*you with*powerful maneuvers", "Fly High*flies*fly*high in the sky*0", "Sky Drop*drops*drop*you from the sky*0"};

String[] warcry = {"Intimidate*intimidates*intimidate*you with an daring posture*0", "War Cry*intimidates*intimidate*you with*strongest cry", "Hurl*intimidates*intimidate*you with*high pitch cry", "Howl*increases*increase* *confidence with a powerful howl", "Scream*screams*scream*out loud*0", "Cry*cries*cry*out loud*0", "Shout*shouts*shout*out loud*0", "Terrorize*terrorizes*terrorize*with*powerful assets", "Fear*intimidates*intimidate*you with*fearful glare", "Frighten*frightens*frighten*you with*powerful assets", "Scare*intimidates*intimidate*you with*powerful assets", "Terrify*terrifies*terrify*you with*horrifying scream"};
String[] taunt = {"Laugh*laughs*laugh*at you*0", "Ignore*ignores*ignore*you*0", "Brag*demonstrates*demonstrate* *superior skills", "Rest*rests*rest*peacefully while watching your desperate attempts*0", "Yawn*yawns*yawn*slowly and loudly*0", "Wait*waits*wait*and watches your next move*0", "Clean*cleans*clean*up and waits for your next move*0"};

return gablist;
} // END String GenerateFoeAbility()






// Prints Hire Screen
public void PrintHireScreen(String hiredtype) {
VerifyOnlineStatus();
VerifySubLocationAction(hiredtype);
String hirehtml = "";

if (hiredtype.equals("informant")) {

hirehtml = "<html><head><title>Hire Informant</title><script language=Javascript>function GetHireValues() {  document.InformantForm.hire.value = document.InformantForm.target.value + document.InformantForm.star.value;if (document.InformantForm.type[0].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 0;} else if  (document.InformantForm.type[1].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 1;} else if  (document.InformantForm.type[2].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 2;} else if  (document.InformantForm.type[3].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 3;} else if  (document.InformantForm.type[4].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 4;} else if  (document.InformantForm.type[5].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 5;} else if  (document.InformantForm.type[6].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 6;} else if  (document.InformantForm.type[7].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 7;} else if  (document.InformantForm.type[8].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 8;} else if (document.InformantForm.type[9].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 9;} else if  (document.InformantForm.type[10].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 10;} else if  (document.InformantForm.type[11].checked==true) { document.InformantForm.hire.value = document.InformantForm.hire.value + 11;} else { document.InformantForm.hire.value = document.InformantForm.hire.value + 12; }}</script></head><body background=http://localhost/Images/mobkgnd.jpg bgcolor=black text=white oncontextmenu=return false;><div align=center><br><img src=http://localhost/Images/hireinformant_title.gif width=259 height=34 border=0><br><br><form name=InformantForm target=MOHire><input type=hidden value=a name=hire><input type=hidden value=* name=star><table border=0 cellpadding=5 cellspacing=0 width=90%><tr><td colspan=3 bgcolor=black>" + fontset + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hello. How can I be of help to you?</font></td></tr><tr><td colspan=2 bgcolor=#222222>" + fontset + "<b>On whom shall I gather information</b>?</font></td><td bgcolor=#222222 width=50% align=center><input type=text name=target size=25 maxlength=30></td></tr><tr><td colspan=3><br></td></tr><tr><td align=center bgcolor=#222222><input type=radio value=history name=type checked></td><td bgcolor=#222222 width=50%>" + fontset + "View his/her Character History.</font></td><td width=50% bgcolor=#222222 align=right>" + fontset + AdjustPrice(InformantCost[0]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td align=center bgcolor=#111111><input type=radio value=report name=type></td><td width=50% bgcolor=#111111>" + fontset + "Get his/her whereabouts.</font></td><td bgcolor=#111111 width=50% align=right>" + fontset + AdjustPrice(InformantCost[1]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#222222><input type=radio value=discipline name=type></td><td bgcolor=#222222 width=50%>" + fontset + "What is his/her current discipline?</font></td><td width=50% bgcolor=#222222 align=right>" + fontset + AdjustPrice(InformantCost[2]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=title name=type></td><td bgcolor=#111111 width=50%>" + fontset + "What is his/her title?</font></td><td bgcolor=#111111 width=50% align=right>" + fontset + AdjustPrice(InformantCost[3]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#222222><input type=radio value=race name=type></td><td bgcolor=#222222 width=50%>" + fontset + "What is his/her race and gender?</font></td><td width=50% bgcolor=#222222 align=right>" + fontset + AdjustPrice(InformantCost[4]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=allegiance name=type></td><td bgcolor=#111111 width=50%>" + fontset + "What is his/her allegiance?</font></td><td width=50% bgcolor=#111111 align=right>" + fontset + AdjustPrice(InformantCost[5]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#222222><input type=radio value=activity name=type></td><td bgcolor=#222222 width=50%>" + fontset + "What is he/she doing right now?</font></td><td width=50% bgcolor=#222222 align=right>" + fontset + AdjustPrice(InformantCost[6]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=activity name=type></td><td width=50% bgcolor=#111111>" + fontset + "How many allies does he/she have?</font></td><td width=50% bgcolor=#111111 align=right>" + fontset + AdjustPrice(InformantCost[7]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#222222><input type=radio value=mimm name=type></td><td bgcolor=#222222 width=50%>" + fontset + "What are his/her magical immunities?</font></td><td width=50% bgcolor=#222222 align=right>" + fontset + AdjustPrice(InformantCost[8]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=mweak name=type></td><td width=50% bgcolor=#111111>" + fontset + "What are his/her magical weaknesses?</font></td><td width=50% bgcolor=#111111 align=right>" + fontset + AdjustPrice(InformantCost[9]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#222222><input type=radio value=maimm name=type></td><td bgcolor=#222222 width=50%>" + fontset + "What are his/her material immunities?</font></td><td width=50% bgcolor=#222222 align=right>"+ fontset + AdjustPrice(InformantCost[10]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=maweak name=type></td><td width=50% bgcolor=#111111>" + fontset + "What are his/her magical weaknesses?</font></td><td width=50% bgcolor=#111111 align=right>" + fontset + AdjustPrice(InformantCost[11]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#222222><input type=radio value=maweak name=type></td><td width=50% bgcolor=#222222>" + fontset + "What are his/her Universal Character Rating (UCR)?</font></td><td width=50% bgcolor=#222222 align=right>" + fontset + AdjustPrice(InformantCost[12]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr></table><br>";

if (myplatform.equals("MAC")) {
hirehtml = hirehtml + "<table border=0 cellpadding=2 cellspacing=0 bgcolor=black><tr><td>&nbsp;<a href=javascript:GetHireValues();window.opener.document.MOMain.HireInformant(document.InformantForm.hire.value) target=MOHire><img src=" + imgurl + "submit_hire.gif border=0></a>&nbsp;</td></tr></table></form></div>";
} else {
hirehtml = hirehtml + "<table border=0 cellpadding=2 cellspacing=0 bgcolor=black><tr><td>&nbsp;<a href=# onClick=GetHireValues();window.opener.document.MOMain.HireInformant(document.InformantForm.hire.value) target=MOHire><img src=" + imgurl + "submit_hire.gif border=0></a>&nbsp;</td></tr></table></form></div>";
}

} else if (hiredtype.equals("messenger")) {
hirehtml = "<html><head><title>Hire Messenger</title><script language=Javascript>function GetMessengerValues() { document.MessengerForm.msg.value = document.MessengerForm.recipient.value + document.MessengerForm.star.value; document.MessengerForm.msg.value = document.MessengerForm.msg.value + document.MessengerForm.matter.value + document.MessengerForm.star.value; document.MessengerForm.msg.value = document.MessengerForm.msg.value + document.MessengerForm.message.value + document.MessengerForm.star.value; document.MessengerForm.msg.value = document.MessengerForm.msg.value + document.MessengerForm.forgeprice.value + document.MessengerForm.star.value; document.MessengerForm.msg.value = document.MessengerForm.msg.value +  document.MessengerForm.fsa.value + document.MessengerForm.star.value;document.MessengerForm.msg.value = document.MessengerForm.msg.value + document.MessengerForm.fsb.value + document.MessengerForm.star.value;if (document.MessengerForm.signature[0].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 0 +  document.MessengerForm.star.value; } else if (document.MessengerForm.signature[1].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 1 +  document.MessengerForm.star.value; } else if (document.MessengerForm.signature[2].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 2 + document.MessengerForm.star.value;} else { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 3 +  document.MessengerForm.star.value; }if (document.MessengerForm.msgimg[0].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 0; } else if (document.MessengerForm.msgimg[1].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 1; } else if (document.MessengerForm.msgimg[2].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 2; } else if (document.MessengerForm.msgimg[3].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 3;} else if (document.MessengerForm.msgimg[4].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 4; } else if (document.MessengerForm.msgimg[5].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 5; } else if (document.MessengerForm.msgimg[6].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 6; } else if (document.MessengerForm.msgimg[7].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 7; } else if (document.MessengerForm.msgimg[8].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 8;} else if (document.MessengerForm.msgimg[9].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 9;} else if (document.MessengerForm.msgimg[10].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 10; } else if (document.MessengerForm.msgimg[11].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 11;} else if (document.MessengerForm.msgimg[12].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 12; } else if (document.MessengerForm.msgimg[13].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 13;} else if (document.MessengerForm.msgimg[14].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 14;} else if (document.MessengerForm.msgimg[15].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 15;} else if (document.MessengerForm.msgimg[16].checked==true) { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 16;} else { document.MessengerForm.msg.value = document.MessengerForm.msg.value + 17; } document.MessengerForm.msg.value =  document.MessengerForm.msg.value + document.MessengerForm.star.value;document.MessengerForm.msg.value = document.MessengerForm.msg.value + document.MessengerForm.priority.options[document.MessengerForm.priority.selectedIndex].value + document.MessengerForm.star.value + document.MessengerForm.spcsign.options[document.MessengerForm.spcsign.selectedIndex].value; }</script></head><body background=http://localhost/Images/mobkgnd.jpg text=white oncontextmenu=return false;><div align=center><br><img src=http://localhost/Images/hiremessenger_title.gif width=265 height=32 border=0><br><br><form name=MessengerForm target=MOMessages><input type=hidden value=0 name=msg><input type=hidden value=* name=star><table border=0 cellpadding=4 cellspacing=0><tr><td width=68 bgcolor=black>" + fontset + "Send to:</font></td><td bgcolor=black><font face=Verdana size=-2><input type=text name=recipient size=30 maxlength=30></font></td></tr><tr><td width=68 bgcolor=#333333>" + fontset + "Matter:</font></td><td bgcolor=#333333><input type=text name=matter size=30 maxlength=50></td></tr><tr><td width=68 bgcolor=black><font face=Verdana size=-2>Priority:</font></td><td bgcolor=black align=center>";

if (CharacterStatus[15].equals("Town Hall")) { hirehtml = hirehtml + "<select name=priority size=1><option value=8>Lowest (Free)</option>";
} else if (CharacterStatus[15].equals("Inn")) { hirehtml = hirehtml + "<select name=priority size=1><option value=7>Lower (25 cc.)</option>";
} else if (CharacterStatus[15].equals("Tavern")) { hirehtml = hirehtml + "<select name=priority size=1><option value=6>Low (50 cc.)</option>";
} else { hirehtml = hirehtml + "<select name=priority size=1><option value=8>Lowest (Free)</option><option value=7>Lower (25 cc.)</option><option value=6>Low (50 cc.)</option><option value=5 selected>Normal (75 cc.)</option><option value=4>Urgent (150 cc.)</option><option value=3>High (300 cc.)</option><option value=2>Higher (600 cc.)</option><option value=1>Highest (1000 cc.)</option><option value=0>Critical (5000 cc.)</option>";
}
hirehtml = hirehtml + "</select></td></tr></table><br>" + tbcc+ "<tr><td bgcolor=black>" + divc + fontset + "Type your Message:</font></div></td></tr><tr><td bgcolor=#333333>" + divc + "<textarea name=message cols=77 rows=6></textarea></div></td></tr></table><br><table border=0 cellpadding=4 cellspacing=0 bgcolor=#333333 width=75%><tr><td align=center bgcolor=black colspan=6>" + fontset + "Select an Image:</font></td></tr><tr><td><input type=radio value=0 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/yellow_happy01.gif></td><td><input type=radio value=1 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/angry01.gif></td><td><input type=radio value=2 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/inquiring.gif></td></tr><tr><td><input type=radio value=3 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/yellow_happy02.gif></td><td><input type=radio value=4 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/angry02.gif></td><td><input type=radio value=5 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/unsure.gif></td></tr><tr><td><input type=radio value=6 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/yellow_happy03.gif></td><td><input type=radio value=7 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/angry03.gif></td><td><input type=radio value=8 name=msgimg></td><td width=33%><img src=" + imgurl + "smileys/dead.gif></td></tr><tr><td><input type=radio value=9 name=msgimg></td><td width=33%><img src=http://localhost/Images/smileys/tongue.gif></td><td><input type=radio value=10 name=msgimg></td><td width=33%><img src=http://localhost/Images/smileys/angry04.gif></td><td><input type=radio name=msgimg value=11></td><td width=33%><img src=http://localhost/Images/smileys/blush.gif></td></tr><tr><td><input type=radio value=12 name=msgimg></td><td width=33%><img src=http://localhost/Images/smileys/bigmouth.gif></td><td><input type=radio value=13 name=msgimg></td><td width=33%><img src=http://localhost/Images/smileys/angry05.gif></td><td><input type=radio value=14 name=msgimg></td><td width=33%><img src=http://localhost/Images/smileys/oh.gif></td></tr><tr><td><input type=radio value=15 name=msgimg></td><td width=33%><img src=http://localhost/Images/smileys/idea.gif></td><td><input type=radio value=16 name=msgimg></td><td width=33%><img src=http://localhost/Images/smileys/crying.gif></td><td><input type=radio name=msgimg checked value=17></td><td width=33%><font size=-2 face=Verdana color=white>None</font></td></tr></table><br><table border=0 cellpadding=4 cellspacing=0 width=75% bgcolor=#333333><tr><td bgcolor=black align=center><font face=Verdana size=-2>Message Signature</font></td></tr><tr><td><font face=Verdana size=-2><input type=radio value=a name=signature checked> Send with your signature</font></td></tr><tr><td>" + fontset + "<input type=radio value=b name=signature> </font>" + fontset + "Send as <select name=spcsign size=1><option value=4>Anonymous</option><option value=3>An Enemy</option><option value=2>A Secret Admirer</option><option value=1>Your Loyal Servant</option><option value=0>A Friend</option></select></font></td></tr><tr><td>" + fontset + "<input type=radio value=c name=signature> </font>" + fontset + "Forge this signature: </font>" + fontset + "<input type=text name=fsa size=30 maxlength=30 value=Signature></font></td></tr><tr><td>" + fontset + "<input type=radio value=d name=signature> </font>" + fontset + "Pay thief to forge this signature: </font>" + fontset + "<input type=text name=fsb size=30 maxlength=30 value=Signature></font></td></tr><tr><td>" + fontset + "How much will you pay the thief to forge this signature: </font>" + fontset + "<input type=text name=forgeprice size=5 maxlength=25> </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></table><br><table border=0 cellpadding=2 cellspacing=0 bgcolor=black><tr><td>&nbsp;";
if (myplatform.equals("MAC")) {
hirehtml = hirehtml + "<a href=javascript:GetMessengerValues();window.opener.document.MOMain.HireMessenger(document.MessengerForm.msg.value) target=MOMessages><img src=http://localhost/Images/submit_hire.gif border=0></a>&nbsp;</td></tr></table></form></div></body></html>";
} else {
hirehtml = hirehtml + "<a href=# onClick=GetMessengerValues();window.opener.document.MOMain.HireMessenger(document.MessengerForm.msg.value) target=MOMessages><img src=http://localhost/Images/submit_hire.gif border=0></a>&nbsp;</td></tr></table></form></div></body></html>"; }
// END HIRED MESSENGERTYPES

} else if (hiredtype.equals("sage")) {
//hirehtml = "<html><head><title>Hire a Sage</title><script language=Javascript>function GetWizard() {if (document.HireWizardForm.myhire[0].checked==true) { document.HireWizardForm.hire.value = 0;} else if  (document.HireWizardForm.myhire[1].checked==true) { document.HireWizardForm.hire.value = 1;} else if  (document.HireWizardForm.myhire[2].checked==true) { document.HireWizardForm.hire.value = 2;} else if  (document.HireWizardForm.myhire[3].checked==true) { document.HireWizardForm.hire.value = 3;} else if  (document.HireWizardForm.myhire[4].checked==true) { document.HireWizardForm.hire.value = 4;} else if  (document.HireWizardForm.myhire[5].checked==true) { document.HireWizardForm.hire.value = 5;} else if  (document.HireWizardForm.myhire[6].checked==true) { document.HireWizardForm.hire.value = 6;} else if  (document.HireWizardForm.myhire[7].checked==true) { document.HireWizardForm.hire.value = 7;} else if  (document.HireWizardForm.myhire[8].checked==true) { document.HireWizardForm.hire.value = 8;} else if  (document.HireWizardForm.myhire[9].checked==true) { document.HireWizardForm.hire.value = 9;} else if  (document.HireWizardForm.myhire[10].checked==true) { document.HireWizardForm.hire.value = 10;} else { document.HireWizardForm.hire.value = 11;}}</script></head><body background=" + imgurl + "mobkgnd.jpg text=white>" + divc + "<br><form name=HireWizardForm target=MOHire><img src=" + imgurl + "hiresage_title.gif border=0><br><input type=hidden value=0 name=hire><br><table border=0 cellpadding=4 cellspacing=0 width=75% bgcolor=#222222><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Restore Toughness</font></td></tr><tr><td bgcolor=#111111><input type=radio value=0 name=myhire checked></td><td bgcolor=#111111>" + fontset + "25% Toughness Restoration</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[0], 0) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=1 name=myhire></td><td bgcolor=#111111>" + fontset + "50% Toughness Restoration</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[1], 1) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=2 name=myhire></td><td bgcolor=#111111>" + fontset + "75% Toughness Restoration</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[2], 2) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=3 name=myhire></td><td bgcolor=#111111>" + fontset + "Full Toughness Restoration</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[3], 3) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Blinded Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=4 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Blinded Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[4], 4) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=5 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Blinded Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[5], 5) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Paralyzed Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=6 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Paralyzed Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[6], 6) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=7 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Paralyzed Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[7], 7) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Poisonned Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=8 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Poisonned Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[8], 8) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=9 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Poisonned Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[9], 9) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Tired Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=10 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Tired Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[10], 10) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=11 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Tired Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[11], 11) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr></table><br>";
hirehtml = "<html><head><title>Hire a Sage</title><script language=Javascript>function GetWizard() {if (document.HireWizardForm.myhire[0].checked==true) { document.HireWizardForm.hire.value = 0;} else if  (document.HireWizardForm.myhire[1].checked==true) { document.HireWizardForm.hire.value = 1;} else if  (document.HireWizardForm.myhire[2].checked==true) { document.HireWizardForm.hire.value = 2;} else if  (document.HireWizardForm.myhire[3].checked==true) { document.HireWizardForm.hire.value = 3;} else if  (document.HireWizardForm.myhire[4].checked==true) { document.HireWizardForm.hire.value = 4;} else if  (document.HireWizardForm.myhire[5].checked==true) { document.HireWizardForm.hire.value = 5;} else if  (document.HireWizardForm.myhire[6].checked==true) { document.HireWizardForm.hire.value = 6;} else if  (document.HireWizardForm.myhire[7].checked==true) { document.HireWizardForm.hire.value = 7;} else if  (document.HireWizardForm.myhire[8].checked==true) { document.HireWizardForm.hire.value = 8;} else if  (document.HireWizardForm.myhire[9].checked==true) { document.HireWizardForm.hire.value = 9;} else if  (document.HireWizardForm.myhire[10].checked==true) { document.HireWizardForm.hire.value = 10;} else if  (document.HireWizardForm.myhire[11].checked==true) { document.HireWizardForm.hire.value = 11;} else if  (document.HireWizardForm.myhire[12].checked==true) { document.HireWizardForm.hire.value = 12;} else { document.HireWizardForm.hire.value = 13;}}</script></head><body background=" + imgurl + "mobkgnd.jpg text=white>" + divc + "<br><form name=HireWizardForm target=MOHire><img src=" + imgurl + "hiresage_title.gif border=0><br><input type=hidden value=0 name=hire><br><table border=0 cellpadding=4 cellspacing=0 width=75% bgcolor=#222222><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Restore Toughness</font></td></tr><tr><td bgcolor=#111111><input type=radio value=0 name=myhire checked></td><td bgcolor=#111111>" + fontset + "25% Toughness Restoration</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[0], 0) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=1 name=myhire></td><td bgcolor=#111111>" + fontset + "50% Toughness Restoration</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[1], 1) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=2 name=myhire></td><td bgcolor=#111111>" + fontset + "75% Toughness Restoration</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[2], 2) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=3 name=myhire></td><td bgcolor=#111111>" + fontset + "Full Toughness Restoration</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[3], 3) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Blinded Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=4 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Blinded Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[4], 4) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=5 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Blinded Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[5], 5) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Paralyzed Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=6 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Paralyzed Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[6], 6) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=7 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Paralyzed Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[7], 7) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Poisonned Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=8 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Poisonned Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[8], 8) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=9 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Poisonned Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[9], 9) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Tired Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=10 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Tired Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[10], 10) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=11 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Tired Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[11], 11) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=black colspan=3>" + fontset + "&nbsp;&nbsp;&nbsp;Heal Tired Status</font></td></tr><tr><td bgcolor=#111111><input type=radio value=12 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Health Status Fully</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[12], 12) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=13 name=myhire></td><td bgcolor=#111111>" + fontset + "Heal Health Status Partially</font></td><td align=right bgcolor=#111111>" + fontset + HireSageCosts(SageCost[13], 13) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr></table><br>";
if (myplatform.equals("MAC")) {
hirehtml = hirehtml + "<br><table border=0 cellpadding=4 cellspacing=0 bgcolor=black><tr><td><a href=javascript:GetWizard();window.opener.document.MOMain.HireSage(document.HireWizardForm.hire.value) target=MOMessages><img src=" + imgurl + "submit_hire.gif border=0></a></table><br>";
} else {
hirehtml = hirehtml + "<br><table border=0 cellpadding=4 cellspacing=0 bgcolor=black><tr><td><a href=# onClick=GetWizard();window.opener.document.MOMain.HireSage(document.HireWizardForm.hire.value) target=MOMessages><img src=" + imgurl+ "submit_hire.gif border=0></a></table><br>";
}
} else { hirehtml = starthtml + divc + tbr + fontset + "<b>This hire type does not exist.</b>"; }
hirehtml = hirehtml  + closewindow + endhtml;
OutputHTML( hirehtml, "MOHire" );
hirehtml = null;
} // END void PrintHireScreen()


// Executes Hire Sage
public void HireSage(String sageval) {
VerifyOnlineStatus();
VerifySubLocationAction("sage");
UpdateStats();
boolean sagepaid = true;
String sagepaidhtml = "";

if (sageval.equals("0")) { sagepaid = PayItem((HireSageCosts(SageCost[0], 0)), 1, false);
if (sagepaid == true) { CharacterStats[6] = (new Integer(Integer.parseInt(CharacterStats[6]) + (int) ((Integer.parseInt(CharacterStats[8]) - Integer.parseInt(CharacterStats[6])) * 0.25))).toString(); } else {}
} else if (sageval.equals("1")) { sagepaid = PayItem((HireSageCosts(SageCost[1], 1)), 1, false);
if (sagepaid == true) { CharacterStats[6] = (new Integer(Integer.parseInt(CharacterStats[6]) + (int) ((Integer.parseInt(CharacterStats[8]) - Integer.parseInt(CharacterStats[6])) * 0.5))).toString(); } else {}
} else if (sageval.equals("2")) { sagepaid = PayItem((HireSageCosts(SageCost[2], 2)), 1, false);
if (sagepaid == true) { CharacterStats[6] = (new Integer(Integer.parseInt(CharacterStats[6]) + (int) ((Integer.parseInt(CharacterStats[8]) - Integer.parseInt(CharacterStats[6])) * 0.75))).toString(); } else {}
} else if (sageval.equals("3")) { sagepaid = PayItem((HireSageCosts(SageCost[3], 3)), 1, false);
if (sagepaid == true) { CharacterStats[6] = CharacterStats[8]; } else {}
} else if (sageval.equals("4")) { sagepaid = PayItem(HireSageCosts(SageCost[4], 4), 1, false);
if (sagepaid == true) { BlindedModifier(-5); } else {}
} else if (sageval.equals("5")) { sagepaid = PayItem(HireSageCosts(SageCost[5], 5), 1, false);
if (sagepaid == true) { BlindedModifier(-1); } else {}
} else if (sageval.equals("6")) { sagepaid = PayItem(HireSageCosts(SageCost[6], 6), 1, false);
if (sagepaid == true) { ParalyzedModifier(-5); } else {}
} else if (sageval.equals("7")) { sagepaid = PayItem(HireSageCosts(SageCost[7], 7), 1, false);
if (sagepaid == true) { ParalyzedModifier(-1); } else {}
} else if (sageval.equals("8")) { sagepaid = PayItem(HireSageCosts(SageCost[8], 8), 1, false);
if (sagepaid == true) { PoisonnedModifier(-5); } else {}
} else if (sageval.equals("9")) { sagepaid = PayItem(HireSageCosts(SageCost[9], 9), 1, false);
if (sagepaid == true) { PoisonnedModifier(-1); } else {}
} else if (sageval.equals("10")) { sagepaid = PayItem(HireSageCosts(SageCost[10], 10), 1, false);
if (sagepaid == true) { TiredModifier(-5); } else {}
} else if (sageval.equals("11")) { sagepaid = PayItem(HireSageCosts(SageCost[11], 11), 1, false);
if (sagepaid == true) { TiredModifier(-1); } else {}
} else if (sageval.equals("12")) { sagepaid = PayItem(HireSageCosts(SageCost[12], 12), 1, false);
if (sagepaid == true) { HealthModifier(-5); } else {}
} else if (sageval.equals("13")) { sagepaid = PayItem(HireSageCosts(SageCost[13], 13), 1, false);
if (sagepaid == true) { HealthModifier(-1); } else {}

} else {}

if (sagepaid == true) {
sagepaidhtml = starthtml  + divc + tbr + fontset + "<b>You have paid the sage for his/her services.</b>" + tbr + closewindow + endhtml;
} else {
sagepaidhtml = starthtml  + divc + tbr + fontset + nomon + tbr + closewindow + endhtml;
}

UpdateStats();
OutputHTML( sagepaidhtml, "MOHire" );
sagepaidhtml = null;
} // END void HireWizard


// Executes Hire sageard
protected int HireSageCosts(int sagefcost, int sagetype) {
int fsagecalst = 0;
int myneed = 0;

if (sagetype == 0) {
// Restore 25%
fsagecalst = AdjustPrice( (int) ((Integer.parseInt(CharacterStats[8]) - Integer.parseInt(CharacterStats[6])) * 0.25 ) * sagefcost);
} else if (sagetype == 1) {
// Restore 50%
fsagecalst = AdjustPrice( (int) ((Integer.parseInt(CharacterStats[8]) - Integer.parseInt(CharacterStats[6])) * 0.5 ) * sagefcost);
} else if (sagetype == 2) {
// Restore 75%
fsagecalst = AdjustPrice( (int) ((Integer.parseInt(CharacterStats[8]) - Integer.parseInt(CharacterStats[6])) * 0.75 ) * sagefcost);
} else if (sagetype == 3) {
// Restore 100%
fsagecalst = AdjustPrice( (int) ((Integer.parseInt(CharacterStats[8]) - Integer.parseInt(CharacterStats[6])) * 1 ) * sagefcost);
} else if (sagetype == 4) {
// Full Blindness

if (CharacterStats[63].equals(Blind[5])) { myneed = 5;
} else if ( CharacterStats[63].equals(Blind[4])) { myneed = 4;
} else if ( CharacterStats[63].equals(Blind[3])) { myneed = 3;
} else if ( CharacterStats[63].equals(Blind[2])) { myneed = 2;
} else if ( CharacterStats[63].equals(Blind[1])) { myneed = 1;
} else {}
fsagecalst = AdjustPrice( (int) (myneed * sagefcost) );

} else if (sagetype == 6) {
// Full Paralyzed

if (CharacterStats[62].equals(Paralyzed[5])) { myneed = 5;
} else if ( CharacterStats[62].equals(Paralyzed[4])) { myneed = 4;
} else if ( CharacterStats[62].equals(Paralyzed[3])) { myneed = 3;
} else if ( CharacterStats[62].equals(Paralyzed[2])) { myneed = 2;
} else if ( CharacterStats[62].equals(Paralyzed[1])) { myneed = 1;
} else {}
fsagecalst = AdjustPrice( (int) (myneed * sagefcost) );

} else if (sagetype == 8) {
// Full Poisonned

if (CharacterStats[61].equals(Poisoned[5])) { myneed = 5;
} else if ( CharacterStats[61].equals(Poisoned[4])) { myneed = 4;
} else if ( CharacterStats[61].equals(Poisoned[3])) { myneed = 3;
} else if ( CharacterStats[61].equals(Poisoned[2])) { myneed = 2;
} else if ( CharacterStats[61].equals(Poisoned[1])) { myneed = 1;
} else {}
fsagecalst = AdjustPrice( (int) (myneed * sagefcost) );

} else if (sagetype == 10) {
// Full Tired

if (CharacterStats[60].equals(Tired[5])) { myneed = 5;
} else if ( CharacterStats[60].equals(Tired[4])) { myneed = 4;
} else if ( CharacterStats[60].equals(Tired[3])) { myneed = 3;
} else if ( CharacterStats[60].equals(Tired[2])) { myneed = 2;
} else if ( CharacterStats[60].equals(Tired[1])) { myneed = 1;
} else {}
fsagecalst = AdjustPrice( (int) (myneed * sagefcost) );

} else if (sagetype == 5) { // Part Blindness
if (CharacterStats[63].equals(" ")) {} else { fsagecalst = AdjustPrice(sagefcost); }
} else if (sagetype == 7) { // Part Paralyzed
if (CharacterStats[62].equals(" ")) {} else { fsagecalst = AdjustPrice(sagefcost); }
} else if (sagetype == 9) { // Part Poisonned
if (CharacterStats[61].equals(" ")) {} else { fsagecalst = AdjustPrice(sagefcost); }
} else if (sagetype == 11) { // Part Tired
if (CharacterStats[60].equals(" ")) {} else { fsagecalst = AdjustPrice(sagefcost); }

} else if (sagetype == 12) {
// Full Health
if (CharacterStats[65].equals(Health[5])) { myneed = 5;
} else if ( CharacterStats[65].equals(Health[4])) { myneed = 4;
} else if ( CharacterStats[65].equals(Health[3])) { myneed = 3;
} else if ( CharacterStats[65].equals(Health[2])) { myneed = 2;
} else if ( CharacterStats[65].equals(Health[1])) { myneed = 1;
} else {}
fsagecalst = AdjustPrice( (int) (myneed * sagefcost) );

} else if (sagetype == 13) { // Part Health
if (CharacterStats[65].equals(" ")) {} else { fsagecalst = AdjustPrice(sagefcost); }

} else {}

if (fsagecalst < 0) { fsagecalst = 0; } else {}

return fsagecalst;
} // END HireSageCosts


// Executes Hire Messenger: Town Hall, Inn, Tavern, Messagery
public void HireMessenger(String msgval) {
//0 recipient ; 1 matter ; 2 message ; 3 forgeprice ; 4 fsa ; 5 fsb ; 6 signature ; 7 msgimg ; 8 priority ; 9 spcsign
VerifyOnlineStatus();
VerifySubLocationAction("messenger");
UpdateStats();
String[] MessengerCost = {"5000", "1000", "600", "300", "150", "75", "50", "25", "0"};
String[] MessengerImage = {"yellow_happy01.gif", "angry01.gif", "inquiring.gif", "yellow_happy02.gif", "angry02.gif", "unsure.gif", "yellow_happy03.gif", "angry03.gif", "dead.gif", "tongue.gif", "angry04.gif", "blush.gif", "bigmouth.gif", "angry05.gif", "oh.gif", "idea.gif", "crying.gif", "none.gif"};
String[] MessengerSpcSigns = {"A Friend", "Your Loyal Servant", "A Secret Admirer", "An Enemy", "Anonymous"};
String messengerhtml = "";
String msgexecute = "";

String[] messengertks = new String[10];
StringTokenizer messengertkss = new StringTokenizer(msgval,"*",false);
int messengertkssize = 0;

while (messengertkss.hasMoreTokens()) {
messengertks[messengertkssize] = messengertkss.nextToken();
messengertkssize = messengertkssize + 1; }

boolean messengerpaid = PayItem(Integer.parseInt(MessengerCost[Integer.parseInt(messengertks[8])]), 1, true);

if (messengertks[0].length() >= 1 && messengertks[1].length() >= 1 && messengertks[2].length() >= 1 && messengertks[6].length() >= 1 && messengertks[8].length() >= 1 && messengerpaid == true) {

if (messengertks[6].equals("1")) {
// MY SPECIAL SIGNATURE (ANONYMOUS, SPECIAL ETC)
msgexecute = "n*" + messengertks[8] + "*" + messengertks[0] + "*" + messengertks[1] + "*" + MessengerSpcSigns[Integer.parseInt(messengertks[9])] + "*" + myCharacter + "*" + MessengerImage[Integer.parseInt(messengertks[1])] + "*" + messengertks[2];
} else if (messengertks[6].equals("2")) {

} else if (messengertks[6].equals("3")) {

} else { 
// MY SIGNATURE
msgexecute = "n*" + messengertks[8] + "*" + messengertks[0] + "*" + messengertks[1] + "*" + myCharacter + "*" + myCharacter + "*" + MessengerImage[Integer.parseInt(messengertks[1])] + "*" + messengertks[2];
}

} else { messengerhtml = divc + tbr + fontset + nomon + tbr; }
String callmsgsrvlt = "";
callmsgsrvlt = MOWorldCall("MOMessenger", msgexecute);
if (callmsgsrvlt.equals("error")) { messengerhtml = starthtml + divc + tbr + fontset + anerr + tbr  + closewindow; } else { messengerhtml = starthtml + divc + tbr + fontset + "<b>Your message was sent.</b>" + tbr  + closewindow; }
OutputHTML( messengerhtml, "MOMessages" );
messengerhtml = null; messengertks = null; messengertkss = null; MessengerCost = null; MessengerImage = null; MessengerSpcSigns = null; msgexecute = null;
} // END HIRED HireMessenger



// Executes Hire Informant: Town Hall
public void HireInformant(String infval) {
VerifyOnlineStatus();
VerifySubLocationAction("informant");
UpdateStats();
String informanthtml = "";

String[] informanttks = new String[2];
StringTokenizer informanttkss = new StringTokenizer(infval,"*",false);
int informanttkssize = 0;

while (informanttkss.hasMoreTokens()) {
informanttks[informanttkssize] = informanttkss.nextToken();
informanttkssize = informanttkssize + 1; }

boolean informantpaid = PayItem(InformantCost[Integer.parseInt(informanttks[1])], 1, true);

if (informanttks[0].length() >= 1 && informantpaid == true) {

if (informanttks[1].equals("0")) {

informanthtml = MOWorldCall("MOInformant", informanttks[0] + "*" + informanttks[1]);
if (informanthtml.equals("error")) { informanthtml = divc + tbr + fontset + anerr + tbr; return; } else {}

String[] informanthisttks = new String[2];
StringTokenizer informanthisttkss = new StringTokenizer(informanthtml,"***MOHISTORYEND***",true);
int informanthisttksize = 0;

while (informanthisttkss.hasMoreTokens() && informanthisttksize < 2) {
informanthisttks[informanthisttksize] = informanthisttkss.nextToken();
informanthisttksize = informanthisttksize + 1; }

informanthtml = informanthisttks[0];

} else if (informanttks[1].equals("1") || informanttks[1].equals("2") || informanttks[1].equals("3") || informanttks[1].equals("4") || informanttks[1].equals("5") || informanttks[1].equals("6")) {

informanthtml = MOWorldCall("MOInformant", informanttks[0] + "*" + informanttks[1]);
if (informanthtml.equals("error")) { informanthtml = divc + tbr + fontset + anerr + tbr; return; } else {}

String[] informanthisttks = new String[22];
StringTokenizer informanthisttkss = new StringTokenizer(informanthtml,"*",false);
int informanthisttksize = 0;

while (informanthisttkss.hasMoreTokens() && informanthisttksize < 22) {
informanthisttks[informanthisttksize] = informanthisttkss.nextToken();
informanthisttksize = informanthisttksize + 1; }

if (informanttks[1].equals("1")) {
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the whereabouts of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Continent: " + informanthisttks[12] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Province: " + informanthisttks[13] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Location: " + informanthisttks[14] + "</font></td></tr></table>" + tbr + "</center>";
} else if (informanttks[1].equals("2")) {
if (informanthisttks[8].equals(" ")) { informanthisttks[8] = "No Discipline"; } else {}
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the discipline of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Discipline: " + informanthisttks[8] + "</font></td></tr></table>" + tbr + "</center>";
} else if (informanttks[1].equals("3")) {
if (informanthisttks[7].equals("Trial")) { informanthisttks[7] = "Undergoing the Trial";
} else if (informanthisttks[7].equals("Staff")) { informanthisttks[7] = "Legend"; } else {}
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the title of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Title: " + informanthisttks[7] + "</font></td></tr></table>" + tbr + "</center>";
} else if (informanttks[1].equals("4")) {
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the race and gender of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Race: " + informanthisttks[2] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Gender: " + informanthisttks[3] + "</font></td></tr></table>" + tbr + "</center>";
} else if (informanttks[1].equals("5")) {
if (informanthisttks[10].equals(" ")) { informanthisttks[10] = "Not known to be part of an alliance."; informanthisttks[11] = "Not known to be part of an alliance."; } else {}
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the allegiance of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Alliance Name: " + informanthisttks[10] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Rank in Alliance: " + informanthisttks[11] + "</font></td></tr></table>" + tbr + "</center>";
} else { 
if (informanthisttks[16].equals(" ")) { informanthisttks[16] = " not known to be doing anything in particular.";
} else if (informanthisttks[16].equals("search")) { informanthisttks[16] = " searching.";
} else if (informanthisttks[16].equals("travel") || informanthisttks[16].equals("goto")) { informanthisttks[16] = " traveling.";
} else if (informanthisttks[16].equals("rest")) { informanthisttks[16] = " resting.";
} else if (informanthisttks[16].equals("dead")) { informanthisttks[16] = " being raised from the dead.";
} else if (informanthisttks[16].equals("loot")) { informanthisttks[16] = " recovering a loot.";
} else { informanthisttks[16] = " not known to be doing anything in particular."; }
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the current activity of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "This character is currently" + informanthisttks[16] + "</font></td></tr></table>" + tbr + "</center>"; }

} else if (informanttks[1].equals("8") || informanttks[1].equals("9") || informanttks[1].equals("10") || informanttks[1].equals("11") || informanttks[1].equals("12")) {
informanthtml = MOWorldCall("MOInformant", informanttks[0] + "*" + informanttks[1]);
if (informanthtml.equals("error")) { informanthtml = divc + tbr + fontset + anerr + tbr; return; } else {}

String[] informanthisttks = new String[69];
StringTokenizer informanthisttkss = new StringTokenizer(informanthtml,"*",false);
int informanthisttksize = 0;

while (informanthisttkss.hasMoreTokens() && informanthisttksize < 69) {
informanthisttks[informanthisttksize] = informanthisttkss.nextToken();
informanthisttksize = informanthisttksize + 1; }

// Fetch Mag Immunities
// Fetch Mag Weaknesses
// Fetch Mat Immunities
// Fetch Mat Weaknesses
// Fetch UCR

if (informanttks[1].equals("8")) {
if (informanthisttks[58].equals(" ") || informanthisttks[58].equals("0")) { informanthisttks[58] = "none."; } else {}
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset+ "Information on the Magic Immunities of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Magic Immunities: " + informanthisttks[58] + "</font></td></tr></table>" + tbr + "</center>";
} else if (informanttks[1].equals("9")) {
if (informanthisttks[65].equals(" ") || informanthisttks[65].equals("0")) { informanthisttks[65] = "none."; } else {}
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the Magic Weaknesses of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Magic Weaknesses: " + informanthisttks[65] + "</font></td></tr></table>" + tbr + "</center>";
} else if (informanttks[1].equals("10")) {
if (informanthisttks[66].equals(" ") || informanthisttks[66].equals("0")) { informanthisttks[66] = "none."; } else {}
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the Material Immunities of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Material Immunities: " + informanthisttks[66] + "</font></td></tr></table>" + tbr + "</center>";
} else if (informanttks[1].equals("11")) {
if (informanthisttks[67].equals(" ") || informanthisttks[67].equals("0")) { informanthisttks[67] = "none."; } else {}
informanthtml = starthtml + "<center><table border=0 cellpadding=4 cellspacing=1 width=75%><tr><td align=center bgcolor=black>" + fontset + "Information on the Material Weaknesses of " + informanttks[0] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "Material Weaknesses: " + informanthisttks[67] + "</font></td></tr></table>" + tbr + "</center>";
} else if (informanttks[1].equals("7")) {
// Fetch Allies
} else { 
// Fetch UCR
informanthtml = starthtml + ""; }
} else { informanthtml = divc + tbr + fontset + anerr + tbr; }
} else { informanthtml = divc + tbr + fontset + nomon + tbr; }

informanthtml = informanthtml  + closewindow;

OutputHTML( informanthtml, "MOHire" );
informanthtml = null; informanttkss = null; informanttkssize = 0; infval = null;
} // END void HireInformant()


public void ThreeGambleStart(String threeoption) {
VerifyOnlineStatus();
VerifySubLocationAction("gamble");
UpdateStats();
String threegamblehtmla = "";
String threegamblehtmlb = "";

String[] threegambletks = new String[5];
StringTokenizer threegambletkss = new StringTokenizer(threeoption,"*",false);
int threegambletkssize = 0;

while (threegambletkss.hasMoreTokens()) {
threegambletks[threegambletkssize] = threegambletkss.nextToken();
threegambletkssize = threegambletkssize + 1;
}

threegambletks[1] = (new Integer(Integer.parseInt(threegambletks[1]) + 1)).toString();

    if (threegambletks[0].equals("start")) {
threegamblehtmla = divc + "<br><img src=" + imgurl+ "gamble_title.gif border=0><br><br><form name=GambleForm target=MOGamble><input type=hidden value=play name=start><input type=hidden value=* name=star><table border=0 cellpadding=5 cellspacing=0><tr><td align=center colspan=2 bgcolor=black>" + fontset + "<b>First Pick</b></font></td><td align=center colspan=2 bgcolor=#444444>" + fontset + "<b>Second Pick</b></font></td><td align=center colspan=2 bgcolor=black>" + fontset + "<b>Third Pick</b></font></td></tr><tr><td align=right bgcolor=black>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=radio value=1 name=first checked></td><td align=left bgcolor=black><img src=" + imgurl+ "3pack1.gif border=0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align=right bgcolor=#444444>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=radio value=1 name=second checked></td><td align=left bgcolor=#444444><img src=" + imgurl + "3pack1.gif border=0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align=right bgcolor=black>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=radio value=1 name=third checked></td><td align=left bgcolor=black><img src=" + imgurl + "3pack1.gif border=0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr><tr><td align=right bgcolor=black><input type=radio value=2 name=first></td><td align=left bgcolor=black><img src=" + imgurl + "3pack2.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=2 name=second></td><td align=left bgcolor=#444444><img src=" + imgurl + "3pack2.gif border=0></td><td align=right bgcolor=black><input type=radio value=2 name=third></td><td align=left bgcolor=black><img src=http://localhost/Images/3pack2.gif border=0></td></tr><tr><td align=right bgcolor=black><input type=radio value=3 name=first></td><td align=left bgcolor=black><img src=http://localhost/Images/3pack3.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=3 name=second></td><td align=left bgcolor=#444444><img src=http://localhost/Images/3pack3.gif border=0></td><td align=right bgcolor=black><input type=radio value=3 name=third></td><td align=left bgcolor=black><img src=http://localhost/Images/3pack3.gif border=0></td></tr></table><br>";
if (myplatform.equals("MAC")) {
threegamblehtmlb = tbcc + "<tr><td bgcolor=black>" + fontset + "Total Money in Copper Coins: " + totalmoney + "</font></td></tr><tr><td bgcolor=black>" + fontset + "&nbsp;Wager: <input type=text name=wager size=3 value=5 maxlength=3> Copper Coins</font></td></tr><tr><td bgcolor=black align=center><a href=javascript:GetGambleValues();window.opener.document.MOMain.ThreeGambleStart(document.GambleForm.start.value) target=MOGamble><img src=" + imgurl + "submit_gamble.gif border=0></td></tr></table></div>" + tbr;
} else {
threegamblehtmlb = tbcc + "<tr><td bgcolor=black>" + fontset + "Total Money in Copper Coins: " + totalmoney + "</font></td></tr><tr><td bgcolor=black>" + fontset + "&nbsp;Wager: <input type=text name=wager size=3 value=5 maxlength=3> Copper Coins</font></td></tr><tr><td bgcolor=black align=center><a href=# onClick=GetGambleValues();window.opener.document.MOMain.ThreeGambleStart(document.GambleForm.start.value) target=MOGamble><img src=" + imgurl + "submit_gamble.gif border=0></td></tr></table></div>" + tbr;
}
     } else if (Integer.parseInt(threegambletks[1]) >= 6 && Integer.parseInt(threegambletks[1]) <= 1000) {

boolean threepaid = PayItem(Integer.parseInt(threegambletks[1]), 1, true);
int randomthreea = 0;
int randomthreeb = 0;
int randomthreec = 0;
int threeprize = 0;
String threeresult = "";

String mythreea = threegambletks[2];
String mythreeb = threegambletks[3];
String mythreec = threegambletks[4];

if (threepaid == true) {
randomthreea = 1 + (int) ( Math.random() * 3 );
randomthreeb = 1 + (int) ( Math.random() * 3 );
randomthreec = 1 + (int) ( Math.random() * 3 );

if (Integer.parseInt(threegambletks[2]) == randomthreea && Integer.parseInt(threegambletks[3]) == randomthreeb && Integer.parseInt(threegambletks[4]) == randomthreec) { threeprize = (Integer.parseInt(threegambletks[1]) * 3) - 1; threeresult = "Wow! You win 3 times your wager."; GetPaid(threeprize, "r");
} else if (Integer.parseInt(threegambletks[2]) == randomthreea && Integer.parseInt(threegambletks[3]) == randomthreeb) { threeprize = (Integer.parseInt(threegambletks[1]) * 2) - 1; threeresult = "Two First! You win 2 times your wager."; GetPaid(threeprize, "r");
} else if (Integer.parseInt(threegambletks[2]) == randomthreea && Integer.parseInt(threegambletks[4]) == randomthreec) { threeprize = (Integer.parseInt(threegambletks[1]) * 2) - 1; threeresult = "First and Last! You win 2 times your wager."; GetPaid(threeprize, "r");
} else if (Integer.parseInt(threegambletks[3]) == randomthreeb && Integer.parseInt(threegambletks[4]) == randomthreec) { threeprize = (Integer.parseInt(threegambletks[1]) * 2) - 1; threeresult = "Last Two! You win 2 times your wager."; GetPaid(threeprize, "r");
} else { threeresult = "You lose your wager. Care to test your luck again?"; }

if (myplatform.equals("MAC")) {
threegamblehtmla = divc + "<table border=0 cellpadding=5 cellspacing=0 width=350><tr><td align=center colspan=3 bgcolor=black>" + fontset + "Chosen Sequence</font></td></tr><tr><td align=center bgcolor=black><img src=" + imgurl + "3pack" + mythreea + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "3pack" + mythreeb + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "3pack" + mythreec + ".gif border=0></td></tr></table><br><table border=0 cellpadding=5 cellspacing=0 width=350><tr><td align=center colspan=3 bgcolor=black>" + fontset + "Order in Which the Stones have been Thrown</font></td></tr><tr><td align=center bgcolor=black><img src=" + imgurl + "3pack" + randomthreea + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "3pack" + randomthreeb + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "3pack" + randomthreec + ".gif border=0></td></tr><tr><td align=center bgcolor=#222222 colspan=3>"+ fontset + threeresult + "</font></td></tr></table>" + tbr + "<form name=GambleForm target=MOGamble><input type=hidden value=start name=start><input type=hidden value=* name=star><input type=hidden value=0 name=wager><input type=hidden value=a name=first><input type=hidden value=b name=second><input type=hidden value=c name=third><table border=0 cellpadding=2 cellspacing=0><tr><td bgcolor=black align=center><a href=javascript:GetGambleValues();window.opener.document.MOMain.ThreeGambleStart(document.GambleForm.start.value) target=MOGamble><img src=http://localhost/Images/submit_gamble.gif border=0></a></td></form></tr></table><br><br><br></div>";
} else {
threegamblehtmla = divc + "<table border=0 cellpadding=5 cellspacing=0 width=350><tr><td align=center colspan=3 bgcolor=black>" + fontset + "Chosen Sequence</font></td></tr><tr><td align=center bgcolor=black><img src=" + imgurl + "3pack" + mythreea + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "3pack" + mythreeb + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "3pack" + mythreec + ".gif border=0></td></tr></table><br><table border=0 cellpadding=5 cellspacing=0 width=350><tr><td align=center colspan=3 bgcolor=black>" + fontset + "Order in Which the Stones have been Thrown</font></td></tr><tr><td align=center bgcolor=black><img src=" + imgurl + "3pack" + randomthreea + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "3pack" + randomthreeb + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "3pack" + randomthreec + ".gif border=0></td></tr><tr><td align=center bgcolor=#222222 colspan=3>" + fontset + threeresult + "</font></td></tr></table></div>" + tbr + "<form name=GambleForm target=MOGamble><input type=hidden value=start name=start><input type=hidden value=* name=star><input type=hidden value=0 name=wager><input type=hidden value=a name=first><input type=hidden value=b name=second><input type=hidden value=c name=third><table border=0 cellpadding=2 cellspacing=0><tr><td bgcolor=black align=center><a href=# onClick=GetGambleValues();window.opener.document.MOMain.ThreeGambleStart(document.GambleForm.start.value) target=MOGamble><center><img src=http://localhost/Images/submit_gamble.gif border=0></center></a></td></form></tr></table><br><br><br>";
}
threegamblehtmlb = "";
threegambletks[0] = "end";
threeprize = 0;
threeresult = null;
mythreea = null; mythreeb = null; mythreec = null;
} else { 
threegamblehtmla = divc + tbr + fontset + nomon + tbr;
threegamblehtmlb = ""; }

     } else { ThreeGambleStart("start*0*a*a*a"); }
String threegamblehtml =  "";
if (threegambletks[0].equals("end")) { threegamblehtml =  "<html><head><title>3 Stones</title><script language=Javascript>function GetGambleValues() {  document.GambleForm.start.value = document.GambleForm.start.value + document.GambleForm.star.value + document.GambleForm.wager.value + document.GambleForm.star.value + document.GambleForm.first.value + document.GambleForm.star.value + document.GambleForm.second.value + document.GambleForm.third.value;}</script></head><body text=white background=" + imgurl + "mobkgnd.jpg onLoad=javascript:window.opener.name=PlayFrame oncontextmenu=return false;>" + threegamblehtmla + threegamblehtmlb  + closewindow + endhtml;
} else { threegamblehtml =  "<html><head><title>3 Stones</title><script language=Javascript>function GetGambleValues() {  document.GambleForm.start.value = document.GambleForm.start.value + document.GambleForm.star.value + document.GambleForm.wager.value + document.GambleForm.star.value; if (document.GambleForm.first[0].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 1 + document.GambleForm.star.value; } else if  (document.GambleForm.first[1].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 2 + document.GambleForm.star.value; } else { document.GambleForm.start.value = document.GambleForm.start.value + 3 + document.GambleForm.star.value; } if (document.GambleForm.second[0].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 1 + document.GambleForm.star.value; } else if  (document.GambleForm.second[1].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 2 + document.GambleForm.star.value; } else { document.GambleForm.start.value = document.GambleForm.start.value + 3 + document.GambleForm.star.value; } if (document.GambleForm.third[0].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 1; } else if  (document.GambleForm.third[1].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 2; } else { document.GambleForm.start.value = document.GambleForm.start.value + 3; }}</script></head><body text=white background=http://localhost/Images/mobkgnd.jpg onLoad=javascript:window.opener.name=PlayFrame oncontextmenu=return false;>" + threegamblehtmla + threegamblehtmlb  + closewindow + endhtml;
}
OutputHTML( threegamblehtml, "MOGamble" );
threeoption = null; threegamblehtml = null; threegamblehtmla = null; threegamblehtmlb = null; threegambletks = null; threegambletkssize = 0; threegambletkss = null;
} // END void ThreeGambleStart()


public void FiveGambleStart(String fiveoption) {
VerifyOnlineStatus();
VerifySubLocationAction("gamble");
UpdateStats();
String fivegamblehtmla = "";
String fivegamblehtmlb = "";

String[] fivegambletks = new String[8];
StringTokenizer fivegambletkss = new StringTokenizer(fiveoption,"*",false);
int fivegambletkssize = 0;

while (fivegambletkss.hasMoreTokens()) {
fivegambletks[fivegambletkssize] = fivegambletkss.nextToken();
fivegambletkssize = fivegambletkssize + 1;
}

fivegambletks[1] = (new Integer(Integer.parseInt(fivegambletks[1]) + 1)).toString();

     if (fivegambletks[0].equals("start")) {
fivegamblehtmla = divc + "<br><img src=" + imgurl + "gamble_title.gif border=0><br><br><form name=GambleForm target=MOGamble><input type=hidden value=play name=start><input type=hidden value=* name=star><table border=0 cellpadding=5 cellspacing=0><tr><td align=center colspan=2 bgcolor=black>" + fontset + "<b>First Pick</b></font></td><td align=center colspan=2 bgcolor=#444444>" + fontset + "<b>Second Pick</b></font></td><td align=center bgcolor=black colspan=2>" + fontset + "<b>Third Pick</b></font></td><td align=center colspan=2 bgcolor=#444444>" + fontset + "<b>Fourth Pick</b></font></td><td align=center bgcolor=black colspan=2>" + fontset + "<b>Fifth Pick</b></font></td></tr><tr><td align=right bgcolor=black>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=radio value=a name=first checked></td><td align=left bgcolor=black><img src=" + imgurl + "5pack1.gif border=0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align=right bgcolor=#444444>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=radio value=a name=second checked></td><td align=left bgcolor=#444444><img src=" + imgurl + "5pack1.gif border=0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align=right bgcolor=black>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=radio value=a name=third checked></td><td align=left bgcolor=black><img src=" + imgurl + "5pack1.gif border=0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align=right bgcolor=#444444>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=radio value=a name=fourth checked></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack1.gif border=0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align=right bgcolor=black>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=radio value=a name=fifth checked></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack1.gif border=0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr><tr><td align=right bgcolor=black><input type=radio value=b name=first></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack2.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=b name=second></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack2.gif border=0></td><td align=right bgcolor=black><input type=radio value=b name=third></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack2.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=b name=fourth></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack2.gif border=0></td><td align=right bgcolor=black><input type=radio value=b name=fifth></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack2.gif border=0></td></tr><tr><td align=right bgcolor=black><input type=radio value=c name=first></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack4.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=c name=second></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack4.gif border=0></td><td align=right bgcolor=black><input type=radio value=c name=third></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack4.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=c name=fourth></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack4.gif border=0></td><td align=right bgcolor=black><input type=radio value=c name=fifth></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack4.gif border=0></td></tr><tr><td align=right bgcolor=black><input type=radio value=d name=first></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack5.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=d name=second></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack5.gif border=0></td><td align=right bgcolor=black><input type=radio value=d name=third></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack5.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=d name=fourth></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack5.gif border=0></td><td align=right bgcolor=black><input type=radio value=d name=fifth></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack5.gif border=0></td></tr><tr><td align=right bgcolor=black><input type=radio value=e name=first></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack3.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=e name=second></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack3.gif border=0></td><td align=right bgcolor=black><input type=radio value=e name=third></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack3.gif border=0></td><td align=right bgcolor=#444444><input type=radio value=e name=fourth></td><td align=left bgcolor=#444444><img src=http://localhost/Images/5pack3.gif border=0></td><td align=right bgcolor=black><input type=radio value=e name=fifth></td><td align=left bgcolor=black><img src=http://localhost/Images/5pack3.gif border=0></td></tr></table><br>";
if (myplatform.equals("MAC")) {
fivegamblehtmlb = tbcc + "<tr><td bgcolor=black>" + fontset + "Total Money in Copper Coins: " + totalmoney + "</font></td></tr><tr><td bgcolor=black>" + fontset + "Wager: <input type=text name=wager size=5 value=25 maxlength=5> Copper Coins</font></td></tr><tr><td bgcolor=black align=center><a href=javascript:GetGambleValues();window.opener.document.MOMain.FiveGambleStart(document.GambleForm.start.value) target=MOGamble><img src=" + imgurl+ "submit_gamble.gif border=0></td></tr></table></div>" + tbr + "</form></div>";
} else {
fivegamblehtmlb = tbcc + "<tr><td bgcolor=black>" + fontset + "Total Money in Copper Coins: " + totalmoney + "</font></td></tr><tr><td bgcolor=black>" + fontset + "Wager: <input type=text name=wager size=5 value=25 maxlength=5> Copper Coins</font></td></tr><tr><td bgcolor=black align=center><a href=# onClick=GetGambleValues();window.opener.document.MOMain.FiveGambleStart(document.GambleForm.start.value) target=MOGamble><img src=" + imgurl + "submit_gamble.gif border=0></td></tr></table></div>" + tbr + "</form></div>";
}
     } else if (Integer.parseInt(fivegambletks[1]) >= 26 && Integer.parseInt(fivegambletks[1]) <= 100000) {

boolean fivepaid = PayItem(Integer.parseInt(fivegambletks[1]), 1, true);
int randomfivea = 0;
int randomfiveb = 0;
int randomfivec = 0;
int randomfived = 0;
int randomfivee = 0;
int fiveprize = 0;
String fiveresult = "";

String myfivea = fivegambletks[2];
String myfiveb = fivegambletks[3];
String myfivec = fivegambletks[4];
String myfived = fivegambletks[5];
String myfivee = fivegambletks[6];

if (fivepaid == true) {
randomfivea = 1 + (int) ( Math.random() * 5 );
randomfiveb = 1 + (int) ( Math.random() * 5 );
randomfivec = 1 + (int) ( Math.random() * 5 );
randomfived = 1 + (int) ( Math.random() * 5 );
randomfivee = 1 + (int) ( Math.random() * 5 );

if (Integer.parseInt(fivegambletks[2]) == randomfivea && Integer.parseInt(fivegambletks[3]) == randomfiveb && Integer.parseInt(fivegambletks[4]) == randomfivec && Integer.parseInt(fivegambletks[5]) == randomfived && Integer.parseInt(fivegambletks[5]) == randomfivee) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 25) - 1; fiveresult = "WOW!!! You win 25 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[2]) == randomfivea && Integer.parseInt(fivegambletks[3]) == randomfiveb && Integer.parseInt(fivegambletks[5]) == randomfived && Integer.parseInt(fivegambletks[6]) == randomfivee) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 10) - 1; fiveresult = "Wow! You win 10 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[3]) == randomfiveb && Integer.parseInt(fivegambletks[4]) == randomfivec && Integer.parseInt(fivegambletks[5]) == randomfived && Integer.parseInt(fivegambletks[6]) == randomfivee) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 10) - 1; fiveresult = "Wow! You win 10 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[2]) == randomfivea && Integer.parseInt(fivegambletks[3]) == randomfiveb && Integer.parseInt(fivegambletks[4]) == randomfivec && Integer.parseInt(fivegambletks[5]) == randomfived && Integer.parseInt(fivegambletks[6]) == randomfivee) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 10) - 1; fiveresult = "Wow! You win 10 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[3]) == randomfiveb && Integer.parseInt(fivegambletks[4]) == randomfivec && Integer.parseInt(fivegambletks[5]) == randomfived) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 5) - 1; fiveresult = "Not bad! You win 5 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[4]) == randomfivec && Integer.parseInt(fivegambletks[5]) == randomfived && Integer.parseInt(fivegambletks[6]) == randomfivee) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 5) - 1; fiveresult = "Not bad! You win 5 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[2]) == randomfivea && Integer.parseInt(fivegambletks[3]) == randomfiveb && Integer.parseInt(fivegambletks[4]) == randomfivec) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 5) - 1; fiveresult = "Not bad! You win 5 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[2]) == randomfivea && Integer.parseInt(fivegambletks[4]) == randomfivec && Integer.parseInt(fivegambletks[6]) == randomfivee) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 5) - 1; fiveresult = "Not bad! You win 5 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[2]) == randomfivea && Integer.parseInt(fivegambletks[6]) == randomfivee) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 3) - 1; fiveresult = "Hey! You win 3 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[2]) == randomfivea && Integer.parseInt(fivegambletks[3]) == randomfiveb) { fiveprize = (Integer.parseInt(fivegambletks[1]) * 3) - 1; fiveresult = "Hey! You win 3 times your wager."; GetPaid(fiveprize, "r");
} else if (Integer.parseInt(fivegambletks[2]) == randomfivea || Integer.parseInt(fivegambletks[3]) == randomfiveb || Integer.parseInt(fivegambletks[4]) == randomfivec || Integer.parseInt(fivegambletks[5]) == randomfived || Integer.parseInt(fivegambletks[6]) == randomfivee) { fiveprize = Integer.parseInt(fivegambletks[1]) - 1; fiveresult = "One Match! You win back your wager..."; GetPaid(fiveprize, "r");
} else { fiveresult = "You lose your wager. Care to test your luck again?"; }

if (myplatform.equals("MAC")) {
fivegamblehtmla = divc + "<table border=0 cellpadding=5 cellspacing=0 width=525><tr><td align=center colspan=5 bgcolor=black>" + fontset + "Chosen Sequence</font></td></tr><tr><td align=center bgcolor=black><img src=" + imgurl+ "5pack" + myfivea + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + myfiveb + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + myfivec + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + myfived + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + myfivee + ".gif border=0></td></tr></table><br><table border=0 cellpadding=5 cellspacing=0 width=525><tr><td align=center colspan=5 bgcolor=black>" + fontset + "Order in Which the Stones have been Thrown</font></td></tr><tr><td align=center bgcolor=black><img src=" + imgurl + "5pack" + randomfivea + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + randomfiveb + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + randomfivec + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + randomfived + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl+ "5pack" + randomfivee + ".gif border=0></td></tr><tr><td align=center bgcolor=#222222 colspan=5><font size=-2 face=Verdana>" + fiveresult + "</font></td></tr></table><br><br><br><form name=GambleForm target=MOGamble><input type=hidden value=start name=start><input type=hidden value=* name=star><input type=hidden value=0 name=wager><input type=hidden value=a name=first><input type=hidden value=b name=second><input type=hidden value=c name=third><input type=hidden value=d name=fourth><input type=hidden value=e name=fifth><table border=0 cellpadding=2 cellspacing=0><tr><td bgcolor=black align=center><a href=javascript:GetGambleValues();window.opener.document.MOMain.FiveGambleStart(document.GambleForm.start.value) target=MOGamble><img src=http://localhost/Images/submit_gamble.gif border=0></a></td></form></tr></table><br><br><br></div>";
} else {
fivegamblehtmla = divc + "<table border=0 cellpadding=5 cellspacing=0 width=525><tr><td align=center colspan=5 bgcolor=black>" + fontset + "Chosen Sequence</font></td></tr><tr><td align=center bgcolor=black><img src=" + imgurl+ "5pack" + myfivea + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl+ "5pack" + myfiveb + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl+ "5pack" + myfivec + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl+ "5pack" + myfived + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl+ "5pack" + myfivee + ".gif border=0></td></tr></table><br><table border=0 cellpadding=5 cellspacing=0 width=525><tr><td align=center colspan=5 bgcolor=black>" + fontset + "Order in Which the Stones have been Thrown</font></td></tr><tr><td align=center bgcolor=black><img src=" + imgurl + "5pack" + randomfivea + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + randomfiveb + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + randomfivec + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl+ "5pack" + randomfived + ".gif border=0></td><td align=center bgcolor=black><img src=" + imgurl + "5pack" + randomfivee + ".gif border=0></td></tr><tr><td align=center bgcolor=#222222 colspan=5><font size=-2 face=Verdana>" + fiveresult + "</font></td></tr></table><br><br><br><form name=GambleForm target=MOGamble><input type=hidden value=start name=start><input type=hidden value=* name=star><input type=hidden value=0 name=wager><input type=hidden value=a name=first><input type=hidden value=d name=fourth><input type=hidden value=e name=fifth><input type=hidden value=b name=second><input type=hidden value=c name=third><table border=0 cellpadding=2 cellspacing=0><tr><td bgcolor=black align=center><a href=# onClick=GetGambleValues();window.opener.document.MOMain.FiveGambleStart(document.GambleForm.start.value) target=MOGamble><img src=http://localhost/Images/submit_gamble.gif border=0></a></td></form></tr></table><br><br><br></div>";
}
fivegamblehtmlb = "";
fivegambletks[0] = "end";
fiveprize = 0;
fiveresult = null;
myfivea = null; myfiveb = null; myfivec = null;
} else { 
fivegamblehtmla = divc + tbr + fontset + nomon + tbr;
fivegamblehtmlb = ""; }

     } else { FiveGambleStart("start*0*a*a*a*a*a"); }
String fivegamblehtml =  "";
if (fivegambletks[0].equals("end")) { fivegamblehtml =  "<html><head><title>5 Stones</title><script language=Javascript>function GetGambleValues() {  document.GambleForm.start.value = document.GambleForm.start.value + document.GambleForm.star.value + document.GambleForm.wager.value + document.GambleForm.star.value + document.GambleForm.first.value + document.GambleForm.star.value + document.GambleForm.second.value + document.GambleForm.third.value + document.GambleForm.fourth.value + document.GambleForm.fifth.value;}</script></head><body text=white background=" + imgurl+ "mobkgnd.jpg onLoad=javascript:window.opener.name=PlayFrame>" + fivegamblehtmla + fivegamblehtmlb  + closewindow + endhtml;
} else { fivegamblehtml =  "<html><head><title>5 Stones</title><script language=Javascript>function GetGambleValues() { document.GambleForm.start.value = document.GambleForm.start.value + document.GambleForm.star.value + document.GambleForm.wager.value + document.GambleForm.star.value;if (document.GambleForm.first[0].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 1 + document.GambleForm.star.value;} else if  (document.GambleForm.first[1].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 2 + document.GambleForm.star.value;} else if  (document.GambleForm.first[2].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 3 + document.GambleForm.star.value;} else if  (document.GambleForm.first[3].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 4 + document.GambleForm.star.value;} else { document.GambleForm.start.value = document.GambleForm.start.value + 5 + document.GambleForm.star.value; }if (document.GambleForm.second[0].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 1 + document.GambleForm.star.value;} else if  (document.GambleForm.second[1].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 2 + document.GambleForm.star.value;} else if  (document.GambleForm.second[2].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 3 + document.GambleForm.star.value;} else if  (document.GambleForm.second[3].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 4 + document.GambleForm.star.value;} else { document.GambleForm.start.value = document.GambleForm.start.value + 5 + document.GambleForm.star.value; }if (document.GambleForm.third[0].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 1 + document.GambleForm.star.value;} else if  (document.GambleForm.third[1].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 2 + document.GambleForm.star.value;} else if  (document.GambleForm.third[2].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 3 + document.GambleForm.star.value;} else if  (document.GambleForm.third[3].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 4 + document.GambleForm.star.value;} else { document.GambleForm.start.value = document.GambleForm.start.value + 5 + document.GambleForm.star.value; }if (document.GambleForm.fourth[0].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 1 + document.GambleForm.star.value;} else if  (document.GambleForm.fourth[1].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 2 + document.GambleForm.star.value;} else if  (document.GambleForm.fourth[2].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 3 + document.GambleForm.star.value;} else if  (document.GambleForm.fourth[3].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 4 + document.GambleForm.star.value;} else { document.GambleForm.start.value = document.GambleForm.start.value + 5 + document.GambleForm.star.value; }if (document.GambleForm.fifth[0].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 1 + document.GambleForm.star.value;} else if  (document.GambleForm.fifth[1].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 2 + document.GambleForm.star.value;} else if  (document.GambleForm.fifth[2].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 3 + document.GambleForm.star.value;} else if  (document.GambleForm.fifth[3].checked==true) { document.GambleForm.start.value = document.GambleForm.start.value + 4 + document.GambleForm.star.value;} else { document.GambleForm.start.value = document.GambleForm.start.value + 5 + document.GambleForm.star.value; }}</script></head><body text=white background=http://localhost/Images/mobkgnd.jpg onLoad=javascript:window.opener.name=PlayFrame >" + fivegamblehtmla + fivegamblehtmlb  + closewindow + endhtml;
}
OutputHTML( fivegamblehtml, "MOGamble" );
fiveoption = null; fivegamblehtml = null; fivegamblehtmla = null; fivegamblehtmlb = null; fivegambletks = null; fivegambletkssize = 0; fivegambletkss = null;
} // END void fiveGambleStart()


public void  PrintGambleScreen() {
String gamblehtmla = "";
if (myplatform.equals("MAC")) { 
gamblehtmla = divc + "<br><form name=GambleForm target=MOGamble><input type=hidden value=start name=start><input type=hidden value=* name=star><input type=hidden value=0 name=wager><input type=hidden value=a name=first><input type=hidden value=b name=second><input type=hidden value=c name=third><img src=" + imgurl+ "gamble_title.gif border=0>" + tbr + "<table border=0 cellpadding=5 cellspacing=0 width=75%><tr><td align=center><a href=javascript:GetGambleValues();window.opener.document.MOMain.ThreeGambleStart(document.GambleForm.start.value) target=MOGamble><img src=" + imgurl + "3Stones_option.gif border=0></a></td><td align=center><a href=javascript:GetGambleValues();window.opener.document.MOMain.FiveGambleStart(document.GambleForm.start.value) target=MOGamble><img src=" + imgurl + "5Stones_option.gif width=173 height=44 border=0></a></td></tr></table><br><br></form>";
} else {
gamblehtmla = divc + "<br><form name=GambleForm target=MOGamble><input type=hidden value=start name=start><input type=hidden value=* name=star><input type=hidden value=0 name=wager><input type=hidden value=a name=first><input type=hidden value=b name=second><input type=hidden value=c name=third><img src=" + imgurl+ "gamble_title.gif border=0>" + tbr + "<table border=0 cellpadding=5 cellspacing=0 width=75%><tr><td align=center><a href=# onClick=GetGambleValues();window.opener.document.MOMain.ThreeGambleStart(document.GambleForm.start.value) target=MOGamble><img src=" + imgurl + "3Stones_option.gif border=0></a></td><td align=center><a href=# onClick=GetGambleValues();window.opener.document.MOMain.FiveGambleStart(document.GambleForm.start.value) target=MOGamble><img src=" + imgurl+ "5Stones_option.gif width=173 height=44 border=0></a></td></tr></table><br><br></form>";
}
String gamblehtml = "<html><head><title></title><script language=Javascript>function GetGambleValues() { document.GambleForm.start.value = document.GambleForm.start.value + document.GambleForm.star.value + document.GambleForm.wager.value + document.GambleForm.star.value + document.GambleForm.first.value + document.GambleForm.star.value + document.GambleForm.second.value + document.GambleForm.star.value + document.GambleForm.third.value; }</script></head><body text=white background=" + imgurl + "mobkgnd.jpg onLoad=javascript:window.opener.name=PlayFrame>" + gamblehtmla  + closewindow + endhtml;
OutputHTML( gamblehtml, "MOGamble" ); gamblehtml = null; gamblehtmla = null;
} // END void PrintGambleScreen()


public void  PrintAbilitySpellScreen() {
String abilityhtml = starthtml  + divc + tbr + fontset + "<b>You do not have any abilities nor spells...<br><br>" + closewindow + "</b>" + endhtml;
OutputHTML( abilityhtml, "MOAbilitySpell" ); abilityhtml = null;
} // END void PrintAbilitySpellScreen()


public void  PrintMessagesScreen() {
String messagehtml = starthtml  + divc + tbr + fontset + "<b>Not yet enabled...<br><br>" + closewindow + "</b>" + endhtml;
OutputHTML( messagehtml, "MOMessages" ); messagehtml = null;
} // END void PrintMessagesScreen()


public void  PrintFollowersScreen() {
String followershtml = starthtml  + divc + tbr + fontset + "<b>Not yet enabled...<br><br>" + closewindow + "</b>" + endhtml;
OutputHTML( followershtml, "MOFollowers" ); followershtml = null;
} // END void PrintFollowersScreen()


public void  PrintInventoryScreen() {
String inventoryhtml = starthtml  + divc + tbr + fontset + "<b>Not yet enabled...<br><br>" + closewindow + "</b>" + endhtml;
OutputHTML( inventoryhtml, "MOInv" ); inventoryhtml = null;
} // END void PrintInventoryScreen()


public void  PrintEquipmentScreen() {
String equipmenthtml = starthtml  + divc + tbr + fontset + "<b>Not yet enabled...<br><br>" + closewindow + "</b>" + endhtml;
OutputHTML( equipmenthtml, "MOEquipment" ); equipmenthtml = null;
} // END void PrintEquipmentScreen()


// Executes Meal from Inn:Have a Meal or Tavern:Have a Meal
public void PrintMealScreen() {
VerifyOnlineStatus();
VerifySubLocationAction("meal");
UpdateStats();
String mealhtml = "";

if (CharacterStatus[15].equals("Inn")) {
// INN OPTIONS
mealhtml = "<html><head><title>Have a Meal</title><script language=Javascript>function GetMeal() {if (document.MealForm.mymeal[0].checked==true) { document.MealForm.pickedmeal.value = 0;} else if  (document.MealForm.mymeal[1].checked==true) { document.MealForm.pickedmeal.value = 1;} else if  (document.MealForm.mymeal[2].checked==true) { document.MealForm.pickedmeal.value = 2;} else if  (document.MealForm.mymeal[3].checked==true) { document.MealForm.pickedmeal.value = 3;} else if  (document.MealForm.mymeal[4].checked==true) { document.MealForm.pickedmeal.value = 4;} else if  (document.MealForm.mymeal[5].checked==true) { document.MealForm.pickedmeal.value = 5;} else { document.MealForm.pickedmeal.value = 6; }}</script></head><body background=" + imgurl + "bkgndinn.gif text=white>" + divc + "<br><form name=MealForm target=MOMeal><img src=" + imgurl + "haveameal_title.gif border=0><br><input type=hidden value=0 name=pickedmeal><br><table border=0 cellpadding=4 cellspacing=0 width=75% bgcolor=#222222><tr><td colspan=3 bgcolor=black align=center>" + fontset + "Inn Meal List</font></td></tr><tr><td><input type=radio value=0 name=mymeal></td><td>" + fontset + "Feast of the Beggar</font></td><td align=right>" + fontset + AdjustPrice(InnMeals[0]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=1 name=mymeal></td><td bgcolor=#111111>" + fontset + "Meal of the Peasant</font></td><td align=right bgcolor=#111111>" + fontset + AdjustPrice(InnMeals[1]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td><input type=radio value=2 name=mymeal checked></td><td>" + fontset + "Meal of the Average Adventurer</font></td><td align=right>" + fontset + AdjustPrice(InnMeals[2]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=3 name=mymeal></td><td bgcolor=#111111>" + fontset + "Meal of the Experienced Adventurer</font></td><td align=right bgcolor=#111111>" + fontset + AdjustPrice(InnMeals[3]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td><input type=radio value=4 name=mymeal></td><td>" + fontset + "Meal of the Legendary Adventurer</font></td><td align=right>" + fontset + AdjustPrice(InnMeals[4]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=5 name=mymeal></td><td bgcolor=#111111>" + fontset + "Meal of the Noble</font></td><td align=right bgcolor=#111111>" + fontset + AdjustPrice(InnMeals[5]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td><input type=radio value=6 name=mymeal></td><td>" + fontset + "Meal of the King</font></td><td align=right>" + fontset + AdjustPrice(InnMeals[6]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr></table><br><table border=0 cellpadding=4 cellspacing=0 bgcolor=black><tr><td>";
if (myplatform.equals("MAC")) { 
mealhtml = mealhtml + "<a href=javascript:GetMeal();window.opener.document.MOMain.EatMeal(document.MealForm.pickedmeal.value) target=MOMeal><img src=" + imgurl + "submit_buy.gif border=0></a></td></tr></table></form>";
} else {
mealhtml = mealhtml + "<a href=# onClick=GetMeal();window.opener.document.MOMain.EatMeal(document.MealForm.pickedmeal.value) target=MOMeal><img src=" + imgurl + "submit_buy.gif border=0></a></td></tr></table></form>";
}} else {
//TAVERN OPTIONS
mealhtml = "<html><head><title>Have a Meal</title><script language=Javascript>function GetMeal() {if (document.MealForm.mymeal[0].checked==true) { document.MealForm.pickedmeal.value = 0;} else if  (document.MealForm.mymeal[1].checked==true) { document.MealForm.pickedmeal.value = 1;} else if  (document.MealForm.mymeal[2].checked==true) { document.MealForm.pickedmeal.value = 2;} else if  (document.MealForm.mymeal[3].checked==true) { document.MealForm.pickedmeal.value = 3;} else if  (document.MealForm.mymeal[4].checked==true) { document.MealForm.pickedmeal.value = 4;} else { document.MealForm.pickedmeal.value = 5; }}</script></head><body background=" + imgurl+ "bkgndinn.gif text=white>" + divc + "<br><form name=MealForm target=MOMeal><img src=" + imgurl+ "haveameal_title.gif border=0><br><input type=hidden value=0 name=pickedmeal><br><table border=0 cellpadding=4 cellspacing=0 width=75% bgcolor=#222222><tr><td colspan=3 bgcolor=black align=center>" + fontset + "Tavern Meal List</font></td></tr><tr><td><input type=radio value=0 name=mymeal checked></td><td>" + fontset + "Have a Beer</font></td><td align=right>" + fontset + AdjustPrice(TavMeals[0]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=1 name=mymeal></td><td bgcolor=#111111>" + fontset + "Have Wine</font></td><td align=right bgcolor=#111111>" + fontset + AdjustPrice(TavMeals[1]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td><input type=radio value=2 name=mymeal></td><td>" + fontset + "Have Mead</font></td><td align=right>" + fontset + AdjustPrice(TavMeals[2]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=3 name=mymeal></td><td bgcolor=#111111>" + fontset + "Meal of the Adventurer</font></td><td align=right bgcolor=#111111>" + fontset + AdjustPrice(TavMeals[3]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td><input type=radio value=4 name=mymeal></td><td>" + fontset + "Meal of the Legendary Adventurer</font></td><td align=right>" + fontset + AdjustPrice(TavMeals[4]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr><tr><td bgcolor=#111111><input type=radio value=5 name=mymeal></td><td bgcolor=#111111>" + fontset + "Meal of the Noble</font></td><td align=right bgcolor=#111111>" + fontset + AdjustPrice(TavMeals[5]) + " </font><font size=-2 face=Verdana color=#cc9900>Copper Coins</font></td></tr></table><br><table border=0 cellpadding=4 cellspacing=0 bgcolor=black><tr><td>";
if (myplatform.equals("MAC")) { 
mealhtml = mealhtml + "<a href=javascript:GetMeal();window.opener.document.MOMain.EatMeal(document.MealForm.pickedmeal.value) target=MOMeal><img src=" + imgurl + "submit_buy.gif border=0></a></td></tr></table></form>";
} else {
mealhtml = mealhtml + "<a href=# onClick=GetMeal();window.opener.document.MOMain.EatMeal(document.MealForm.pickedmeal.value) target=MOMeal><img src=" + imgurl+ "submit_buy.gif border=0></a></td></tr></table></form>";
}}
mealhtml = mealhtml  + tbr + closewindow + endhtml;
OutputHTML( mealhtml, "MOMeal" );
mealhtml = null;
} // END void PrintMealScreen()


// Executes EAT Meal from Inn:Have a Meal or Tavern:Have a Meal
public void EatMeal(String pickedmeal) {
VerifyOnlineStatus();
VerifySubLocationAction("meal");
UpdateStats();

boolean paidmeal = false;
String mealeathtml = "";
int eatmorplus = 0;

if (CharacterStatus[15].equals("Inn")) {
// INN OPTIONS
paidmeal = PayItem(InnMeals[Integer.parseInt(pickedmeal)], 1, true);
eatmorplus = InnMorMin[Integer.parseInt(pickedmeal)] + (int) ( Math.random() * InnMor[Integer.parseInt(pickedmeal)] );
} else {
//TAVERN OPTIONS
paidmeal = PayItem(TavMeals[Integer.parseInt(pickedmeal)], 1, true);
eatmorplus = TavMorMin[Integer.parseInt(pickedmeal)] + (int) ( Math.random() * TavMor[Integer.parseInt(pickedmeal)] );

if (pickedmeal.equals("0") || pickedmeal.equals("1")) { TiredModifier(1);
} else if (pickedmeal.equals("2")) { TiredModifier(2);
} else {}

}

if (paidmeal == true) { mealeathtml = divc + tbr + fontset + "<b>You have eaten and gained... " + eatmorplus + " morale units." + tbr;
CharacterStats[39] = (new Integer(Integer.parseInt(CharacterStats[39]) + eatmorplus)).toString();;
if (Integer.parseInt(CharacterStats[39]) > Integer.parseInt(CharacterStats[41])) { CharacterStats[39] = CharacterStats[41]; } else {}
} else { mealeathtml = divc + tbr + fontset + nomon + tbr; }

mealeathtml = starthtml + mealeathtml  + closewindow + endhtml;
UpdateStats(); 
OutputHTML( mealeathtml, "MOMeal" );
mealeathtml = null;
}
// END void EatMeal()


// Executes Pay Item
protected boolean PayItem(int cost, int amount, boolean adjustprice) {
UpdateStats();
//DEBUG...
if (adjustprice == true) { cost = AdjustPrice(cost); } else {}
if ((cost * amount) > totalmoney || amount <= 0 || (cost * amount) < 0) { return false; } else {
float earnedloss = (earnedmoney/(float)totalmoney) * cost * amount;
earnedmoney = (int) (earnedmoney - earnedloss);
float receivedloss = (receivedmoney /(float)totalmoney) * cost * amount;
receivedmoney = (int) (receivedmoney - receivedloss);
totalmoney = earnedmoney + receivedmoney;
UpdateGemsANDCoins();
UpdateStats();
return true;
}} // END void PayItem()


// Executes Adjust Item Price Depending on Location Price var LocationVars[12]
protected int AdjustPrice(int costoadj) {
double locprvar = Double.valueOf(LocationVars[12].trim()).doubleValue();
double adjusted = costoadj * locprvar;
costoadj = (int) adjusted;
return costoadj;
} // END String AdjustPrice()


// Executes GetPaid Item
protected boolean GetPaid(int pay, String paytype) {
UpdateStats(); 
if (pay < 0) { pay = 0; } else {}
if (paytype.equals("e")) {
earnedmoney = earnedmoney + pay;
} else {
receivedmoney = receivedmoney + pay;
}
totalmoney = earnedmoney + receivedmoney;
UpdateGemsANDCoins();
UpdateStats();
return true;
} // END void PayItem()


// Prints Invest Screen
public void PrintInvestScreen() {
VerifyOnlineStatus();
VerifySubLocationAction("invest");
UpdateStats();
String investa = "";

if (myplatform.equals("MAC")) { 
investa = divc + "<br><img src=" + imgurl+ "invest_title.gif border=0>" + tbr + "<form name=InvestForm target=MOInvest>" + divc + tbcc + "<tr><td bgcolor=#101010>" + fontset + "Earned Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + earnedmoney + "</font></td></tr><tr><td bgcolor=#101010>" + fontset + "Received Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + receivedmoney + "</font></td></tr><tr><td bgcolor=#101010>" + fontset + "Total Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + money + "</font></td></tr><tr><td colspan=2><br></td></tr><tr><td bgcolor=black align=center colspan=2>" + fontset + "Amount: <input type=text name=investammount size=25 maxlength=10></font></td></tr><tr><td bgcolor=black align=center colspan=2><table border=0 cellpadding=2 cellspacing=0 width=100%><tr><td align=center width=50%><a href=javascript:window.opener.document.MOMain.InvestOptions(document.InvestForm.investammount.value) target=MOInvest><img src=" + imgurl+ "submit_invest.gif width=63 height=15 border=0></a></td></tr></table></td></tr></table></div></form></div>";
} else {
investa = divc + "<br><img src=" + imgurl + "invest_title.gif border=0>" + tbr + "<form name=InvestForm target=MOInvest>" + divc + tbcc + "<tr><td bgcolor=#101010>" + fontset + "Earned Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + earnedmoney + "</font></td></tr><tr><td bgcolor=#101010>" + fontset + "Received Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + receivedmoney + "</font></td></tr><tr><td bgcolor=#101010>" + fontset + "Total Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + money + "</font></td></tr><tr><td colspan=2><br></td></tr><tr><td bgcolor=black align=center colspan=2>" + fontset + "Amount: <input type=text name=investammount size=25 maxlength=10></font></td></tr><tr><td bgcolor=black align=center colspan=2><table border=0 cellpadding=2 cellspacing=0 width=100%><tr><td align=center width=50%><a href=# onClick=window.opener.document.MOMain.InvestOptions(document.InvestForm.investammount.value) target=MOInvest><img src=" + imgurl + "submit_invest.gif width=63 height=15 border=0></a></td></tr></table></td></tr></table></div></form></div>";
}

String investhtml = "<html><head><title></title></head><body text=white background=" + imgurl + "mobkgnd.jpg onLoad=javascript:window.opener.name=PlayFrame oncontextmenu=return false;>"  + investa + endhtml;
OutputHTML( investhtml, "MOInvest" );
investa = null;
investhtml = null;
} // END void PrintInvestScreen()

// Executes Invest Option
public void InvestOptions(String invedamount) {
VerifyOnlineStatus();
VerifySubLocationAction("invest");
UpdateStats();
String investaa = "";
String[] InvestComment = {"You have not invested.", "Your investment brought you 1 additional point of fame per earned copper coin!", "Your investment brought you 2 additional points of fame per earned copper coin... Lucky!", "Your investment was greatly appreciated.<br>However, your investment did not bring you additional fame."};

if ( Integer.parseInt(invedamount) > 0 && Integer.parseInt(invedamount) <= Integer.parseInt(money) ) {
int randominvest = 1 + (int) ( Math.random() * 3 );

earnedmoney = earnedmoney - Integer.parseInt(invedamount);

if (earnedmoney < 0) {
receivedmoney = receivedmoney - (earnedmoney * -1);
invedamount = (new Integer(Integer.parseInt(invedamount) - (earnedmoney * -1))).toString();
earnedmoney = 0;
if (Integer.parseInt(invedamount) < 0) { invedamount = "0"; } else {}
} else {}

if (randominvest == 1) {
CharacterStatus[9] = (new Integer((Integer.parseInt(invedamount) + Integer.parseInt(CharacterStatus[9])))).toString();
UpdateGemsANDCoins();
} else if (randominvest == 2) {
CharacterStatus[9] = (new Integer(( (Integer.parseInt(invedamount) * 2) + Integer.parseInt(CharacterStatus[9])))).toString();
UpdateGemsANDCoins();
} else { // DO NOTHING
}

UpdateStats();

investaa = divc + tbr + fontset + "<b>" + InvestComment[randominvest] + "<br><br>" + closewindow + "</b>";

String investhtml = starthtml  + investaa + endhtml;
OutputHTML( investhtml, "MOInvest" );
investaa = null;
investhtml = null;
randominvest = 0;
invedamount = null;
} else {} // if invedamount <= 0
} // END void InvestOptions()


// Prints Charity Screen
public void PrintCharityScreen() {
VerifyOnlineStatus();
VerifySubLocationAction("charity");
UpdateStats();
String charitya = "";
String charityb = "";
String chsend = "get*" + CharacterStatus[12] + "*" + CharacterStatus[13] + "*" + CharacterStatus[14] + "*0";

String callchrsrvlt = MOWorldCall("MOCharity", chsend);
if (callchrsrvlt.equals("error")) { charitya = starthtml + divc + tbr + fontset + anerr + tbr;
} else {

if (myplatform.equals("MAC")) { 
charitya = divc + "<br><img src=" + imgurl + "charity_title.gif border=0>" + tbr + "<form name=CharityForm target=MOCharity>" + divc + tbcc + "<tr><td bgcolor=#101010>" + fontset + "Town Hall Charity Basket:</font></td><td bgcolor=#101010 align=center><font size=-2 face=Verdana color=#ce9c00>" + callchrsrvlt + " Copper Coins</font></td></tr><tr><td colspan=2><br></td></tr><tr><td bgcolor=#101010>" + fontset + "Earned Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + earnedmoney + "</font></td></tr><tr><td bgcolor=#101010>" + fontset + "Received Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + receivedmoney + "</font></td></tr><tr><td bgcolor=#101010>" + fontset + "Total Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + money + "</font></td></tr><tr><td colspan=2><br></td></tr><tr><td bgcolor=black align=center colspan=2>" + fontset + "Amount: <input type=text name=charityammount size=25 maxlength=15></font></td></tr><tr><td bgcolor=black align=center colspan=2><table border=0 cellpadding=2 cellspacing=0 width=100%><tr><td align=center width=50%>";
charityb = "<a href=javascript:window.opener.document.MOMain.Donate(document.CharityForm.charityammount.value) target=MOCharity><img src=" + imgurl + "submit_donate.gif width=63 height=15 border=0></a></td><td align=center width=50%><a href=javascript:window.opener.document.MOMain.Beg(document.CharityForm.charityammount.value) target=MOCharity><img src=" + imgurl + "submit_beg.gif width=31 height=16 border=0></a></td></tr></table></td></tr></table></div></form></div>";
} else {
charitya = divc + "<br><img src=" + imgurl + "charity_title.gif border=0>" + tbr + "<form name=CharityForm target=MOCharity>" + divc + tbcc + "<tr><td bgcolor=#101010>" + fontset + "Town Hall Charity Basket:</font></td><td bgcolor=#101010 align=center><font size=-2 face=Verdana color=#ce9c00>" + callchrsrvlt + " Copper Coins</font></td></tr><tr><td colspan=2><br></td></tr><tr><td bgcolor=#101010>" + fontset + "Earned Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + earnedmoney + "</font></td></tr><tr><td bgcolor=#101010>" + fontset+ "Received Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + receivedmoney + "</font></td></tr><tr><td bgcolor=#101010>" + fontset + "Total Money in Copper Coins:</font></td><td bgcolor=#101010 align=right><font size=-2 face=Verdana color=#ce9c00>" + money + "</font></td></tr><tr><td colspan=2><br></td></tr><tr><td bgcolor=black align=center colspan=2>" + fontset + "Amount: <input type=text name=charityammount size=25 maxlength=15></font></td></tr><tr><td bgcolor=black align=center colspan=2><table border=0 cellpadding=2 cellspacing=0 width=100%><tr><td align=center width=50%>";
charityb = "<a href=# onClick=window.opener.document.MOMain.Donate(document.CharityForm.charityammount.value) target=MOCharity><img src=" + imgurl + "submit_donate.gif width=63 height=15 border=0></a></td><td align=center width=50%><a href=# onClick=window.opener.document.MOMain.Beg(document.CharityForm.charityammount.value) target=MOCharity><img src=" + imgurl + "submit_beg.gif width=31 height=16 border=0></a></td></tr></table></td></tr></table></div></form></div>";
}}

String charityhtml = "<html><head><title></title></head><body text=white background=" + imgurl + "mobkgnd.jpg onLoad=javascript:window.opener.name=PlayFrame oncontextmenu=return false;>"  + charitya + charityb + tbr + closewindow + endhtml;
OutputHTML( charityhtml, "MOCharity" );
charitya = null;
charityb = null;
charityhtml = null;
chsend = null;
} // END void PrintCharityScreen()


// Executes Donate Option
public void Donate(String CharityAmount) {
VerifyOnlineStatus();
VerifySubLocationAction("charity");
UpdateStats();
String DonateHTML = "";
String chdonate = "";
int realearned = 0;

if (Integer.parseInt(CharityAmount) > 0 && Integer.parseInt(CharityAmount) <= Integer.parseInt(money)) {

chdonate = "don*" + CharacterStatus[12] + "*" + CharacterStatus[13] + "*" + CharacterStatus[14] + "*" + CharityAmount;
chdonate = MOWorldCall("MOCharity", chdonate);

earnedmoney = earnedmoney - Integer.parseInt(CharityAmount);
if (earnedmoney < 0) {
realearned = Integer.parseInt(CharityAmount) + earnedmoney;
receivedmoney = receivedmoney - (earnedmoney * -1);
earnedmoney = 0;
} else { realearned = Integer.parseInt(CharityAmount); }

CharacterStatus[9] = (new Integer(realearned + Integer.parseInt(CharacterStatus[9]))).toString();
UpdateGemsANDCoins();
UpdateStats();

DonateHTML = divc + tbr + fontset + "<b>Your donation is very much appreciated.</b>" + tbr;
} else {
DonateHTML = divc + tbr + fontset + nomon + tbr;
}
DonateHTML = starthtml  + DonateHTML + closewindow + endhtml;
OutputHTML( DonateHTML, "MOCharity" );
DonateHTML = null;
chdonate = null;
} // END void Donate()


// Executes Beg Option
public void Beg(String CharityAmount) {
VerifyOnlineStatus();
VerifySubLocationAction("charity");
UpdateStats();
String BegHTML = "";
String chsend = "get*" + CharacterStatus[12] + "*" + CharacterStatus[13] + "*" + CharacterStatus[14] + "*0";

String chbeg = MOWorldCall("MOCharity", chsend);
if (Integer.parseInt(CharityAmount) > 0 && Integer.parseInt(CharityAmount) <= Integer.parseInt(chbeg)) {
boolean begpaid = GetPaid(Integer.parseInt(CharityAmount), "r");
CharacterStatus[9] = (new Integer((Integer.parseInt(CharacterStatus[9]) - (2 * Integer.parseInt(CharityAmount))))).toString();
chbeg = "beg*" + CharacterStatus[12] + "*" + CharacterStatus[13] + "*" + CharacterStatus[14] + "*" + CharityAmount;
chbeg = MOWorldCall("MOCharity", chbeg);
BegHTML = divc + tbr + fontset + "<b>You have begged and received money.</b>" + tbr;
} else {
BegHTML = divc + tbr + fontset + "<b>There is not that much money in the Charity Basket...</b>" + tbr;
}
BegHTML = starthtml  + BegHTML + closewindow + endhtml;
OutputHTML( BegHTML, "MOCharity" );
BegHTML = null;
chbeg = null;
chsend = null;
UpdateStats();
} // END void Beg()



// Prints Travel Screen
public void PrintTravelScreen() {
VerifyOnlineStatus();
VerifySubLocationAction("travel");
UpdateStats();

String myprovincefixed = CharacterStatus[13];

String travelhtmla = divc + "<br><img src=http://localhost/Images/travel_title.gif width=163 height=41 border=0>" + tbr + "<form action= method=post target=Travel>" + divc + tbcc + "<tr><td bgcolor=#101010>" + fontset+ "You are currently at " + CharacterStatus[14] + " in the province of " + myprovincefixed + " in " + CharacterStatus[12] + ".</font></td>";
String travelhtmlb = "</tr></table>" + fontset+ "<br><img src=http://localhost/Images/Dragoria/diamondskingdom.gif width=230 height=187 border=0 usemap=#diamondskingdom><map name=diamondskingdom><area shape=rect coords=150,148,223,180 href=http://localhost/guide/Dragoria/diamondskingdom.html target=_blank><area shape=rect coords=97,148,115,173 nohref><area shape=rect coords=160,117,175,138 nohref><area shape=rect coords=136,116,157,134 nohref><area shape=rect coords=113,123,131,143 nohref><area shape=rect coords=74,122,100,145 nohref><area shape=rect coords=47,149,70,173 nohref><area shape=rect coords=199,85,215,109 nohref><area shape=rect coords=142,89,162,112 nohref><area shape=rect coords=105,88,126,112 nohref><area shape=rect coords=66,92,88,118 nohref><area shape=rect coords=31,97,52,122 nohref><area shape=rect coords=189,37,209,62 nohref><area shape=rect coords=144,58,168,84 nohref><area shape=rect coords=106,49,125,74 nohref><area shape=rect coords=106,15,124,39 nohref><area shape=rect coords=58,52,86,86 nohref><area shape=rect coords=22,34,43,60 nohref></map><br><br></font>" + tbcc + "<tr><td bgcolor=black align=center>" + fontset + "View Map:</font></td><td bgcolor=black align=center colspan=2>" + fontset + "<select name=province size=1>";
String travelhtmlc = "<option value=Diamonds Kingdom>Diamonds Kingdom</option><option value=Dragoncrystals Realm>Dragoncrystals Realm</option><option value=Dragonstones Kingdom>Dragonstones Kingdom</option><option value=Emeralds Kingdom>Emeralds Kingdom</option><option value=Pearls Lands>Pearls Lands</option><option value=Topazs Kingdom>Topazs Kingdom</option></select></font></td></tr></table>" + fontset + tbr + "Note: Travel will only be allowed in the province of Diamonds Kingdom in Dragoria.</font></div></form></div>";
String travelstarthtml = starthtml  + travelhtmla + travelhtmlb + travelhtmlc + endhtml;
OutputHTML( travelstarthtml, "MOTravel" );
} // END void PrintTravelScreen()


// Prints Rest Screen
public void PrintRestScreen(String RestTime) {
VerifyOnlineStatus();
VerifySubLocationAction("rest");
int falseresttime = 0;

GetServerTime();
int gotohour = Integer.parseInt(RawTimeArray[1]);
int gotominute = Integer.parseInt(RawTimeArray[2]);
int gotosecond = Integer.parseInt(RawTimeArray[3]);
int moyear = Integer.parseInt(RawTimeArray[4]);
int moday = Integer.parseInt(RawTimeArray[5]);

int gototime = 12 * ( gotohour * 3600 + gotominute * 60 + gotosecond );
int gototimeday = gototime / 3600;
int gotoday = ( (gototimeday / 24) + moday );

gotohour = gototimeday % 24;
gotominute = (gototime % 3600) / 60;
gotosecond = (gototime % 3600) % 60;

if (Integer.parseInt(RestTime) == 25) {
falseresttime = (int) ((double) (Integer.parseInt(CharacterStats[8]) - Integer.parseInt(CharacterStats[6])) / 2);
} else { falseresttime = Integer.parseInt(RestTime);}

int mgotohour = gotohour + falseresttime;
int mgotominute = gotominute;
int mgotoday = gotoday;
int mgotoyear = moyear;

if (mgotohour > 23) { mgotohour = mgotohour - 24; mgotoday = mgotoday + 1; } else {}
if (mgotoday > 360) { mgotoday = mgotoday - 360; mgotoyear = mgotoyear + 1; } else {}

CharacterStatus[16] = "rest*25*" + falseresttime;
CharacterStatus[17] = moyear + "*" + gotoday + "*" + gotohour + "*" + gotominute;
CharacterStatus[18] = mgotoyear + "*" + mgotoday + "*" + mgotohour + "*" + mgotominute;

String resthtml = starthtml  + divc + "<br><img src=http://localhost/Images/youareresting.gif border=0><br><br><img src=http://localhost/Images/wait.gif border=0><br><br><table border=0 cellpadding=4 cellspacing=0 bgcolor=#101010><tr><td>" + fontset + "Ends on:</font></td><td>" + fontset + mgotohour + ":" + FixMinutes(mgotominute) + " - " + MODayState[mgotohour] + ", on day " + mgotoday + " of the year " + mgotoyear + ".</font></td></tr><tr><td bgcolor=black>" + fontset + "Current Time:</font></td><td bgcolor=black><applet code=MOTime.class width=375 height=17 codebase=http://localhost/Applets/" + mybrowser + "><param name=year value=" + RawTimeArray[4] + "><param name=day value=" + RawTimeArray[5] + "><param name=hour value=" + RawTimeArray[1] + "><param name=minute value=" + RawTimeArray[2] + "><param name=second value=" + RawTimeArray[3] + "></applet></td></tr></table><br><br>" + tbcc + "<tr><td bgcolor=black align=center><form name=\"Relogin\" action=\"http://localhost/servlet/MOCharOpts\" method=\"post\" target=\"_self\"><input src=http://localhost/Images/submit_characterrelogin.gif width=148 height=18 border=0 type=image name=Relogin><input type=hidden value=default name=format><input type=hidden value=" + mybrowser + " name=mybrowser><input type=hidden value=" + myplatform + " name=myplatform></td></form></tr><tr><td bgcolor=black align=center><form name=COS action=http://localhost/servlet/MOLogin method=post target=_self><input src=http://localhost/Images/submit_choptionsscreen.gif width=211 height=19 border=0 type=image name=COS><input type=hidden value=default name=format><input type=hidden value=defaulttype name=password><input type=hidden value=defaulttype name=ova><input type=hidden value=" + mybrowser + " name=mybrowser><input type=hidden value=" + myplatform + " name=myplatform></td></form></tr><tr><td bgcolor=black align=center><form name=StopAct action=http://localhost/servlet/MOStopActivity method=post target=MOStopped><input src=http://localhost/Images/submit_stopactivity.gif width=107 height=15 border=0 vspace=2 type=image name=StopAct></td></form></tr></table><br><br><br><br><br>" + closewindow + "</div>" + endhtml;
OutputHTML( resthtml, "PlayFrame" );
} // END void PrintRestScreen()


// Prints Search Screen
public void PrintSearchScreen(String SearchTime) {
VerifyOnlineStatus();
VerifySubLocationAction("search");

GetServerTime();
int gotohour = Integer.parseInt(RawTimeArray[1]);
int gotominute = Integer.parseInt(RawTimeArray[2]);
int gotosecond = Integer.parseInt(RawTimeArray[3]);
int moyear = Integer.parseInt(RawTimeArray[4]);
int moday = Integer.parseInt(RawTimeArray[5]);

int gototime = 12 * ( gotohour * 3600 + gotominute * 60 + gotosecond );
int gototimeday = gototime / 3600;
int gotoday = ( (gototimeday / 24) + moday );

gotohour = gototimeday % 24;
gotominute = (gototime % 3600) / 60;
gotosecond = (gototime % 3600) % 60;

int mgotohour = gotohour + Integer.parseInt(SearchTime);
int mgotominute = gotominute;
int mgotoday = gotoday;
int mgotoyear = moyear;

if (mgotohour > 23) { mgotohour = mgotohour - 24; mgotoday = mgotoday + 1; } else {}
if (mgotoday > 360) { mgotoday = mgotoday - 360; mgotoyear = mgotoyear + 1; } else {}

CharacterStatus[16] = "search*" + "money" + "*" + SearchTime;
CharacterStatus[17] = moyear + "*" + gotoday + "*" + gotohour + "*" + gotominute;
CharacterStatus[18] = mgotoyear + "*" + mgotoday + "*" + mgotohour + "*" + mgotominute;

String searchhtml = starthtml  + divc + "<br><img src=http://localhost/Images/youaresearching.gif border=0><br><br><img src=http://localhost/Images/wait.gif border=0><br><br><table border=0 cellpadding=4 cellspacing=0 bgcolor=#101010><tr><td>" + fontset + "Ends on:</font></td><td>" + fontset + mgotohour + ":" + FixMinutes(mgotominute) + " - " + MODayState[mgotohour] + ", on day " + mgotoday + " of the year " + mgotoyear + ".</font></td></tr><tr><td bgcolor=black>" + fontset + "Current Time:</font></td><td bgcolor=black><applet code=MOTime.class width=375 height=17 codebase=http://localhost/Applets/" + mybrowser + "><param name=year value=" + RawTimeArray[4] + "><param name=day value=" + RawTimeArray[5] + "><param name=hour value=" + RawTimeArray[1] + "><param name=minute value=" + RawTimeArray[2] + "><param name=second value=" + RawTimeArray[3] + "></applet></td></tr></table><br><br>" + tbcc + "<tr><td bgcolor=black align=center><form name=Relogin action=http://localhost/servlet/MOCharOpts method=post target=_self><input src=http://localhost/Images/submit_characterrelogin.gif width=148 height=18 border=0 type=image name=Relogin><input type=hidden value=default name=format><input type=hidden value=" + mybrowser + " name=mybrowser><input type=hidden value=" + myplatform + " name=myplatform></td></form></tr><tr><td bgcolor=black align=center><form name=COS action=http://localhost/servlet/MOLogin method=post target=_self><input src=http://localhost/Images/submit_choptionsscreen.gif width=211 height=19 border=0 type=image name=COS><input type=hidden value=default name=format><input type=hidden value=defaulttype name=password><input type=hidden value=defaulttype name=ova><input type=hidden value=" + mybrowser + " name=mybrowser><input type=hidden value=" + myplatform + " name=myplatform></td></form></tr><tr><td bgcolor=black align=center><form name=StopAct action=http://localhost/servlet/MOStopActivity method=post target=MOStopped><input type=hidden value=stop name=stop><input type=hidden value=stop name=stop><input src=http://localhost/Images/submit_stopactivity.gif width=107 height=15 border=0 vspace=2 type=image name=StopAct></td></form></tr></table><br><br><br><br><br>" + closewindow + "</div>" + endhtml;
OutputHTML( searchhtml, "PlayFrame" );
} // END void PrintSearchScreen()


// Prints Goto Screen
public void PrintGotoScreen(String ChosenSubLocation) {
VerifyOnlineStatus();
VerifySubLocationAction("goto");

int GotoMinutestoWait;
int moralegotoreduce = 1;

if (ChosenSubLocation.equals("Tavern")) { GotoMinutestoWait = 2; // 10 seconds
} else if (ChosenSubLocation.equals("Temple")) { GotoMinutestoWait = 3; // 15 seconds
} else if (ChosenSubLocation.equals("Inn")) { GotoMinutestoWait = 1; // 5 seconds
} else if (ChosenSubLocation.equals("Town Hall")) { GotoMinutestoWait = 2; // 10 seconds
} else if (ChosenSubLocation.equals("Guards Quarters")) { GotoMinutestoWait = 3; // 15 seconds
} else if (ChosenSubLocation.equals("Black Market")) { GotoMinutestoWait = 5; // 25 seconds
} else if (ChosenSubLocation.equals("Market")) { GotoMinutestoWait = 2; // 10 seconds
} else if (ChosenSubLocation.equals("Library")) { GotoMinutestoWait = 3; // 15 seconds
} else if (ChosenSubLocation.equals("Hall of Honors")) { GotoMinutestoWait = 5; // 25 seconds
} else if (ChosenSubLocation.equals("Hall of Glories")) { GotoMinutestoWait = 5; // 25 seconds
} else if (ChosenSubLocation.equals("Guilds Quarters")) { GotoMinutestoWait = 3; // 15 seconds
} else if (ChosenSubLocation.equals("Palisade")) { GotoMinutestoWait = 5; // 25 seconds
} else if (ChosenSubLocation.equals("Alchemist Tower")) { GotoMinutestoWait = 10; moralegotoreduce = 2; // 50 seconds
} else if (ChosenSubLocation.equals("Arena")) { GotoMinutestoWait = 7; // 35 seconds
} else if (ChosenSubLocation.equals("Circle of Chaos")) { GotoMinutestoWait = 7; // 35 seconds
} else if (ChosenSubLocation.equals("Academy")) { GotoMinutestoWait = 7; // 35 seconds
} else if (ChosenSubLocation.equals("Training Grounds")) { GotoMinutestoWait = 7; // 35 seconds
} else if (ChosenSubLocation.equals("Elders Council")) { GotoMinutestoWait = 5; // 25 seconds
} else if (ChosenSubLocation.equals("Messagery")) { GotoMinutestoWait = 3; // 15 seconds
} else if (ChosenSubLocation.equals("Surroundings")) { GotoMinutestoWait = 15; moralegotoreduce = 2; // 75 seconds
} else { GotoMinutestoWait = 25; moralegotoreduce = 3; }

if (CharacterStats[64].equals("sad")) { moralegotoreduce = moralegotoreduce + 1; GotoMinutestoWait = GotoMinutestoWait * 2;
} else if (CharacterStats[64].equals("depressed")) { moralegotoreduce = moralegotoreduce + 2; GotoMinutestoWait = GotoMinutestoWait * 3;
} else if (CharacterStats[64].equals("weak")) { moralegotoreduce = moralegotoreduce + 3; GotoMinutestoWait = GotoMinutestoWait * 4;
} else if (CharacterStats[64].equals("weaker")) { moralegotoreduce = moralegotoreduce + 4; GotoMinutestoWait = GotoMinutestoWait * 5;
} else if (CharacterStats[64].equals("weakest")) { moralegotoreduce = moralegotoreduce + 5; GotoMinutestoWait = GotoMinutestoWait * 6;
} else {}

GetServerTime();
int gotohour = Integer.parseInt(RawTimeArray[1]);
int gotominute = Integer.parseInt(RawTimeArray[2]);
int gotosecond = Integer.parseInt(RawTimeArray[3]);
int moyear = Integer.parseInt(RawTimeArray[4]);
int moday = Integer.parseInt(RawTimeArray[5]);

int gototime = 12 * ( gotohour * 3600 + gotominute * 60 + gotosecond );
int gototimeday = gototime / 3600;
int gotoday = ((gototimeday / 24) + moday );

gotohour = gototimeday % 24;
gotominute = (gototime % 3600) / 60;
gotosecond = (gototime % 3600) % 60;

int mgotohour = gotohour;
int mgotominute = gotominute;
int mgotoday = gotoday;
int mgotoyear = moyear;

mgotominute = gotominute + GotoMinutestoWait;

if (mgotominute >= 60) { mgotohour = mgotohour + 1; mgotominute = mgotominute - 60; }
if (mgotohour >= 24) { mgotoday = mgotoday + 1; mgotohour = mgotohour - 24; }
if (mgotoday >= 361) { mgotoyear = mgotoyear + 1; mgotoday = mgotoday - 360; }

CharacterStatus[15] = ChosenSubLocation;
CharacterStatus[16] = "goto";
CharacterStatus[17] = moyear + "*" + gotoday + "*" + gotohour + "*" + gotominute;
CharacterStatus[18] = mgotoyear + "*" + mgotoday + "*" + mgotohour + "*" + mgotominute;

int newmoralegoto = Integer.parseInt(CharacterStats[39]);
if (newmoralegoto > 1) { newmoralegoto = newmoralegoto - moralegotoreduce; CharacterStats[39] = (new Integer(newmoralegoto)).toString(); } else {}

String gotohtml = starthtml  + divc + "<br><img src=http://localhost/Images/youaretraveling.gif border=0><br><br><img src=http://localhost/Images/wait.gif border=0><br><br><table border=0 cellpadding=4 cellspacing=0 bgcolor=#101010><tr><td>" + fontset + "You will arrive on:</font></td><td>" + fontset + mgotohour + ":" + FixMinutes(mgotominute) + " - " + MODayState[mgotohour] + ", on day " + mgotoday + " of the year " + mgotoyear + ".</font></td></tr><tr><td bgcolor=black>" + fontset +  "Current Time:</font></td><td bgcolor=black><applet code=MOTime.class width=375 height=17 codebase=http://localhost/Applets/" + mybrowser + "><param name=year value=" + RawTimeArray[4] + "><param name=day value=" + RawTimeArray[5] + "><param name=hour value=" + RawTimeArray[1] + "><param name=minute value=" + RawTimeArray[2] + "><param name=second value=" + RawTimeArray[3] + "></applet></td></tr></table><br><br>" + tbcc + "<tr><td bgcolor=black align=center><form name=Relogin action=http://localhost/servlet/MOCharOpts method=post target=_self><input src=http://localhost/Images/submit_characterrelogin.gif width=148 height=18 border=0 type=image name=Relogin><input type=hidden value=default name=format><input type=hidden value=" + mybrowser + " name=mybrowser><input type=hidden value=" + myplatform + " name=myplatform></td></form></tr><tr><td bgcolor=black align=center><form name=COS action=http://localhost/servlet/MOLogin method=post type=image target=_self><input src=http://localhost/Images/submit_choptionsscreen.gif width=211 height=19 border=0 type=image name=COS><input type=hidden value=default name=format><input type=hidden value=defaulttype name=password><input type=hidden value=defaulttype name=ova><input type=hidden value=" + mybrowser + " name=mybrowser><input type=hidden value=" + myplatform + " name=myplatform></td></form></tr></table><br><br><br><br><br>" + closewindow + "</div>" + endhtml;
OutputHTML( gotohtml, "PlayFrame" );
} // END void PrintGotoScreen()


// Prints Status Screen
public void PrintStatusScreen() {
VerifyOnlineStatus();
VerifySubLocationAction("status");
UpdateStats();

int vsccs = Integer.parseInt(CharacterMoney[0]) + Integer.parseInt(CharacterMoney[13]);
int vsscs = Integer.parseInt(CharacterMoney[1]) + Integer.parseInt(CharacterMoney[14]);
int vsgcs = Integer.parseInt(CharacterMoney[2]) + Integer.parseInt(CharacterMoney[15]);
int vsopals = Integer.parseInt(CharacterMoney[3]) + Integer.parseInt(CharacterMoney[16]);
int vstopazs = Integer.parseInt(CharacterMoney[4]) + Integer.parseInt(CharacterMoney[17]);
int vslapiss = Integer.parseInt(CharacterMoney[5]) + Integer.parseInt(CharacterMoney[18]);
int vsgarnets = Integer.parseInt(CharacterMoney[6]) + Integer.parseInt(CharacterMoney[19]);
int vspearls = Integer.parseInt(CharacterMoney[7]) + Integer.parseInt(CharacterMoney[20]);
int vssapphires = Integer.parseInt(CharacterMoney[8]) + Integer.parseInt(CharacterMoney[21]);
int vsrubys = Integer.parseInt(CharacterMoney[9]) + Integer.parseInt(CharacterMoney[22]);
int vsgems = Integer.parseInt(CharacterMoney[10]) + Integer.parseInt(CharacterMoney[23]);
int vsemeralds = Integer.parseInt(CharacterMoney[11]) + Integer.parseInt(CharacterMoney[24]);
int vsdiamonds = Integer.parseInt(CharacterMoney[12]) + Integer.parseInt(CharacterMoney[25]);

String trialstatus = "";
String trialuntil = "";
String discstatus = "";
String allnstatus = "";
String allnstatusb = "";
int trialsize = 0;

if (CharacterStatus[7].equals("Trial")) { trialstatus = "Undergoing Trial";
String[] trialtokens = new String[9];
StringTokenizer trialvwtiltok = new StringTokenizer(CharacterStatus[21],"*",false);

while (trialvwtiltok.hasMoreTokens()) {
trialtokens[trialsize] = trialvwtiltok.nextToken();
trialsize = trialsize + 1;
}

trialuntil = "<b>Undergoing Trial Until</b>: </font><font size=-2 face=Verdana color=yellow> Day " + trialtokens[1] + " of the year " + trialtokens[0] + " at " + MODayState[Integer.parseInt(trialtokens[2])] + " - " + trialtokens[2]  + ":" + FixMinutes(Integer.parseInt(trialtokens[3])) + ".</font>";
} else { trialstatus = CharacterStatus[7];}

if (CharacterStatus[8].equals(" ")) { discstatus = "No Discipline"; } else { discstatus = CharacterStatus[8];}
if (CharacterStatus[10].equals(" ")) { allnstatus = "Not part of an Alliance"; allnstatusb = "Not part of an Alliance"; } else { allnstatus = CharacterStatus[10]; allnstatusb = CharacterStatus[11]; }

String statusparta = divc + "<img src=http://localhost/Images/viewcharactertitle.gif width=252 height=34 border=0><br><br>" + fontset + "<noscript><form action=http://localhost/waiting.html method=post target=PlayFrame></noscript><form name=MOReLogin action=http://localhost/relogin.html method=post target=PlayFrame>" + tbcc + "<tr><td align=center rowspan=2>" + tbcc + "<tr><td bgcolor=black colspan=5 align=center>" + fontset + "CHARACTER INFORMATION</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Short Name</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStatus[0] + "</font></td><td bgcolor=#222222><br></td><td bgcolor=#222222>" + fontset + "<b>Race</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStatus[2] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Full Name</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStatus[1] + "</font></td><td bgcolor=#111111><br></td><td bgcolor=#111111>" + fontset + "<b>Sex</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStatus[3] + "</font></td></tr><tr><td bgcolor=#222222>" + fontset;
String statuspartb = "<b>Title</b>:</font></td><td bgcolor=#222222>" + fontset + trialstatus + "</font></td><td bgcolor=#222222><br></td><td bgcolor=#222222>" + fontset + "<b>Fame</b>:</font></td><td bgcolor=#222222><font size=-2 face=Verdana color=#3366cc>" + fame + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Discipline</b>:</font></td><td bgcolor=#111111>" + fontset + discstatus + "</font></td><td bgcolor=#111111><br></td><td bgcolor=#111111>" + fontset + "<b>Next Title</b>:</font></td><td bgcolor=#111111><font size=-2 face=Verdana color=#ee0000>" + FameRequired[GetTitleNumber()] + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Strength</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStatus[4] + " %</font></td><td bgcolor=#222222><br></td><td bgcolor=#222222>" + fontset + "<b>Alliance</b>:</font></td><td bgcolor=#222222>" + fontset + allnstatus + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Mind</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStatus[5] + " %</font></td><td bgcolor=#111111><br></td><td bgcolor=#111111>" + fontset+ "<b>Alliance Rank</b>:</font></td><td bgcolor=#111111>";
String statuspartc = fontset + allnstatusb + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Skill</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStatus[6] + " %</font></td><td bgcolor=#222222><br></td><td bgcolor=#222222>" + fontset + "<b>UCR</b>:</font></td><td bgcolor=#222222><font size=-2 face=Verdana color=#cc9900>" + "0" + "</font></td></tr><tr><td bgcolor=#111111 colspan=5 align=center>" + fontset + trialuntil + "</td></tr></table></td><td align=center valign=middle>" + tbcc + "<tr><td bgcolor=black colspan=2 align=center>" + fontset+ "STRENGTH<br>( BASE / TOTAL )</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Power</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[0] + " / " + CharacterStats[2] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Might</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[3] + " / " + CharacterStats[5] + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Toughness</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[6];
String statuspartd = " / " + CharacterStats[8] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Potential</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[9] + " / " + CharacterStats[11] + "</font></td></tr></table></td><td align=center valign=middle>" + tbcc + "<tr><td bgcolor=black colspan=2 align=center>" + fontset + "MIND<br>( BASE / TOTAL )</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Awareness</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[12] + " / " + CharacterStats[14] + " </font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Magic</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[15] + " / " + CharacterStats[17] + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Focus</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[18] + " / " + CharacterStats[20] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Potential</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[21] + " / " + CharacterStats[23] + "</font></td></tr></table></td></tr><tr><td align=center colspan=2>";
String statusparte = tbcc + "<tr><td bgcolor=black colspan=2 align=center>" + fontset + "SKILL<br>( BASE / TOTAL )</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Accuracy</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[24] + " / " + CharacterStats[26] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Evasion</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[27] + " / " + CharacterStats[29] + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Agility</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[30] + " / " + CharacterStats[32] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Potential</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[33] + " / " + CharacterStats[35] + "</font></td></tr></table></td></tr></table><br>" + tbcc + "<tr><td align=center valign=top>" + tbcc + "<tr><td bgcolor=black colspan=2 align=center>" + fontset + "ACTIVE STATS</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Toughness</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[6] + " / </font>";
String statuspartf = "<font size=-2 face=Verdana color=#00bb00><b>" + CharacterStats[8] + "</b></font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Magic</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[15] + " / </font><font size=-2 face=Verdana color=#6633cc><b>" + CharacterStats[17] + "</b></font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Morale</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[39] + " / </font><font size=-2 face=Verdana color=#bb0000><b>" + CharacterStats[41] + "</b></font></td></tr><tr><td bgcolor=#111111>" + fontset+ "<b>Strength Potential</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[9] + " % is redirected.</font></td></tr><tr><td bgcolor=#111111 colspan=2>" + fontset + "<input type=radio value=0 name=strpot> 0% <input type=radio value=25 name=strpot> 25% <input type=radio value=50 name=strpot> 50% </font><input type=radio value=75 name=strpot> " + fontset + "75% </font><input type=radio value=100 name=strpot> " + fontset + "100%<br></font><input type=radio value=ot name=strpot> " + fontset + "Other: <input type=text name=strother size=2 maxlength=2>% </font><font size=-2 face=Verdana color=#888888>(Applies on exit)</font>";
String statuspartg = "</td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Mind Potential</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[21] + " % is redirected.</font></td></tr><tr><td bgcolor=#222222 colspan=2>" + fontset + "<input type=radio value=0 name=mndpot> 0% <input type=radio value=25 name=mndpot> 25% <input type=radio value=50 name=mndpot> 50% </font><input type=radio value=75 name=mndpot> " + fontset + "75% </font><input type=radio value=100 name=mndpot> " + fontset+ "100%<br></font><input type=radio value=ot name=mndpot> " + fontset + "Other: <input type=text name=mndother size=2 maxlength=2>% </font><font size=-2 face=Verdana color=#888888>(Applies on exit)</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Skill Potential</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[33] + " % is redirected.</font></td></tr><tr><td bgcolor=#111111 colspan=2>" + fontset+ "<input type=radio value=0 name=sklpot> 0% <input type=radio value=25 name=sklpot> 25% <input type=radio value=50 name=sklpot> 50% </font><input type=radio value=75 name=sklpot> " + fontset + "75% </font><input type=radio value=100 name=sklpot> " + fontset + "100%<br></font><input type=radio value=ot name=sklpot> " + fontset;
String statusparth = "Other: <input type=text name=sklother size=2 maxlength=2>% </font><font size=-2 face=Verdana color=#888888>(Applies on exit)</font></td></tr></table><br>" + tbcc + "<tr><td bgcolor=black colspan=6 align=center>" + fontset + "MONEY</font></td></tr><tr><td bgcolor=#222222>" + fontset+ "<b>Copper Coin</b>:</font></td><td bgcolor=#222222 align=right>" + fontset + vsccs + "</font></td><td bgcolor=#222222>" + fontset + "<b>Silver Coin</b>:</font></td><td bgcolor=#222222 align=right>" + fontset + vsscs + "</font></td><td bgcolor=#222222>" + fontset + "<b>Gold Coin</b>:</font></td><td bgcolor=#222222 align=right>" + fontset + vsgcs + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Opal</b>:</font></td><td bgcolor=#111111 align=right>" + fontset + vsopals + "</font></td><td bgcolor=#111111>" + fontset + "<b>Topaz</b>:</font></td><td bgcolor=#111111 align=right>" + fontset + vstopazs + "</font></td><td bgcolor=#111111>" + fontset + "<b>Lapis</b>:</font></td><td bgcolor=#111111 align=right>" + fontset + vslapiss + "</font></td></tr><td bgcolor=#222222>" + fontset + "<b>Garnet</b>:</font></td><td bgcolor=#222222 align=right>" + fontset;
String statusparti = vsgarnets + "</td><td bgcolor=#222222>" + fontset + "<b>Pearl</b>:</font></td><td bgcolor=#222222 align=right>" + fontset + vspearls + "</font></td><td bgcolor=#222222>" + fontset + "<b>Sapphire</b>:</font></td><td bgcolor=#222222 align=right>" + fontset + vssapphires + "</font></td></tr>";
String statuspartii = "<tr><td bgcolor=#111111>" + fontset + "<b>Ruby</b>:</font></td><td bgcolor=#111111 align=right>" + fontset + vsrubys + "</font></td><td bgcolor=#111111>" + fontset + "<b>Gem</b>:</font></td><td bgcolor=#111111 align=right>" + fontset + vsgems + "</font></td><td bgcolor=#111111>" + fontset + " <b>Emerald</b>:</font></td><td bgcolor=#111111 align=right>" + fontset + vsemeralds + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Diamond</b>:</font></td><td bgcolor=#222222 align=right>" + fontset + vsdiamonds + "</font></td><td bgcolor=#222222 colspan=4>" + fontset + "<b>Total Money in CCs</b>: " + money + "</font></td></tr><tr><td bgcolor=#111111 colspan=6>" + fontset + "<b>Earned Money in CCs</b>: " + earnedmoney + "</font></td></tr><tr><td bgcolor=#222222 colspan=6>" + fontset + "<b>Received Money in CCs</b>: " + receivedmoney + "</font></td></tr></table></td><td align=center valign=top>" + tbcc + "<tr><td bgcolor=black colspan=4 align=center>" + fontset;
String statuspartj = "PASSIVE STATS</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Appearance</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[36] + "</font></td><td bgcolor=#222222>" + fontset + "<b>Technique</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[42] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Travel Speed</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[45] + "</font></td><td bgcolor=#111111>" + fontset + "<b>Travel Type</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[43] + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Body Absolute Wound Deflection</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[48] + "</font></td><td bgcolor=#222222>" + fontset + "<b>Body % Wound Deflection</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[50] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Armor Absolute Wound Deflection</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td><td bgcolor=#111111>" + fontset + "<b>Armor % Wound Deflection</b>:</font></td><td bgcolor=#111111>";
String statuspartk = fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Body Absolute Magic Deflection</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[54] + "</font></td><td bgcolor=#222222>" + fontset + "<b>Body % Magic Deflection</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[56] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Armor Absolute Magic Deflection</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td><td bgcolor=#111111>" + fontset + " <b>Armor % Magic Deflection</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Base Regeneration</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[52] + "</font></td><td bgcolor=#222222>" + fontset + "<b>Final Regeneration</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[53] + "</font></td></tr><tr><td bgcolor=#111111 colspan=4>" + fontset + "<b>Material Resistance(s)</b>: " + CharacterStats[67] + "</font></td></tr><tr><td bgcolor=#222222 colspan=4>" + fontset + "<b>Material Weakness(es)</b>: " + CharacterStats[68] + "</font>";
String statuspartl = "</td></tr></table><br>" + tbcc + "<tr><td align=center>" + tbcc + "<tr><td bgcolor=black colspan=2 align=center>" + fontset + "MAGIC RESISTANCES<br>( BASE / TOTAL )</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Air</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Darkness</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Death</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Earth</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Fire</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Life</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Lightness</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Space</b>:</font>";
String statuspartm = "</td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Time</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Water</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr></table></td><td align=center>" + tbcc + "<tr><td bgcolor=black colspan=2 align=center>" + fontset + "MAGIC WEAKNESSES<br>( BASE / TOTAL )</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Air</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Darkness</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Death</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Earth</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Fire</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>";
String statuspartn = fontset + "<b>Life</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Lightness</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Space</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Time</b>:</font></td><td bgcolor=#222222>" + fontset + "0" + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Water</b>:</font></td><td bgcolor=#111111>" + fontset + "0" + "</font></td></tr></table></td><td align=center>" + tbcc + "<tr><td bgcolor=black colspan=2 align=center>" + fontset + "STATUSES</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Blinded</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[63] + "</font></td></tr><tr><td bgcolor=#111111>" + fontset + "<b>Paralyzed</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[62] + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Poisonned</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[61] + "</font></td></tr><tr><td bgcolor=#111111>";
String statusparto = fontset + "<b>Sluggish</b>:</font></td><td bgcolor=#111111>" + fontset + CharacterStats[64] + "</font></td></tr><tr><td bgcolor=#222222>" + fontset + "<b>Tired</b>:</font></td><td bgcolor=#222222>" + fontset + CharacterStats[60] + "</font></td></tr></table></td></tr></table></td></tr></table></form><br>" + closewindow + "</font></div>";
String statushtml = starthtml  + statusparta + statuspartb + statuspartc + statuspartd + statusparte + statuspartf + statuspartg + statusparth + statusparti + statuspartii + statuspartj + statuspartk + statuspartl + statuspartm + statuspartn + statusparto + endhtml;
OutputHTML( statushtml, "MOStatus" );
} // END void PrintStatusScreen()




					// SAVE/READ SERVER, OUTPUT HTML, MORALE REDUCER, STATS UPDATER, STATE CHECK


protected void UpdateCoches(String cname, int camount) {
if (camount <= 0) { return; } else {}

if (cname.equals("p")) { CharacterCoches[0] = (new Integer(Integer.parseInt(CharacterCoches[0]) + camount)).toString();
} else if (cname.equals("mi")) { CharacterCoches[1] = (new Integer(Integer.parseInt(CharacterCoches[1]) + camount)).toString();
} else if (cname.equals("t")) { CharacterCoches[2] = (new Integer(Integer.parseInt(CharacterCoches[2]) + camount)).toString();
} else if (cname.equals("aw")) { CharacterCoches[3] = (new Integer(Integer.parseInt(CharacterCoches[3]) + camount)).toString();
} else if (cname.equals("ma")) { CharacterCoches[4] = (new Integer(Integer.parseInt(CharacterCoches[4]) + camount)).toString();
} else if (cname.equals("f")) { CharacterCoches[5] = (new Integer(Integer.parseInt(CharacterCoches[5]) + camount)).toString();
} else if (cname.equals("ac")) { CharacterCoches[6] = (new Integer(Integer.parseInt(CharacterCoches[6]) + camount)).toString();
} else if (cname.equals("e")) { CharacterCoches[7] = (new Integer(Integer.parseInt(CharacterCoches[7]) + camount)).toString();
} else if (cname.equals("ag")) { CharacterCoches[8] = (new Integer(Integer.parseInt(CharacterCoches[8]) + camount)).toString();
} else { return; } 

} // END int UpdateCoches()


protected String MOWorldCall(String requestedservlet, String infostosend) { 
String receivedinfos = "";

try {
URL ServletURL = new URL("http://localhost/servlet/" + requestedservlet);
URLConnection worldconn = ServletURL.openConnection();
worldconn.setRequestProperty("Content-Type", "text/plain");
worldconn.setDoOutput(true); worldconn.setDoInput(true); worldconn.setUseCaches(false); worldconn.setDefaultUseCaches(false);

ObjectOutputStream worldoos = new ObjectOutputStream(worldconn.getOutputStream());
worldoos.writeObject(infostosend);
worldoos.flush();
worldoos.close();

DataInputStream worlddis = new DataInputStream(worldconn.getInputStream());
receivedinfos = worlddis.readLine();
worlddis.readLine();
worlddis.close();
}

catch (Exception MOWorldCallExc) { receivedinfos = "error"; }

requestedservlet = null;
infostosend = null;
return receivedinfos;

} // END int MOWorldCall()


protected String FixMinutes(int MinutetoFix) { 
String MinuteFixed;
if (MinutetoFix == 0) { MinuteFixed = "00";
} else if (MinutetoFix == 1) { MinuteFixed = "01";
} else if (MinutetoFix == 2) { MinuteFixed = "02";
} else if (MinutetoFix == 3) { MinuteFixed = "03";
} else if (MinutetoFix == 4) { MinuteFixed = "04";
} else if (MinutetoFix == 5) { MinuteFixed = "05";
} else if (MinutetoFix == 6) { MinuteFixed = "06";
} else if (MinutetoFix == 7) { MinuteFixed = "07";
} else if (MinutetoFix == 8) { MinuteFixed = "08";
} else if (MinutetoFix == 0) { MinuteFixed = "09";
} else { MinuteFixed = (new Integer(MinutetoFix)).toString(); }
return MinuteFixed;
} // END int FixMinutes()


// Gets Local Server Time
protected void GetServerTime() {
String RawServerTime = "";
int RawTimeSize = 1;

RawServerTime = MOWorldCall("MOGetServerTime", "*");

StringTokenizer RawTimeTk = new StringTokenizer(RawServerTime,"%",false);
while (RawTimeTk.hasMoreTokens()) {
RawTimeArray[RawTimeSize] = RawTimeTk.nextToken();
RawTimeSize = RawTimeSize + 1; }
} // END void GetServerTime()


// Fetches Character Infos from MO Server - MOGetCharactertInfos
protected void GetCharacterInfos(String GetCharacterInfosPLAYER, String GetCharacterInfosCHARACTER) {
String RawServerLegacy = "";
RawServerLegacy = MOWorldCall("MOGetCharactertInfos", myCharacter);
if (RawServerLegacy.equals("error")) { stop(); } else {}

StringTokenizer ServerLegacy = new StringTokenizer(RawServerLegacy,"%",false);
int ServerLegacySize = 1;

while (ServerLegacy.hasMoreTokens()) {
CharacterLegacy[ServerLegacySize] = ServerLegacy.nextToken();
ServerLegacySize = ServerLegacySize + 1;
}

int CharacterLegacySize = 1;
int GetInfosStatusSize = 0;
int GetInfosStatsSize = 0;
int GetInfosMoneySize = 0;
int GetInfosCochesSize = 0;
int LocationVarssize = 0;

while (GetInfosStatusSize < 23) {
CharacterStatus[GetInfosStatusSize] = CharacterLegacy[CharacterLegacySize];
GetInfosStatusSize = GetInfosStatusSize + 1;
CharacterLegacySize = CharacterLegacySize + 1;
}

CharacterLegacySize = CharacterLegacySize - 1;

while (GetInfosStatsSize < 70) {
CharacterStats[GetInfosStatsSize] = CharacterLegacy[CharacterLegacySize];
GetInfosStatsSize = GetInfosStatsSize + 1;
CharacterLegacySize = CharacterLegacySize + 1;
}

CharacterLegacySize = CharacterLegacySize - 1;

while (GetInfosMoneySize < 27) {
CharacterMoney[GetInfosMoneySize] = CharacterLegacy[CharacterLegacySize];
GetInfosMoneySize = GetInfosMoneySize + 1;
CharacterLegacySize = CharacterLegacySize + 1;
}

CharacterLegacySize = CharacterLegacySize - 1;

while ( GetInfosCochesSize < 13 ) {
CharacterCoches[GetInfosCochesSize]  = CharacterLegacy[CharacterLegacySize];
GetInfosCochesSize = GetInfosCochesSize + 1;
CharacterLegacySize = CharacterLegacySize + 1;
}

CharacterLegacySize = CharacterLegacySize - 1;

while ( LocationVarssize < 32 ) {
LocationVars[LocationVarssize]  = CharacterLegacy[CharacterLegacySize];
LocationVarssize = LocationVarssize + 1;
CharacterLegacySize = CharacterLegacySize + 1;
}

// For Garbage Collection...
RawServerLegacy = null;
CharacterLegacy = null;
} // END void GetCharacterInfos()


// Updates Visible Stats for HTML
protected void UpdateStats() {
VerifyOnlineStatus();
res = CharacterStats[6]; maxres = CharacterStats[8];
mag = CharacterStats[15]; maxmag = CharacterStats[17];
maxmor = CharacterStats[41];

if (Integer.parseInt(CharacterStats[39]) <= 50 && Integer.parseInt(CharacterStats[39]) > 40) { mor =  "<font color=#ffff66>" + CharacterStats[39] + "</font>"; CharacterStats[64] = "sad";
} else if (Integer.parseInt(CharacterStats[39]) <= 40 && Integer.parseInt(CharacterStats[39]) > 30) { mor =  "<font color=#ffcc00>" + CharacterStats[39] + "</font>"; CharacterStats[64] = "depressed";
} else if (Integer.parseInt(CharacterStats[39]) <= 30 && Integer.parseInt(CharacterStats[39]) > 20) { mor =  "<font color=#ff9900>" + CharacterStats[39] + "</font>"; CharacterStats[64] = "weak";
} else if (Integer.parseInt(CharacterStats[39]) <= 20 && Integer.parseInt(CharacterStats[39]) > 10) { mor =  "<font color=#ff6600>" + CharacterStats[39] + "</font>"; CharacterStats[64] = "weaker";
} else if (Integer.parseInt(CharacterStats[39]) <= 10 && Integer.parseInt(CharacterStats[39]) > 0) { mor =  "<font color=red>" + CharacterStats[39] + "</font>"; CharacterStats[64] = "weakest";
} else { mor = CharacterStats[39]; CharacterStats[64] = " "; }

UpdateGemsANDCoins();

int vsccs = Integer.parseInt(CharacterMoney[0]) + Integer.parseInt(CharacterMoney[13]);
int vsscs = Integer.parseInt(CharacterMoney[1]) + Integer.parseInt(CharacterMoney[14]);
int vsgcs = Integer.parseInt(CharacterMoney[2]) + Integer.parseInt(CharacterMoney[15]);
int vsopals = Integer.parseInt(CharacterMoney[3]) + Integer.parseInt(CharacterMoney[16]);
int vstopazs = Integer.parseInt(CharacterMoney[4]) + Integer.parseInt(CharacterMoney[17]);
int vslapiss = Integer.parseInt(CharacterMoney[5]) + Integer.parseInt(CharacterMoney[18]);
int vsgarnets = Integer.parseInt(CharacterMoney[6]) + Integer.parseInt(CharacterMoney[19]);
int vspearls = Integer.parseInt(CharacterMoney[7]) + Integer.parseInt(CharacterMoney[20]);
int vssapphires = Integer.parseInt(CharacterMoney[8]) + Integer.parseInt(CharacterMoney[21]);
int vsrubys = Integer.parseInt(CharacterMoney[9]) + Integer.parseInt(CharacterMoney[22]);
int vsgems = Integer.parseInt(CharacterMoney[10]) + Integer.parseInt(CharacterMoney[23]);
int vsemeralds = Integer.parseInt(CharacterMoney[11]) + Integer.parseInt(CharacterMoney[24]);
int vsdiamonds = Integer.parseInt(CharacterMoney[12]) + Integer.parseInt(CharacterMoney[25]);

earnedmoney = Integer.parseInt(CharacterMoney[0]) + (10 * Integer.parseInt(CharacterMoney[1])) + (100 * Integer.parseInt(CharacterMoney[2])) + (250 * Integer.parseInt(CharacterMoney[3])) + (500 * Integer.parseInt(CharacterMoney[4])) + (1000 * Integer.parseInt(CharacterMoney[5])) + (2500 * Integer.parseInt(CharacterMoney[6])) + (5000 * Integer.parseInt(CharacterMoney[7])) + (10000 * Integer.parseInt(CharacterMoney[8])) + (25000 * Integer.parseInt(CharacterMoney[9])) + (50000 * Integer.parseInt(CharacterMoney[10])) + (100000 * Integer.parseInt(CharacterMoney[11])) + (Integer.parseInt(CharacterMoney[12]) * 1000000);
receivedmoney = Integer.parseInt(CharacterMoney[13]) + (10 * Integer.parseInt(CharacterMoney[14])) + (100 * Integer.parseInt(CharacterMoney[15])) + (250 * Integer.parseInt(CharacterMoney[16])) + (500 * Integer.parseInt(CharacterMoney[17])) + (1000 * Integer.parseInt(CharacterMoney[18])) + (2500 * Integer.parseInt(CharacterMoney[19])) + (5000 * Integer.parseInt(CharacterMoney[20])) + (10000 * Integer.parseInt(CharacterMoney[21])) + (25000 * Integer.parseInt(CharacterMoney[22])) + (50000 * Integer.parseInt(CharacterMoney[23])) + (100000 * Integer.parseInt(CharacterMoney[24])) + (Integer.parseInt(CharacterMoney[25]) * 1000000);
totalmoney = earnedmoney + receivedmoney;
money = (new Integer(totalmoney)).toString();
fame = (new Integer((earnedmoney / 2) + Integer.parseInt(CharacterStatus[9]))).toString();

int falsefame = ((Integer.parseInt(fame)) / (FameRequired[GetTitleNumber()])) * 100;

if (falsefame >= 100) { fame =  "<font color=orange>" + fame + "</font>";
} else if (falsefame >= 95) { fame =  "<font color=yellow>" + fame + "</font>";
} else {}
} // END void UpdateStats()

// Gets Title Number
protected int GetTitleNumber() {
int mytitlenumber = 0;
if (CharacterStatus[7].equals(Title[0])) { mytitlenumber = 0;
} else if (CharacterStatus[7].equals(Title[1])) { mytitlenumber = 1;
} else if (CharacterStatus[7].equals(Title[2])) { mytitlenumber = 2;
} else if (CharacterStatus[7].equals(Title[3])) { mytitlenumber = 3;
} else if (CharacterStatus[7].equals(Title[4])) { mytitlenumber = 4;
} else if (CharacterStatus[7].equals(Title[5])) { mytitlenumber = 5;
} else if (CharacterStatus[7].equals(Title[6])) { mytitlenumber = 6;
} else if (CharacterStatus[7].equals(Title[7])) { mytitlenumber = 7;
} else if (CharacterStatus[7].equals(Title[8])) { mytitlenumber = 8;
} else if (CharacterStatus[7].equals(Title[9])) { mytitlenumber = 9;
} else if (CharacterStatus[7].equals(Title[10])) { mytitlenumber = 10;
} else if (CharacterStatus[7].equals(Title[11])) { mytitlenumber = 11;
} else if (CharacterStatus[7].equals(Title[12])) { mytitlenumber = 12;
} else if (CharacterStatus[7].equals(Title[13])) { mytitlenumber = 13;
} else if (CharacterStatus[7].equals(Title[14])) { mytitlenumber = 14;
} else if (CharacterStatus[7].equals(Title[15])) { mytitlenumber = 15;
} else if (CharacterStatus[7].equals(Title[16])) { mytitlenumber = 16;
} else if (CharacterStatus[7].equals(Title[17])) { mytitlenumber = 17;
} else if (CharacterStatus[7].equals(Title[18])) { mytitlenumber = 18;
} else if (CharacterStatus[7].equals(Title[19])) { mytitlenumber = 18;
} else { mytitlenumber = 0; }
return mytitlenumber;
}  // END int GetTitleNumber()


// Updates Gems and Coins
protected void UpdateGemsANDCoins() {
int diamond = (earnedmoney / 1000000);
int emerald = ((earnedmoney % 1000000) / 100000);
int gem = (((earnedmoney % 1000000) % 100000) / 50000);
int ruby = ((((earnedmoney % 1000000) % 100000) % 50000) / 25000);
int sapphire = (((((earnedmoney % 1000000) % 100000) % 50000) % 25000) / 10000);
int pearl = ((((((earnedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) / 5000);
int garnet = (((((((earnedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) / 2500);
int lapis = ((((((((earnedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) / 1000);
int topaz = (((((((((earnedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) / 500);
int opal = ((((((((((earnedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) % 500) / 250);
int gold = (((((((((((earnedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) % 500) % 250) / 100);
int silver = ((((((((((((earnedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) % 500) % 250) % 100) / 10);
int copper = (((((((((((earnedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) % 500) % 250) % 100) % 10;

int rdiamond = (receivedmoney / 1000000);
int remerald = ((receivedmoney % 1000000) / 100000);
int rgem = (((receivedmoney % 1000000) % 100000) / 50000);
int rruby = ((((receivedmoney % 1000000) % 100000) % 50000) / 25000);
int rsapphire = (((((receivedmoney % 1000000) % 100000) % 50000) % 25000) / 10000);
int rpearl = ((((((receivedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) / 5000);
int rgarnet = (((((((receivedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) / 2500);
int rlapis = ((((((((receivedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) / 1000);
int rtopaz = (((((((((receivedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) / 500);
int ropal = ((((((((((receivedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) % 500) / 250);
int rgold = (((((((((((receivedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) % 500) % 250) / 100);
int rsilver = ((((((((((((receivedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) % 500) % 250) % 100) / 10);
int rcopper = (((((((((((receivedmoney % 1000000) % 100000) % 50000) % 25000) % 10000) % 5000) % 2500) % 1000) % 500) % 250) % 100) % 10;

CharacterMoney[0] = Integer.toString(copper);
CharacterMoney[1] = Integer.toString(silver);
CharacterMoney[2] = Integer.toString(gold);
CharacterMoney[3] = Integer.toString(opal);
CharacterMoney[4] = Integer.toString(topaz);
CharacterMoney[5] = Integer.toString(lapis);
CharacterMoney[6] = Integer.toString(garnet);
CharacterMoney[7] = Integer.toString(pearl);
CharacterMoney[8] = Integer.toString(sapphire);
CharacterMoney[9] = Integer.toString(ruby);
CharacterMoney[10] = Integer.toString(gem);
CharacterMoney[11] = Integer.toString(emerald);
CharacterMoney[12] = Integer.toString(diamond);

CharacterMoney[13] = Integer.toString(rcopper);
CharacterMoney[14] = Integer.toString(rsilver);
CharacterMoney[15] = Integer.toString(rgold);
CharacterMoney[16] = Integer.toString(ropal);
CharacterMoney[17] = Integer.toString(rtopaz);
CharacterMoney[18] = Integer.toString(rlapis);
CharacterMoney[19] = Integer.toString(rgarnet);
CharacterMoney[20] = Integer.toString(rpearl);
CharacterMoney[21] = Integer.toString(rsapphire);
CharacterMoney[22] = Integer.toString(rruby);
CharacterMoney[23] = Integer.toString(rgem);
CharacterMoney[24] = Integer.toString(remerald);
CharacterMoney[25] = Integer.toString(rdiamond);
} // END void UpdateGemsANDCoins()


// False Save Character
public void Save() { CharacterSave(); }

// Saves Character
protected void CharacterSave() {
int MOSaveStatusSize = 0;
int MOSaveStatsSize = 0;
int MOSaveMoneySize = 0;
int MOCochesSize = 0;
String CharacterInfos = "";

while ( MOSaveStatusSize < 22 ) {
CharacterInfos = CharacterInfos + "%" + CharacterStatus[MOSaveStatusSize];
MOSaveStatusSize = MOSaveStatusSize + 1;
}

while ( MOSaveStatsSize < 69)  {
CharacterInfos = CharacterInfos + "%" + CharacterStats[MOSaveStatsSize];
MOSaveStatsSize = MOSaveStatsSize + 1;
}

while ( MOSaveMoneySize < 26 ) {
CharacterInfos = CharacterInfos + "%" + CharacterMoney[MOSaveMoneySize];
MOSaveMoneySize = MOSaveMoneySize + 1;
}

while ( MOCochesSize < 12 ) {
CharacterInfos = CharacterInfos + "%" + CharacterCoches[MOCochesSize];
MOCochesSize = MOCochesSize + 1;
}

String save = MOWorldCall("MOSaveCH", CharacterInfos);

// For Garbage Collection...
save = null;
CharacterInfos = null;
} // END void MOSave()


// Verifies that Character is Online
protected void VerifyOnlineStatus() {
if (connectedstatus.equals("online")) {} else {
try { getAppletContext().showDocument(new URL("javascript:self.close()")); } catch (Exception StopException) { basewindow = null; basedocument = null; }
myCharacter = "null"; myAccount = "null";
res = "offline"; maxres = "offline"; mag = "offline"; maxmag = "offline"; mor = "offline"; maxmor = "offline"; fame = "offline"; money = "offline";
basewindow = null; basedocument = null;
}
} // END void VerifyOnlineStatus()


// Verifies that Character is Able to Do Action in Sublocation
protected void VerifySubLocationAction(String ChosenAction) {
boolean CanPerformAction = true;

if (ChosenAction.equals("loots") || ChosenAction.equals("wandering foes")) {  if (CharacterStatus[15].equals("Surroundings")) { } else { CanPerformAction = false; }
} else if (ChosenAction.equals("charity") || ChosenAction.equals("invest") || ChosenAction.equals("informant")) {  if (CharacterStatus[15].equals("Town Hall")) { } else { CanPerformAction = false; }
} else if (ChosenAction.equals("status") || ChosenAction.equals("logout") || ChosenAction.equals("inventory") || ChosenAction.equals("equipment") || ChosenAction.equals("goto") || ChosenAction.equals("messages") || ChosenAction.equals("give to")) {
} else if (ChosenAction.equals("gamble")) { if (CharacterStatus[15].equals("Tavern")) { } else { CanPerformAction = false; }
} else if (ChosenAction.equals("meal")) {  if (CharacterStatus[15].equals("Inn") || CharacterStatus[15].equals("Tavern")) { } else { CanPerformAction = false; }
} else if (ChosenAction.equals("wizard") || ChosenAction.equals("sage")) {  if (CharacterStatus[15].equals("Temple")) { } else { CanPerformAction = false; }
} else {}

if (CanPerformAction == false) {
String wrongactionhtml = starthtml  + "<br><br>" + fontset + "<b>You cannot perform " + ChosenAction + " here.<br><br>You have been merged into the world... Please relogin.</b></font>" + endhtml;
OutputHTML( wrongactionhtml, "ChosenActionError" );
stop();
} else {}
} // END void VerifySubLocationAction()


// Statuses Modify Stats
protected int StatsMods(String smod, int smodtype) {
// 0 = power mods, 1 = might, NO TOUGHNESS MODS
// 2 = awareness, 3 = focus, 4 = maxmagic (NO MAGIC MODS)
// 5 = accuracy, 6 = evasion, 7 = agility

int mysmod = Integer.parseInt(smod);

if (CharacterStats[60].equals(Tired[1])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.88);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.88);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.88);
  } else {}
} else if (CharacterStats[60].equals(Tired[2])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.76);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.76);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.76); 
  } else {}
} else if (CharacterStats[60].equals(Tired[3])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.64); 
  } else {}
} else if (CharacterStats[60].equals(Tired[4])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.52);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.52);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.52);
  } else {}
} else if (CharacterStats[60].equals(Tired[5])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.40);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.40);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.40);
  } else {}
} else {}

if (CharacterStats[62].equals(Paralyzed[1])) {
// 1, 2, 5, 6, 7
} else if (CharacterStats[62].equals(Paralyzed[2])) {
} else if (CharacterStats[62].equals(Paralyzed[3])) {
} else if (CharacterStats[62].equals(Paralyzed[4])) {
} else if (CharacterStats[62].equals(Paralyzed[5])) {
} else {}

if (CharacterStats[63].equals(Blind[1])) {
// 3, 5, 6
} else if (CharacterStats[63].equals(Blind[2])) {
} else if (CharacterStats[63].equals(Blind[3])) {
} else if (CharacterStats[63].equals(Blind[4])) {
} else if (CharacterStats[63].equals(Blind[5])) {
} else {}

if (CharacterStats[64].equals(Sluggish[1])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.82);
  } else if (smodtype == 1) { mysmod = (int) (mysmod * 0.82);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.82);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.82);
  } else if (smodtype == 4) { mysmod = (int) (mysmod * 0.82);
  } else if (smodtype == 5) { mysmod = (int) (mysmod * 0.82);
  } else if (smodtype == 6) { mysmod = (int) (mysmod * 0.82);
  } else if (smodtype == 7) { mysmod = (int) (mysmod * 0.82);
  } else {}
} else if (CharacterStats[64].equals(Sluggish[2])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 1) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 4) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 5) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 6) { mysmod = (int) (mysmod * 0.64);
  } else if (smodtype == 7) { mysmod = (int) (mysmod * 0.64);
  } else {}
} else if (CharacterStats[64].equals(Sluggish[3])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.46);
  } else if (smodtype == 1) { mysmod = (int) (mysmod * 0.46);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.46);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.46);
  } else if (smodtype == 4) { mysmod = (int) (mysmod * 0.46);
  } else if (smodtype == 5) { mysmod = (int) (mysmod * 0.46);
  } else if (smodtype == 6) { mysmod = (int) (mysmod * 0.46);
  } else if (smodtype == 7) { mysmod = (int) (mysmod * 0.46);
  } else {}
} else if (CharacterStats[64].equals(Sluggish[4])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.28);
  } else if (smodtype == 1) { mysmod = (int) (mysmod * 0.28);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.28);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.28);
  } else if (smodtype == 4) { mysmod = (int) (mysmod * 0.28);
  } else if (smodtype == 5) { mysmod = (int) (mysmod * 0.28);
  } else if (smodtype == 6) { mysmod = (int) (mysmod * 0.28);
  } else if (smodtype == 7) { mysmod = (int) (mysmod * 0.28);
  } else {}
} else if (CharacterStats[64].equals(Sluggish[5])) {
  if (smodtype == 0) { mysmod = (int) (mysmod * 0.10);
  } else if (smodtype == 1) { mysmod = (int) (mysmod * 0.10);
  } else if (smodtype == 2) { mysmod = (int) (mysmod * 0.10);
  } else if (smodtype == 3) { mysmod = (int) (mysmod * 0.10);
  } else if (smodtype == 4) { mysmod = (int) (mysmod * 0.10);
  } else if (smodtype == 5) { mysmod = (int) (mysmod * 0.10);
  } else if (smodtype == 6) { mysmod = (int) (mysmod * 0.10);
  } else if (smodtype == 7) { mysmod = (int) (mysmod * 0.10);
  } else {}
} else {}

if (mysmod <= 0) { mysmod = 1; } else {}
return mysmod;
} // END StatsMods


// Sluggish Status Modifier
protected void SluggishModifier(int mods) {
if (mods == -1) {
if (CharacterStats[64].equals(" ") || CharacterStats[64].equals(Sluggish[1])) { CharacterStats[64] = " ";
} else if ( CharacterStats[64].equals(Sluggish[2])) { CharacterStats[64] = Sluggish[1];
} else if ( CharacterStats[64].equals(Sluggish[3])) { CharacterStats[64] = Sluggish[2];
} else if ( CharacterStats[64].equals(Sluggish[4])) { CharacterStats[64] = Sluggish[3];
} else { CharacterStats[64] = Sluggish[4]; }

} else if (mods == -2) {
if (CharacterStats[64].equals(" ") || CharacterStats[64].equals(Sluggish[1]) || CharacterStats[64].equals(Sluggish[2])) { CharacterStats[64] = " ";
} else if ( CharacterStats[64].equals(Sluggish[3])) { CharacterStats[64] = Sluggish[1];
} else if ( CharacterStats[64].equals(Sluggish[4])) { CharacterStats[64] = Sluggish[2];
} else { CharacterStats[64] = Sluggish[3]; }

} else if (mods == -3) {
if (CharacterStats[64].equals(" ") || CharacterStats[64].equals(Sluggish[1]) || CharacterStats[64].equals(Sluggish[2]) || CharacterStats[64].equals(Sluggish[3])) { CharacterStats[64] = " ";
} else if ( CharacterStats[64].equals(Sluggish[4])) { CharacterStats[64] = Sluggish[1];
} else { CharacterStats[64] = Sluggish[2]; }

} else if (mods == -4) {
if (CharacterStats[64].equals(" ") || CharacterStats[64].equals(Sluggish[1]) || CharacterStats[64].equals(Sluggish[2]) || CharacterStats[64].equals(Sluggish[3]) || CharacterStats[64].equals(Sluggish[4])) { CharacterStats[64] = " ";
} else { CharacterStats[64] = Sluggish[1]; }

} else if (mods == -5) {
CharacterStats[64] = " ";

} else if (mods == 1) {

if (CharacterStats[64].equals(Sluggish[5]) || CharacterStats[64].equals(Sluggish[4])) { CharacterStats[64] = Sluggish[5];
} else if ( CharacterStats[64].equals(Sluggish[3])) { CharacterStats[64] = Sluggish[4];
} else if ( CharacterStats[64].equals(Sluggish[2])) { CharacterStats[64] = Sluggish[3];
} else if ( CharacterStats[64].equals(Sluggish[1])) { CharacterStats[64] = Sluggish[2];
} else { CharacterStats[64] = Sluggish[1]; }

} else if (mods == 2) {
if (CharacterStats[64].equals(Sluggish[5]) || CharacterStats[64].equals(Sluggish[4]) || CharacterStats[64].equals(Sluggish[3])) { CharacterStats[64] = Sluggish[5];
} else if ( CharacterStats[64].equals(Sluggish[2])) { CharacterStats[64] = Sluggish[4];
} else if ( CharacterStats[64].equals(Sluggish[1])) { CharacterStats[64] = Sluggish[3];
} else { CharacterStats[64] = Sluggish[2]; }

} else if (mods == 3) {
if (CharacterStats[64].equals(Sluggish[5]) || CharacterStats[64].equals(Sluggish[4]) || CharacterStats[64].equals(Sluggish[3]) || CharacterStats[64].equals(Sluggish[2])) { CharacterStats[64] = Sluggish[5];
} else if (CharacterStats[64].equals(Sluggish[1])) { CharacterStats[64] = Sluggish[4];
} else { CharacterStats[64] = Sluggish[3]; }

} else if (mods == 4) {
if (CharacterStats[64].equals(Sluggish[5]) || CharacterStats[64].equals(Sluggish[4]) || CharacterStats[64].equals(Sluggish[3]) || CharacterStats[64].equals(Sluggish[2]) || CharacterStats[64].equals(Sluggish[1])) { CharacterStats[64] = Sluggish[5];
} else { CharacterStats[64] = Sluggish[4]; }

} else if (mods == 5) { CharacterStats[64] = Sluggish[5];
} else { }
} // END void SluggishModifier()


// ParalyzedModifier Status Modifier
protected void ParalyzedModifier(int mods) {
if (mods == -1) {
if (CharacterStats[62].equals(" ") || CharacterStats[62].equals(Paralyzed[1])) { CharacterStats[62] = " ";
} else if ( CharacterStats[62].equals(Paralyzed[2])) { CharacterStats[62] = Paralyzed[1];
} else if ( CharacterStats[62].equals(Paralyzed[3])) { CharacterStats[62] = Paralyzed[2];
} else if ( CharacterStats[62].equals(Paralyzed[4])) { CharacterStats[62] = Paralyzed[3];
} else { CharacterStats[62] = Paralyzed[4]; }

} else if (mods == -2) {
if (CharacterStats[62].equals(" ") || CharacterStats[62].equals(Paralyzed[1]) || CharacterStats[62].equals(Paralyzed[2])) { CharacterStats[62] = " ";
} else if ( CharacterStats[62].equals(Paralyzed[3])) { CharacterStats[62] = Paralyzed[1];
} else if ( CharacterStats[62].equals(Paralyzed[4])) { CharacterStats[62] = Paralyzed[2];
} else { CharacterStats[62] = Paralyzed[3]; }

} else if (mods == -3) {
if (CharacterStats[62].equals(" ") || CharacterStats[62].equals(Paralyzed[1]) || CharacterStats[62].equals(Paralyzed[2]) || CharacterStats[62].equals(Paralyzed[3])) { CharacterStats[62] = " ";
} else if ( CharacterStats[62].equals(Paralyzed[4])) { CharacterStats[62] = Paralyzed[1];
} else { CharacterStats[62] = Paralyzed[2]; }

} else if (mods == -4) {
if (CharacterStats[62].equals(" ") || CharacterStats[62].equals(Paralyzed[1]) || CharacterStats[62].equals(Paralyzed[2]) || CharacterStats[62].equals(Paralyzed[3]) || CharacterStats[62].equals(Paralyzed[4])) { CharacterStats[62] = " ";
} else { CharacterStats[62] = Paralyzed[1]; }

} else if (mods == -5) {
CharacterStats[62] = " ";

} else if (mods == 1) {

if (CharacterStats[62].equals(Paralyzed[5]) || CharacterStats[62].equals(Paralyzed[4])) { CharacterStats[62] = Paralyzed[5];
} else if ( CharacterStats[62].equals(Paralyzed[3])) { CharacterStats[62] = Paralyzed[4];
} else if ( CharacterStats[62].equals(Paralyzed[2])) { CharacterStats[62] = Paralyzed[3];
} else if ( CharacterStats[62].equals(Paralyzed[1])) { CharacterStats[62] = Paralyzed[2];
} else { CharacterStats[62] = Paralyzed[1]; }

} else if (mods == 2) {
if (CharacterStats[62].equals(Paralyzed[5]) || CharacterStats[62].equals(Paralyzed[4]) || CharacterStats[62].equals(Paralyzed[3])) { CharacterStats[62] = Paralyzed[5];
} else if ( CharacterStats[62].equals(Paralyzed[2])) { CharacterStats[62] = Paralyzed[4];
} else if ( CharacterStats[62].equals(Paralyzed[1])) { CharacterStats[62] = Paralyzed[3];
} else { CharacterStats[62] = Paralyzed[2]; }

} else if (mods == 3) {
if (CharacterStats[62].equals(Paralyzed[5]) || CharacterStats[62].equals(Paralyzed[4]) || CharacterStats[62].equals(Paralyzed[3]) || CharacterStats[62].equals(Paralyzed[2])) { CharacterStats[62] = Paralyzed[5];
} else if (CharacterStats[62].equals(Paralyzed[1])) { CharacterStats[62] = Paralyzed[4];
} else { CharacterStats[62] = Paralyzed[3]; }

} else if (mods == 4) {
if (CharacterStats[62].equals(Paralyzed[5]) || CharacterStats[62].equals(Paralyzed[4]) || CharacterStats[62].equals(Paralyzed[3]) || CharacterStats[62].equals(Paralyzed[2]) || CharacterStats[62].equals(Paralyzed[1])) { CharacterStats[62] = Paralyzed[5];
} else { CharacterStats[62] = Paralyzed[4]; }

} else if (mods == 5) { CharacterStats[62] = Paralyzed[5];
} else { }
} // END void ParalyzedModifier()


// Blinded Status Modifier
protected void BlindedModifier(int mods) {
  if (mods == -1) {
if (CharacterStats[63].equals(" ") || CharacterStats[63].equals(Blind[1])) { CharacterStats[63] = " ";
} else if ( CharacterStats[63].equals(Blind[2])) { CharacterStats[63] = Blind[1];
} else if ( CharacterStats[63].equals(Blind[3])) { CharacterStats[63] = Blind[2];
} else if ( CharacterStats[63].equals(Blind[4])) { CharacterStats[63] = Blind[3];
} else { CharacterStats[63] = Blind[4]; }

  } else if (mods == -2) {
if (CharacterStats[63].equals(" ") || CharacterStats[63].equals(Blind[1]) || CharacterStats[63].equals(Blind[2])) { CharacterStats[63] = " ";
} else if ( CharacterStats[63].equals(Blind[3])) { CharacterStats[63] = Blind[1];
} else if ( CharacterStats[63].equals(Blind[4])) { CharacterStats[63] = Blind[2];
} else { CharacterStats[63] = Blind[3]; }

  } else if (mods == -3) {
if (CharacterStats[63].equals(" ") || CharacterStats[63].equals(Blind[1]) || CharacterStats[63].equals(Blind[2]) || CharacterStats[63].equals(Blind[3])) { CharacterStats[63] = " ";
} else if ( CharacterStats[63].equals(Blind[4])) { CharacterStats[63] = Blind[1];
} else { CharacterStats[63] = Blind[2]; }

  } else if (mods == -4) {
if (CharacterStats[63].equals(" ") || CharacterStats[63].equals(Blind[1]) || CharacterStats[63].equals(Blind[2]) || CharacterStats[63].equals(Blind[3]) || CharacterStats[63].equals(Blind[4])) { CharacterStats[63] = " ";
} else { CharacterStats[63] = Blind[1]; }

  } else if (mods == -5) {
CharacterStats[63] = " ";

  } else if (mods == 1) {
if (CharacterStats[63].equals(Blind[5]) || CharacterStats[63].equals(Blind[4])) { CharacterStats[63] = Blind[5];
} else if ( CharacterStats[63].equals(Blind[3])) { CharacterStats[63] = Blind[4];
} else if ( CharacterStats[63].equals(Blind[2])) { CharacterStats[63] = Blind[3];
} else if ( CharacterStats[63].equals(Blind[1])) { CharacterStats[63] = Blind[2];
} else { CharacterStats[63] = Blind[1]; }

  } else if (mods == 2) {
if (CharacterStats[63].equals(Blind[5]) || CharacterStats[63].equals(Blind[4]) || CharacterStats[63].equals(Blind[3])) { CharacterStats[63] = Blind[5];
} else if ( CharacterStats[63].equals(Blind[2])) { CharacterStats[63] = Blind[4];
} else if ( CharacterStats[63].equals(Blind[1])) { CharacterStats[63] = Blind[3];
} else { CharacterStats[63] = Blind[2]; }

  } else if (mods == 3) {
if (CharacterStats[63].equals(Blind[5]) || CharacterStats[63].equals(Blind[4]) || CharacterStats[63].equals(Blind[3]) || CharacterStats[63].equals(Blind[2])) { CharacterStats[63] = Blind[5];
} else if (CharacterStats[63].equals(Blind[1])) { CharacterStats[63] = Blind[4];
} else { CharacterStats[63] = Blind[3]; }

  } else if (mods == 4) {
if (CharacterStats[63].equals(Blind[5]) || CharacterStats[63].equals(Blind[4]) || CharacterStats[63].equals(Blind[3]) || CharacterStats[63].equals(Blind[2]) || CharacterStats[63].equals(Blind[1])) { CharacterStats[63] = Blind[5];
} else { CharacterStats[63] = Blind[4]; }

  } else if (mods == 5) { CharacterStats[63] = Blind[5];
  } else { }
} // END void BlindModifier()


// Poisonned Status Modifier
protected void PoisonnedModifier(int mods) {
if (mods == -1) {
if (CharacterStats[61].equals(" ") || CharacterStats[61].equals(Poisoned[1])) { CharacterStats[61] = " ";
} else if ( CharacterStats[61].equals(Poisoned[2])) { CharacterStats[61] = Poisoned[1];
} else if ( CharacterStats[61].equals(Poisoned[3])) { CharacterStats[61] = Poisoned[2];
} else if ( CharacterStats[61].equals(Poisoned[4])) { CharacterStats[61] = Poisoned[3];
} else { CharacterStats[61] = Poisoned[4]; }

} else if (mods == -2) {
if (CharacterStats[61].equals(" ") || CharacterStats[61].equals(Poisoned[1]) || CharacterStats[61].equals(Poisoned[2])) { CharacterStats[61] = " ";
} else if ( CharacterStats[61].equals(Poisoned[3])) { CharacterStats[61] = Poisoned[1];
} else if ( CharacterStats[61].equals(Poisoned[4])) { CharacterStats[61] = Poisoned[2];
} else { CharacterStats[61] = Poisoned[3]; }

} else if (mods == -3) {
if (CharacterStats[61].equals(" ") || CharacterStats[61].equals(Poisoned[1]) || CharacterStats[61].equals(Poisoned[2]) || CharacterStats[61].equals(Poisoned[3])) { CharacterStats[61] = " ";
} else if ( CharacterStats[61].equals(Poisoned[4])) { CharacterStats[61] = Poisoned[1];
} else { CharacterStats[61] = Poisoned[2]; }

} else if (mods == -4) {
if (CharacterStats[61].equals(" ") || CharacterStats[61].equals(Poisoned[1]) || CharacterStats[61].equals(Poisoned[2]) || CharacterStats[61].equals(Poisoned[3]) || CharacterStats[61].equals(Poisoned[4])) { CharacterStats[61] = " ";
} else { CharacterStats[61] = Poisoned[1]; }

} else if (mods == -5) {
CharacterStats[61] = " ";

} else if (mods == 1) {

if (CharacterStats[61].equals(Poisoned[5]) || CharacterStats[61].equals(Poisoned[4])) { CharacterStats[61] = Poisoned[5];
} else if ( CharacterStats[61].equals(Poisoned[3])) { CharacterStats[61] = Poisoned[4];
} else if ( CharacterStats[61].equals(Poisoned[2])) { CharacterStats[61] = Poisoned[3];
} else if ( CharacterStats[61].equals(Poisoned[1])) { CharacterStats[61] = Poisoned[2];
} else { CharacterStats[61] = Poisoned[1]; }

} else if (mods == 2) {
if (CharacterStats[61].equals(Poisoned[5]) || CharacterStats[61].equals(Poisoned[4]) || CharacterStats[61].equals(Poisoned[3])) { CharacterStats[61] = Poisoned[5];
} else if ( CharacterStats[61].equals(Poisoned[2])) { CharacterStats[61] = Poisoned[4];
} else if ( CharacterStats[61].equals(Poisoned[1])) { CharacterStats[61] = Poisoned[3];
} else { CharacterStats[61] = Poisoned[2]; }

} else if (mods == 3) {
if (CharacterStats[61].equals(Poisoned[5]) || CharacterStats[61].equals(Poisoned[4]) || CharacterStats[61].equals(Poisoned[3]) || CharacterStats[61].equals(Poisoned[2])) { CharacterStats[61] = Poisoned[5];
} else if (CharacterStats[61].equals(Poisoned[1])) { CharacterStats[61] = Poisoned[4];
} else { CharacterStats[61] = Poisoned[3]; }

} else if (mods == 4) {
if (CharacterStats[61].equals(Poisoned[5]) || CharacterStats[61].equals(Poisoned[4]) || CharacterStats[61].equals(Poisoned[3]) || CharacterStats[61].equals(Poisoned[2]) || CharacterStats[61].equals(Poisoned[1])) { CharacterStats[61] = Poisoned[5];
} else { CharacterStats[61] = Poisoned[4]; }

} else if (mods == 5) { CharacterStats[61] = Poisoned[5];
} else { }
} // END void PoisonnedModifier()


// Tired Status Modifier
protected void TiredModifier(int mods) {
if (mods == -1) {
if (CharacterStats[60].equals(" ") || CharacterStats[60].equals(Tired[1])) { CharacterStats[60] = " ";
} else if ( CharacterStats[60].equals(Tired[2])) { CharacterStats[60] = Tired[1];
} else if ( CharacterStats[60].equals(Tired[3])) { CharacterStats[60] = Tired[2];
} else if ( CharacterStats[60].equals(Tired[4])) { CharacterStats[60] = Tired[3];
} else { CharacterStats[60] = Tired[4]; }

} else if (mods == -2) {
if (CharacterStats[60].equals(" ") || CharacterStats[60].equals(Tired[1]) || CharacterStats[60].equals(Tired[2])) { CharacterStats[60] = " ";
} else if ( CharacterStats[60].equals(Tired[3])) { CharacterStats[60] = Tired[1];
} else if ( CharacterStats[60].equals(Tired[4])) { CharacterStats[60] = Tired[2];
} else { CharacterStats[60] = Tired[3]; }

} else if (mods == -3) {
if (CharacterStats[60].equals(" ") || CharacterStats[60].equals(Tired[1]) || CharacterStats[60].equals(Tired[2]) || CharacterStats[60].equals(Tired[3])) { CharacterStats[60] = " ";
} else if ( CharacterStats[60].equals(Tired[4])) { CharacterStats[60] = Tired[1];
} else { CharacterStats[60] = Tired[2]; }

} else if (mods == -4) {
if (CharacterStats[60].equals(" ") || CharacterStats[60].equals(Tired[1]) || CharacterStats[60].equals(Tired[2]) || CharacterStats[60].equals(Tired[3]) || CharacterStats[60].equals(Tired[4])) { CharacterStats[60] = " ";
} else { CharacterStats[60] = Tired[1]; }

} else if (mods == -5) {
CharacterStats[60] = " ";

} else if (mods == 1) {

if (CharacterStats[60].equals(Tired[5]) || CharacterStats[60].equals(Tired[4])) { CharacterStats[60] = Tired[5];
} else if ( CharacterStats[60].equals(Tired[3])) { CharacterStats[60] = Tired[4];
} else if ( CharacterStats[60].equals(Tired[2])) { CharacterStats[60] = Tired[3];
} else if ( CharacterStats[60].equals(Tired[1])) { CharacterStats[60] = Tired[2];
} else { CharacterStats[60] = Tired[1]; }

} else if (mods == 2) {
if (CharacterStats[60].equals(Tired[5]) || CharacterStats[60].equals(Tired[4]) || CharacterStats[60].equals(Tired[3])) { CharacterStats[60] = Tired[5];
} else if ( CharacterStats[60].equals(Tired[2])) { CharacterStats[60] = Tired[4];
} else if ( CharacterStats[60].equals(Tired[1])) { CharacterStats[60] = Tired[3];
} else { CharacterStats[60] = Tired[2]; }

} else if (mods == 3) {
if (CharacterStats[60].equals(Tired[5]) || CharacterStats[60].equals(Tired[4]) || CharacterStats[60].equals(Tired[3]) || CharacterStats[60].equals(Tired[2])) { CharacterStats[60] = Tired[5];
} else if (CharacterStats[60].equals(Tired[1])) { CharacterStats[60] = Tired[4];
} else { CharacterStats[60] = Tired[3]; }

} else if (mods == 4) {
if (CharacterStats[60].equals(Tired[5]) || CharacterStats[60].equals(Tired[4]) || CharacterStats[60].equals(Tired[3]) || CharacterStats[60].equals(Tired[2]) || CharacterStats[60].equals(Tired[1])) { CharacterStats[60] = Tired[5];
} else { CharacterStats[60] = Tired[4]; }

} else if (mods == 5) { CharacterStats[60] = Tired[5];
} else { }
} // END void TiredModifier()


// Health Status Modifier
protected void HealthModifier(int mods) {
if (mods == -1) {
if (CharacterStats[65].equals(" ") || CharacterStats[65].equals(Health[2])) { CharacterStats[65] = " ";
} else if ( CharacterStats[65].equals(Health[1])) { CharacterStats[65] = Health[2];
} else if ( CharacterStats[65].equals(Health[3])) { CharacterStats[65] = Health[1];
} else if ( CharacterStats[65].equals(Health[4])) { CharacterStats[65] = Health[3];
} else { CharacterStats[65] = Health[4]; }

} else if (mods == -2) {
if (CharacterStats[65].equals(" ") || CharacterStats[65].equals(Health[2]) || CharacterStats[65].equals(Health[1])) { CharacterStats[65] = " ";
} else if ( CharacterStats[65].equals(Health[3])) { CharacterStats[65] = Health[2];
} else if ( CharacterStats[65].equals(Health[4])) { CharacterStats[65] = Health[1];
} else { CharacterStats[65] = Health[3]; }

} else if (mods == -3) {
if (CharacterStats[65].equals(" ") || CharacterStats[65].equals(Health[2]) || CharacterStats[65].equals(Health[1]) || CharacterStats[65].equals(Health[3])) { CharacterStats[65] = " ";
} else if ( CharacterStats[65].equals(Health[4])) { CharacterStats[65] = Health[2];
} else { CharacterStats[65] = Health[1]; }

} else if (mods == -4) {
if (CharacterStats[65].equals(" ") || CharacterStats[65].equals(Health[2]) || CharacterStats[65].equals(Health[1]) || CharacterStats[65].equals(Health[3]) || CharacterStats[65].equals(Health[4])) { CharacterStats[65] = " ";
} else { CharacterStats[65] = Health[2]; }

} else if (mods == -5) {
CharacterStats[65] = " ";

} else if (mods == 1) {

if (CharacterStats[65].equals(Health[5]) || CharacterStats[65].equals(Health[4])) { CharacterStats[65] = Health[5];
} else if ( CharacterStats[65].equals(Health[3])) { CharacterStats[65] = Health[4];
} else if ( CharacterStats[65].equals(Health[1])) { CharacterStats[65] = Health[3];
} else if ( CharacterStats[65].equals(Health[2])) { CharacterStats[65] = Health[1];
} else { CharacterStats[65] = Health[2]; }

} else if (mods == 2) {
if (CharacterStats[65].equals(Health[5]) || CharacterStats[65].equals(Health[4]) || CharacterStats[65].equals(Health[3])) { CharacterStats[65] = Health[5];
} else if ( CharacterStats[65].equals(Health[1])) { CharacterStats[65] = Health[4];
} else if ( CharacterStats[65].equals(Health[2])) { CharacterStats[65] = Health[3];
} else { CharacterStats[65] = Health[1]; }

} else if (mods == 3) {
if (CharacterStats[65].equals(Health[5]) || CharacterStats[65].equals(Health[4]) || CharacterStats[65].equals(Health[3]) || CharacterStats[65].equals(Health[1])) { CharacterStats[65] = Health[5];
} else if (CharacterStats[65].equals(Health[2])) { CharacterStats[65] = Health[4];
} else { CharacterStats[65] = Health[3]; }

} else if (mods == 4) {
if (CharacterStats[65].equals(Health[5]) || CharacterStats[65].equals(Health[4]) || CharacterStats[65].equals(Health[3]) || CharacterStats[65].equals(Health[1]) || CharacterStats[65].equals(Health[2])) { CharacterStats[65] = Health[5];
} else { CharacterStats[65] = Health[4]; }

} else if (mods == 5) { CharacterStats[65] = Health[5];
} else { }
} // END void HealthModifier()


// Morale Reducer, Regeneration - Public Function Called from JS -> PrivateMoraleReducer();
public void MoraleReducer() {
VerifyOnlineStatus();
PrivateMoraleReducer();
} // END void MoraleReducer()

// Reduces Morale Every Minutes (12 Server Minutes)
protected void PrivateMoraleReducer() {
int newmorale = Integer.parseInt(CharacterStats[39]);
if (newmorale > 1) { newmorale = newmorale - 1; CharacterStats[39] = (new Integer(newmorale)).toString(); } else {}

// REGENERATION
int newresi = Integer.parseInt(CharacterStats[6]) + Integer.parseInt(CharacterStats[52]);

// POISON EFFECT
if (CharacterStats[61].equals(Poisoned[1])) { newresi = (int) ((double) newresi - (Integer.parseInt(CharacterStats[8]) * 0.05));
} else if ( CharacterStats[61].equals(Poisoned[2])) { newresi = (int) ((double) newresi - (Integer.parseInt(CharacterStats[8]) * 0.1));
} else if ( CharacterStats[61].equals(Poisoned[3])) { newresi = (int) ((double) newresi - (Integer.parseInt(CharacterStats[8]) * 0.15));
} else if ( CharacterStats[61].equals(Poisoned[4])) { newresi = (int) ((double) newresi - (Integer.parseInt(CharacterStats[8]) * 0.2));
} else if ( CharacterStats[61].equals(Poisoned[5])) { newresi = (int) ((double) newresi - (Integer.parseInt(CharacterStats[8]) * 0.25));
} else {}

if (newresi > Integer.parseInt(CharacterStats[8])) { CharacterStats[6] = CharacterStats[8]; } else { CharacterStats[6] = (new Integer(newresi)).toString(); }

UpdateStats();
} // END void PrivateMoraleReducer()


// Outputs HTML Generated by MOMain - SPECIFIC TO NETSCAPE
protected void OutputHTML( String generatedhtml, String pagename ) {

generatedhtml = generatedhtml.replace('\'', ' ');
generatedhtml = generatedhtml.replace('"', ' ');

try {
generatedhtml = "javascript:'" + generatedhtml + "'" ;
getAppletContext().showDocument((new URL("javascript:OpenMOWindow(\"" + generatedhtml + "\",\"" + pagename + "\" )"))); }
catch (Exception ExOutputHTML) { stop(); }
// For Garbage Collection...
generatedhtml = null;
pagename = null;
} // END void OutputHTML()

public String getAppletInfo() {
return "";
}

}
