package de.telekom.pni.rmstest.backend.jenkinsClient.test;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;


public class TestJenkinsAPI {

    JenkinsServer jenkins;

    public TestJenkinsAPI() {
        //connectionsToJenkinsServer();
    }

    public void connectionsToJenkinsServer() {
        try {
            System.out.println("Login to JenkinsSever\n");


            jenkins = new JenkinsServer(new URI("http://localhost:8080"), "a.kutekidila", "1405%Eva2");
            Map<String, Job> jobs = jenkins.getJobs();
            JobWithDetails job = jobs.get("akiditestcountiuns").details();
            System.out.println(job.getDisplayName());
            System.out.println(job.getNextBuildNumber());
            System.out.println(job.getUrl());
            System.out.println(job.getClient().toString());


            System.out.println("\nLogout to JenkinsSever");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        TestJenkinsAPI jenkins = new TestJenkinsAPI();
        jenkins.connectionsToJenkinsServer();
    }
}