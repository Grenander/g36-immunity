package se.lexicon.immunity.model;

import java.util.Objects;

public class Hobby {
    private String id;
    private String value;

    public Hobby(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public Hobby() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return Objects.equals(getId(), hobby.getId()) && Objects.equals(getValue(), hobby.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue());
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
