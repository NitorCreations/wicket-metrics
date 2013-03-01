package com.nitorcreations.metrics.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Timer;
import com.yammer.metrics.core.TimerContext;

public class TimingBehavior extends Behavior {
    private static final long serialVersionUID = -1087866639896000332L;
    private transient Timer timer;
    private transient TimerContext timerContext;

    private Class<?> componentClazz;

    private Timer getTimer() {
        if (timer == null) {
            timer = Metrics.newTimer(componentClazz, "Page render time");
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