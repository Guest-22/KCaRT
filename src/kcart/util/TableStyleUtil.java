package kcart.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class TableStyleUtil {

    private static final Color TABLE_BG = Color.BLACK;
    private static final Color TABLE_FG = Color.WHITE;
    private static final Color GRID_COLOR = new Color(75, 75, 75);
    private static final Color SELECTION_BG = new Color(65, 65, 65);

    public static void applyBlackWhiteStyle(JTable table) {

        // Overall table appearance.
        table.setBackground(TABLE_BG);
        table.setForeground(TABLE_FG);
        table.setGridColor(GRID_COLOR);
        table.setSelectionBackground(SELECTION_BG);
        table.setSelectionForeground(TABLE_FG);
        table.setShowGrid(true);
        table.setFillsViewportHeight(true);

        // Empty space around the table.
        if (table.getParent() != null) {
            table.getParent().setBackground(TABLE_BG);
        }

        // Header appearance.
        JTableHeader header = table.getTableHeader();

        header.setBackground(TABLE_BG);
        header.setForeground(TABLE_FG);

        DefaultTableCellRenderer headerRenderer
                = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component component = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

                component.setBackground(TABLE_BG);
                component.setForeground(TABLE_FG);
                component.setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD));
                setHorizontalAlignment(CENTER);

                return component;
            }
        };

        header.setDefaultRenderer(headerRenderer);
    }
}
