package com.dsys.uid.sdk.uniquecode;

import com.dsys.api.bean.worknode.WorkerNode;
import com.dsys.uid.mapper.WorkerNodeMapper;
import com.xfvape.uid.utils.DockerUtils;
import com.xfvape.uid.utils.NetUtils;
import com.xfvape.uid.worker.WorkerIdAssigner;
import com.xfvape.uid.worker.WorkerNodeType;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Title: MyDisposableWorkerIdAssigner
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/1/16 17:07
 */
@Component
public class MyDisposableWorkerIdAssigner implements WorkerIdAssigner {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyDisposableWorkerIdAssigner.class);

    @Autowired
    private WorkerNodeMapper workerNodeMapper;

    public WorkerNodeMapper getWorkerNodeMapper () {
        return workerNodeMapper;
    }

    public void setWorkerNodeMapper (WorkerNodeMapper workerNodeMapper) {
        this.workerNodeMapper = workerNodeMapper;
    }

    
    @Override
    public long assignWorkerId() {
        WorkerNode workerNode = buildWorkerNode();
        workerNodeMapper.addWorkerNode(workerNode);
        LOGGER.info("Add worker node:" + workerNode);
        return workerNode.getId();
    }

    /**
     * Build worker node entity by IP and PORT
     */
    private WorkerNode buildWorkerNode() {
        WorkerNode WorkerNode = new WorkerNode();
        if (DockerUtils.isDocker()) {
            WorkerNode.setType(WorkerNodeType.CONTAINER.value());
            WorkerNode.setHostName(DockerUtils.getDockerHost());
            WorkerNode.setPort(DockerUtils.getDockerPort());
        } else {
            WorkerNode.setType(WorkerNodeType.ACTUAL.value());
            WorkerNode.setHostName(NetUtils.getLocalAddress());
            WorkerNode.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(100000));
        }
        return WorkerNode;
    }
}
