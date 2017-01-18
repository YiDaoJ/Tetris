/**
 * Created by Administrator on 08-Jan-17.
 */
public class Fivetris {
    public static void main(String[]args){
        // Objekt von Fivetris erzeugen
        Fivetris spiel = new Fivetris();

        // Spiel ablaufen
        spiel.programmablauf();

    } // Ende main

    /**
     * Ablauf der ganzen Spielrunde.
     */
    public void programmablauf(){
        /* Erzeugen und Initialisieren von notwendigen Objekten */
        // Ein Tastatur-Objekt erzeugen
        Tastatur tastatur = Tastatur.tastatur();
        // String erzeugen, um die Tastatur-Eingabe zu speichern
        String s;

        /* Begrüßung-Ausgabe mit Erklärung */
        System.out.println("Willkommen im Spiel 5Tris. \n");
        System.out.println("A: Block um 1 nach links bewegen \n" +
                "D: Block um 1 nach rechts bewegen \n" +
                "W: Block um 90 Grad im Uhrzeigersinn drehen \n" +
                "S: Block um 1 nach unten bewegen \n" +
                "Q: bricht die laufende Runde ab. \n");

        // neue Spielrunde erstellen
        do{ // Eine Spielrunde muss zuerst ablaufen, dann abfrage, ob neue Runde laut Eingabe beginnt.
            spielrunde();
            s =  tastatur.nextLineFromKeyboard();
        }while(neuRunde(s));
    }

    /**
     * Steuere eine neue Speilrunde nach der Eingabe.
     * @param s Eingabe
     * @return true wenn eingabe "j", "ja" oder "J" ist.
     */
    public boolean neuRunde(String s){
        // Variable zur Speicherung des Ergebnises
        boolean newRound = false;
        if(s.equals("j") || s.equals("ja") || s.equals("J")){
            newRound = true;
        }else if(s.equals("n") || s.equals("nein") || s.equals("N")){
            System.out.println("Bye!");
            newRound = false;
        }
        return newRound;
    }


    /**
     * Durchfuehrung einer Spielrunde.
     */
    public void spielrunde(){
        // Tastatur-Objekt erzeugen
        Tastatur tastatur = Tastatur.tastatur();
        String s;

        // Spielfeld-Objekt erzeugen
        Spielfeld spielfeld = new Spielfeld();

        // Block-Objekt erzegen
        Block bblock = new Block(0, 3, 0, spielfeld);
        Block block;

        // boolean-Variable, um den Abbruch-Zustand zu speichern.
        boolean abbrechen = false;

        do {// Erzeugen eines neun Blocks, solange der aktuelle Block nicht mehr weglich ist.

            // Erzeugen eines zufaelligen neuen Blocks.
            int zufall = (int)Math.round(Math.random() * 4);
            // Setze zufaelligeb erzeugteb Block auf "block".
            block =  zufalligBlock(zufall, bblock, spielfeld);

            // Weiter geht die Spielrunde,
            // bis beim Erzeugen eines Blocks eine Kollision (hinsichtlich einer Spalte) auftritt oder die Runde abgebrochen wurde
            if(block.kollisionImSpielfeld()){
                System.out.println("Game Over");
                System.out.println("Neues Spiel?");
                abbrechen = true;
//                break;
            }else{
            // wenn keine Kollision auftritt
                do{
                    /* Darstellen des Spielfelds mit uebertragendem Block */
                    block.uebertrageInsSpielfeld();
                    spielfeld.drucken();

                    // Block bewegen und Befehl von Abbrechen speichern
                    s =  tastatur.nextLineFromKeyboard();
                    abbrechen = blockBewegen(s, block);

                // Wenn der Block sich weiter (nach unten) bewegen kann und das Spiel nicht abgebrochen ist,
                // wird die Schleife weiter durchgefuehrt.
                }while(block.obWeiterBewegen() && !abbrechen);   // Ende do-While innere

                // Volle Zeilen im Spielfeld loeschen
                spielfeld.loeschenVolleZeile();

            }   // Ende if-else

        // Wenn das aktuelle Spiel nicht abgebrochen wird, kann neuer Block weiter erzeugt werden.
        } while (!abbrechen);   // Ende do-While aussere

    } // Ende spielrunde()

    /**
     * Erzeuge verschiedene Block-Instanz laut der eigegebenen Index.
     * @param zufall index fuer Block
     * @param block Block-Objekt
     * @param spielfeld Spielfeld-Objekt
     * @return Block-instanz laut der Index
     */
    public Block zufalligBlock(int zufall, Block block, Spielfeld spielfeld) {
        switch (zufall) {
            case 0:
                block = new IBlock(spielfeld);
                break;
            case 1:
                block = new OBlock(spielfeld);
                break;
            case 2:
                block = new SBlock(spielfeld);
                break;
            case 3:
                block = new TBlock(spielfeld);
                break;
            case 4:
                block = new ZBlock(spielfeld);
                break;
        }
        return block;
    }


    /**
     * Steure Action des Blocks und Status der Spielrunde laut der Eingabe.
     * @param s Eingabe
     * @param block gestreuerter Block
     * @return true, wenn Spiel nach Eingabe abgebrochen.
     */
    public boolean blockBewegen(String s, Block block){

        boolean spielAbbrechen = false;

        switch (s) {
            case "A":
            case "a":
                block.nachLinksVerschieben();
                break;

            case "D":
            case "d":
                block.nachRechtsVerschieben();
                break;

            case "W":
            case "w":
                block.um90Drehen();
                break;

            case "S":
            case "s":
                block.nachUntenVerschieben();
                break;

            case "Q":
            case "q":
                spielAbbrechen = true;
                System.out.println("Aktuelle Runde aufgeben.");
                System.out.println("Neues Spiel?");
                break;

            default:
                System.out.println("Eingabe ist nicht gueltig.");
        }
        return spielAbbrechen;
    }

}
