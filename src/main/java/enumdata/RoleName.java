package enumdata;

import javax.management.loading.PrivateClassLoader;

public enum RoleName {
	ADMIN("1"),
	LEAD("2"),
	USER("3");
	
	private final String name;
	
	private RoleName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
