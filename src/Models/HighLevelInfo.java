package Models;

public class HighLevelInfo {
    private int count;
    private String id;
    private AnimalAdoptionInfo[] attributes;
    private AnimalAdoptionInfo[] relationships;

    //constructor
    public HighLevelInfo(int count, String id, AnimalAdoptionInfo[] attributes, AnimalAdoptionInfo[] relationships) {
        setCount(count);
        setId(id);
        setAttributes(attributes);
        setRelationships(relationships);
    }

    //getters and setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AnimalAdoptionInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AnimalAdoptionInfo[] attributes) {
        this.attributes = attributes;
    }

    public AnimalAdoptionInfo[] getRelationships() {
        return relationships;
    }

    public void setRelationships(AnimalAdoptionInfo[] relationships) {
        this.relationships = relationships;
    }

    public String toString(){
        return String.format("%d pets, including id#%s", count, id);
    }

}
