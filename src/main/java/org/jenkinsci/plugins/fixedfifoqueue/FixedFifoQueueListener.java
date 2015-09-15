package org.jenkinsci.plugins.fixedfifoqueue;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.Queue;
import hudson.model.queue.QueueListener;
import jenkins.model.Jenkins;

import java.util.List;

@Extension
public class FixedFifoQueueListener extends QueueListener {
    @Override
    public void onEnterWaiting(Queue.WaitingItem wi) {
        super.onEnterWaiting(wi);

        Queue.Task t = wi.task.getOwnerTask();
        if (t instanceof Job) {
            Job j = (Job) t;
            FixedFifoQueueJobProperty p = (FixedFifoQueueJobProperty) j.getProperty(FixedFifoQueueJobProperty.class);
            if (p != null && p.getLimit() > 0) {
                Queue q = Jenkins.getInstance().getQueue();
                List<Queue.Item> items;
                while ((items = q.getItems(t)).size() > p.getLimit()) {
                    q.cancel(items.get(0));
                }
            }
        }
    }
}
