package Models;

public class MetaData {
    private int count;  //This is the total number of animals available
    private int countReturned;   //This is the number of animals returned in the search (limit)

    //getters and setters

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count >= 0)
            this.count = count;
        else throw new IllegalArgumentException("Count cannot be less than 0.");
    }

    public int getCountReturned() {
        return countReturned;
    }

    public void setCountReturned(int countReturned) {
        if(countReturned >=0)
            this.countReturned = countReturned;
        else throw new IllegalArgumentException("Count returned cannot be less than 0.");
    }

    //toString override - this is the count displayed above the listview in the Animal Adoption View
    public String toString(){
        return String.format("Displaying %d / %d pets available", countReturned, count);
    }
}
