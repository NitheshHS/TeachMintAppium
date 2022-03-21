package enums;

public enum Langauges {

    ENGLISH("english"),
    HINDI("hindi");

    String label;
    Langauges(String label){
        this.label=label;
    }

    @Override
    public String toString() {
        return label;
    }
}
