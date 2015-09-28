package com.dummy.project.letsLearn.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;


/**
 * Created by pawaskar.a.prachi on 9/24/2015.
 */
public class baseClass {

    //Webdriver is an interface and is declared globally to used anywhere
    public WebDriver driver;

    //These are variables used to set env configs path and file name
    //ENV value is take as maven parameter eg: mvn test -Denv=BAU
    public static  String ENV = System.getProperty("env");
    private static final String ENVRESOURCEDIR = "/env/" + ENV ;
    //This file name remains same in env>BAU and env>SBX etc directory
    private static final String ENVCONFIGFILENAME = "envConfig.xml";

    //These are variables used to set env configs variables
    public static String baseURL;
    public static String username;
    public static String password;

    //Runs before the suite runs
    @BeforeSuite()
    public void startDriver(){
        //Starts new firefox session and not the default, means it doesn't take the FF proxies and adds on etc..
        driver = new FirefoxDriver();
        //Maximize the opened FF window
        driver.manage().window().maximize();
        //Hits the baseURL in to the browser which is set at line#74
        driver.get(baseURL);
    }

    //This is baseClass constructor which means it runs first when baseClass called
    public baseClass(){
        //Calling initial functions
        setDefaults();
        setEnv();
    }

    //This method is used to set defaults for the run
    private void setDefaults() {
        //If no env parameter passed then default is BAU
        if (ENV.isEmpty()) {ENV = "BAU";}
    }

    //This function is used to read envConfig.xml and set values accordingly.
    public void setEnv() {
        //Local variable for setting config file path ie.envConfig.xml
        String resourcePath = ENVRESOURCEDIR;
        String filePath = ENVCONFIGFILENAME;
        String resourceFilePath = resourcePath + "/" + filePath;
        System.out.println("resourceFilePath:" + resourceFilePath);
        //Below line is used to map the envConfig.xml to your system's project location
        URL urlToData = this.getClass().getResource(resourceFilePath);
        String resourceURL = urlToData.getPath();
        System.out.println("resourceURL:" + resourceURL);
        try {
            //Reads the file
            File file = new File(resourceURL);

            //This line is used to create instance of our xml of type JAXB[which is needed to convert xml to object].
            JAXBContext jaxbContext = JAXBContext.newInstance(envConfig.class);

            //Creating unmarshaller object.
            //Unmarshalling is converting xml into object and marshalling is vice versa.
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //Actual unmarshalling takes place and objects are added to configClass object of type envConfig.class
            //This is the time when lib>envConfig.java is called and all the values are set
            envConfig configClass = (envConfig) jaxbUnmarshaller.unmarshal(file);

            //Simply use the above object "configClass" to retrieve xml values
            baseURL = configClass.getUrl();
            System.out.println("baseURL: " + baseURL);
            username = configClass.getUsername();
            System.out.println("username: " + username);
            password = configClass.getPassword();
            System.out.println("password: " + password);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    //Run after the suite
    @AfterSuite (alwaysRun = true)
    public void stopDriver(){
        //Closes the currently active firefox session
        driver.close();
    }



}
