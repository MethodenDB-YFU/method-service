package de.yfu.intranet.methods.data.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import static de.yfu.intranet.methods.data.domain.SeminarGoal.SEMINAR_GOAL_TABLE;

import java.util.UUID;

import javax.persistence.*;

/**
 * @author Alex Senger <alexander.senger@yfu-deutschland.de>
 */
@Entity
@Table(name=SEMINAR_GOAL_TABLE)
public class SeminarGoal {
	
	public static final String SEMINAR_GOAL_TABLE = "sg_seminar_goal";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sg_id")
	private UUID id;
	
	@Column(name="sg_method_id")
	private UUID methodId;

	@Column(name = "sg_seminar_goal_id")
	private UUID seminarGoalId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


}
