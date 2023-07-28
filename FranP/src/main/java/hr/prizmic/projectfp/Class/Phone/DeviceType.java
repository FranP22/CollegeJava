package hr.prizmic.projectfp.Class.Phone;

public enum DeviceType {
    TABLET("Tablet"),SMART_PHONE("Phone");
    private String type;

    DeviceType(String type) {
        this.type = type;
    }

    /*public String getStr(DeviceType t){
        switch(t){
            case TABLET: return "Tablet";
            case SMART_PHONE: return "Phone";
        }
        return null;
    }*/
}
