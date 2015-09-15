package org.jenkinsci.plugins.fixedfifoqueue;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

import java.util.List;

/**
 * Created by danielbeck on 15.09.2015.
 */
public class FixedFifoQueueJobProperty extends JobProperty<Job<?, ?>> {

    private int limit = 0;

    @DataBoundConstructor
    public FixedFifoQueueJobProperty() {
    }

    public int getLimit() {
        return limit;
    }

    @DataBoundSetter
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Extension
    public static class DescriptorImpl extends JobPropertyDescriptor {

        @Override
        public boolean isApplicable(Class<? extends Job> jobType) {
            return Job.class.isAssignableFrom(jobType);
        }

        @Override
        public String getDisplayName() {
            return "Limit number of concurrent items in queue";
        }

        @Override
        public JobProperty<?> newInstance(StaplerRequest req,
                                          JSONObject formData) throws FormException {
            if (formData.isNullObject()) {
                return null;
            }

            JSONObject fixedfifo = formData.getJSONObject("fixedfifo");

            if (fixedfifo.isNullObject()) {
                return null;
            }

            return req.bindJSON(FixedFifoQueueJobProperty.class, fixedfifo);
        }

    }
}
