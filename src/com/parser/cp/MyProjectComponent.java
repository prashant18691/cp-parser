package com.parser.cp;

import com.intellij.openapi.components.ProjectComponent;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class MyProjectComponent implements ProjectComponent {
    private static final Logger LOGGER = Logger.getLogger(MyProjectComponent.class.getSimpleName());
    @Override
    public void projectOpened() {
    }

    @Override
    public void projectClosed() {
    }

    @Override
    public void initComponent() {
        LOGGER.info("I will load project");
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "MyProjectComponent";
    }

}
