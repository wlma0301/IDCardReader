package com.wlma.dao.bean;

/**
 * Describe�����֤��Ϣ��JavaBean
 * @author wlma
 * 
 */
public class CardInfo {

	private String ID;	//����������
	private String cardNo;	//���֤��
	private String cardName;	//����
	private String sex;	//�Ա�
	private String folk;	//����
	private String birthday;	//����
	private String address;	//��ַ
	private String newAddress;	//�µ�ַ
	private String issueOrgan;	//��֤����
	private String availabilityBegin;	//��Ч����
	private String availabilityEnd;	//ʧЧ����
	private String controlNum;	//ˢ��ʱ��
	private byte photo;	//ͼ��

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