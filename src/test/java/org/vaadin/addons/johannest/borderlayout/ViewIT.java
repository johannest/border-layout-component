package org.vaadin.addons.johannest.borderlayout;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.testbench.TestBenchElement;

public class ViewIT extends AbstractViewTest {

    @Test
    public void componentWorks() {
        final TestBenchElement borderLayout = $("border-layout").waitForFirst();
        // Check that border-layout contains at least one other element, which means that
        // is has been upgraded to a custom element and not just rendered as an empty
        // tag
        Assert.assertTrue(
                borderLayout.$(TestBenchElement.class).all().size() > 0);
    }
}
