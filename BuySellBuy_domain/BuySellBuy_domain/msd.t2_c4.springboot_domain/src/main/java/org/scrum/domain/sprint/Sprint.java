package org.scrum.domain.sprint;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.scrum.domain.project.Feature;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Sprint implements Serializable{
	@EqualsAndHashCode.Include
	private Integer sprintID;
	private String objective;
	
	private List<Feature> features = new ArrayList<>();
	
	private List<Task> tasks = new ArrayList<>();
	
	private Date startDate;
	private String review;
	//
	public Sprint(Integer sprintID, String objective, Date startDate) {
		super();
		this.sprintID = sprintID;
		this.objective = objective;
		this.startDate = startDate;
	}
}
