package model;

public class Toy {
    private Integer id;
    private String name;
    private Integer count;
    private Integer dropFreq;

    public Toy(Integer id, String name, Integer count, Integer dropFreq) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.dropFreq = dropFreq;
    }

    public Integer getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    public Integer getCount() {
        return this.count;
    }

    public Integer getFreq() {
        return this.dropFreq;
    }

    public boolean takeToy() {
        this.count--;
        return this.count == 0;
    }
    
}
