package ac;

import java.util.Date;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortIn;

public class Receiver {
	
	protected static final int Interger = 0;
	public boolean[] mutes, singles;
	
	public Receiver() {
		mutes = new boolean[9];
		singles = new boolean[9];
		for(int i = 0; i < mutes.length; i++) {
			mutes[i] = true;
			singles[i] = false;
		}
		
		try {
			OSCPortIn receiver = new OSCPortIn(12000);
			OSCListener listener = new OSCListener() {
				public void acceptMessage(java.util.Date time, OSCMessage message) {
					// System.out.println(message.getArguments()[0].toString());
					// System.out.println(message.getAddress());
					if(message.getAddress().equals("/mute")) {
						// System.out.println(message.getArguments()[0].toString());
						String value = message.getArguments()[0].toString();
						int number = Integer.parseInt(value);
						mutes[number-1] = !mutes[number-1];
						// System.out.println(mutes[0] + " " + mutes[1]);
					}
				}
			};
			receiver.addListener("/test", listener);
			receiver.addListener("/mute", listener);
			receiver.startListening();
		} catch (Exception e) {}
	}
}