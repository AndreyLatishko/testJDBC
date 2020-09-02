package task;

public class Group {
    private String idgroup;
    private int quantityhours;

    public Group(String idgroup, int quantityhours) {
        this.idgroup = idgroup;
        this.quantityhours = quantityhours;
    }

    public String getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(String idgroup) {
        this.idgroup = idgroup;
    }

    public int getQuantityhours() {
        return quantityhours;
    }

    public void setQuantityhours(int quantityhours) {
        this.quantityhours = quantityhours;
    }
}
