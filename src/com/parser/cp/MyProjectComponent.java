package com.parser.cp;

import com.intellij.openapi.application.TransactionGuard;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.parser.cp.exception.ImpartialException;
import com.parser.cp.impl.HackerRankDomParserImpl;
import com.parser.cp.model.Question;
import com.parser.cp.model.Task;
import com.parser.cp.util.Constant;
import com.parser.cp.util.FileUtility;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyProjectComponent implements ProjectComponent {
    private static final Logger LOGGER = Logger.getLogger(MyProjectComponent.class.getSimpleName());
    private static final int PORT = 4243;
    private ServerSocket serverSocket;
    private Project project;

    public void setProject(Project project) {
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
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream(), "UTF-8"));
                        final String type = bufferedReader.readLine();
                        StringBuilder builder = new StringBuilder();
                        String s;
                        while ((s = bufferedReader.readLine()) != null)
                            builder.append(s).append('\n');
                        final String page = embedHTML(builder.toString());
                        /*What is this used for ?*/
                        TransactionGuard.getInstance().submitTransactionAndWait(() -> {
                            DomParser domParser = new HackerRankDomParserImpl();
                            try {
                                /*OK I have the task, Do I have the project with me ?*/
                                Task task = domParser.parse(page);
                                /*Do we have java-cp project with us?*/
                                Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
                                Optional<Project> neededProject = Arrays.asList(openProjects).stream().filter(currentProject -> currentProject.getName().equals("java-cp")).findFirst();
                                if (neededProject.isPresent()) {
                                    /*Change the value of input & output*/
                                    Project project = neededProject.get();
                                    setProject(project);
                                    task.getQuestions().forEach(question -> {
                                        try {
                                            initializeModule(question);
                                        } catch (IOException e) {
                                            /*Should we now ignore this test case alone or is it the only test case ?*/
                                            e.printStackTrace();
                                        }
                                    });
                                } else {
                                    /*Clone project*/
                                }

                                LOGGER.info("Whatever");
                            } catch (ImpartialException e) {
                                LOGGER.severe("Error occurred during parsing : " + e.getLocalizedMessage());
                            }
                        });
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

    private void initializeModule(Question question) throws IOException {
        FileUtility.writeTextFile(project.getBaseDir().findFileByRelativePath(Constant.TEST_CASE_DIRECTORY), Constant.INPUT_TEST_CASE_FILE, question.getInput());
        FileUtility.writeTextFile(project.getBaseDir().findFileByRelativePath(Constant.TEST_CASE_DIRECTORY), Constant.OUTPUT_TEST_CASE_FILE, question.getOutput());
    }

    @Override
    public void projectClosed() {
        /*Stash changes*/
    }

    @Override
    public void initComponent() {
        /*Load project or clone from source*/
        /*try {

            Project[] projects =  ProjectManager.getInstance().getOpenProjects();
            projects[0].getName();
            ProjectManager.getInstance().loadAndOpenProject("java-cp");
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }*/
        /*Load values from properties like URL etc.*/
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Whatever";
    }

    private String embedHTML(String innerHTML) {
        return "<html><body>" + innerHTML + "</body></html>";
    }
}
