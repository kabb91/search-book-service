package com.kabby.app.springboot.repository.jpo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import com.kabby.app.domain.model.History;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "History")
@Table(name = "TB_HISTORY")
@NoArgsConstructor
public class HistoryJpo {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "HISTORY_ID")
	private String historyId;

	@Column(name = "HISTORY_QUERY")
	private String query;

	@CreationTimestamp
	@Column(name = "HISTORY_SEARCH_DATE")
	private LocalDateTime searchDate;

	@Column(name = "HISTORY_USER_ID")
	private String userId;

	public HistoryJpo(History history) {

		BeanUtils.copyProperties(history, this);

	}

	public History toModel() {

		History history = new History();

		BeanUtils.copyProperties(this, history);

		return history;

	}

}
