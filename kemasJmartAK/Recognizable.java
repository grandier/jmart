package kemasJmartAK;


/**
 * Abstract class Recognizable - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */

public class Recognizable implements Comparable<Recognizable>
{
    public final int id;

    protected Recognizable() {
        this.id = 1;
    }

    public static <T> int setClosingId(Class<T> clazz){
        if(clazz.isAssignableFrom(Recognizable.class)){
            return 0;
        } else{
            return 1;
        }
    }

    public static <T> int getClosing(Class<T> clazz, int id){
        if(clazz.isAssignableFrom(Recognizable.class)){
            return 0;
        }else{
            return 1;
        }
    }

    public boolean equals(Object other) {
        return (other instanceof Recognizable) && ((Recognizable) other).id == id;
    }

    public boolean equals(Recognizable other) {
        return other.id == id;
    }

    @Override
    public int compareTo(Recognizable other) {
        return Integer.compare(this.id, other.id);
    }
}
