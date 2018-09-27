package com.mitrais.carrot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * @author Medianto_L132
 *
 */
@Entity
@Table(name = "sharing_level")
@JsonIgnoreProperties(value = { "createdTime", "lastModifiedTime" }, allowGetters = true)
@Data
public class SharingLevel extends ModelAudit {
	/**
	 * id SharingLevel Id auto increment
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * grade the grade of this SharingLevel.
	 */
	@NotNull
	@Size(max = 50)
	private String grade;

	/**
	 * sharingLevel SharingLevel sharing level name.
	 */
	@NotNull
	@Column(name = "sharing_level")
	private Integer sharingLevel;
}
