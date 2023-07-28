package hr.prizmic.projectfp.Class.Phone;

import java.math.BigDecimal;

public class Tablet extends MobileDevice{

    //private final DeviceType type = DeviceType.TABLET;
    /*public DeviceType getType() {
        return type;
    }*/
    public Tablet(String manufacturer, String model, BigDecimal price, Characteristics characteristics) {
        super(manufacturer, model, price, characteristics);
        super.setType(DeviceType.TABLET);
    }
}
