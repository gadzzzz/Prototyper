package com.modeller.models;

import java.io.Serializable;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
public class ContextObject implements Serializable {
	private String tagtype;
	private String text;
	private String width;
	private String height;
	private String x;
	private String y;
	private String textsize;
	private String color;

	public String getTagtype() {
		return tagtype;
	}

	public void setTagtype(String tagtype) {
		this.tagtype = tagtype;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getTextsize() {
		return textsize;
	}

	public void setTextsize(String textsize) {
		this.textsize = textsize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
