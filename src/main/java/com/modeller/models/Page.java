package com.modeller.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Gadzzzz on 10.02.2016.
 */
public class Page {
	@Getter @Setter
	private int id;
	@Getter @Setter
	private int prototypeId;
	@Getter @Setter
	private String json;
}