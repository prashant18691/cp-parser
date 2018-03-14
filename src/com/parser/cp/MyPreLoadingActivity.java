package com.parser.cp;

import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.progress.ProgressIndicator;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class MyPreLoadingActivity extends PreloadingActivity {
    private static final Logger LOGGER = Logger.getLogger(MyPreLoadingActivity.class.getSimpleName());

    @Override
    public void preload(@NotNull ProgressIndicator progressIndicator) {
        LOGGER.info("Pre-loading plugin-data / parsing DOM");
    }
}
