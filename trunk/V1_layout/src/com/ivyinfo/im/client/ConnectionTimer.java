package com.ivyinfo.im.client;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zy.im.client.config.PushConfig;

/**
 * This class is used (with a Timer) to monitor a connection and interrupt
 * it if it hangs for more than command timeout. For each IO operation to be
 * monitored, one such object must be created. The client is responsible for
 * notifying when the operation is terminated. If by the time the alarm is
 * triggered, the operation is not terminated, then such an operation is
 * considered timeout and closeConnection is invoked. This will cause
 * exceptions in any hanging read/write, allowing each thread to resume
 * execution.
 */
/*public class ConnectionTimer extends TimerTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionTimer.class);

    *//** IO timeout (timer delay) *//*
    private int delay = -1;
    *//** Specifies whether the IO operation has actually terminated *//*
    private boolean terminated = false;
    
    private PushConfig pushConfig = new PushConfig();

    *//** Constructor. The delay is specified in the Configuration *//*
    public ConnectionTimer() {
        delay = pushConfig.getCtpCmdTimeout() * 1000;
    }

    *//**
     * Notifies the ConnectionTimer that the IO operation has terminated.
     * Whenever the alarm will be triggered it won't cause a timeout because
     * the operation is finished.
     *//*
    public void endOperation() {
        terminated = true;
    }

    *//** Returns the delay for this task *//*
    public int getDelay() {
        return delay;
    }

    *//**
     * This method is invoked when the alarm expires. If the operation this
     * task is monitoring has not finished yet, then we force the entire
     * connection to shut down. This will cause exceptions for all the
     * pending read/write operations
     *//*
    public void run() {
        // We were monitoring an operation whose idx is nextTimed
        // check if it terminated
        if (terminated == false) {
            // The operation did not terminate
            // We force an exception to wake up the thread
        	LOGGER.error("[IMClient] An IO operation did not complete before"
                    + " maximum allowed time. Restart the IMClient");
        	//ClientEngine.disconnect(cSession);
        }
    }
}*/