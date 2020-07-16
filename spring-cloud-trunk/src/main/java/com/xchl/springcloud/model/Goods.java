package com.xchl.springcloud.model;

import java.io.Serializable;

/**
 * 商品表
 */
public class Goods implements Serializable {

	private static final long serialVersionUID = -5342835355593988746L;

	public Goods() {
	}

	public Goods(String goodsName, Integer goodsType) {
		this.goodsName = goodsName;
		this.goodsType = goodsType;
	}

	private Long id;
	/**
	 * 商品编码
	 */
	private String goodsCode;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品类型
	 */
	private Integer goodsType;
	private String updateTime;
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
