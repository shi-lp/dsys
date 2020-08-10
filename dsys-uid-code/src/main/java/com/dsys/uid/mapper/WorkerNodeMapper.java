package com.dsys.uid.mapper;

import com.dsys.api.bean.worknode.WorkerNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @discription 唯一编码Mapper
 * @author shilp       
 * @created 2020/1/2  16:28
 * @Param 
 * @Return 
*/
public interface WorkerNodeMapper{

    /**
     * Get {@link WorkerNode} by node host
     * 
     * @param host host
     * @param port port
     * @return WorkerNodeEntity
     */
    public WorkerNode getWorkerNodeByHostPort(@Param("host") String host,@Param("port") String port);

    /**
     * Add {@link WorkerNode}
     * @param  workerNodeEntity
     */
    public void addWorkerNode(WorkerNode workerNodeEntity);

}
