package com.xing.mongodbtest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xing.mongodbtest.model.User;
import com.xing.mongodbtest.repository.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)  
//@ContextConfiguration(locations = "classpath:spring-context.xml")  
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:springmvc-servlet.xml"})  
public class UserDaoTestController {  

  // 在任何需要记录日志的类中  
  private static Logger logger = Logger.getLogger(UserDaoTestController.class);  

  @Resource  
  private UserDao userDao;  

  /** 
   * 测试Spring IOC的开发环境 
   */  
//  @Test  
  public void springIoc() {  
      ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");  
      TestSpringIocController test = (TestSpringIocController) context.getBean("test");  
      test.print();  
  }  

//  @Test
  public void save() {  
      User user = new User();  
      user.setUsername("test1");  
      user.setPassword("55555");  
      userDao.store(user);  

      User user2 = userDao.findOneByUsername("test1");  
      logger.info("-------获取账户密码:-------" + user2.getPassword());  
  }  

//  @Test
  public void update() {  
      User user = userDao.findOneByUsername("test1");  
      logger.info("-------更新之前账户密码:-------" + user.getPassword());  

      user.setPassword("666666");
      userDao.updateFirst(user);

      logger.info("-------更新之后账户密码:-------" + user.getPassword());  
  }  

//  @Test  
  public void findAll() {
	  
      List<User> lists = userDao.findAll();  
      for (User user : lists) {  
          logger.info("-------user遍历111:-------" + user.getUsername());  
      }  

      List<User> lists2= userDao.findList(0,1);  
      for (User user : lists2) {  
          logger.info("-------user遍历222:-------" + user.getUsername());  
      }  
  }  

  @Test
  public void delete() {  
      userDao.delete(userDao.findOneByUsername("test1").getId());  
  }  

}
