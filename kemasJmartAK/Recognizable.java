package kemasJmartAK;


/**
 * Abstract class Recognizable - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Recognizable
{
    public final int id;

    protected Recognizable(int id) {
        this.id = id;
    }

    public boolean equals(Object object) {
        return (object instanceof Recognizable) && ((Recognizable) object).id == id;
    }

    public boolean equals(Recognizable object) {
        return object.id == id;
    }
}
