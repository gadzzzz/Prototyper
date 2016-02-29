package com.modeller.models;

import lombok.*;

/**
 * Created by Gadzzzz on 10.02.2016.
 */
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Page {
	@Getter @Setter
	private int id;
	@Getter @Setter @NonNull
	private int prototypeId;
	@Getter @Setter @NonNull
	private String json;
}
