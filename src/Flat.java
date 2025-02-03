import java.util.Objects;

public class Flat extends Premis{
    private int roomCount;

    public Flat(String name, String metroStation, String address,
                int square, int price, float rating, int roomCount) {
        super(name, metroStation, address, square, price, rating);
        this.roomCount = roomCount;
    }
}
