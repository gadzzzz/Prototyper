package com.modeller.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Gadzzzz on 01.03.2016.
 */
@Getter @Setter
public class JSONObject implements Serializable {
	private int id;
	private String name;
	private ContextObject[][] contextObjects;
}
