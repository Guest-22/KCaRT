package kcart;

import kcart.view.Login;
import kcart.view.dashboardview.AdminDashboard;
import kcart.view.dashboardview.StaffDashboard;
import kcart.view.customerview.CustomerMenu;
import kcart.view.customerview.AddCustomer;
import kcart.view.customerview.EditCustomer;
import kcart.view.carview.CarMenu;
import kcart.view.carview.AddCar;
import kcart.view.carview.EditCar;
import kcart.view.rentalview.RentalMenu;
import kcart.view.rentalview.AddRental;
import kcart.view.rentalview.EditRental;
import kcart.view.rentalview.ProcessPickup;
import kcart.view.rentalview.ProcessReturn;
import kcart.view.returnview.ReturnMenu;
import kcart.view.billingview.BillingMenu;
import kcart.view.billingview.ViewInvoice;
import kcart.view.billingview.ViewPayment;
import kcart.view.userview.UserMenu;
import kcart.view.userview.AddUser;
import kcart.view.userview.EditUser;

public class KCaRT {

    public static void main(String[] args) {
        // Use (Ctrl + 7 shortcut) to inspect all of the variables used in this system project.
        // Shows the login JFrame by default.
        // Note: Preload the specific JFrame Dashboard below for layout testing; else use login for active session tracking of current user.

        // Pre-loads UI; avoid glitches.
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}