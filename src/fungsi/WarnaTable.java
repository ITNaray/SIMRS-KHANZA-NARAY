/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Owner
 */
public class WarnaTable extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         if (!isSelected) {
            // --- striping bawaan (merah muda / putih) ---
            if (row % 2 == 1) {
                component.setBackground(new Color(220, 250, 250));
            } else {
                component.setBackground(new Color(255, 255, 255));
            }

            // ==== KHUSUS TABEL CEK DATA RM ====
            // pastikan nama tabel sama dengan yang kamu set di form
            if (table != null && "tbCheckDataRM".equals(table.getName())) {
                // kolom 7 s.d. 14 berisi "Ada" / "Tidak Ada"
                if (value != null && column >= 7 && column <= 14) {
                    String val = value.toString();

                    if ("Ada".equalsIgnoreCase(val)) {
                        // hijau muda kalau datanya ADA
                        component.setBackground(new Color(198, 239, 206));
                    } else if ("Tidak Ada".equalsIgnoreCase(val)
                            || "Tidak".equalsIgnoreCase(val)) {
                        // merah muda kalau TIDAK ADA
                        component.setBackground(new Color(255, 199, 206));
                    }
                }
            }
        }   
        return component;
    }

}
