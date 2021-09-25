package kemasJmartAK;

/**
 * Write a description of class Recognizable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Recognizable
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
