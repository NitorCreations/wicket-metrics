package com.nitorcreations.metrics.wicket;

import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertThat;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.MetricName;

public class SessionSizeHistogramRequestCycleListenerTest {

    @Before
    public void setup() {
        @SuppressWarnings("unused")
        WicketTester tester = new WicketTester();
    }

    @Test
    public void createsExpectedMetric() {
        SessionSizeHistogramRequestCycleListener listener = new SessionSizeHistogramRequestCycleListener();
        final RequestCycle rq = RequestCycle.get();

        listener.onBeginRequest(rq);

        MetricName expectedName = new MetricName("com.nitorcreations.metrics.wicket",
                "SessionSizeHistogramRequestCycleListener", "Session size in Bytes");

        assertThat(Metrics.defaultRegistry().allMetrics(), hasKey(expectedName));
    }
}
