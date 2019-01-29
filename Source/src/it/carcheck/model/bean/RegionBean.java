package it.carchek.model.bean;

public class Region {
    public Region(short id,String name)
    {
        this.id=id;
        this.name=name;
    }
    private short id;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
