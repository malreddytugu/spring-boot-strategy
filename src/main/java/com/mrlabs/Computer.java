package com.mrlabs;
public class Computer {
    
    // Required parameters
    private final String hdd;
    private final String ram;
        
    // Optional parameters
    private final boolean isGraphicsCardEnabled;
    private final boolean isBluetoothEnabled;

    // 1. Private constructor ensures the object can only be built via the Builder
    private Computer(ComputerBuilder builder) {
        this.hdd = builder.hdd;
        this.ram = builder.ram;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    // Getters (No Setters to preserve immutability)
    public String getHdd() { return hdd; }
    public String getRam() { return ram; }
    public boolean isGraphicsCardEnabled() { return isGraphicsCardEnabled; }
    public boolean isBluetoothEnabled() { return isBluetoothEnabled; }

    @Override
    public String toString() {
        return "Computer [HDD=" + hdd + ", RAM=" + ram + 
               ", GraphicsCard=" + isGraphicsCardEnabled + 
               ", Bluetooth=" + isBluetoothEnabled + "]";
    }

    // 2. Static Nested Builder Class
    public static class ComputerBuilder {
        private final String hdd;
        private final String ram;
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        // Constructor for required parameters
        public ComputerBuilder(String hdd, String ram) {
            this.hdd = hdd;
            this.ram = ram;
        }

        // Setter-like methods for optional parameters that return 'this' for chaining
        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        // 3. The final build method to return the orchestrated object
        public Computer build() {
            return new Computer(this);
        }
    }
    
    public static void main(String[] args) {
        // Build a high-end computer with all options
        Computer gamingPc = new Computer.ComputerBuilder("2TB NVMe", "32GB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();

        // Build a basic office computer with only required parameters
        Computer officePc = new Computer.ComputerBuilder("500GB HDD", "8GB")
                .build();

        System.out.println(gamingPc);
        System.out.println(officePc);
    }
}