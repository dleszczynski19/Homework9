package pl.moderntester.models.basic;

public class Mountain {
    private int rank;
    private String peak;
    private String range;
    private String state;
    private int height;

    public Mountain(int rank, String peak, String range, String state, int height) {
        this.rank = rank;
        this.peak = peak;
        this.range = range;
        this.state = state;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Rank: " + rank + "\nPeak: " + peak + "\nMountain range: " + range + "\n--------------------";
    }
}
