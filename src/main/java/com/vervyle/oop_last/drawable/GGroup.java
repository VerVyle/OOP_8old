package com.vervyle.oop_last.drawable;

import com.vervyle.oop_last.containers.MyLinkedList;
import com.vervyle.oop_last.containers.MyList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class GGroup extends Element {

    private final MyList<Element> children;

    public GGroup(Pane pane) {
        super(false, pane);
        children = new MyLinkedList<>();
    }

    public GGroup(Pane pane, MyList<Element> children) {
        super(false, pane);
        this.children = new MyLinkedList<>();
        Iterator<Element> iterator = children.iterator();
        while (iterator.hasNext()) {
            this.children.add(iterator.next());
        }
    }

    public MyList<Element> getChildren() {
        return children;
    }

    @Override
    public boolean intersects(Point2D point2D) {
        Iterator<Element> iterator = children.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().intersects(point2D))
                return true;
        }
        return false;
    }

    @Override
    public void select() {
        children.iterator().forEachRemaining(Element::select);
    }

    @Override
    public void deselect() {
        children.iterator().forEachRemaining(Element::deselect);
    }

    @Override
    public void show() {
        Iterator<Element> iterator = children.iterator();
        while (iterator.hasNext()) {
            iterator.next().show();
        }
    }

    @Override
    public void hide() {
        Iterator<Element> iterator = children.iterator();
        while (iterator.hasNext()) {
            iterator.next().hide();
        }
    }

    @Override
    public void resize(double newRadius) {
        if (isOutOfPane(newRadius)) {
            System.out.println("Cannot resize element (" + this + "), it doesn't fit pane size");
            return;
        }
        children.iterator().forEachRemaining(element -> element.resize(newRadius));
    }

    @Override
    public void move(double deltaX, double deltaY) {
        if (isOutOfPane(deltaX, deltaY)) {
            System.out.println("Cannot move element (" + this + "), it doesn't fit pane size");
            return;
        }
        children.iterator().forEachRemaining(element -> element.move(deltaX, deltaY));
    }

    @Override
    public void setColor(Color color) {
        children.iterator().forEachRemaining(element -> element.setColor(color));
    }

    @Override
    public boolean isOutOfPane(double deltaX, double deltaY) {
        Iterator<Element> iterator = children.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isOutOfPane(deltaX, deltaY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateShape() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isOutOfPane(double newRadius) {
        Iterator<Element> iterator = children.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isOutOfPane(newRadius)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject save() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        children.iterator().forEachRemaining(element -> {
            JSONObject jsonElement = element.save();
            jsonArray.put(jsonElement);
        });
        jsonObject.put("type", GGroup.class.getName())
                .put("children", jsonArray);
        return jsonObject;
    }
}
