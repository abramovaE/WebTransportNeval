package com.springapp.mvc.model;



import javax.persistence.*;

/**
 * Created by kot on 14.09.17.
 */
@Entity
@Table(name = "costByZone")
public class CostByZone extends Model {



    @Column(name = "numberZone")
    private Integer numberZone;

    @Column(name = "costByZone")
    private Integer costByZone;

    public CostByZone() {
        super();
    }


    public Integer getNumberZone() {
        return numberZone;
    }

    public void setNumberZone(Integer numberZone) {
        this.numberZone = numberZone;
    }

    public Integer getCostByZone() {
        return costByZone;
    }

    public void setCostByZone(Integer costByZone) {
        this.costByZone = costByZone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostByZone)) return false;

        CostByZone that = (CostByZone) o;

        if (getId() != that.getId()) return false;
        if (getNumberZone() != null ? !getNumberZone().equals(that.getNumberZone()) : that.getNumberZone() != null)
            return false;
        return !(getCostByZone() != null ? !getCostByZone().equals(that.getCostByZone()) : that.getCostByZone() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getNumberZone() != null ? getNumberZone().hashCode() : 0);
        result = 31 * result + (getCostByZone() != null ? getCostByZone().hashCode() : 0);
        return result;
    }
}
