package com.dsys.base.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dsys.base.bean.WorkerNode;
import com.xfvape.uid.worker.entity.WorkerNodeEntity;

/**     
 * @discription ${在此输入一句话描述此文件的作用}
 * @author shilp       
 * @created 2020/1/2  16:28
 * @Param 
 * @Return 
*/
@Mapper
public interface WorkerNodeDao{

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
