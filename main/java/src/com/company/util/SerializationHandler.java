package com.company.util;

import com.company.exceptions.ServiceException;
import com.company.model.AEntity;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class SerializationHandler {

    private static final Logger LOGGER = Logger.getLogger(SerializationHandler.class.getName());

    @SafeVarargs
    public static void serialize(List<? extends AEntity>... entities) {
        List<List<? extends AEntity>> marshalingList = List.of(entities);
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(PropertiesHandler.getPathToFile()))) {
            outStream.writeObject(marshalingList);
        } catch (IOException e) {
            LOGGER.warning("Serialization to file failed:" + e.getLocalizedMessage());
            throw new ServiceException(e);
        }

    }

    public static <T> List<T> deserialize(Class<T> clazz) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PropertiesHandler.getPathToFile()))) {
            List<List<? extends AEntity>> list = (List<List<? extends AEntity>>) inputStream.readObject();
            for (List<? extends AEntity> entities : list) {
                if (!entities.isEmpty() && entities.get(0).getClass().equals(clazz)) {
                    return (List<T>) entities;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.warning("Deserialization failed " + e.getLocalizedMessage());
            throw new ServiceException(e);
        }
        return Collections.emptyList();
    }
}
