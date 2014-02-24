package com.nitorcreations.metrics.wicket;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.protocol.http.WebApplication;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;

public class MetricRegistryRom extends AbstractReadOnlyModel<MetricRegistry> {

	private static final long serialVersionUID = -7239159770981379194L;

	@Override
	public MetricRegistry getObject() {
		return (MetricRegistry) WebApplication.get().getServletContext()
				.getAttribute(MetricsServlet.METRICS_REGISTRY);
	}
}
