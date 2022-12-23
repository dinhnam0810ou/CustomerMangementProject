<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Ticket Order List: ${ticketOrders.size()}</h2>
<div>
    <button><a href="/order/view" style="text-decoration: none">TICKET ORDER VIEW</a></button>
</div><br>
<table width="1200" border="1">
     <thead>
         <tr>
             <th width="100">Order Date</th>
             <th width="100">Quantity</th>
             <th width="100">Unit Price</th>
             <th width="200">Customer</th>
             <th width="300">Movie</th>
             <th width="60">Action</th>
         </tr>
     </thead>
     <tbody>
         <c:forEach items="${ticketOrders}" var="t">
         <tr>
             <td><c:out value="${t.orderDate}" /></td>
             <td><c:out value="${t.quantity}" /></td>
             <td><c:out value="${t.unitPrice}" /></td>
             <td><c:out value="${t.customer.name}" /></td>
             <td><c:out value="${t.movie.name}" /></td>
             <td>
                 <a href="/order/delete?id=${t.id}">Delete</a>
                 <a href="/order/edit?id=${t.id}">Edit</a>
             </td>
         </tr>
        </c:forEach>
     </tbody>
</table>
<div class="Pagination">
     <c:forEach begin="1" end="${((counter/pageSize)-(counter/pageSize)%1) + 1}" var="i">
        <a class="item" href="/order/list?page=${i}">${i}</a>
    </c:forEach>
</div>
