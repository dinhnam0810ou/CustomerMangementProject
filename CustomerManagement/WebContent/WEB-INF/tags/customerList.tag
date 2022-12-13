<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Customer List: ${customers.size()}</h2>
       <table width="1200" border="1">
            <thead>
                <tr>
                    <th width="50">ID</th>
                    <th width="300">Name</th>
                    <th width="200">Gender</th>
                    <th width="200">Phone Number</th>
                    <th width="200">Address</th>
                    <th width="200">Email</th>
                    <th width="200">Membership Level</th>
                    <th width="150">Point</th>
                    <th width="200">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td><c:out value="${customer.name}" /></td>
                    <td><c:out value="${customer.gender}" /></td>
                    <td><c:out value="${customer.phoneNumber}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.membershipLevel}" /></td>
                    <td><c:out value="${customer.point}" /></td>
                    <td>
                        <a href="/customer/view?id=${customer.id}">View</a>
                        <a href="/customer/delete?id=${customer.id}">Delete</a>
                        <a href="/customer/edit?id=${customer.id}">Edit</a>
                    </td>
                </tr>
               </c:forEach>
            </tbody>
       </table>
       <div class="pagination">
	        <c:forEach begin="1" end="${(counter/pageSize)+(1-((counter/pageSize)%1))%1}" var="i">
	          <a class="item" href="/customer/list?page=${i}">${i}</a>
	        </c:forEach>
       </div>