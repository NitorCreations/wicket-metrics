package com.nitorcreations.metrics.wicket;

import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertThat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.MetricName;

public class PageSizeHistogramBehaviorTest {

    private static WicketTester wicketTester = new WicketTester();
    private PageSizeHistogramBehavior behavior;
    private Label l;

    @Before
    public void withBehavior() throws Exception {
        l = new Label("id");
        behavior = new PageSizeHistogramBehavior();
        l.add(behavior);
    }

    @Test
    public void createsHistogramWithCorrectName() {
        wicketTester.startComponentInPage(l);

        MetricName expectedName = new MetricName("org.apache.wicket.markup.html.basic", "Label", "Page size in Bytes");

        assertThat(Metrics.defaultRegistry().allMetrics(), hasKey(expectedName));
    }

    @Test
    @Ignore("on ignore until serializable matcher available from other Nitor project")
    public void isSerializable() {
        // assertThat(behavior, serializable());
    }

}
