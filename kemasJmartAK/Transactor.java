package kemasJmartAK;


/**
 * Write a description of interface Transactor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Transactor
{
    public abstract boolean validate();
    public abstract Invoice perform();
}   
