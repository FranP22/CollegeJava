package hr.prizmic.projectfp.Class.Phone;

public class Storage {
    private Integer storageInternal;
    private String storageExternal;
    private Integer RAM;

    public Storage(Integer storageInternal, String storageExternal, Integer RAM) {
        this.storageInternal = storageInternal;
        this.storageExternal = storageExternal;
        this.RAM = RAM;
    }

    public Integer getStorageInternal() {
        return storageInternal;
    }

    public void setStorageInternal(Integer storageInternal) {
        this.storageInternal = storageInternal;
    }

    public String getStorageExternal() {
        return storageExternal;
    }

    public void setStorageExternal(String storageExternal) {
        this.storageExternal = storageExternal;
    }

    public Integer getRAM() {
        return RAM;
    }

    public void setRAM(Integer RAM) {
        this.RAM = RAM;
    }
}
