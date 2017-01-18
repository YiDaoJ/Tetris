/**
 * Created by Administrator on 15-Dec-16.
 */
class ZBlock extends Block {
    ZBlock(Spielfeld sf){
        super(0, 3, 0, sf);
        super.pixels = this._pixels;

    }

    char [][] _pixels = {
                        {'Z','Z', ' '},
                        {' ','Z', 'Z'},
                        };

    public void testeRedefinieren() {
        System.out.println("Die ist eine Methode, die inc Block nicht existiert.");
    }
}
