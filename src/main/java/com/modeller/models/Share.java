package com.modeller.models;

import lombok.*;

/**
 * Created by Gadzzzz on 22.03.2016.
 */
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Share {
	@Getter @Setter
	private int shareId;
	@Getter @Setter @NonNull
	private int userId;
	@Getter @Setter @NonNull
	private String link;
}
