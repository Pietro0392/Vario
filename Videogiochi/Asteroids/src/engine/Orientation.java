package engine;

public enum Orientation {
    LEFT(-1),
    RIGHT(1),
    STILL(0);

    private int multiplier;
    private Orientation(int multiplier) {this.multiplier = multiplier;}
    public int getMultiplier() {return multiplier;}
}
