/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SanPham;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ViewSanPham extends javax.swing.JFrame {
    ArrayList<ModeSanPham> list = new ArrayList<>();
    ArrayList<ModeSanPham> listmau = new ArrayList<>();
    ArrayList<ModeSanPham> listkc = new ArrayList<>();
    ArrayList<ModeSanPham> listth = new ArrayList<>();
    SanPhamServices spsv = new SanPhamServices();
    DefaultTableModel model  = new DefaultTableModel();
    /**
     * Creates new form ViewSanPham
     */
    public ViewSanPham() throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        getdata();
        loadcbb();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cbbngaytao.setDateFormat(dateFormat);
    }
    
    public void getdata() throws SQLException{
        list = spsv.loaddata();
        filltotable(list);
        listmau = spsv.getmau();
        filltablemausac(listmau);
        listkc = spsv.getkc();
        filltablekichco(listkc);
        listth = spsv.getth();
        filltablethuonghieu(listth);
    }
    public void filltotable(ArrayList<ModeSanPham> list){
        DefaultTableModel model = (DefaultTableModel) tbtable.getModel();
        model.setNumRows(0);
        for (ModeSanPham modeSanPham : list) {
            Object[] row = {
                modeSanPham.getMaSP(), 
                modeSanPham.getTensanpham(),
                modeSanPham.getTenmau(),
                modeSanPham.getTenkc(),
                modeSanPham.getTenth(),
                modeSanPham.getMota(),
                modeSanPham.getGia(),
                modeSanPham.getSoluongtonkho(),
                modeSanPham.getDonvitinh(),
                modeSanPham.getNgaytao(),
                modeSanPham.getTrangthai()
            };
            model.addRow(row);
        }
    }
    public void filltablemausac(ArrayList<ModeSanPham> list){
        DefaultTableModel mode = (DefaultTableModel) tbmausac.getModel();
        mode.setNumRows(0);
        for (ModeSanPham modeSanPham : list) {
            Object[] row ={
                modeSanPham.getMamausac() , 
                modeSanPham.getTenmau()};
            mode.addRow(row);
        }
        
    }
    public void filltablekichco(ArrayList<ModeSanPham> list){
        DefaultTableModel mode = (DefaultTableModel) tbkichco.getModel();
        mode.setNumRows(0);
        for (ModeSanPham modeSanPham : list) {
            Object[] row ={
                modeSanPham.getMakc(), 
                modeSanPham.getTenkc()};
            mode.addRow(row);
        }
        
    }
    public void filltablethuonghieu(ArrayList<ModeSanPham> list){
        list = listth;
        DefaultTableModel mode = (DefaultTableModel) tbthuonghieu.getModel();
        mode.setNumRows(0);
        for (ModeSanPham modeSanPham : list) {
            Object[] row ={
                modeSanPham.getMath(), 
                modeSanPham.getTenth()};
            mode.addRow(row);
        }
        
    }
    
    public void loadcbb() throws SQLException{
        cbbmausac.removeAllItems();
        cbbkichco.removeAllItems();
        cbbthuonghieu.removeAllItems();
        for (ModeSanPham modeSanPham : spsv.gettenmau()) {
            cbbmausac.addItem(modeSanPham.getTenmaucbb());
        }
        for (ModeSanPham modeSanPham : spsv.gettenkc()) {
            cbbkichco.addItem(modeSanPham.getTenkccbb());
        }
        for (ModeSanPham modeSanPham : spsv.gettenth()) {
            cbbthuonghieu.addItem(modeSanPham.getTenthcbb());
        }
        
    }
    public void filltotxtmau(int index){
        ModeSanPham sp = listmau.get(index);
        txtmamau.setText(sp.getMamausac());
        txttenmau.setText(sp.getTenmau());
    }
    public void filltotxtkc(int index){
        ModeSanPham sp = listkc.get(index);
        txtmakc.setText(sp.getMakc());
        txtkc.setText(sp.getTenkc());
    }public void filltotxtth(int index){
        ModeSanPham sp = listth.get(index);
        txtth.setText(sp.getTenth());
        txtmath.setText(sp.getMath());
    }
    
    public void filltotxt(int index){
        if (index >=0 && index < list.size()) {
            ModeSanPham sp = list.get(index);
            txtmasp.setText(sp.getMaSP());
            txttensp.setText(sp.getTensanpham());
            txtamota.setText(sp.getMota());
            txtgia.setText(Integer.toString(sp.getGia()));
            txtsoluong.setText(Integer.toString(sp.getSoluongtonkho()));
            txtdonvitinh.setText(sp.getDonvitinh());
            String mauduocchon = sp.getTenmau(); // Lấy giá trị màu sắc từ cột tương ứng
            // Kiểm tra xem màu sắc có trong JComboBox chưa, nếu có thì chọn màu đó
            for (int i = 0; i < cbbmausac.getItemCount(); i++) {
                if (cbbmausac.getItemAt(i).toString().equals(mauduocchon)) {
                    cbbmausac.setSelectedIndex(i); // Đặt giá trị được chọn trong JComboBox
                    break;
                }
            }
            String sizeduocchon = sp.getTenkc();
            for (int i = 0; i < cbbkichco.getItemCount(); i++) {
                if (cbbkichco.getItemAt(i).equals(sizeduocchon)) {
                    cbbkichco.setSelectedIndex(i);
                }
            }
            String thduocchon = sp.getTenth();
            for (int i = 0; i < cbbthuonghieu.getItemCount(); i++) {
                if (cbbthuonghieu.getItemAt(i).equals(thduocchon)) {
                    cbbthuonghieu.setSelectedIndex(i);
                }
            }
            if (sp.getTrangthai().equals("Còn Hàng")) {
                rdoconhang.setSelected(true);
            }else{
                rdohethang.setSelected(true);
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// Định dạng ngày
            try {
                Date datengaytao = dateFormat.parse(sp.getNgaytao()); // Chuyển đổi chuỗi thành java.util.Date
                cbbngaytao.setDate(datengaytao);
            } catch (Exception e) {
            }
        } else {
            System.out.println("index ngoại lệ : "+index);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtmasp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txttensp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtamota = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtgia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbbmausac = new javax.swing.JComboBox<>();
        cbbkichco = new javax.swing.JComboBox<>();
        cbbthuonghieu = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdonvitinh = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbbngaytao = new de.wannawork.jcalendar.JCalendarComboBox();
        jLabel11 = new javax.swing.JLabel();
        rdoconhang = new javax.swing.JRadioButton();
        rdohethang = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();
        txttimkiemsanpham = new javax.swing.JTextField();
        btntimkiemsanpham = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbmausac = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbkichco = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbthuonghieu = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtmamau = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txttenmau = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtmakc = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtkc = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtmath = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtth = new javax.swing.JTextField();
        btnthemmau = new javax.swing.JButton();
        btnsuamau = new javax.swing.JButton();
        btnxoamau = new javax.swing.JButton();
        btnthemkc = new javax.swing.JButton();
        btnsuakc = new javax.swing.JButton();
        btnxoakc = new javax.swing.JButton();
        btnxoath = new javax.swing.JButton();
        btnthemth = new javax.swing.JButton();
        btnsuath = new javax.swing.JButton();
        txttimkiemmau = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        txttimkiemkc = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        txttimkiemth = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        btnlammoimau = new javax.swing.JButton();
        btnlammoikc = new javax.swing.JButton();
        btnlammoikc1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MaSP", "Tên Sản Phẩm", "Màu Sắc", "Kích Cỡ", "Thương Hiệu", "Mô Tả", "Giá", "Số Lượng Tồn", "Đơn Vị Tính", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tbtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtable);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi Tiết Sản Phẩm"));

        jLabel1.setText("MaSP :");

        txtmasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaspActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên Sản Phẩm :");

        jLabel3.setText("Mô Tả  :");

        txtamota.setColumns(20);
        txtamota.setRows(5);
        jScrollPane2.setViewportView(txtamota);

        jLabel4.setText("Giá :");

        jLabel5.setText("Số Lượng Tồn :");

        jLabel6.setText("Màu Sắc :");

        cbbmausac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbmausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbmausacActionPerformed(evt);
            }
        });

        cbbkichco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbthuonghieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Kích Cỡ :");

        jLabel8.setText("Thương HIệu :");

        jLabel9.setText("Đơn Vị Tính :");

        jLabel10.setText("Ngày Tạo :");

        jLabel11.setText("Trạng Thái :");

        buttonGroup1.add(rdoconhang);
        rdoconhang.setText("Còn Hàng");

        buttonGroup1.add(rdohethang);
        rdohethang.setText("Hết Hàng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addComponent(txttensp))))
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbkichco, 0, 231, Short.MAX_VALUE)
                            .addComponent(txtgia)
                            .addComponent(txtsoluong)
                            .addComponent(cbbmausac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbthuonghieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel4))
                .addGap(64, 64, 64)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdohethang, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(txtdonvitinh)
                    .addComponent(cbbngaytao, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(rdoconhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbbmausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtdonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbkichco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbthuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(28, 28, 28))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbbngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(rdoconhang))
                        .addGap(18, 18, 18)
                        .addComponent(rdohethang)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnlammoi.setText("Làm Mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        btntimkiemsanpham.setText("Tìm Kiếm");
        btntimkiemsanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemsanphamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txttimkiemsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btntimkiemsanpham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnthem)
                .addGap(36, 36, 36)
                .addComponent(btnsua)
                .addGap(27, 27, 27)
                .addComponent(btnxoa)
                .addGap(35, 35, 35)
                .addComponent(btnlammoi)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnsua)
                    .addComponent(btnxoa)
                    .addComponent(btnlammoi)
                    .addComponent(txttimkiemsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiemsanpham))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1255, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel2);

        tbmausac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Màu", "Tên Màu"
            }
        ));
        tbmausac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbmausacMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbmausac);

        tbkichco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Kích Cỡ", "Tên Kích Cỡ"
            }
        ));
        tbkichco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkichcoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbkichco);

        tbthuonghieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã TH", "Thương Hiệu"
            }
        ));
        tbthuonghieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbthuonghieuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbthuonghieu);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Màu Sắc"));

        jLabel12.setText("Mã Màu");

        jLabel13.setText("Tên Màu");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtmamau)
                    .addComponent(txttenmau, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmamau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenmau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Kích Cỡ"));

        jLabel14.setText("Mã KC");

        jLabel15.setText("Kích Cỡ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtmakc)
                    .addComponent(txtkc, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmakc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Thương Hiệu"));

        jLabel16.setText("Mã Thương Hiệu");

        jLabel17.setText("Thương Hiệu");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtth, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmath, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnthemmau.setText("Thêm Màu");
        btnthemmau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemmauActionPerformed(evt);
            }
        });

        btnsuamau.setText("Sửa Màu");
        btnsuamau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuamauActionPerformed(evt);
            }
        });

        btnxoamau.setText("Xóa Màu");
        btnxoamau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoamauActionPerformed(evt);
            }
        });

        btnthemkc.setText("Thêm Kc");
        btnthemkc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemkcActionPerformed(evt);
            }
        });

        btnsuakc.setText("Sửa Kc");
        btnsuakc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuakcActionPerformed(evt);
            }
        });

        btnxoakc.setText("Xóa Kc");
        btnxoakc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoakcActionPerformed(evt);
            }
        });

        btnxoath.setText("Xóa TH");

        btnthemth.setText("Thêm TH");

        btnsuath.setText("Sửa TH");

        jButton10.setText("Tìm Kiếm ");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Tìm Kiếm");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Tìm Kiếm");

        btnlammoimau.setText("Làm Mới Màu");
        btnlammoimau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoimauActionPerformed(evt);
            }
        });

        btnlammoikc.setText("Làm Mới KC");
        btnlammoikc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoikcActionPerformed(evt);
            }
        });

        btnlammoikc1.setText("Làm Mới TH");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnthemmau)
                                .addGap(18, 18, 18)
                                .addComponent(btnsuamau)
                                .addGap(21, 21, 21)
                                .addComponent(btnxoamau)
                                .addGap(152, 152, 152)
                                .addComponent(btnthemkc)
                                .addGap(37, 37, 37)
                                .addComponent(btnsuakc)
                                .addGap(27, 27, 27)
                                .addComponent(btnxoakc)
                                .addGap(18, 18, 18)
                                .addComponent(btnlammoikc))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(txttimkiemmau, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttimkiemkc, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton11)
                                .addGap(48, 48, 48)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(txttimkiemth, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton12))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(btnthemth)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnxoath)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsuath)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnlammoikc1)))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnlammoimau)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnthemmau)
                        .addComponent(btnsuamau)
                        .addComponent(btnxoamau)
                        .addComponent(btnthemkc)
                        .addComponent(btnxoakc)
                        .addComponent(btnsuakc)
                        .addComponent(btnlammoimau)
                        .addComponent(btnlammoikc))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnthemth)
                        .addComponent(btnxoath)
                        .addComponent(btnsuath)
                        .addComponent(btnlammoikc1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiemmau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10)
                    .addComponent(txttimkiemkc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11)
                    .addComponent(txttimkiemth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thuộc Tính", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaspActionPerformed

    private void cbbmausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbmausacActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbbmausacActionPerformed

    private void tbtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtableMouseClicked
        // TODO add your handling code here:
        int index = tbtable.getSelectedRow();
        filltotxt(index);
        
    }//GEN-LAST:event_tbtableMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        ModeSanPham sp = new ModeSanPham();
        sp.setMaSP(txtmasp.getText());
        sp.setTensanpham(txttensp.getText());
        sp.setMota(txtamota.getText());
        sp.setGia(Integer.parseInt(txtgia.getText()));
        sp.setSoluongtonkho(Integer.parseInt(txtsoluong.getText()));
        sp.setDonvitinh(txtdonvitinh.getText());
        Date ngaytao = cbbngaytao.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
//            Date ngaytaoDate = dateFormat.parse(ngaytao);
            if (ngaytao != null) {
                 String ngaytaoDate = dateFormat.format(ngaytao);
                sp.setNgaytao(ngaytaoDate);
            } else {
                JOptionPane.showMessageDialog(this, "ngay tao khong duoc de trong");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xử lý ngày tạo: " + e.getMessage());
            System.out.println(ngaytao);
            return;
        }
        String tenmau = cbbmausac.getSelectedItem().toString();
        try {
            String mamau = spsv.mamau(tenmau).get(0).getMamausac();  // Đảm bảo spsv.mamau() trả về giá trị đúng
            sp.setMamausac(mamau);    
            System.out.println("Mã màu: " + mamau);      
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

        String tenkc = cbbkichco.getSelectedItem().toString();
        try {
            String makc = spsv.makc(tenkc).get(0).getMakc();  // Đảm bảo spsv.makc() trả về giá trị đúng
            sp.setMakc(makc);
            System.out.println("Mã kích cỡ: " + makc);
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

        String tenth = cbbthuonghieu.getSelectedItem().toString();
        try {
            String math = spsv.math(tenth).get(0).getMath();  // Đảm bảo spsv.math() trả về giá trị đúng
            sp.setMath(math);
            System.out.println("Mã thương hiệu: " + math);
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rdoconhang.isSelected()) {
            sp.setTrangthai("Còn Hàng");
        }else{
            sp.setTrangthai("Hết Hàng");
        }
        try {
            spsv.them(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        txtmasp.setText("");
        txttensp.setText("");
        txtamota.setText("");
        txtgia.setText("");
        txtsoluong.setText("");
        buttonGroup1.clearSelection();
        txtdonvitinh.setText("");
        try {
            // TODO add your handling code here:
            loadcbb();
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:]
        ModeSanPham sp = new ModeSanPham();
        sp.setMaSP(txtmasp.getText());
        sp.setTensanpham(txttensp.getText());
        sp.setMota(txtamota.getText());
        sp.setGia(Integer.parseInt(txtgia.getText()));
        sp.setSoluongtonkho(Integer.parseInt(txtsoluong.getText()));
        sp.setDonvitinh(txtdonvitinh.getText());
        Date date = cbbngaytao.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayduocchon = dateFormat.format(date);
        sp.setNgaytao(ngayduocchon);
        String tenmau = cbbmausac.getItemAt(0);
        try {
            sp.setMamausac(spsv.mamau(tenmau).get(0).getMamausac());
            System.out.println(sp.getMamausac() );
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tenkc = cbbkichco.getItemAt(0);
        try {
            sp.setMakc(spsv.makc(tenkc).get(0).getMakc());
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tenth = cbbthuonghieu.getItemAt(0);
        try {
            sp.setMath(spsv.math(tenth).get(0).getMath());
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rdoconhang.isSelected()) {
            sp.setTrangthai("Còn Hàng");
        }else{
            sp.setTrangthai("Hết Hàng");
        }
        try {
            spsv.sua(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        ModeSanPham sp = new ModeSanPham();
        sp.setMaSP(txtmasp.getText());
        try {
            spsv.xoa(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnthemmauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemmauActionPerformed
        // TODO add your handling code here:
        ModeSanPham sp = new ModeSanPham();
        sp.setMamausac(txtmamau.getText());
        sp.setTenmau(txttenmau.getText());
        try {
            spsv.themmau(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnthemmauActionPerformed

    private void tbmausacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbmausacMouseClicked
        // TODO add your handling code here:
        int index = tbmausac.getSelectedRow();
        filltotxtmau(index);
    }//GEN-LAST:event_tbmausacMouseClicked

    private void tbkichcoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkichcoMouseClicked
        // TODO add your handling code here:
        int index = tbkichco.getSelectedRow();
        filltotxtkc(index);
    }//GEN-LAST:event_tbkichcoMouseClicked

    private void tbthuonghieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbthuonghieuMouseClicked
        // TODO add your handling code here:
        int index = tbthuonghieu.getSelectedRow();
        filltotxtth(index);
    }//GEN-LAST:event_tbthuonghieuMouseClicked

    private void btnxoamauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoamauActionPerformed
        // TODO add your handling code here:
        ModeSanPham sp = new ModeSanPham();
        sp.setMamausac(txtmamau.getText());
        try {
            spsv.xoamau(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoamauActionPerformed

    private void btnsuamauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuamauActionPerformed
        // TODO add your handling code here:
        ModeSanPham sp = new ModeSanPham();
        sp.setMamausac(txtmamau.getText());
        sp.setTenmau(txttenmau.getText());
        try {
            spsv.suamau(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsuamauActionPerformed

    private void btnlammoimauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoimauActionPerformed
        // TODO add your handling code here:
        txtmamau.setText("");
        txttenmau.setText("");
        try {
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnlammoimauActionPerformed

    private void btnthemkcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemkcActionPerformed
        // TODO add your handling code here:
        ModeSanPham sp = new ModeSanPham();
        sp.setMakc(txtmakc.getText());
        sp.setTenkc(txtkc.getText());
        try {
            spsv.themkc(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnthemkcActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            // TODO add your handling code here:
            String ttcantim = txttimkiemmau.getText();
            ArrayList<ModeSanPham> ketqua = spsv.timkiemmau(ttcantim);
            filltablemausac(ketqua);
            System.out.println(ketqua);
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btntimkiemsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemsanphamActionPerformed
        // TODO add your handling code here:
        String ttcantim = txttimkiemsanpham.getText().trim();
        try {
            ArrayList<ModeSanPham> ketqua = spsv.timkiemsanpham(ttcantim);
            filltotable(ketqua);
            System.out.println(ketqua);
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btntimkiemsanphamActionPerformed

    private void btnsuakcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuakcActionPerformed
        // TODO add your handling code here:
        ModeSanPham sp = new ModeSanPham();
        sp.setMakc(txtmakc.getText());
        sp.setTenkc(txtkc.getText());
        try {
            spsv.suakc(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsuakcActionPerformed

    private void btnxoakcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoakcActionPerformed
        // TODO add your handling code here:
        ModeSanPham sp = new ModeSanPham();
        sp.setMakc(txtmakc.getText());
        try {
            spsv.xoakc(sp);
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoakcActionPerformed

    private void btnlammoikcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoikcActionPerformed
        // TODO add your handling code here:
        txtmakc.setText("");
        txtkc.setText("");
        try {
            getdata();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnlammoikcActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String ttcantim = txttimkiemkc.getText();
            ArrayList<ModeSanPham> ketqua = spsv.timkiemkichco(ttcantim);
            filltablekichco(ketqua);
            System.out.println(ketqua);
        } catch (SQLException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewSanPham().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnlammoikc;
    private javax.swing.JButton btnlammoikc1;
    private javax.swing.JButton btnlammoimau;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnsuakc;
    private javax.swing.JButton btnsuamau;
    private javax.swing.JButton btnsuath;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthemkc;
    private javax.swing.JButton btnthemmau;
    private javax.swing.JButton btnthemth;
    private javax.swing.JButton btntimkiemsanpham;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxoakc;
    private javax.swing.JButton btnxoamau;
    private javax.swing.JButton btnxoath;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbkichco;
    private javax.swing.JComboBox<String> cbbmausac;
    private de.wannawork.jcalendar.JCalendarComboBox cbbngaytao;
    private javax.swing.JComboBox<String> cbbthuonghieu;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoconhang;
    private javax.swing.JRadioButton rdohethang;
    private javax.swing.JTable tbkichco;
    private javax.swing.JTable tbmausac;
    private javax.swing.JTable tbtable;
    private javax.swing.JTable tbthuonghieu;
    private javax.swing.JTextArea txtamota;
    private javax.swing.JTextField txtdonvitinh;
    private javax.swing.JTextField txtgia;
    private javax.swing.JTextField txtkc;
    private javax.swing.JTextField txtmakc;
    private javax.swing.JTextField txtmamau;
    private javax.swing.JTextField txtmasp;
    private javax.swing.JTextField txtmath;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttenmau;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txtth;
    private javax.swing.JTextField txttimkiemkc;
    private javax.swing.JTextField txttimkiemmau;
    private javax.swing.JTextField txttimkiemsanpham;
    private javax.swing.JTextField txttimkiemth;
    // End of variables declaration//GEN-END:variables
}
