package model;
import model.Ban;
import java.util.List;
public class BanDuocDat {
    private int ID;
    private List<Ban> ban;
    public BanDuocDat() {
    }
    public BanDuocDat(int ID, List<Ban> ban) {
        this.ID = ID;
        this.ban = ban;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public List<Ban> getBan() {
        return ban;
    }
    public void setBan(List<Ban> ban) {
        this.ban = ban;
    }
}