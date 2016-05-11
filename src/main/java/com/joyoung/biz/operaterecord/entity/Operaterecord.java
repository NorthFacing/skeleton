package com.joyoung.biz.operaterecord.entity;

import java.util.Date;

import com.joyoung.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
* Operaterecord
* @since v0.1
* @author Bob
* @Date 2016-5-10 9:26:30
*/
public class Operaterecord  extends BaseEntity {

	private boolean isDeleted;    //  
	private Date lastModifyTime;    //
	private long version;    //  
	private byte actionCruf;    //  curf

	private Integer actionLength;    //  具体数据结构的长度
	private byte category;    //  eg：操作指令 0x00b2->0x00
	private Integer dataLength;    //  
	private String fromHost;    //  
	private String isSynced;    //  
	private Integer linkAction;    //  
	private byte linkHeader;    //  
	private byte linkLf;    //  
	private String machineId;    //  生产编码
	private byte[] playLoad;    //  
	private String playLoadStr;    //  
	private Integer productCode;    //  产品型号
	private Integer protocolVersion;    //  协议版本
	private byte resource;    //  eg：操作指令 0x00b2->0xb2 和category对应组成一个指令
	private String toHost;    //  
	private String uid;    //  
	private String appLoginType;    //  
	private String deviceLoginType;    //  
	private String originCode;    //  原始数据的16进制窜，但不一定正确
	private Date channelOpenTime;    //
	private Date lastDecodeTime;    //
		

	/**  */
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public Operaterecord setIsDeleted (boolean isDeleted) {
		this.isDeleted = isDeleted;
		return this;
	}


	/**  */
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public Operaterecord setLastModifyTime (Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
		return this;
	}


	/**  */
	public long getVersion() {
		return version;
	}
	public Operaterecord setVersion (long version) {
		this.version = version;
		return this;
	}


	/** curf
 */
	public byte getActionCruf() {
		return actionCruf;
	}
	public Operaterecord setActionCruf (byte actionCruf) {
		this.actionCruf = actionCruf;
		return this;
	}


	/** 具体数据结构的长度 */
	public Integer getActionLength() {
		return actionLength;
	}
	public Operaterecord setActionLength (Integer actionLength) {
		this.actionLength = actionLength;
		return this;
	}


	/** eg：操作指令 0x00b2->0x00 */
	public byte getCategory() {
		return category;
	}
	public Operaterecord setCategory (byte category) {
		this.category = category;
		return this;
	}


	/**  */
	public Integer getDataLength() {
		return dataLength;
	}
	public Operaterecord setDataLength (Integer dataLength) {
		this.dataLength = dataLength;
		return this;
	}


	/**  */
	public String getFromHost() {
		return fromHost;
	}
	public Operaterecord setFromHost (String fromHost) {
		this.fromHost = fromHost;
		return this;
	}


	/**  */
	public String getIsSynced() {
		return isSynced;
	}
	public Operaterecord setIsSynced (String isSynced) {
		this.isSynced = isSynced;
		return this;
	}


	/**  */
	public Integer getLinkAction() {
		return linkAction;
	}
	public Operaterecord setLinkAction (Integer linkAction) {
		this.linkAction = linkAction;
		return this;
	}


	/**  */
	public byte getLinkHeader() {
		return linkHeader;
	}
	public Operaterecord setLinkHeader (byte linkHeader) {
		this.linkHeader = linkHeader;
		return this;
	}


	/**  */
	public byte getLinkLf() {
		return linkLf;
	}
	public Operaterecord setLinkLf (byte linkLf) {
		this.linkLf = linkLf;
		return this;
	}


	/** 生产编码 */
	public String getMachineId() {
		return machineId;
	}
	public Operaterecord setMachineId (String machineId) {
		this.machineId = machineId;
		return this;
	}


	/**  */
	public byte[] getPlayLoad() {
		return playLoad;
	}
	public Operaterecord setPlayLoad (byte[] playLoad) {
		this.playLoad = playLoad;
		return this;
	}


	/**  */
	public String getPlayLoadStr() {
		return playLoadStr;
	}
	public Operaterecord setPlayLoadStr (String playLoadStr) {
		this.playLoadStr = playLoadStr;
		return this;
	}


	/** 产品型号 */
	public Integer getProductCode() {
		return productCode;
	}
	public Operaterecord setProductCode (Integer productCode) {
		this.productCode = productCode;
		return this;
	}


	/** 协议版本 */
	public Integer getProtocolVersion() {
		return protocolVersion;
	}
	public Operaterecord setProtocolVersion (Integer protocolVersion) {
		this.protocolVersion = protocolVersion;
		return this;
	}


	/** eg：操作指令 0x00b2->0xb2
和category对应组成一个指令 */
	public byte getResource() {
		return resource;
	}
	public Operaterecord setResource (byte resource) {
		this.resource = resource;
		return this;
	}


	/**  */
	public String getToHost() {
		return toHost;
	}
	public Operaterecord setToHost (String toHost) {
		this.toHost = toHost;
		return this;
	}


	/**  */
	public String getUid() {
		return uid;
	}
	public Operaterecord setUid (String uid) {
		this.uid = uid;
		return this;
	}


	/**  */
	public String getAppLoginType() {
		return appLoginType;
	}
	public Operaterecord setAppLoginType (String appLoginType) {
		this.appLoginType = appLoginType;
		return this;
	}


	/**  */
	public String getDeviceLoginType() {
		return deviceLoginType;
	}
	public Operaterecord setDeviceLoginType (String deviceLoginType) {
		this.deviceLoginType = deviceLoginType;
		return this;
	}


	/** 原始数据的16进制窜，但不一定正确 */
	public String getOriginCode() {
		return originCode;
	}
	public Operaterecord setOriginCode (String originCode) {
		this.originCode = originCode;
		return this;
	}


	/**  */
	public Date getChannelOpenTime() {
		return channelOpenTime;
	}
	public Operaterecord setChannelOpenTime (Date channelOpenTime) {
		this.channelOpenTime = channelOpenTime;
		return this;
	}


	/**  */
	public Date getLastDecodeTime() {
		return lastDecodeTime;
	}
	public Operaterecord setLastDecodeTime (Date lastDecodeTime) {
		this.lastDecodeTime = lastDecodeTime;
		return this;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
