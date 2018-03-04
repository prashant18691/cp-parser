package com.parser.cp;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

/**
 * Source: - https://www.plugin-dev.com/intellij/
 */
public class MyApplicationComponent implements ApplicationComponent {
    private static final Logger LOGGER = Logger.getLogger(MyApplicationComponent.class.getSimpleName());

    @Override
    public void initComponent() {
        LOGGER.info("Initializing plugin data structures");

    }

    @Override
    public void disposeComponent() {
        LOGGER.info("Disposing plugin data structures");
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Application";
    }
}
