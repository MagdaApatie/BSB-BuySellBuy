package org.scrum.domain.team;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Team implements Serializable{
	@EqualsAndHashCode.Include
	private Integer teamID;
	
	private Specialization specialization;
	private String abilities;
	
	private List<Member> members = new ArrayList<Member>();
	
	private TeamLeader teamLeader;
	
	// properties from bean accessors
	public Team(Integer idEchipa, Specialization specializare, String competente) {
		super();
		this.teamID = idEchipa;
		this.specialization = specializare;
		this.abilities = competente;
	}
	//
	public Team(Integer teamID, Specialization specialization) {
		super();
		this.teamID = teamID;
		this.specialization = specialization;
	}
	// polimorfism parametrizare
	public void addMember(Member member){
		this.members.add(member);
	}
	
	public enum Specialization {
		BACKEND, FRONTEND, DATABASE;
	}	
}

