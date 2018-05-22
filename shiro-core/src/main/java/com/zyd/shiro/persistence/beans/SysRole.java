package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
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
public class SysRole extends AbstractDO {

    private static final long serialVersionUID = -7206345155723108987L;

    private String name;

    private String description;

    private Boolean available;

    @Transient private Integer selected;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(1 << 7);
        sb.append("SysRole{");
        sb.append("name=").append(name);
        sb.append(super.toString());
        sb.append(", description=").append(description);
        sb.append(", available=").append(available);
        sb.append(", selected=").append(selected);
        sb.append('}');
        return sb.toString();
    }
}
