package de.haevn.models.dependency;

import java.util.List;

public class CustomModel {
    private String groupId;
    private String artifactId;
    private String name;
    private String description;
    private String url;
    private String version;
    private String id;
    private String defaultGoal;
    private String packaging;
    private List<CustomDependency> dependencies;
    private List<CustomProfile> profiles;


    public CustomModel addDependencies(List<CustomDependency> dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    public CustomModel addArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public CustomModel addDefaultGoal(String defaultGoal) {
        this.defaultGoal = defaultGoal;
        return this;
    }

    public CustomModel addDescription(String description) {
        this.description = description;
        return this;
    }

    public CustomModel addGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public CustomModel addId(String id) {
        this.id = id;
        return this;
    }

    public CustomModel addName(String name) {
        this.name = name;
        return this;
    }

    public CustomModel addPackaging(String packaging) {
        this.packaging = packaging;
        return this;
    }

    public CustomModel addUrl(String url) {
        this.url = url;
        return this;
    }

    public CustomModel addVersion(String version) {
        this.version = version;
        return this;
    }

    public CustomModel addProfiles(List<CustomProfile> profiles) {
        this.profiles = profiles;
        return this;
    }
}
