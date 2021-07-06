package com.company.dao;

import com.company.api.dao.IServiceDao;
import com.company.model.Service;
import com.company.util.IdCreate;
import com.company.util.SerializationHandler;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDao extends AbstractDao<Service> implements IServiceDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

    private static ServiceDao INSTANCE;

    private ServiceDao() {
    }

    public static ServiceDao getServiceDao() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceDao();
            INSTANCE.init();
        }
        return INSTANCE;
    }

    private void init() {
        List<Service> services = SerializationHandler.deserialize(Service.class);
        this.saveAll(services);
        IdCreate.setServiceId(services.size()+1L);
    }

    @Override
    public Service update(Service entity) {
        Service service = getById(entity.getId());
        LOGGER.log(Level.INFO, String.format("Update Service"));
        service.setName(entity.getName());
        service.setPrice(entity.getPrice());
        return service;
    }
}
