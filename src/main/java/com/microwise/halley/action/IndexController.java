package com.microwise.halley.action;

import com.bastengao.struts2.freeroute.Results;
import com.bastengao.struts2.freeroute.annotation.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author gaohui
 * @date 13-10-25 14:21
 */
@Component
@Scope("prototype")
public class IndexController {

    @Route("/index")
    public String index(){
        return Results.jsp("index_.jsp");
    }
}
