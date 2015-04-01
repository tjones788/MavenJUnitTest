/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.btl.toolbets.tests;

import java.io.File;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import edu.usd.btl.toolbets.ToolBetsEntity;
import edu.usd.btl.toolbets.ToolBetsEntityFacade;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Bill Conn <Bill.Conn@usd.edu>
 */
@RunWith(Arquillian.class)
public class ToolBetsEntityFacadeTest {
    
     @BeforeClass
     public static void setUpClass() {
     }

     @AfterClass
     public static void tearDownClass() {
     }

     @Before
     public void setUp() {
         List<ToolBetsEntity> list = tbeFacade.findAll();
         for(ToolBetsEntity i : list) {
             tbeFacade.remove(i);
         }
     }

     @After
     public void tearDown() {
     }
     

    public ToolBetsEntityFacadeTest() {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        FileAsset persistenceXml = new FileAsset(
                new File("src/test/resources/META-INF/jpa-test-persistence.xml"));
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ToolBetsEntity.class)
                .addClass(ToolBetsEntityFacade.class)
                .addAsManifestResource(persistenceXml, "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    //@Inject
    //private UserTransaction txn;
    
    @Inject
    private ToolBetsEntityFacade tbeFacade;

    @PersistenceContext
    private EntityManager em;

    //@Inject
    //private ToolBetsEntityFacade tBEF;
    
    @Test
    public void testCreate() throws Exception {
        ToolBetsEntity tBE = new ToolBetsEntity();
        ToolBetsEntity tBE2 = new ToolBetsEntity();
        ToolBetsEntity tBE3 = new ToolBetsEntity();
        tBE.setName("Test Name");
        tBE2.setName("Test Name 2");
        tBE3.setName("Test Name 3");
        tBE.setVersion("1.0");
        tBE2.setVersion("1.0");
        tBE3.setVersion("1.0");
        tBE.setSummary("Test Summary");
        tBE2.setSummary("Test Summary");
        tBE3.setSummary("Test Summary");
        tBE.setBets("Test BETS data".getBytes());
        tBE2.setBets("Test BETS data".getBytes());
        tBE3.setBets("Test BETS data".getBytes());
        
        tbeFacade.create(tBE);
        tbeFacade.create(tBE2);
        tbeFacade.create(tBE3);
        
        assertEquals(tbeFacade.count(),3);

    }
    
    @Test
    public void testFindAll() throws Exception {
        List<ToolBetsEntity> list = tbeFacade.findAll();
        assertEquals(list.size(),0);
    }
}
