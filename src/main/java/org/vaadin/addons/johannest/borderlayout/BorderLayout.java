package org.vaadin.addons.johannest.borderlayout;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Label;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsModule("./border-layout.ts")
@Tag("border-layout")
public class BorderLayout extends Component implements HasStyle, HasComponents, HasSize, HasTheme {

    public enum Constraint {
        NORTH, WEST, CENTER, EAST, SOUTH, PAGE_START, PAGE_END, LINE_START, LINE_END
    }

    public enum BorderLayoutVariant {

        HEADER_MAIN_FOOTER("header-main-footer"), FRAMED("framed");

        private final String variant;

        BorderLayoutVariant(String variant) {
            this.variant = variant;
        }

        /**
         * Gets the variant name.
         *
         * @return variant name
         */
        public String getVariantName() {
            return variant;
        }
    }

    protected Component defaultNorth = new Label(" ");
    protected Component defaultWest = new Label(" ");
    protected Component defaultCenter = new Label(" ");
    protected Component defaultEast = new Label(" ");
    protected Component defaultSouth = new Label(" ");

    protected Component north = defaultNorth;
    protected Component west = defaultWest;
    protected Component center = defaultCenter;
    protected Component east = defaultEast;
    protected Component south = defaultSouth;

    int hgap;
    int vgap;

    public BorderLayout(Component... components) {
        this.add(components);
    }

    public BorderLayout() {
    }

    public BorderLayout(int hgap, int vgap) {
        setHgap(hgap);
        setVgap(vgap);
    }

    public int getHgap() {
        return hgap;
    }

    public void setHgap(int hgap) {
        this.hgap = hgap;
        this.getElement().getStyle().set("--column-gap", hgap+"px");
    }

    public int getVgap() {
        return vgap;
    }

    public void setVgap(int vgap) {
        this.vgap = vgap;
        this.getElement().getStyle().set("--row-gap", vgap+"px");
    }

    public void addLayoutComponent(Component component, Object constraint) {
        addComponent(component, (Constraint) constraint);
    }

    public void removeLayoutComponent(Component component) {
        remove(component);
    }

    public Component getLayoutComponent(Object constraint) {
        return getComponent((Constraint) constraint);
    }

    /**
     * Adds components into layout in the default order: 1. CENTER, 2. NORTH, 3. WEST,
     * 4. EAST, 5. SOUTH until all slots are filled
     *
     * @throws IllegalArgumentException if layout is "full"
     */
    public BorderLayout addComponent(Component c) {
        if (getComponent(Constraint.CENTER).equals(defaultCenter)) {
            addComponent(c, Constraint.CENTER);
            return this;
        } else if (getComponent(Constraint.NORTH).equals(defaultNorth)) {
            addComponent(c, Constraint.NORTH);
            return this;
        } else if (getComponent(Constraint.WEST).equals(defaultWest)) {
            addComponent(c, Constraint.WEST);
            return this;
        } else if (getComponent(Constraint.EAST).equals(defaultEast)) {
            addComponent(c, Constraint.EAST);
            return this;
        } else if (getComponent(Constraint.SOUTH).equals(defaultSouth)) {
            addComponent(c, Constraint.SOUTH);
            return this;
        }
        throw new IllegalArgumentException(
                "All layout places are filled, please use addComponent(Component c, Constraint constraint) for force fill given place");
    }

    public BorderLayout addComponent(Component component, Constraint constraint) {
        if (constraint == Constraint.NORTH || constraint == Constraint.PAGE_START) {
            north = component;
            component.getElement().setAttribute("slot", "NORTH");
            getElement().appendChild(component.getElement());
        }
        else if (constraint == Constraint.WEST || constraint == Constraint.LINE_START) {
            west = component;
            component.getElement().setAttribute("slot", "WEST");
            getElement().appendChild(component.getElement());
        }
        else if (constraint == Constraint.CENTER) {
            center = component;
            component.getElement().setAttribute("slot", "CENTER");
            getElement().appendChild(component.getElement());
        }
        else if (constraint == Constraint.EAST || constraint == Constraint.LINE_END) {
            east = component;
            component.getElement().setAttribute("slot", "EAST");
            getElement().appendChild(component.getElement());
        }
        else if (constraint == Constraint.SOUTH || constraint == Constraint.PAGE_END) {
            south = component;
            component.getElement().setAttribute("slot", "SOUTH");
            getElement().appendChild(component.getElement());
        }
        else {
            throw new IllegalArgumentException(
                    "Invalid BorderLayout constraint.");
        }
        return this;
    }

    /**
     * Return component from specific position
     *
     * @param constraint
     * @return
     */
    public Component getComponent(Constraint constraint) {
        if (constraint == Constraint.NORTH || constraint == Constraint.PAGE_START) {
            return north;
        } else if (constraint == Constraint.WEST || constraint == Constraint.LINE_START) {
            return west;
        } else if (constraint == Constraint.CENTER) {
            return center;
        } else if (constraint == Constraint.EAST || constraint == Constraint.LINE_END) {
            return east;
        } else if (constraint == Constraint.SOUTH || constraint == Constraint.PAGE_END) {
            return south;
        } else {
            throw new IllegalArgumentException(
                    "Invalid BorderLayout constraint.");
        }
    }


    private Component getDefault(Constraint constraint) {
        if (constraint == Constraint.NORTH || constraint == Constraint.PAGE_START) {
            return defaultNorth;
        } else if (constraint == Constraint.WEST || constraint == Constraint.LINE_START) {
            return defaultWest;
        } else if (constraint == Constraint.CENTER) {
            return defaultCenter;
        } else if (constraint == Constraint.EAST || constraint == Constraint.LINE_END) {
            return defaultEast;
        } else if (constraint == Constraint.SOUTH || constraint == Constraint.PAGE_END) {
            return defaultSouth;
        } else {
            throw new IllegalArgumentException(
                    "Invalid BorderLayout constraint.");
        }
    }

    /**
     * Returns position of given component or null if the layout doesn't contain it
     *
     * @param component
     * @return
     */
    public Constraint getConstraint(Component component) {
        if (north.equals(component)) {
            return Constraint.NORTH;
        } else if (west.equals(component)) {
            return Constraint.WEST;
        } else if (center.equals(component)) {
            return Constraint.CENTER;
        } else if (east.equals(component)) {
            return Constraint.EAST;
        } else if (south.equals(component)) {
            return Constraint.SOUTH;
        } else {
            return null;
        }
    }

    /**
     * Removes the given child components from this component.
     *
     * @param components The components to remove.
     * @throws IllegalArgumentException if any of the components is not a child of this component.
     */
    public void remove(Component... components) {
        for (Component component : components) {
            if (getElement().equals(component.getElement().getParent())) {
                component.getElement().removeAttribute("slot");
                getElement().removeChild(component.getElement());
            } else {
                throw new IllegalArgumentException("The given component ("
                        + component + ") is not a child of this component");
            }
        }
    }

    /**
     * Removes all contents from this component, this includes child components,
     * text content as well as child elements that have been added directly to
     * this component using the Element API.
     */
    public void removeAll() {
        getElement().getChildren()
                .forEach(child -> child.removeAttribute("slot"));
        getElement().removeAllChildren();
    }

    /**
     * Adds theme variants to the component.
     *
     * @param variants
     *            theme variants to add
     */
    public void addThemeVariants(BorderLayoutVariant... variants) {
        getThemeNames()
                .addAll(Stream.of(variants).map(BorderLayoutVariant::getVariantName)
                        .collect(Collectors.toList()));
    }

    /**
     * Removes theme variants from the component.
     *
     * @param variants
     *            theme variants to remove
     */
    public void removeThemeVariants(BorderLayoutVariant... variants) {
        getThemeNames().removeAll(
                Stream.of(variants).map(BorderLayoutVariant::getVariantName)
                        .collect(Collectors.toList()));
    }
}
