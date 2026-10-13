package kcart.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class StatusCellRenderer extends DefaultTableCellRenderer {

    public enum StatusType {
        RENTAL, CAR, BILLING, CUSTOMER, RETURN, USER
    }

    private final int statusColumn;
    private final StatusType statusType;

    // Shared status colors.
    private static final Color ACTIVE_BG = new Color(36, 91, 158);
    private static final Color ACTIVE_FG = Color.WHITE;

    private static final Color PENDING_BG = new Color(166, 107, 0);
    private static final Color PENDING_FG = Color.WHITE;

    private static final Color LATE_BG = new Color(198, 40, 40);
    private static final Color LATE_FG = Color.WHITE;

    private static final Color COMPLETED_BG = new Color(33, 132, 71);
    private static final Color COMPLETED_FG = Color.WHITE;

    private static final Color DISMISSED_BG = new Color(85, 85, 85);
    private static final Color DISMISSED_FG = Color.WHITE;

    public StatusCellRenderer(int statusColumn, StatusType statusType) {
        this.statusColumn = statusColumn;
        this.statusType = statusType;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component component = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        // Preserve the table's selection colors.
        if (isSelected) {
            return component;
        }

        int modelRow = table.convertRowIndexToModel(row);

        Object statusValue = table.getModel().getValueAt(
                modelRow, statusColumn);

        String status = statusValue == null ? "" : statusValue.toString().trim().toLowerCase();

        Color background = table.getBackground();
        Color foreground = table.getForeground();

        switch (statusType) {

            case RENTAL:
                switch (status) {
                    case "active":
                        background = ACTIVE_BG;
                        foreground = ACTIVE_FG;
                        break;

                    case "pending":
                        background = PENDING_BG;
                        foreground = PENDING_FG;
                        break;

                    case "late":
                        background = LATE_BG;
                        foreground = Color.WHITE;
                        break;

                    case "completed":
                        background = COMPLETED_BG;
                        foreground = COMPLETED_FG;
                        break;

                    case "dismissed":
                        background = DISMISSED_BG;
                        foreground = DISMISSED_FG;
                        break;
                }
                break;

            case CAR:
                switch (status) {
                    case "available":
                        background = COMPLETED_BG;
                        foreground = COMPLETED_FG;
                        break;

                    case "reserved":
                        background = PENDING_BG;
                        foreground = PENDING_FG;
                        break;

                    case "rented":
                        background = ACTIVE_BG;
                        foreground = ACTIVE_FG;
                        break;

                    case "maintenance":
                        background = DISMISSED_BG;
                        foreground = DISMISSED_FG;
                        break;
                }
                break;

            case BILLING:
                switch (status) {
                    case "paid":
                        background = COMPLETED_BG;
                        foreground = COMPLETED_FG;
                        break;

                    case "unpaid":
                        background = DISMISSED_BG;
                        foreground = DISMISSED_FG;
                        break;
                }
                break;

            case CUSTOMER:
                switch (status) {
                    case "active":
                        background = COMPLETED_BG;
                        foreground = COMPLETED_FG;
                        break;

                    case "inactive":
                        background = DISMISSED_BG;
                        foreground = DISMISSED_FG;
                        break;
                }
                break;

            case RETURN:
                switch (status) {
                    case "good":
                        background = COMPLETED_BG;
                        foreground = COMPLETED_FG;
                        break;

                    case "damaged":
                        background = LATE_BG;
                        foreground = Color.WHITE;
                        break;
                }
                break;
            case USER:
                switch (status) {
                    case "active":
                        background = COMPLETED_BG;
                        foreground = COMPLETED_FG;
                        break;

                    case "inactive":
                        background = DISMISSED_BG;
                        foreground = DISMISSED_FG;
                        break;
                }
                break;
        }

        component.setBackground(background);
        component.setForeground(foreground);

        return component;
    }
}
