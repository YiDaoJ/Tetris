//@start codecd902d40-be3a-20bd-7ce5-f57b412a5ae4
import java.io.*;// diese Zeile muss ganz am Anfang des files stehen
	
/** ******************************************
* Die Klasse stellt die Tastatur für die Eingabe zur Verfügung
*
* Es darf nur ein Tastatur-Objekt pro Programm existieren !
*
* Sie müssen es daher in main() 1x erzeugen und dann an alle 
* Objekte übergeben, die von der Tastatur lesen wollen.
************************************************/
class Tastatur {

	// IV
	private BufferedReader tast=null;
	static private Tastatur t = null;
	
/** ******************************************
* Beim Erzeugen einer Tastatur wird sie initialisiert.
* Wenn das daneben geht, wird eine Meldung ausgegeben
*
* Es darf nur ein Tastatur-Objekt pro Programm existieren !
* Daher bekommt man dieses eine Objekt nur über eine Methode
************************************************/
private Tastatur() {
try{	tast = new BufferedReader(new InputStreamReader(System.in)); 
   } catch(Exception e) { 
   System.out.println ("Fehler bei Init der Tastatur");}
}


static Tastatur tastatur() {
	if(t == null)
		t = new Tastatur();
	return t;
}

/** ******************************************
* Diese Methode liest eine Zeile von der Konsole ein
* Eine Zeile wird immer durch die Eingabetaste („Return“) abgeschlossen 
*
* @return	eingelesene Zeile als String
************************************************/
String nextLineFromKeyboard() { 
String line = "";
try{	line = tast.readLine(); 
   } catch(Exception e) { return "q ** Fehler bei der Eingabe";}
return line;
}


/** ******************************************
* Ein main() zum Testen der Tastatur
************************************************/
public static void main(String[] args) {
String s ="";		// der eingelesene String 
Tastatur tastatur = Tastatur.tastatur();  // Erzeuge eine Tastatur (nur 1x im Programm!)
long l;
System.out.println("Geben Sie etwas ein und beenden mit Return");
while(!s.equals("q")) {
	l = Math.round(Math.random() * 4);
	s = tastatur.nextLineFromKeyboard();	// lese eine Zeile ein
	System.out.println(l + " --> " + s);		// und gib sie aus
	}
System.out.println("Ende");
}

} // end class Tastatur


//@end codecd902d40-be3a-20bd-7ce5-f57b412a5ae4