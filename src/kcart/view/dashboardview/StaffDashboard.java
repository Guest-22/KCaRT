package kcart.view.dashboardview;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import kcart.dao.BillingDAO;
import kcart.dao.RentalDAO;
import kcart.daoimpl.RentalDAOImpl;
import kcart.model.Rental;
import kcart.util.ActiveSession;
import kcart.util.Message;
import kcart.util.TableStyleUtil;
import kcart.util.StatusCellRenderer;
import kcart.view.Login;
import kcart.view.customerview.CustomerMenu;
import kcart.view.carview.CarMenu;
import kcart.view.rentalview.RentalMenu;
import kcart.view.returnview.ReturnMenu;
import kcart.view.billingview.BillingMenu;
import kcart.view.userview.UserMenu;
import kcart.dao.CarDAO;
import kcart.dao.ReturnDAO;
import kcart.daoimpl.BillingDAOImpl;
import kcart.daoimpl.CarDAOImpl;
import kcart.daoimpl.ReturnDAOImpl;

public class StaffDashboard extends javax.swing.JFrame {

    public StaffDashboard() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        lblWelcome1.setText(ActiveSession.loggedInUsername);
        applyRoleRestrictions();

        populateOverview();
        styleUpcomingPickupsAndDueReturns();
        populateUpcomingPickups();
        populateDueReturns();
    }

    private void applyRoleRestrictions() {
        // Hide Admin-only buttons for Staff.
        if ("Staff".equalsIgnoreCase(ActiveSession.role)) {
            btnReturn.setVisible(false);
            btnUser.setVisible(false);
        }
    }

    // Populate the Staff Dashboard overview.
    private void populateOverview() {
        CarDAO carDAO = new CarDAOImpl();
        lblAvailableCarsCount.setText(
                String.valueOf(carDAO.getAvailableCarCount())
        );

        RentalDAO rentalDAO = new RentalDAOImpl();
        lblActiveRentalsCount.setText(
                String.valueOf(rentalDAO.getActiveRentalCount())
        );
        lblPendingRentalsCount.setText(
                String.valueOf(rentalDAO.getPendingRentalCount())
        );
        lblLateRentalsCount.setText(
                String.valueOf(rentalDAO.getLateRentalCount())
        );
        
        ReturnDAO returnDAO = new ReturnDAOImpl();
        lblTodaysReturnCount.setText(
                String.valueOf(returnDAO.getTodaysReturnCount())
        );
        
        BillingDAO billingDAO = new BillingDAOImpl();
        lblTodaysPaymentsCount.setText(
                String.format("₱%,.2f", billingDAO.getTodaysPayment())
        );
    }

    private void styleUpcomingPickupsAndDueReturns() {
        TableStyleUtil.applyBlackWhiteStyle(tblRecord1);
        TableStyleUtil.applyBlackWhiteStyle(tblRecord2);

        StatusCellRenderer renderer = new StatusCellRenderer(4, StatusCellRenderer.StatusType.RENTAL);
        for (int i = 0; i < tblRecord2.getColumnCount(); i++) {
            tblRecord2.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    // Populate the Upcoming Pickups table.
    private void populateUpcomingPickups() {
        DefaultTableModel model = (DefaultTableModel) tblRecord1.getModel();

        model.setRowCount(0);

        try {
            RentalDAO rentalDAO = new RentalDAOImpl();
            List<Rental> rentals = rentalDAO.getUpcomingPickups();

            // Format date and time.
            java.time.format.DateTimeFormatter dateTimeFormat
                    = java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy | hh:mm a");

            for (Rental r : rentals) {
                Object[] row = {
                    r.getRentalId(),
                    r.getCustomerName(),
                    r.getCarInfo(),
                    r.getStartDate() != null
                    ? r.getStartDate().format(dateTimeFormat) : "—"
                };

                model.addRow(row);
            }

        } catch (Exception e) {
            Message.error("Error loading upcoming pickups:\n" + e.getMessage());
        }
    }

    // Populate the Due Returns table.
    private void populateDueReturns() {
        DefaultTableModel model = (DefaultTableModel) tblRecord2.getModel();

        model.setRowCount(0);

        try {
            RentalDAO rentalDAO = new RentalDAOImpl();
            List<Rental> rentals = rentalDAO.getDueReturns();

            // Format date and time.
            java.time.format.DateTimeFormatter dateTimeFormat
                    = java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy | hh:mm a");

            for (Rental r : rentals) {
                Object[] row = {
                    r.getRentalId(),
                    r.getCustomerName(),
                    r.getCarInfo(),
                    r.getExpectedReturnDate() != null
                    ? r.getExpectedReturnDate().format(dateTimeFormat) : "—",
                    r.getRentalStatus()
                };

                model.addRow(row);
            }

        } catch (Exception e) {
            Message.error("Error loading due returns:\n" + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlSideNav = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnCar = new javax.swing.JButton();
        btnRental = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        btnBilling = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblWelcome = new javax.swing.JLabel();
        lblWelcome1 = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();
        pnlOverview = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        pnlLateRentals = new javax.swing.JPanel();
        lblLateRentalsTitle = new javax.swing.JLabel();
        lblLateRentalsCount = new javax.swing.JLabel();
        pnlTodaysReturn = new javax.swing.JPanel();
        lblTodaysReturnTitle = new javax.swing.JLabel();
        lblTodaysReturnCount = new javax.swing.JLabel();
        pnlPendingRentals = new javax.swing.JPanel();
        lblPendingRentalsTitle = new javax.swing.JLabel();
        lblPendingRentalsCount = new javax.swing.JLabel();
        pnlActiveRentals = new javax.swing.JPanel();
        lblActiveRentalsTitle = new javax.swing.JLabel();
        lblActiveRentalsCount = new javax.swing.JLabel();
        pnlAvailableCars = new javax.swing.JPanel();
        lblAvailableCarsTitle = new javax.swing.JLabel();
        lblAvailableCarsCount = new javax.swing.JLabel();
        pnlTotalEarnings = new javax.swing.JPanel();
        lblTodaysPaymentsTitle = new javax.swing.JLabel();
        lblTodaysPaymentsCount = new javax.swing.JLabel();
        pnlUpcomingPickups = new javax.swing.JPanel();
        lblHeader1 = new javax.swing.JLabel();
        scrlRecord1 = new javax.swing.JScrollPane();
        tblRecord1 = new javax.swing.JTable();
        pnlDueReturns = new javax.swing.JPanel();
        lblHeader2 = new javax.swing.JLabel();
        scrlRecord2 = new javax.swing.JScrollPane();
        tblRecord2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KCaRT | Dashboard");
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(204, 204, 204));
        pnlMain.setPreferredSize(new java.awt.Dimension(1200, 600));

        pnlSideNav.setBackground(new java.awt.Color(0, 0, 0));

        pnlMenu.setBackground(new java.awt.Color(0, 0, 0));

        btnDashboard.setBackground(new java.awt.Color(51, 51, 51));
        btnDashboard.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.setFocusable(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        btnCustomer.setBackground(new java.awt.Color(0, 0, 0));
        btnCustomer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btnCustomer.setText("Customer");
        btnCustomer.setFocusable(false);
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnCar.setBackground(new java.awt.Color(0, 0, 0));
        btnCar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCar.setForeground(new java.awt.Color(255, 255, 255));
        btnCar.setText("Car");
        btnCar.setFocusable(false);
        btnCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarActionPerformed(evt);
            }
        });

        btnRental.setBackground(new java.awt.Color(0, 0, 0));
        btnRental.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRental.setForeground(new java.awt.Color(255, 255, 255));
        btnRental.setText("Rental");
        btnRental.setFocusable(false);
        btnRental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRentalActionPerformed(evt);
            }
        });

        btnReturn.setBackground(new java.awt.Color(0, 0, 0));
        btnReturn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(255, 255, 255));
        btnReturn.setText("Return");
        btnReturn.setFocusable(false);
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        btnBilling.setBackground(new java.awt.Color(0, 0, 0));
        btnBilling.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBilling.setForeground(new java.awt.Color(255, 255, 255));
        btnBilling.setText("Biilling");
        btnBilling.setFocusable(false);
        btnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillingActionPerformed(evt);
            }
        });

        btnUser.setBackground(new java.awt.Color(0, 0, 0));
        btnUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUser.setForeground(new java.awt.Color(255, 255, 255));
        btnUser.setText("User");
        btnUser.setFocusable(false);
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(0, 0, 0));
        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setFocusable(false);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addComponent(btnCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRental, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBilling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRental, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBilling, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblWelcome.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcome.setText("Welcome,");
        lblWelcome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblWelcome.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        lblWelcome1.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        lblWelcome1.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcome1.setText("null");
        lblWelcome1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblWelcome1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlSideNavLayout = new javax.swing.GroupLayout(pnlSideNav);
        pnlSideNav.setLayout(pnlSideNavLayout);
        pnlSideNavLayout.setHorizontalGroup(
            pnlSideNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSideNavLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlSideNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSideNavLayout.setVerticalGroup(
            pnlSideNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideNavLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblWelcome)
                .addGap(5, 5, 5)
                .addComponent(lblWelcome1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));

        pnlOverview.setBackground(new java.awt.Color(255, 255, 255));
        pnlOverview.setForeground(new java.awt.Color(204, 204, 204));

        lblHeader.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(0, 0, 0));
        lblHeader.setText("Overview");
        lblHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnlLateRentals.setBackground(new java.awt.Color(0, 0, 0));

        lblLateRentalsTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblLateRentalsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblLateRentalsTitle.setText("Late Rentals");

        lblLateRentalsCount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblLateRentalsCount.setForeground(new java.awt.Color(255, 255, 255));
        lblLateRentalsCount.setText("0");

        javax.swing.GroupLayout pnlLateRentalsLayout = new javax.swing.GroupLayout(pnlLateRentals);
        pnlLateRentals.setLayout(pnlLateRentalsLayout);
        pnlLateRentalsLayout.setHorizontalGroup(
            pnlLateRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLateRentalsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLateRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLateRentalsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(lblLateRentalsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlLateRentalsLayout.setVerticalGroup(
            pnlLateRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLateRentalsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLateRentalsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLateRentalsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        pnlTodaysReturn.setBackground(new java.awt.Color(0, 0, 0));

        lblTodaysReturnTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTodaysReturnTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTodaysReturnTitle.setText("Today's Returns");

        lblTodaysReturnCount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTodaysReturnCount.setForeground(new java.awt.Color(255, 255, 255));
        lblTodaysReturnCount.setText("0");

        javax.swing.GroupLayout pnlTodaysReturnLayout = new javax.swing.GroupLayout(pnlTodaysReturn);
        pnlTodaysReturn.setLayout(pnlTodaysReturnLayout);
        pnlTodaysReturnLayout.setHorizontalGroup(
            pnlTodaysReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodaysReturnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTodaysReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTodaysReturnTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(lblTodaysReturnCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTodaysReturnLayout.setVerticalGroup(
            pnlTodaysReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodaysReturnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTodaysReturnTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTodaysReturnCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        pnlPendingRentals.setBackground(new java.awt.Color(0, 0, 0));

        lblPendingRentalsTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblPendingRentalsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblPendingRentalsTitle.setText("Pending Rentals");

        lblPendingRentalsCount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPendingRentalsCount.setForeground(new java.awt.Color(255, 255, 255));
        lblPendingRentalsCount.setText("0");

        javax.swing.GroupLayout pnlPendingRentalsLayout = new javax.swing.GroupLayout(pnlPendingRentals);
        pnlPendingRentals.setLayout(pnlPendingRentalsLayout);
        pnlPendingRentalsLayout.setHorizontalGroup(
            pnlPendingRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendingRentalsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPendingRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPendingRentalsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(lblPendingRentalsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPendingRentalsLayout.setVerticalGroup(
            pnlPendingRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendingRentalsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPendingRentalsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPendingRentalsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        pnlActiveRentals.setBackground(new java.awt.Color(0, 0, 0));

        lblActiveRentalsTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblActiveRentalsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblActiveRentalsTitle.setText("Active Rentals");

        lblActiveRentalsCount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblActiveRentalsCount.setForeground(new java.awt.Color(255, 255, 255));
        lblActiveRentalsCount.setText("0");

        javax.swing.GroupLayout pnlActiveRentalsLayout = new javax.swing.GroupLayout(pnlActiveRentals);
        pnlActiveRentals.setLayout(pnlActiveRentalsLayout);
        pnlActiveRentalsLayout.setHorizontalGroup(
            pnlActiveRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActiveRentalsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActiveRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblActiveRentalsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(lblActiveRentalsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlActiveRentalsLayout.setVerticalGroup(
            pnlActiveRentalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActiveRentalsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblActiveRentalsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblActiveRentalsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        pnlAvailableCars.setBackground(new java.awt.Color(0, 0, 0));

        lblAvailableCarsTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblAvailableCarsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailableCarsTitle.setText("Available Cars");

        lblAvailableCarsCount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAvailableCarsCount.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailableCarsCount.setText("0");

        javax.swing.GroupLayout pnlAvailableCarsLayout = new javax.swing.GroupLayout(pnlAvailableCars);
        pnlAvailableCars.setLayout(pnlAvailableCarsLayout);
        pnlAvailableCarsLayout.setHorizontalGroup(
            pnlAvailableCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvailableCarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAvailableCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvailableCarsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAvailableCarsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAvailableCarsLayout.setVerticalGroup(
            pnlAvailableCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvailableCarsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvailableCarsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAvailableCarsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        pnlTotalEarnings.setBackground(new java.awt.Color(0, 0, 0));

        lblTodaysPaymentsTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTodaysPaymentsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTodaysPaymentsTitle.setText("Today's Payments");

        lblTodaysPaymentsCount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTodaysPaymentsCount.setForeground(new java.awt.Color(255, 255, 255));
        lblTodaysPaymentsCount.setText("0");

        javax.swing.GroupLayout pnlTotalEarningsLayout = new javax.swing.GroupLayout(pnlTotalEarnings);
        pnlTotalEarnings.setLayout(pnlTotalEarningsLayout);
        pnlTotalEarningsLayout.setHorizontalGroup(
            pnlTotalEarningsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalEarningsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTotalEarningsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTodaysPaymentsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTodaysPaymentsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTotalEarningsLayout.setVerticalGroup(
            pnlTotalEarningsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalEarningsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTodaysPaymentsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTodaysPaymentsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        pnlUpcomingPickups.setBackground(new java.awt.Color(255, 255, 255));

        lblHeader1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblHeader1.setForeground(new java.awt.Color(0, 0, 0));
        lblHeader1.setText("Upcoming Pickups");
        lblHeader1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblRecord1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Rental ID", "Customer", "Car", "Start Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRecord1.setSelectionBackground(new java.awt.Color(74, 144, 226));
        tblRecord1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrlRecord1.setViewportView(tblRecord1);

        javax.swing.GroupLayout pnlUpcomingPickupsLayout = new javax.swing.GroupLayout(pnlUpcomingPickups);
        pnlUpcomingPickups.setLayout(pnlUpcomingPickupsLayout);
        pnlUpcomingPickupsLayout.setHorizontalGroup(
            pnlUpcomingPickupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpcomingPickupsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpcomingPickupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpcomingPickupsLayout.createSequentialGroup()
                        .addComponent(lblHeader1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scrlRecord1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)))
        );
        pnlUpcomingPickupsLayout.setVerticalGroup(
            pnlUpcomingPickupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpcomingPickupsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeader1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlRecord1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDueReturns.setBackground(new java.awt.Color(255, 255, 255));

        lblHeader2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblHeader2.setForeground(new java.awt.Color(0, 0, 0));
        lblHeader2.setText("Due Returns");
        lblHeader2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblRecord2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Rental ID", "Customer", "Car", "Return Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRecord2.setSelectionBackground(new java.awt.Color(74, 144, 226));
        tblRecord2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrlRecord2.setViewportView(tblRecord2);

        javax.swing.GroupLayout pnlDueReturnsLayout = new javax.swing.GroupLayout(pnlDueReturns);
        pnlDueReturns.setLayout(pnlDueReturnsLayout);
        pnlDueReturnsLayout.setHorizontalGroup(
            pnlDueReturnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDueReturnsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDueReturnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrlRecord2)
                    .addGroup(pnlDueReturnsLayout.createSequentialGroup()
                        .addComponent(lblHeader2)
                        .addContainerGap(370, Short.MAX_VALUE))))
        );
        pnlDueReturnsLayout.setVerticalGroup(
            pnlDueReturnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDueReturnsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeader2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlRecord2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlOverviewLayout = new javax.swing.GroupLayout(pnlOverview);
        pnlOverview.setLayout(pnlOverviewLayout);
        pnlOverviewLayout.setHorizontalGroup(
            pnlOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOverviewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeader)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlOverviewLayout.createSequentialGroup()
                .addGroup(pnlOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTotalEarnings, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAvailableCars, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlActiveRentals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPendingRentals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlLateRentals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlTodaysReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOverviewLayout.createSequentialGroup()
                .addComponent(pnlUpcomingPickups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDueReturns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOverviewLayout.setVerticalGroup(
            pnlOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOverviewLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLateRentals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTodaysReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPendingRentals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAvailableCars, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlActiveRentals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTotalEarnings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDueReturns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlUpcomingPickups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOverview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addComponent(pnlOverview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlSideNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSideNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed

    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        new CustomerMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarActionPerformed
        new CarMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCarActionPerformed

    private void btnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillingActionPerformed
        new BillingMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBillingActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        new UserMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        new Login().setVisible(true);
        ActiveSession.clearSession();
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentalActionPerformed
        new RentalMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRentalActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        new ReturnMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        populateOverview();
        populateUpcomingPickups();
        populateDueReturns();
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBilling;
    private javax.swing.JButton btnCar;
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRental;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnUser;
    private javax.swing.JLabel lblActiveRentalsCount;
    private javax.swing.JLabel lblActiveRentalsTitle;
    private javax.swing.JLabel lblAvailableCarsCount;
    private javax.swing.JLabel lblAvailableCarsTitle;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblHeader1;
    private javax.swing.JLabel lblHeader2;
    private javax.swing.JLabel lblLateRentalsCount;
    private javax.swing.JLabel lblLateRentalsTitle;
    private javax.swing.JLabel lblPendingRentalsCount;
    private javax.swing.JLabel lblPendingRentalsTitle;
    private javax.swing.JLabel lblTodaysPaymentsCount;
    private javax.swing.JLabel lblTodaysPaymentsTitle;
    private javax.swing.JLabel lblTodaysReturnCount;
    private javax.swing.JLabel lblTodaysReturnTitle;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblWelcome1;
    private javax.swing.JPanel pnlActiveRentals;
    private javax.swing.JPanel pnlAvailableCars;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDueReturns;
    private javax.swing.JPanel pnlLateRentals;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlOverview;
    private javax.swing.JPanel pnlPendingRentals;
    private javax.swing.JPanel pnlSideNav;
    private javax.swing.JPanel pnlTodaysReturn;
    private javax.swing.JPanel pnlTotalEarnings;
    private javax.swing.JPanel pnlUpcomingPickups;
    private javax.swing.JScrollPane scrlRecord1;
    private javax.swing.JScrollPane scrlRecord2;
    private javax.swing.JTable tblRecord1;
    private javax.swing.JTable tblRecord2;
    // End of variables declaration//GEN-END:variables
}
