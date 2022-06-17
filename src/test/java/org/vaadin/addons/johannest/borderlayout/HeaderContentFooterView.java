package org.vaadin.addons.johannest.borderlayout;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.ArrayList;
import java.util.List;

@Route("header-footer-view")
public class HeaderContentFooterView extends Div {

    public HeaderContentFooterView() {
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.addThemeVariants(BorderLayout.BorderLayoutVariant.HEADER_MAIN_FOOTER);

        HorizontalLayout navigationLayout = getNavigationLayout();
        Div contentLayout = getContentLayout();
        HorizontalLayout footerLayout = getFooterLayout();

        borderLayout.addComponent(navigationLayout, BorderLayout.Constraint.PAGE_START);
        borderLayout.addComponent(contentLayout, BorderLayout.Constraint.LINE_START);
        borderLayout.addComponent(footerLayout, BorderLayout.Constraint.PAGE_END);

        add(borderLayout);
    }

    private HorizontalLayout getNavigationLayout() {
        RouterLink link1 = new RouterLink("Home", SimpleView.class);
        RouterLink link2 = new RouterLink("Header-Content-Footer", HeaderContentFooterView.class);
        RouterLink link3 = new RouterLink("Logout", HeaderContentFooterView.class);
        HorizontalLayout navigationLayout = new HorizontalLayout(new HorizontalLayout(link1, link2), link3);
        navigationLayout.setWidthFull();
        navigationLayout.setSpacing(false);
        navigationLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        return navigationLayout;
    }

    private Div getContentLayout() {
        Grid<Person> grid = new Grid<>(Person.class, false);
        grid.addColumn(Person::getFirstName).setHeader("First name");
        grid.addColumn(Person::getLastName).setHeader("Last name");
        grid.addColumn(Person::getEmail).setHeader("Email");
        List<Person> people = createPersons();
        grid.setItems(people);
        return new Div(grid);
    }

    private HorizontalLayout getFooterLayout() {
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");
        button1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button2.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        HorizontalLayout footerLayout = new HorizontalLayout(button1, button2, button3, button4);
        footerLayout.setWidthFull();
        return footerLayout;
    }

    private List<Person> createPersons() {
        List<Person> personList = new ArrayList<>();
        for (int i=0; i<30; i++) {
            personList.add(new Person("Test "+i, "Foo "+i, "test"+i+"@test.com"));
        }
        return personList;
    }
}
