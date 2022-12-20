<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Customer</title>
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
        .ErrorLabel {
           color: red;
        }
        </style>
    </head>
    <body>
       <%@include file="header.jsp" %>
       <h3>Edit Customer</h3>
       <form id="editCustomerForm" method="post" action="/customer/update"> 
           <input type="hidden" class="Input" type="text" id="id" name="id" value="${customer.id}"/> 
           <c:if test="${errorId != null}">
                 <label class="ErrorLabel">${errorId}</label>
           </c:if> 
           <br>
           <label for="name">Customer name:</label>
           <br />
           <input class="Input" type="text" id="name" name="name" value="${customer.name}"/>
           <c:if test="${errorName != null}">
                 <label class="ErrorLabel">${errorName}</label>
           </c:if> 
           <br />
           <div class="form">
              <label class="SubjectLabel" for="name">Customer gender:</label>
              <br>
              <select class="Combobox" name="gender" id="gender" style="width:180px;">
                  <c:forEach items="${genders}" var="gender">
                    <option value="${gender}">${gender}</option>
                  </c:forEach>
              </select>  
              <c:if test="${errorGender != null}">
                 <label class="ErrorLabel">${errorGender}</label>
              </c:if>           
            </div>   
            <br>
           <label for="name">Customer phone:</label>
           <br />
           <input class="Input" type="text" id="phone" name="phone" value="${customer.phoneNumber}"/>
           <c:if test="${errorPhone != null}">
                 <label class="ErrorLabel">${errorPhone}</label>
           </c:if>   
           <br />
           <label for="name">Customer address:</label>
           <br />
           <input class="Input" type="text" id="address" name="address" value="${customer.address}"/>
           <br />
           <label for="name">Customer email:</label>
           <br />
           <input class="Input" type="email" id="email" name="email" value="${customer.email}"/>
           <br />
           <label for="name">Customer point:</label>
           <br />
           <input class="Input" type="number" id="point" name="point" value="${customer.point}"/>
            <c:if test="${errorPoint != null}">
                 <label class="ErrorLabel">${errorPoint}</label>
           </c:if>
           <br />
           <input type="submit" value="EDIT CUSTOMER" />
       </form>
         
    </body>
</html>
   