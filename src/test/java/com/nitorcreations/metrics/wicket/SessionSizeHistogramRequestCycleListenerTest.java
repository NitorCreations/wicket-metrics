package com.nitorcreations.metrics.wicket;

import static org.junit.Assert.*;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;


public class SessionSizeHistogramRequestCycleListenerTest {

	private MetricRegistry registry;
    @Before
    public void setup() {
        @SuppressWarnings("unused")
        WicketTester tester = new WicketTester();
        registry = new MetricRegistry();
		WebApplication.get().getServletContext().setAttribute(MetricsServlet.METRICS_REGISTRY,  registry);
    }

    @Test
    public void createsExpectedMetric() {
        SessionSizeHistogramRequestCycleListener listener = new SessionSizeHistogramRequestCycleListener();
        final RequestCycle rq = RequestCycle.get();

        listener.onBeginRequest(rq);

        String expectedName = MetricRegistry.name("com.nitorcreations.metrics.wicket",
                "SessionSizeHistogramRequestCycleListener", "Session size in Bytes");

        assertTrue(registry.getHistograms().containsKey(expectedName));
    }
}
