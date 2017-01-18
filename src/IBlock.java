/**
 * Created by Administrator on 15-Dec-16.
 */
class IBlock extends Block{
    IBlock(Spielfeld sf){
        super(0, 2, 0, sf);
        super.pixels = this._pixels;
    }

    char [][] _pixels = {
                        {'I', 'I', 'I', 'I'},
                        };

    public String toString(){

        return "Hier ist IBlock";
    }
}
