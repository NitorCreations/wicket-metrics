package com.nitorcreations.metrics.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

/**
 * Attach this to your {@link RequestCycle} and you will get JMX metrics about the session size in
 * Bytes.
 */
public class SessionSizeHistogramRequestCycleListener extends AbstractRequestCycleListener {

    @Override
    public void onBeginRequest(RequestCycle cycle) {
        super.onBeginRequest(cycle);

        Histogram histogram = new MetricRegistryRom().getObject().histogram(MetricRegistry.name(getClass(), "Session size in Bytes"));
        histogram.update(Session.get().getSizeInBytes());
    }

}
