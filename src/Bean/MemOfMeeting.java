package Bean;

import models.ThamGiaCuocHopModel;

/**
 * 
 * @author Nuan
 */

public class MemOfMeeting {
    private CuocHopBean cuocHop;
    private ThamGiaCuocHopModel thamGiaCuocHopModel;

    public MemOfMeeting(CuocHopBean cuocHop, ThamGiaCuocHopModel thamGiaCuocHopModel) {
        this.cuocHop = cuocHop;
        this.thamGiaCuocHopModel = thamGiaCuocHopModel;
    }

    public MemOfMeeting() {
        this.cuocHop = new CuocHopBean();
        this.thamGiaCuocHopModel = new ThamGiaCuocHopModel();
    }

    public CuocHopBean getCuocHop() {
        return cuocHop;
    }

    public void setCuocHop(CuocHopBean cuocHop) {
        this.cuocHop = cuocHop;
    }

    public ThamGiaCuocHopModel getThamGiaCuocHopModel() {
        return thamGiaCuocHopModel;
    }

    public void setThamGiaCuocHopModel(ThamGiaCuocHopModel thamGiaCuocHopModel) {
        this.thamGiaCuocHopModel = thamGiaCuocHopModel;
    }

}
