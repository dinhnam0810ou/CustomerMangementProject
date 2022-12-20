<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Search</h3>
<form id="searchBar" method="get" action="/customer/list" accept-charset="UTF-8">
          <input class="SearchInput" type="text" id="name" name="kw_name" placeholder="Enter customer name to search"/>

          <select  class="Combobox" name="kw_gender" id="gender">
              <option value="">All gender</option>
              <c:forEach items="${genders}" var="gender">
                    <option value="${gender}">${gender}</option>
              </c:forEach>
		  </select>

          <input class="SearchInput" type="text" id="phone" name="kw_phone" placeholder="Enter customer phone to search"/>
         
          <select class="Combobox" name="kw_membership_level" id="membershipLevel">
              <option value="">All membership level</option>
              <c:forEach items="${membershipLevels}" var="membershipLevel">
                    <option value="${membershipLevel}">${membershipLevel}</option>
              </c:forEach>     
          </select>
		  <br /> 
          <input type="submit" value="SEARCH CUSTOMER">
   </form>
   <br>
   <br>