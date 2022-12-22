<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Ticket Order</h3>
<form id="addTicketOrderForm" method="post" action="/order/save">     
       <div class="Entry">
           <label class="SubjectLabel" for="name">Movie Name(*):</label>
           <select class="Combobox" name="movieId" id="movieId">
              <c:forEach items="${movies}" var="movie">
                <option value="${movie.id}">${movie.name} - Price: ${movie.price} VND</option>
             </c:forEach>
          </select>
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
          <input type="radio" id="noCustomerRadio" name="option" value="false" checked="true" >
          <label for="noCustomerRadio">Add empty customer</label>&ensp;
          <input type="radio" id="exsitingCustomerRadio" name="option" value="true" >
          <label for="exsitingCustomerRadio">Select existing customer</label>&ensp;
          <select class="Combobox" name="customerId" id="customerCombobox">
             <c:forEach items="${customers}" var="c">
                <option value="${c.id}">${c.name} - ${c.gender} - ${c.phoneNumber} - ${c.ticketFree} free ticket</option>
             </c:forEach>
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
	       <input type="submit" value="SAVE ORDER">
	       <button><a href="/customer/save" style="text-decoration: none">NEW CUSTOMER</a></button>
	       <button><a href="/customer/list" style="text-decoration: none">CANCEL</a></button>
      </div>
</form>