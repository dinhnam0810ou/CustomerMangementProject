<h3>Search</h3>
<form id="searchBar" method="get" action="/customer/list">
          <input class="SearchInput" type="text" id="name" name="kw_name" placeholder="Enter customer name to search"/>
          <br />
          <select class="SearchInput" name="kw_gender" id="gender">
              <option value="">Trong</option>
			  <option value="Nam">Nam</option>
			  <option value="Nu">Nu</option>
			  <option value="Khac">Khac</option>
		  </select>
          <br />
          <input class="SearchInput" type="text" id="phone" name="kw_phone" placeholder="Enter customer phone to search"/>
          <br /> 
          <select class="SearchInput" name="kw_membership_level" id="membershipLevel">
              <option value="">Trong</option>
              <option value="Silver">Silver</option>
              <option value="Gold">Gold</option>
              <option value="Platinum">Platinum</option>     
          </select>
		  <br /> 
          <input type="submit" value="SEARCH CUSTOMER">
   </form>