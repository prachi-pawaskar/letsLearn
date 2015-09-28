package com.dummy.project.letsLearn.lib;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pawaskar.a.prachi on 9/28/2015.
 */

//<envConfig>
//        <url>http://zero.webappsecurity.com</url>
//        <username>username</username>
//        <password>password</password>
//</envConfig>

// This class is used to map xml elements to methods and is called in baseClass>>Line# 75
    // XmlRootElement >> <envConfig>
    // XmlElement >> <url>, <username>, <password>
//Note: without "set" method, "get" won't work!

@XmlRootElement
public class envConfig {

    String username, password, url ;

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
