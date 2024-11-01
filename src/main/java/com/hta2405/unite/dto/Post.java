package com.hta2405.unite.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
	private Long postId;
	private Long boardId;
	private String postWriter;
	private String postSubject;
	private String postContent;
	private LocalDateTime postDate;
	private LocalDateTime postUpdateDate;
	private Long postReRef;
	private Long postReLev;
	private Long postReSeq;
	private Long postView;
}
