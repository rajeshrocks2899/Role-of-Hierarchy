import java.util.HashMap;

public class UsersManager {

	public User user;
	static HashMap<String,Role>USERS= new HashMap<String,Role>();
	static UsersManager users= new UsersManager();
	
	public void setUser(User user) {
		this.user=user;
	}

	public static UsersManager getUsersManager() {
		return users;
	}
	
	public void addUsers(String userName,Role role) {
		USERS.put(userName, role);
	}
	
	public HashMap<String,Role> getUser() {
		return USERS;
	}
	
	
	
	
}
