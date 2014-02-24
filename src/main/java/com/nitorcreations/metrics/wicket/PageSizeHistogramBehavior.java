package com.nitorcreations.metrics.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;


/**
 * Adds a metric to the {@link WebPage} that this {@link Behavior} is attached
 * to. This metric will track the page size in Bytes.
 * 
 * Metrics use the runtime classname as their name. So you can add this behavior
 * to your base page and you will still get separate metrics for each page.
 */
public class PageSizeHistogramBehavior  extends Behavior {

	private static final long serialVersionUID = 2072445181121144191L;

	private Class<?> componentClazz;

	@Override
	public void bind(Component component) {
		this.componentClazz = component.getClass();
	}

	@Override
	public void afterRender(Component component) {
		Histogram histogram = new MetricRegistryRom().getObject().histogram(
				MetricRegistry.name(componentClazz, "pageSizeInBytes"));
		histogram.update(component.getPage().getSizeInBytes());
	}

}
