package Models;

public class Data {
    private String id;
    private Attributes attributes;

    //setters and getters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!id.isBlank())
            this.id = id;
        else throw new IllegalArgumentException("Id cannot be blank.");
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        if (attributes != null)
            this.attributes = attributes;
        else throw new IllegalArgumentException("Attributes cannot be null.");
    }

    //toString
    public String toString(){
        return String.format("%s - %s, %s %s", attributes.getName(), attributes.getAgeGroup(), attributes.getSex(), attributes.getBreedString());
    }
}
