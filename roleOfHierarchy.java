import java.util.*;

public class roleOfHierarchy {
static Scanner scan = new Scanner(System.in);
public static void main(String[] args) {
	
	System.out.println("Enter the root role name :");
	Role role1 = new Role(scan.next(),true);
	RolesManager.getRolesManager().setRootRole(role1);
	RolesManager.getRolesManager().addRole(role1);
	Operation();
}
public static  void addSubRole() {
	System.out.println("Enter the sub role name :");
	String subRoleName = scan.next();
	Role searchedsubRole=RolesManager.getRolesManager().searchRole(subRoleName);
	if(searchedsubRole!=null)
	{
		System.out.println("Role already exists ,Try new role");
	}else {
		Role subRole = new Role(subRoleName,false);
		System.out.println("Enter the reporting to role name : ");
		String reportingTo = scan.next();
		Role reportingRole=RolesManager.getRolesManager().searchRole(reportingTo);
		if(reportingRole==null)
		{
			System.out.println("Reporting Role Not Found Try Again");
			addSubRole();
		}else {
			reportingRole.addSubordinateRole(subRole);
			subRole.setReportingTo(reportingRole);
			RolesManager.getRolesManager().addRole(subRole);
		}
	}
	
	Operation();
}
public static void displayRoles() {
	ArrayList<Role>displayingRoles= RolesManager.getRolesManager().getRoles();
	for (int i = 0; i < displayingRoles.size(); i++) {
		System.out.println(displayingRoles.get(i).getName());
	}
}
public static void deleteRoles() {
	System.out.println("Enter the role to be deleted : ");
	String deletingRole=scan.next();
	Role searchedDeletingRole=RolesManager.getRolesManager().searchRole(deletingRole);
	if(searchedDeletingRole.equals(null))
	{
		System.out.println("Role does not exists ,Try again");
		deleteRoles();
	}else {
		System.out.println("Enter the role to be transferred :");
		String changingParent=scan.next();
		Role searchedTransferRole=RolesManager.getRolesManager().searchRole(changingParent);
		if(searchedTransferRole.equals(null))
		{
			System.out.println("Role does not exists ,Try again");
			deleteRoles();
		}else {
			ArrayList<Role> subordinates=searchedDeletingRole.getSubOrdinateRoles();
			for (int i = 0; i < subordinates.size(); i++) {
				subordinates.get(i).setReportingTo(searchedTransferRole);
			}
			searchedTransferRole.addSubordinateRoles(subordinates);
			searchedDeletingRole.getReporting().removeSubordinate(searchedDeletingRole);
			RolesManager.getRolesManager().removeRole(searchedDeletingRole);
		}
	}
}

public static void addUser() {
	System.out.println("Enter User Name : ");
	String userName = scan.next();
	System.out.println("Enter Role : ");
	String userRole = scan.next();
	Role searchedUserRole=RolesManager.getRolesManager().searchRole(userRole);
	if(searchedUserRole.equals(null)) {
		System.out.println("Entered User Role does not exists ,Try again");
		addUser();
	}else {
		User addingUser= new User(userName,searchedUserRole);
		UsersManager.USERS.put(userName, searchedUserRole);
		Operation();
	}
}
/*public static void displayUser() {
	HashMap<String, Role>displayingUser=UsersManager.getUsersManager().getUser();
	for (int i = 0; i < displayingUser.size(); i++) {
		System.out.println(displayingUser.get(i).g);
		Operation();
	}
	
}*/
public static void Operation(){
    int choice=0;
     System.out.println("Operation to be performed.");
     System.out.println("(1) Add Sub Role.");
     System.out.println("(2) Display Roles.");
     System.out.println("(3) Delete Roles.");
     System.out.println("(4) Add User.");
     /*System.out.println("(5) Display Users.");
     System.out.println("(6) Display User and Sub Users.");
     System.out.println("(7) Delete Users and Sub Users.");
     System.out.println("(8) Number of User from Top.");
     System.out.println("(9) Height of Role Hierarchy.");
     System.out.println("(10) Common boss of users");*/
     System.out.println("(0) Exit.");

     choice = scan.nextInt();

     switch (choice){
         case 1 :
             addSubRole();
             break;
         case 2 :
             displayRoles();
             break;
         case 3 :
             deleteRoles();
             break;
         case 4 :
             addUser();
             break;
        /* case 5 :
             displayUser();
             break;
         case 6 :
             displayUserAndSubUser();
             break;
         case 7 :
             deleteUsersAndSubUsers();
             break;
         case 8 :
             numberOfUserFromTop();
             break;
         case 9 :
             heightOfRoleHierarchy();
             break;
         case 10 :
             commonBossOfUsers();
             break;*/
            
         case 0 :
             break;
         default:
             System.out.println("Invalid Operation");
             Operation();
             break;
     }
}

/*public static void commonBossOfUsers() {
	// TODO Auto-generated method stub
	
}
public static void heightOfRoleHierarchy() {
	// TODO Auto-generated method stub
	
}
public static void numberOfUserFromTop() {
	// TODO Auto-generated method stub
	
}
public static void deleteUsersAndSubUsers() {
	// TODO Auto-generated method stub
	
}
public static void displayUserAndSubUser() {
	
}
*/


}