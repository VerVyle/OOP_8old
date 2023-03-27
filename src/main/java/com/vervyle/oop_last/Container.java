package com.vervyle.oop_last;

import com.vervyle.oop_last.containers.MyLinkedList;
import com.vervyle.oop_last.containers.MyList;
import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.Point2D;

import java.util.Iterator;

public class Container {

    private final MyList<Element> allElements;
    private MyList<Element> selectedElements;

    public Container() {
        allElements = new MyLinkedList<>();
        selectedElements = new MyLinkedList<>();
    }

    public void addElement(Element element) {
        allElements.add(element);
    }

    public void removeElement(Element element) {
        element.hide();
        allElements.remove(element);
    }

    private void selectElement(Element element) {
        element.select();
        selectedElements.add(element);
    }

    private void deselectElement(Element element) {
        element.deselect();
        selectedElements.remove(element);
    }

    public void removeAllElements() {
        allElements.iterator().forEachRemaining(this::removeElement);
    }

    public void deselectAllElements() {
        selectedElements.iterator().forEachRemaining(this::deselectElement);
        if (!selectedElements.isEmpty()) {
            throw new RuntimeException("Fatal error");
        }
    }

    public void selectLastElement(Point2D point2D) {
        Iterator<Element> iterator = ((MyLinkedList<Element>) allElements).descendingIterator();
        Element element;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (element.intersects(point2D)) {
                selectElement(element);
                return;
            }
        }
    }

    public void selectAllElements() {
        allElements.iterator().forEachRemaining(this::selectElement);
    }

    public void selectAllElements(Point2D point2D) {
        allElements.iterator().forEachRemaining(element -> {
            if (element.intersects(point2D))
                selectElement(element);
        });
    }
}
