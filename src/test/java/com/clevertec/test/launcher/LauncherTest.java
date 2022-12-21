package com.clevertec.test.launcher;

import com.clevertec.test.configuration.MyConfigurationTest;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.PrintWriter;


class LauncherTest {
    public static void main(String[] args) {

        var launcher = LauncherFactory.create();

        var summaryGeneratingListener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(summaryGeneratingListener);

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(DiscoverySelectors.selectPackage("com.clevertec.test"))
                .build();

        launcher.execute(request);

        try(var writer = new PrintWriter(System.out)) {
            summaryGeneratingListener.getSummary().printTo(writer);
        }
    }
}
