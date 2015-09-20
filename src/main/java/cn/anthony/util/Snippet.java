package cn.anthony.util;

public class Snippet {
	private static int deductBase = 0;
	public static void main(String[] args) {
		
		while (deductBase<129) {
		deductBase++;
		if(deductBase>Integer.MAX_VALUE)
			deductBase = 50;
		if(deductBase<50||(deductBase-50)%15!=0) {
			System.out.println(deductBase
					);
		}
		}
	}
}

