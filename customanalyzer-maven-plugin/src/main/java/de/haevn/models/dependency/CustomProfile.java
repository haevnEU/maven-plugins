package de.haevn.models.dependency;

import org.apache.maven.model.Profile;

import java.util.List;
import java.util.Properties;

public class CustomProfile {
    private final String id;
    private final String source;
    private final Properties properties;
    private final List<CustomDependency> dependencies;

    public CustomProfile(Profile profile){
        this.id = profile.getId();
        this.properties = profile.getProperties();
        this.source = profile.getSource();
        this.dependencies = profile.getDependencies().stream().map(CustomDependency::new).toList();
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public Properties getProperties() {
        return properties;
    }

    public List<CustomDependency> getDependencies() {
        return dependencies;
    }
}
