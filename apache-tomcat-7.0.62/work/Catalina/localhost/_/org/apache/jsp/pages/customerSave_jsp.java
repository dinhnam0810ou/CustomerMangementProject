/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.62
 * Generated at: 2022-12-19 07:13:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class customerSave_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/tags/addCustomerForm.tag", Long.valueOf(1671434021000L));
    _jspx_dependants.put("/pages/header.jsp", Long.valueOf(1670481399000L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("        <title>Customer</title>\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("        #addCustomerForm {\n");
      out.write("            padding-top: 4px;  \n");
      out.write("                \n");
      out.write("        }\n");
      out.write("        .form {\n");
      out.write("            display: flex;\n");
      out.write("            flex-direction:row;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("        }\n");
      out.write("        .Input {\n");
      out.write("           margin-bottom: 5px;\n");
      out.write("           width: 300px;\n");
      out.write("        }\n");
      out.write("        .LabelWrapper {\n");
      out.write("            width:150px;\n");
      out.write("        }\n");
      out.write("        .SearchInput {\n");
      out.write("            width: 220px;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("            margin-right: 10px;\n");
      out.write("        }\n");
      out.write("        .SubjectLabel { \n");
      out.write("            width: 200px;\n");
      out.write("        }\n");
      out.write("        .pagination a {\n");
      out.write("          color: black;\n");
      out.write("          float: left;\n");
      out.write("          padding: 3px 6px;\n");
      out.write("          text-decoration: none;\n");
      out.write("          transition: background-color .3s;\n");
      out.write("        }\n");
      out.write("        .pagination a.item {\n");
      out.write("          background-color: white;\n");
      out.write("          color: red;\n");
      out.write("          border:1px solid black;\n");
      out.write("        }\n");
      out.write("        .Combobox {\n");
      out.write("          width: 100px;\n");
      out.write("          margin-right: 10px;\n");
      out.write("        }\n");
      out.write("        .ErrorLabel {\n");
      out.write("           color: red;\n");
      out.write("        }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       ");
      out.write("<h1>Customer Management</h1>");
      out.write("  \n");
      out.write("       ");
      if (_jspx_meth_tag_005faddCustomerForm_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_tag_005faddCustomerForm_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tag:addCustomerForm
    org.apache.jsp.tag.webaddCustomerForm_tag _jspx_th_tag_005faddCustomerForm_005f0 = (new org.apache.jsp.tag.webaddCustomerForm_tag());
    _jsp_instancemanager.newInstance(_jspx_th_tag_005faddCustomerForm_005f0);
    _jspx_th_tag_005faddCustomerForm_005f0.setJspContext(_jspx_page_context);
    _jspx_th_tag_005faddCustomerForm_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tag_005faddCustomerForm_005f0);
    return false;
  }
}
