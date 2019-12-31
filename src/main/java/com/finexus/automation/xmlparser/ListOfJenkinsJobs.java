package com.finexus.automation.xmlparser;

import org.dom4j.io.*;
import org.dom4j.*;
import java.net.*;
import java.util.*;

public class ListOfJenkinsJobs {

	public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/job/MavenDemoTest/api/xml");
        Document dom = new SAXReader().read(url);

        for( Element job : (List<Element>)dom.getRootElement().elements("job")) {
            System.out.println(String.format("Job %s has status %s",
                job.elementText("name"), job.elementText("color")));
        }
    }
}
