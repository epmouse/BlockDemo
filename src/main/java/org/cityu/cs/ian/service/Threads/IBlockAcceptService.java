package org.cityu.cs.ian.service.Threads;

import org.cityu.cs.ian.model.bean.BlockBean;

public interface IBlockAcceptService {
    boolean verifyBlock(BlockBean blockBean);
    void interruptPow();
    boolean saveBlock(BlockBean blockBean);

}
