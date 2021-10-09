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

    protected Recognizable(int id) {
        this.id = id;
    }

    public static <T> int setClosingId(Class <Recognizable> clazz, int id){
        if(Recognizable.class.isAssignableFrom(clazz)){
            return 0;
        }
        else{   
            return 1;
        }
    }

    public static <T> int getClosingId(Class <Recognizable> clazz){
        if(Recognizable.class.isAssignableFrom(clazz)){
            return 0;
        }
        else{   
            return 1;
        }
    }

    public boolean equals(Object object) {
        return (object instanceof Recognizable) && ((Recognizable) object).id == id;
    }

    public boolean equals(Recognizable object) {
        return object.id == id;
    }

    @Override
    public int compareTo(Recognizable other) {
        if (id == other.id){
            return 1;
        }
        else{
            return 0;
        }
    }
}
