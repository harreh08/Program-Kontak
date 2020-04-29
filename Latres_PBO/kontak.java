package latrespbo;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class kontak extends JDialog {

    private JPanel contentPanel = new cigradient();
    private JLabel lNo;
    private JTextField txtNo;
    private JLabel lNama;
    private JTextField txtNama;
    private JTextField txtHp;
    private JLabel lNoTelp;
    @SuppressWarnings("rawtypes")
    private JComboBox cbKategori;
    private JLabel lKategori;
    private JLabel lSimpan;
    private JLabel lUbah;
    private JLabel lHapus;
    private JLabel lRefresh;
    private JScrollPane scrollPane;
    private JTable table;
    private JLabel lPencarian;
    private JTextField txtCari;
    DefaultTableModel tabelModel;
    String data[] = { "No", "Nama", "Telp", "Kategori" };


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public kontak() {
        setTitle("Tambah Kontak");
        setResizable(false);
        setBounds(100, 100, 751, 256);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        lNo = new JLabel("No : ");
        lNo.setForeground(new Color(255, 255, 255));
        lNo.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lNo.setBounds(12, 12, 46, 15);
        contentPanel.add(lNo);

        txtNo = new JTextField();
        txtNo.setEditable(false);
        txtNo.setFont(new Font("Droid Sans", Font.BOLD, 12));
        txtNo.setBounds(120, 6, 93, 27);
        contentPanel.add(txtNo);
        txtNo.setColumns(10);

        lNama = new JLabel("Nama : ");
        lNama.setForeground(new Color(255, 255, 255));
        lNama.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lNama.setBounds(12, 50, 60, 15);
        contentPanel.add(lNama);

        txtNama = new JTextField();
        txtNama.setFont(new Font("Droid Sans", Font.BOLD, 12));
        txtNama.setBounds(120, 44, 205, 27);
        contentPanel.add(txtNama);
        txtNama.setColumns(10);

        lNoTelp = new JLabel("No.HP / Telp : ");
        lNoTelp.setForeground(new Color(255, 255, 255));
        lNoTelp.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lNoTelp.setBounds(12, 83, 93, 15);
        contentPanel.add(lNoTelp);

        txtHp = new JTextField();
        txtHp.setFont(new Font("Droid Sans", Font.BOLD, 12));
        txtHp.setBounds(120, 77, 205, 27);
        contentPanel.add(txtHp);
        txtHp.setColumns(10);

        lKategori = new JLabel("Kategori : ");
        lKategori.setForeground(new Color(255, 255, 255));
        lKategori.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lKategori.setBounds(12, 115, 81, 15);
        contentPanel.add(lKategori);

        cbKategori = new JComboBox();
        cbKategori.setFont(new Font("Droid Sans", Font.BOLD, 12));
        cbKategori.setModel(new DefaultComboBoxModel(new String[] {
                "- Pilih -", "Keluarga", "Teman", "Sahabat" }));
        cbKategori.setBounds(120, 110, 205, 25);
        contentPanel.add(cbKategori);

        lSimpan = new JLabel("Simpan");
        lSimpan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Connection koneksi = konek.getKoneksi();
                    String sql = "INSERT INTO data VALUES (?,?,?,?,?)";
                    PreparedStatement prepare = koneksi.prepareStatement(sql);

                    prepare.setString(1, txtNo.getText());
                    prepare.setString(2, txtNama.getText());
                    prepare.setString(3, txtHp.getText());
                    prepare.setString(4, (String) cbKategori.getSelectedItem());
                    prepare.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                            "Data kontak berhasil ditambahkan");
                    prepare.close();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Data kontak gagal ditambahkan");
                    System.out.println(ex);
                } finally {
                    autonumber();
                    tampilDataTabel();
                    txtNama.setText("");
                    txtHp.setText("");
                    cbKategori.setSelectedIndex(0);
                    txtNama.requestFocus();
                }
            }
        });
        lSimpan.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lSimpan.setHorizontalTextPosition(SwingConstants.CENTER);
        lSimpan.setHorizontalAlignment(SwingConstants.CENTER);
        lSimpan.setVerticalTextPosition(SwingConstants.BOTTOM);
        lSimpan.setIcon(new ImageIcon(kontak.class
                .getResource("/latrespbo/aptdaemon-add.png")));
        lSimpan.setBounds(525, 172, 60, 51);
        contentPanel.add(lSimpan);

        lUbah = new JLabel("Ubah");
        lUbah.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Connection koneksi = konek.getKoneksi();
                    String sql = "UPDATE data SET nama = ?, hp = ?, kategori = ?  WHERE no = ? ";
                    PreparedStatement prepare = koneksi.prepareStatement(sql);

                    prepare.setString(1, txtNama.getText());
                    prepare.setString(2, txtHp.getText());
                    prepare.setString(3, (String) cbKategori.getSelectedItem());
                    prepare.setString(4, txtNo.getText());
                    prepare.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                            "Data kontak berhasil diubah");
                    prepare.close();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Data kontak gagal diubah");
                    System.out.println(e1);
                } finally {
                    autonumber();
                    tampilDataTabel();
                    txtNama.setText("");
                    txtHp.setText("");
                    cbKategori.setSelectedIndex(0);
                    txtNama.requestFocus();
                }
            }
        });
        lUbah.setIcon(new ImageIcon(kontak.class
                .getResource("/latrespbo/aptdaemon-working.png")));
        lUbah.setVerticalTextPosition(SwingConstants.BOTTOM);
        lUbah.setHorizontalTextPosition(SwingConstants.CENTER);
        lUbah.setHorizontalAlignment(SwingConstants.CENTER);
        lUbah.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lUbah.setBounds(578, 172, 60, 51);
        contentPanel.add(lUbah);

        lHapus = new JLabel("Hapus");
        lHapus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Connection koneksi = konek.getKoneksi();
                    String sql = "DELETE FROM data WHERE no = ?";
                    PreparedStatement prepare = koneksi.prepareStatement(sql);

                    prepare.setString(1, txtNo.getText());
                    prepare.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                            "Data kontak berhasil dihapus");
                    prepare.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Data kontak gagal dihapus");
                    System.out.println(ex);
                } finally {
                    autonumber();
                    tampilDataTabel();
                    txtNama.setText("");
                    txtHp.setText("");
                    cbKategori.setSelectedIndex(0);
                    txtNama.requestFocus();
                }
            }
        });
        lHapus.setIcon(new ImageIcon(kontak.class
                .getResource("/latrespbo/user-trash-full.png")));
        lHapus.setVerticalTextPosition(SwingConstants.BOTTOM);
        lHapus.setHorizontalTextPosition(SwingConstants.CENTER);
        lHapus.setHorizontalAlignment(SwingConstants.CENTER);
        lHapus.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lHapus.setBounds(630, 172, 60, 51);
        contentPanel.add(lHapus);

        lRefresh = new JLabel("Refresh");
        lRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                autonumber();
                tampilDataTabel();
                txtNama.setText("");
                txtHp.setText("");
                cbKategori.setSelectedIndex(0);
                txtCari.setText("");
                txtNama.requestFocus();
            }
        });
        lRefresh.setIcon(new ImageIcon(kontak.class
                .getResource("/latrespbo/appointment-soon.png")));
        lRefresh.setVerticalTextPosition(SwingConstants.BOTTOM);
        lRefresh.setHorizontalTextPosition(SwingConstants.CENTER);
        lRefresh.setHorizontalAlignment(SwingConstants.CENTER);
        lRefresh.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lRefresh.setBounds(683, 172, 60, 51);
        contentPanel.add(lRefresh);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(383, 44, 360, 121);
        contentPanel.add(scrollPane);

        tabelModel = new DefaultTableModel(null, data);
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pilih = table.getSelectedRow();
                if (pilih == -1) {
                    return;
                }

                String no = (String) table.getValueAt(pilih, 0);
                txtNo.setText(no);
                String nama = (String) table.getValueAt(pilih, 1);
                txtNama.setText(nama);
                String hp = (String) table.getValueAt(pilih, 2);
                txtHp.setText(hp);
                String kategori = (String) table.getValueAt(pilih, 3);
                cbKategori.setSelectedItem(kategori);

            }
        });
        table.setModel(tabelModel);
        scrollPane.setViewportView(table);

        lPencarian = new JLabel("Pencarian : ");
        lPencarian.setForeground(new Color(255, 255, 255));
        lPencarian.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lPencarian.setBounds(473, 18, 69, 15);
        contentPanel.add(lPencarian);

        txtCari = new JTextField();
        txtCari.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tabelModel.getDataVector().removeAllElements();
                tabelModel.fireTableDataChanged();
                try {
                    Connection koneksi = konek.getKoneksi();
                    Statement state = koneksi.createStatement();
                    String query = "SELECT * FROM data WHERE nama LIKE '"
                            + txtCari.getText() + "%'";

                    ResultSet rs = state.executeQuery(query);
                    while (rs.next()) {
                        Object obj[] = new Object[5];
                        obj[0] = rs.getString(1);
                        obj[1] = rs.getString(2);
                        obj[2] = rs.getString(3);
                        obj[3] = rs.getString(4);
                        obj[4] = rs.getString(5);

                        tabelModel.addRow(obj);
                    }
                    rs.close();
                    state.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        txtCari.setBounds(554, 12, 189, 27);
        contentPanel.add(txtCari);
        txtCari.setColumns(10);
        tampilDataTabel();
        autonumber();
    }

    public void tampilDataTabel() {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try {
            Connection koneksi = konek.getKoneksi();
            Statement state = koneksi.createStatement();
            String sql = "SELECT * FROM data";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getString(4);
                obj[4] = rs.getString(5);

                tabelModel.addRow(obj);
            }
            rs.close();
            state.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void autonumber() {
        try {
            Connection koneksi = konek.getKoneksi();
            Statement state = koneksi.createStatement();
            String query = "SELECT MAX(no) FROM data"; // Mendapatkan nilai id
            // terakhir

            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                int a = rs.getInt(1);
                txtNo.setText(Integer.toString(a + 1)); // Nilai input yang
                // terakhir
                // ditambahkan 1
            }
            rs.close();
            state.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
