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
        this.ageGroup = ageGroup;
    }

    public String getBreedString() {
        return breedString;
    }

    public void setBreedString(String breedString) {
        this.breedString = breedString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureThumbnailUrl() {
        return pictureThumbnailUrl;
    }

    public void setPictureThumbnailUrl(String pictureThumbnailUrl) {
        this.pictureThumbnailUrl = pictureThumbnailUrl;
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
        this.url = url;
    }

    public String getSizeGroup() {
        return sizeGroup;
    }

    public void setSizeGroup(String sizeGroup) {
        this.sizeGroup = sizeGroup;
    }

    //toString Override
    public String toString(){
        return String.format("%s - %s, %s %s", name, ageGroup, sex, breedString);
    }
}
