package am.ik.blog.config;

import io.opentelemetry.sdk.trace.export.SpanExporter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OtelConfig {
	@Bean
	public BeanPostProcessor filteringSpanExporterRegistrar(UriFilter uriFilter) {
		return new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (bean instanceof final SpanExporter spanExporter) {
					return new FilteringSpanExporter(spanExporter, uriFilter);
				}
				return bean;
			}
		};
	}
}
