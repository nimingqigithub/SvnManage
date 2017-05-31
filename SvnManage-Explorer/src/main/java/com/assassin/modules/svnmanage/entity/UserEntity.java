package com.assassin.modules.svnmanage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/18.
 */
public class UserEntity {
    private Long bsm; //标识码
    private String yhm; //用户名
    private String mm; //密码
    private String xm; //姓名
    private String yx; //邮箱
    private String bz; //备注
    private Date gxsj; //更新时间
    private String dh;//电话
    private String ymm;//原密码
    private String fsyhm;//右键发送用户名

    public String getFsyhm() {
        return this.fsyhm;
    }

    public void setFsyhm(String fsyhm) {
        this.fsyhm = fsyhm;
    }

    public String getYmm() {
        return ymm;
    }

    public void setYmm(String ymm) {
        this.ymm = ymm;
    }

    public Long getBsm() {
        return bsm;
    }

    public void setBsm(Long bsm) {
        this.bsm = bsm;
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }
}
