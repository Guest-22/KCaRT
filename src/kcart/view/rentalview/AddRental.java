package kcart.view.rentalview;

import java.util.Date;
import kcart.dao.CarDAO;
import kcart.dao.CustomerDAO;
import kcart.dao.RentalDAO;
import kcart.daoimpl.CarDAOImpl;
import kcart.daoimpl.CustomerDAOImpl;
import kcart.daoimpl.RentalDAOImpl;
import kcart.model.Car;
import kcart.model.Customer;
import kcart.model.Rental;
import kcart.util.Message;
import kcart.util.ActiveSession;

public class AddRental extends javax.swing.JFrame {

    private int carId;

    public AddRental() {
        initComponents();
    }

    public AddRental(int carId) {
        initComponents();

        this.carId = carId;
        loadCarDetails();
    }

    // Retrieve car info. based-off the passed argument in CarMenu form.
    private void loadCarDetails() {
        CarDAO carDao = new CarDAOImpl();
        Car car = carDao.getCarInfo(carId);

        if (car != null) {
            txtCarId.setText(String.valueOf(car.getCarId()));
            txtBrand.setText(car.getBrand());
            txtModel.setText(car.getModel());
            cmbType.setSelectedItem(car.getCarType());
            txtYear.setText(String.valueOf(car.getYear()));
            txtDailyRate.setText(String.valueOf(car.getDailyRate()));
        } else {
            Message.error("Car details not found for ID: " + carId);
        }
    }
    
    // Retrieve car info. based on the inputted Car ID.
    private void loadCarDetails(int carId) {
        CarDAO carDao = new CarDAOImpl();
        Car car = carDao.getCarInfo(carId);

        if (car != null) {
            txtCarId.setText(String.valueOf(car.getCarId()));
            txtBrand.setText(car.getBrand());
            txtModel.setText(car.getModel());
            cmbType.setSelectedItem(car.getCarType());
            txtYear.setText(String.valueOf(car.getYear()));
            txtDailyRate.setText(String.valueOf(car.getDailyRate()));
        } else {
            Message.error("Car details not found for ID: " + carId);
        }
    }

    // Retrieve Customer informations.
    private void loadCustomerDetails(int customerId) {
        CustomerDAO customerDao = new CustomerDAOImpl();
        Customer customer = customerDao.getCustomerInfo(customerId);

        if (customer != null) {
            // Name format: LastName, FirstName M.I.
            String middleInitial = (customer.getMiddleName() != null && !customer.getMiddleName().isEmpty())
                    ? customer.getMiddleName().substring(0, 1) + "."
                    : "";

            String formattedName = customer.getLastName() + ", " + customer.getFirstName() + " " + middleInitial;

            txtCustomerName.setText(formattedName);
            txtContact.setText(customer.getContactNo());
        } else {
            Message.error("Customer not found for ID: " + customerId);
        }
    }

    // Uses start and return date to calculate total no. of days.
    private long calculateDaysBetween(Date startDate, Date returnDate) {
        if (startDate == null || returnDate == null) {
            return 0;
        }
        long diffInMillis = returnDate.getTime() - startDate.getTime();
        return diffInMillis / (1000 * 60 * 60 * 24);
    }

    // Update rental days; verifies if dates are valid.
    private void updateRentalDays() {
        Date startDate = txtStartDate.getDate();
        Date returnDate = txtReturnDate.getDate();

        long days = calculateDaysBetween(startDate, returnDate);

        if (days > 0) {
            txtNoOfDays.setText(String.valueOf(days));
            calculateTotalCost(); // Updates cost.
        } else {
            txtNoOfDays.setText("0");
            txtCost.setText("0");
            if (startDate != null && returnDate != null) {
                txtReturnDate.setDate(null); // Clear invalid (past) return date.
                Message.error("Return date must be after start date.");
            }
        }
    }

    // Calculate total cost based on no. of days and daily rate of Car reference.
    private void calculateTotalCost() {
        try {
            double dailyRate = Double.parseDouble(txtDailyRate.getText());
            int days = Integer.parseInt(txtNoOfDays.getText());
            double totalCost = dailyRate * days;
            txtCost.setText(String.valueOf(totalCost));
        } catch (NumberFormatException e) {
            Message.error("Invalid number format for daily rate or days.");
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
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
        pnlContent1 = new javax.swing.JPanel();
        lblStartDate = new javax.swing.JLabel();
        lblNoOfDays = new javax.swing.JLabel();
        lblHeader = new javax.swing.JLabel();
        lblRentalID = new javax.swing.JLabel();
        lblReturnDate = new javax.swing.JLabel();
        lblCost = new javax.swing.JLabel();
        lblRentalDetails = new javax.swing.JLabel();
        txtRentalID = new javax.swing.JTextField();
        txtNoOfDays = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        lblExtraPad1 = new javax.swing.JLabel();
        txtStartDate = new com.toedter.calendar.JDateChooser();
        txtReturnDate = new com.toedter.calendar.JDateChooser();
        pnlContent2 = new javax.swing.JPanel();
        lblContact = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        lblCustomerDetails = new javax.swing.JLabel();
        lblCustomerId = new javax.swing.JLabel();
        txtCustomerId = new javax.swing.JTextField();
        txtCustomerName = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        lblExtraPad2 = new javax.swing.JLabel();
        txtDailyRate = new javax.swing.JTextField();
        lblCarDetails = new javax.swing.JLabel();
        lblBrand = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        lblModel = new javax.swing.JLabel();
        lblDailyRate = new javax.swing.JLabel();
        txtCarId = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        lblCarId = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<>();
        pnlContent3 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));

        pnlMain.setBackground(new java.awt.Color(0, 0, 0));
        pnlMain.setPreferredSize(new java.awt.Dimension(1000, 600));

        pnlContent1.setBackground(new java.awt.Color(0, 0, 0));

        lblStartDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblStartDate.setForeground(new java.awt.Color(255, 255, 255));
        lblStartDate.setText("Start Date:");
        lblStartDate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblNoOfDays.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNoOfDays.setForeground(new java.awt.Color(255, 255, 255));
        lblNoOfDays.setText("No. of Days:");
        lblNoOfDays.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblHeader.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setText("Add Reservation");
        lblHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblRentalID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRentalID.setForeground(new java.awt.Color(255, 255, 255));
        lblRentalID.setText("Rental ID:");
        lblRentalID.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblReturnDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblReturnDate.setForeground(new java.awt.Color(255, 255, 255));
        lblReturnDate.setText("Return Date:");
        lblReturnDate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCost.setForeground(new java.awt.Color(255, 255, 255));
        lblCost.setText("Cost:");
        lblCost.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblRentalDetails.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRentalDetails.setForeground(new java.awt.Color(255, 255, 255));
        lblRentalDetails.setText("Rental Details:");
        lblRentalDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtRentalID.setEditable(false);
        txtRentalID.setBackground(new java.awt.Color(204, 204, 204));
        txtRentalID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRentalID.setForeground(new java.awt.Color(0, 0, 0));
        txtRentalID.setEnabled(false);

        txtNoOfDays.setEditable(false);
        txtNoOfDays.setBackground(new java.awt.Color(204, 204, 204));
        txtNoOfDays.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNoOfDays.setForeground(new java.awt.Color(0, 0, 0));
        txtNoOfDays.setText("0");
        txtNoOfDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoOfDaysActionPerformed(evt);
            }
        });

        txtCost.setEditable(false);
        txtCost.setBackground(new java.awt.Color(204, 204, 204));
        txtCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCost.setForeground(new java.awt.Color(0, 0, 0));
        txtCost.setText("0");

        lblExtraPad1.setBackground(new java.awt.Color(255, 255, 255));
        lblExtraPad1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblExtraPad1.setForeground(new java.awt.Color(255, 255, 255));

        txtStartDate.setBackground(new java.awt.Color(255, 255, 255));
        txtStartDate.setForeground(new java.awt.Color(0, 0, 0));
        txtStartDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtStartDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtStartDatePropertyChange(evt);
            }
        });

        txtReturnDate.setBackground(new java.awt.Color(255, 255, 255));
        txtReturnDate.setForeground(new java.awt.Color(0, 0, 0));
        txtReturnDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtReturnDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtReturnDatePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnlContent1Layout = new javax.swing.GroupLayout(pnlContent1);
        pnlContent1.setLayout(pnlContent1Layout);
        pnlContent1Layout.setHorizontalGroup(
            pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContent1Layout.createSequentialGroup()
                        .addComponent(lblHeader)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContent1Layout.createSequentialGroup()
                        .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRentalDetails)
                            .addGroup(pnlContent1Layout.createSequentialGroup()
                                .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStartDate)
                                    .addComponent(lblReturnDate)
                                    .addComponent(lblRentalID))
                                .addGap(22, 22, 22)
                                .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRentalID, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                    .addComponent(txtStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtReturnDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContent1Layout.createSequentialGroup()
                                .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNoOfDays)
                                    .addComponent(lblCost, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNoOfDays, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                                    .addComponent(txtCost)))
                            .addComponent(lblExtraPad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))))
        );
        pnlContent1Layout.setVerticalGroup(
            pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContent1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblRentalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContent1Layout.createSequentialGroup()
                        .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRentalID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRentalID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(pnlContent1Layout.createSequentialGroup()
                        .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlContent1Layout.createSequentialGroup()
                                .addComponent(txtNoOfDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCost)
                            .addGroup(pnlContent1Layout.createSequentialGroup()
                                .addComponent(lblNoOfDays)
                                .addGap(28, 28, 28)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblReturnDate)
                            .addGroup(pnlContent1Layout.createSequentialGroup()
                                .addGroup(pnlContent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(lblExtraPad1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlContent2.setBackground(new java.awt.Color(0, 0, 0));

        lblContact.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblContact.setForeground(new java.awt.Color(255, 255, 255));
        lblContact.setText("Contact:");
        lblContact.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblFullName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFullName.setForeground(new java.awt.Color(255, 255, 255));
        lblFullName.setText("Name:");
        lblFullName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCustomerDetails.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCustomerDetails.setForeground(new java.awt.Color(255, 255, 255));
        lblCustomerDetails.setText("Customer Details:");
        lblCustomerDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCustomerId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCustomerId.setForeground(new java.awt.Color(255, 255, 255));
        lblCustomerId.setText("Customer ID:");
        lblCustomerId.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtCustomerId.setBackground(new java.awt.Color(255, 255, 255));
        txtCustomerId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCustomerId.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerIdActionPerformed(evt);
            }
        });

        txtCustomerName.setEditable(false);
        txtCustomerName.setBackground(new java.awt.Color(204, 204, 204));
        txtCustomerName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCustomerName.setForeground(new java.awt.Color(0, 0, 0));

        txtContact.setEditable(false);
        txtContact.setBackground(new java.awt.Color(204, 204, 204));
        txtContact.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtContact.setForeground(new java.awt.Color(0, 0, 0));

        lblExtraPad2.setBackground(new java.awt.Color(255, 255, 255));
        lblExtraPad2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblExtraPad2.setForeground(new java.awt.Color(255, 255, 255));

        txtDailyRate.setEditable(false);
        txtDailyRate.setBackground(new java.awt.Color(204, 204, 204));
        txtDailyRate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDailyRate.setForeground(new java.awt.Color(0, 0, 0));

        lblCarDetails.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCarDetails.setForeground(new java.awt.Color(255, 255, 255));
        lblCarDetails.setText("Car Details:");
        lblCarDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblBrand.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblBrand.setText("Brand:");
        lblBrand.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblYear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblYear.setForeground(new java.awt.Color(255, 255, 255));
        lblYear.setText("Year:");
        lblYear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblModel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModel.setForeground(new java.awt.Color(255, 255, 255));
        lblModel.setText("Model:");
        lblModel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblDailyRate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDailyRate.setForeground(new java.awt.Color(255, 255, 255));
        lblDailyRate.setText("Daily Rate:");
        lblDailyRate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtCarId.setBackground(new java.awt.Color(255, 255, 255));
        txtCarId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCarId.setForeground(new java.awt.Color(0, 0, 0));
        txtCarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarIdActionPerformed(evt);
            }
        });

        txtBrand.setEditable(false);
        txtBrand.setBackground(new java.awt.Color(204, 204, 204));
        txtBrand.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBrand.setForeground(new java.awt.Color(0, 0, 0));

        txtYear.setEditable(false);
        txtYear.setBackground(new java.awt.Color(204, 204, 204));
        txtYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtYear.setForeground(new java.awt.Color(0, 0, 0));

        txtModel.setEditable(false);
        txtModel.setBackground(new java.awt.Color(204, 204, 204));
        txtModel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtModel.setForeground(new java.awt.Color(0, 0, 0));

        lblCarId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCarId.setForeground(new java.awt.Color(255, 255, 255));
        lblCarId.setText("Car ID:");
        lblCarId.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblType.setForeground(new java.awt.Color(255, 255, 255));
        lblType.setText("Type:");
        lblType.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cmbType.setBackground(new java.awt.Color(204, 204, 204));
        cmbType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbType.setForeground(new java.awt.Color(0, 0, 0));
        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sedan", "SUV", "Hatchback", "MPV", "Van", "Pickup" }));

        javax.swing.GroupLayout pnlContent2Layout = new javax.swing.GroupLayout(pnlContent2);
        pnlContent2.setLayout(pnlContent2Layout);
        pnlContent2Layout.setHorizontalGroup(
            pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContent2Layout.createSequentialGroup()
                        .addComponent(lblCarDetails)
                        .addGap(831, 831, 831))
                    .addGroup(pnlContent2Layout.createSequentialGroup()
                        .addComponent(lblCustomerDetails)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContent2Layout.createSequentialGroup()
                        .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContent2Layout.createSequentialGroup()
                                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCarId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblModel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCarId, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBrand, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtModel, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblDailyRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDailyRate, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                                    .addComponent(txtYear)
                                    .addComponent(cmbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContent2Layout.createSequentialGroup()
                                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFullName)
                                    .addComponent(lblCustomerId))
                                .addGap(24, 24, 24)
                                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCustomerId)
                                    .addComponent(txtCustomerName))
                                .addGap(18, 18, 18)
                                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlContent2Layout.createSequentialGroup()
                                        .addComponent(lblContact, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(txtContact))
                                    .addComponent(lblExtraPad2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(47, 47, 47))))
        );
        pnlContent2Layout.setVerticalGroup(
            pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent2Layout.createSequentialGroup()
                .addComponent(lblCustomerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContact)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFullName)
                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblExtraPad2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCarDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContent2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContent2Layout.createSequentialGroup()
                                .addComponent(lblCarId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lblBrand))
                            .addGroup(pnlContent2Layout.createSequentialGroup()
                                .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlContent2Layout.createSequentialGroup()
                                        .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblType)
                                            .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2))
                                    .addComponent(txtCarId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlContent2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlContent2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDailyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlContent2Layout.createSequentialGroup()
                                    .addComponent(lblYear)
                                    .addGap(5, 5, 5)
                                    .addComponent(lblDailyRate)))
                            .addGroup(pnlContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblModel)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContent3.setBackground(new java.awt.Color(0, 0, 0));

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(0, 0, 0));
        btnCancel.setText("Cancel");
        btnCancel.setBorder(null);
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(255, 255, 255));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 0, 0));
        btnAdd.setText("Add");
        btnAdd.setBorder(null);
        btnAdd.setFocusable(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContent3Layout = new javax.swing.GroupLayout(pnlContent3);
        pnlContent3.setLayout(pnlContent3Layout);
        pnlContent3Layout.setHorizontalGroup(
            pnlContent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent3Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContent3Layout.setVerticalGroup(
            pnlContent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContent3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlContent3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlContent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addComponent(pnlContent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtCustomerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerIdActionPerformed
        if (!txtCustomerId.getText().isEmpty()) {
            try {
                int customerId = Integer.parseInt(txtCustomerId.getText());
                loadCustomerDetails(customerId);
            } catch (NumberFormatException e) {
                Message.error("Invalid Customer ID format.");
            }
        }
    }//GEN-LAST:event_txtCustomerIdActionPerformed

    private void txtReturnDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtReturnDatePropertyChange
        if ("date".equals(evt.getPropertyName())) {
            updateRentalDays();
        }
    }//GEN-LAST:event_txtReturnDatePropertyChange

    private void txtStartDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtStartDatePropertyChange
        if ("date".equals(evt.getPropertyName())) {
            updateRentalDays();
        }
    }//GEN-LAST:event_txtStartDatePropertyChange

    private void txtCarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarIdActionPerformed
        if (!txtCarId.getText().isEmpty()) {
            try {
                int carId = Integer.parseInt(txtCarId.getText());
                // Get the inputted Car ID in textfield and retrieve the necessary details.
                loadCarDetails(carId); 
            } catch (NumberFormatException e) {
                Message.error("Invalid Car ID format.");
            }
        }
    }//GEN-LAST:event_txtCarIdActionPerformed

    private void txtNoOfDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoOfDaysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoOfDaysActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            if (txtCustomerId.getText().trim().isEmpty()
                    || txtCarId.getText().trim().isEmpty()
                    || txtStartDate.getDate() == null
                    || txtReturnDate.getDate() == null) {
                Message.error("Please fill in all required fields.");
                return;
            }

            // Verify Id reference if its an integer data type.
            if (!isInteger(txtCustomerId.getText().trim())) {
                Message.error("Customer ID must be a whole number.");
                return;
            }
            if (!isInteger(txtCarId.getText().trim())) {
                Message.error("Car ID must be a whole number.");
                return;
            }

            // Gets the ID reference and other inputted values.
            int customerId = Integer.parseInt(txtCustomerId.getText().trim());
            int carId = Integer.parseInt(txtCarId.getText().trim());
            java.sql.Date startDate = new java.sql.Date(txtStartDate.getDate().getTime());
            java.sql.Date returnDate = new java.sql.Date(txtReturnDate.getDate().getTime());
            int processedBy = ActiveSession.loggedInUserId;
            String rentalStatus = "Active";

            // Build Rental object using constructor.
            Rental rental = new Rental(
                    customerId,
                    carId,
                    processedBy,
                    startDate,
                    returnDate,
                    rentalStatus
            );

            RentalDAO rentalDao = new RentalDAOImpl();
            boolean success = rentalDao.addRental(rental);

            if (success) {
                Message.show("Rental added successfully!", "Success");
                this.dispose();
            } else {
                Message.error("Failed to add rental.");
            }
        } catch (Exception e) {
            // Message.error("Error adding rental:\n" + e.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

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
            java.util.logging.Logger.getLogger(AddRental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddRental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddRental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddRental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddRental().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblCarDetails;
    private javax.swing.JLabel lblCarId;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblCost;
    private javax.swing.JLabel lblCustomerDetails;
    private javax.swing.JLabel lblCustomerId;
    private javax.swing.JLabel lblDailyRate;
    private javax.swing.JLabel lblExtraPad1;
    private javax.swing.JLabel lblExtraPad2;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblNoOfDays;
    private javax.swing.JLabel lblRentalDetails;
    private javax.swing.JLabel lblRentalID;
    private javax.swing.JLabel lblReturnDate;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblYear;
    private javax.swing.JPanel pnlContent1;
    private javax.swing.JPanel pnlContent2;
    private javax.swing.JPanel pnlContent3;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtCarId;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtCustomerId;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtDailyRate;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtNoOfDays;
    private javax.swing.JTextField txtRentalID;
    private com.toedter.calendar.JDateChooser txtReturnDate;
    private com.toedter.calendar.JDateChooser txtStartDate;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
