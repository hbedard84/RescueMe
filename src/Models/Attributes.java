package Models;

public class Attributes {
    private String ageGroup;
    private String breedString;
    private String name;
    private String pictureThumbnailUrl;
    private String sex;
    private String url;
    private String sizeGroup;
    
    //constructor
    public Attributes(String ageGroup, String breedString, String name, String pictureThumbnailUrl, String sex, String url, String sizeGroup) {
        setAgeGroup(ageGroup);
        setBreedString(breedString);
        setName(name);
        setPictureThumbnailUrl(pictureThumbnailUrl);
        setSex(sex);
        setUrl(url);
        setSizeGroup(sizeGroup);
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        if (ageGroup.length() < 50)
            this.ageGroup = ageGroup;
        else throw new IllegalArgumentException("Age group must be less than 50 characters.");
    }

    public String getBreedString() {
        return breedString;
    }

    public void setBreedString(String breedString) {
        if (breedString.length() < 200)
            this.breedString = breedString;
        else throw new IllegalArgumentException("Breed must be less than 200 characters.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 200)
            this.name = name.toUpperCase();
        else throw new IllegalArgumentException("Name must be less than 200 characters.");
    }

    public String getPictureThumbnailUrl() {
        return pictureThumbnailUrl;
    }

    public void setPictureThumbnailUrl(String pictureThumbnailUrl) {
        if (pictureThumbnailUrl.toLowerCase().contains("http") || pictureThumbnailUrl.toLowerCase().contains("www"))
            this.pictureThumbnailUrl = pictureThumbnailUrl;
        else throw new IllegalArgumentException("Picture Thumbnail URL must be a url containing http or www.");
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url.toLowerCase().contains("http") || url.toLowerCase().contains("www"))
            this.url = url;
        else throw new IllegalArgumentException("URL must be a url containing http or www.");
    }

    public String getSizeGroup() {
        return sizeGroup;
    }

    public void setSizeGroup(String sizeGroup) {
        if (!sizeGroup.isBlank())
            this.sizeGroup = sizeGroup;
        else throw new IllegalArgumentException("Size group cannot be blank");
    }

    //toString Override - this is the output on the listview
    public String toString(){
        return String.format("%s - %s, %s %s", name, ageGroup, sex, breedString);
    }
}
