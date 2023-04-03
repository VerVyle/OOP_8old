package com.vervyle.oop_last;

import com.vervyle.oop_last.containers.MyLinkedList;
import com.vervyle.oop_last.containers.MyList;
import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.Point2D;
import javafx.scene.paint.Color;

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
        if (element.isSelected())
            return;
        element.select();
        selectedElements.add(element);
    }

    private void deselectElement(Element element) {
        element.deselect();
        selectedElements.remove(element);
    }

    private void deleteElement(Element element) {
        selectedElements.remove(element);
        allElements.remove(element);
        element.hide();
    }

    public void removeAllElements() {
        Iterator<Element> iterator = allElements.iterator();
        while (iterator.hasNext()) {
            deleteElement(iterator.next());
        }
    }

    public void deselectAllElements() {
        Iterator<Element> iterator = selectedElements.iterator();
        while (iterator.hasNext()) {
            deselectElement(iterator.next());
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

    public void selectLastElement() {
        Element element = allElements.getLast();
        if (element == null || element.isSelected()) {
            return;
        }
        selectElement(element);
    }

    public void selectAllElements() {
        allElements.iterator().forEachRemaining(this::selectElement);
    }

    public boolean intersectsAnyElements(Point2D point2D) {
        Iterator<Element> iterator = allElements.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().intersects(point2D))
                return true;
        }
        return false;
    }

    public void selectAllElements(Point2D point2D) {
        allElements.iterator().forEachRemaining(element -> {
            if (element.intersects(point2D))
                selectElement(element);
        });
    }

    public void deleteSelectedElements() {
        Iterator<Element> iterator = selectedElements.iterator();
        while (iterator.hasNext()) {
            deleteElement(iterator.next());
        }
    }

    public void moveSelectedElements(double deltaX, double deltaY) {
        Iterator<Element> iterator = selectedElements.iterator();
        Element element;
        while (iterator.hasNext()) {
            element = iterator.next();
            element.move(deltaX, deltaY);
        }
    }

    public void changeColorOnSelectedElements(Color color) {
        selectedElements.iterator().forEachRemaining(element -> element.setColor(color));
    }

    public void resizeSelectedElements(double newRadius) {
        selectedElements.iterator().forEachRemaining(element -> element.resize(newRadius));
    }
}
