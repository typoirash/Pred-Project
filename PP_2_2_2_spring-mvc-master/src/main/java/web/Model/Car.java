package web.Model;


public class Car {
    private String brand;
    private String model;
    private int series;

    public Car() {
    }

    public Car(String brand, String model, int series) {
        this.brand = brand;
        this.model = model;
        this.series = series;
    }

    public String getbrand() {
        return brand;
    }

    public void setbrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
