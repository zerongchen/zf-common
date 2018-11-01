package com.aotain.common.utils.zookeeper;

public class BizThread extends Thread {
	
	private LeaderLatchClient leaderSelector;
	
	public BizThread(LeaderLatchClient leaderSelector){
		this.leaderSelector = leaderSelector;
	}
	
	@Override
	public void run(){
		
		while(true){
			
			if(!leaderSelector.getLeader()){
				System.out.println("I have a rest!");
			}
			else{
				System.out.println("I am working!");
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
