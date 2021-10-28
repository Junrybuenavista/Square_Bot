
public class Monitor {
	Square_Bot bot;
	public Monitor() {
		
		bot=new Square_Bot();
		
		bot.setBrowser();
		bot.Login();
		bot.process();
	}
	
	public static void main(String args[]) {
		new Monitor();
	}

}
