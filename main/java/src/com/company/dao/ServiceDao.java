package com.company.dao;

import com.company.api.dao.IServiceDao;
import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;
import com.company.model.Service;
import com.company.service.SerializationService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ServiceDao extends AbstractDao<Service> implements IServiceDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

    @Autowired
    private static ServiceDao INSTANCE;

    public static ServiceDao getServiceDao() {
        INSTANCE.init();
        return INSTANCE;
    }

    private void init() {
        SerializationService serializationService = new SerializationService();
        this.saveAll(serializationService.deserializeServiceFromFile());
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
