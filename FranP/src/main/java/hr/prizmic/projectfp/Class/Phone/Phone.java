package hr.prizmic.projectfp.Class.Phone;

import java.math.BigDecimal;

public class Phone extends MobileDevice{

    //private final DeviceType type = DeviceType.SMART_PHONE;

    /*public DeviceType getType() {
        return type;
    }*/

    public Phone(String manufacturer, String model, BigDecimal price, Characteristics characteristics) {
        super(manufacturer, model, price, characteristics);
        super.setType(DeviceType.SMART_PHONE);
    }
}
