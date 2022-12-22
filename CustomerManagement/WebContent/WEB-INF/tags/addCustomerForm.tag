   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <h3>Save Customer</h3>
   <form id="addCustomerForm" method="post" action="/customer/save">
          <c:if test="${errorId != null}">
                <label class="ErrorLabel">${errorId}</label>
           </c:if>
           <c:if test="${customer.id != null}">
                <input type="hidden" class="Input" type="text" id="id" name="id" value="${customer.id}"/> 
           </c:if>      
          <div class="Entry">
	          <label class="SubjectLabel" for="name">Customer name(*):</label>
	          <input class="Input" type="text" id="name" name="name" autocomplete="off" placeholder="Enter customer name" value="${customer.name}"/>
	          <c:if test="${errorName != null}">
	             <label class="ErrorLabel">${errorName}</label>
	          </c:if>     
          </div>       
          <div class="Entry">
	          <label class="SubjectLabel" for="name">Customer gender(*):</label>
	          <select class="Combobox" name="gender" id="gender">
	             <c:forEach items="${genders}" var="gender">
                    <option value="${gender}">${gender}</option>
                 </c:forEach>
	          </select>
	          <c:if test="${errorGender != null}">
                 <label class="ErrorLabel">${errorGender}</label>
              </c:if>          
          </div>     
          <div class="Entry">
	          <label class="SubjectLabel" for="name">Customer phone(*):</label>
	          <input class="Input" type="text" id="phone" name="phone" autocomplete="off" placeholder="Enter customer phone" value="${customer.phoneNumber}"/>
	          <c:if test="${errorPhone != null}">
                 <label class="ErrorLabel">${errorPhone}</label>
              </c:if>       
          </div>    
          <div class="Entry">
	          <label class="SubjectLabel" for="name">Customer address:</label>
	          <input class="Input" type="text" id="address" name="address" autocomplete="off" placeholder="Enter customer address" value="${customer.address}"/>
          </div> 
          <div class="Entry">
	          <label class="SubjectLabel" for="name">Customer email:</label>
	          <input class="Input" type="email" id="email" name="email" autocomplete="off" placeholder="Enter customer email" value="${customer.email}"/>
          </div>
          <div class="Entry">
              <label class="SubjectLabel" for="name">Customer point:</label>
              <input class="Input" type="number" id="point" name="point" autocomplete="off" placeholder="Enter customer point" value="${customer.point}"/>
              <c:if test="${errorPoint != null}">
                 <label class="ErrorLabel">${errorPoint}</label>
              </c:if>
          </div><br>
          <div>
          <input type="submit" value="SAVE CUSTOMER">
          <button><a href="/order/view" style="text-decoration: none">ORDER VIEW</a></button>
          <button><a href="/customer/list" style="text-decoration: none">CANCEL</a></button>
          </div>
   </form>