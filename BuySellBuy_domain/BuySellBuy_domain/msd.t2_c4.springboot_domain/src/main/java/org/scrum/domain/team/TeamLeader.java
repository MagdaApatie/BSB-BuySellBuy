package org.scrum.domain.team;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TeamLeader extends Member{

	private String technologicalAbilities; // JEE, Spring, .NET, JS/Node.js, Ruby_Rails
	//
	public TeamLeader(Integer id, String name,
			String technologicalAbilities) {
		super(id, name, Role.MANAGER);
		this.technologicalAbilities = technologicalAbilities;
	}
	
	// Polimorfism
	@Override
	public void setAbilities(String abilities) {
		this.setTechnologicalAbilities(abilities);
	}	
	
	// Supraincarcare
	public void setAbilities(String abilities, AbilityType type) {
		if (type.equals(AbilityType.MANAGERIAL))
			super.setAbilities(abilities);
		
		if (type.equals(AbilityType.TECHNOLOGICAL))
			setTechnologicalAbilities(abilities);
		
	}
	public enum AbilityType {MANAGERIAL, TECHNOLOGICAL}
}
