package com.spring.jamplan.manageplan;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class PlanMainHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
   
   @Override
   public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, 
         WebSocketHandler wsHandler, Map<String, Object> map) throws Exception {
   
      System.out.println("Before Handshake");
      
      ServletServerHttpRequest ssreq = (ServletServerHttpRequest)request;
      HttpServletRequest req = ssreq.getServletRequest();
      
      String id = (String)req.getSession().getAttribute("id");
      System.out.println(id);
      String teamName = (String)req.getSession().getAttribute("teamName");
      String planName = (String)req.getSession().getAttribute("planName");
      map.put("id", id);
      map.put("teamName", teamName);
      map.put("planName", planName);
      System.out.println("HttpSession에 저장된 id: " + id);
      System.out.println("HttpSession에 저장된 teamName: " + teamName);
      System.out.println("HttpSession에 저장된 planname: " + planName);
      
      return super.beforeHandshake(request, response, wsHandler, map);
   }
   
   
   @Override
   public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, 
         WebSocketHandler wsHandler, Exception ex) {
      System.out.println("After Handshake");
      super.afterHandshake(request, response, wsHandler, ex);
   }
}