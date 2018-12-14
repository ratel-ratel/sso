package com.nice.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangliang
 * @since 2017-08-28
 */

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_b")
public class UserB extends Model<UserB> {

    private static final long serialVersionUID = 1L;
	@TableField("id")
	private Long id;
    /**
     * 用户号码
     */
	@TableField("userNo")
	private String userNo;
    /**
     * 密码
     */
	@TableField("password")
	private String password;
    /**
     * 备注
     */
	@TableField("remark")
	private String remark;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
