
public class Monitor {
	Square_Bot bot;
	public Monitor() {
		
		bot=new Square_Bot();
		bot.run();
	}
	
	public static void main(String args[]) {
		new Monitor();
	}

}
