package org.vaadin.addons.johannest.borderlayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("")
public class SimpleView extends Div {

    private String[] texts = { "NORTH", "SOUTH", "CENTER", "EAST", "WEST" };

    public SimpleView() {
        Component[] components = new Component[5];
        for (int i = 0; i < 5; i++) {
            components[i] = new TextArea();
            ((TextArea) components[i]).setSizeFull();
            ((TextArea) components[i]).setValue(texts[i]);
        }

        BorderLayout borderLayout = new BorderLayout(10, 5);
        borderLayout.addComponent(components[0], BorderLayout.Constraint.NORTH);
        borderLayout.addComponent(components[1], BorderLayout.Constraint.SOUTH);
        borderLayout.addComponent(components[2], BorderLayout.Constraint.CENTER);
        borderLayout.addComponent(components[3], BorderLayout.Constraint.EAST);
        borderLayout.addComponent(components[4], BorderLayout.Constraint.WEST);

        add(borderLayout);
    }
}
