package hr.prizmic.projectfp.Class.Phone;

public class Characteristics{
    private Integer battery;
    private String OS;
    private String CPU;
    private String GPU;
    private Storage storage;

    public Characteristics(Integer battery, String OS, String CPU, String GPU, Storage storage) {
        this.battery = battery;
        this.OS = OS;
        this.CPU = CPU;
        this.GPU = GPU;
        this.storage = storage;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
