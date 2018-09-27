package com.mitrais.carrot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;



/**
 * Reward Entity
 * @author Rudy
 *
 */
@Entity
@Table(name = "rewards")
@JsonIgnoreProperties(
        value = {"createdTime", "lastModifiedTime"},
        allowGetters = true
)
@Data
public class Rewards extends ModelAudit {
	
	/**
	 * id Reward Id auto increment
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id;

	/**
	 * typeName Reward TypeName (i.e. Well documented code, Hard Work) to be identified as reward type
	 */
    @NotNull
    @Size(max = 50)
    @Column(name = "type_name")
    private String typeName;

    /**
	 *carrot Carrot (i.e. 10,20) is the amount of carrot which is given to the employee
	 */
    @NotNull
    @Column(name = "sharing_level")
    private Integer sharingLevel;

    /**
     * carrot
     */
    @NotNull
    @Column(name = "carrot")
    private Integer carrot;

    /**
     * open,close
     */
    @Size(max = 10)
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private RewardsStatusEnum status;

    /**
	 * rewardTypeName (i.e. Manager Reward, Stockist Reward) is the specific reward type
	 */
    @Column(name = "reward_type_name")
    private String rewardTypeName;

    /**
	 * type (i.e. End year, Date, Column) Specific date type, if the user choose column then event value is needed
	 */
    @Size(max = 25)
    private String type;

    /**
	 * event (i.e. DateOfBirth, StartJoiningDate, 1/4/2018) Specific date value if the user choose Column type
	 */
    @Size(max = 25)
    private String event;

    /**
	 * statusCloseReason (i.e. Not Used Again, Canceled) is reason why the reward is inactivated
	 */
    @Size(max = 25)
    @Column(name = "status_close_reason")
    private String statusCloseReason;

    /**
	 * maxClaim (i.e. 5, 10) is maximal amount that user can claim the reward
	 */
    @Column(name = "max_claim")
    private Integer maxClaim;

    /**
	 * expiredDate (i.e. 1/4/2018) is the expired date of reward
	 */
    @Column(name = "expired_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiredDate;

}
