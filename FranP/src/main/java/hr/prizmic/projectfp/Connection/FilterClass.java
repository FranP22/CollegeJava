package hr.prizmic.projectfp.Connection;

public class FilterClass {
    private String model;
    private String manufacturer;
    private String price;
    private String battery;
    private String os;
    private String cpu;
    private String gpu;
    private String in;
    private String ex;
    private String ram;
    private String user;

    public FilterClass(FilterBuilder f) {
        this.model = f.model;
        this.manufacturer = f.manufacturer;
        this.price = f.price;
        this.battery = f.battery;
        this.os = f.os;
        this.cpu = f.cpu;
        this.gpu = f.gpu;
        this.in = f.in;
        this.ex = f.ex;
        this.ram = f.ram;
        this.user = f.user;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public static class FilterBuilder{
        private String model;
        private String manufacturer;
        private String price;
        private String battery;
        private String os;
        private String cpu;
        private String gpu;
        private String in;
        private String ex;
        private String ram;
        private String user;

        public FilterBuilder(String model, String manufacturer, String price, String battery, String os, String cpu, String gpu, String in, String ex, String ram, String user) {
            this.model = model;
            this.manufacturer = manufacturer;
            this.price = price;
            this.battery = battery;
            this.os = os;
            this.cpu = cpu;
            this.gpu = gpu;
            this.in = in;
            this.ex = ex;
            this.ram = ram;
            this.user = user;
        }
        public FilterBuilder(){}

        public void setModelBuild(String model) {
            this.model = model;
        }

        public void setManufacturerBuild(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public void setPriceBuild(String price) {
            this.price = price;
        }

        public void setBatteryBuild(String battery) {
            this.battery = battery;
        }

        public void setOsBuild(String os) {
            this.os = os;
        }

        public void setCpuBuild(String cpu) {
            this.cpu = cpu;
        }

        public void setGpuBuild(String gpu) {
            this.gpu = gpu;
        }

        public void setInBuild(String in) {
            this.in = in;
        }

        public void setExBuild(String ex) {
            this.ex = ex;
        }

        public void setRamBuild(String ram) {
            this.ram = ram;
        }

        public void setUserBuild(String user) {
            this.user = user;
        }

        public FilterClass build(){
            return new FilterClass(this);
        }
    }
}
