package io.jenkins.plugins.sample;

import hudson.Launcher;
import hudson.Launcher.ProcStarter;
import hudson.Proc;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.ArgumentListBuilder;
import hudson.util.FormValidation;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Build;
import hudson.model.BuildListener;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

public class BranchBuilder extends Builder implements SimpleBuildStep {

    private final String name;
    private Builder build;

    @DataBoundConstructor
    public BranchBuilder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @SuppressWarnings("null")
	public int pushCommand() throws IOException, InterruptedException {
    	Launcher launcher = null;
    	AbstractBuild<?, ?> build = null;
    	BuildListener listener = null;
    	
    	ArgumentListBuilder command = new ArgumentListBuilder("git checkout" + name);
    	command.addTokenized("xcopy /?");
    	
    	ProcStarter ps = launcher.new ProcStarter();
    	ps = ps.cmds(command).stdout(listener);
    	ps = ps.pwd(build.getWorkspace()).envs(build.getEnvironment(listener));
		Proc proc = launcher.launch(ps);
    	int retcode = proc.join();
    	
		return retcode;
    }
    
    public int executeBranchScript() throws IOException, InterruptedException {
    	//this function will execute the script makeBranchScript() made
    	ProcessBuilder pb = new ProcessBuilder("git checkout" + name);
    	Process p = pb.start();
    	p.waitFor();
    	return 0;
    }

   
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
    	//makeBranchScript();
    	executeBranchScript();
    	pushCommand();
    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckName(@QueryParameter String value)	
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error(Messages.BranchBuilder_DescriptorImpl_errors_missingName());
            if (value.length() < 1)
                return FormValidation.warning(Messages.BranchBuilder_DescriptorImpl_warnings_tooShort());
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages.BranchBuilder_DescriptorImpl_DisplayName();
        }

    }

}
