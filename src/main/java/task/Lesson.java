package task;

public class Lesson {
    private String name;
    private int minquantityhours;

    public Lesson(String name, int minquantityhours) {
        this.name = name;
        this.minquantityhours = minquantityhours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinquantityhours() {
        return minquantityhours;
    }

    public void setMinquantityhours(int minquantityhours) {
        this.minquantityhours = minquantityhours;
    }
}
