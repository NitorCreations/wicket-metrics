package com.nitorcreations.metrics.wicket;

import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;


public class TimingBehaviorTest {
	private MetricRegistry registry;
    private static WicketTester wicketTester = new WicketTester();
    private TimingBehavior behavior;
    private Label l;

    @Before
    public void withBehavior() throws Exception {
    	registry = new MetricRegistry();
        l = new Label("id");
        behavior = new TimingBehavior();
        l.add(behavior);
		WebApplication.get().getServletContext().setAttribute(MetricsServlet.METRICS_REGISTRY,  registry);
    }

    @Test
    public void createsTimerWithCorrectName() {
        wicketTester.startComponentInPage(l);

        String expectedName = MetricRegistry.name("org.apache.wicket.markup.html.basic", "Label", "Page render time");

        assertTrue(registry.getTimers().containsKey(expectedName));
    }

    @Test
    public void missingTimerContextDoesNotCauseException() {
        behavior.afterRender(l);
    }

    @Test
    public void reRenderWorks() {
        wicketTester.startComponentInPage(l);
        l.render();

        String expectedName = MetricRegistry.name("org.apache.wicket.markup.html.basic", "Label", "Page render time");

        assertTrue(registry.getTimers().containsKey(expectedName));
    }

    @Test
    public void isSerializable() {
        assertTrue(behavior instanceof Serializable);
    }
}
