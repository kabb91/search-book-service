package com.kabby.app.springboot.repository.jpo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.kabby.app.domain.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "TB_USER")
@NoArgsConstructor
public class UserJpo {

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@Column(name = "USER_NAME")
	private String userName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "HISTORY_USER_ID")
	private List<HistoryJpo> historyJpos;

	public UserJpo(User user) {

		BeanUtils.copyProperties(user, this);

	}

	public User toModel() {
		
		User user = new User();
		BeanUtils.copyProperties(this, user);
		return user;
	}

}
