   <form id="addCustomerForm" method="post" action="/customer/add">
          <label for="name">Enter customer name:</label>
          <br />
          <input class="Input" type="text" id="name" name="name" autocomplete="off"/>
          <br />
          <select class="Input" name="gender" id="gender" style="width: 100px;">
              <option value="Nam">Nam</option>
              <option value="Nu">Nu</option>
              <option value="Khac">Khac</option>
          </select>
          <br />
          <label for="name">Enter customer phone:</label>
          <br />
          <input class="Input" type="text" id="phone" name="phone" autocomplete="off"/>
          <br />
          <label for="name">Enter customer address:</label>
          <br />
          <input class="Input" type="text" id="address" name="address" autocomplete="off"/>
          <br />
          <label for="name">Enter customer email:</label>
          <br />
          <input class="Input" type="email" id="email" name="email" autocomplete="off"/>
          <br />
          <input type="submit" value="ADD CUSTOMER">
   </form>