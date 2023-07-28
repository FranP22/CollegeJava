package hr.prizmic.projectfp.Class;

import hr.prizmic.projectfp.Class.Phone.DeviceType;
import hr.prizmic.projectfp.Class.Phone.MobileDevice;
import hr.prizmic.projectfp.Class.User.UserClass;

import java.time.LocalDateTime;

public class Item {
    private Long ID;
    private MobileDevice device;
    private DeviceType type;
    private UserClass user;
    private LocalDateTime timeAdded;

    public Item(Long id, MobileDevice device, DeviceType type, UserClass user, LocalDateTime timeAdded) {
        ID = id;
        this.device = device;
        this.type = type;
        this.user = user;
        this.timeAdded = timeAdded;
    }

    public MobileDevice getDevice() {
        return device;
    }

    public void setDevice(MobileDevice device) {
        this.device = device;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public UserClass getUser() {
        return user;
    }

    public void setUser(UserClass user) {
        this.user = user;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
