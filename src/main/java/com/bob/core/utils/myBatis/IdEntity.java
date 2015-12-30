package com.bob.core.utils.myBatis;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 统一定义id的entity基类.
 * 
 * @author henliqi
 */
@SuppressWarnings("serial")
public abstract class IdEntity implements Serializable {

	protected String id;

	protected String txnCode; // 接口交易码

	@Id
	@Column
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

}
