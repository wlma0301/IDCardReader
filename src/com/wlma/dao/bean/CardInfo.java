package com.wlma.dao.bean;

/**
 * Describe：身份证信息的JavaBean
 * @author wlma
 * 
 */
public class CardInfo {

	private String ID;	//自增长主键
	private String cardNo;	//身份证号
	private String cardName;	//姓名
	private String sex;	//性别
	private String folk;	//民族
	private String birthday;	//生日
	private String address;	//地址
	private String newAddress;	//新地址
	private String issueOrgan;	//发证机构
	private String availabilityBegin;	//生效日期
	private String availabilityEnd;	//失效日期
	private String controlNum;	//刷卡时间
	private byte photo;	//图像

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFolk() {
		return folk;
	}
	public void setFolk(String folk) {
		this.folk = folk;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNewAddress() {
		return newAddress;
	}
	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}
	public String getIssueOrgan() {
		return issueOrgan;
	}
	public void setIssueOrgan(String issueOrgan) {
		this.issueOrgan = issueOrgan;
	}
	public String getAvailabilityBegin() {
		return availabilityBegin;
	}
	public void setAvailabilityBegin(String availabilityBegin) {
		this.availabilityBegin = availabilityBegin;
	}
	public String getAvailabilityEnd() {
		return availabilityEnd;
	}
	public void setAvailabilityEnd(String availabilityEnd) {
		this.availabilityEnd = availabilityEnd;
	}
	public String getControlNum() {
		return controlNum;
	}
	public void setControlNum(String controlNum) {
		this.controlNum = controlNum;
	}
	public byte getPhoto() {
		return photo;
	}
	public void setPhoto(byte photo) {
		this.photo = photo;
	}
}