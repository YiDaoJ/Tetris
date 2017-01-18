/**
 * Created by Administrator on 15-Dec-16.
 */
class TBlock extends Block{
    TBlock(Spielfeld sf){
        super(0, 3, 0, sf);
        super.pixels = this._pixels;
    }

    char [][] _pixels = {
                        {'T','T', 'T'},
                        {' ','T', ' '},
                        };


    public String toString(){

        return "Hier ist TBlock";
    }

}
