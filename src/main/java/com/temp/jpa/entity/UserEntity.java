package com.temp.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "\"User\"")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 使用者鍵值 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userKey;
	
	/** 使用者名稱 */
	@Column
	private String userName;
	
	/** 密碼 */
	@Column
	private String password;
	
	/** 使用者顯示名稱 */
	@Column
	private String userDisplayName;
	
	/** 入職日期 */
	@Column
	private Date joinDate;
	
	/** 所屬部門代碼 */
	@Column
	private Long departmentKey;
	
	/** 員工編號 */
	@Column
	private Long employeeId;
	
	/** 生日 */
	@Column
	private Date birthday;
	
	/** 電子郵件 */
	@Column
	private String email;
	
	/** 連絡電話 */
	@Column
	private String tel;
	
	/** 角色鍵值 */
	@Column
	private Long roleKey;
	
	/** 資料建立時間 */
	@Column
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	/** 資料更新時間 */
	@Column
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
}
