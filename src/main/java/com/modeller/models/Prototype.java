package com.modeller.models;

import lombok.*;

/**
 * Created by Gadzzzz on 10.02.2016.
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class Prototype {
	@Getter @Setter
	private int id;
	@Getter @Setter @NonNull
	private Long userId;
	@Getter @Setter @NonNull
	private java.sql.Date updateDate;
}
