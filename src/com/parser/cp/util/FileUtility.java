package com.parser.cp.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class FileUtility {
    private static final Logger LOGGER = Logger.getLogger(FileUtility.class.getSimpleName());

    public static VirtualFile getFile(Project project, String location) {
        VirtualFile baseDir = project.getBaseDir();
        if (baseDir == null) {
            return null;
        }
        return baseDir.findFileByRelativePath(location);
    }

    public static String readTextFile(VirtualFile file) {
        try {
            return VfsUtil.loadText(file);
        } catch (IOException e) {
            return null;
        }
    }

    public static VirtualFile writeTextFile(@NotNull final VirtualFile location, final String fileName, final String fileContent) throws IOException {
        ApplicationManager.getApplication().runWriteAction(() -> {
            OutputStream stream = null;
            try {
                VirtualFile file = location.findOrCreateChildData(null, fileName);
                stream = file.getOutputStream(null);
                stream.write(fileContent.getBytes(Charset.forName("UTF-8")));
            } catch (IOException ignored) {
                LOGGER.severe("Error occurred " + ignored.getLocalizedMessage());
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        });
        if (location == null) {
            return null;
        }
        return location.findChild(fileName);
    }
}
