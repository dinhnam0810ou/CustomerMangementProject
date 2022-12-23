<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Ticket Order</h3>
<form id="addTicketOrderForm" method="post" action="/order/save">     
       <input type="hidden" class="Input" type="text" id="ticketOrderId" name="orderId" value="${ticketOrder.id}"/> 
       <div class="Entry">
           <label class="SubjectLabel" for="name">Movie Name(*):</label>
           <select class="Combobox" name="movieId" id="movieSelector">
             <c:forEach items="${movies}" var="movie">
                <option value="${movie.id}" id="movieOption${movie.id}" unitPrice="${movie.price}">${movie.name}</option>
             </c:forEach>
          </select>
      </div>
      <div class="Entry">
          <label class="SubjectLabel" for="name">Price(*):</label> 
          <input class="Input" type="number" id="price" />
      </div> 
      <div class="Entry">
          <label class="SubjectLabel" for="name">Quantity(*):</label> 
          <input class="Input" type="number" id="quantity" name="quantity" value="1" autocomplete="off" />
          <c:if test="${Quantity != null}">
             <label class="ErrorLabel">${Quantity}</label>
          </c:if>       
      </div>      
      <div class="Entry" id="customerSelectionEntry">
          <label class="SubjectLabel" for="name">Customer Information(*):</label>
          <input type="radio" id="noCustomerRadio" name="customerRadioChecked" value="false" checked="true" >
          <label for="noCustomerRadio">No customer</label>&ensp;
          <input type="radio" id="exsitingCustomerRadio" name="customerRadioChecked" value="true" >
          <label for="exsitingCustomerRadio">Select existing customer</label>&ensp;&ensp;
          <select class="Combobox" name="customerId" id="customerCombobox">
             <c:if test="${ticketOrder != null}">
                <option value="${ticketOrder.customer.id}">${ticketOrder.customer.name} - ${ticketOrder.customer.gender} - ${ticketOrder.customer.phoneNumber} - ${ticketOrder.customer.ticketFree} free ticket</option>
             </c:if>
             <c:if test="${ticketOrder == null}">
	             <c:forEach items="${customers}" var="c">
	                <option value="${c.id}">${c.name} - ${c.gender} - ${c.phoneNumber} - ${c.ticketFree} free ticket</option>
	             </c:forEach>
             </c:if>
          </select>
       </div>
       <div class="Entry">
          <label class="SubjectLabel" for="name">Use Free Ticket:</label>
          <input class="Input" type="number" id="freeTicket" name="freeTicket" value="0" autocomplete="off"/>
          <c:if test="${FreeTicket != null}">
             <label class="ErrorLabel">${FreeTicket}</label>
          </c:if>       
      </div> 
      <div>
           <button><a href="/customer/save" style="text-decoration: none">NEW CUSTOMER</a></button>&ensp;&ensp;&ensp;
	       <input type="submit" value="SAVE ORDER">
	       <button><a href="/customer/list" style="text-decoration: none">CANCEL</a></button>
      </div>
</form>