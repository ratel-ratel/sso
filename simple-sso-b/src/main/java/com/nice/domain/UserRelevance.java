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
@TableName("user_relevance")
public class UserRelevance extends Model<UserRelevance> {

    private static final long serialVersionUID = 1L;
	@TableField("id")
	private Long id;
    /**
     * 平台编号
     */
	@TableField("userNO_A")
	private String userNOA;
    /**
     * 用户号码
     */
	@TableField("userNO_B")
	private String userNOB;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
