
/**
 *
 * @author Jorge & Jacob
 */
class Clock {

    private int id = 0;
    private int fresh = 0;
    private Clock nextItem;

    public Clock() {
    }

    public Clock(int setID, Clock setNextItem) {
        id = setID;
        nextItem = setNextItem;
    }

    public Clock getNext() {
        return nextItem;
    }

    public void setNext(Clock setNextItem) {
        nextItem = setNextItem;
    }

    public int getID() {
        return id;
    }

    public int getRef() {
        return fresh;
    }

    public void setRef() {
        fresh = 1;
    }

    public void deRef() {
        fresh = 0;
    }
}
