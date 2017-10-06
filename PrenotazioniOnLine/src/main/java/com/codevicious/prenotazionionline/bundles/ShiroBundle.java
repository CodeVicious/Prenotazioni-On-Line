package com.codevicious.prenotazionionline.bundles;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.secnod.shiro.jersey.AuthInjectionBinder;
import org.secnod.shiro.jersey.AuthorizationFilterFeature;
import org.secnod.shiro.jersey.SubjectFactory;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public abstract class ShiroBundle<T> implements ConfiguredBundle<T> {

	public void initialize(Bootstrap<?> bootstrap) {

	}

	public void run(T configuration, Environment environment) throws Exception {
		ShiroConfiguration shiroConfig = narrow(configuration);
		ResourceConfig resourceConfig = environment.jersey().getResourceConfig();

		resourceConfig.register(new AuthorizationFilterFeature());
		resourceConfig.register(new SubjectFactory());
		resourceConfig.register(new AuthInjectionBinder());

		Filter shiroFilter = createFilter(configuration); 
		environment.servlets().addFilter("ShiroFilter", shiroFilter)
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, shiroConfig.filterUrlPattern());

	}

	private Filter createFilter(final T configuration) {
		ShiroConfiguration shiroConfig = narrow(configuration);
		final IniWebEnvironment shiroEnv = new IniWebEnvironment();
		shiroEnv.setConfigLocations(shiroConfig.iniConfigs());
		shiroEnv.init();

		AbstractShiroFilter shiroFilter = new AbstractShiroFilter() {
			@Override
			public void init() throws Exception {
				Collection<Realm> realms = createRealms(configuration);
				WebSecurityManager securityManager = realms.isEmpty() ? shiroEnv.getWebSecurityManager()
						: new DefaultWebSecurityManager(realms);
				
				setSecurityManager(securityManager);
				
				setFilterChainResolver(shiroEnv.getFilterChainResolver());
			}
		};
		return shiroFilter;
	}

	protected abstract ShiroConfiguration narrow(T configuration);

	/**
	 * Create and configure the Shiro realms. Override this method in order to add
	 * realms that require configuration.
	 *
	 * @return a non-null list of realms. If empty, no realms will be added, but
	 *         depending on the content of the INI file Shiro might still add its
	 *         automatic IniRealm.
	 */
	protected Collection<Realm> createRealms(T configuration) {
		return Collections.emptyList();
	}

}