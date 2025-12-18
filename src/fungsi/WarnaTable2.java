/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants; // Wajib import ini
import java.text.DecimalFormat;    // Wajib import ini
import java.text.DecimalFormatSymbols; // Wajib import ini

/**
 *
 * @author Owner
 */
public class WarnaTable2 extends DefaultTableCellRenderer {
    public int kolom;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        
        DecimalFormatSymbols symbol = new DecimalFormatSymbols();
        symbol.setGroupingSeparator('.');
        symbol.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("#,##0", symbol);

        if (value instanceof Double || value instanceof Float || value instanceof Integer) {
            try {
                value = df.format(value);
            } catch (Exception e) {}
            this.setHorizontalAlignment(SwingConstants.RIGHT); // Rata Kanan
            this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 3)); // Padding
        } else {
            this.setHorizontalAlignment(SwingConstants.LEFT);
            this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0)); // Padding
        }
        
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (row % 2 == 1){
            component.setBackground(new Color(255,244,244));
        } else {
            component.setBackground(new Color(255,255,255));
        } 
        
        // B. Logika Stok vs Stok Min
        try {
            Object objStok = table.getModel().getValueAt(row, 6);
            Object objMin  = table.getModel().getValueAt(row, 13);
            
            if (objStok != null && objMin != null) {
                double stok = Double.parseDouble(objStok.toString());
                double min  = Double.parseDouble(objMin.toString());
                
                if (stok < min) {
                    component.setBackground(new Color(255, 150, 150)); // Merah Soft
                } else if (stok > min) {
                    component.setBackground(new Color(200, 255, 200)); // Hijau Soft
                }
            }
        } catch (Exception e) {
        }

        // C. Warna Kolom Aktif (Bawaan Khanza)
        if (column == kolom){
            component.setBackground(new Color(215,215,255));
            component.setForeground(new Color(255,255,255));
            try {
                if(table.getValueAt(row,kolom)!=null && !table.getValueAt(row,kolom).toString().equals("")){
                    component.setBackground(new Color(255,255,255));
                    component.setForeground(new Color(55,55,175));
                }
            } catch (Exception e) {}
        } else {
            component.setForeground(new Color(70,70,70));
        }

        // D. Warna Seleksi (Paling Penting)
        if (isSelected) {
            component.setBackground(new Color(255, 255, 0)); // Kuning
            component.setForeground(Color.BLACK);
        }
        
        return component;
    }
}