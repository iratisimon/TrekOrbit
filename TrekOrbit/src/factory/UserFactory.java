package factory;

import controller.ManageUser;
import controller.UserController;

public class UserFactory {
	private static UserController user = new UserController();
	
	public static ManageUser getManageUser() {
		return user;
	}
}
