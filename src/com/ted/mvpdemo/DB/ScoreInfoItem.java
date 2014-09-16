package com.ted.mvpdemo.DB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ted on 2014/9/15.
 */
@DatabaseTable(tableName = "scoreinfo")
public class ScoreInfoItem {
    /**用户信息编号，作为主键*/
    @DatabaseField(id=true)
    private int id;
    @DatabaseField()
    private float math;
    @DatabaseField()
    private float yuwen;
    @DatabaseField()
    private float ziran;

    public ScoreInfoItem(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMath() {
        return math;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public float getYuwen() {
        return yuwen;
    }

    public void setYuwen(float yuwen) {
        this.yuwen = yuwen;
    }

    public float getZiran() {
        return ziran;
    }

    public void setZiran(float ziran) {
        this.ziran = ziran;
    }
}
