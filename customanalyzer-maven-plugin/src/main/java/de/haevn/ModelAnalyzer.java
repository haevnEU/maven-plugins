package de.haevn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.haevn.models.dependency.CustomDependency;
import de.haevn.models.dependency.CustomModel;
import de.haevn.models.dependency.CustomProfile;
import de.haevn.utils.FileIO;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.ExceptionUtils;

import java.io.File;
import java.util.*;


@Mojo(name = "run",
        defaultPhase = LifecyclePhase.PROCESS_RESOURCES,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class ModelAnalyzer extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    public static final String OUT_PATH = "./target/analyzer/results/model.json";

    public void execute() throws MojoExecutionException {
        try {
            long start = System.currentTimeMillis();
            getLog().info("================================================================================");
            getLog().info("                            Analyze maven project                               ");
            getLog().info("================================================================================");

            getLog().debug("Collect data");
            var profiles = collectProfiles();
            var dependencies = collectDependencies();
            getLog().debug("Data collected");

            CustomModel model = new CustomModel();

            getLog().debug("Create summary model");
            model
                    .addArtifactId(project.getArtifactId())
                    .addDefaultGoal(project.getDefaultGoal())
                    .addDescription(project.getDescription())
                    .addGroupId(project.getGroupId())
                    .addId(project.getId())
                    .addName(project.getName())
                    .addPackaging(project.getPackaging())
                    .addUrl(project.getUrl())
                    .addVersion(project.getVersion())
                    .addProfiles(profiles)
                    .addDependencies(dependencies);
            getLog().debug("Summary model created");

            getLog().debug("Create json data from model");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(model);
            getLog().debug("Json data created");

            getLog().debug("Export summary");
            File file = new File(OUT_PATH);
            FileIO.write(json, file);
            getLog().debug("Summary exported");

            getLog().info("Summary generated path: " + file.getAbsolutePath());
            long runtime = System.currentTimeMillis() - start;
            getLog().info("Analyzed in " + runtime + "ms");
            getLog().info("================================================================================");
        }catch (Exception ex){
            String output = ex.getMessage() + System.lineSeparator() + ExceptionUtils.getStackTrace(ex);
            throw new MojoExecutionException(output);
        }
    }



    private List<CustomProfile> collectProfiles(){
        getLog().debug("Collect profiles");
        List<Profile> profiles = project.getActiveProfiles();
        getLog().debug("Profiles collected");
        return profiles.stream().map(CustomProfile::new).toList();
    }

    private List<CustomDependency> collectDependencies(){
        getLog().debug("Collect dependencies");
        Set<Artifact> dependencies = project.getArtifacts();
        getLog().debug("Dependencies collected");
        return dependencies.stream().map(CustomDependency::new).toList();
    }
}
