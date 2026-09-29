package kcart.dao;

import java.util.List;
import kcart.model.Billing;

public interface BillingDAO {

    int addInvoice(Billing billing); // Returns invoice id to process payment.
    boolean addPayment(Billing billing, int invoiceId);
}
