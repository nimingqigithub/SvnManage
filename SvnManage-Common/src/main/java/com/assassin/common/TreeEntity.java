package com.assassin.common;

/**
 * Created by Administrator on 2017/4/15.
 */
public class TreeEntity<T> {
    private String subnetId;  //节点内码
    private String subnetNode; //父节点内码，如果是根节点，就设置-1
    private String subnetRemark; //节点代码
    private String subnetName; //节点名称
    private T t;  //对象实体，由外部控制

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getSubnetNode() {
        return subnetNode;
    }

    public void setSubnetNode(String subnetNode) {
        this.subnetNode = subnetNode;
    }

    public String getSubnetRemark() {
        return subnetRemark;
    }

    public void setSubnetRemark(String subnetRemark) {
        this.subnetRemark = subnetRemark;
    }

    public String getSubnetName() {
        return subnetName;
    }

    public void setSubnetName(String subnetName) {
        this.subnetName = subnetName;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
