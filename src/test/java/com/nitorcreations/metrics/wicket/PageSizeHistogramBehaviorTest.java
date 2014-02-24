package com.nitorcreations.metrics.wicket;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;


public class PageSizeHistogramBehaviorTest {

    private static WicketTester wicketTester = new WicketTester();
    private PageSizeHistogramBehavior behavior;
    private Label l;
    private MetricRegistry registry;

    @Before
    public void withBehavior() throws Exception {
    	registry = new MetricRegistry();
        l = new Label("id");
        behavior = new PageSizeHistogramBehavior();
        l.add(behavior);
		WebApplication.get().getServletContext().setAttribute(MetricsServlet.METRICS_REGISTRY,  registry);
    }

    @Test
    public void createsHistogramWithCorrectName() {
        wicketTester.startComponentInPage(l);

        String expectedName = MetricRegistry.name("org.apache.wicket.markup.html.basic", "Label", "pageSizeInBytes");

        assertTrue(registry.getHistograms().containsKey(expectedName));
    }

    @Test
    public void isSerializable() {
            assertTrue(behavior instanceof Serializable);
    }

}
