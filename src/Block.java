class Block {
	/**
	 * Definition der Eigenschaften eines Blocks als Instanz-Variable (IV)
	 */
	// Die Koordinaten der Position
	protected int pos_x;
	protected int pos_y;
	
	// Wert der Rotation
	protected int rotate;
	
	// "Farbe" jedes Pixels mit einem einzelnen Buchstaben
	protected char farbe = 'T';
	
	// Pixels im Block als 2D-Array von char
//	protected char [][] pixels = {
//								{'a','b','c'},
//								{'d','e','f'},
//								{'g','h','i'} };

	protected char [][] pixels = {
			{'F','F','F','F'},
			{'F', ' ', ' ', ' '},
			{'F','F',' ', ' '}
	};
	
	// Spielfeld als IV
	protected Spielfeld sf;

	protected boolean beweglich = true;

	/**
	 * Konstruktor
	 * @param _x Spalten-Koordinate
	 * @param _y Zeilen-Koordinate
	 * @param _winkel Orientierung des Blockes
	 * @param _feld Spielfeld, zu dem der Block gehÃ¶rt
	 */
	Block(int _x, int _y, int _winkel, Spielfeld _feld) {

		// Aufgabe 3d) Spielfeld bekannt machen, da es nun für die
		// Positionsüberprüfung benötigt wird!
		this.sf = _feld;

		// Koordinaten nur setzen, wenn sie gültig sind
		if (positionIstGueltig(_x, _y)) {
			this.pos_x = _x;
			this.pos_y = _y;
		}
		// sonst Default-Werte initialisieren
		else {
			this.pos_x = 0;
			this.pos_y = 0;
		}

		// Winkel nur setzen, wenn er gültig ist
		if (rotationIstGueltig(_winkel)) {
			this.rotate = _winkel;
		}
		// sonst Default-Wert initialisieren
		else {
			this.rotate = 0;
		}
	}

	/**
	 * Ueberpruefen, ob die eingegebene kooridinaten-Wert gueltig ist.
	 * @param s Wert auf der horizontalen Achse / Spalte
	 * @param z Wert auf der vertikalen Achse	/ Zeile
	 * @return true, wenn die Werte positive ganze Zahl sind.
	 */
	boolean positionIstGueltig(int z, int s){
		boolean gueltigePosition = false;
		if(this.sf.zeileIstGueltig(z) && this.sf.zeileIstGueltig(z + this.pixels.length - 1) &&	// oberste und unterste Zeile
			this.sf.spalteIstGueltig(s) && this.sf.spalteIstGueltig(s + this.pixels[0].length - 1))	// linke und rechte Spalte
			gueltigePosition = true;

		// Umdrehen wird auch beruechsichtigt, besonders wenn der Block nicht quadradisch ist.
		if(this.rotate == 90 || this.rotate == 270){
			if(this.sf.zeileIstGueltig(z) && this.sf.zeileIstGueltig(z + this.pixels[0].length - 1) &&
				this.sf.spalteIstGueltig(s) && this.sf.spalteIstGueltig(s + this.pixels.length - 1))
				gueltigePosition = true;
			else
				gueltigePosition = false;
		}
		return gueltigePosition;

	}
	
	/**
	 * Ueberpruefen, ob der eingegebene Winkel gueltig ist.
	 * @param r winkel umzudrehender Winkel
	 * @return  true, wenn der Werte 0, 90, 180 oder 270 ist.
	 */
	boolean rotationIstGueltig(int r){
		return (r == 0 || r == 90 || r == 180 || r == 270);
	}
	
	/**
	 * Einen Block positionieren.
	 * @param x Wert auf der horizontalen Achse
	 * @param y Wert auf der vertikalen Achse
	 */
	void positioniereAuf(int x, int y){
		if(positionIstGueltig(x, y)){
			this.pos_x = x;
			this.pos_y = y;
		}else{
			System.out.println("Achtung: Eingabe der Position nicht gueltig.");
		}
	}
	
	/**
	* Den Block umdrehen.
	* @param winkel umzudrehender Winkel
	*/
	void dreheAufWinkel(int winkel){
		if(rotationIstGueltig(winkel)){
			this.rotate = winkel;
		}
	}

	/**
	 * Ein Pixelwert ist uebertragbar, wenn er kein Leerzeichen ist.
	 * @param pixelwert Zeichen des Pixels
	 * @return true, wenn kein Leerzeichen
     */
	boolean pixelwertIstUebertragbar(char pixelwert){
		return pixelwert !=' ';
	}

	/**
	* Infomationen des Blocks ausgeben.
	* @return Die Information des aktuelles Block
	*/
	public String toString(){
		String text = "";

		text = 	"Position x: " + this.pos_x + ", y: " + this.pos_y + "\n"
				+	"Rotation um Winkel: " + this.rotate + "\n";

		// ueber alle Zeilen des Arrays iterieren
		for (int zeile = 0; zeile < this.pixels.length; zeile++) {

			// ueber alle Spalten des Arrays iterieren, die sich in _dieser_
			// Zeile befinden
			for (int spalte = 0; spalte < this.pixels[zeile].length; spalte++)

				// Jedex Pixel im Feld zeilen- und spaltenweise an den Text anh盲ngen
				text += this.pixels[zeile][spalte] + " ";

			// Zeilenumbruch
			text += "\n";
		}
		return text;
	}
	
	 /**
	 * Der Block soll das Spielfeld kennen und dem Spielfeld zuordnen.
	 * @param s zuzuordene Spielfeld - Instanz
	 */
	public void ordneSpielfeldZu(Spielfeld s){
	    this.sf = s;
	}

	/**
	 * Uebertragen die Werte aller Pixel eines Blocks in die Pixel eines Spielfelds.
     */
	public void uebertrageInsSpielfeld(){

		for(int zeile = 0; zeile < this.pixels.length; zeile++){
			for(int spalte = 0; spalte < this.pixels[zeile].length; spalte++){
				// jede Pixel des Spielfeld zuweisen
				if( pixelwertIstUebertragbar(this.pixels[zeile][spalte]))
					uebertragePixelwertInsSpielfeld(zeile, spalte, this.rotate, this.pixels[zeile][spalte]);
			}
		}
	}

	/**
	 * Einzelne Pixel eines Blocks wird nach dem Winkel ins Spielfeld uebertragen.
	 * @param zeile	Zeile der Pixel im Block
	 * @param spalte Spalte der Pixel im Block
	 * @param winkel Winkel der Rotation des Blocks
	 * @param pixelWert zu uebertragendes Zeichen des Blocks
     */
	public void uebertragePixelwertInsSpielfeld(int zeile, int spalte, int winkel, char pixelWert){
		// die Anzahl von Zeilen und Spalten des originales Blocks
		int _zeile = this.pixels.length-1;	// 2
		int _spalte = this.pixels[0].length-1;	// 3

		int x = 0;
		int y = 0;

		if(winkel == 0){
			x = zeile;
			y = spalte;
		} else if(winkel == 90) {
			x = spalte;
			y = _zeile - zeile;
		}else if(winkel == 180) {
			x = _zeile - zeile;
			y = _spalte - spalte;

		}else if(winkel == 270) {
			x = _spalte - spalte;
			y = zeile;
		}

		this.sf.setzePixelwertAn(x + this.pos_x, y + this.pos_y, pixelWert);
	}

	/**
	 * 1. b
	 * Ueberpruefe, ob ein Zeichen des Blocks kollidiert.
	 * Falls doch, dann kollidiert der ganze Block.
	 * @return	true, wenn der Block kollidiert.
	 */
	boolean kollisionImSpielfeld() {
		boolean kollisionTrittauf = false;
		// Falls fuer irgend ein Zeichen des Block eine Kollision auftritt, ist die Kollision des ganzen Block
		for(int zeile = 0; zeile < this.pixels.length; zeile++) {
			for( int spalte = 0; spalte < this.pixels[zeile].length; spalte++){
				if(pixelWertKollidiertImSpielfeld(zeile, spalte)){
					kollisionTrittauf = true;
					return kollisionTrittauf;
				}
			}
		}
		return kollisionTrittauf;
	}

	/**
	 * Ueberpruefe, ob einzelnes Zeichen eines Blocks kollidiert.
	 * @param zeile Zeilenummer des Zeichens im Block
	 * @param spalte Spaltenummer des Zeichens im Block
     * @return true, wenn das Zeichen im Spielfeld kollidiert.
     */
	boolean pixelWertKollidiertImSpielfeld(int zeile, int spalte){
		int _zeile = this.pixels.length-1;
		int _spalte = this.pixels[0].length-1;
		// Boolean-Variable, um den Status von Kollision des Zeichens zu speichern
		boolean obBesetzt = true;

		// kein Leerzeichen
		if(pixelwertIstUebertragbar(this.pixels[zeile][spalte])){
			// Wenn die Position diese Zeichens auf dem Spielfeld leer ist, keine Kollision, return false;
			// Ansonsten return true
			// Umdrehung beruecksichtigen
			switch (this.rotate){
				case 0:
					obBesetzt = this.sf.pixelWertKollidiertAn(zeile + this.pos_x, spalte + this.pos_y);
					break;
				case 90:
					obBesetzt = this.sf.pixelWertKollidiertAn(spalte + this.pos_x, _zeile - zeile + this.pos_y);
					break;
				case 180:
					obBesetzt = this.sf.pixelWertKollidiertAn(_zeile - zeile + this.pos_x, _spalte -spalte + this.pos_y);
					break;
				case 270:
					obBesetzt = this.sf.pixelWertKollidiertAn(_spalte-spalte + this.pos_x, zeile + this.pos_y);
					break;
			}

			return obBesetzt;
		}else{
			// das Zeichen ist Leerzeichen, auf jeden Fall kein Kollision
			return false;
		}
	}


	/**
	 * 1.d
	 * Wenn der Bock auf eine neue Postion beweget wird, soll er an der alten Stelle im Spielfeld geloescht.
	 * D.h, an den alten Stellen werden Leerzeichen uebertragen.
	 */
	public void uebertrageLeerzeichenInsSpielfeld() {
		for(int zeile = 0; zeile < this.pixels.length; zeile++){
			for(int spalte = 0; spalte < this.pixels[zeile].length; spalte++){
				// jede Pixel des Spielfeld zuweisen
				if( pixelwertIstUebertragbar(this.pixels[zeile][spalte]))
				uebertragePixelwertInsSpielfeld(zeile, spalte, this.rotate, ' ');
			}
		}
	}

	/*
		Alle vier Aktionen werden nach dem Schema durchgeführt.
		1. Block aus dem Spielfeld löschen
		2. Block auf neue Wert für Position, bzw. neuen Winkel setzten
		3. Kollision im Spielfeld testen
		4. Falls es eine Kollision gegeben hat, wieder die vorherigen Werte für Postion & Winkel setzen
		5. Block ins Spielfeld übertragen
	 */

	/**
	 * Der Block wird um 1 nach links verschoben.
	 */
	public void nachLinksVerschieben(){
		// alte Position des Block aus dem Spielfeld löschen
		uebertrageLeerzeichenInsSpielfeld();

		// Block auf neue Wert für Position setzten: Spalte am Spielfeld - 1
		this.pos_y --;

		// Falls eine Kollision auftritt oder die neue Position nicht gueltig ist, wieder die vorherigen Werte für Postion & Winkel setzen
		if(kollisionImSpielfeld() || !positionIstGueltig(this.pos_x, this.pos_y)){
			this.pos_y ++;
//			this.beweglich = false;
		}

		// Block ins Spielfeld übertragen
		uebertrageInsSpielfeld();
	}

	/**
	 * Der Block wird um 1 nach rechts verschoben.
	 */
	public void nachRechtsVerschieben(){
		uebertrageLeerzeichenInsSpielfeld();

		// Block auf neue Wert für Position setzten: Spalte am Spielfeld + 1
		this.pos_y ++;

		if(kollisionImSpielfeld() || !positionIstGueltig(this.pos_x, this.pos_y)){
			this.pos_y --;
//			this.beweglich = false;
		}

		uebertrageInsSpielfeld();
	}

	/**
	 * Der Block wird um 1 nach unten verschoben.
	 */
	public void nachUntenVerschieben(){
		uebertrageLeerzeichenInsSpielfeld();

		// Block auf neue Wert für Position setzten: Zeile am Spielfeld + 1
		this.pos_x ++;

		if(kollisionImSpielfeld() || !positionIstGueltig(this.pos_x, this.pos_y)){
			this.pos_x --;
			this.beweglich = false;
		}

		uebertrageInsSpielfeld();
	}

	/**
	 * Der Block wird um 90 Grad umgedreht.
	 */
	public void um90Drehen(){
		uebertrageLeerzeichenInsSpielfeld();

		this.rotate = this.rotate + 90;
		// Falls der Winkel 360 Grad erreicht, 360 = 0
		if(this.rotate == 360)
			this.rotate = 0;

		if(kollisionImSpielfeld() || !positionIstGueltig(this.pos_x, this.pos_y)){
			this.rotate = this.rotate - 90;
			if(this.rotate == -90)
				this.rotate = 270;
			this.beweglich = false;
		}
		uebertrageInsSpielfeld();
	}

	/**
	 * @return true wenn der Block weiter bewegen kann.
     */
	boolean obWeiterBewegen(){
		return this.beweglich;
	}

}		// Ende Klasse Block
