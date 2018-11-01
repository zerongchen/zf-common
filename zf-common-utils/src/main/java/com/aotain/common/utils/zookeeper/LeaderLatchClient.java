package com.aotain.common.utils.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

import java.io.Closeable;
import java.io.IOException;

public class LeaderLatchClient implements LeaderLatchListener,
		ConnectionStateListener, Closeable {

	private final String name;
	private final LeaderLatch leaderSelector;
	private boolean isLeader = false;

	public LeaderLatchClient(CuratorFramework client, String path,
			String name) {
		this.name = name;
		this.leaderSelector = new LeaderLatch(client, path, name);
		this.leaderSelector.addListener(this);
	}

	public boolean getLeader() {
		return isLeader;
	}

	public void start() throws Exception {
		leaderSelector.start();
	}

	@Override
	public void close() throws IOException {
		leaderSelector.close();
	}

	@Override
	public void isLeader() {
		isLeader = true;
	}

	@Override
	public void notLeader() {
		isLeader = false;
	}

	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		if (client.getConnectionStateErrorPolicy().isErrorState(newState)) {
			isLeader = false;
			System.out.println(name + " discarding leadership.");
			throw new CancelLeadershipException();
		}

	}

}
