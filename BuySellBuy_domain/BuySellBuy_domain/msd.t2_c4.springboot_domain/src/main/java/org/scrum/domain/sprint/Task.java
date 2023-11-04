package org.scrum.domain.sprint;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.scrum.domain.project.Feature;
import org.scrum.domain.team.Member;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Task implements Serializable{
	@EqualsAndHashCode.Include
	private Integer taskID;
	private String name;
	private String description;
	
	// timing
	private Date startDate;
	
	private Integer estimatedTime; // initial, exprimat in ore
	private Integer remainingTime; // actualizat, exprimat in ore
	private Integer realTime;	
	
	private TaskStatus taskStatus;
	
	private Feature feature;
	
	// assessment
	private Member responsible;
	
	private TaskCategory taskCategory;
	
	// Burn down
	private Map<Date, Integer> burnDownRecords = new HashMap<>();

	public void setRemainingTime(Integer remainingTime) {
		this.remainingTime = remainingTime;
		burnDownRecords.put(new Date(), remainingTime);
	}
	//-------------------------------------------------------------------------
	public Task(Integer taskID, String name, String description,
			Date startDate, Integer estimatedTime, TaskStatus taskStatus,
			TaskCategory taskCategory) {
		super();
		this.taskID = taskID;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.estimatedTime = estimatedTime;
		this.taskStatus = taskStatus;
		this.taskCategory = taskCategory;
	}
	public Task(Integer taskID, String name, Date startDate,
			Integer estimatedTime) {
		super();
		this.taskID = taskID;
		this.name = name;
		this.startDate = startDate;
		this.estimatedTime = estimatedTime;
	}
	public Task(Integer taskID, String name,
			Date startDate, Integer estimatedTime, Member responsible) {
		super();
		this.taskID = taskID;
		this.name = name;
		this.startDate = startDate;
		this.estimatedTime = estimatedTime;
		this.responsible = responsible;
	}
	
}
