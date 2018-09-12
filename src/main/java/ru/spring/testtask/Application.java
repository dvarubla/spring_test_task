package ru.spring.testtask;

import org.apache.catalina.startup.Tomcat;
import org.apache.commons.cli.*;

import java.io.File;

public class Application {
    public static void main(String[] args) throws Exception {
        Options options = new Options();

        Option portOption = new Option(null, "port", true, "port number");
        portOption.setRequired(true);
        portOption.setType(Number.class);
        options.addOption(portOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("testtask", options);
            System.exit(1);
        }

        String webAppDirLocation = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(((Number) cmd.getParsedOptionValue("port")).intValue());
        tomcat.getConnector();
        tomcat.addWebapp("", new File(webAppDirLocation).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
