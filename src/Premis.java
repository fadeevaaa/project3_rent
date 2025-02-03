public abstract class   Premis {
    private String name;
    private String metroStation;
    private String address;
    private int square;
    private int price;
    private float rating;

    public Premis(String name, String metroStation, String address, int square, int price, float rating) {
        this.name = name;
        this.metroStation = metroStation;
        this.address = address;
        this.square = square;
        this.price = price;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Premis{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", avgValuation=" + rating +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public float getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
