package com.company;

import com.company.dao.GuestDao;
import com.company.dao.RoomDao;
import com.company.dao.ServiceDao;
import com.company.dao.util.EntityManagerUtil;
import com.company.dao.util.EntityMapper;
import com.company.injection.ApplicationContext;
import com.company.injection.HotelApplication;
import com.company.model.Guest;
import com.company.ui.UiApplication;


public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        HotelApplication.run(context);
        UiApplication.run(context);
      //  EntityManager entityManager = enu.getEntityManager();
     // GuestDao guestDao = new GuestDao();
//RoomDao roomDao = new RoomDao(entityManager);
//ServiceDao serviceDao = new ServiceDao(entityManager);
//        Guest g1 = new Guest();
//        g1.setName("New guest");
//        g1.setDaysOfStay(3);
    //   enu.beginTransaction();
      //  Guest g1 = guestDao.getById(6L);
       // g1.setName("Paul");
     //   guestDao.delete(g1);

        //guestDao.save(g1);
     //  System.out.println(guestDao.getAll());
     //   System.out.println(roomDao.getAll());
       //enu.commit();
       // enu.beginTransaction();
      // System.out.println(serviceDao.getAll());
    //    enu.commit();
       // System.out.println(guestDao.getAll());

    }
}