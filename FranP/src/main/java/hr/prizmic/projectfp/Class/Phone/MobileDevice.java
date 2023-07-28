package hr.prizmic.projectfp.Class.Phone;


import java.math.BigDecimal;

public abstract class MobileDevice implements TypeFunc{
    private String manufacturer;
    private String model;
    private BigDecimal price;

    private Characteristics characteristics;
    private DeviceType type;

    @Override
    public void setType(DeviceType t){
        type=t;
    }
    @Override
    public DeviceType getType(){
        return type;
    }

    /*@Override
    public DeviceType getType(){
        if()
    }*/

    public MobileDevice(String manufacturer, String model, BigDecimal price, Characteristics characteristics) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.characteristics = characteristics;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }
}
