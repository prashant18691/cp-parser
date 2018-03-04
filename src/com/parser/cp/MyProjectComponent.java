package com.parser.cp;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyProjectComponent implements ProjectComponent {
    private static final Logger LOGGER = Logger.getLogger(MyProjectComponent.class.getSimpleName());
    private static final int PORT = 4243;
    private ServerSocket serverSocket;
    private final Project project;

    public MyProjectComponent(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {
        /*Add Connection listener on port */
        /*Check if a project is open*/
        try {
            serverSocket = new ServerSocket(PORT);
            new Thread(() -> {
                while (true) {
                    if (serverSocket.isClosed())
                        return;
                    try {
                        Socket socket = serverSocket.accept();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream(), "UTF-8"));
                        StringBuilder builder = new StringBuilder();
                        String s;
                        while ((s = reader.readLine()) != null)
                            builder.append(s).append('\n');
                        final String page = builder.toString();
                        LOGGER.info(page);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Error occurred during socket acceptance ", e);
                        return;
                    }
                }
            }).start();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred ", e);
        }
    }

    @Override
    public void projectClosed() {
        /*Stash changes*/
    }

    @Override
    public void initComponent() {
        /*Parse dom here?*/
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Whatever";
    }
}
