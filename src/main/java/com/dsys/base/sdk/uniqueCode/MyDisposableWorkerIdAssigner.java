package com.dsys.base.sdk.uniqueCode;

import com.dsys.base.bean.WorkerNode;
import com.dsys.base.dao.WorkerNodeDao;
import com.xfvape.uid.utils.DockerUtils;
import com.xfvape.uid.utils.NetUtils;
import com.xfvape.uid.worker.DisposableWorkerIdAssigner;
import com.xfvape.uid.worker.WorkerIdAssigner;
import com.xfvape.uid.worker.WorkerNodeType;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
public class MyDisposableWorkerIdAssigner implements WorkerIdAssigner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisposableWorkerIdAssigner.class);

    @Resource
    private WorkerNodeDao workerNodeDAO;

    public WorkerNodeDao getWorkerNodeDAO() {
        return workerNodeDAO;
    }

    public void setWorkerNodeDAO(WorkerNodeDao workerNodeDAO) {
        this.workerNodeDAO = workerNodeDAO;
    }

    /**
     * Assign worker id base on database.<p>
     * If there is host name & port in the environment, we considered that the node runs in Docker container <br>
     * Otherwise, the node runs on an actual machine.
     * </p>
     * @return assigned worker id
     */
    @Transactional
    public long assignWorkerId() {
        // build worker node entity
        WorkerNode workerNode = buildWorkerNode();

        // add worker node for new (ignore the same IP + PORT)
        workerNodeDAO.addWorkerNode(workerNode);
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
