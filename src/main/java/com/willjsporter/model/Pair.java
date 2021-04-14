package com.willjsporter.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.willjsporter.controller.PairDeserializer;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonDeserialize(using = PairDeserializer.class)
public class Pair {

    @Id
    @SequenceGenerator(name = "pair_sequence", sequenceName = "pair_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pair_sequence")
    private long id;
    private int x;
    private int y;

    public static Pair of (int x, int y) {
        return new Pair(x, y);
    }

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Pair{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair that = (Pair) o;
        return getX() == that.getX() &&
            getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }


}
