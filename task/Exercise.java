package task;

public class Exercise {
    private int id;
    private String name;
    private int quantityhours;
    private String form;
    private int idE;
    private int idgroup;
    private int idlesson;
    private String topic;

    public Exercise(int id, String name, int quantityhours, String form, int idE, int idgroup, int idlesson, String topic) {
        this.id = id;
        this.name = name;
        this.quantityhours = quantityhours;
        this.form = form;
        this.idE = idE;
        this.idgroup = idgroup;
        this.idlesson = idlesson;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityhours() {
        return quantityhours;
    }

    public void setQuantityhours(int quantityhours) {
        this.quantityhours = quantityhours;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public int getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }

    public int getIdlesson() {
        return idlesson;
    }

    public void setIdlesson(int idlesson) {
        this.idlesson = idlesson;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
