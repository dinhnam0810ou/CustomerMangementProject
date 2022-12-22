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
        #addCustomerForm {
            padding-top: 4px;  
                
        }
        .form {
            display: flex;
            flex-direction:row;
            margin-bottom: 5px;
        }
        .Input {
           margin-bottom: 5px;
           width: 300px;
        }
        .LabelWrapper {
            width:150px;
        }
        .SearchInput {
            width: 220px;
            margin-bottom: 5px;
            margin-right: 10px;
        }
        .SubjectLabel { 
            width: 200px;
        }
        .Pagination a {
          color: black;
          float: left;
          padding: 3px 6px;
          text-decoration: none;
          transition: background-color .3s;
        }
        .Pagination a.item {
          background-color: white;
          color: red;
          border:1px solid black;
        }
        .Combobox {
          width: 100px;
          margin-right: 10px;
        }
        .ErrorLabel {
           color: red;
        }
        </style>
    </head>
    <body>
       <%@include file="header.jsp" %>
       <tag:searchBar></tag:searchBar>
       <button><a href="/customer/save">ADD CUSTOMER</a></button>     
       <tag:customerList></tag:customerList>   
    </body>
</html>