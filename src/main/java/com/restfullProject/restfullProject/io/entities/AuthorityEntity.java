package com.restfullProject.restfullProject.io.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class AuthorityEntity implements Serializable {

	private static final long serialVersionUID = -7935820486726651982L;

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO )
	private long id ; 
	
	
	private String name ;
	public AuthorityEntity() {}
	
	@ManyToMany(mappedBy = "authorities")
	private Collection<RoleEntity> roles  ;

	public AuthorityEntity(String name) {
		this.name=name ;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}
	
	
	
}
