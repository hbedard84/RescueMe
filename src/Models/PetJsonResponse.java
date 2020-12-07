package Models;

public class PetJsonResponse {
    private HighLevelInfo[] meta;
    private HighLevelInfo[] data;

    //getters and setters

    public HighLevelInfo[] getMeta() {
        return meta;
    }

    public void setMeta(HighLevelInfo[] meta) {
        this.meta = meta;
    }

    public HighLevelInfo[] getData() {
        return data;
    }

    public void setData(HighLevelInfo[] data) {
        this.data = data;
    }



}
