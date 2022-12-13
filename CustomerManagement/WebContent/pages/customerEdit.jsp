<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Patients</title>
        <style type="text/css">
        #editCustomerForm {
            padding-top: 20px;
        }
        .Input {
           margin-bottom: 10px;
        }
        .LabelWrapper {
            width:150px;
        }
        </style>
    </head>
    <body>
       <%@include file="header.jsp" %>
       <h3>Edit Customer</h3>
       <form id="editCustomerForm" method="post" action="/customer/update">
           <label for="name">Enter customer id:</label>
           <br />
           <input class="Input" type="number" id="id" name="id" value="${customer.id}"/>
           <br />
           <label for="name">Enter customer name:</label>
           <br />
           <input class="Input" type="text" id="name" name="name" value="${customer.name}"/>
           <br />
           <label for="name">Enter customer gender:</label>
           <br />
           <input class="Input" type="text" id="gender" name="gender" value="${customer.gender}"/>
           <br />
           <label for="name">Enter customer phone:</label>
           <br />
           <input class="Input" type="text" id="phone" name="phone" value="${customer.phoneNumber}"/>
           <br />
           <label for="name">Enter customer address:</label>
           <br />
           <input class="Input" type="text" id="address" name="address" value="${customer.address}"/>
           <br />
           <label for="name">Enter customer email:</label>
           <br />
           <input class="Input" type="email" id="email" name="email" value="${customer.email}"/>
           <br />
           <label for="name">Enter customer point:</label>
           <br />
           <input class="Input" type="number" id="point" name="point" value="${customer.point}"/>
           <br />
           <input type="submit" value="EDIT CUSTOMER" />
       </form>
         
    </body>
</html>
   