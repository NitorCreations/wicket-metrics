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

public class TimingBehaviorTest {

    private static WicketTester wicketTester = new WicketTester();
    private TimingBehavior behavior;
    private Label l;

    @Before
    public void withBehavior() throws Exception {
        l = new Label("id");
        behavior = new TimingBehavior();
        l.add(behavior);
    }

    @Test
    public void createsTimerWithCorrectName() {
        wicketTester.startComponentInPage(l);

        MetricName expectedName = new MetricName("org.apache.wicket.markup.html.basic", "Label", "Page render time");

        assertThat(Metrics.defaultRegistry().allMetrics(), hasKey(expectedName));
    }

    @Test
    public void missingTimerContextDoesNotCauseException() {
        behavior.afterRender(l);
    }

    @Test
    public void reRenderWorks() {
        wicketTester.startComponentInPage(l);
        l.render();

        MetricName expectedName = new MetricName("org.apache.wicket.markup.html.basic", "Label", "Page render time");

        assertThat(Metrics.defaultRegistry().allMetrics(), hasKey(expectedName));
    }

    @Test
    @Ignore("needs custom matcher from other Nitor project")
    public void isSerializable() {
        // assertThat(behavior, serializable());
    }
}
