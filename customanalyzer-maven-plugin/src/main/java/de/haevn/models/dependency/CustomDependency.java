package de.haevn.models.dependency;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import java.util.List;

public class  CustomDependency {
    private final String groupID;
    private final String artifactID;
    private final String version;
    private final String scope;
    private final List<String> dependencyTrail;

    public CustomDependency(Dependency dependency){
        this.artifactID = dependency.getArtifactId();
        this.groupID = dependency.getGroupId();
        this.scope = dependency.getScope();
        this.version = dependency.getVersion();
        this.dependencyTrail = null;
    }

    public CustomDependency(Artifact dependency){
        this.artifactID = dependency.getArtifactId();
        this.groupID = dependency.getGroupId();
        this.scope = dependency.getScope();
        this.version = dependency.getVersion();
        this.dependencyTrail = dependency.getDependencyTrail();
    }


    public String getArtifactID() {
        return artifactID;
    }

    public String getGroupID() {
        return groupID;
    }

    public String getScope() {
        return scope;
    }

    public String getVersion() {
        return version;
    }

    public List<String> getDependencyTrail() {
        return dependencyTrail;
    }
}
