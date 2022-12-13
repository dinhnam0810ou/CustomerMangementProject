<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Customer</title>
    </head>
    <body>
       <%@include file="header.jsp" %>
       <div>
            <div>Id: <b>${customer.id}</b></div>
            <div>Name: <b>${customer.name}</b></div>
            <div>Gender: <b>${customer.gender}</b></div>
            <div>Phone: <b>${customer.phoneNumber}</b></div>
            <div>Address: <b>${customer.address}</b></div>
            <div>Email: <b>${customer.email}</b></div>
            <div>Point: <b>${customer.point}</b></div>
            <div>Membership Level: <b>${membership_level}</b></div>
            <div>Ticket Free: <b>${ ticket_free}</b></div>  
        </div>   
       <a href="/customer/list">View Customer List</a>
    </body>
</html>