
package com.nicky.shiro.framework.object;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"id"})
public abstract class AbstractDO implements Serializable {

    private static final long serialVersionUID = -1679770357930200297L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Date createTime;

    protected Date updateTime;

}
