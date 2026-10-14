package kcart.daoimpl;

import kcart.dao.BillingDAO;
import kcart.model.Billing;
import kcart.util.DBConnection;
import kcart.util.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAOImpl implements BillingDAO {

    // Invoice table.
    private static final String TABLE_INVOICE = "tbl_invoice";
    private static final String COL_INVOICE_ID = "invoice_id";
    private static final String COL_RENTAL_ID = "rental_id";
    private static final String COL_RETURN_ID = "return_id";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_INVOICE_AMOUNT = "invoice_amount";
    private static final String COL_INVOICE_STATUS = "invoice_status";
    private static final String COL_INVOICE_DATE = "created_at";

    // Payment table.
    private static final String TABLE_PAYMENT = "tbl_payment";
    private static final String COL_PAYMENT_ID = "payment_id";
    private static final String COL_PAYMENT_INVOICE_ID = "invoice_id";
    private static final String COL_PROCESSED_BY = "processed_by";
    private static final String COL_PAY_METHOD = "pay_method";
    private static final String COL_PAY_AMOUNT = "pay_amount";
    private static final String COL_PAY_DATE = "created_at";

    private Connection conn;

    public BillingDAOImpl() {
        this.conn = DBConnection.getConnection(); // Establish connection.
    }

    // Inserts invoice record for reference.
    @Override
    public int addInvoice(Billing billing) {
        String sql = "INSERT INTO " + TABLE_INVOICE + " ("
                + COL_RENTAL_ID + ", "
                + COL_RETURN_ID + ", "
                + COL_DESCRIPTION + ", "
                + COL_INVOICE_AMOUNT + ", "
                + COL_INVOICE_STATUS + ") "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, billing.getRentalId());

            if (billing.getReturnId() != null) {
                stmt.setInt(2, billing.getReturnId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            stmt.setString(3, billing.getDescription());
            stmt.setDouble(4, billing.getInvoiceAmount());
            stmt.setString(5, billing.getInvoiceStatus());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            Message.error("Error adding invoice:\n" + e.getMessage());
        }

        return -1;
    }

    // Inserts payment record referencing invoice id.
    @Override
    public boolean addPayment(Billing billing, int invoiceId) {
        String sql = "INSERT INTO " + TABLE_PAYMENT + " ("
                + COL_PAYMENT_INVOICE_ID + ", "
                + COL_PROCESSED_BY + ", "
                + COL_PAY_METHOD + ", "
                + COL_PAY_AMOUNT + ") "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, invoiceId);
            stmt.setInt(2, billing.getProcessedBy());
            stmt.setString(3, billing.getPayMethod());
            stmt.setDouble(4, billing.getPayAmount());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            Message.error("Error adding payment:\n" + e.getMessage());
            return false;
        }
    }

    // Retrieve billing info for populating Billing Menu table.
    @Override
    public List<Billing> getAllBillings() {
        List<Billing> list = new ArrayList<>();

        String sql = "SELECT "
                + "i." + COL_INVOICE_ID + ", "
                + "i." + COL_RENTAL_ID + ", "
                + "i." + COL_DESCRIPTION + ", "
                + "i." + COL_INVOICE_AMOUNT + ", "
                + "i." + COL_INVOICE_STATUS + ", "
                + "p." + COL_PAY_DATE + " AS payment_date "
                + "FROM " + TABLE_INVOICE + " i "
                + "LEFT JOIN " + TABLE_PAYMENT + " p "
                + "ON i." + COL_INVOICE_ID + " = p." + COL_PAYMENT_INVOICE_ID;

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Billing b = new Billing(
                        rs.getInt(COL_INVOICE_ID),
                        rs.getInt(COL_RENTAL_ID),
                        rs.getString(COL_DESCRIPTION),
                        rs.getDouble(COL_INVOICE_AMOUNT),
                        rs.getString(COL_INVOICE_STATUS),
                        rs.getTimestamp("payment_date")
                );

                list.add(b);
            }

        } catch (SQLException e) {
            Message.error("Error retrieving all billings:\n" + e.getMessage());
        }

        return list;
    }

    // Retrieve invoice details for viewing purposes.
    @Override
    public Billing getInvoiceDetails(int invoiceId) {
        String sql = "SELECT "
                + COL_DESCRIPTION + ", "
                + COL_INVOICE_AMOUNT + ", "
                + COL_INVOICE_STATUS + ", "
                + COL_INVOICE_DATE
                + " FROM " + TABLE_INVOICE
                + " WHERE " + COL_INVOICE_ID + " = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, invoiceId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Billing billing = new Billing();

                    billing.setDescription(
                            rs.getString(COL_DESCRIPTION));

                    billing.setInvoiceAmount(
                            rs.getDouble(COL_INVOICE_AMOUNT));

                    billing.setInvoiceStatus(
                            rs.getString(COL_INVOICE_STATUS));

                    billing.setInvoiceDate(
                            rs.getTimestamp(COL_INVOICE_DATE));

                    return billing;
                }
            }

        } catch (SQLException e) {
            Message.error("Error retrieving invoice details:\n" + e.getMessage());
        }

        return null;
    }

    // Get payment record via invoiceId.
    @Override
    public Billing getPaymentByInvoiceId(int invoiceId) {

        String sql = "SELECT "
                + "i." + COL_INVOICE_ID + ", "
                + "p." + COL_PAYMENT_ID + ", "
                + "p." + COL_PROCESSED_BY + ", "
                + "p." + COL_PAY_METHOD + ", "
                + "p." + COL_PAY_AMOUNT + ", "
                + "p." + COL_PAY_DATE + " "
                + "FROM " + TABLE_INVOICE + " i "
                + "LEFT JOIN " + TABLE_PAYMENT + " p "
                + "ON i." + COL_INVOICE_ID + " = p." + COL_PAYMENT_INVOICE_ID + " "
                + "WHERE i." + COL_INVOICE_ID + " = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, invoiceId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Billing billing = new Billing();

                    billing.setInvoiceId(rs.getInt(COL_INVOICE_ID));
                    billing.setPaymentId(rs.getInt(COL_PAYMENT_ID));
                    billing.setProcessedBy(rs.getInt(COL_PROCESSED_BY));
                    billing.setPayMethod(rs.getString(COL_PAY_METHOD));
                    billing.setPayAmount(rs.getDouble(COL_PAY_AMOUNT));
                    billing.setPaymentDate(rs.getTimestamp(COL_PAY_DATE));

                    return billing;
                }
            }

        } catch (SQLException e) {
            Message.error("Error retrieving billing payment details:\n" + e.getMessage());
        }

        return null;
    }

    // Retrieves earnings for the current month.
    @Override
    public double getMonthlyEarnings() {
        String sql = "SELECT COALESCE(SUM(" + COL_PAY_AMOUNT + "), 0) "
                + "FROM " + TABLE_PAYMENT
                + " WHERE " + COL_PAY_DATE + " >= "
                + "DATE_FORMAT(CURDATE(), '%Y-%m-01')"
                + " AND " + COL_PAY_DATE + " < "
                + "DATE_FORMAT(CURDATE() + INTERVAL 1 MONTH, '%Y-%m-01')";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            Message.error("Error retrieving monthly earnings:\n" + e.getMessage());
        }

        return 0.0;
    }

    // Retrieves total earnings.
    @Override
    public double getTotalEarnings() {
        String sql = "SELECT COALESCE(SUM(" + COL_PAY_AMOUNT + "), 0) "
                + "FROM " + TABLE_PAYMENT;

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            Message.error("Error retrieving total earnings:\n" + e.getMessage());
        }

        return 0.0;
    }

    // Retrieves the total payments recorded today.
    @Override
    public double getTodaysPayment() {
        String sql = "SELECT COALESCE(SUM(" + COL_PAY_AMOUNT + "), 0) "
                + "FROM " + TABLE_PAYMENT + " "
                + "WHERE DATE(" + COL_PAY_DATE + ") = CURDATE()";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            Message.error("Error retrieving today's payments:\n" + e.getMessage());
        }

        return 0.0;
    }
}
