/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.usd.btl.jsf;

import edu.usd.btl.toolbridge.ToolBridgeEntity;
import edu.usd.btl.toolbridge.ToolBridgeEntityFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bill Conn <Bill.Conn@usd.edu>
 */
@ManagedBean(name="toolBridgeController")
public class ToolBridgeController {
    
    @EJB
    private ToolBridgeEntityFacade tBEF;
    
    private List<ToolBridgeEntity> allBridgeEntities;

    public List<ToolBridgeEntity> getAllBridgeEntities() {
        return allBridgeEntities;
    }

    public void setAllBridgeEntities(List<ToolBridgeEntity> allBridgeEntities) {
        this.allBridgeEntities = allBridgeEntities;
    }
    
    
}
