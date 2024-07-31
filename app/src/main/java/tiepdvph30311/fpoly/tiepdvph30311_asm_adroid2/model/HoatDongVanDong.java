package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model;

public class HoatDongVanDong {
    private int id;
    private int maNguoiDung;
    private String ngayThucHien;
    private int soBuocChan;
    private String mucTieu;
    private String daDatDuoc;

    // Constructor, getters, and setters
    public HoatDongVanDong(int id, int maNguoiDung, String ngayThucHien, int soBuocChan, String mucTieu, String daDatDuoc) {
        this.id = id;
        this.maNguoiDung = maNguoiDung;
        this.ngayThucHien = ngayThucHien;
        this.soBuocChan = soBuocChan;
        this.mucTieu = mucTieu;
        this.daDatDuoc = daDatDuoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getNgayThucHien() {
        return ngayThucHien;
    }

    public void setNgayThucHien(String ngayThucHien) {
        this.ngayThucHien = ngayThucHien;
    }

    public int getSoBuocChan() {
        return soBuocChan;
    }

    public void setSoBuocChan(int soBuocChan) {
        this.soBuocChan = soBuocChan;
    }

    public String getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
    }

    public String getDaDatDuoc() {
        return daDatDuoc;
    }

    public void setDaDatDuoc(String daDatDuoc) {
        this.daDatDuoc = daDatDuoc;
    }

    @Override
    public String toString() {
        return "Date: " + ngayThucHien + ", Steps: " + soBuocChan;
    }
}
