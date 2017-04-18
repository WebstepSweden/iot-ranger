package se.webstep.iotr.dt.model;

public class Type {

    private String id;

    private String name;

    private String icon;

    private String chart_type;

    private String primary;


    public Type() {
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getChart_type() {
        return chart_type;
    }


    public void setChart_type(String chart_type) {
        this.chart_type = chart_type;
    }


    public String getPrimary() {
        return primary;
    }


    public void setPrimary(String primary) {
        this.primary = primary;
    }


    @Override
    public String toString() {
        return "Type{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", chart_type='" + chart_type + '\'' +
                ", primary='" + primary + '\'' +
                '}';
    }
}
