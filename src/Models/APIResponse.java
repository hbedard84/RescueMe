package Models;

public class APIResponse {
    private MetaData meta;
    private Data data[];

    //getters and setters

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        if(meta != null)
            this.meta = meta;
        else throw new IllegalArgumentException("Meta cannot be null");
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        if (data.length > 0)
            this.data = data;
        else throw new IllegalArgumentException("Data cannot be an empty array.");
    }
}
