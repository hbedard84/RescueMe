package Models;

public class MetaData {
    private int count;
    private int countReturned;

    //getters and setters

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountReturned() {
        return countReturned;
    }

    public void setCountReturned(int countReturned) {
        this.countReturned = countReturned;
    }

    //toString override
    public String toString(){
        return String.format("Displaying %d / %d pets available", countReturned, count);
    }
}
