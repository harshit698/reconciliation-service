public class DataType {

    private final String name;

    public DataType(String name) {
        this.name = name;
    }

    /**
     * Uses default equals of name field which is astring
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return name.equals(o);
    }

    /**
     * Uses default hashCode of name field which is astring
     * @return
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}