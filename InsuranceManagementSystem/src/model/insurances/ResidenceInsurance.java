package model.insurances;

public class ResidenceInsurance extends Insurance {
    public ResidenceInsurance() {
        setName("Residence Insurance");
        setPrice(60000);
    }

    @Override
    public double calculate() {
        return getPrice();
    }
}
