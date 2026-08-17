public class Engine {
    private String type;
    private int horsePower;

    public Engine(String type, int horsePower) {
            this.type = type;
            this.horsePower = horsePower;
    }

    public String getType() { return type; }
    public int getHorsePower() { return horsePower; }
}