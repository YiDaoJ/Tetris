class Spielfeld {

    // Das Spielfeld besteht aus einem zweidimensionalen Feld von der Größe 10 Zeilen x 8 Spalten.
    private char[][] spielfeld = new char[10][8];
    private char farbe = ' ';

    Spielfeld() {
        farbeEinstellen(' ');
    }

    /**
     * Das Spielfeld wird auf die Konsole ausgegeben.
     */
    public void drucken(){

        System.out.print("\t\t\t");
        for(int i = 0; i < this.spielfeld[0].length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();

        for (int i = 0; i < this.spielfeld.length; i++){
            System.out.print("Zeile " + i + ":\t");	// Überschrift(Zeilenindex) für jede Zeile

            for(int j = 0; j < this.spielfeld[i].length; j++){
                System.out.print(this.spielfeld[i][j] + "\t");
            }

            System.out.println();
        }
        System.out.println();
    }

    /**
     * Farbe fuer das Spielfeld einsetzen.
     * @param farbe der einzusetzende Farbe
     */
    public void farbeEinstellen(char farbe){
        for(int i = 0; i < spielfeld.length; i++){		// kontrolliert jede Zeile
            for(int j = 0; j < spielfeld[i].length; j++){ 	// kontrolliert jedes Elemnt in Zeile
                spielfeld[i][j] = farbe;		// Jedes Element zuweisen
            }
        }

    }

    /**
     * Winkel eines Block auf 33 setzten
     * @param b zubearbeitende Instanz von Klasse Block
     */
    public void illegalerZugriff(Block b){
//        b.winkel = 33;
    }

    /**
     * Ueberpruefen, ob Zeilenummer der zusetzenden Pixel innerhalb des Spielfelds
     * @param z Zeilenummer der zusetzender Pixel
     * @return true wenn innerhalb des Spielfelds
     */
    boolean zeileIstGueltig(int z) {
        return z >= 0 && z < this.spielfeld.length;
    }

    /**
     * Ueberpruefen, ob Spaltennummer der zusetzenden Pixel innerhalb des Spielfelds
     * @param s Spaltenummer der zusetzender Pixel
     * @return true wenn innerhalb des Spielfelds
     */
    boolean spalteIstGueltig(int s) {
        return s >= 0 && s < this.spielfeld[0].length;
    }

    /**
     * Ueberpruefe, ob die gegebene Position auf dem Spielfeld schon besetzt ist.
     * @param zeile Zeilenummer des Spielfelds
     * @param spalte Spaltenummer de Spielfelds
     * @return true, wenn die Position nicht leer / schon besetzt
     */
    // obPixelBestzt()
    boolean pixelWertKollidiertAn(int zeile, int spalte) {
        if (spalteIstGueltig(spalte) && zeileIstGueltig(zeile))
            return spielfeld[zeile][spalte] != ' ';
        else
            return false;
    }


    /**
     * Setze eines Pixelwertes im Spielfeld ändere die Daten des Spielfelds.
     * @param zeile  Zeile der Pixel auf dem Spielfed
     * @param spalte Zeile der Pixel auf dem Spielfeld
     * @param wert zusetzender Wert
     */
    public void setzePixelwertAn(int zeile, int spalte, char wert){
        // Ein 2D Array mit char erstellen, um das Spielfeld zu speichern
        // Setze den Wert auf dieser Pixel auf dem Spielfeld
        if (spalteIstGueltig(spalte) && zeileIstGueltig(zeile))
            spielfeld[zeile][spalte] = wert;
    }


    /**
     * Teste, ob die zugegebene Zeile voll ist.
     * @param zeile zugegebene Zeile
     * @return true, wenn es kein Leerzeichen in der Zeile gibt
     */
    boolean obZeileVoll(int zeile){
        boolean voll = true;
        for(int i = 0; i < spielfeld[zeile].length; i++){
            if(spielfeld[zeile][i]==' '){
                voll = false;
            }
        }
        return voll;
    }

    /**
     * Zeile mit groesseren Zeilenindex umkopieren
     * @param zuloeschendeZeile
     */
    void umkopierenAlleZeilen(int zuloeschendeZeile){
        for(int n = zuloeschendeZeile; n > 0; n--){
            umkopierenEinerZeile(n);
        }
    }

    /**
     * Alle Elmente einer Zeile werden zugewiesen.
     * @param zeile
     * @param zeile
     */
    void umkopierenEinerZeile(int zeile){
        for(int spalte = 0; spalte < spielfeld[zeile].length; spalte++) {
            spielfeld[zeile][spalte] = spielfeld[zeile-1][spalte];
        }
    }

    /**
     * Alle Elemente der Zeile 0 in Spielfeld mit Leerzeichen zuweisen.
     */
    void leerZeichenZuweisen(){
        for(int m = 0; m < spielfeld[0].length; m++){
            // arrary[array.length-1] die letzte Zeile des Arrays
            spielfeld[0][m] = ' ';
        }
    }

    /**
     * Alle volle Zeile loeschen.
     */
    void loeschenVolleZeile(){

        for(int zeile = 0; zeile < spielfeld.length; zeile ++){
            if(obZeileVoll(zeile)){
                umkopierenAlleZeilen(zeile);
                leerZeichenZuweisen();
            }
        }
    }

} // Ende Spielfeld
