import java.util.Objects;

public class CommercialPremises extends Premis{
    private String roomType;

    public CommercialPremises(String name, String metroStation, String address,
                              int square, int price, float rating, String roomType) {
        super(name, metroStation, address, square, price, rating);
        this.roomType = roomType;
    }
}
