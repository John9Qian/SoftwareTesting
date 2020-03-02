package experiment1;

public class Money {
	private static int[] pocketMoney = {50, 20, 10, 5, 5, 1, 1, 1};
	public static boolean pay(int x) {
		int last = x;
		for(int i=0; i<pocketMoney.length; i++) {
			if(last >= pocketMoney[i]) {
				last -= pocketMoney[i];
			}
		}
		return (last == 0);
	}
}
