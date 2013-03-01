package com.nitorcreations.metrics.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.Behavior;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Histogram;

public class PageSizeHistogramBehavior extends Behavior {

    private static final long serialVersionUID = 2072445181121144191L;

    private Class<?> componentClazz;

    @Override
    public void bind(Component component) {
        this.componentClazz = component.getClass();
    }

    @Override
    public void afterRender(Component component) {
        Histogram histogram = Metrics.newHistogram(componentClazz, "Page size in Bytes");
        histogram.update(Session.get().getSizeInBytes());
    }

}