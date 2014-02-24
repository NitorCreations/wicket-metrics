package com.nitorcreations.metrics.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.Timer.Context;


/**
 * Adds a metric to the {@link WebPage} that this {@link Behavior} is attached to. This metric will
 * track the time it took to render this page.
 * 
 * Metrics use the runtime classname as their name. So you can add this behavior to your base page
 * and you will still get separate metrics for each page.
 */
public class TimingBehavior extends Behavior {
    private static final long serialVersionUID = -1087866639896000332L;
    private transient Timer timer;
    private transient Context timerContext;

    private Class<?> componentClazz;

    private Timer getTimer() {
        if (timer == null) {
            timer = new MetricRegistryRom().getObject().timer(MetricRegistry.name(componentClazz, "Page render time"));
        }
        return timer;
    }

    @Override
    public void bind(Component component) {
        this.componentClazz = component.getClass();
    }

    private void startTimer() {
        timerContext = getTimer().time();
    }

    private void endTimer() {
        if (timerContext != null) {
            timerContext.stop();
        }
    }

    @Override
    public void onConfigure(Component component) {
        startTimer();
    }

    @Override
    public void afterRender(Component component) {
        endTimer();
    }

}
