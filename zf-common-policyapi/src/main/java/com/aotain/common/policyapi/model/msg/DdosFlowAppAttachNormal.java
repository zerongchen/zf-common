package com.aotain.common.policyapi.model.msg;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DdosFlowAppAttachNormal {

    private Integer appAttackType;

    private Integer attackThreshold;

    private Integer attackControl;
}
