package org.scrum.domain.team;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Member 
	implements Comparable<Member>, Serializable{
	@EqualsAndHashCode.Include
	private Integer memberID;
	
	private String name;
	private Role role;
	
	private String userName;
	private String password;
	//
	public Member(Integer memberID, String name) {
		super();
		this.memberID = memberID;
		this.name = name;
	}
	public Member(Integer memberID, String name, Role role) {
		super();
		this.memberID = memberID;
		this.name = name;
		this.role = role;
	}

	public Member(Integer memberID, String name, String userName,
			String password) {
		super();
		this.memberID = memberID;
		this.name = name;
		this.userName = userName;
		this.password = password;
	}


	// caz supra-incarcare
	private String abilities;
	
	@Override
	public int compareTo(Member other) {
		if (this.equals(other))
			return 0;
		return this.getMemberID().compareTo(other.getMemberID());
	}
	
	public enum Role{
		MANAGER, PRODUCT_OWNER, SCRUM_MASTER, DEVELOPER, ANALYST, TESTER;
	}	
}