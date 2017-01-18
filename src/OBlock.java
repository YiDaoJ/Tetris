/**
 * Created by Administrator on 15-Dec-16.
 */
class OBlock extends  Block{

    OBlock(Spielfeld sf){

        super(0, 3, 0, sf);

        super.pixels = this._pixels;

    }

    char [][] _pixels = {
            {'O','O'},
            {'O','O'},
    };

//    boolean kollisionImSpielfeld(){
//        return false;
//    }



}
