package Models;

public class AnimalAdoptionInfo {
    private String ageGroup, breedString, name, pictureThumbnailUrl, sex, sizeGroup;
    
    //constructor
    public AnimalAdoptionInfo(String ageGroup, String breedString, String name, String pictureThumbnailUrl, String sex, String sizeGroup) {
        setAgeGroup(ageGroup);
        setBreedString(breedString);
        setName(name);
        setPictureThumbnailUrl(pictureThumbnailUrl);
        setSex(sex);
        setSizeGroup(sizeGroup);
    }

    //getters and setters

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

    public String getSizeGroup() {
        return sizeGroup;
    }

    public void setSizeGroup(String sizeGroup) {
        this.sizeGroup = sizeGroup;
    }

    public String toString(){
        return String.format("%s - %s, %s %s", name, ageGroup, sex, breedString);
    }
}
