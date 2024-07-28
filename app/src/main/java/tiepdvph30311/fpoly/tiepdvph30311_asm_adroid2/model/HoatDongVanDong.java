package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model;

public class HoatDongVanDong {
    private int maHoatDongVanDong;
    private int maNguoiDung;
    private String ngayThucHien;
    private int soBuocChan;
    private int mucTieu;
    private int daDatDuoc;

    public HoatDongVanDong() {
    }

    public HoatDongVanDong(int maHoatDongVanDong, int maNguoiDung, String ngayThucHien, int soBuocChan, int mucTieu, int daDatDuoc) {
        this.maHoatDongVanDong = maHoatDongVanDong;
        this.maNguoiDung = maNguoiDung;
        this.ngayThucHien = ngayThucHien;
        this.soBuocChan = soBuocChan;
        this.mucTieu = mucTieu;
        this.daDatDuoc = daDatDuoc;
    }

    public int getMaHoatDongVanDong() {
        return maHoatDongVanDong;
    }

    public void setMaHoatDongVanDong(int maHoatDongVanDong) {
        this.maHoatDongVanDong = maHoatDongVanDong;
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

    public int getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(int mucTieu) {
        this.mucTieu = mucTieu;
    }

    public int getDaDatDuoc() {
        return daDatDuoc;
    }

    public void setDaDatDuoc(int daDatDuoc) {
        this.daDatDuoc = daDatDuoc;
    }
}
