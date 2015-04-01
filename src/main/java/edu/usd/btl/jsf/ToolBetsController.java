/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.btl.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import edu.usd.btl.toolbets.ToolBetsEntity;
import edu.usd.btl.toolbets.ToolBetsEntityFacade;

/**
 *
 * @author Bill Conn <Bill.Conn@usd.edu>
 */
@ManagedBean(name = "toolBetsController")
@RequestScoped
public class ToolBetsController {

    @EJB
    private ToolBetsEntityFacade tBEF;

    private List<ToolBetsEntity> allToolBets;

    /**
     * Creates a new instance of ToolBetsController
     */

    public ToolBetsController() {
    }

    public List<ToolBetsEntity> getAllToolBets() {
        return tBEF.findAll();
    }

    public void setAllToolBets(List<ToolBetsEntity> allToolBets) {
        this.allToolBets = allToolBets;
    }

    public void makeTestToolBets() {
        System.out.println("Making Test Tool Bets");
        ToolBetsEntity foo = new ToolBetsEntity();
        foo.setName("Test Name");
        foo.setSummary("Test Summary");
        foo.setVersion("1.0");
        foo.setBets("blah".getBytes());
        tBEF.create(foo);
    }

}
