package factory;

import controller.AccessController;
import controller.ManageAccess;

public class AccessFactory {

	private static AccessController login = new AccessController();
	
	public static ManageAccess getManageAccess() {
		return login;
	}
}
