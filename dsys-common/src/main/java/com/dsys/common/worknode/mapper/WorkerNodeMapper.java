package com.dsys.common.worknode.mapper;

import com.dsys.common.worknode.bean.WorkerNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xfvape.uid.worker.entity.WorkerNodeEntity;

/**     
 * @discription 唯一编码Mapper
 * @author shilp       
 * @created 2020/1/2  16:28
 * @Param 
 * @Return 
*/
@Mapper
public interface WorkerNodeMapper{

    /**
     * Get {@link WorkerNodeEntity} by node host
     * 
     * @param host host
     * @param port port
     * @return WorkerNodeEntity
     */
    public WorkerNodeEntity getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port);

    /**
     * Add {@link WorkerNodeEntity}
     * @param workerNodeEntity workerNodeEntity
     */
    public void addWorkerNode(WorkerNode workerNodeEntity);

}
