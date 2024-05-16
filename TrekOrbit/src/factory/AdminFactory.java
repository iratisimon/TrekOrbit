package factory;

import controller.AdminController;
import controller.ManageAdmin;

public class AdminFactory {
	private static AdminController admin = new AdminController();
	
	public static ManageAdmin getManageAdmin() {
		return admin;
	}
}
