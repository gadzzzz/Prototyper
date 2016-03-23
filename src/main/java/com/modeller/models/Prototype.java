package com.modeller.models;

import lombok.*;

import java.util.List;

/**
 * Created by Gadzzzz on 10.02.2016.
 */
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Prototype {
	@Getter @Setter
	private int id;
	@Getter @Setter @NonNull
	private Long userId;
	@Getter @Setter @NonNull
	private java.sql.Date updateDate;
	@Getter @Setter @NonNull
	private String name;
	@Getter @Setter @NonNull
	private String link;
	@Getter @Setter
	private List<Page> pageList;
}
