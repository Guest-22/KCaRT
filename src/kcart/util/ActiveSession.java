package kcart.util;

// Active session tracking.
// All collected infos will serve as a reference to cater the current user.
public class ActiveSession {
    // Store account infos.
    public static int loggedInUserID; // 'ID' from tblUser to track the info of the active user.
    public static String loggedInUsername; // Username for display and tracking.
    public static String role; // 'Staff' or 'Admin'.

    // Clears session's data after logout.
    public static void clearSession() {
        loggedInUserID = 0;
        loggedInUsername = "";
        role = null;
    }
}