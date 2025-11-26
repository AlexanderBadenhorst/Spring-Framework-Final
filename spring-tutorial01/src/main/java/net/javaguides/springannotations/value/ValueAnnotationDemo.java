package net.javaguides.springannotations.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueAnnotationDemo {

    // Simple literal value
    @Value("Default Name")
    private String defaultName;

    // Properties loaded from application.properties
    @Value("${mail.host:localhost}")
    private String host;

    @Value("${mail.email:no-email-set}")
    private String email;

    @Value("${mail.password:no-password}")
    private String password;

    // Java home always exists on all systems
    @Value("${java.home}")
    private String javaHome;

    // Windows-safe replacement for ${HOME}
    @Value("${USERPROFILE:C:/Users/unknown}")
    private String homeDir;

    public String getDefaultName() {
        return defaultName;
    }

    public String getHost() {
        return host;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public String getHomeDir() {
        return homeDir;
    }
}
