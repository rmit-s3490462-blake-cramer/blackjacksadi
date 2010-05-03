package client;


public class TimeCounter extends Thread {

	/**
	 * GameInterface
	 */
	private Interface gi;

	public TimeCounter(Interface i){
		gi = i;
	}
	
	private int hour;
	private int min;
	private int sec;
		
	public void run() {
		String zeroSec = String.valueOf(0);
		String zeroMin = String.valueOf(0);
		String zeroHour = String.valueOf(0);
		
		while(true) {
			if(sec == 60) {
				sec = 0;
				min++;
				zeroSec = String.valueOf(0);
				if(min == 60) {
					sec = 0;
					min = 0;
					hour++;
					zeroMin = String.valueOf(0);
					if(hour == 24) {
						sec = 0;
						min = 0;
						hour = 0;
						zeroHour = String.valueOf(0);
					}
				}
			}
			
			//Seconds
			if(sec >= 10)
				zeroSec = "";
			
			//Minutes
			if(min >= 10)
				zeroMin = "";
			
			//Hours
			if(hour >= 10)
				zeroHour = "";
			
			gi.setTime(zeroHour+hour+":"+zeroMin+min+":"+zeroSec+sec++);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("InterruptedException accured: " + e);
			}
		}
	}

}
