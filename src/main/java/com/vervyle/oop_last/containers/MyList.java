package com.vervyle.lw8_oop.containers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.Iterator;

public interface MyList<T> {

    boolean isEmpty();

    int size();

    void add(T val);

    void add(T val, int where);

    T getLast();

    void remove(T val);

    void remove(int where);

    void remove(MyList<T> toDelete);

    Iterator<T> iterator();

    void print();
}
