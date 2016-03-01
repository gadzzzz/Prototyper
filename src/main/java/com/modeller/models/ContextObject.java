package com.modeller.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
@Getter @Setter
public class ContextObject implements Serializable {
	private String tagtype;
	private String text;
	private String width;
	private String height;
	private String x;
	private String y;
	private String textsize;
	private String color;
}
