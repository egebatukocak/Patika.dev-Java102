package model.insurances;

public class HealthInsurance extends Insurance {
    public HealthInsurance() {
        setName("Health Insurance");
        setPrice(36000);
    }

    @Override
    public double calculate() {
        return getPrice();
    }
}
