package com.aotain.common.utils.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LeaderSelectorClient implements LeaderSelectorListener, Closeable{

	private final String name;
    private final LeaderSelector leaderSelector;
    private boolean isLeader = false;

    public LeaderSelectorClient(CuratorFramework client, String path, String name) {
        this.name = name;
        leaderSelector = new LeaderSelector(client, path, this);
        leaderSelector.autoRequeue();
    }

   
    public boolean isLeader() {
		return isLeader;
	}


	public void start() throws IOException {
        leaderSelector.start();
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }
    
    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState){
    	
        if ( client.getConnectionStateErrorPolicy().isErrorState(newState) ){
        	isLeader = false;
        	System.out.println(name + " discarding leadership.");
            throw new CancelLeadershipException();
        }
    }
    
	@Override
	public void takeLeadership(CuratorFramework client) throws Exception {
		isLeader = true;
		
    	try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            System.err.println(name + " was interrupted.");
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(name + " relinquishing leadership.");
        }

	}
	
}
