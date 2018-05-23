package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
import com.zyd.shiro.persistence.adapter.AdapteeTarget;
import com.zyd.shiro.persistence.adapter.StringAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends AbstractDO implements AdapteeTarget {

    private static final long serialVersionUID = -7206345155723108987L;

    private String name;

    private String description;

    private Boolean available;

    @Transient private Integer selected;


    @Override
    public String toString() {
        AdapteeTarget target = new StringAdapter<>(this, 1 << 7);
        return target.toString();
    }
}
