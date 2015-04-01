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
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import edu.usd.btl.toolbets.ToolBetsEntity;
import edu.usd.btl.toolbets.ToolBetsEntityFacade;

/**
 *
 * @author Bill Conn <Bill.Conn@usd.edu>
 */
@RunWith(Arquillian.class)
public class ToolBetsEntityTest {
    /*
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
*/
    public ToolBetsEntityTest() {

    }

    @Deployment
    public static JavaArchive createDeployment() {
        FileAsset persistenceXml = new FileAsset(
                new File("src/test/resources/META-INF/jpa-test-persistence.xml"));
        return ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(persistenceXml, "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    private UserTransaction txn;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testCreate() throws Exception {
        ToolBetsEntity tBE = new ToolBetsEntity();
        tBE.setName("Test Name");
        tBE.setVersion("1.0");
        tBE.setSummary("Test Summary");
        tBE.setBets("Test BETS data".getBytes());

        assertNull(tBE.getId());

        try {
            this.txn.begin();
            this.em.persist(tBE);
            this.txn.commit();
        } catch (Exception e) {
            this.txn.rollback();
            throw new Exception(e.fillInStackTrace());
        }

        assertNotNull(tBE.getId());
    }
}
