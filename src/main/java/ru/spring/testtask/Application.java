package ru.spring.testtask;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.cli.*;

import java.io.File;

public class Application {
    /*Похоже, другого способа отключить JSP нет*/
    private static class noJSPListener implements LifecycleListener {
        @Override
        public void lifecycleEvent(LifecycleEvent event) {
        }
    }
    private static class NoJSPTomcat extends Tomcat{
        @Override
        public LifecycleListener getDefaultWebXmlListener() {
            return new noJSPListener();
        }
    }
    public static void main(String[] args) throws Exception {
        Options options = new Options();

        Option portOption = new Option(null, "port", true, "port number");
        portOption.setRequired(true);
        portOption.setType(Number.class);
        options.addOption(portOption);

        Option dbOption = new Option(null, "db", true, "database, username, password, port");
        dbOption.setArgs(4);
        options.addOption(dbOption);

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

        String[] dbArgs = cmd.getOptionValues("db");

        String webAppDirLocation = ".";
        NoJSPTomcat tomcat = new NoJSPTomcat();
        tomcat.setPort(((Number) cmd.getParsedOptionValue("port")).intValue());
        tomcat.getConnector();

        Context ctx = tomcat.addWebapp("", new File(webAppDirLocation).getAbsolutePath());
        ctx.addParameter("db.name", dbArgs[0]);
        ctx.addParameter("db.username", dbArgs[1]);
        ctx.addParameter("db.password", dbArgs[2]);
        ctx.addParameter("db.port", dbArgs[3]);
        tomcat.start();
        tomcat.getServer().await();
    }
}
