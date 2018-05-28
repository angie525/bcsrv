package com.bcsrv.fabric;

import org.bouncycastle.util.encoders.Hex;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;

import java.io.*;
import java.util.Set;

/**
 * @Auther: xinyanfei
 * @Date: 2018/5/23 15:51
 * @Description: 联盟用户对象
 */
public class FabricUser implements User,Serializable{
    /** 名称 */
    private String name;
    /** 规则 */
    private Set<String> roles;
    /** 账户 */
    private String account;
    /** 从属联盟 */
    private String affiliation;
    /** 组织 */
    private String organization;
    /** 注册操作的密码 */
    private String enrollmentSecret;
    /** 会员id */
    private String mspId;
    /** 注册登记操作 */
    Enrollment enrollment = null; //
    /** 存储配置对象 */
    private transient FabricStore keyValStore;
    private String keyValStoreName;

    public FabricUser(String name, String org, FabricStore store){
        this.name = name;
        this.keyValStore = store;
        this.organization = org;
        this.keyValStoreName = toKeyValStoreName(this.name, org);

        String memberStr = keyValStore.getValue(keyValStoreName);
        if (null != memberStr) {
            saveState();
        } else {
            restoreState();
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
    /**
     * 设置规则信息并将用户状态更新至存储配置对象
     *
     * @param roles
     *            规则
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
        saveState();
    }
    @Override
    public Set<String> getRoles() {
        return this.roles;
    }

    /**
     * 设置账户信息并将用户状态更新至存储配置对象
     * @return
     */
    public  void setAccount(String account){
        this.account=account;
        saveState();
    }
    /**
     * 设置账户信息并将用户状态更新至存储配置对象
     * @return
     */
    @Override
    public String getAccount() {
        return this.account;
    }


    /**
     * 设置从属联盟信息并将用户状态更新至存储配置对象
     *
     * @param affiliation
     *            从属联盟
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
        saveState();
    }

    @Override
    public String getAffiliation() {
        return this.affiliation;
    }
    /**
     * 设置注册登记操作信息并将用户状态更新至存储配置对象
     *
     * @param enrollment
     *            注册登记操作
     */
    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        saveState();
    }
    @Override
    public Enrollment getEnrollment() {
        return this.enrollment;
    }
    public String getEnrollmentSecret() {
        return enrollmentSecret;
    }

    /**
     * 设置注册操作的密钥信息并将用户状态更新至存储配置对象
     *
     * @param enrollmentSecret
     *            注册操作的密码
     */
    public void setEnrollmentSecret(String enrollmentSecret) {
        this.enrollmentSecret = enrollmentSecret;
        saveState();
    }

    /**
     * 设置会员id信息并将用户状态更新至存储配置对象
     *
     * @param mspID
     *            会员id
     */
    public void setMspId(String mspID) {
        this.mspId = mspID;
        saveState();
    }
    @Override
    public String getMspId() {
        return this.mspId;
    }

    public static String toKeyValStoreName(String name, String org) {
        System.out.println("toKeyValStoreName = " + "user." + name + org);
        return "user." + name + org;
    }

    /** 将用户状态保存至存储配置对象 */
    public void saveState() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            keyValStore.setValue(keyValStoreName, Hex.toHexString(bos.toByteArray()));
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从键值存储中恢复该用户的状态(如果找到的话)。如果找不到，什么也不要做
     *
     * @return 返回用户
     */
    private FabricUser restoreState() {
        String memberStr = keyValStore.getValue(keyValStoreName);
        if (null != memberStr) {
            // 用户在键值存储中被找到，因此恢复状态
            byte[] serialized = Hex.decode(memberStr);
            ByteArrayInputStream bis = new ByteArrayInputStream(serialized);
            try {
                ObjectInputStream ois = new ObjectInputStream(bis);
                FabricUser state = (FabricUser) ois.readObject();
                if (state != null) {
                    this.name = state.name;
                    this.roles = state.roles;
                    this.account = state.account;
                    this.affiliation = state.affiliation;
                    this.organization = state.organization;
                    this.enrollmentSecret = state.enrollmentSecret;
                    this.enrollment = state.enrollment;
                    this.mspId = state.mspId;
                    return this;
                }
            } catch (Exception e) {
                throw new RuntimeException(String.format("Could not restore state of member %s", this.name), e);
            }
        }
        return null;
    }
}
