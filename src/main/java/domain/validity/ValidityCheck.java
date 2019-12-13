package domain.validity;

public class ValidityCheck {
	public String getName(String name) throws IllegalArgumentException {
		if (name.equals("")) {
			throw new IllegalArgumentException();
		}
		
		return name;
	}
	
	public int getMoney(int money) throws IllegalArgumentException {
		return money;
	}
}
